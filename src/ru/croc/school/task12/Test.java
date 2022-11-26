package ru.croc.school.task12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Test {
    static Set<String> badWords = Set.of("bad", "stupid", "cool");

    public static void main(String[] args){
        ArrayList<String> comments = new ArrayList<>();
        comments.addAll(Arrays.asList("I love sushi!", "I hate bad words.", "Stupid man", "Today is a day.",
                "COOL day", "NOW I sleep"));
        FilterComments filter = new FilterComments();
        filter.filterComments(comments, badWords);
        System.out.println(comments);
    }
}
