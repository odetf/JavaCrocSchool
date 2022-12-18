package ru.croc.school.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class AudioTest {

    private List<Word> words;
    private Scanner in;
    private int numOfCorrect = 0;

    public AudioTest(List<Word> words){
        this.words = words;
        this.in = new Scanner(System.in);
    }

    public void doTest(){
        for (Word word:words){
            WordPlayer wordPlayer = new WordPlayer(word.getFilePath());
            wordPlayer.playWord();
            String answer = in.nextLine();
            while (true){
                if (answer.equals("repeat")){
                    wordPlayer.restartWord();
                    answer = in.nextLine();
                }
                else {
                    wordPlayer.stopAndClose();
                    break;
                }
            }
            if (answer.equalsIgnoreCase(word.getWord())){
                numOfCorrect += 1;
                System.out.println("Correct!");
            }
            else {
                System.out.println("Your answer is wrong! Correct answer - '"+ word.getWord()+"'");
            }
        }
    }

    public float calculateResult(){
        return ((float)numOfCorrect / words.size()) * 100;
    }

    public TestResult generateFinalResults(User user) throws NoSuchLevelException {
        BigDecimal percentageOfCorrect = BigDecimal.valueOf(this.calculateResult());
        return new TestResult(LocalDateTime.now(),
                words.get(0).getLevel(), percentageOfCorrect, user);
    }

}
