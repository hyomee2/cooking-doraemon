package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.RecipeRepository;
import com.cooking_doraemon.repository.RefrigeratorRepository;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CookingService {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public String chooseMenu() {
        System.out.println("너가 만들 수 있는 음식들이야!");
        RecipeService.showMenusInRecipe();

        System.out.println("\n==============================");
        System.out.println("어떤 음식을 만들고 싶어?: ");

        return scanner.nextLine();
    }

    public boolean cookingCheck(String chosenMenu) {
        // 입력한 메뉴가 레시피북에 있는 메뉴인지 체크
        boolean menuInRecipe = checkMenuInRecipe(chosenMenu);

        // 입력한 메뉴를 만들기 위해 필요한 재료가 냉장고에 있는지 체크
        if (menuInRecipe) {
            return checkIngredientsForCooking(chosenMenu);
        }
        else
            return false;
    }

    public boolean checkMenuInRecipe(String chosenMenu) {
        boolean result;

        if (RecipeRepository.getRecipeList().containsKey(chosenMenu)) {
            result = true;
        }
        else {
            result = false;
            System.out.println("레시피북에 없는 메뉴야!");
        }
        return result;
    }

    public boolean checkIngredientsForCooking(String menuName) {
        boolean checkIngredients = true;

        List<Ingredient> ingredients = RecipeRepository.getRecipeList().get(menuName);
        for (Ingredient ingredient : ingredients) {
            if (!(RefrigeratorRepository.getRefrigerator().containsKey(ingredient))) {
                checkIngredients = false;
                System.out.println("냉장고에 " + ingredient.getName() + " 부족해..");
            }
        }

        return checkIngredients;
    }

    public int cooking(String chosenMenu) {
        System.out.println("요리를 시작하자!");
        System.out.println("\n...");
        System.out.println("요리중");
        System.out.println("\n...");
        RefrigeratorRepository.useIngredient(chosenMenu);


        // 요리의 성공/실패 랜덤값
        boolean cookingSuccess = random.nextBoolean();
        if (cookingSuccess) {
            System.out.println("\n" + chosenMenu + " 만들기 성공~!");
            // 숙련도 랜덤값
            int gainedExp = random.nextInt(20) + 10;
            System.out.println(gainedExp + "만큼의 숙련도를 획득했어!");
            return gainedExp;
        }
        else {
            System.out.println("요리를 실패했어...");
            return 0;
        }
    }
}
