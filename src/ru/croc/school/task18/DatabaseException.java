package ru.croc.school.task18;

public class DatabaseException extends Exception {

    private String id;

    public DatabaseException(String id){
        this.id = id;
    }
    @Override
    public String getMessage(){
        return "The record with id "+ id + " already exists.";
    }
}
