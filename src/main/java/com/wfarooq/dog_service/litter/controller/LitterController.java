package com.wfarooq.dog_service.litter.controller;


import com.wfarooq.dog_service.shared.ResponseDto;
import com.wfarooq.dog_service.litter.constants.LitterConstants;
import com.wfarooq.dog_service.litter.dto.requests.CreateLitterRequest;
import com.wfarooq.dog_service.litter.dto.responses.LitterResponseDto;
import com.wfarooq.dog_service.litter.service.ILitterService;
import com.wfarooq.dog_service.shared.StatusConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/litters", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LitterController {

    private final ILitterService litterService;

    public LitterController(ILitterService litterService) {
        this.litterService = litterService;
    }


    @PostMapping
    public ResponseEntity<ResponseDto> createLitter(@Valid @RequestBody CreateLitterRequest request) {
        litterService.createLitter(request);
        return new ResponseEntity<>(
                new ResponseDto(StatusConstants.STATUS_201, LitterConstants.MESSAGE_201),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<LitterResponseDto>> fetchAllLitters() {
        List<LitterResponseDto> litters = litterService.fetchAllLitters();
        return ResponseEntity.ok(litters);
    }

    @GetMapping("/{litterId}")
    public ResponseEntity<LitterResponseDto> fetchLitterById(@PathVariable UUID litterId) {
        LitterResponseDto response = litterService.fetchLitterById(litterId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{litterId}")
    public ResponseEntity<ResponseDto> updateLitter(@Valid @RequestBody CreateLitterRequest request,
                                                    @PathVariable UUID litterId) {
        boolean updated = litterService.updateLitter(request, litterId);
        return updated
                ? ResponseEntity.ok(new ResponseDto(StatusConstants.STATUS_200, LitterConstants.MESSAGE_200_UPDATE))
                : new ResponseEntity<>(new ResponseDto(StatusConstants.STATUS_417, LitterConstants.MESSAGE_417_UPDATE),
                                       HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/{litterId}")
    public ResponseEntity<ResponseDto> deleteLitter(@PathVariable UUID litterId) {
        boolean deleted = litterService.deleteLitter(litterId);
        return deleted
                ? ResponseEntity.ok(new ResponseDto(StatusConstants.STATUS_200, LitterConstants.MESSAGE_200_DELETE))
                : new ResponseEntity<>(new ResponseDto(StatusConstants.STATUS_417, LitterConstants.MESSAGE_417_DELETE),
                                       HttpStatus.EXPECTATION_FAILED);
    }
}
