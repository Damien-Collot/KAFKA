package com.example.kafkaBDD.repository;


import com.example.kafkaBDD.model.Global;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GlobalRepository extends JpaRepository<Global, Integer> {

    List<Global> findAllByOrderByDateMajDesc();
    Optional<Global> findGlobalByOrderByDateMajDesc();

}
