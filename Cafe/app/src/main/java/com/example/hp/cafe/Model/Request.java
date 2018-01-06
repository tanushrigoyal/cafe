package com.example.hp.cafe.Model;

import java.util.List;

/**
 * Created by Hp on 06-01-2018.
 */

public class Request {

    private String Phone;
    private String Name;
    private String Total;
    private String Address;

    private List<Order> foods; //list of ordered food

    public Request() {
    }

    public Request(String phone, String name, String total, String address, List<Order> foods) {
        Phone = phone;
        Name = name;
        Total = total;
        Address = address;
        this.foods = foods;
    }



    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getAddress() {
        return Address;
    }

    public void setRollno(String rollno) {Address= rollno;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}

