package com.bme.pricebckservice.repository;

import com.bme.pricebckservice.domain.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDBRepository extends MongoRepository<Price, String> {
}
