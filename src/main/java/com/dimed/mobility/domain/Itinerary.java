package com.dimed.mobility.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Itinerary {

    private String idlinha;
    private String nome;
    private String codigo;
    private Map<String, Coordenadas> map;
}
