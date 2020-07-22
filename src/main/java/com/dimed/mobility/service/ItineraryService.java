package com.dimed.mobility.service;

import com.dimed.mobility.domain.BusLine;
import com.dimed.mobility.domain.Coordenada;
import com.dimed.mobility.domain.Itinerary;
import com.dimed.mobility.http.RestClient;
import com.dimed.mobility.repository.ItineraryRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItineraryService {

    @Autowired
    private RestClient client;

    @Autowired
    private ItineraryRepository repository;

    public Itinerary insertFromClient() {
        return repository.insert(convertJSON("http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5566"));
    }

    public Itinerary insert(Itinerary itinerary) {
        return repository.insert(itinerary);
    }

    public void delete(Itinerary itinerary) {
        repository.delete(itinerary);
    }

    public List<Itinerary> getAll() {
        return repository.findAll();
    }

    public Itinerary update(Itinerary itinerary) {
        return repository.save(itinerary);
    }

    public Itinerary getById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public Itinerary convertJSON(String uri) {
        String itineraryJSON = client.getItineraryJSON(uri);
        Gson gson = new Gson();
        Itinerary itinerary = gson.fromJson(itineraryJSON, Itinerary.class);
        itinerary.setCoordenadas(convertCoordenadasFromJSON(itineraryJSON));
        return itinerary;
    }

    public List<Coordenada> convertCoordenadasFromJSON(String json) {
        List<String> latitudes = new ArrayList<>();
        List<String> longitudes = new ArrayList<>();

        StringBuilder builder;
        for (int i = 0; i < json.length(); i++) {
           try {
               if (json.substring(i, i + 3).equals("lat")) {
                   builder = new StringBuilder();
                   while (isNumeric(Character.toString(json.charAt(i + 6))) || json.charAt(i + 6) == '-' || json.charAt(i + 6) == '.') {
                       builder.append(json.charAt(i + 6));
                       i++;
                   }
                   String latitude = builder.toString();
                   latitudes.add(latitude);
               }
               if (json.substring(i, i + 3).equals("lng")) {
                   builder = new StringBuilder();
                   while (isNumeric(Character.toString(json.charAt(i + 6))) || json.charAt(i + 6) == '-' || json.charAt(i + 6) == '.') {
                       builder.append(json.charAt(i + 6));
                       i++;
                   }
                   String longitude = builder.toString();
                   longitudes.add(longitude);
               }
           } catch (Exception e) {
               return createCoordenadas(latitudes, longitudes);
           }
        }

        return createCoordenadas(latitudes, longitudes);
    }

    public List<Coordenada> createCoordenadas(List<String> latitudes, List<String> longitudes) {
        List<Coordenada> coordenadas = new ArrayList<>();
        for(int i = 0; i < latitudes.size(); i++) {
            coordenadas.add(new Coordenada(latitudes.get(i), longitudes.get(i)));
        }
        return coordenadas;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
