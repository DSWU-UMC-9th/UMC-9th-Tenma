package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.dto.HomeMissionDTO;
import com.example.umc.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        SELECT new com.example.umc.domain.mission.dto.HomeMissionDTO(
            m.id,
            s.name,
            c.name,
            m.deadline,
            m.condition,
            m.point
        )
        FROM Mission m
        JOIN m.store s
        JOIN s.category c
        JOIN s.region r
        WHERE r.name = :location
        ORDER BY m.createdAt DESC
    """)
    Page<HomeMissionDTO> findHomeMissionsByLocation(@Param("location") String location,
                                                    Pageable pageable);
}