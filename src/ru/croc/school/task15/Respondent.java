package ru.croc.school.task15;

public class Respondent implements Comparable{

    private String fio;
    private Integer age;

    public Respondent(String fio, Integer age){
        this.fio = fio;
        this.age = age;
    }

    @Override
    public String toString(){
        return fio + " (" + age.toString() + ")";
    }

    @Override
    public int compareTo(Object o){
        if (this.age == ((Respondent) o).age){
            return this.fio.compareTo(((Respondent) o).fio);
        }
        else{
            return this.age.compareTo(((Respondent) o).age);
        }
    }
}
