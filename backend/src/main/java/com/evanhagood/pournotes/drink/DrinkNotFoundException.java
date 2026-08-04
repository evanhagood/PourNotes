package com.evanhagood.pournotes.drink;

public class DrinkNotFoundException extends RuntimeException {
    public DrinkNotFoundException(long id) {
        super("Drink not found with id: " + id);
    }
}
