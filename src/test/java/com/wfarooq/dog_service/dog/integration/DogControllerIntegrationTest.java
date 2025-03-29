package com.wfarooq.dog_service.dog.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wfarooq.dog_service.dog.constants.DogBreed;
import com.wfarooq.dog_service.dog.constants.DogConstants;
import com.wfarooq.dog_service.dog.constants.GoverningBody;
import com.wfarooq.dog_service.dog.dto.request.CreateDogRequest;
import com.wfarooq.dog_service.dog.entity.Dog;
import com.wfarooq.dog_service.dog.repository.DogRepository;
import com.wfarooq.dog_service.shared.StatusConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class DogControllerIntegrationTest {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void cleanDb () {
        dogRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "should save dog and return responseDto")
    public void givenValidCreateDogRequest_shouldReturnResponseDto() throws Exception {
        CreateDogRequest request = new CreateDogRequest();
        request.setName("Max");
        request.setBreed(DogBreed.ROTTWEILER);
        request.setMicrochipNumber("MC1001");
        request.setRegistrationNumber("REG1001");
        request.setGoverningBody(GoverningBody.ADRK);
        request.setOwner("John");
        request.setBreeder("Jane");
        request.setDateOfBirth(LocalDate.of(2022, 1, 1));
        request.setFartherDog("Diesel");
        request.setMotherDog("Bella");

        mockMvc.perform(post("/api/v1/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.responseStatus").value(StatusConstants.STATUS_201))
                .andExpect(jsonPath("$.responseMessage").value(DogConstants.MESSAGE_201));
    }

    @Test
    public void shouldReturnListOfDogResponseDto () throws Exception {
        Dog dog1 = new Dog();
        dog1.setName("Max");
        dog1.setBreed(DogBreed.ROTTWEILER);
        dog1.setMicrochipNumber("MC1001");
        dog1.setRegistrationNumber("REG1001");
        dog1.setGoverningBody(GoverningBody.ADRK);
        dog1.setOwner("John");
        dog1.setBreeder("Jane");
        dog1.setDateOfBirth(LocalDate.of(2022, 1, 1));
        dog1.setFartherDog("Diesel");
        dog1.setMotherDog("Bella");

        Dog dog2 = new Dog();
        dog2.setName("Vitus");
        dog2.setBreed(DogBreed.GERMAN_SHEPHERD);
        dog2.setMicrochipNumber("MC1003");
        dog2.setRegistrationNumber("REG1000");
        dog2.setGoverningBody(GoverningBody.CKC);
        dog2.setOwner("Tim");
        dog2.setBreeder("Saint");
        dog2.setDateOfBirth(LocalDate.of(2022, 1, 1));
        dog2.setFartherDog("Petrol");
        dog2.setMotherDog("Sam");

        dogRepository.save(dog1);
        dogRepository.save(dog2);

        mockMvc.perform(get("/api/v1/dogs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Max"))
                .andExpect(jsonPath("$[1].name").value("Vitus"));

    }

    @Test
    @DisplayName("Should fetch dog by registration number")
    void shouldFetchDogByRegistrationNumber() throws Exception {
        Dog dog = new Dog("Max", DogBreed.ROTTWEILER, "MC1010", GoverningBody.ADRK, "REG1010", "John", "Jane", LocalDate.of(2022, 1, 1), "Diesel", "Bella");
        dogRepository.save(dog);

        mockMvc.perform(get("/api/v1/dogs/{registrationNumber}", "REG1010")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Max"));
    }

    @Test
    @DisplayName("Should return 404 when dog not found by registration number")
    void shouldReturn404WhenDogNotFoundByRegistrationNumber() throws Exception {
        mockMvc.perform(get("/api/v1/dogs/{registrationNumber}", "NON_EXISTENT"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should update existing dog")
    void shouldUpdateDogSuccessfully() throws Exception {
        Dog dog = new Dog("Max", DogBreed.ROTTWEILER, "MC2001",GoverningBody.ADRK, "REG2001",  "John", "Jane", LocalDate.of(2022, 1, 1), "Diesel", "Bella");
        Dog savedDog = dogRepository.save(dog);

        CreateDogRequest updatedRequest = new CreateDogRequest();
        updatedRequest.setName("Maximus");
        updatedRequest.setBreed(DogBreed.ROTTWEILER);
        updatedRequest.setMicrochipNumber("MC2001");
        updatedRequest.setRegistrationNumber("REG2001");
        updatedRequest.setGoverningBody(GoverningBody.ADRK);
        updatedRequest.setOwner("John");
        updatedRequest.setBreeder("Jane");
        updatedRequest.setDateOfBirth(LocalDate.of(2022, 1, 1));
        updatedRequest.setFartherDog("Diesel");
        updatedRequest.setMotherDog("Bella");

        mockMvc.perform(put("/api/v1/dogs/{id}", savedDog.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMessage").value(DogConstants.MESSAGE_200_UPDATE));
    }

    @Test
    @DisplayName("Should throw resource not found exception when updating with invalid or none existing ID")
    void shouldThrowResourceNotFoundExceptionWhenUpdatingInvalidOrNoneExistingDogId() throws Exception {
        UUID invalidId = UUID.randomUUID();

        CreateDogRequest update = new CreateDogRequest();
        update.setName("Ghost");
        update.setBreed(DogBreed.ROTTWEILER);
        update.setMicrochipNumber("MC0000");
        update.setRegistrationNumber("REG0000");
        update.setGoverningBody(GoverningBody.ADRK);
        update.setOwner("Nobody");
        update.setBreeder("Nobody");
        update.setDateOfBirth(LocalDate.of(2020, 1, 1));
        update.setFartherDog("None");
        update.setMotherDog("None");

        mockMvc.perform(put("/api/v1/dogs/{id}", invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should delete dog successfully")
    void shouldDeleteDogSuccessfully() throws Exception {
        Dog dog = new Dog("Max", DogBreed.ROTTWEILER, "MC3001", GoverningBody.ADRK,"REG3001",  "John", "Jane", LocalDate.of(2022, 1, 1), "Diesel", "Bella");
        Dog savedDog = dogRepository.save(dog);

        mockMvc.perform(delete("/api/v1/dogs/{id}", savedDog.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMessage").value("Dog deleted successfully."));
    }

    @Test
    @DisplayName("Should throw resource not found exception when deleting invalid or none existing dog ID")
    void shouldThrowResourceNotFoundExceptionWhenDeletingInvalidOrNoneExistingDogId() throws Exception {
        UUID invalidId = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/dogs/{id}", invalidId))
                .andExpect(status().isNotFound());
    }



}
