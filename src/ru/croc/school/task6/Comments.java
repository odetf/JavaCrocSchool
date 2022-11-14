package ru.croc.school.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comments {

    public static String removeJavaComments(String programText){
        Pattern pattern = Pattern.compile("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)");
        Matcher match = pattern.matcher(programText);
        String newProgramText = match.replaceAll("");
        return newProgramText;
    }

    public static void main(String[] args){
        String source = "/*\n" +
                " * My first ever program in Java!\n" +
                " */\n" +
                "class Hello { // class body starts here \n" +
                "  \n" +
                "  /* main method */\n" +
                "  public static void main(String[] args/* we put command line arguments here*/) {\n" +
                "    // this line prints my first greeting to the screen\n" +
                "    System.out.println(\"Hi!\"); // :)\n" +
                "  }\n" +
                "} // the end\n" +
                "// to be continued...\n";
        String noComments = removeJavaComments(source);
        System.out.println(noComments);

    }
}
