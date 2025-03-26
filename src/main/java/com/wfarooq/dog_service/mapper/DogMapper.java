package com.wfarooq.dog_service.mapper;

import com.wfarooq.dog_service.dto.request.CreateDogRequest;
import com.wfarooq.dog_service.dto.response.DogResponseDto;
import com.wfarooq.dog_service.entity.Dog;

public final class DogMapper {

    private DogMapper () {}

    public static Dog mapToDogEntity (CreateDogRequest request, Dog dog) {
        dog.setName(request.getName());
        dog.setBreed(request.getBreed());
        dog.setMicrochipNumber(request.getMicrochipNumber());
        dog.setGoverningBody(request.getGoverningBody());
        dog.setRegistrationNumber(request.getRegistrationNumber());
        dog.setOwner(request.getOwner());
        dog.setBreeder(request.getBreeder());
        dog.setDateOfBirth(request.getDateOfBirth());
        dog.setFartherDog(request.getFartherDog());
        dog.setMotherDog(request.getMotherDog());
        return dog;
    }

    public static DogResponseDto mapToDogResponseDto (Dog dog, DogResponseDto responseDto) {
        responseDto.setId(dog.getId());
        responseDto.setName(dog.getName());
        responseDto.setBreed(dog.getBreed());
        responseDto.setMicrochipNumber(dog.getMicrochipNumber());
        responseDto.setRegistrationNumber(dog.getRegistrationNumber());
        responseDto.setGoverningBody(dog.getGoverningBody());
        responseDto.setOwner(dog.getOwner());
        responseDto.setBreeder(dog.getBreeder());
        responseDto.setDateOfBirth(dog.getDateOfBirth());
        responseDto.setFartherDog(dog.getFartherDog());
        responseDto.setMotherDog(dog.getMotherDog());
        return responseDto;
    }
}
