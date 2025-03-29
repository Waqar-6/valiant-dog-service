package com.wfarooq.dog_service.litter.service.impl;

import com.wfarooq.dog_service.dog.dto.response.DogResponseDto;
import com.wfarooq.dog_service.dog.entity.Dog;
import com.wfarooq.dog_service.shared.exception.ResourceNotFoundException;
import com.wfarooq.dog_service.dog.mapper.DogMapper;
import com.wfarooq.dog_service.dog.repository.DogRepository;
import com.wfarooq.dog_service.litter.dto.requests.CreateLitterRequest;
import com.wfarooq.dog_service.litter.dto.responses.LitterResponseDto;
import com.wfarooq.dog_service.litter.entity.Litter;
import com.wfarooq.dog_service.litter.mapper.LitterMapper;
import com.wfarooq.dog_service.litter.repository.LitterRepository;
import com.wfarooq.dog_service.litter.service.ILitterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class LitterServiceImpl implements ILitterService {

    private final LitterRepository litterRepository;
    private final DogRepository dogRepository;

    public LitterServiceImpl(LitterRepository litterRepository, DogRepository dogRepository) {
        this.litterRepository = litterRepository;
        this.dogRepository = dogRepository;
    }

    @Override
    public void createLitter(CreateLitterRequest request) {
        Dog sire = dogRepository.findById(request.getSireId())
                .orElseThrow(() -> new ResourceNotFoundException("Dog", "id", request.getSireId().toString()));

        Dog madam = dogRepository.findById(request.getMadamId())
                .orElseThrow(() -> new ResourceNotFoundException("Dog", "id", request.getMadamId().toString()));

        Litter litter = LitterMapper.mapToLitterEntity(new Litter(),request, sire, madam);
        litterRepository.save(litter);
    }


    @Override
    public List<LitterResponseDto> fetchAllLitters() {
        List<Litter> litters = litterRepository.findAll();
        return litters.stream().map(litter -> {
            DogResponseDto sireDto = DogMapper.mapToDogResponseDto(litter.getSire(), new DogResponseDto());
            DogResponseDto madamDto = DogMapper.mapToDogResponseDto(litter.getMadam(), new DogResponseDto());
            return LitterMapper.mapToLitterResponseDto( litter,new LitterResponseDto(), sireDto, madamDto);
        }).toList();
    }

    @Override
    public LitterResponseDto fetchLitterById(UUID litterId) {
        Litter litter = litterRepository.findById(litterId)
                .orElseThrow(() -> new ResourceNotFoundException("Litter", "id", litterId.toString()));

        DogResponseDto sireDto = DogMapper.mapToDogResponseDto(litter.getSire(), new DogResponseDto());
        DogResponseDto madamDto = DogMapper.mapToDogResponseDto(litter.getMadam(), new DogResponseDto());

        return LitterMapper.mapToLitterResponseDto(litter,new LitterResponseDto(), sireDto, madamDto);
    }

    @Override
    public boolean updateLitter(CreateLitterRequest request, UUID litterId) {
        Litter litter = litterRepository.findById(litterId)
                .orElseThrow(() -> new ResourceNotFoundException("Litter", "id", litterId.toString()));

        Dog sire = dogRepository.findById(request.getSireId())
                .orElseThrow(() -> new ResourceNotFoundException("Dog", "id", request.getSireId().toString()));

        Dog madam = dogRepository.findById(request.getMadamId())
                .orElseThrow(() -> new ResourceNotFoundException("Dog", "id", request.getMadamId().toString()));

        LitterMapper.mapToLitterEntity(litter, request, sire, madam);
        litterRepository.save(litter);
        return true;
    }

    @Override
    public boolean deleteLitter(UUID litterId) {
        Litter litter = litterRepository.findById(litterId)
                .orElseThrow(() -> new ResourceNotFoundException("Litter", "id", litterId.toString()));
        litterRepository.delete(litter);
        return true;
    }
}
