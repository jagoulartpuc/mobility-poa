package com.dimed.mobility.service;

import com.dimed.mobility.domain.BusLine;
import com.dimed.mobility.http.RestClient;
import com.dimed.mobility.repository.BusLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusLineService {

    @Autowired
    private RestClient client;

    @Autowired
    private BusLineRepository repository;

    public List<BusLine> insertAll() {
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
}
