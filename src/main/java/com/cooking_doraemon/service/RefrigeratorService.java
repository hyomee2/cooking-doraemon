package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.RefrigeratorRepository;

import java.util.List;

public class RefrigeratorService {

    private final RefrigeratorRepository refrigeratorRepository = new RefrigeratorRepository();

    public List<Ingredient> getRefrigerators() {
        return refrigeratorRepository.getRefrigerator();
    }

    public void printRefrigerators() {
        List<Ingredient> refrigerators = getRefrigerators();

        System.out.println("냉장고 품목");
        for (Ingredient ingredient : refrigerators) {
            System.out.println("물건" + ingredient);
        }
    }

    public void addRefrigerator(List<Ingredient> ingredients) {
        ingredients.forEach(refrigeratorRepository::addRefrigerator);
    }
}
