package ru.croc.school.project;

public class NoSuchLevelException extends Exception{

    private final String levelName;

    public NoSuchLevelException(String levelName){
        this.levelName = levelName;
    }

    @Override
    public String getMessage(){
        return "Such level doesn't exist. Please, write one of the following ones: A1, A2, B1, B2, C1, C2.";
    }

}
