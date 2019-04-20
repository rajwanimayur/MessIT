package com.example.mayur.messit;

public class Menu_Item {
    private String itemCategory;
    private String itemName;

    public Menu_Item(String itemCategory, String itemName) {
        this.itemCategory = itemCategory;
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
