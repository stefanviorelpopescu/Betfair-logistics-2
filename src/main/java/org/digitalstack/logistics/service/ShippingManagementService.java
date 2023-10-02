package org.digitalstack.logistics.service;

import lombok.extern.slf4j.Slf4j;
import org.digitalstack.logistics.dao.model.Destination;
import org.digitalstack.logistics.dao.model.Order;
import org.digitalstack.logistics.dao.model.OrderStatus;
import org.digitalstack.logistics.dao.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShippingManagementService {
    private final CompanyInformationService companyInformationService;
    private final ShippingService shippingService;
    private final OrdersRepository ordersRepository;

    public ShippingManagementService(CompanyInformationService companyInformationService, ShippingService shippingService, OrdersRepository ordersRepository) {
        this.companyInformationService = companyInformationService;
        this.shippingService = shippingService;
        this.ordersRepository = ordersRepository;
    }

    public String startNewDay() {

        companyInformationService.advanceDate();

        long currentDate = companyInformationService.getCurrentDateAsMilis();
        List<Order> ordersForToday = ordersRepository.findAllByDeliveryDate(currentDate);

        ordersForToday.forEach(order -> order.setStatus(OrderStatus.DELIVERING));
        ordersRepository.saveAll(ordersForToday);

        Map<Destination, List<Order>> ordersByDestination = ordersForToday.stream()
                .collect(Collectors.groupingBy(Order::getDestination));

        ordersByDestination.entrySet().forEach(shippingService::shipToDestination);

        String response = String.format("Started shipping %d orders to %d destinations.", ordersForToday.size(), ordersByDestination.keySet().size());
        log.info(response);
        return response;
    }

}
