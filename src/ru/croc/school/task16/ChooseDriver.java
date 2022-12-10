package ru.croc.school.task16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChooseDriver {

    private static List<Taxi> taxiBase;

    public static void main(String[] args){
        Taxi t1 = new Taxi("12345", 100.01747, 67.99203, "Комфорт");
        t1.addInfo("Детское кресло");
        t1.addInfo("Большой багажник");
        Taxi t2 = new Taxi("34567",100.98, 68.03, "Эконом");
        t2.addInfo("Детское кресло");
        Taxi t3 = new Taxi("87654",101.2390, 68.541, "Комфорт");
        t3.addInfo("");
        Taxi t4 = new Taxi("09876",100.01747, 67.123408, "Комфорт");
        t4.addInfo("Детское кресло");
        Taxi t5 = new Taxi("13579", 102.987, 65.3333, "Комфорт+");
        t5.addInfo("");
        Taxi t6 = new Taxi("45310", 99.99999, 66.3332, "Эконом");
        t6.addInfo("Большой багажник");


        taxiBase = new ArrayList<>();
        //добавляю их в список такси
        taxiBase.add(t1);
        taxiBase.add(t2);
        taxiBase.add(t3);
        taxiBase.add(t4);
        taxiBase.add(t5);
        taxiBase.add(t6);

        Double coord1 = Double.parseDouble(args[0].substring(0, args[0].length() - 1));
        Double coord2 = Double.parseDouble(args[1]);
        String taxiType = args[2];
        String specialDesire = args[3]+" "+args[4];

        taxiBase.sort((p1, p2) ->
                //проверяю, что для p1 и p2 равны типы такси и дополнительные сервисы (типа дет кресла)
                (p1.comparateTypeInfo(taxiType, specialDesire) & p2.comparateTypeInfo(taxiType, specialDesire)) ?

                //если они равны, то выдаю значение для сравнения расстояний для p1 и p2 (умноженное на (-1), чтобы
                // меньшее расстояние считалось за большее
                Double.compare(Math.sqrt(Math.pow(p1.getCoord1() - coord1, 2) + Math.pow(p1.getCoord2() - coord2, 2)),
                        Math.sqrt(Math.pow(p2.getCoord1() - coord1, 2) + Math.pow(p2.getCoord2() - coord2, 2))) :

                p1.comparateTypeInfo(taxiType, specialDesire) ? -1 :
                        p2.comparateTypeInfo(taxiType, specialDesire) ? 1 : 0);


        //получаем такси с наибольшим айдишником
        System.out.println(taxiBase.get(0).getId());

        //можно выводить и топ-3 рекомендации такси, но у меня маленькая база, поэтому оставляю для 1

    }

}
