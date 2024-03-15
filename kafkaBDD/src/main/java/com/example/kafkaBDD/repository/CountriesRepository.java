package com.example.kafkaBDD.repository;


import com.example.kafkaBDD.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountriesRepository extends JpaRepository<Countries, Integer> {

    @Query("SELECT count(Distinct country) from Countries country")
    int findNoCountries();

    List<Countries> findAllByCountryIgnoreCaseOrderByDateMajDesc(String country);
    List<Countries> findAllByOrderByDateMajDesc();

}
