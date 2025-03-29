package com.wfarooq.dog_service.litter.service;

import com.wfarooq.dog_service.litter.dto.requests.CreateLitterRequest;
import com.wfarooq.dog_service.litter.dto.responses.LitterResponseDto;

import java.util.List;
import java.util.UUID;

public interface ILitterService {

    void createLitter(CreateLitterRequest request);

    List<LitterResponseDto> fetchAllLitters();

    LitterResponseDto fetchLitterById(UUID litterId);

    boolean updateLitter(CreateLitterRequest request, UUID litterId);

    boolean deleteLitter(UUID litterId);
}
