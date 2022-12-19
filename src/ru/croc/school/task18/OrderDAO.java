package ru.croc.school.task18;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private final Connection connection;

    public OrderDAO(Connection connection){
        this.connection = connection;
    }

    public Order createOrder(String user_login, List<Product> products){
        try (Statement statement = connection.createStatement()){
            int newOrderId = 0;
            try (ResultSet resultSet = statement.executeQuery("SELECT id FROM order GROUP BY id ORDER BY id DESC LIMIT 1")){
                while (resultSet.next()){
                    newOrderId = resultSet.getInt("id") + 1;
                }
            }
            for (Product product:products){
                statement.execute("INSERT INTO order (id, user_login, article, product, price)" +
                        "VALUES (+'" + newOrderId +"', '" + user_login+"', '"+ product.getArticle()+"', '"+product.getName()+"', '"+ product.getPrice()+"');");
            }
            return returnOrders(newOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order returnOrders(int orderID) throws SQLException{
        try (Statement statement = connection.createStatement()){
            Order order1 = null;
            List <Product> products = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM order WHERE id = '"+orderID+"' LIMIT 1")){
                while (resultSet.next()){
                    order1 = new Order(resultSet.getInt("id"), resultSet.getString("user_name"));
                }
            }
            try (ResultSet resultSet2 = statement.executeQuery("SELECT article, product, price FROM order WHERE id = '"+orderID+"';")){
                while (resultSet2.next()){
                    order1.addToProducts(new Product(resultSet2.getString("article"), resultSet2.getString("product"),
                            resultSet2.getInt("price")));
                }
            }
            return order1;
        }
    }
}
