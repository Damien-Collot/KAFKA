package com.example.kafkaBDD.repository;

import com.example.kafkaBDD.model.Movements;
import com.example.kafkaBDD.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movements, Long> {
    List<Movements> findAllByStay(Stay stay);
}
