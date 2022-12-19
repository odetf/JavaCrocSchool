package ru.croc.school.task17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreData {

    private List<Order> ordersData;
    private Path filepath;

    public StoreData (Path path){
        this.filepath = path;
        this.ordersData = new ArrayList<>();
    }

    public void normalize(){
        try{
            List <String> ordersReader = Files.readAllLines(filepath);
            for (String order:ordersReader){
                String[] orderItems = order.split(",");
                User user = new User(orderItems[1]);
                Product product = new Product(orderItems[2], orderItems[3], Integer.parseInt(orderItems[4]));
                Order newOrder = new Order(Integer.parseInt(orderItems[0]), user, product);
                ordersData.add(newOrder);
            }
        }
        catch(IOException ioException){

        }
    }

    public List<Order> getOrdersData() {
        return ordersData;
    }
}
