package ru.croc.school.task13;

//класс для чтения данных и их "подготовки" - превращения в нужные форматы

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

    //конструктор для получения данных из файла
    public DataPreparation(String filmsFilePath, String watchesFilePath) {
        filmsFile = Paths.get(filmsFilePath);
        watchesFile = Paths.get(watchesFilePath);
    }

    //метод для чтения файла и создания мапы существующих фильмов
    public HashMap<Integer, String> getFilms(FilmsData filmsData) {
        HashMap<Integer, String> films = new HashMap<>();
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
        return films;
    }

    //метод для чтения файла с пользователями и создания списка их просмотров
    public List<List<Integer>> getWatches(FilmsData filmsData) {
        List<List<Integer>> filmsWatched = new ArrayList<>();
        try {
            List<String> watchesReader = Files.readAllLines(watchesFile);
            for (String watch : watchesReader) {
                List<String> watchList = Arrays.asList(watch.split(","));
                filmsWatched.add(watchList.stream().map(Integer::parseInt).toList());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return filmsWatched;
    }
}
