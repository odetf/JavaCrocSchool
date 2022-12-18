package ru.croc.school.task16;

public enum TaxiType {

    ECONOM("Эконом"),
    COMFORT("Комфорт"),
    COMFORTPLUS("Комфорт+"),
    BUSINESS("Бизнес");

    private String typeName;

    TaxiType(String typeName){
        this.typeName = typeName;
    }

    public static TaxiType fromString(String name){
        for (TaxiType type:TaxiType.values()){
            if (type.typeName.equalsIgnoreCase(name)){
                return type;
            }
        }
        return null;
    }

    public String getTypeName() {
        return typeName;
    }
}
