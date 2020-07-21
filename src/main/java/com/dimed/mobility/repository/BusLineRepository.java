package com.dimed.mobility.repository;

import com.dimed.mobility.domain.BusLine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusLineRepository extends MongoRepository<BusLine, String> {
}
