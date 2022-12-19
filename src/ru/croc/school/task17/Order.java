package ru.croc.school.task17;

public class Order {

    private int id;
    private User user;
    private Product product;

    public Order(int id, User user, Product product){
        this.id =  id;
        this.user = user;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
}
