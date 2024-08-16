package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.MartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MartService {

    private final MartRepository martRepository = new MartRepository();

    public List<Ingredient> shopGroceries() {
        greetingMart();
        printIngredientList();
        return subMartMenu();
    }

    private List<Ingredient> getIngredientList() {
        return martRepository.getIngredientList();
    }

    private void greetingMart() {
        System.out.println("\n=========================");
        System.out.println("형미 마트에 온 걸 환영해!\n");
        System.out.println("아래 물건 중에 사고 싶은 거를 골라봐\n");
    }

    private List<Ingredient> subMartMenu() {
        Scanner scanner = new Scanner(System.in);
        List<Ingredient> ingredientList = getIngredientList();
        List<Ingredient> cart = new ArrayList<>();

        while (true) {
            System.out.print("물건 선택(물건은 하나씩 골라줘~ / 나가고 싶으면 exit) : ");
            String input = scanner.nextLine();

            Ingredient newIngredient = new Ingredient(input);

            if (ingredientList.contains(newIngredient)) {
                cart.add(newIngredient);
            } else if (input.equals("exit")) {
                System.out.println("잘가~ 다음에 또 와!");
                break;
            } else {
                System.out.println("그런 물건은 없어!");
            }
        }

        return cart;
    }

    private void printIngredientList() {
        List<Ingredient> ingredientList = getIngredientList();

        System.out.print("[");
        for (Ingredient ingredient : ingredientList) {
            System.out.print(ingredient.getName() + " ");
        }
        System.out.print("]\n");
    }
}
