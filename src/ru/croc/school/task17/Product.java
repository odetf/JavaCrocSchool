package ru.croc.school.task17;

public class Product {

    private String article;
    private String productName;
    private int price;

    public Product(String article, String productName, int price){
        this.article = article;
        this.productName = productName;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getArticle() {
        return article;
    }

    public String getProductName() {
        return productName;
    }
}
