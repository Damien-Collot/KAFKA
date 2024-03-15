package com.example.kafkaClient.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movement_id")
    private Long movementId;
    @ManyToOne
    @JoinColumn(name = "stay_id", nullable = false)
    private Stay stay;
    @Column
    private String service;
    @Column
    private String room;
    @Column
    private String bed;
    @Column(name="movement_date")
    private Date movementDate;

    // Getters and setters
}
