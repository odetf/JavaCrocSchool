package ru.croc.school.task11;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{

    private static final String breakWord = "bye"; //когда клиент "прощается" с сервером, тот перестает с ним взаимодействовать
    private static ArrayList<ClientHandler> clientsConnected;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12000);
            clientsConnected = new ArrayList<>();
            while (true) {

                //соединяем клиента с сервером и запрашиваем никнейм клиента
                Socket clientSocket = serverSocket.accept();
                System.out.println("connected");

                //создаем поток для пришедшего клиента
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread clientThread = new Thread(clientHandler);
                clientsConnected.add(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //класс для работы с каждым отдельным клиентом
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        DataOutputStream output;
        DataInputStream input;

        //конструктор для использования на сервере
        public ClientHandler(Socket clientSocket) throws IOException{
            this.clientSocket = clientSocket;
            //задаем потоки ввода и вывода сообщений клиенту
            output = new DataOutputStream(clientSocket.getOutputStream());
            input = new DataInputStream(clientSocket.getInputStream());
        }

        public void run() {
            try {
                //читаем никнейм юзера и запоминаем его
                String nickname = input.readUTF();

                //выводим на сервере имя пользователя
                System.out.println("New user with nickname " + nickname);

                //говорим юзеру, что мы запомнили его никнейм
                output.writeUTF("Your nickname was successfully written. You may continue.");
                output.flush();
                Thread.sleep(1000);

                //бесконечно принимаем сообщения от пользователя и выводим их на сервере, пока он не "попрощается" с сервером
                String message;
                while ((message = input.readUTF()) != breakWord) {
                    System.out.println("Message from " + nickname + ": " + message);
                    for (ClientHandler client:clientsConnected){
                        client.output.writeUTF("Message from " + nickname + ": " + message);
                        client.output.flush();
                        Thread.sleep(1000);
                    }
                }
            } catch (IOException e) {
                System.out.println("The thread with the user is closed.");
            } catch (InterruptedException ex) {
                System.out.println("The thread was interrupted.");
            }
        }
    }
}
