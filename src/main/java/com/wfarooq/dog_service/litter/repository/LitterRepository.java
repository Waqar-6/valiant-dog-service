package com.wfarooq.dog_service.litter.repository;

import com.wfarooq.dog_service.litter.entity.Litter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LitterRepository extends JpaRepository<Litter, UUID> {
}
