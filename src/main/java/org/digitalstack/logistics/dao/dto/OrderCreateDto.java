package org.digitalstack.logistics.dao.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCreateDto {

    @NotNull(message = "Destination ID must be provided")
    @Min(value = 0, message = "Destination ID must be positive")
    Long destinationId;

    @NotNull(message = "Delivery Date is required!")
    Long deliveryDate;

    //ONLY FOR DEMO
//    @AssertTrue(message = "Start date should be before end date")
//    public boolean isDeliveryDateValid() {
//        return destinationId < deliveryDate;
//    }
}
