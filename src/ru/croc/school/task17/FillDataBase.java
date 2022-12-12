package ru.croc.school.task17;

import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class FillDataBase {
    private static String JDBC_DRIVER = "java.sql.Driver";

    public static void main(String[] args) throws ClassNotFoundException{

        try{
        Class.forName(JDBC_DRIVER);
        StoreData storeData = new StoreData(Paths.get(args[0]));
        storeData.normalize();

        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        CreateDatabase database = new CreateDatabase(connection);
        database.createTables();

        for (List<String> order: storeData.getOrdersData()){
            database.insertData(order.get(0), order.get(1), order.get(2), order.get(3), order.get(4));
        }

        System.out.println(database.checkCreation());}

        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
