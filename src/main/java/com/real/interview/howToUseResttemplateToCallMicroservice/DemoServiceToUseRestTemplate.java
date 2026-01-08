package com.real.interview.howToUseResttemplateToCallMicroservice;

import java.util.*;
import org.springframework.http.*;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.web.client.*;

@Service
public class DemoServiceToUseRestTemplate {

    private final RestTemplate restTemplate;

    public DemoServiceToUseRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MyDataClass> fetchAllData() {
        String url = "https://api.example.com/data";
        // Set headers (optional)
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // Create the request entity (headers and body)
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        //?
        restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        //?
        //?
        String urlWithVar = "https://api.example.com/data/{id}";
        restTemplate.exchange(urlWithVar, HttpMethod.GET, entity, String.class, 111);//111 is for {id}
        //?
        //?
        //Note the use of ParameterizedTypeReference for generic types (List<T>)
        ParameterizedTypeReference<List<MyDataClass>> myDataClass =
                new ParameterizedTypeReference<List<MyDataClass>>() {};

        ResponseEntity<List<MyDataClass>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                myDataClass
        );
        //?

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("API call failed with status: " + response.getStatusCode());
        }
    }
}
