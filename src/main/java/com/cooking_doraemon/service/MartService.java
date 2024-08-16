package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.MartRepository;

import java.util.List;

public class MartService {

    private final MartRepository martRepository = new MartRepository();

    public void getIngredientList() {
        List<Ingredient> ingredientList = martRepository.getIngredientList();

        System.out.println("\n=========================");
        System.out.println("형미 마트에 온 걸 환영해!\n");
        System.out.println("아래 물건 중에 사고 싶은 거를 골라봐\n");

        System.out.print("[");
        for (Ingredient ingredient : ingredientList) {
            System.out.print(ingredient.getName() + " ");
        }
        System.out.print("]\n");
    }
}
