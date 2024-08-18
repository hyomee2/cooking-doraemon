package com.cooking_doraemon.repository;

import com.cooking_doraemon.aggregate.Ingredient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MartRepository {

    private static final List<Ingredient> ingredientList = new ArrayList<Ingredient>();
    private static final String MART_FILE_PATH = "src/main/java/com/cooking_doraemon/db/martDB.dat";

    public MartRepository() {
        File martFile = new File(MART_FILE_PATH);

        if (!martFile.exists()) {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(new Ingredient("바나나"));
            ingredients.add(new Ingredient("우유"));
            ingredients.add(new Ingredient("치즈"));
            ingredients.add(new Ingredient("밀가루"));
            ingredients.add(new Ingredient("고구마"));
            ingredients.add(new Ingredient("기름"));
            ingredients.add(new Ingredient("계란"));
            ingredients.add(new Ingredient("빵"));
            ingredients.add(new Ingredient("소세지"));
            ingredients.add(new Ingredient("양상추"));
            ingredients.add(new Ingredient("닭"));

            saveIngredient(martFile, ingredients);
        }

        loadIngredient(martFile);
    }

    private void loadIngredient(File martFile) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(martFile))) {

            while (true) {
                try {
                    ingredientList.add((Ingredient) ois.readObject());
                } catch (EOFException e) {
                    // System.out.println("마트 상품 리스트를 모두 로딩하였습니다.");
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveIngredient(File martFile, List<Ingredient> ingredients) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(martFile))) {

            for (Ingredient ingredient : ingredients) {
                oos.writeObject(ingredient);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }
}
