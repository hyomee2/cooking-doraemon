package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.RefrigeratorRepository;

import java.util.List;

public class RefrigeratorService {

    private final RefrigeratorRepository refrigeratorRepository = new RefrigeratorRepository();

    public void getRefrigerators() {
        List<Ingredient> refrigerator = refrigeratorRepository.getRefrigerator();

        for (Ingredient ingredient : refrigerator) {
            System.out.println(ingredient);
        }
    }
}
