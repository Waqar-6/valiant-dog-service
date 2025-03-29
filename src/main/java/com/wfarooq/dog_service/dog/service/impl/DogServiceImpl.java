package com.wfarooq.dog_service.dog.service.impl;

import com.wfarooq.dog_service.dog.dto.request.CreateDogRequest;
import com.wfarooq.dog_service.dog.dto.response.DogResponseDto;
import com.wfarooq.dog_service.dog.entity.Dog;
import com.wfarooq.dog_service.shared.exception.ResourceAlreadyExistsException;
import com.wfarooq.dog_service.shared.exception.ResourceNotFoundException;
import com.wfarooq.dog_service.dog.mapper.DogMapper;
import com.wfarooq.dog_service.dog.repository.DogRepository;
import com.wfarooq.dog_service.dog.service.IDogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class DogServiceImpl implements IDogService {

    private static final Logger log = LoggerFactory.getLogger(DogServiceImpl.class);
    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }


    @Override
    public void createDog(CreateDogRequest request) {

        if (dogRepository.existsByMicrochipNumber(request.getMicrochipNumber())) {
            throw new ResourceAlreadyExistsException("Dog", "microchipNumber", request.getMicrochipNumber());
        }
        if (dogRepository.existsByRegistrationNumber(request.getRegistrationNumber()))
            throw new ResourceAlreadyExistsException("Dog", "registrationNumber", request.getRegistrationNumber());

        Dog newDog = DogMapper.mapToDogEntity(request, new Dog());
        dogRepository.save(newDog);
        log.info("created and saved new dog : {}", newDog.getName());



    }

    @Override
    public DogResponseDto fetchDogByRegistrationNumber(String registrationNumber) {
        Dog dog = dogRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Dog", "registrationNumber", registrationNumber));
        log.info("fetched {} with the given registration number {}", dog.getName(), registrationNumber);
        return DogMapper.mapToDogResponseDto(dog, new DogResponseDto());
    }

    @Override
    public List<DogResponseDto> fetchAllDogs() {
        List<Dog> allDogs = dogRepository.findAll();

        log.info("returned a list of all dogs");
        return allDogs.stream()
                .map(dog -> DogMapper.mapToDogResponseDto(dog, new DogResponseDto())).toList();
    }

    @Override
    public boolean updateDog(CreateDogRequest request, UUID dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new ResourceNotFoundException("Dog", "id", dogId.toString()));
        DogMapper.mapToDogEntity(request, dog);
        log.info("dog {} updated and saved",dog.getName());
        dogRepository.save(dog);
        return true;
    }

    @Override
    public boolean deleteDog(UUID dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new ResourceNotFoundException("Dog", "id", dogId.toString()));
        log.info("deleted {} from database", dogId);
        dogRepository.delete(dog);
        return true;
    }
}
