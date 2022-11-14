package ru.croc.school.task4;

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
    }}