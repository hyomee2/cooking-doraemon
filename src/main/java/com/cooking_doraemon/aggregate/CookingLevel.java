package com.cooking_doraemon.aggregate;

public enum CookingLevel {
    BEGINNER(1, "요리왕초보"),
    INTERMEDIATE(2, "요리적응러"),
    ADVANCED(3, "요리마스터"),
    GOD(4, "요리의신");

    private final int level;
    private final String desc;

    CookingLevel(int level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }

    public static CookingLevel getByLevel(int level) {
        for (CookingLevel lv : CookingLevel.values()) {
            if (lv.getLevel() == level) {
                return lv;
            }
        }
        return null;
    }

    public static int getRequiredExp() {
        return 100;
    }
}
