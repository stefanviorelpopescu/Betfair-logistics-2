package org.digitalstack.logistics.dao.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DestinationDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer distance;
}
