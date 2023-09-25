package org.digitalstack.logistics.service;

import org.digitalstack.logistics.converter.OrderConverter;
import org.digitalstack.logistics.dao.dto.OrderCreateDto;
import org.digitalstack.logistics.dao.dto.OrderDto;
import org.digitalstack.logistics.dao.model.Destination;
import org.digitalstack.logistics.dao.model.Order;
import org.digitalstack.logistics.dao.repository.DestinationRepository;
import org.digitalstack.logistics.dao.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final DestinationRepository destinationRepository;


    public OrdersService(OrdersRepository ordersRepository, DestinationRepository destinationRepository) {
        this.ordersRepository = ordersRepository;
        this.destinationRepository = destinationRepository;
    }

    public List<OrderDto> addOrders(List<OrderCreateDto> ordersDtos) {
        List<Order> orders = new ArrayList<>();
        for (OrderCreateDto ordersDto : ordersDtos) {
            Optional<Destination> destination = destinationRepository.findById(ordersDto.getDestinationId());
            if (destination.isPresent()) {
                Order order = new Order();
                order.setDeliveryDate(ordersDto.getDeliveryDate());
                order.setDestination(destination.get());
                orders.add(order);
            } else {
                //TODO
            }
        }
        List<Order> savedOrders = ordersRepository.saveAll(orders);
        return OrderConverter.entityListToDtoList(savedOrders);
    }

}
