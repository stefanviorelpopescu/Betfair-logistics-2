package org.digitalstack.logistics.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.digitalstack.logistics.dao.dto.OrderCreateDto;
import org.digitalstack.logistics.dao.dto.OrderDto;
import org.digitalstack.logistics.exception.DateRangeException;
import org.digitalstack.logistics.exception.InvalidDestinationException;
import org.digitalstack.logistics.service.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/add")
    public ResponseEntity<List<OrderDto>> addOrders(@Valid @RequestBody List<OrderCreateDto> createDtos)
            throws DateRangeException, InvalidDestinationException {
        List<OrderDto> orderDtos = ordersService.addOrders(createDtos);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @ExceptionHandler({ConstraintViolationException.class, DateRangeException.class, InvalidDestinationException.class})
    public ResponseEntity<String> handleConstraintViolationException(Exception ex) {
        return new ResponseEntity<>("Bad request: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
