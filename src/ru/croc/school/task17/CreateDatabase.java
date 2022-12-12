package ru.croc.school.task17;

import java.sql.*;

public class CreateDatabase {

    private Connection connection;

    public CreateDatabase(String databaseURL, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(databaseURL, username, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void createTables() {
        try (Statement st = connection.createStatement()){
            String mainTable = "CREATE TABLE orders (" +
                    "id INT PRIMARY KEY," +
                    "user_id INT REFERENCES users(id)," +
                    "article VARCHAR(255) REFERENCES products(article)," +
                    "product VARCHAR(255)," +
                    "price INT);";

            String productsTable = "CREATE TABLE products (" +
                    "article VARCHAR(255) PRIMARY KEY UNIQUE AUTO_INCREMENT," +
                    "name VARCHAR(255));";

            String usersTable = "CREATE TABLE users (" +
                    "id INT PRIMARY KEY UNIQUE AUTO_INCREMENT," +
                    "user_login VARCHAR(255));";

            st.execute(productsTable);
            st.execute(usersTable);
            st.execute(mainTable);
            System.out.println("Creation completed.");
        }
        catch (SQLException sqlException){
            sqlException.getMessage();
        }
    }


    public void insertData(String id, String user_login, String article, String product, String price){
        try(Statement st = connection.createStatement()){
            String insertMain = "INSERT INTO orders (id, user_login, article, product, price) " +
                    "VALUES ('" + id + "', '" + user_login + "', '" + article + "', '" + product + "', '" + price + "');";

            String insertGoods = "INSERT INTO products (article, name, price) " +
                    "VALUES ('" + article + "', '" + product + "', '" + price + "');";

            String insertUsers = "INSERT INTO users (user_login) " +
                    "VALUES ('" + user_login + "');";

            if (checkUniqueness(article, "article", "products")){
                st.execute(insertGoods);
            }

            if (checkUniqueness(user_login, "user_login", "users")){
                st.execute(insertUsers);
            }

            st.execute(insertMain);

            System.out.println("Insertion completed.");
        }
        catch(SQLException sqlException){
            sqlException.getMessage();
        }
    }

    public boolean checkUniqueness(String indicatorToCheck, String type, String tableName) throws SQLException{
        String getResult = "SELECT "+type+" FROM "+tableName+" WHERE "+type+" = "+indicatorToCheck;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(getResult);
            return resultSet.next();
        }
    }

}
