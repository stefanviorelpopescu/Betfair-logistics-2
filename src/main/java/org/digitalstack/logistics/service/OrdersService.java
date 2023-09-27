package org.digitalstack.logistics.service;

import org.digitalstack.logistics.converter.OrderConverter;
import org.digitalstack.logistics.dao.dto.OrderCreateDto;
import org.digitalstack.logistics.dao.dto.OrderDto;
import org.digitalstack.logistics.dao.model.Destination;
import org.digitalstack.logistics.dao.model.Order;
import org.digitalstack.logistics.dao.repository.DestinationRepository;
import org.digitalstack.logistics.dao.repository.OrdersRepository;
import org.digitalstack.logistics.exception.DateRangeException;
import org.digitalstack.logistics.exception.InvalidDestinationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final CompanyInformationService companyInformationService;
    private final OrdersRepository ordersRepository;
    private final DestinationRepository destinationRepository;


    public OrdersService(CompanyInformationService companyInformationService,
                         OrdersRepository ordersRepository,
                         DestinationRepository destinationRepository) {
        this.companyInformationService = companyInformationService;
        this.ordersRepository = ordersRepository;
        this.destinationRepository = destinationRepository;
    }

    public List<OrderDto> addOrders(List<OrderCreateDto> ordersDtos) throws DateRangeException, InvalidDestinationException {

        Map<Long, Destination> destinationsById = destinationRepository.findAll().stream()
                .collect(Collectors.toMap(Destination::getId, Function.identity()));

        validateDtos(ordersDtos, destinationsById.keySet());

        List<Order> orders = new ArrayList<>();
        ordersDtos.forEach(orderDto -> orders.add(getBasicOrderFromDto(orderDto, destinationsById)));

        List<Order> savedOrders = ordersRepository.saveAll(orders);
        return OrderConverter.entityListToDtoList(savedOrders);
    }

    private static Order getBasicOrderFromDto(OrderCreateDto orderDto, Map<Long, Destination> destinationsById) {
        Long deliveryDate = orderDto.getDeliveryDate();
        Long destinationId = orderDto.getDestinationId();
        return new Order(deliveryDate, destinationsById.get(destinationId));
    }

    private void validateDtos(List<OrderCreateDto> ordersDtos, Set<Long> existingDestinationIds) throws DateRangeException, InvalidDestinationException {
        Long currentDate = companyInformationService.getCurrentDate();
        for (OrderCreateDto orderDto : ordersDtos) {
            if (orderDto.getDeliveryDate() <= currentDate) {
                throw new DateRangeException("Delivery date must be in the future!");
            }
            if (!existingDestinationIds.contains(orderDto.getDestinationId())) {
                throw new InvalidDestinationException("Invalid destination ID");
            }
        }
    }

}
