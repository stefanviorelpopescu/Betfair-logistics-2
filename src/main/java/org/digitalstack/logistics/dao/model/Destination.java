package org.digitalstack.logistics.dao.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "destinations")
@Getter
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    @Column(nullable = false)
    Integer distance;

}
