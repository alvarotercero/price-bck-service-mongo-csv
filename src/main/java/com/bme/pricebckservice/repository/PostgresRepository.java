package com.bme.pricebckservice.repository;

import com.bme.pricebckservice.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresRepository extends JpaRepository<Price, Long> {
}
