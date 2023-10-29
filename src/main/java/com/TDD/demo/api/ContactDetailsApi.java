package com.TDD.demo.api;

import com.TDD.demo.model.ContactDetailsDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/contact")
public interface ContactDetailsApi {

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/details")
    ResponseEntity<Object> createContactDetails(@Valid @RequestBody ContactDetailsDto contactDetailsDto, BindingResult bindingResult);
}
