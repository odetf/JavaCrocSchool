package ru.croc.school.task16;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws NoSuchTaxiException {
        Taxi t1 = new Taxi("12345", 100.01747, 67.99203, "Комфорт");
        t1.addInfo("Детское кресло");
        t1.addInfo("Большой багажник");
        Taxi t2 = new Taxi("34567", 100.98, 68.03, "Эконом");
        t2.addInfo("Детское кресло");
        Taxi t3 = new Taxi("87654", 101.2390, 68.541, "Комфорт");
        Taxi t4 = new Taxi("09876", 100.01747, 67.123408, "Комфорт");
        t4.addInfo("Детское кресло");
        Taxi t5 = new Taxi("13579", 102.987, 65.3333, "Комфорт+");
        Taxi t6 = new Taxi("45310", 99.99999, 66.3332, "Эконом");
        t6.addInfo("Большой багажник");
        t6.addInfo("Детское кресло");
        Taxi t7 = new Taxi("67760", 105.22, 62.3, "Комфорт+");
        t7.addInfo("Животное с собой");


        TaxiChoser taxiChoser = new TaxiChoser();

        //добавляю их в список такси
        taxiChoser.addTaxi(t1);
        taxiChoser.addTaxi(t2);
        taxiChoser.addTaxi(t3);
        taxiChoser.addTaxi(t4);
        taxiChoser.addTaxi(t5);
        taxiChoser.addTaxi(t6);
        taxiChoser.addTaxi(t7);

        Taxi idealTaxi = new Taxi();
        Scanner in = new Scanner(System.in);
        String[] coords = in.nextLine().split(", ");
        idealTaxi.setCoord1(Double.parseDouble(coords[0]));
        idealTaxi.setCoord2(Double.parseDouble(coords[1]));
        idealTaxi.setTaxiType(in.nextLine());
        idealTaxi.addInfo(in.nextLine());
        System.out.println(taxiChoser.chooseDriver(idealTaxi));
    }
}
