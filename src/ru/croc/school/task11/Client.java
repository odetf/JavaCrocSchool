package ru.croc.school.task11;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private DataInputStream inMessage;
    private DataOutputStream outMessage;

    public Client(Socket clientSocket, BufferedReader bufferedReader, DataInputStream inMessage, DataOutputStream outMessage) {
        this.clientSocket = clientSocket;
        this.bufferedReader = bufferedReader;
        this.inMessage = inMessage;
        this.outMessage = outMessage;
    }

    public Client(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    private static class ClientWaiting implements Runnable {
        private static Client client;

        public ClientWaiting(Client client) {
            this.client = client;
        }

        //класс для получения сообщений, пока пользователь не попрощается с сервером
        public void run() {
            while (true) {
                try {
                    System.out.println(client.inMessage.readUTF());
                } catch (IOException ignored) {
                }

            }
        }
    }

    private static class ClientWriting implements Runnable {
        private Client client;

        public ClientWriting(Client client) {
            this.client = client;
        }

        //класс для написания и отправки сообщений
        public void run() {
            try {
                while (!client.clientSocket.isOutputShutdown()) {
                    if (client.bufferedReader != null) {
                        String clientMessage = client.bufferedReader.readLine();
                        if (clientMessage.equalsIgnoreCase("bye")) {
                            //если пользователь попрощался, закрываем все потоки и чтение из буфера
                            client.outMessage.close();
                            client.inMessage.close();
                            client.bufferedReader.close();
                            System.out.println("connection stopped");
                        } else {
                            client.outMessage.writeUTF(clientMessage);
                            client.outMessage.flush();
                        }
                    }
                }
            } catch (IOException ex) {
            }
        }
    }


    public static void main(String[] args) {
        try (
                //задаем параметры для входа пользователя: сокет, потоки ввода и вывода, буфер для чтения
                Socket clientSocket = new Socket("localhost", 12361);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                DataInputStream inMessage = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outMessage = new DataOutputStream(clientSocket.getOutputStream());) {

            Client client = new Client(clientSocket, bufferedReader, inMessage, outMessage);
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

            //запускаю потоки для общения и получения данных
            Thread clientWrites = new Thread(new ClientWriting(client));
            Thread clientWaiting = new Thread(new ClientWaiting(client));
            clientWrites.start();
            clientWaiting.run(); //немного странно, но без этого метода и запуска через run() ничего не работает. далее - все ок
        } catch (IOException ioException) {
            System.out.print("Connections failed.");
        }


    }
}
