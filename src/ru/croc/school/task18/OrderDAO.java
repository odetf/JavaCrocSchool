package ru.croc.school.task18;

import java.sql.*;
import java.util.List;

public class OrderDAO {

    private Connection connection;

    public OrderDAO(Connection connection){
        this.connection = connection;
    }

    public Order createOrder(String user_login, List<Product> products){
        try (Statement statement = connection.createStatement()){
            for (Product product:products){
                statement.execute("INSERT INTO orders (user_login, article, product, price)" +
                        "VALUES (+'" + user_login+"', '"+ product.getArticle()+"', '"+product.getName()+"', '"+ product.getPrice()+"');");
            }
            return new Order(user_login, products);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
