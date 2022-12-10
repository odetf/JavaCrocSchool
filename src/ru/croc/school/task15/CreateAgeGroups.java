package ru.croc.school.task15;

import java.util.*;

public class CreateAgeGroups {

    public static void main(String[] args){

        RespondentsData respondentsData = new RespondentsData();
        respondentsData.mapCreate(args);
        Scanner reader = new Scanner(System.in);
        String readData = reader.nextLine();
        while (!readData.equals("END")){
            String[] tempVar = readData.split(", ");
            Integer respAge = Integer.parseInt(tempVar[1]);
            Respondent resp = new Respondent(tempVar[0], respAge);
            AgeBounders<Integer, Integer> respKey = respondentsData.
                    getRespondentsData().floorKey(new AgeBounders<>(respAge, respAge));
            respondentsData.getRespondentsData().get(respKey).add(resp);
            readData = reader.nextLine();}
        respondentsData.printMap();
    }
}
