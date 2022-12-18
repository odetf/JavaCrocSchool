package ru.croc.school.project;

public class TooBigTest extends Exception{

    private int possibleTestSize;

    public TooBigTest(int possibleTestSize){
        this.possibleTestSize = possibleTestSize;
    }

    @Override
    public String getMessage(){
        return "Sorry, we cannot generate test of such length. Maximum possible test size - "+possibleTestSize;
    }
}
