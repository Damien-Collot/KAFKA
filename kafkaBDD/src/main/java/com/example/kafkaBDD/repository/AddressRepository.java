package com.example.kafkaBDD.repository;

import com.example.kafkaBDD.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
