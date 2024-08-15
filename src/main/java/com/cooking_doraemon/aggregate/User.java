package com.cooking_doraemon.aggregate;

public class User {

    private String username;
    private int level;
    private int exp;

    public User(String username, int level, int exp) {
        this.username = username;
        this.level = level;
        this.exp = exp;
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
