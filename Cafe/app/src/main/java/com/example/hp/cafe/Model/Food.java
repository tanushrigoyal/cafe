package com.example.hp.cafe.Model;

/**
 * Created by Hp on 01-01-2018.
 */

public class Food {
    private String Name, Image, Description, Price, MenuId;

    public Food() {
    }

    public Food(String name, String image, String description, String price, String menuId) {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }


}
