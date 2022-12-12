package ru.croc.school.task19;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Delivery {

    private static String JDBC_DRIVER = "org.h2.Driver";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        //создаю БД с доставкой
        Class.forName(JDBC_DRIVER);
        CreateDatabaseWithDelivery database = new CreateDatabaseWithDelivery("jdbc:h2:mem:~/test", "sa", "");
        database.createTables();

        //наполняю ее некоторыми данными
        database.insertData("1", "olga", "T4", "Computer", "70000", "30", "1");
        database.insertData("1", "olga", "T7", "Smart Watch", "50000", "30", "1");
        database.insertData("2", "vasya", "T1", "Mouse", "3000", "10", "2");
        database.insertData("3", "petya", "T3", "Earphones", "15000", "40", "2");
        database.insertData("3", "petya", "T7", "Smart Watch", "50000", "40", "2");
        database.insertData("4", "dasha", "T3", "Earphones", "15000", "15", "1");
        database.insertData("4", "dasha", "T7", "Computer", "70000", "15", "1");
        database.insertData("5", "dima", "T1", "Mouse", "3000", "65", "2");

        database.insertNewEmployer("1", "Pavel", "Pavlov");
        database.insertNewEmployer("2", "Ekaterina", "Stepanova");

        //сами запросы
        System.out.println(new GetDeliveryData(database).getOrdersDeliveryTime(1));
        System.out.println(new GetDeliveryData(database).getOrdersToDeliver(2));
    }

}
