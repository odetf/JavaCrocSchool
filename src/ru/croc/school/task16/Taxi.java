package ru.croc.school.task16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Taxi{

    private String id;
    private Double coord1;
    private Double coord2;
    private String taxiType;
    private List<String> info;

    public Taxi(String id, Double coord1, Double coord2, String taxiType) {
        this.id = id;
        this.coord1 = coord1;
        this.coord2 = coord2;
        this.taxiType = taxiType;
        this.info = new ArrayList<>();
    }

    public List<String> getInfo() {
        return info;
    }

    //в задании не нужно, но потенциально может пригодиться
    public void addInfo(String newInfo) {
        info.add(newInfo);
    }

    public Double getCoord1() {
        return coord1;
    }

    public Double getCoord2() {
        return coord2;
    }

    public String getTaxiType() {
        return taxiType;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return id;
    }

    public boolean comparateTypeInfo(String type, String information){
        return taxiType.equals(type) & info.contains(information);
    }


}
