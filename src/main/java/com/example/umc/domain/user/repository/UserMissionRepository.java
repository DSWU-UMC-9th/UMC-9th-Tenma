package com.example.umc.domain.user.repository;

import com.example.umc.domain.mission.enums.MissionStatus;
import com.example.umc.domain.user.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Page<UserMission> findByUserIdAndStatus(Long userId, MissionStatus status, Pageable pageable);
}