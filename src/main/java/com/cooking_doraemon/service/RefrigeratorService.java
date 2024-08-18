package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.repository.RefrigeratorRepository;

import java.util.Map;
import java.util.Scanner;

public class RefrigeratorService {

    private final Scanner scanner = new Scanner(System.in);
    private final RefrigeratorRepository refrigeratorRepository;

    public RefrigeratorService(RefrigeratorRepository refrigeratorRepository) {
        this.refrigeratorRepository = refrigeratorRepository;
    }

    private Map<Ingredient, Integer> getRefrigerators() {
        return refrigeratorRepository.getRefrigerator();
    }

    public void printRefrigerators() {
        Map<Ingredient, Integer> refrigerators = getRefrigerators();

        System.out.println("\n==============================");

        if (refrigerators.isEmpty()) {
            System.out.println("\n냉장고가 비었어 ●︿●");
            System.out.println("\n==============================");
        }
        else {
            System.out.println("\n냉장고에 들어있는 재료야!\n");

            for (Map.Entry<Ingredient, Integer> entry : refrigerators.entrySet()) {
                Ingredient ingredient = entry.getKey();
                Integer quantity = entry.getValue();
                System.out.println(ingredient + ", 수량: " + quantity);
            }

            System.out.println("\n==============================");
        }
    }

    public void addRefrigerator(Map<Ingredient, Integer> ingredients) {
        ingredients.forEach((ingredient, quantity) -> refrigeratorRepository.addRefrigerator(Map.of(ingredient, quantity)));
    }

    public void removeRefrigerator() {

        while (true) {
            printRefrigerators();

            if (!(getRefrigerators().isEmpty())) {
                System.out.print("\n버리고 싶은 재료를 입력해줘!(정리를 그만하려면 exit를 입력해!): ");
                String removeIngredient = scanner.nextLine();
                boolean containIngredient = false;

                for (Ingredient ingredient : refrigeratorRepository.getRefrigerator().keySet()) {
                    if (ingredient.getName().equals(removeIngredient)) {
                        getRefrigerators().remove(ingredient);
                        containIngredient = true;
                        break;
                    }
                }

                if (removeIngredient.equals("exit")) {
                    System.out.println("\n==============================");
                    break;
                }

                if (!containIngredient)
                    System.out.println("냉장고에 그런 재료는 없어!");
            }
            else {
                break;
            }
        }
    }
}
