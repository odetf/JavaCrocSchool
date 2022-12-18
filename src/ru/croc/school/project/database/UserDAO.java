package ru.croc.school.project.database;

import ru.croc.school.project.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection){
        this.connection = connection;
    }

    public void insertUser(User user){
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("INSERT INTO users (user_name) VALUES('"+user.getUser_name()+"');");
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public int findUserID(User user){
        String userIDSelect = "SELECT id FROM users WHERE user_name = '"+user.getUser_name()+"';";
        int q = 0;
        try(Statement statement = connection.createStatement()){
            try(ResultSet rs = statement.executeQuery(userIDSelect)){
                while (rs.next()){
                    q = rs.getInt("id");
                }
                return q;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return q;
    }
}
