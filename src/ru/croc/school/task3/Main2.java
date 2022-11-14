package ru.croc.school.task3;

public class Main2 {
    static class Point{
        double x;
        double y;
    }
    public static void main(String[] args){
        Point a = new Point();
        a.x = Double.parseDouble(args[0]);
        a.y = Double.parseDouble(args[1]);

        Point b = new Point();
        b.x = Double.parseDouble(args[2]);
        b.y = Double.parseDouble(args[3]);

        Point c = new Point();
        c.x = Double.parseDouble(args[4]);
        c.y = Double.parseDouble(args[5]);

        double ab = Math.sqrt(Math.pow((a.x -  b.x), 2) + Math.pow((a.y - b.y), 2));
        double bc = Math.sqrt(Math.pow((b.x -  c.x), 2) + Math.pow((b.y - c.y), 2));
        double ac = Math.sqrt(Math.pow((a.x -  c.x), 2) + Math.pow((a.y - c.y), 2));

        double p = (ab + bc + ac) / 2;
        double s = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));

        System.out.println(s);
    }
}
