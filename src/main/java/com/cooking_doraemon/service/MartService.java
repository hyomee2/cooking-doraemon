package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.MartRepository;

import java.util.*;

public class MartService {

    private final MartRepository martRepository = new MartRepository();

    public Map<Ingredient, Integer> shopGroceries() {
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

    private Map<Ingredient, Integer> subMartMenu() {
        Scanner scanner = new Scanner(System.in);
        List<Ingredient> ingredientList = getIngredientList();
        Map<Ingredient, Integer> cart = new HashMap<>();

        while (true) {
            System.out.print("물건이랑 개수를 입력해줘 선택(물건은 하나씩 골라줘~ / 나가고 싶으면 exit) : ");
            String input = scanner.next();
            if (input.equals("exit")) {
                System.out.println("잘가~ 다음에 또 와!");
                break;
            }
            Ingredient newIngredient = new Ingredient(input);

            int quantity = scanner.nextInt();

            if (quantity <= 0) {
                System.out.println("개수 입력이 잘못되었어~");
                continue;
            }

            checkCart(cart, ingredientList, newIngredient, quantity);
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

    private void checkCart(Map<Ingredient, Integer> cart, List<Ingredient> ingredientList, Ingredient newIngredient, int quantity) {
        if (ingredientList.contains(newIngredient)) {
            if (cart.containsKey(newIngredient)) {
                cart.put(newIngredient, cart.get(newIngredient) + quantity);
            } else {
                cart.put(newIngredient, quantity);
            }
        } else {
            System.out.println("그런 물건은 없어!");
        }
    }
}
