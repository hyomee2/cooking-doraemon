package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.MartRepository;

import java.util.List;

public class MartService {

    private final MartRepository martRepository = new MartRepository();

    public void getIngredientList() {
        List<Ingredient> ingredientList = martRepository.getIngredientList();

        for (Ingredient ingredient : ingredientList) {
            System.out.println(ingredient);
        }
    }
}
