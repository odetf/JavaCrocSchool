package ru.croc.school.task9;

public class Searching {

    private static int numOfFlows;
    private static volatile String hashToSeach;
    public static volatile String truePassword;

    public static String getHashToSeach() {
        return hashToSeach;
    }

    public static void main(String[] args) {
        numOfFlows = Integer.parseInt(args[0]);
        hashToSeach = args[1];
        Thread[] threads = new Thread[numOfFlows];
        for (int i = 0; i < numOfFlows; i++) {
            threads[i] = new Thread(new checkAllPossiblePasswords(i, numOfFlows, getHashToSeach()));
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(truePassword);
    }


}
