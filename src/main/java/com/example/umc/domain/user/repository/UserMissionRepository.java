package com.example.umc.domain.user.repository;

import com.example.umc.domain.user.entity.mapping.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}