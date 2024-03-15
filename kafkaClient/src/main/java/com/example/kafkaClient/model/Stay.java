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
public class Stay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stay_id")
    private Long stayId;

    @Column(nullable = false, length = 50, unique = true, name="stay_number")
    private String stayNumber;

    @Column(nullable = false, name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;
}
