package com.kafka.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="person_id")
    private Integer personId;
    @Column
    private String pid;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="birth_name")
    private String birthName;
    @Column(name="date_of_birth")
    private Date dateOfBirth;
    @Column
    private String gender;
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Address> addresses;

    public Person(JSONObject personObject){
        this.pid = (String) personObject.get("pid");
        this.firstName = (String) personObject.get("firstName");
        this.lastName = (String) personObject.get("lastName");
        this.birthName = (String) personObject.get("birthName");
        this.dateOfBirth = (Date) personObject.get("dateOfBirth");
        this.gender = (String) personObject.get("gender");
    }
}
