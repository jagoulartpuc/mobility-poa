package com.dimed.mobility.http;

import com.dimed.mobility.domain.BusLine;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
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
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        rest.getMessageConverters().add(converter);
    }

    public List<BusLine> getBusLines(String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        var type = new ParameterizedTypeReference<List<BusLine>>() {};
        ResponseEntity<List<BusLine>> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, type);
        this.setStatus(responseEntity.getStatusCode());

        return responseEntity.getBody();
    }

    public String getItineraryJSON(String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> response = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        return response.getBody();
    }


}
