package ru.croc.school.task16;

public class NoSuchTaxiException extends Exception{

    public NoSuchTaxiException(){
    }

    @Override
    public String getMessage(){
        return "Среди доступных такси нет такси одновременно соответствующего введенным типу и специальным пожеланиям";
    }
}
