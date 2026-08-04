package com.evanhagood.pournotes.drink;

/**
 * Represents the drink information returned by the PourNotes API.
 *
 * @param id unique identifier of the drink
 * @param name display name of the drink
 * @param description educational description of the drink
 * @param espressoMl volume of espresso in milliliters
 * @param milkMl volume of milk in milliliters
 * @param servingSizeMl typical total serving size in milliliters
 * @param milkTexture description of the drink's typical milk texture,
 *                    or {@code null} when the drink contains no milk
 */

public record DrinkResponse(
    Long id,
    String name,
    String description,
    Integer espressoMl,
    Integer milkMl,
    Integer servingSizeMl,
    String milkTexture
){
    /**
     * Creates a response containing data from the given drink entity.
     *
     * @param drink drink entity to convert; must not be {@code null}
     * @return a response containing the drink's API-facing data
     * @throws NullPointerException if {@code drink} is {@code null}
     */
    public static DrinkResponse from(Drink drink) {
        return new DrinkResponse(
                drink.getId(),
                drink.getName(),
                drink.getDescription(),
                drink.getEspressoMl(),
                drink.getMilkMl(),
                drink.getServingSizeMl(),
                drink.getMilkTexture()
        );
    }
}
