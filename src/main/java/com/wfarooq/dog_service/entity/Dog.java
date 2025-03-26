package com.wfarooq.dog_service.entity;

import com.wfarooq.dog_service.constants.DogBreed;
import com.wfarooq.dog_service.constants.GoverningBody;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private DogBreed breed;
    private String microchipNumber;
    private String registrationNumber;
    @Enumerated(value = EnumType.STRING)
    private GoverningBody governingBody;
    private String owner;
    private String breeder;
    private LocalDate dateOfBirth;
    private String fartherDog;
    private String motherDog;

    public Dog(String name, DogBreed breed, String microchipNumber, GoverningBody governingBody, String registrationNumber, String owner, String breeder, LocalDate dateOfBirth, String fartherDog, String motherDog) {
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

    public Dog () {}

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
