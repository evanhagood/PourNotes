package com.evanhagood.pournotes.drink;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/drinks")
public class DrinkController {
    
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public List<DrinkResponse> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    @GetMapping("{id}")
    public DrinkResponse getDrink(
        @PathVariable 
        @Positive(message="Drink ID must be greater than zero.")
        long id) {
        return drinkService.getDrink(id);
    }
}
