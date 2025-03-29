package com.wfarooq.dog_service.dog.dto.response;

import com.wfarooq.dog_service.dog.constants.DogBreed;
import com.wfarooq.dog_service.dog.constants.GoverningBody;

import java.time.LocalDate;
import java.util.UUID;

public class DogResponseDto {
    private UUID id;
    private String name;
    private DogBreed breed;
    private String microchipNumber;
    private String registrationNumber;
    private GoverningBody governingBody;
    private String owner;
    private String breeder;
    private LocalDate dateOfBirth;
    private String fartherDog;
    private String motherDog;

    public DogResponseDto(UUID id, String name, DogBreed breed, String microchipNumber, String registrationNumber, GoverningBody governingBody, String owner, String breeder, LocalDate dateOfBirth, String fartherDog, String motherDog) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.microchipNumber = microchipNumber;
        this.registrationNumber = registrationNumber;
        this.governingBody = governingBody;
        this.owner = owner;
        this.breeder = breeder;
        this.dateOfBirth = dateOfBirth;
        this.fartherDog = fartherDog;
        this.motherDog = motherDog;
    }

    public DogResponseDto () {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DogBreed getBreed() {
        return breed;
    }

    public void setBreed(DogBreed breed) {
        this.breed = breed;
    }

    public String getMicrochipNumber() {
        return microchipNumber;
    }

    public void setMicrochipNumber(String microchipNumber) {
        this.microchipNumber = microchipNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public GoverningBody getGoverningBody() {
        return governingBody;
    }

    public void setGoverningBody(GoverningBody governingBody) {
        this.governingBody = governingBody;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBreeder() {
        return breeder;
    }

    public void setBreeder(String breeder) {
        this.breeder = breeder;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFartherDog() {
        return fartherDog;
    }

    public void setFartherDog(String fartherDog) {
        this.fartherDog = fartherDog;
    }

    public String getMotherDog() {
        return motherDog;
    }

    public void setMotherDog(String motherDog) {
        this.motherDog = motherDog;
    }
}
