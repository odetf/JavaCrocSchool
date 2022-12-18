package ru.croc.school.project;

import java.util.Arrays;

public enum Level {

    A1("A1"),
    A2("A2"),
    B1("B1"),
    B2("B2"),
    C1("C1"),
    C2("C2");

    private String levelName;
    Level(String levelName){
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }

    public static Level fromString(String name) throws NoSuchLevelException {
        for (Level level:Level.values()){
            if (level.levelName.equalsIgnoreCase(name)){
                return level;
            }
        }
        throw new NoSuchLevelException(name);
    }

    public static void levelChecker(String levelChosen) throws NoSuchLevelException {
        if (!Arrays.stream(Level.values()).anyMatch(level -> level.getLevelName().equalsIgnoreCase(levelChosen))) {
            throw new NoSuchLevelException(levelChosen);
        }
    }

    public static class TooBigTest {


    }
}
