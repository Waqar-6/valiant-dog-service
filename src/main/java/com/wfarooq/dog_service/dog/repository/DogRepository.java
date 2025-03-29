package com.wfarooq.dog_service.dog.repository;

import com.wfarooq.dog_service.dog.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface DogRepository extends JpaRepository<Dog, UUID> {
    boolean existsByMicrochipNumber (String microchipNumber);
    boolean existsByRegistrationNumber (String registrationNumber);
    Optional<Dog> findByRegistrationNumber (String registrationNumber);
}
