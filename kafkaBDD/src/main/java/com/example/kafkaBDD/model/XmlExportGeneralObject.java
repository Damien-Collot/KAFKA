package com.example.kafkaBDD.model;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlExportGeneralObject {

    @XmlElementWrapper(name = "informations")
    @XmlElement(name = "information")
    private List<XmlExportUniqueObject> informations;

}
