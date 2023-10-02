package org.digitalstack.logistics.service;

import lombok.extern.slf4j.Slf4j;
import org.digitalstack.logistics.dao.model.Destination;
import org.digitalstack.logistics.dao.model.Order;
import org.digitalstack.logistics.dao.model.OrderStatus;
import org.digitalstack.logistics.dao.repository.OrdersRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShippingService {

    private final OrdersService ordersService;
    private final OrdersRepository ordersRepository;

    public ShippingService(OrdersService ordersService, OrdersRepository ordersRepository) {
        this.ordersService = ordersService;
        this.ordersRepository = ordersRepository;
    }

    @Async("shippingExecutor")
    public void shipToDestination(Map.Entry<Destination, List<Order>> destinationListEntry) {

        Destination destination = destinationListEntry.getKey();
        List<Order> orders = destinationListEntry.getValue();

        log.info(String.format("Starting %d deliveries for %s for %d km on thread %s",
                orders.size(), destination.getName(), destination.getDistance(), Thread.currentThread().getName()));


        try {
            Thread.sleep(destination.getDistance() * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<Long> orderIds = orders.stream()
                .map(Order::getId)
                .toList();
        int updatedOrders = ordersService.bulkUpdateOrderStatus(orderIds, OrderStatus.DELIVERED);
        log.info(String.format("%d deliveries completed for %s", updatedOrders, destination.getName()));
        //TODO update company profit

        //TODO use bulk update in other places
        //TODO actuator

        //TODO swagger

        //TODO crud for destinations
    }
}
