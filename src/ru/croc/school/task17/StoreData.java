package ru.croc.school.task17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreData {

    private List<List<String>> ordersData;
    private Path filepath;

    public StoreData (Path path){
        this.filepath = path;
        this.ordersData = new ArrayList<>();
    }

    public void normalize(){
        try{
            List <String> ordersReader = Files.readAllLines(filepath);
            for (String order:ordersReader){
                ordersData.add(Arrays.stream(order.split(",")).toList());
            }
        }
        catch(IOException ioException){

        }
    }

    public List<List<String>> getOrdersData() {
        return ordersData;
    }
}
