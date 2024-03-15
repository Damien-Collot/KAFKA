package com.example.kafkaBDD.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
@XmlRootElement(name = "global")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Global")
public class Global {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlElement
    private Integer id;
    @Column(name = "new_confirmed")
    @XmlElement
    private Integer newConfirmed;
    @Column(name = "total_confirmed")
    @XmlElement
    private Integer totalConfirmed;
    @Column(name = "new_deaths")
    @XmlElement
    private Integer newDeaths;
    @Column(name = "total_deaths")
    @XmlElement
    private Integer totalDeaths;
    @Column(name = "new_recovered")
    @XmlElement
    private Integer newRecovered;
    @Column(name = "total_recovered")
    @XmlElement
    private Integer totalRecovered;
    @Column(name = "date_maj")
    @XmlElement
    private Date dateMaj;

}
