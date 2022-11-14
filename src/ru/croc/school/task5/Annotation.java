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

    public String getDescription(){
        return description;
    }

    Annotation findByPoint(Annotation annot[], int x, int y, Annotation annotWithPoint){
        for (Annotation a:annot){
            if (a.figure.checkPoint(x, y) == true){
                annotWithPoint = a;
                break;
            }
        }
        return annotWithPoint;
    }

    Annotation findByLabel(Annotation[] annots, String label, Annotation annotation){
        for (Annotation annot:annots){
            if (annot.description.contains(label)){
                annotation = annot;
                break;
            }
        }
        return annotation;
    }
    public interface Movable {
        void move(int dx, int dy); //"place" shows a place of the coordinate to change
    }
    abstract class Figure implements Movable {
        boolean checkPoint(int x, int y){return true;};
    }

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

        public void setCoord(int x1, int y1){
            this.x1 = x1;
            this.y1 = y1;
        }

        @Override
        public void move(int dx, int dy){
            setCoord(x1+dx, y1+dx);
        }

        @Override
        public String toString(){
            return "Circle ("+ this.getX1()+", "+ this.getY1()+"), "+ this.getRadius();
        }
        @Override
        boolean checkPoint (int x, int y){
            if ((this.getX1()-this.getRadius() <= x | this.getX1()-this.getRadius() >= x)
                    & (this.getY1()- this.getRadius() <= y) | (this.getY1()- this.getRadius() >= y)){
                return true;
            }
            else {return false;}
        }
    }

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
            if ((this.getX1()<= x)&(this.getX2()>=x)&(this.getY1()<=y)&(this.getY2()>=y)){
                return true;
            }
            else {return false;}
        }
    }}