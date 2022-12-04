package ru.croc.school.task15;

import java.util.*;

public class AgeGroups {

    private List<Integer> groups;
    private static TreeMap<AgeBounders<Integer, Integer>, Set<Respondent>> respondentsData = new TreeMap<>(Collections.reverseOrder());


    //создание мапы с группой
    public static void mapCreate(TreeMap<AgeBounders<Integer, Integer>, Set<Respondent>> respondentsData, String[] groups){
        int left = 0;
        int right;
        for (String group:groups){
            right = Integer.parseInt(group);
            respondentsData.put(new AgeBounders<>(left, right), new TreeSet<>());
            left = right + 1;
        }
        respondentsData.put(new AgeBounders<>(left, null), new TreeSet<>());
    }

    //вывод мапы
    public static void printMap(TreeMap<AgeBounders<Integer, Integer>, Set<Respondent>> respondentsData){
        for (Map.Entry keyValue:respondentsData.entrySet()){
            if (!((TreeSet<Respondent>) keyValue.getValue()).isEmpty()){
                System.out.println((AgeBounders)keyValue.getKey());
                Iterator<Respondent> takePeople = ((TreeSet<Respondent>) keyValue.getValue()).iterator();
                while (takePeople.hasNext()){
                    Respondent respondent = takePeople.next();
                    System.out.println(respondent);
                    if (takePeople.hasNext()){
                        System.out.println(",");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args){
        TreeMap<AgeBounders<Integer, Integer>, Set<Respondent>> respondentsData = new TreeMap<>(Collections.reverseOrder());
        AgeGroups ageGroups = new AgeGroups();
        mapCreate(respondentsData, args);
        Scanner reader = new Scanner(System.in);
        String readData = reader.nextLine();
        List <String> names = new ArrayList<>();
        while (!readData.equals("END")){
            String[] tempVar = readData.split(", ");
            Integer respAge = Integer.parseInt(tempVar[1]);
            Respondent resp = new Respondent(tempVar[0], respAge);
            AgeBounders<Integer, Integer> respKey = respondentsData.ceilingKey(new AgeBounders<>(respAge, respAge));
            respondentsData.get(respKey).add(resp);
            readData = reader.nextLine();}
        printMap(respondentsData);
    }
}
