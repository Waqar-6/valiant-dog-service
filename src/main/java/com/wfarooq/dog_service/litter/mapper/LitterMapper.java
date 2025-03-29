package com.wfarooq.dog_service.litter.mapper;

import com.wfarooq.dog_service.dog.dto.response.DogResponseDto;
import com.wfarooq.dog_service.dog.entity.Dog;
import com.wfarooq.dog_service.litter.dto.requests.CreateLitterRequest;
import com.wfarooq.dog_service.litter.dto.responses.LitterResponseDto;
import com.wfarooq.dog_service.litter.entity.Litter;

public final class LitterMapper {

    private LitterMapper () {}

    public static Litter mapToLitterEntity (Litter litter, CreateLitterRequest request, Dog sire, Dog madam) {
        litter.setBreedingDate(request.getBreedingDate());
        litter.setExpectedDeliveryDate(request.getExpectedDeliveryDate());
        litter.setActualDateOfBirth(request.getActualDateOfBirth());
        litter.setLitterSize(request.getLitterSize());
        litter.setMaleCount(request.getMaleCount());
        litter.setFemaleCount(request.getFemaleCount());
        litter.setKennelName(request.getKennelName());
        litter.setDescription(request.getDescription());
        litter.setSire(sire);
        litter.setMadam(madam);
        return litter;
    }

    public static LitterResponseDto mapToLitterResponseDto (Litter litter,LitterResponseDto responseDto, DogResponseDto sireDto, DogResponseDto madamDto) {
        responseDto.setId(litter.getId());
        responseDto.setBreedingDate(litter.getBreedingDate());
        responseDto.setExpectedDeliveryDate(litter.getExpectedDeliveryDate());
        responseDto.setActualDateOfBirth(litter.getActualDateOfBirth());
        responseDto.setLitterSize(litter.getLitterSize() != null ? litter.getLitterSize() : 0);
        responseDto.setMaleCount(litter.getMaleCount() != null ? litter.getMaleCount() : 0);
        responseDto.setFemaleCount(litter.getFemaleCount() != null ? litter.getFemaleCount() : 0);
        responseDto.setKennelName(litter.getKennelName());
        responseDto.setDescription(litter.getDescription());
        responseDto.setPublished(litter.isPublished());
        responseDto.setSire(sireDto);
        responseDto.setMadam(madamDto);
        return responseDto;
    }
}
