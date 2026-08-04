package com.evanhagood.pournotes.drink;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * Contains the client-supplied data required to create a drink.
 *
 * @param name display name of the drink
 * @param description educational description of the drink
 * @param espressoMl typical espresso volume in milliliters
 * @param milkMl typical milk volume in milliliters
 * @param servingSizeMl typical total serving size in milliliters
 * @param milkTexture description of the drink's milk texture, or {@code null}
 *                    when milk texture is not applicable
 */
public record CreateDrinkRequest(

        @NotBlank(message = "Drink name is required")
        @Size(max = 100, message = "Drink name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 1000, message = "Description cannot exceed 1000 characters")
        String description,

        @PositiveOrZero(message = "Espresso volume cannot be negative")
        Integer espressoMl,

        @PositiveOrZero(message = "Milk volume cannot be negative")
        Integer milkMl,

        @Positive(message = "Serving size must be greater than zero")
        Integer servingSizeMl,

        @Size(max = 100, message = "Milk texture cannot exceed 100 characters")
        String milkTexture
) {
}