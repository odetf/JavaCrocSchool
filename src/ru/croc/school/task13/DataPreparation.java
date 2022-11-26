package ru.croc.school.task13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataPreparation {
    private final Path filmsFile;
    private final Path watchesFile;

    public HashMap<Integer, String> films = new HashMap<>();
    public List<List<Integer>> filmsWatched = new ArrayList<>();

    //конструктор для получения данных из файла
    public DataPreparation(String filmsFilePath, String watchesFilePath) {
        filmsFile = Paths.get(filmsFilePath);
        watchesFile = Paths.get(watchesFilePath);
        this.getFilms();
        this.getWatches();
    }

    //создаем в объекте список фильмов
    private void getFilms() {
        try {
            List<String> filmReader = Files.readAllLines(filmsFile);
            for (String film : filmReader) {
                Integer filmPlace = Integer.parseInt(film.substring(0, film.indexOf(",")));
                String filmName = film.substring(2);
                films.put(filmPlace, filmName);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //создаем список "просмотров" разными пользователями
    private void getWatches() {
        try {
            List<String> watchesReader = Files.readAllLines(watchesFile);
            for (String watch : watchesReader) {
                List<String> watchList = Arrays.asList(watch.split(","));
                filmsWatched.add(watchList.stream().map(Integer::parseInt).toList());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
