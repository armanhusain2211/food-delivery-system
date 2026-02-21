package com.foodapp.restaurantservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurant_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}