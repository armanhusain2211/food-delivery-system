package com.foodapp.menuservice.repository;

import com.foodapp.menuservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}