package com.cooking_doraemon.repository;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.aggregate.Recipe;

import java.io.*;
import java.util.*;

public class RecipeRepository {
    //
    private static final Map<String, List<Ingredient>> recipeList = new HashMap<>();
    private static final String RECIPE_FILE_PATH = "src/main/java/com/cooking_doraemon/db/recipeDB.dat";
    List<Ingredient> ingredients = new ArrayList<>();

    public RecipeRepository() {
        File recipeFile = new File(RECIPE_FILE_PATH);

        if (!recipeFile.exists()) {
            Map<String, List<Ingredient>> recipes = new HashMap<>();

            Recipe bananaMilk = new Recipe("바나나우유", Arrays.asList(new Ingredient("바나나"), new Ingredient("우유")));
            recipes.put(bananaMilk.getName(), bananaMilk.getIngredients());

            Recipe cheeseCake = new Recipe("치즈케이크", Arrays.asList(new Ingredient("치즈"), new Ingredient("우유"), new Ingredient("밀가루")));
            recipes.put(cheeseCake.getName(), cheeseCake.getIngredients());

            Recipe sweetPotatoCake = new Recipe("고구마케이크", Arrays.asList(new Ingredient("고구마"), new Ingredient("우유"), new Ingredient("밀가루")));
            recipes.put(sweetPotatoCake.getName(), sweetPotatoCake.getIngredients());

            Recipe sweetPotatoLatte = new Recipe("고구마라떼", Arrays.asList(new Ingredient("고구마"), new Ingredient("우유")));
            recipes.put(sweetPotatoLatte.getName(), sweetPotatoLatte.getIngredients());

            Recipe sweetPotatoFries = new Recipe("고구마튀김", Arrays.asList(new Ingredient("고구마"), new Ingredient("기름")));
            recipes.put(sweetPotatoFries.getName(), sweetPotatoFries.getIngredients());

            Recipe sandwich = new Recipe("샌드위치", Arrays.asList(new Ingredient("계란"), new Ingredient("빵"), new Ingredient("소시지"), new Ingredient("양상추")));
            recipes.put(sandwich.getName(), sandwich.getIngredients());

            Recipe sweetPotatoSalad = new Recipe("고구마샐러드", Arrays.asList(new Ingredient("고구마"), new Ingredient("양상추"), new Ingredient("바나나")));
            recipes.put(sweetPotatoSalad.getName(), sweetPotatoSalad.getIngredients());

            Recipe hotdog = new Recipe("핫도그", Arrays.asList(new Ingredient("밀가루"), new Ingredient("소시지"), new Ingredient("기름"), new Ingredient("계란")));
            recipes.put(hotdog.getName(), hotdog.getIngredients());

            Recipe chicken = new Recipe("치킨", Arrays.asList(new Ingredient("기름"), new Ingredient("닭"), new Ingredient("밀가루")));
            recipes.put(chicken.getName(), chicken.getIngredients());

            Recipe cheeseChickenSkewers = new Recipe("치즈닭꼬치", Arrays.asList(new Ingredient("닭"), new Ingredient("기름"), new Ingredient("치즈")));
            recipes.put(cheeseChickenSkewers.getName(), cheeseChickenSkewers.getIngredients());

            saveRecipes(recipeFile, recipes);
        }
        loadRecipes(recipeFile);
    }

    public Map<String, List<Ingredient>> getRecipeList() {
        return recipeList;
    }

    private void loadRecipes(File recipeFile) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(recipeFile))) {
            while(true) {
                String recipe = (String) ois.readObject();
                List<Ingredient> ingredients = (List<Ingredient>) ois.readObject();
                recipeList.put(recipe, ingredients);
            }
        } catch(EOFException e) {
            System.out.println("레시피 정보를 모두 로딩하였습니다.");
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveRecipes(File recipeFile, Map<String, List<Ingredient>> recipes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(recipeFile))) {
            for (Map.Entry<String, List<Ingredient>> entry : recipes.entrySet()) {
                oos.writeObject(entry.getKey());
                oos.writeObject(entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
