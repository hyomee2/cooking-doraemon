package com.cooking_doraemon.aggregate;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    private String name;
    private List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
}
