package com.dimed.mobility.repository;

import com.dimed.mobility.domain.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends MongoRepository<Itinerary, String> {
}