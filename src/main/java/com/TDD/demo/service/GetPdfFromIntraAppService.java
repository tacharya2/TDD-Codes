package com.TDD.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetPdfFromIntraAppService {
    private final String intraUrl = "http://localhost:8080/api/v1/download/";

    @Autowired
    private RestTemplate restTemplate;

    public byte [] getPdfFileFromIntraApp(Long id){

        // Build the URL for the GET request
        String url = intraUrl + id;

        // Make the GET request
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);

        // Check if the request was successful (HTTP status 200 OK)

        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else{
            return null;
        }
    }
}