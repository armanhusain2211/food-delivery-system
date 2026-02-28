package com.foodapp.menuservice.repository;

import com.foodapp.menuservice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}