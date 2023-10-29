package com.TDD.demo.controller;

import com.TDD.demo.api.ContactDetailsApi;
import com.TDD.demo.model.ContactDetailsDto;
import com.TDD.demo.service.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ContactDetailsController implements ContactDetailsApi {

    private final ContactDetailsService contactDetailsService;

    @Autowired
    public ContactDetailsController(ContactDetailsService contactDetailsService) {
        this.contactDetailsService = contactDetailsService;
    }

    @Override
    public ResponseEntity<Object> createContactDetails(ContactDetailsDto contactDetailsDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(Collections.singletonMap("errors", errorMessages));
        }
        contactDetailsService.postUserEntity((contactDetailsDto));
        String successMessage = "Hello " + contactDetailsDto.getLName() + ", " + contactDetailsDto.getFName() + "./n Your message is successfully sent for consideration";
        return ResponseEntity.ok(Collections.singletonMap("message", successMessage));
    }
}