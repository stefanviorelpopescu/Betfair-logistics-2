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
    private final OrdersService ordersService;

    public ShippingManagementService(CompanyInformationService companyInformationService, ShippingService shippingService, OrdersRepository ordersRepository, OrdersService ordersService) {
        this.companyInformationService = companyInformationService;
        this.shippingService = shippingService;
        this.ordersRepository = ordersRepository;
        this.ordersService = ordersService;
    }

    public String startNewDay() {

        companyInformationService.advanceDate();

        long currentDate = companyInformationService.getCurrentDateAsMilis();
        List<Order> ordersForToday = ordersRepository.findAllByDeliveryDate(currentDate);

        int deliveringCount = ordersService.bulkUpdateOrderStatus(ordersForToday, OrderStatus.DELIVERING, true);

        Map<Destination, List<Order>> ordersByDestination = ordersForToday.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERING)
                .collect(Collectors.groupingBy(Order::getDestination));

        ordersByDestination.entrySet().forEach(shippingService::shipToDestination);

        String response = String.format("Started shipping %d orders to %d destinations.", deliveringCount, ordersByDestination.keySet().size());
        log.info(response);
        return response;
    }

}
