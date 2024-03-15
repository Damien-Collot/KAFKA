package com.example.kafkaBDD.repository;

import com.example.kafkaBDD.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByLastNameOrBirthName(String lastName, String birthName);

}
