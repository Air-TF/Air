package com.air.bean;

public enum Menu {
    CATE("category"), SUBCATE("category"), ITEM("item"), PARAM("param");
    private String typeName;

    public String getTypeName() {
        return this.typeName;
    }

    Menu(String typeName) {
        this.typeName=typeName;
    }

    public static Menu getMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getTypeName().equals(name)) {
                return menu;
            }
        }
        return null;
    }
}
