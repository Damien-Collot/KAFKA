package com.example.kafkaBDD.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Movements(JSONObject movementObject) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.service = movementObject.optString("service");
        this.room = movementObject.optString("room");
        this.bed = movementObject.optString("bed");
        String dateStr = movementObject.optString("movementDate");
        if (!dateStr.isEmpty()) {
            this.movementDate = sdf.parse(dateStr);
        } else {
            this.movementDate = null;
        }
    }
}
