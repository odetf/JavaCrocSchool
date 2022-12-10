package ru.croc.school.task15;

public class Respondent implements Comparable<Respondent>{

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
    public int compareTo(Respondent r){
        if (this.age == r.age){
            return this.fio.compareTo(r.fio);
        }
        else{
            return this.age.compareTo(r.age);
        }
    }
}
