package org.digitalstack.logistics.controller;

import org.digitalstack.logistics.service.ShippingManagementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingManagementService shippingManagementService;

    public ShippingController(ShippingManagementService shippingManagementService) {
        this.shippingManagementService = shippingManagementService;
    }

    @PostMapping("/new-day")
    public String startNewDay() {
        return shippingManagementService.startNewDay();
    }

}
