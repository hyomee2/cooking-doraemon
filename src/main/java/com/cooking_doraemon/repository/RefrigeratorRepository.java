package com.cooking_doraemon.repository;

import com.cooking_doraemon.aggregate.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class RefrigeratorRepository {

    private final List<Ingredient> refrigerator;

    public RefrigeratorRepository() {
        refrigerator = new ArrayList<>();
    }

    public List<Ingredient> getRefrigerator() {
        return refrigerator;
    }

    public void addRefrigerator(Ingredient ingredient) {
        refrigerator.add(ingredient);
    }

    public void removeRefrigerator(Ingredient ingredient) {
        refrigerator.remove(ingredient);
    }
}
