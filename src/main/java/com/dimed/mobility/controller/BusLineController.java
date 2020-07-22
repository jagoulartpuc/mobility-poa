package com.dimed.mobility.controller;

import com.dimed.mobility.domain.BusLine;
import com.dimed.mobility.domain.Itinerary;
import com.dimed.mobility.service.BusLineService;
import com.dimed.mobility.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/busline")
public class BusLineController {

    @Autowired
    private BusLineService busLineService;

    @Autowired
    private ItineraryService itineraryService;

    @PostMapping
    public BusLine postBusLine(
            @RequestBody BusLine busLine
    ) {
        return busLineService.insert(busLine);
    }

    @GetMapping("/add-all")
    public List<BusLine> addBusLinesFromClient() {
        return busLineService.insertFromClient();
    }

    @GetMapping
    public List<BusLine> getBusLines() {
        return busLineService.getAll();
    }

    @GetMapping("/per-name")
    public List<BusLine> getBusLinesByName(
            @RequestParam String nome
    ) {

        return busLineService.getLinesByName(nome);
    }

    @PutMapping
   public BusLine updateBusLine(
           @RequestBody BusLine busLine
    ) {
       return busLineService.update(busLine);
   }

    @DeleteMapping
    public boolean deleteBusLine(
            @RequestParam String id
    ) {
        BusLine busLine = busLineService.getById(id);
        busLineService.delete(busLine);
        return true;
    }

    @GetMapping("/close-lines")
    public List<BusLine> getCloseLines(
            @RequestParam String latitude,
            @RequestParam String longitude,
            @RequestParam String raio
    ) {
        return busLineService.getCloseLines(latitude, longitude, Double.parseDouble(raio), itineraryService.getAll());
    }

}
