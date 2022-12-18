package ru.croc.school.project;

public class Word {

    private String filePath;
    private String word;
    private Level level;

    public Word(String filePath, String word, String level){
        this.filePath = filePath;
        this.word = word;
        this.level = Level.valueOf(level);
    }

    public String getFilePath() {
        return filePath;
    }

    public String getWord(){
        return word;
    }

    public String getLevel() {
        return level.name();
    }
}
