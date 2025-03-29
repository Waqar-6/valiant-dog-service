package com.wfarooq.dog_service.litter.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class CreateLitterRequest {

    @NotNull(message = "can not be null")
    private LocalDate breedingDate;

    @NotNull(message = "expected delivery date can not be empty")
    private LocalDate expectedDeliveryDate;

    private LocalDate actualDateOfBirth;
    private Integer litterSize;
    private Integer maleCount;
    private Integer femaleCount;

    @NotBlank(message = "kennel name can not be empty")
    private String kennelName;

    private String description;

    @NotNull(message = "Sire of the litter can not be null")
    private UUID sireId;

    @NotNull(message = "madam of the litter can not be null")
    private UUID madamId;


    public CreateLitterRequest(LocalDate breedingDate, LocalDate expectedDeliveryDate, LocalDate actualDateOfBirth, Integer litterSize, Integer maleCount, Integer femaleCount, String kennelName, String description, UUID sireId, UUID madamId) {
        this.breedingDate = breedingDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.actualDateOfBirth = actualDateOfBirth;
        this.litterSize = litterSize;
        this.maleCount = maleCount;
        this.femaleCount = femaleCount;
        this.kennelName = kennelName;
        this.description = description;
        this.sireId = sireId;
        this.madamId = madamId;
    }

    public CreateLitterRequest () {}

    public @NotNull LocalDate getBreedingDate() {
        return breedingDate;
    }

    public void setBreedingDate(@NotNull LocalDate breedingDate) {
        this.breedingDate = breedingDate;
    }

    public @NotNull LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(@NotNull LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public LocalDate getActualDateOfBirth() {
        return actualDateOfBirth;
    }

    public void setActualDateOfBirth(LocalDate actualDateOfBirth) {
        this.actualDateOfBirth = actualDateOfBirth;
    }

    public Integer getLitterSize() {
        return litterSize;
    }

    public void setLitterSize(Integer litterSize) {
        this.litterSize = litterSize;
    }

    public Integer getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(Integer maleCount) {
        this.maleCount = maleCount;
    }

    public Integer getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(Integer femaleCount) {
        this.femaleCount = femaleCount;
    }

    public @NotBlank String getKennelName() {
        return kennelName;
    }

    public void setKennelName(@NotBlank String kennelName) {
        this.kennelName = kennelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull UUID getSireId() {
        return sireId;
    }

    public void setSireId(@NotNull UUID sireId) {
        this.sireId = sireId;
    }

    public @NotNull UUID getMadamId() {
        return madamId;
    }

    public void setMadamId(@NotNull UUID madamId) {
        this.madamId = madamId;
    }
}
