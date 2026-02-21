package com.foodapp.restaurantservice.exception;

public class DuplicateRestaurantException extends RuntimeException {

    public DuplicateRestaurantException(String message) {
        super(message);
    }
}