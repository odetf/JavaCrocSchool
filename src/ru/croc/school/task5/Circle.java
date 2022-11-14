package ru.croc.school.task5;

class Circle extends Figure{
    private int x1;
    private int y1;
    private int radius;

    public Circle(int x1, int y1, int radius){
        this.x1 = x1;
        this.y1 = y1;
        this.radius = radius;
    }
    public int getX1(){
        return this.x1;
    }
    public int getY1(){
        return this.y1;
    }
    public int getRadius(){
        return this.radius;
    }

    public void setCoords(int x1, int y1){
        this.x1 = x1;
        this.y1 = y1;
    }


    @Override
    public void move(int dx, int dy){
        setCoords(x1+dx, y1+dy);
    }

    @Override
    public String toString(){
        return "Circle ("+ this.getX1()+", "+ this.getY1()+"), "+ this.getRadius();
    }
    @Override
    boolean checkPoint (int x, int y){
        return ((this.getX1()-this.getRadius() <= x | this.getX1()+this.getRadius() >= x)
                & (this.getY1()- this.getRadius() <= y) | (this.getY1()+this.getRadius() >= y));
    }
}
