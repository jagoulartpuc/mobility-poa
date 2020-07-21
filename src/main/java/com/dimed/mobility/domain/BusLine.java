package com.dimed.mobility.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "busline")
public class BusLine {

    @Id
    private String id;
    private String codigo;
    private String nome;
}
