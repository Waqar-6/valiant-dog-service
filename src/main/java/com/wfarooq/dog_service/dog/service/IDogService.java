package com.wfarooq.dog_service.dog.service;

import com.wfarooq.dog_service.dog.dto.request.CreateDogRequest;
import com.wfarooq.dog_service.dog.dto.response.DogResponseDto;

import java.util.List;
import java.util.UUID;

public interface IDogService {

    void createDog (CreateDogRequest request);
    DogResponseDto fetchDogByRegistrationNumber (String registrationNumber);
    List<DogResponseDto> fetchAllDogs ();
    boolean updateDog (CreateDogRequest request, UUID dogId);
    boolean deleteDog (UUID dogId);

}
