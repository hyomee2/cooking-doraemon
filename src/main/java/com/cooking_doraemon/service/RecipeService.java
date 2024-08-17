package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.aggregate.Recipe;
import com.cooking_doraemon.repository.RecipeRepository;

import java.util.*;

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

    // 레시피에 메뉴 추가하기
    public void addRecipe() {
        // 레시피 메뉴의 재료를 담을 list 선언
        List<Ingredient> ingredients = new ArrayList<>();
        System.out.print("추가하고 싶은 레시피의 메뉴 이름을 입력해줘!:");
        String recipeName = scanner.nextLine();

        System.out.print("메뉴를 만들기 위해 필요한 재료를 한 개씩 입력해줘! (다 입력했다면 exit를 입력해줘~): ");
        while(true) {
            String requiredIngredient = scanner.nextLine();

            if (requiredIngredient.equals("exit"))
                break;

            Ingredient ingredient = new Ingredient(requiredIngredient);
            ingredients.add(ingredient);
        }

        Map<String, List<Ingredient>> newRecipe = new HashMap<>();
        recipeRepository.selectAllRecipes().put(recipeName, ingredients);
    }
}
