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

    public Stay(JSONObject stayObject) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.stayNumber = stayObject.getString("stayNumber");
        String startDateStr = stayObject.getString("startDate");
        this.startDate = sdf.parse(startDateStr);
        String endDateStr = stayObject.optString("endDate", null);
        if (endDateStr != null && !endDateStr.isEmpty()) {
            this.endDate = sdf.parse(endDateStr);
        }
    }
}
