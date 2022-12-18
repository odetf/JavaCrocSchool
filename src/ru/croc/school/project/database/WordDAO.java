package ru.croc.school.project.database;

import ru.croc.school.project.Level;
import ru.croc.school.project.NoSuchLevelException;
import ru.croc.school.project.Word;

import java.sql.*;
import java.util.ArrayList;

public class WordDAO {

    private Connection connection;
    public WordDAO(Connection connection){
        this.connection = connection;
    }

    public void insertWord(Word word){
        String insertWord = "INSERT INTO words (file_path, word, level) " +
                "SELECT '"+ word.getFilePath() +"', '"+word.getWord()+"', id FROM levels WHERE level_name = '"+word.getLevel()+"';";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(insertWord);
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public ArrayList <Word> getWords(String level) throws NoSuchLevelException {
        Level chosenLevel = Level.fromString(level);
        ArrayList<Word> words = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery("SELECT file_path, word FROM words WHERE level = '"+chosenLevel.getLevelName()+"';")){
                while (rs.next()){
                    words.add(new Word(rs.getString("file_path"), rs.getString("word"), chosenLevel.getLevelName()));
                }
                return words;
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }
}
