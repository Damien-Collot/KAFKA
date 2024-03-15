package com.kafka.demo.repository;

import com.kafka.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
