package com.example.umc.domain.store.repository;

import com.example.umc.domain.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}