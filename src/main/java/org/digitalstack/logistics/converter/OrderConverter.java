package org.digitalstack.logistics.converter;

import org.digitalstack.logistics.dao.dto.OrderDto;
import org.digitalstack.logistics.dao.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {

    public static OrderDto entityToDto (Order order) {
        return OrderDto.builder()
                .withId(order.getId())
                .withStatus(order.getStatus())
                .withDestination(order.getDestination().getName())
                .withDeliveryDate(order.getDeliveryDate())
                .build();
    }

    public static List<OrderDto> entityListToDtoList(List<Order> orders) {
        return orders.stream()
                .map(OrderConverter::entityToDto)
                .collect(Collectors.toList());
    }
}
