package com.cooking_doraemon.run;

import com.cooking_doraemon.aggregate.Ingredient;
import com.cooking_doraemon.aggregate.User;
import com.cooking_doraemon.service.MartService;
import com.cooking_doraemon.service.RecipeService;
import com.cooking_doraemon.service.RefrigeratorService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);
    private static final MartService martService = new MartService();
    private static final RefrigeratorService refrigeratorService = new RefrigeratorService();
    private static final RecipeService recipeService = new RecipeService();

    public static void main(String[] args) {


        /* 시작 문구 출력 */
        printStartingPhrase();
        /* 유저 설정 */
        String userName = setUser();
        User user = new User(userName);
        System.out.println("user = " + user.getCookingLevel());
        System.out.println("user = " + user.getExp());

        user.setExp(150);
        System.out.println("user = " + user.getCookingLevel());
        System.out.println("user = " + user.getExp());

        user.setExp(50);
        System.out.println("user = " + user.getCookingLevel());
        System.out.println("user = " + user.getExp());


        while (true) {
            System.out.println("\n지금 뭐 하고 싶어?");
            printMenu();
            System.out.print("아래 번호를 입력해줘~~~ : ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    Map<Ingredient, Integer> shopGroceries = martService.shopGroceries();
                    refrigeratorService.addRefrigerator(shopGroceries);
                    break;
                case "2":
                    refrigeratorService.printRefrigerators();
                    break;
                case "4":
                    recipeService.findAllRecipeName();
                    break;
                case "5":
                    recipeService.addRecipe();
                    break;
                case "6": break;
                case "7": break;
                case "9":
                    System.out.println("\n" + "다음에 또 만나자~");
                    return;
                default:
                    System.out.println("\n번호를 잘못 입력했어. 다시 골라줘~\n");
                    break;
            }
        }
    }

    private static void printStartingPhrase() {
        System.out.println(
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠛⠋⠉⠉⠉⠉⠉⣉⠉⠛⠛⠿⣿⣿⣿⣿⣿⣿ \n" +
                        "⣿⣿⣿⣿⣿⣿⣿⡿⠛⠁⠀⠀⠀⣾⣿⡿⣷⡀⡿⠿⣿⣆⠀⠀⠀⠙⠻⣿⣿⣿⣿ \n" +
                        "⣿⣿⣿⣿⣿⣿⠋⠀⠀⠀⠀⠀⣀⣿⣿⠠⢸⡧⠁⠂⣿⣿⢰⣶⣦⡤⠀⠈⢩⣿⣿ \n" +
                        "⣿⣿⣿⣿⣿⠁⠀⠀⢀⣀⣈⣉⣛⣦⣭⣿⣭⡄⠀⠀⢈⣴⣿⣋⣡⣴⣾⡦⠀⠻⣿ \n" +
                        "⣿⣿⣿⣿⡇⠀⠀⠐⠛⠛⠛⠛⠛⢻⣿⣿⣿⣿⣶⡆⢻⣿⣿⣿⣤⣤⣴⣶⣆⢸ \n" +
                        "⣿⣿⣿⣿⡇⠀⠀⣿⣿⣿⠿⠟⠛⠛⠛⠛⠛⠛⠛⠃⠸⠿⠿⠿⠤⠤⠭⠍⡁⢈ \n" +
                        "⡿⣛⣭⣭⡃⠀⠀⣉⣡⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⠃⣼ ⣿⣿\n" +
                        "⡑⣿⣿⣿⣿⠀⠀⠹⣿⣿⣄⠀⢀⣤⣶⣶⣾⣿⣶⣶⣤⣀⠀⠀⣠⣾⣿⠃⣼ ⣿ \n" +
                        "⣷⣬⣉⠉⠁⠀⠀⠀⠘⢿⣿⣷⣭⡛⠿⠿⢿⣿⣿⠿⠿⢟⣥⣾⣿⡿⢃⣼⣿⣿ \n" +
                        "⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀⠈⠛⠛⠛⠛⠒⠒⠒⠒⠒⠚⠛⠛⠛⠋⠠⣾⣿⣿⣿");

        System.out.println("도라에몽의 요리 생활 시작!\n");
        System.out.println("안녕? 나는 너랑 함께 할 도라에몽이야~\n");
    }

    private static String setUser() {
        System.out.print("너를 뭐라고 불러줄까? : ");
        String userName = scanner.nextLine();
        System.out.println("\n==================================");
        System.out.println(userName + " 안녕?");

        return userName;
    }

    private static void printMenu() {
        System.out.println("====================================");
        System.out.println("1. 마트 가기");
        System.out.println("2. 냉장고 속 재료 보기");
        System.out.println("4. 레시피 보기");
        System.out.println("5. 레시피 추가하기");
        System.out.println("6. 요리하기");
        System.out.println("7. 내 프로필 보기");
        System.out.println("9. 종료하기");
        System.out.println("====================================");
    }
}
