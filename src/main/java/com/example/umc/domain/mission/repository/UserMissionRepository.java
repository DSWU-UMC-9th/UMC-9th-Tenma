package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.dto.MyMissionDTO;
import com.example.umc.domain.user.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
        SELECT new com.example.umc.domain.mission.dto.MyMissionDTO(
            um.id,
            s.id,
            s.name,
            m.point,
            m.condition,
            um.status
        )
        FROM UserMission um
        JOIN um.mission m
        JOIN m.store s
        WHERE um.user.id = :userId
        ORDER BY um.createdAt DESC
    """)
    Page<MyMissionDTO> findMyMissions(@Param("userId") Long userId, Pageable pageable);
}