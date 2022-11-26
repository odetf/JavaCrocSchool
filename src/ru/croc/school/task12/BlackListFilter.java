package ru.croc.school.task12;
import java.util.List;
import java.util.Set;

public interface BlackListFilter {
    void filterComments(List<String> comments, Set<String> blackList);
}
