package ru.croc.school.project.test;

import ru.croc.school.project.User;
import ru.croc.school.project.Word;
import ru.croc.school.project.database.DatabaseCreation;
import ru.croc.school.project.database.UserDAO;
import ru.croc.school.project.database.WordDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabase {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

            //создаю базу данных
            DatabaseCreation database = new DatabaseCreation(connection);
            database.createTables();

            //заполняю таблицу с уровнями, т.к. там всегда одни и те же значения
            database.fulfillLevels();

            //заполняю таблицу со словами
            WordDAO wordDAO = new WordDAO(connection);
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/ru/croc/school/project/data/data for download"));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] wordParts = line.split(", ");
                Word word = new Word(wordParts[0], wordParts[1], wordParts[2]);
                wordDAO.insertWord(word);}

            //создаю юзеров
            User user1 = new User("Olga");
            User user2 = new User ("Petya");
            User user3 = new User("Vasya");
            User user4 = new User("Dasha");

            UserDAO userDAO = new UserDAO(connection);
            userDAO.insertUser(user1);
            userDAO.insertUser(user2);
            userDAO.insertUser(user3);
            userDAO.insertUser(user4);

            //вывод результатов

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }}
