package ru.croc.school.task4;

public class Annot {
    protected String description;
    private Figure figure;

    public Annot(String description, Figure figure){
            this.description = description;
            this.figure = figure;
        }

    @Override
    public String toString(){
        return figure.toString() + " " + description;
    }

    public String getDescription() {
        return description;
    }
}

