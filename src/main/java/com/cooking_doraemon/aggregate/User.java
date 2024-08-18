package com.cooking_doraemon.aggregate;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private CookingLevel cookingLevel;
    private int exp;

    public User(String username) {
        this.username = username;
        this.cookingLevel = CookingLevel.BEGINNER;
        this.exp = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp += exp;
        checkLevelUp();
    }

    public CookingLevel getCookingLevel() {
        return cookingLevel;
    }

    private void checkLevelUp() {
        while (exp >= cookingLevel.getRequiredExp()) {
            exp -= cookingLevel.getRequiredExp();
            if (cookingLevel.getLevel() < CookingLevel.values().length) {
                cookingLevel = CookingLevel.getByLevel(cookingLevel.getLevel() + 1);
            } else {
                break;
            }
        }
    }
}
