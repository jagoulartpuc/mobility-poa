package com.dimed.mobility.service;

import com.dimed.mobility.domain.BusLine;
import com.dimed.mobility.domain.Coordenada;
import com.dimed.mobility.domain.Itinerary;
import com.dimed.mobility.http.RestClient;
import com.dimed.mobility.repository.BusLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusLineService {

    @Autowired
    private RestClient client;

    @Autowired
    private BusLineRepository repository;

    public List<BusLine> insertFromClient() {
        return repository.insert(client.getBusLines("http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o"));
    }

    public BusLine insert(BusLine busLine) {
        return repository.insert(busLine);
    }

    public void delete(BusLine busLine) {
        repository.delete(busLine);
    }

    public List<BusLine> getAll() {
        return repository.findAll();
    }

    public BusLine update(BusLine busLine) {
        return repository.save(busLine);
    }

    public BusLine getById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public List<BusLine> getLinesByName(String nome) {
        return repository.findAll().stream()
                .filter(b -> b.getNome().equals(nome))
                .collect(Collectors.toList());
    }

    public List<BusLine> getCloseLines(String latitude, String longitude, double raio, List<Itinerary> itineraries) {
        List<BusLine> busLines = new ArrayList<>();
        for (Itinerary itinerary: itineraries) {
            for (Coordenada coordenada : itinerary.getCoordenadas()) {
                double distance = calculateDistance(latitude, longitude, coordenada);
                if (distance <= raio) {
                    busLines.add(getById(itinerary.getIdlinha()));
                }
            }
        }
        return busLines.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public double calculateDistance(String latitude, String longitude, Coordenada coordenada) {
        double dla = (Double.parseDouble(latitude) - Double.parseDouble(coordenada.getLat()))/100;
        double dlo = (Double.parseDouble(longitude) - Double.parseDouble(coordenada.getLng()))/100;

        double x = (dla/60) * 1852;
        double y = (dlo/60) * 1852;

        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
}
