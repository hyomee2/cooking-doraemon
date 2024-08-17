package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.aggregate.Recipe;
import com.cooking_doraemon.repository.RecipeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RecipeService {
    Scanner scanner = new Scanner(System.in);
    private final RecipeRepository recipeRepository = new RecipeRepository();

    // 레시피에 저장된 음식 목록 보기
    public void findAllRecipeName() {
        System.out.println("저장된 레시피 목록이야!\n");
        System.out.println("==============================\n");
        Map<String, List<Ingredient>> findRecipes = recipeRepository.selectAllRecipes();

        for (Map.Entry<String, List<Ingredient>> entry : findRecipes.entrySet()) {
            String recipeName = entry.getKey();
            System.out.println(recipeName);
        }

        System.out.println("\n==============================\n");
        System.out.print("레시피를 더 자세히 보고 싶은 음식 이름을 입력해줘!: ");
        String recipeName = scanner.nextLine();
        findRecipeByRecipeName(recipeName);
    }

    public void findRecipeByRecipeName(String Name) {
        Map<String, List<Ingredient>> findRecipes = recipeRepository.selectAllRecipes();
        try {
            List<Ingredient> ingredients = findRecipes.get(Name);
            for (Ingredient ingredient : ingredients)
                System.out.print(ingredient.getName() + " ");
        } catch (NullPointerException e) {
            System.out.println("그런 레시피는 없어!");
        }
    }
}
