package org.digitalstack.logistics.service;

import org.digitalstack.logistics.converter.DestinationConverter;
import org.digitalstack.logistics.dao.dto.DestinationDto;
import org.digitalstack.logistics.dao.model.Destination;
import org.digitalstack.logistics.dao.repository.DestinationRepository;
import org.digitalstack.logistics.exception.DestinationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Destination findById(Long destinationId) throws DestinationNotFoundException {
        return destinationRepository.findById(destinationId)
                .orElseThrow(() -> new DestinationNotFoundException("Destination not found."));
    }

    public Destination deleteDestination(Long destinationId) throws DestinationNotFoundException {
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new DestinationNotFoundException("Destination not found."));
        destinationRepository.deleteById(destinationId);
        return destination;
    }

    public DestinationDto upsertDestination(DestinationDto destinationDto) {
        Destination destination = DestinationConverter.dtoToModel(destinationDto);
        return DestinationConverter.modelToDto(destinationRepository.save(destination));
    }
}
