package com.wfarooq.dog_service.litter.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wfarooq.dog_service.dog.entity.Dog;
import com.wfarooq.dog_service.dog.repository.DogRepository;
import com.wfarooq.dog_service.litter.dto.requests.CreateLitterRequest;
import com.wfarooq.dog_service.litter.entity.Litter;
import com.wfarooq.dog_service.litter.repository.LitterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LitterControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LitterRepository litterRepository;

    @Autowired
    private DogRepository dogRepository;

    private Dog sire;
    private Dog madam;

    @BeforeEach
    void setup() {
        litterRepository.deleteAll();
        dogRepository.deleteAll();

        sire = new Dog();
        sire.setName("Max");
        dogRepository.save(sire);

        madam = new Dog();
        madam.setName("Julie");
        dogRepository.save(madam);
    }

    @Test
    @DisplayName("POST /api/v1/litters - should create litter")
    void shouldCreateLitter() throws Exception {
        CreateLitterRequest request = new CreateLitterRequest();
        request.setSireId(sire.getId());
        request.setMadamId(madam.getId());
        request.setKennelName("Test Kennel");
        request.setBreedingDate(LocalDate.now());
        request.setExpectedDeliveryDate(LocalDate.now().plusDays(60));

        mockMvc.perform(post("/api/v1/litters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.responseMessage").value("Litter created successfully."));
    }

    @Test
    @DisplayName("GET /api/v1/litters - should return list of litters")
    void shouldFetchAllLitters() throws Exception {
        Litter litter = new Litter();
        litter.setSire(sire);
        litter.setMadam(madam);
        litter.setKennelName("Sample Kennel");
        litter.setBreedingDate(LocalDate.now());
        litter.setExpectedDeliveryDate(LocalDate.now().plusDays(60));
        litterRepository.save(litter);

        mockMvc.perform(get("/api/v1/litters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].kennelName").value("Sample Kennel"));
    }

    @Test
    @DisplayName("GET /api/v1/litters/{id} - should return litter by id")
    void shouldFetchLitterById() throws Exception {
        Litter litter = new Litter();
        litter.setSire(sire);
        litter.setMadam(madam);
        litter.setKennelName("Kennel One");
        litter.setBreedingDate(LocalDate.now());
        litter.setExpectedDeliveryDate(LocalDate.now().plusDays(60));
        litter = litterRepository.save(litter);

        mockMvc.perform(get("/api/v1/litters/{id}", litter.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kennelName").value("Kennel One"));
    }

    @Test
    @DisplayName("PUT /api/v1/litters/{id} - should update litter")
    void shouldUpdateLitter() throws Exception {
        Litter litter = new Litter();
        litter.setSire(sire);
        litter.setMadam(madam);
        litter.setKennelName("Old Kennel");
        litter.setBreedingDate(LocalDate.now());
        litter.setExpectedDeliveryDate(LocalDate.now().plusDays(60));
        litter = litterRepository.save(litter);

        CreateLitterRequest update = new CreateLitterRequest();
        update.setSireId(sire.getId());
        update.setMadamId(madam.getId());
        update.setKennelName("New Kennel");
        update.setBreedingDate(LocalDate.now());
        update.setExpectedDeliveryDate(LocalDate.now().plusDays(60));

        mockMvc.perform(put("/api/v1/litters/{id}", litter.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMessage").value("Litter updated successfully."));
    }

    @Test
    @DisplayName("DELETE /api/v1/litters/{id} - should delete litter")
    void shouldDeleteLitter() throws Exception {
        Litter litter = new Litter();
        litter.setSire(sire);
        litter.setMadam(madam);
        litter.setKennelName("DeleteMe");
        litter.setBreedingDate(LocalDate.now());
        litter.setExpectedDeliveryDate(LocalDate.now().plusDays(60));
        litter = litterRepository.save(litter);

        mockMvc.perform(delete("/api/v1/litters/{id}", litter.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMessage").value("Litter deleted successfully."));
    }
}
