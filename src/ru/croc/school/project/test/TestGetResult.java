package ru.croc.school.project.test;

import ru.croc.school.project.TestResult;
import ru.croc.school.project.User;
import ru.croc.school.project.database.TestResultDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TestGetResult {

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, SQLException {

        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        User user = new User("Olga");

        TestResultDAO testResultDAO = new TestResultDAO(connection);
        List<TestResult> testResults = testResultDAO.getAllUserResults(user);
        for (TestResult i:testResults){
        System.out.println(i);
        }
    }

}
