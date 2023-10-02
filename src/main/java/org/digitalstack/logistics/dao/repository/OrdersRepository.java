package org.digitalstack.logistics.dao.repository;

import org.digitalstack.logistics.dao.model.Destination;
import org.digitalstack.logistics.dao.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByDeliveryDateAndDestinationIn(Long daliveryDate, List<Destination> destination);
    List<Order> findAllByDeliveryDate(Long daliveryDate);
}
