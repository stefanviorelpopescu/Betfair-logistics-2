package org.digitalstack.logistics.dao.dto;

import lombok.Builder;
import lombok.Data;
import org.digitalstack.logistics.dao.model.OrderStatus;

@Data
@Builder(setterPrefix = "with")
public class OrderDto {

    Long id;
    Long deliveryDate;
    OrderStatus status;
    String destination;

}
