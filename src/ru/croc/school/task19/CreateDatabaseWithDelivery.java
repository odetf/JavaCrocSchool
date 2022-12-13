package ru.croc.school.task19;

import java.sql.*;

public class CreateDatabaseWithDelivery {

    private Connection connection;

    public CreateDatabaseWithDelivery(String databaseURL, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(databaseURL, username, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void createTables() {
        try (Statement st = connection.createStatement()){
            String mainTable = "CREATE TABLE orders (" +
                    "id INT," +
                    "user_id INT REFERENCES users(id)," +
                    "article VARCHAR(255) REFERENCES products(article)," +
                    "product VARCHAR(255)," +
                    "price INT," +
                    "delivery_time INT," +
                    "courier_id INT REFERENCES employees(id));";

            String productsTable = "CREATE TABLE products (" +
                    "article VARCHAR(255) PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "price INT);";

            String usersTable = "CREATE TABLE users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "user_login VARCHAR(255));";

            String employeesTable = "CREATE TABLE employees (" +
                    "id INT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "surname VARCHAR(255));";

            st.execute(employeesTable);
            st.execute(productsTable);
            st.execute(usersTable);
            st.execute(mainTable);

            System.out.println("Creation completed.");
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }


    public void insertData(String id, String user_login, String article, String product, String price, String deliveryTime, String courierID){
        try(Statement st = connection.createStatement()){

            int user_id;

            String insertUsers = "INSERT INTO users (user_login) " +
                    "VALUES ('" + user_login + "');";

            if (!checkUniqueness(user_login, "user_login", "users")) {
                st.executeUpdate(insertUsers);
            }

            String insertGoods = "INSERT INTO products (article, name, price) " +
                    "VALUES ('" + article + "', '" + product + "', '" + price + "');";

            if (!checkUniqueness(article, "article", "products")) {
                st.executeUpdate(insertGoods);
            }

            ResultSet rs1 = st.executeQuery("SELECT id FROM users WHERE user_login = '" + user_login + "';");
            while (rs1.next()) {
                user_id = rs1.getInt("id");
                st.executeUpdate("INSERT INTO orders (id, user_id, article, product, price, delivery_time, courier_id) " +
                        "VALUES ('" + id + "', '" + user_id + "', '" + article + "', '" + product + "', '" + price + "', '" + deliveryTime + "', '" + courierID + "';");
            }



            System.out.println("Insertion completed.");
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public void insertNewEmployer(String id, String name, String surname) throws SQLException{
        try(Statement statement = connection.createStatement()){
            String sqlQuery = "INSERT INTO employees (id, name, surname) VALUES ('" + id + "', '" + name + "', '" + surname + "');";
            statement.execute(sqlQuery);
        }
    }

    public boolean checkUniqueness(String indicatorToCheck, String type, String tableName) throws SQLException{
        String getResult = "SELECT "+type+" FROM "+tableName+" WHERE "+type+" = '"+indicatorToCheck+"';";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(getResult);
            return resultSet.next();
        }
    }

}