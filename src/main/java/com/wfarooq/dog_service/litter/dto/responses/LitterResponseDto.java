package com.wfarooq.dog_service.litter.dto.responses;

import com.wfarooq.dog_service.dog.dto.response.DogResponseDto;

import java.time.LocalDate;
import java.util.UUID;

public class LitterResponseDto {

    private UUID id;
    private LocalDate breedingDate;
    private LocalDate expectedDeliveryDate;
    private LocalDate actualDateOfBirth;

    private int litterSize;
    private int maleCount;
    private int femaleCount;

    private String kennelName;
    private String description;
    private boolean isPublished;

    private DogResponseDto sire;
    private DogResponseDto madam;

    public LitterResponseDto(UUID id, LocalDate breedingDate, LocalDate expectedDeliveryDate, LocalDate actualDateOfBirth, int litterSize, int maleCount, int femaleCount, String kennelName, String description, boolean isPublished, DogResponseDto sire, DogResponseDto madam) {
        this.id = id;
        this.breedingDate = breedingDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.actualDateOfBirth = actualDateOfBirth;
        this.litterSize = litterSize;
        this.maleCount = maleCount;
        this.femaleCount = femaleCount;
        this.kennelName = kennelName;
        this.description = description;
        this.isPublished = isPublished;
        this.sire = sire;
        this.madam = madam;
    }

    public LitterResponseDto () {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getBreedingDate() {
        return breedingDate;
    }

    public void setBreedingDate(LocalDate breedingDate) {
        this.breedingDate = breedingDate;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public LocalDate getActualDateOfBirth() {
        return actualDateOfBirth;
    }

    public void setActualDateOfBirth(LocalDate actualDateOfBirth) {
        this.actualDateOfBirth = actualDateOfBirth;
    }

    public int getLitterSize() {
        return litterSize;
    }

    public void setLitterSize(int litterSize) {
        this.litterSize = litterSize;
    }

    public int getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(int maleCount) {
        this.maleCount = maleCount;
    }

    public int getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(int femaleCount) {
        this.femaleCount = femaleCount;
    }

    public String getKennelName() {
        return kennelName;
    }

    public void setKennelName(String kennelName) {
        this.kennelName = kennelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public DogResponseDto getSire() {
        return sire;
    }

    public void setSire(DogResponseDto sire) {
        this.sire = sire;
    }

    public DogResponseDto getMadam() {
        return madam;
    }

    public void setMadam(DogResponseDto madam) {
        this.madam = madam;
    }
}
