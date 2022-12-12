package ru.croc.school.task18;

import java.util.List;

public class Order {

    private int id;
    private String user_login;
    private List<Product> products;

    public Order(String user_login, List<Product> product){
        this.user_login = user_login;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public String getUser_login() {
        return user_login;
    }

    public List<Product> getProduct() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }
}
