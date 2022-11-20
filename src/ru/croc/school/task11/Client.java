package ru.croc.school.task11;

import java.io.*;
import java.net.Socket;

public class Client {


    public static void main(String[] args) {
        try (
                //задаем параметры для входа пользователя: сокет, потоки ввода и вывода, буфер для чтения
                Socket clientSocket = new Socket("localhost", 100);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                DataInputStream inMessage = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outMessage = new DataOutputStream(clientSocket.getOutputStream());) {

            //подключение пользователя и запрос его никнейма
            System.out.println("connected");
            System.out.println("Hello! What's your nickname?");

            //читаем никнейм
            String userNickname = bufferedReader.readLine();

            //передаем никнейм на сервер
            outMessage.writeUTF(userNickname);
            outMessage.flush();

            //сообщаем пользователю, что его никнейм успешно записан и он может продолжать
            System.out.println(inMessage.readUTF());

            //бесконечно получаем сообщения от пользователя, пока он не "попрощается" с сервером
            while (!clientSocket.isOutputShutdown()){
                if (bufferedReader.ready()){
                    System.out.println("Write any message to others.");
                    String clientMessage = bufferedReader.readLine();
                    if (clientMessage.equalsIgnoreCase("bye")){
                        //если пользователь попрощался, закрываем все потоки и чтение из буфера
                        outMessage.close();
                        inMessage.close();
                        bufferedReader.close();
                        System.out.println("connection stopped");
                    }
                    else {
                        outMessage.writeUTF(clientMessage);
                        outMessage.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("The thread was successfully closed.");
        }
    }
}
