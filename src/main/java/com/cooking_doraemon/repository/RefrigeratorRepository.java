package com.cooking_doraemon.repository;

import com.cooking_doraemon.aggregate.Ingredient;

import java.util.*;

public class RefrigeratorRepository {
    
    private final Map<Ingredient, Integer> refrigerator;

    public RefrigeratorRepository() {
        refrigerator = new HashMap<>();
    }

    public  Map<Ingredient, Integer>  getRefrigerator() {
        return refrigerator;
    }

    public void addRefrigerator(Map<Ingredient, Integer> cart) {
        for (Map.Entry<Ingredient, Integer> entry : cart.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();

            if (refrigerator.containsKey(ingredient)) {
                refrigerator.put(ingredient, refrigerator.get(ingredient) + quantity);
            } else {
                refrigerator.put(ingredient, quantity);
            }
        }
    }


}
