package com.wfarooq.dog_service.litter.entity;

import com.wfarooq.dog_service.shared.BaseEntity;
import com.wfarooq.dog_service.dog.entity.Dog;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Litter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate breedingDate;

    @Column(nullable = false)
    private LocalDate expectedDeliveryDate;

    private LocalDate actualDateOfBirth;

    private Integer litterSize;
    private Integer maleCount;
    private Integer femaleCount;

    @Column(nullable = false)
    private String kennelName;

    private String description;
    private boolean isPublished = false;

    @ManyToOne
    private Dog sire;

    @ManyToOne
    private Dog madam;

    public Litter(UUID id, LocalDate breedingDate, LocalDate expectedDeliveryDate, LocalDate actualDateOfBirth, Integer litterSize, Integer maleCount, Integer femaleCount, String kennelName, String description, boolean isPublished, Dog sire, Dog madam) {
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

    public Litter () {}

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

    public Dog getSire() {
        return sire;
    }

    public void setSire(Dog sire) {
        this.sire = sire;
    }

    public Dog getMadam() {
        return madam;
    }

    public void setMadam(Dog madam) {
        this.madam = madam;
    }

}
