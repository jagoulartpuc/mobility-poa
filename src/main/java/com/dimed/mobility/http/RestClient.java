package com.dimed.mobility.http;

import com.dimed.mobility.domain.BusLine;
import com.dimed.mobility.domain.Itinerary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@Data
@Component
public class RestClient {

    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    public RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public List<BusLine> getBusLines(String uri) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        rest.getMessageConverters().add(converter);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        var type = new ParameterizedTypeReference<List<BusLine>>() {};
        ResponseEntity<List<BusLine>> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, type);
        this.setStatus(responseEntity.getStatusCode());

        return responseEntity.getBody();
    }

    public Itinerary getItinerary(String uri) throws JsonProcessingException {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        rest.getMessageConverters().add(converter);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        var type = new ParameterizedTypeReference<Itinerary>() {};
        ResponseEntity<Itinerary> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, type);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(requestEntity));
        Iterator<String> fieldNames = root.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if(isNumeric(fieldName)) {
                JsonNode node = root.get(fieldName);

                System.out.println(node.asText());
            }
        }
        this.setStatus(responseEntity.getStatusCode());

        return responseEntity.getBody();
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
