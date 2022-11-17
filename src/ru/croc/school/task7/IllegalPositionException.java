package ru.croc.school.task7;

public class IllegalPositionException extends Exception {
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
