package com.cooking_doraemon.service;

import com.cooking_doraemon.aggregate.User;

public class UserService {

    public void getUser(User user) {
        System.out.println("\n==============================\n");
        System.out.println("닉네임 : " + user.getUsername());
        System.out.println("레벨 : " + user.getCookingLevel().getDesc());
        System.out.println("숙련도 : " + user.getExp());
        System.out.println("\n==============================");
    }
}
