package org.digitalstack.logistics.converter;

import org.digitalstack.logistics.dao.dto.DestinationDto;
import org.digitalstack.logistics.dao.model.Destination;

public class DestinationConverter {

    public static DestinationDto modelToDto(Destination destination) {
        DestinationDto destinationDto = new DestinationDto();
        destinationDto.setId(destination.getId());
        destinationDto.setName(destination.getName());
        destinationDto.setDistance(destination.getDistance());
        return destinationDto;
    }

    public static Destination dtoToModel(DestinationDto destinationDto) {
        return Destination.builder()
                .id(destinationDto.getId())
                .name(destinationDto.getName())
                .distance(destinationDto.getDistance())
                .build();
    }

}
