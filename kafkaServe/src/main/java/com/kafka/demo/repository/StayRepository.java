package com.kafka.demo.repository;

import com.kafka.demo.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StayRepository extends JpaRepository<Stay, Long> {
}
