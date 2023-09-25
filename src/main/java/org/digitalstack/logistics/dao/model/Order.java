package org.digitalstack.logistics.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long deliveryDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    OrderStatus status = OrderStatus.NEW;

    @Column(nullable = false)
    Long lastUpdated = System.currentTimeMillis();

    @ManyToOne
    @JoinColumn(name = "destination_id")
    Destination destination;

}
