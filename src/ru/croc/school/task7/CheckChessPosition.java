package ru.croc.school.task7;

public class CheckChessPosition {
    public static boolean checkHorse (ChessPosition coord1, ChessPosition coord2) throws IllegalMoveException{
        //проверяю, что сумма модулей разности координат двух точек на доске равны или 2,
        // или 1 (по отдельности), но вместе суммарно 3
        if ((Math.abs(coord1.getColumn() - coord2.getColumn()) == 1 &&
                Math.abs(coord1.getString() - coord2.getString()) == 2) |
                (Math.abs(coord1.getColumn() - coord2.getColumn()) == 2 &&
                        Math.abs(coord1.getString() - coord2.getString()) == 1)){
            return true;
        }
        else{
            throw new IllegalMoveException (coord1.toString(), coord2.toString());
        }
    }
}
