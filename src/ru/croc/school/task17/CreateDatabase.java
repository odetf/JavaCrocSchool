package ru.croc.school.task17;

import java.sql.*;

public class CreateDatabase {

    private Connection connection;

    public CreateDatabase(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void createTables() {
        try (Statement st = connection.createStatement()) {
            String mainTable = "CREATE TABLE orders (" +
                    "id INT PRIMARY KEY," +
                    "user_id INT REFERENCES users(id)," +
                    "article VARCHAR(255) REFERENCES products(article)," +
                    "product VARCHAR(255)," +
                    "price INT);";

            String productsTable = "CREATE TABLE products (" +
                    "article VARCHAR(255) PRIMARY KEY UNIQUE," +
                    "name VARCHAR(255));";

            String usersTable = "CREATE TABLE users (" +
                    "id INT PRIMARY KEY UNIQUE AUTO_INCREMENT," +
                    "user_login VARCHAR(255));";

            st.execute(productsTable);
            st.execute(usersTable);
            st.execute(mainTable);
            System.out.println("Creation completed.");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }


    public void insertData(String id, String user_login, String article, String product, String price) {
        try (Statement st = connection.createStatement()) {

            int user_id;

            String insertUsers = "INSERT INTO users (user_login) " +
                    "VALUES ('" + user_login + "');";

            if (checkUniqueness(user_login, "user_login", "users")) {
                st.executeUpdate(insertUsers);
            }

            String insertGoods = "INSERT INTO products (article, name, price) " +
                    "VALUES ('" + article + "', '" + product + "', '" + price + "');";

            if (checkUniqueness(article, "article", "products")) {
                st.executeUpdate(insertGoods);
            }

            ResultSet rs1 = st.executeQuery("SELECT id FROM users WHERE user_login = '" + user_login + "';");
            while (rs1.next()) {
                user_id = rs1.getInt("id");
                st.executeUpdate("INSERT INTO orders (id, user_id, article, product, price) " +
                        "VALUES ('" + id + "', '" + user_id + "', '" + article + "', '" + product + "', '" + price + "');");
            }



            System.out.println("Insertion completed.");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public boolean checkUniqueness(String indicatorToCheck, String type, String tableName) throws SQLException {
        String getResult = "SELECT " + type + " FROM " + tableName + " WHERE " + type + " = '" + indicatorToCheck + "';";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getResult);
            return resultSet.next();
        }
    }

    public ResultSet checkCreation() {
        ResultSet rs = null;
        try (Statement statement = connection.createStatement()) {
            rs = statement.executeQuery("SELECT * FROM orders");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

}
