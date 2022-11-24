package ru.croc.school.task10;

import ru.croc.school.task10.AuctionLot;
import java.util.Date;

public class Test {

    public static void main(String[] args){
        AuctionLot auctionLotTest = new AuctionLot(10000, "James", new Date(2022, 11, 25));
        Thread user1 = new Thread(new User("Dave", auctionLotTest, 17000));
        Thread user2 = new Thread(new User("Not Dave", auctionLotTest, 30000));
        Thread user3 = new Thread(new User("Kevin", auctionLotTest, 40000));
        Thread user4 = new Thread(new User("Not Kevin", auctionLotTest, 7000));
        Thread user5 = new Thread(new User("Not Kevin, not Dave", auctionLotTest, 33000));
        Thread[] users = {user1, user2, user3, user4, user5};
        for (Thread user:users){
            user.start();
            try{user.join();}
            catch(InterruptedException e){e.printStackTrace();}
        }
        System.out.println(auctionLotTest.userName);
        System.out.println(auctionLotTest.currentPrice);
    }
}
