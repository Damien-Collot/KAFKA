package com.example.kafkaBDD.model;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "information")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlExportUniqueObject {

    @XmlElement
    private Date dataDate;
    @XmlElementWrapper(name = "countries")
    @XmlElement(name = "country")
    private List<Countries> countriesInfo;
    @XmlElement
    private Global global;




}
