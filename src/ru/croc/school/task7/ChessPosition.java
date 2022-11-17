package ru.croc.school.task7;

import java.util.Arrays;

public class ChessPosition {
    private int x;
    private int y;
    static final String[] COLUMN_NAMES = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public int getColumn() {
        return this.x;
    }

    public int getString() {
        return this.y;
    }

    public void setCoords(int x, int y) throws IllegalPositionException {
        if (x < 1 | x > 8 | y < 1 | y > 8) {
            throw new IllegalPositionException(x, y);
        }
        this.x = x;
        this.y = y;
    }

    ChessPosition(int x, int y) throws IllegalPositionException {
        if (x < 1 | x > 8 | y < 1 | y > 8) {
            throw new IllegalPositionException(x, y);
        }
        this.x = x;
        this.y = y;
    }

    static ChessPosition parse(String position) throws IllegalPositionException {
        String[] positions = position.split("(?!^)");
        int firstCoord = Arrays.binarySearch(COLUMN_NAMES, positions[0]) + 1;
        int secondCoord = Integer.parseInt(positions[1]);
        return new ChessPosition(firstCoord, secondCoord);
    }

    @Override
    public String toString() {
        return COLUMN_NAMES[(x - 1)] + Integer.toString(y);
    }

    public static String isHorseOk(ChessPosition[] steps) throws IllegalMoveException {
        for (int i = 1; i < steps.length; i++) {
            try {
                CheckChessPosition.checkHorse(steps[i - 1], steps[i]);
            } catch (IllegalMoveException incorrectMove) {
                return "конь так не ходит: " + steps[i - 1].toString() + " -> " + steps[i].toString();
            }
        }
        return "OK";
    }
}


