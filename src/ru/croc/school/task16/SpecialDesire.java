package ru.croc.school.task16;

public enum SpecialDesire {

    CHILDCHAIR("Детское кресло"),
    BIGTRUNK("Большой багажник"),
    WITHANIMAL("Животное с собой"),
    NODESIRE("");

    private String desireText;

    SpecialDesire(String desireText){
        this.desireText = desireText;
    }

    public static SpecialDesire fromString(String name){
        for (SpecialDesire desire:SpecialDesire.values()){
            if (desire.desireText.equalsIgnoreCase(name)){
                return desire;
            }
        }
        return null;
    }

}
