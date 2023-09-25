package org.digitalstack.logistics.dao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
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
