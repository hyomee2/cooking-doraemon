package com.cooking_doraemon.aggregate;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private String name;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
