package com.example.hp.cafe.Model;

/**
 * Created by Hp on 01-01-2018.
 */

public class category {
    private String Name;
    private String Image;

    public category(String name, String image) {
        Name = name;
        Image = image;
    }

    public category() {
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
}


