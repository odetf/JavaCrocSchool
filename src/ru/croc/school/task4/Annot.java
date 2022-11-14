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

    class Figure {
}

public class Circle extends Figure {
    private int x1;
    private int y1;
    private int radius;

    public Circle(int x1, int y1, int radius){
        this.x1 = x1;
        this.y1 = y1;
        this.radius = radius;
    }
    public int getX1(){
        return x1;
    }
    public int getY1(){
        return y1;
    }
    public int getRadius(){
        return radius;
    }

    @Override
    public String toString(){
        return "Circle ("+Circle.this.getX1()+", "+Circle.this.getY1()+"), "+Circle.this.getRadius();
    }
}

public class Rectangle extends Figure{
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1(){
        return this.x1;
    }
    public int getY1(){
        return this.y1;
    }
    public int getX2(){
        return this.x2;
    }
    public int getY2(){
        return this.y2;
    }

    @Override
    public String toString(){
        return "Rectangle ("+this.getX1()+", "+this.getY1()+"), ("+this.getX2()+", "+this.getY2()+"):";
}}}
