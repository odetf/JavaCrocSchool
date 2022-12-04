package ru.croc.school.task13;

//класс для хранения данных по фильмам

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilmsData {


    private HashMap<Integer, String> films = new HashMap<>();
    private List<List<Integer>> filmsWatched = new ArrayList<>();

    public FilmsData(DataPreparation dataPrep){
        films = dataPrep.getFilms(this);
        filmsWatched = dataPrep.getWatches(this);
    }

    public List<List<Integer>> getFilmsWatched() {
        return filmsWatched;
    }

    public HashMap<Integer, String> getFilms() {
        return films;
    }
}
