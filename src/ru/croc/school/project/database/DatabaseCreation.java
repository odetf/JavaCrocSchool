package ru.croc.school.project.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreation {

    private Connection connection;

    public DatabaseCreation(Connection connection){
        this.connection = connection;
    }

    public void createTables(){
        try (Statement statement = connection.createStatement()){

            String createLevels = "CREATE TABLE levels(" +
                    "id VARCHAR PRIMARY KEY," +
                    "level_name VARCHAR);";

            String createTables = "CREATE TABLE users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "user_name VARCHAR);";
            String createWords = "CREATE TABLE words (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "file_path VARCHAR," +
                    "word VARCHAR," +
                    "level VARCHAR REFERENCES levels(id));";

            String createTestResults = "CREATE TABLE results(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "test_completion_date DATETIME," +
                    "level VARCHAR REFERENCES levels(id)," +
                    "num_of_correct DOUBLE," +
                    "user_id INT REFERENCES users(id));";

            statement.execute(createLevels);
            statement.execute(createTables);
            statement.execute(createWords);
            statement.execute(createTestResults);
        }
        catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }

    public void fulfillLevels(){
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("INSERT INTO levels (id, level_name) VALUES ('A1', 'A1'), ('A2', 'A2'), " +
                    "('B1', 'B1'), ('B2', 'B2'), ('C1', 'C1'), ('C2', 'C2')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
