package ru.croc.school.task7;

import java.util.Arrays;

public class ChessPosition {
    private int x;
    private int y;
    static final String[] columnNames = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public int getColumn(){
        return this.x;
    }
    public int getString(){
        return this.y;
    }

    public void setCoords(int x, int y) throws IllegalPositionException{
        if (x<1 | x>8 | y<1 | y>8){
            throw new IllegalPositionException(x, y);
        }
        this.x = x;
        this.y = y;
    }

    ChessPosition(int x, int y) throws IllegalPositionException{
        if (x<1 | x>8 | y<1 | y>8){
            throw new IllegalPositionException(x, y);
        }
        this.x = x;
        this.y = y;
    }

    static ChessPosition parse(String position) throws IllegalPositionException{
        String[] positions = position.split("(?!^)");
        int firstCoord = Arrays.binarySearch(columnNames, positions[0])+1;
        int secondCoord = Integer.parseInt(positions[1]);
        if (firstCoord < 1 | firstCoord > 8 | secondCoord < 1 | secondCoord > 8) {
            throw new IllegalPositionException(firstCoord, secondCoord);
        }
        return new ChessPosition(firstCoord, secondCoord);
    }

    @Override
    public String toString(){
        return columnNames[(x - 1)] + Integer.toString(y);
    }

    public static String checkHorse(ChessPosition[] steps) throws IllegalMoveException {
        for (int i = 1; i < steps.length; i++){
            //в условии проверяю, что сумма модулей разности координат двух точек на доске равны или 2,
            // или 1 (по отдельности), но вместе суммарно 3
            if ((Math.abs(steps[i-1].getColumn() - steps[i].getColumn()) == 1 &&
                    Math.abs(steps[i-1].getString() - steps[i].getString()) == 2) |
                    (Math.abs(steps[i-1].getColumn() - steps[i].getColumn()) == 2 &&
                            Math.abs(steps[i-1].getString() - steps[i].getString()) == 1)) {
                continue;
            }
            else {
                throw new IllegalMoveException(steps[i-1].toString(), steps[i].toString());
            }}
            return "OK";
        }
    }


class IllegalPositionException extends Exception {
    private final int x;
    private final int y;

    public IllegalPositionException(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String getMessage(){
        return "Such position does not exist. Coordinates' values must be in the interval [1,8].";
    }


}

class IllegalMoveException extends Exception{
    private String pos1;
    private String pos2;

    public IllegalMoveException(String pos1, String pos2){
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    @Override
    public String getMessage(){
        return "конь так не ходит: "+pos1+" -> "+pos2;
    }

}
