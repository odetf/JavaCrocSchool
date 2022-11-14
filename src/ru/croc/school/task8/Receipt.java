package ru.croc.school.task8;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Receipt {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a double: ");
        double num = in.nextDouble();
        NumberFormat curr = NumberFormat.getCurrencyInstance(Locale.getDefault());
        System.out.println("Result: "+curr.format(num));
    }
}
