package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.RecipeRepository;
import com.cooking_doraemon.repository.RefrigeratorRepository;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CookingService {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final RefrigeratorRepository refrigeratorRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    public CookingService(RefrigeratorRepository refrigeratorRepository, RecipeRepository recipeRepository, RecipeService recipeService) {
        this.refrigeratorRepository = refrigeratorRepository;
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }

    public String chooseMenu() {
        System.out.println("\n==============================");
        System.out.println("\n너가 만들 수 있는 음식들이야!\n");

        recipeService.showMenusInRecipe();

        System.out.println("\n==============================");
        System.out.print("\n어떤 음식을 만들고 싶어?: ");

        return scanner.nextLine();
    }

    public boolean cookingCheck(String chosenMenu) {
        // 입력한 메뉴가 레시피북에 있는 메뉴인지 체크
        boolean menuInRecipe = checkMenuInRecipe(chosenMenu);

        // 입력한 메뉴를 만들기 위해 필요한 재료가 냉장고에 있는지 체크
        if (menuInRecipe) {
            return checkIngredientsForCooking(chosenMenu);
        } else {
            return false;
        }
    }

    private boolean checkMenuInRecipe(String chosenMenu) {
        boolean result;

        if (recipeRepository.getRecipeList().containsKey(chosenMenu)) {
            result = true;
        } else {
            result = false;
            System.out.println("\n레시피북에 없는 메뉴야!");
            System.out.println("\n==============================");
        }
        return result;
    }

    private boolean checkIngredientsForCooking(String menuName) {
        boolean checkIngredients = true;

        List<Ingredient> ingredients = recipeRepository.getRecipeList().get(menuName);
        System.out.println();
        for (Ingredient ingredient : ingredients) {
            if (!(refrigeratorRepository.getRefrigerator().containsKey(ingredient))) {
                checkIngredients = false;
                System.out.println("냉장고에 " + ingredient.getName() + " 부족해..");
            }
        }
        System.out.println("\n==============================");

        return checkIngredients;
    }

    public int cooking(String chosenMenu) {
        System.out.println("\n요리를 시작하자!");
        System.out.println("\n...");
        System.out.println("\n요리중");
        System.out.println("\n...");
        refrigeratorRepository.useIngredient(chosenMenu);


        // 요리의 성공/실패 랜덤값
        boolean cookingSuccess = random.nextBoolean();
        if (cookingSuccess) {
            System.out.println("\n" + chosenMenu + " 만들기 성공~!\n");
            // 숙련도 랜덤값
            int gainedExp = random.nextInt(10) + 20;
            System.out.println(gainedExp + "만큼의 숙련도를 획득했어!");
            System.out.println("\n==============================");

            return gainedExp;
        } else {
            System.out.println("\n요리를 실패했어 (っ◞‸◟c)");
            System.out.println("\n==============================");
            return 0;
        }
    }
}
