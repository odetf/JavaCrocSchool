package ru.croc.school.task9;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class checkAllPossiblePasswords implements Runnable {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private int flowNum; //номер потока
    private int numOfFlows; //кол-во потоков
    private int countFlows = (int) Math.pow(26, 7); //кол-во вариантов паролей
    private final int passwordLength = 7; //длина пароля
    private int numStart; //начальный номер поиска для потока
    private int numEnd; //конечный номер поиска для потока
    private static volatile boolean passwordFound = false; //флаг для поиска верного пароля
    private String hashedPassword;

    //конструктор
    public checkAllPossiblePasswords(int flowNum, int numOfFlows, String hashedPassword) {
        this.flowNum = flowNum;
        this.numOfFlows = numOfFlows;
        this.hashedPassword = hashedPassword;
        numStart = (countFlows * (flowNum - 1)) / numOfFlows;
        numEnd = (countFlows * flowNum) / numOfFlows - 1;
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    private static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }


    public void run() {
        for (int i = numStart; i <= numEnd & passwordFound != true; i++) {
            String possiblePassword = generatePossiblePass(i).toString();
            if (hashPassword(possiblePassword).equals(hashedPassword)) {
                Searching.truePassword = possiblePassword;
                passwordFound = true;
            }

        }
    }

    private StringBuilder generatePossiblePass(int num) {
        int[] passwordIndex = new int[7];
        //запись кода 7-ми символов в массив
        for (int i = 0; i < 7; i++) {
            passwordIndex[i] = (int) num % 26;
            num /= 26;
        }
        StringBuilder password = new StringBuilder();
        //формирование строки, используя индексы из массива
        for (int i = 0; i < 7; i++) {
            password.append((char) (passwordIndex[i]));
        }
        return password;
    }
}
