package com.evanhagood.pournotes.drink;

import jakarta.persistence.*;

@Entity
@Table(name = "drinks")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "espresso_ml")
    private Integer espressoMl;

    @Column(name = "milk_ml")
    private Integer milkMl;

    @Column(name = "serving_size_ml")
    private Integer servingSizeMl;

    @Column(name = "milk_texture", length = 100)
    private String milkTexture;

    protected Drink() {}

    public Drink(
            String name,
            String description,
            Integer espressoMl,
            Integer milkMl,
            Integer servingSizeMl,
            String milkTexture) {
        this.name = name;
        this.description = description;
        this.espressoMl = espressoMl;
        this.milkMl = milkMl;
        this.servingSizeMl = servingSizeMl;
        this.milkTexture = milkTexture;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getEspressoMl() {
        return espressoMl;
    }

    public Integer getMilkMl() {
        return milkMl;
    }

    public Integer getServingSizeMl() {
        return servingSizeMl;
    }

    public String getMilkTexture() {
        return milkTexture;
    }
}