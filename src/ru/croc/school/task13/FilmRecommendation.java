package ru.croc.school.task13;

import java.util.*;

public class FilmRecommendation {

    private List<Integer> clientViews; //список с фильмами, которые пользователь просмотрел
    private Set<Integer> filmsToWatch; //множество непросмотренных и рекомендуемых
    private String bestFilmName; //"наилучшая" рекомендация, определенная алгоритмом
    private FilmsData filmsData; //данные по фильмам


    //конструктор, "собирающий" с помощью методов все переменные и рекмоендации
    public FilmRecommendation(List<Integer> clientViews, FilmsData filmsData) {
        this.clientViews = clientViews;
        this.filmsData = filmsData;
        this.filmsToWatch = new HashSet<>();
    }

    public void giveRecommendation() {
        //метод для рекомендуемых пользователю фильмов (которые смотрели другие похожие пользователи)
        for (List<Integer> view : filmsData.getFilmsWatched()) {

            //создаю множество из уникально просмотренных фильмов какого-то пользователя из списка
            Set <Integer> setView = new HashSet<>(Set.copyOf(view));

            //добавляю в это множество просмотры нашего пользователя
            setView.addAll(clientViews);

            //и оставляю только совпадающие с view
            setView.retainAll(view);

            //проверяю, количество "совпадающих" больше 50%
            if ((setView.size() / clientViews.size()) > 0.5){

                //добавляю сет с фильмами и убираю уже просмотренные пользователем
                filmsToWatch.addAll(view);
                clientViews.forEach(filmsToWatch::remove);
            }
        }
    }

    public void bestRecommendation(){
        //метод для выбора наиболее популярного фильма из рекомендуемых
        HashMap<Integer, Integer> filmViews = new HashMap<>();

        //для каждого фильма из отобранных ранее считаю суммарные просмотры
        for (Integer film: filmsToWatch){
            int viewCount = 0;
            for (List<Integer> view : filmsData.getFilmsWatched()){
                if (view.contains(film)){
                    viewCount += Collections.frequency(view, film);
                }
            }

            //результат кладу в словарь: фильм -> просмотры
            filmViews.put(film, viewCount);
        }

        //выбираю вариант с максимальным количеством просмотров
        int bestFilm =  Collections.max(filmViews.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();

        //нахожу его имя
        bestFilmName = filmsData.getFilms().get(bestFilm);
    }

    public static void main(String[] args){
        //считываю строку со значениями
        Scanner getViews = new Scanner(System.in);
        String stringViews = getViews.nextLine();

        //создаю из полученной строки список
        List<String> listViews = Arrays.asList(stringViews.split(" "));

        //перевожу значения списка из строк в числа
        List<Integer> clientViews = listViews.stream().map(Integer::parseInt).toList();

        //подаю этот список в конструктор и нахожу рекомендацию
        FilmsData filmsData = new FilmsData(new DataPreparation("src/ru/croc/school/task13/films",
                "src/ru/croc/school/task13/views"));
        FilmRecommendation filmRecommendation = new FilmRecommendation(clientViews, filmsData);
        filmRecommendation.giveRecommendation();
        filmRecommendation.bestRecommendation();
        System.out.println(filmRecommendation.bestFilmName);
    }

}
