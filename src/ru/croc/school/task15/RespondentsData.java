package ru.croc.school.task15;

import java.util.*;

public class RespondentsData {

    private List<Integer> groups;
    private TreeMap<AgeBounders<Integer, Integer>, Set<Respondent>> respondentsData;

    public RespondentsData(){
        this.respondentsData = new TreeMap<>();
    }

    public TreeMap<AgeBounders<Integer, Integer>, Set<Respondent>> getRespondentsData() {
        return respondentsData;
    }

    //создание мапы с группой
    public void mapCreate(String[] groups){
        int left = 0;
        int right;
        for (String group:groups){
            right = Integer.parseInt(group);
            respondentsData.put(new AgeBounders<>(left, right), new TreeSet<>());
            left = right + 1;
        }
        respondentsData.put(new AgeBounders<>(left, null), new TreeSet<>());
    }


    public void printMap (){
        respondentsData.forEach((key, value) -> {
            if (!value.isEmpty()) {
                System.out.println(key);
                Iterator<Respondent> takePeople = value.iterator();
                while (takePeople.hasNext()) {
                    Respondent respondent = takePeople.next();
                    System.out.println(respondent);
                    if (takePeople.hasNext()) {
                        System.out.println(",");
                    }
                }
                System.out.println();
            }
        });
    }



}
