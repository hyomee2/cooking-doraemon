package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.RefrigeratorRepository;

import java.util.Map;

public class RefrigeratorService {

    private final RefrigeratorRepository refrigeratorRepository = new RefrigeratorRepository();

    public Map<Ingredient, Integer> getRefrigerators() {
        return refrigeratorRepository.getRefrigerator();
    }

    public void printRefrigerators() {
        Map<Ingredient, Integer> refrigerators = getRefrigerators();

        if (refrigerators.isEmpty()) {
            System.out.println("냉장고가 비었어 ●︿●");
        }

        for (Map.Entry<Ingredient, Integer> entry : refrigerators.entrySet()) {
            Ingredient ingredient = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println("재료: " + ingredient + ", 수량: " + quantity);
        }
    }

    public void addRefrigerator(Map<Ingredient, Integer> ingredients) {
        ingredients.forEach((ingredient, quantity) -> refrigeratorRepository.addRefrigerator(Map.of(ingredient, quantity)));
    }
}
