package org.digitalstack.logistics.controller;

import jakarta.validation.Valid;
import org.digitalstack.logistics.dao.dto.DestinationDto;
import org.digitalstack.logistics.dao.model.Destination;
import org.digitalstack.logistics.exception.DestinationNotFoundException;
import org.digitalstack.logistics.service.DestinationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
@Validated
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/{id}")
    public Destination getDestinationById(@PathVariable(name = "id") Long destinationId) throws DestinationNotFoundException {
        return destinationService.findById(destinationId);
    }

    @DeleteMapping("/{id}")
    public Destination deleteDestination(@PathVariable(name = "id") Long destinationId) throws DestinationNotFoundException {
        return destinationService.deleteDestination(destinationId);
    }

    @PutMapping
    public DestinationDto createDestination(@RequestBody @Valid DestinationDto destinationDto) {
        return destinationService.upsertDestination(destinationDto);
    }

}
