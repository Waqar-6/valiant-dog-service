package com.wfarooq.dog_service.controller;

import com.wfarooq.dog_service.constants.DogConstants;
import com.wfarooq.dog_service.dto.request.CreateDogRequest;
import com.wfarooq.dog_service.dto.response.DogResponseDto;
import com.wfarooq.dog_service.dto.response.ResponseDto;
import com.wfarooq.dog_service.service.IDogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/dogs", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class DogController {

    private final IDogService dogService;

    public DogController(IDogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createDog (@Valid @RequestBody CreateDogRequest request) {
        dogService.createDog(request);
        return new ResponseEntity<>(new ResponseDto(DogConstants.STATUS_201, DogConstants.MESSAGE_201), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DogResponseDto>> fetchAllDogs () {
        List<DogResponseDto> listOfDogs = dogService.fetchAllDogs();
        return new ResponseEntity<>(listOfDogs, HttpStatus.OK);
    }

    @GetMapping("/{registrationNumber}")
    public ResponseEntity<DogResponseDto> fetchDogByRegistrationNumber (@PathVariable String registrationNumber) {
        DogResponseDto dogResponseDto = dogService.fetchDogByRegistrationNumber(registrationNumber);
        return new ResponseEntity<>(dogResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{dogId}")
    public ResponseEntity<ResponseDto> updateDog (@Valid @RequestBody CreateDogRequest request, @PathVariable UUID dogId) {
        boolean isUpdated = dogService.updateDog(request, dogId);
        return isUpdated ? new ResponseEntity<>(new ResponseDto(DogConstants.STATUS_200, DogConstants.MESSAGE_200_UPDATE), HttpStatus.OK) :
                new ResponseEntity<>(new ResponseDto(DogConstants.STATUS_417, DogConstants.MESSAGE_417_UPDATE), HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/{dogId}")
    public ResponseEntity<ResponseDto> updateDog (@PathVariable UUID dogId) {
        boolean isDeleted = dogService.deleteDog(dogId);
        return isDeleted ? new ResponseEntity<>(new ResponseDto(DogConstants.STATUS_200, DogConstants.MESSAGE_200_DELETE), HttpStatus.OK) :
                new ResponseEntity<>(new ResponseDto(DogConstants.STATUS_417, DogConstants.MESSAGE_417_DELETE), HttpStatus.EXPECTATION_FAILED);
    }



}
