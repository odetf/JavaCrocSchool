package ru.croc.school.task10;

import java.util.Date;

public class AuctionLot {
    public volatile int currentPrice;
    public volatile String userName;
    private final Date endTradeDate;

    public AuctionLot(int currentPrice, String userName, Date endTradeDate) {
        this.currentPrice = currentPrice;
        this.userName = userName;
        this.endTradeDate = endTradeDate;
    }

    public void setBet(int newCurrentPrice, String userOfferedPrice) {
        synchronized (this) {
            if (newCurrentPrice > currentPrice & System.currentTimeMillis() < endTradeDate.getTime()) {
                this.currentPrice = newCurrentPrice;
                this.userName = userOfferedPrice;
            } else if (currentPrice <= newCurrentPrice) {
                System.out.println("Новая ставка для лота должна быть выше текущей. Поставьте цену больше, " +
                        "чем " + currentPrice);
            } else if (System.currentTimeMillis() >= endTradeDate.getTime()) {
                System.out.println("Время торгов завершено. Поставить ставку больше нельзя.");
            }
        }
    }

    public String getWinnerName() {
        if (endTradeDate.getTime() <= System.currentTimeMillis()){
            return userName;
        }
        else {
            return  "Lot is still active. There's no winner yet.";
        }
    }
}
