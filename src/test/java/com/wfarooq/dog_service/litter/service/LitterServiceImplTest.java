package com.wfarooq.dog_service.litter.service;

import com.wfarooq.dog_service.dog.entity.Dog;
import com.wfarooq.dog_service.dog.repository.DogRepository;
import com.wfarooq.dog_service.litter.dto.requests.CreateLitterRequest;
import com.wfarooq.dog_service.litter.dto.responses.LitterResponseDto;
import com.wfarooq.dog_service.litter.entity.Litter;
import com.wfarooq.dog_service.litter.repository.LitterRepository;
import com.wfarooq.dog_service.litter.service.impl.LitterServiceImpl;
import com.wfarooq.dog_service.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LitterServiceImplTest {

    @Mock
    private LitterRepository litterRepository;

    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private LitterServiceImpl litterService;

    @Test
    @DisplayName("Should create litter when valid request is given")
    void shouldCreateLitterSuccessfully() {
        CreateLitterRequest request = new CreateLitterRequest();
        request.setBreedingDate(LocalDate.of(2024, 3, 15));
        request.setExpectedDeliveryDate(LocalDate.of(2024, 5, 15));
        request.setKennelName("Test Kennel");
        request.setDescription("Desc");
        request.setSireId(UUID.randomUUID());
        request.setMadamId(UUID.randomUUID());

        Dog sire = new Dog();
        Dog madam = new Dog();

        when(dogRepository.findById(request.getSireId())).thenReturn(Optional.of(sire));
        when(dogRepository.findById(request.getMadamId())).thenReturn(Optional.of(madam));

        litterService.createLitter(request);

        verify(litterRepository, times(1)).save(any(Litter.class));
    }

    @Test
    @DisplayName("Should fetch litter by ID")
    void shouldFetchLitterById() {
        UUID litterId = UUID.randomUUID();


        Dog sire = new Dog();
        sire.setId(UUID.randomUUID());
        sire.setName("Max");

        Dog madam = new Dog();
        madam.setId(UUID.randomUUID());
        madam.setName("Julie");


        Litter litter = new Litter();
        litter.setId(litterId);
        litter.setSire(sire);
        litter.setMadam(madam);
        litter.setKennelName("Working Dogs UK");
        litter.setDescription("Strong, healthy litter");
        litter.setBreedingDate(LocalDate.of(2024, 12, 10));
        litter.setExpectedDeliveryDate(LocalDate.of(2025, 02, 10));

        when(litterRepository.findById(litterId)).thenReturn(Optional.of(litter));

        LitterResponseDto result = litterService.fetchLitterById(litterId);

        assertEquals(litterId, result.getId());
        assertEquals("Max", result.getSire().getName());
        assertEquals("Julie", result.getMadam().getName());
    }

    @Test
    @DisplayName("Should throw when litter not found by ID")
    void shouldThrowIfLitterNotFoundById() {
        UUID invalidId = UUID.randomUUID();

        when(litterRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> litterService.fetchLitterById(invalidId));
    }

    @Test
    @DisplayName("Should update litter successfully")
    void shouldUpdateLitter() {
        UUID litterId = UUID.randomUUID();
        CreateLitterRequest request = new CreateLitterRequest();
        request.setSireId(UUID.randomUUID());
        request.setMadamId(UUID.randomUUID());
        request.setBreedingDate(LocalDate.of(2024, 3, 15));

        Dog sire = new Dog();
        Dog madam = new Dog();
        Litter existing = new Litter();

        when(litterRepository.findById(litterId)).thenReturn(Optional.of(existing));
        when(dogRepository.findById(request.getSireId())).thenReturn(Optional.of(sire));
        when(dogRepository.findById(request.getMadamId())).thenReturn(Optional.of(madam));

        boolean updated = litterService.updateLitter(request, litterId);

        assertTrue(updated);
        verify(litterRepository, times(1)).save(any(Litter.class));
    }

    @Test
    @DisplayName("Should delete litter successfully")
    void shouldDeleteLitter() {
        UUID litterId = UUID.randomUUID();
        Litter litter = new Litter();
        litter.setId(litterId);

        when(litterRepository.findById(litterId)).thenReturn(Optional.of(litter));

        boolean deleted = litterService.deleteLitter(litterId);

        assertTrue(deleted);
        verify(litterRepository).delete(litter);
    }




}