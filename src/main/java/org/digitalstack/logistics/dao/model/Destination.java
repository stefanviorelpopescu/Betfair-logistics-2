package org.digitalstack.logistics.dao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    @Column(nullable = false)
    Integer distance;

}
