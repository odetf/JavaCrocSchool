package ru.croc.school.task14;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface BlackListFilter <T> {

    default List <T> filterComments(Iterable<T> comments, Predicate <T> blackList) {

        //создаю список для получения "чистых комментариев"
        List <T> cleanedCollection = new ArrayList<>();

        for (T comment:comments){

            //проверяю, что условие фильтрации предиката не выполняется
            if (!blackList.test(comment)){

                //добавляю коммент, если он "чистый"
                cleanedCollection.add(comment);
            }
        }
        return cleanedCollection;
    }
}
