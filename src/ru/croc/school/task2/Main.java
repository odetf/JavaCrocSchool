package ru.croc.school.task2;

public class Main {

    public static void main(String[] args) {
        int a1 = Integer.parseInt(args[0]);
        int diff = Integer.parseInt(args[1]);
        int qNum = Integer.parseInt(args[2]);
        int sum = 0;
        for (int i = 0; i < qNum; i++) {
            sum += a1;
            a1 += diff;
        }
        System.out.println(sum);
    }
}