package com.wfarooq.dog_service.dog.service;

import com.wfarooq.dog_service.dog.constants.DogBreed;
import com.wfarooq.dog_service.dog.constants.GoverningBody;
import com.wfarooq.dog_service.dog.dto.request.CreateDogRequest;
import com.wfarooq.dog_service.dog.dto.response.DogResponseDto;
import com.wfarooq.dog_service.dog.entity.Dog;
import com.wfarooq.dog_service.shared.exception.ResourceAlreadyExistsException;
import com.wfarooq.dog_service.shared.exception.ResourceNotFoundException;
import com.wfarooq.dog_service.dog.repository.DogRepository;
import com.wfarooq.dog_service.dog.service.impl.DogServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DogServiceImplTest {

    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private DogServiceImpl dogService;

    @Test
    @DisplayName(value = "createDogTest")
    public void givenValidCreateDogRequest_shouldSaveDogEntityToDb () {
        CreateDogRequest request = new CreateDogRequest();
        request.setName("Max");
        request.setBreed(DogBreed.ROTTWEILER);
        request.setMicrochipNumber("MC123456");
        request.setRegistrationNumber("REG789456");
        request.setGoverningBody(GoverningBody.ADRK);
        request.setOwner("John");
        request.setBreeder("Jane");
        request.setDateOfBirth(LocalDate.of(2022, 1, 10));
        request.setFartherDog("Diesel");
        request.setMotherDog("Bella");

        when(dogRepository.existsByMicrochipNumber("MC123456")).thenReturn(false);
        when(dogRepository.existsByRegistrationNumber("REG789456")).thenReturn(false);

        dogService.createDog(request);

        verify(dogRepository, times(1)).save(any(Dog.class));
    }

    @Test
    @DisplayName(value = "Should throw exception when microchip number already exists")
    public void givenCreateDogRequestWithExistingMicrochipNumber_shouldThrowResourceAlreadyExistsException () {
        CreateDogRequest request = new CreateDogRequest();
        request.setName("Max");
        request.setBreed(DogBreed.ROTTWEILER);
        request.setMicrochipNumber("MC123456");
        request.setRegistrationNumber("REG789456");
        request.setGoverningBody(GoverningBody.ADRK);
        request.setOwner("John");
        request.setBreeder("Jane");
        request.setDateOfBirth(LocalDate.of(2022, 1, 10));
        request.setFartherDog("Diesel");
        request.setMotherDog("Bella");

        when(dogRepository.existsByMicrochipNumber("MC123456")).thenReturn(true);

        ResourceAlreadyExistsException exception = assertThrows(ResourceAlreadyExistsException.class, () -> {
            dogService.createDog(request);
        });

        verify(dogRepository, never()).save(any());
    }


    @Test
    @DisplayName(value = "Should throw exception when registration number already exists")
    public void givenCreateDogRequestWithExistingRegistrationNumber_shouldThrowResourceAlreadyExistsException () {
        CreateDogRequest request = new CreateDogRequest();
        request.setName("Max");
        request.setBreed(DogBreed.ROTTWEILER);
        request.setMicrochipNumber("MC123456");
        request.setRegistrationNumber("REG789456");
        request.setGoverningBody(GoverningBody.ADRK);
        request.setOwner("John");
        request.setBreeder("Jane");
        request.setDateOfBirth(LocalDate.of(2022, 1, 10));
        request.setFartherDog("Diesel");
        request.setMotherDog("Bella");

        when(dogRepository.existsByRegistrationNumber("REG789456")).thenReturn(true);

        ResourceAlreadyExistsException exception = assertThrows(ResourceAlreadyExistsException.class, () -> {
            dogService.createDog(request);
        });

        verify(dogRepository, never()).save(any());
    }

    @Test
    @DisplayName(value = "Should fetch dog based on registration number and return a dog response dto ")
    public void givenValidRegistrationNumber_shouldFetchDogFromDb_returnDogResponseDto () {

        Dog dog = new Dog();
        dog.setId(UUID.randomUUID());
        dog.setName("Max");
        dog.setBreed(DogBreed.ROTTWEILER);
        dog.setMicrochipNumber("MC123456");
        dog.setRegistrationNumber("REG789456");
        dog.setGoverningBody(GoverningBody.ADRK);
        dog.setOwner("John");
        dog.setBreeder("Jane");
        dog.setDateOfBirth(LocalDate.of(2022, 1, 10));
        dog.setFartherDog("Diesel");
        dog.setMotherDog("Bella");

        when(dogRepository.findByRegistrationNumber(dog.getRegistrationNumber())).thenReturn(Optional.of(dog));

        DogResponseDto result = dogService.fetchDogByRegistrationNumber(dog.getRegistrationNumber());

        assertEquals(dog.getName(), result.getName());
        assertEquals(dog.getFartherDog(), result.getFartherDog());
        assertEquals(dog.getMotherDog(), result.getMotherDog());

        verify(dogRepository, times(1)).findByRegistrationNumber(dog.getRegistrationNumber());

    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when dog is not found by registration number")
    public void givenInvalidRegistrationNumber_shouldThrowResourceNotFoundException() {
        String invalidRegNum = "NON_EXISTENT";

        when(dogRepository.findByRegistrationNumber(invalidRegNum)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            dogService.fetchDogByRegistrationNumber(invalidRegNum);
        });

        verify(dogRepository, times(1)).findByRegistrationNumber(invalidRegNum);
    }

    @Test
    @DisplayName(value = "Should find and update existing dog entity with new details held by Create dog request save to db and return true")
    public void givenValidCreateDogRequest_shouldUpdateExistingDogEntityAndSaveToDb_returnTrue () {
        CreateDogRequest updateDetails = new CreateDogRequest();
        updateDetails.setName("Max");
        updateDetails.setBreed(DogBreed.ROTTWEILER);
        updateDetails.setMicrochipNumber("MC123456");
        updateDetails.setRegistrationNumber("REG789456");
        updateDetails.setGoverningBody(GoverningBody.ADRK);
        updateDetails.setOwner("John");
        updateDetails.setBreeder("Jane");
        updateDetails.setDateOfBirth(LocalDate.of(2022, 1, 10));
        updateDetails.setFartherDog("Diesel");
        updateDetails.setMotherDog("Bella");


        UUID dogId = UUID.randomUUID();
        Dog existingDog = new Dog();
        existingDog.setId(dogId);
        existingDog.setName("John");
        existingDog.setBreed(DogBreed.ROTTWEILER);
        existingDog.setMicrochipNumber("MC123456");
        existingDog.setRegistrationNumber("REG789456");
        existingDog.setGoverningBody(GoverningBody.ADRK);
        existingDog.setOwner("Tim");
        existingDog.setBreeder("Princess");
        existingDog.setDateOfBirth(LocalDate.of(2022, 1, 10));
        existingDog.setFartherDog("Joe");
        existingDog.setMotherDog("Niki");

        when(dogRepository.findById(existingDog.getId())).thenReturn(Optional.of(existingDog));

       boolean result = dogService.updateDog(updateDetails, existingDog.getId());

       assertTrue(result);

       verify(dogRepository, times(1)).findById(existingDog.getId());
       verify(dogRepository, times(1)).save(any(Dog.class));
    }

    @Test
    @DisplayName(value = "Should throw resource not found exception when given none existing dogId")
    public void givenValidCreateDogRequestButInvalidDogId_shouldThrowResourceNotFoundException () {
        CreateDogRequest updateDetails = new CreateDogRequest();
        updateDetails.setName("Max");
        updateDetails.setBreed(DogBreed.ROTTWEILER);
        updateDetails.setMicrochipNumber("MC123456");
        updateDetails.setRegistrationNumber("REG789456");
        updateDetails.setGoverningBody(GoverningBody.ADRK);
        updateDetails.setOwner("John");
        updateDetails.setBreeder("Jane");
        updateDetails.setDateOfBirth(LocalDate.of(2022, 1, 10));
        updateDetails.setFartherDog("Diesel");
        updateDetails.setMotherDog("Bella");

        UUID invalidId = UUID.randomUUID();

        UUID dogId = UUID.randomUUID();
        Dog existingDog = new Dog();
        existingDog.setId(dogId);
        existingDog.setName("John");
        existingDog.setBreed(DogBreed.ROTTWEILER);
        existingDog.setMicrochipNumber("MC123456");
        existingDog.setRegistrationNumber("REG789456");
        existingDog.setGoverningBody(GoverningBody.ADRK);
        existingDog.setOwner("Tim");
        existingDog.setBreeder("Princess");
        existingDog.setDateOfBirth(LocalDate.of(2022, 1, 10));
        existingDog.setFartherDog("Joe");
        existingDog.setMotherDog("Niki");

        when(dogRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            dogService.updateDog(updateDetails, invalidId);
        });

        verify(dogRepository, times(1)).findById(invalidId);
        verify(dogRepository, never()).save(any());
    }

    @Test
    @DisplayName(value = "Should find and delete existing dog entity and return True")
    public void givenValidExistingDogId_shouldFindAndDeleteDogEntity_returnTrue() {;

        UUID dogId = UUID.randomUUID();
        Dog existingDog = new Dog();
        existingDog.setId(dogId);
        existingDog.setName("John");
        existingDog.setBreed(DogBreed.ROTTWEILER);
        existingDog.setMicrochipNumber("MC123456");
        existingDog.setRegistrationNumber("REG789456");
        existingDog.setGoverningBody(GoverningBody.ADRK);
        existingDog.setOwner("Tim");
        existingDog.setBreeder("Princess");
        existingDog.setDateOfBirth(LocalDate.of(2022, 1, 10));
        existingDog.setFartherDog("Joe");
        existingDog.setMotherDog("Niki");

        when(dogRepository.findById(existingDog.getId())).thenReturn(Optional.of(existingDog));


        boolean result = dogService.deleteDog(existingDog.getId());

        assertTrue(result);

        verify(dogRepository, times(1)).findById(existingDog.getId());
        verify(dogRepository, never()).save(any());
        verify(dogRepository, times(1)).delete(existingDog);
    }

    @Test
    @DisplayName(value = "Should throw Resource not found exception when given invalid non existing dogId")
    public void givenInvalidNonExistingDogId_shouldThrowResourceNotFoundException() {;

        UUID invalidDogId = UUID.randomUUID();
        UUID dogId = UUID.randomUUID();
        Dog existingDog = new Dog();
        existingDog.setId(dogId);
        existingDog.setName("John");
        existingDog.setBreed(DogBreed.ROTTWEILER);
        existingDog.setMicrochipNumber("MC123456");
        existingDog.setRegistrationNumber("REG789456");
        existingDog.setGoverningBody(GoverningBody.ADRK);
        existingDog.setOwner("Tim");
        existingDog.setBreeder("Princess");
        existingDog.setDateOfBirth(LocalDate.of(2022, 1, 10));
        existingDog.setFartherDog("Joe");
        existingDog.setMotherDog("Niki");

        when(dogRepository.findById(invalidDogId)).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> {
            dogService.deleteDog(invalidDogId);
        });


        verify(dogRepository, times(1)).findById(invalidDogId);
        verify(dogRepository, never()).delete(existingDog);
    }

    @Test
    @DisplayName("Should fetch all dogs and return list of DogResponseDto")
    public void whenFetchAllDogs_shouldReturnDogList() {
        Dog dog = new Dog();
        dog.setId(UUID.randomUUID());
        dog.setName("Max");
        dog.setBreed(DogBreed.ROTTWEILER);
        dog.setMicrochipNumber("MC123456");
        dog.setRegistrationNumber("REG789456");
        dog.setGoverningBody(GoverningBody.ADRK);
        dog.setOwner("John");
        dog.setBreeder("Jane");
        dog.setDateOfBirth(LocalDate.of(2022, 1, 10));
        dog.setFartherDog("Diesel");
        dog.setMotherDog("Bella");

        when(dogRepository.findAll()).thenReturn(List.of(dog));

        var result = dogService.fetchAllDogs();

        assertEquals(1, result.size());
        assertEquals("Max", result.get(0).getName());

        verify(dogRepository, times(1)).findAll();
    }


}
