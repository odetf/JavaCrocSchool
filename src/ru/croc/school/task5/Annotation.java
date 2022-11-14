package ru.croc.school.task5;

public class Annotation {
    private String description;
    private Figure figure;

    public Annotation(String description, Figure figure){
        this.description = description;
        this.figure = figure;
    }

    @Override
    public String toString(){
        return figure.toString() + " " + description;
    }

    public Figure getFigure(){return figure;}
    public String getDescription(){
        return description;
    }

}