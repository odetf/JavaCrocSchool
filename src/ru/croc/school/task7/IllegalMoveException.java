package ru.croc.school.task7;

public class IllegalMoveException extends Exception{
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
