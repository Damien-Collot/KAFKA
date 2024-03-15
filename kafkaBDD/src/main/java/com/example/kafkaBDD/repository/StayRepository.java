package com.example.kafkaBDD.repository;

import com.example.kafkaBDD.model.Person;
import com.example.kafkaBDD.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StayRepository extends JpaRepository<Stay, Long> {
    List<Stay> findAllByPerson(Person person);
}
