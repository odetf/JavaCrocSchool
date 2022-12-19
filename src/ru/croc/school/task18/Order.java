package ru.croc.school.task18;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private final String userLogin;
    private final List<Product> products;

    public Order(int id, String userLogin){
        this.userLogin = userLogin;
        products = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void addToProducts(Product product) {
        this.products.add(product);
    }

    public void setId(int id) {
        this.id = id;
    }
}
