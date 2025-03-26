package com.wfarooq.dog_service.dto.request;

import com.wfarooq.dog_service.constants.DogBreed;
import com.wfarooq.dog_service.constants.GoverningBody;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreateDogRequest {
    @NotNull(message = "Name can not be empty")
    @Size(min = 3, max = 50, message = "name has to be between 3 to 50 characters")
    private String name;
    @NotNull(message = "breed can not be empty")
    private DogBreed breed;
    private String microchipNumber;
    private GoverningBody governingBody;
    private String registrationNumber;
    @NotNull(message = "Owner can not to be empty")
    private String owner;
    @NotNull(message = "breeder can not be empty")
    private String breeder;
    @Past(message = "date of birth can not be in the future")
    private LocalDate dateOfBirth;
    @NotNull(message = "farther dog can not be empty")
    @Size(min = 3, max = 50, message = "Farther dog name has to be between 3 to 50 characters")
    private String fartherDog;
    @NotNull(message = "Mother dog can not be empty")
    @Size(min = 3, max = 50, message = "Mother dog name has to be between 3 to 50 characters")
    private String motherDog;

    public CreateDogRequest(String name, DogBreed breed, String microchipNumber, GoverningBody governingBody, String registrationNumber, String owner, String breeder, LocalDate dateOfBirth, String fartherDog, String motherDog) {
        this.name = name;
        this.breed = breed;
        this.microchipNumber = microchipNumber;
        this.governingBody = governingBody;
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.breeder = breeder;
        this.dateOfBirth = dateOfBirth;
        this.fartherDog = fartherDog;
        this.motherDog = motherDog;
    }

    public CreateDogRequest () {}

    public @NotNull(message = "Name can not be empty") @Size(min = 3, max = 50, message = "name has to be between 3 to 50 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name can not be empty") @Size(min = 3, max = 50, message = "name has to be between 3 to 50 characters") String name) {
        this.name = name;
    }

    public @NotNull(message = "breed can not be empty") DogBreed getBreed() {
        return breed;
    }

    public void setBreed(@NotNull(message = "breed can not be empty") DogBreed breed) {
        this.breed = breed;
    }

    public String getMicrochipNumber() {
        return microchipNumber;
    }

    public void setMicrochipNumber(String microchipNumber) {
        this.microchipNumber = microchipNumber;
    }

    public GoverningBody getGoverningBody() {
        return governingBody;
    }

    public void setGoverningBody(GoverningBody governingBody) {
        this.governingBody = governingBody;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public @NotNull(message = "Owner can not to be empty") String getOwner() {
        return owner;
    }

    public void setOwner(@NotNull(message = "Owner can not to be empty") String owner) {
        this.owner = owner;
    }

    public @NotNull(message = "breeder can not be empty") String getBreeder() {
        return breeder;
    }

    public void setBreeder(@NotNull(message = "breeder can not be empty") String breeder) {
        this.breeder = breeder;
    }

    public @Past(message = "date of birth can not be in the future") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@Past(message = "date of birth can not be in the future") LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotNull(message = "farther dog can not be empty") @Size(min = 3, max = 50, message = "Farther dog name has to be between 3 to 50 characters") String getFartherDog() {
        return fartherDog;
    }

    public void setFartherDog(@NotNull(message = "farther dog can not be empty") @Size(min = 3, max = 50, message = "Farther dog name has to be between 3 to 50 characters") String fartherDog) {
        this.fartherDog = fartherDog;
    }

    public @NotNull(message = "Mother dog can not be empty") @Size(min = 3, max = 50, message = "Mother dog name has to be between 3 to 50 characters") String getMotherDog() {
        return motherDog;
    }

    public void setMotherDog(@NotNull(message = "Mother dog can not be empty") @Size(min = 3, max = 50, message = "Mother dog name has to be between 3 to 50 characters") String motherDog) {
        this.motherDog = motherDog;
    }
}
