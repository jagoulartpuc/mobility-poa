package com.dimed.mobility.controller;

import com.dimed.mobility.domain.BusLine;
import com.dimed.mobility.domain.Itinerary;
import com.dimed.mobility.http.RestClient;
import com.dimed.mobility.service.BusLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/busline")
public class BusLineController {

    @Autowired
    private RestClient restClient;

    @Autowired
    private BusLineService busLineService;

    @PostMapping
    public BusLine postBusLine(
            @RequestBody BusLine busLine
    ) {
        return busLineService.insert(busLine);
    }

    @GetMapping("/add-all")
    public List<BusLine> addBusLinesFromClient() {
        return busLineService.insertAll();
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

    @GetMapping("/itinerary")
    public Itinerary getItinerary() throws JsonProcessingException {

        return restClient.getItinerary("http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5566");
    }
}
