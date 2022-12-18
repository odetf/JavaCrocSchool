package ru.croc.school.task16;

import java.util.HashSet;
import java.util.Set;

public class Taxi {

    private String id;
    private Double coord1;
    private Double coord2;
    private TaxiType taxiType;
    private Set<SpecialDesire> info;

    public Taxi(String id, Double coord1, Double coord2, String taxiType) {
        this.id = id;
        this.coord1 = coord1;
        this.coord2 = coord2;
        this.taxiType = TaxiType.fromString(taxiType);
        this.info = new HashSet<>();
        this.info.add(SpecialDesire.NODESIRE);
    }

    //конструктор для формирования "идеального такси"
    public Taxi() {
        this.info = new HashSet<>();
    }

    public Set<SpecialDesire> getInfo() {
        return info;
    }

    //в задании не нужно, но потенциально может пригодиться
    public void addInfo(String newInfo) {
        info.add(SpecialDesire.fromString(newInfo));
    }

    public Double getCoord1() {
        return coord1;
    }

    public Double getCoord2() {
        return coord2;
    }

    public TaxiType getTaxiType() {
        return taxiType;
    }

    public void setCoord1(Double coord1) {
        this.coord1 = coord1;
    }

    public void setCoord2(Double coord2) {
        this.coord2 = coord2;
    }


    public void setTaxiType(String taxiType) {
        this.taxiType = TaxiType.fromString(taxiType);
    }


    @Override
    public String toString() {
        return id;
    }

    public boolean comparateTypeInfo(Taxi taxi) {
        return taxiType.equals(taxi.getTaxiType()) & info.containsAll(taxi.getInfo());
    }


}
