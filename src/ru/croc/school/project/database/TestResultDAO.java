package ru.croc.school.project.database;

import ru.croc.school.project.NoSuchLevelException;
import ru.croc.school.project.TestResult;
import ru.croc.school.project.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestResultDAO {

    private Connection connection;

    public TestResultDAO(Connection connection){
        this.connection = connection;
    }

    public void insertTestResult(TestResult testResult){
        String insertResult = "INSERT INTO results (test_completion_date, level, num_of_correct, user_id)" +
                "SELECT '"+testResult.getTestCompletionDate()+"', '"+testResult.getLevel()+"', "+
                testResult.getPercentageOfCorrect()+", id FROM users WHERE user_name = '"+testResult.getUser().getUser_name()+"';";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(insertResult);
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public List<TestResult> getAllUserResults(User user) throws SQLException {
        List<TestResult> userResults = new ArrayList<>();
        int user_id = new UserDAO(connection).findUserID(user);
        String userResultsSelect = "SELECT test_completion_date, level, num_of_correct FROM results WHERE user_id = "+user_id;
        try(Statement statement = connection.createStatement()){
            try(ResultSet rs = statement.executeQuery(userResultsSelect)){
                while (rs.next()){
                    String date = rs.getString("test_completion_date").substring(0, 19).replace(" ", "T");
                    userResults.add(new TestResult(LocalDateTime.parse(date),
                            rs.getString("level"), rs.getBigDecimal("num_of_correct"), user));
                }
            } catch (NoSuchLevelException e) {
                System.out.println(e.getMessage());
            }
        }
        return userResults;
    }

}
