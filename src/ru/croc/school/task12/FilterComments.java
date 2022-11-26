package ru.croc.school.task12;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FilterComments implements BlackListFilter{

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        List<String> deleteComments = new ArrayList<>();
        for (String comment:comments){
            for (String badWord:blackList){
                if (comment.toLowerCase().contains(badWord)){
                    deleteComments.add(comment);
                }
            }
        }
        comments.removeAll(deleteComments);
    }
}
