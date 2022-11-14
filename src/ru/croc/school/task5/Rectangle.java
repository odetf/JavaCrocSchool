package ru.croc.school.task5;

class Rectangle extends Figure{
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

    void setCoords(int x, int y, int place){
        if (place == 1){
            this.x1 = x; this.y1 = y;
        } else {
            this.x2 = x; this.y2 = y;
        }
    }

    @Override
    public void move(int dx, int dy){
        setCoords(x1+dx, y1+dy, 1);
        setCoords(x2+dx, y2+dy, 2);
    }
    @Override
    public String toString(){
        return "Rectangle ("+ this.getX1()+", "+ this.getY1()+"), ("
                + this.getX2()+", "+ this.getY2()+"):";
    }
    @Override
    boolean checkPoint(int x, int y){
        return ((this.getX1()<= x)&(this.getX2()>=x)&(this.getY1()<=y)&(this.getY2()>=y));
    }
}
