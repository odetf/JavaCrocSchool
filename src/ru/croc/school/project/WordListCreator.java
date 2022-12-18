package ru.croc.school.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordListCreator {

    private List<Word> words;
    private final int maxBorder;

    public WordListCreator(List<Word> words) {
        this.words = words;
        this.maxBorder = words.size();
    }

    public ArrayList<Word> generateWordList(int numOfQuestions) throws TooBigTest{
        if (numOfQuestions <= maxBorder){
            ArrayList<Word> wordsForTest = new ArrayList<>();
            for (int i = 0; i < numOfQuestions; i++){
                int num = this.randNum();
                wordsForTest.add(words.get(num));
            }
            return wordsForTest;
        }
        else throw new TooBigTest(maxBorder);
    }

    public int randNum(){
        Random random = new Random();
        return random.nextInt(maxBorder);
    }
}
