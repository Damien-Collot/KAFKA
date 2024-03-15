package com.kafka.demo.repository;

import com.kafka.demo.model.Movements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movements, Long> {
}
