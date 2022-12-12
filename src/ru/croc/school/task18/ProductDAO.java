package ru.croc.school.task18;

import java.sql.*;

public class ProductDAO {

    private Connection connection;

    public ProductDAO(Connection connection){
        this.connection = connection;
    }

    public Product findProduct(String productCode) throws SQLException{
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery("SELECT * FROM products WHERE article = '"+productCode+"'");
            if (result.next()){
                return  new Product(result.getString("article"),
                        result.getString("name"), result.getInt("price"));
            }
            else {
                return null;
            }
        }
    }



    public Product createProduct(Product product) throws DatabaseException, SQLException {
        if (findProduct(product.getArticle()) != null){
            try (Statement statement = connection.createStatement()){
                statement.execute("INSERT INTO products (article, name, price) VALUES ('" + product.getArticle() + "' ,'"+
                        product.getName() + "' ,'"+ product.getPrice() + "';");
                return product;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw new DatabaseException(product.getArticle());
        }
    }

    public Product updateProduct(Product product){
        try(Statement statement = connection.createStatement()) {
            statement.execute("UPDATE products " +
                    "SET name = '" + product.getName()+"', price = '" + product.getPrice()+"'" +
                    "WHERE article = '" + product.getArticle()+ "';");
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(Product product){
        try (Statement statement = connection.createStatement()){
            statement.execute("DELETE FROM products WHERE article = '"+product.getArticle()+"';");
            statement.execute("DELETE FROM orders WHERE article = '"+ product.getArticle()+"';");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
