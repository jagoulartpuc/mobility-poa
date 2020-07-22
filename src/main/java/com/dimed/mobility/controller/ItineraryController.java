package com.dimed.mobility.controller;

import com.dimed.mobility.domain.BusLine;
import com.dimed.mobility.domain.Itinerary;
import com.dimed.mobility.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @PostMapping
    public Itinerary postItinerary(
            @RequestBody Itinerary itinerary
    ) {
        return itineraryService.insert(itinerary);
    }

    @GetMapping("/add-client")
    public Itinerary addItineraryFromClient() {
        return itineraryService.insertFromClient();
    }

    @GetMapping
    public List<Itinerary> getItineraries() {
        return itineraryService.getAll();
    }

    @PutMapping
    public Itinerary updateItinerary(
            @RequestBody Itinerary itinerary
    ) {
        return itineraryService.update(itinerary);
    }

    @DeleteMapping
    public boolean deleteItinerary(
            @RequestParam String idlinha
    ) {
        Itinerary itinerary = itineraryService.getById(idlinha);
        itineraryService.delete(itinerary);
        return true;
    }

}
