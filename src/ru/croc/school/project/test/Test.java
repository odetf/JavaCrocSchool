package ru.croc.school.project.test;

import ru.croc.school.project.*;
import ru.croc.school.project.database.TestResultDAO;
import ru.croc.school.project.database.WordDAO;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException,
            IOException, InterruptedException, NoSuchLevelException, SQLException, ClassNotFoundException, TooBigTest {

        //достаю список слов по условию
        int numOfQuestions = Integer.parseInt(args[0]);
        String levelChosen = args[1];

        //проверяю, что введённый пользователем уровень существует
        Level.levelChecker(levelChosen);

        //достаю из БД список подходящих слов слов
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        WordDAO wordDAO = new WordDAO(connection);
        List<Word> words = wordDAO.getWords(levelChosen);

        //далее генерирую случайным образом список для теста
        WordListCreator wordListCreator = new WordListCreator(words);
        List <Word> wordsForTest = wordListCreator.generateWordList(numOfQuestions);

        //передаю этот список в тест
        AudioTest audioTest = new AudioTest(wordsForTest);
        audioTest.doTest();

        //генерирую результат
        TestResult testResult = audioTest.generateFinalResults(new User("Olga"));

        //вывожу результат пользователю
        System.out.println(testResult);

        //отправляю результат в БД
        TestResultDAO testResultDAO = new TestResultDAO(connection);
        testResultDAO.insertTestResult(testResult);
    }
}
