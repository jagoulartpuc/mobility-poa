package com.dimed.mobility.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Itinerary {

    @Id
    private String idlinha;
    private String nome;
    private String codigo;
    private List<Coordenada> coordenadas;
}
