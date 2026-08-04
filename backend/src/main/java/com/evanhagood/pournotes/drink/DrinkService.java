package com.evanhagood.pournotes.drink;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<DrinkResponse> getAllDrinks() {
        return drinkRepository.findAll()
                .stream()
                .map(DrinkResponse::from)
                .toList();
    }

    public DrinkResponse getDrink(long id) {
        Drink drink = drinkRepository.findById(id)
                    .orElseThrow(() -> new DrinkNotFoundException(id));

        return DrinkResponse.from(drink);
    }
}
