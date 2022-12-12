package ru.croc.school.task19;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetDeliveryData {

    private CreateDatabaseWithDelivery database;

    public GetDeliveryData(CreateDatabaseWithDelivery database){
        this.database = database;
    }

    public List<List<Object>> getOrdersDeliveryTime(int id) throws SQLException {
        try(Statement statement = database.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT o.id, delivery_time, e.name " +
                    "FROM users u INNER JOIN orders o ON u.id = o.user_id INNER JOIN employees e ON o.courier_id = e.id " +
                    "WHERE u.id = " + id);
            List<List<Object>> resData = new ArrayList<>();
            while (resultSet.next()){
                resData.add(Arrays.asList(resultSet.getInt("id"),
                        resultSet.getString("delivery_time"), resultSet.getString("name")));
            }
            return resData;
        }
    }

    public List<List<Object>> getOrdersToDeliver(int id) throws SQLException{
        try (Statement statement = database.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT o.id, user_login " +
                    "FROM employees e INNER JOIN orders o ON e.id = o.courier_id INNER JOIN users u ON u.id = o.user_id " +
                    "WHERE e.id = " + id);
            List<List<Object>> resDataSecond = new ArrayList<>();
            while (resultSet.next()){
                resDataSecond.add(Arrays.asList(resultSet.getInt("id"),
                        resultSet.getString("delivery_time"), resultSet.getString("name")));
            }
            return resDataSecond;
        }
    }
}
