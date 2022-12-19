package ru.croc.school.task18;

public class Product {

    private final String article;
    private final String name;
    private final int price;

    public Product(String article, String name, int price){
        this.article = article;
        this.name = name;
        this.price = price;
    }

    public String getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
