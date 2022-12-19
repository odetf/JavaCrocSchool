package ru.croc.school.task18;

import java.sql.*;

public class ProductDAO {

    private final Connection connection;

    public ProductDAO(Connection connection){
        this.connection = connection;
    }

    public Product findProduct(String article) throws SQLException{
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery("SELECT * FROM product WHERE article = '"+article+"'");
            if (result.next()){
                return  new Product(result.getString("article"),
                        result.getString("name"), result.getInt("price"));
            }
            else {
                return null;
            }
        }
    }



    public Product createProduct(Product product) {
            try (Statement statement = connection.createStatement()){
                statement.execute("INSERT INTO product (article, name, price) VALUES ('" + product.getArticle() + "' ,'"+
                        product.getName() + "' ,'"+ product.getPrice() + "';");
                return returnProduct(product.getArticle());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    public Product updateProduct(Product product){
        try(Statement statement = connection.createStatement()) {
            statement.execute("UPDATE product " +
                    "SET name = '" + product.getName()+"', price = '" + product.getPrice()+"'" +
                    "WHERE article = '" + product.getArticle()+ "';");
            return returnProduct(product.getArticle());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(Product product){
        try (Statement statement = connection.createStatement()){
            statement.execute("DELETE FROM order WHERE article = '"+ product.getArticle()+"';");
            statement.execute("DELETE FROM product WHERE article = '"+product.getArticle()+"';");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Product returnProduct(String article) throws SQLException{
        try (Statement statement = connection.createStatement()){
            Product product1 = null;
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE article = '"+article+"'")){
                while (resultSet.next()){
                    product1 = new Product(resultSet.getString("article"), resultSet.getString("name"), resultSet.getInt("price"));
                }
            }
            return product1;
        }
    }
}
