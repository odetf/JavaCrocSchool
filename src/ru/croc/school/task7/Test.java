package ru.croc.school.task7;

import ru.croc.school.task7.ChessPosition;
import ru.croc.school.task7.IllegalPositionException;
import ru.croc.school.task7.IllegalMoveException;

public class Test {
    public static void main(String[] args) throws IllegalPositionException, IllegalMoveException {
        //тестирую функции для коня
        ChessPosition[] coords = {ChessPosition.parse("g8"), ChessPosition.parse("e7"), ChessPosition.parse("c8")};
        System.out.println(ChessPosition.checkHorse(coords)); //тут все ок
        ChessPosition[] coordsNo = {ChessPosition.parse("g8"), ChessPosition.parse("e7"), ChessPosition.parse("e6")};
        System.out.println(ChessPosition.checkHorse(coordsNo)); //тут ошибка
    }
}
