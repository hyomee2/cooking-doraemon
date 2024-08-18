package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.RecipeRepository;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RecipeService {
    private static RecipeService instance;
    Scanner scanner = new Scanner(System.in);
    private static final RecipeRepository recipeRepository = new RecipeRepository();

    // 레시피에 저장된 음식 목록 보기
    public void findAllRecipeName() {
        System.out.println("저장된 레시피 목록이야!\n");
        System.out.println("==============================\n");
        showMenusInRecipe();

        System.out.println("\n==============================\n");
        System.out.print("레시피를 더 자세히 보고 싶은 음식 이름을 입력해줘!: ");
        String recipeName = scanner.nextLine();
        findRecipeByRecipeName(recipeName);
    }

    public static void showMenusInRecipe() {
        Map<String, List<Ingredient>> findRecipes = RecipeRepository.getRecipeList();

        for (Map.Entry<String, List<Ingredient>> entry : findRecipes.entrySet()) {
            String recipeName = entry.getKey();
            System.out.println(recipeName);
        }
    }

    public void findRecipeByRecipeName(String Name) {
        Map<String, List<Ingredient>> findRecipes = RecipeRepository.getRecipeList();
        try {
            List<Ingredient> ingredients = findRecipes.get(Name);
            for (Ingredient ingredient : ingredients)
                System.out.print(ingredient.getName() + " ");
        } catch (NullPointerException e) {
            System.out.println("그런 레시피는 없어!");
        }
    }
}
