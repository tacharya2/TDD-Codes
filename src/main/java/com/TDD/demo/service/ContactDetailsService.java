package com.TDD.demo.service;

import com.TDD.demo.model.ContactDetailsDto;
import com.TDD.demo.repository.UserRepository;
import com.TDD.demo.repository.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public ContactDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> postUserEntity(ContactDetailsDto contactDetailsDto){
        UserEntity userEntity = new UserEntity();

        userEntity.setFName(contactDetailsDto.getFName());
        userEntity.setLName(contactDetailsDto.getLName());
        userEntity.setPhone(contactDetailsDto.getPhone());
        userEntity.setEmail(contactDetailsDto.getEmail());
        userEntity.setState(contactDetailsDto.getState());
        userEntity.setCity(contactDetailsDto.getCity());
        userEntity.setReason(contactDetailsDto.getReason());

        try{
            UserEntity user = userRepository.save(userEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Failed to create message for" + contactDetailsDto.getFName() + contactDetailsDto.getLName());
        }
    }
}