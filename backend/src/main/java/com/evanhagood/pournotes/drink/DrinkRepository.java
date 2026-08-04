package com.evanhagood.pournotes.drink;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Optional<Drink> findByNameIgnoreCase(String name);
}
