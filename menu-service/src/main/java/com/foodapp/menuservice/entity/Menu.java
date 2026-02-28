package com.foodapp.menuservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String restaurantId;

    private String name;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Category> categories;
}