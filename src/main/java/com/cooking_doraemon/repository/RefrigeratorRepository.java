package com.cooking_doraemon.repository;

import com.cooking_doraemon.aggregate.Ingredient;

import java.util.*;

public class RefrigeratorRepository {

    private static final Map<Ingredient, Integer> refrigerator = new HashMap<>();
    private final RecipeRepository recipeRepository;

    public RefrigeratorRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Map<Ingredient, Integer> getRefrigerator() {
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

    public void useIngredient(String chosenMenu) {
        List<Ingredient> ingredients = recipeRepository.getRecipeList().get(chosenMenu);
        for (Ingredient ingredient : ingredients) {
            refrigerator.put(ingredient, refrigerator.get(ingredient) - 1);
            // 개수가 0이 될 경우 refrigerator에서 해당 ingredient remove
            if(refrigerator.get(ingredient) == 0) {
                refrigerator.remove(ingredient);
            }
        }
    }
}
