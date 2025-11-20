package com.example.umc.domain.store.repository;

import com.example.umc.domain.store.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}