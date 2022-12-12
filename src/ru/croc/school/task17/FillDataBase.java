package ru.croc.school.task17;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class FillDataBase {
    private static String JDBC_DRIVER = "org.h2.Driver";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName(JDBC_DRIVER);
        StoreData storeData = new StoreData(Paths.get(args[0]));
        storeData.normalize();

        CreateDatabase database = new CreateDatabase("jdbc:h2:~/test", "sa", "");
        database.createTables();

        for (List<String> order: storeData.getOrdersData()){
            database.insertData(order.get(0), order.get(1), order.get(2), order.get(3), order.get(4));
        }
    }
}
