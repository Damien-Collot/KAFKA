package com.kafka.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "address_type")
    private String addressType;

    public Address(JSONObject addressObject) {
        this.addressLine1 = addressObject.getString("addressLine1");
        this.addressLine2 = addressObject.optString("addressLine2");
        this.city = addressObject.getString("city");
        this.state = addressObject.optString("state");
        this.zipCode = addressObject.optString("zipCode");
        this.country = addressObject.getString("country");
        this.addressType = addressObject.optString("addressType");
    }

}
