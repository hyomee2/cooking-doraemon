package com.cooking_doraemon.aggregate;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private int level;
    private int exp;

    public User(String username) {
        this.username = username;
        this.level = 1;
        this.exp = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }
}
