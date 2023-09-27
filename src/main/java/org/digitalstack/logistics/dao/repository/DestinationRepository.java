package org.digitalstack.logistics.dao.repository;

import org.digitalstack.logistics.dao.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findAllByNameContainingIgnoreCase(String name);
}
