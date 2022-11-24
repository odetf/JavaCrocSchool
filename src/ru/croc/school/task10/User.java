package ru.croc.school.task10;

public class User implements Runnable {
    private String username;
    private AuctionLot userLot;
    private int userOfferedPrice;

    public User(String username, AuctionLot userLot, int userOfferedPrice) {
        this.username = username;
        this.userLot = userLot;
        this.userOfferedPrice = userOfferedPrice;
    }

    public void run() {
        userLot.setBet(userOfferedPrice, username);
    }
}
