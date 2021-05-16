package lesson6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public EchoServer() {
        try (ServerSocket serverSocket = new ServerSocket(EchoConstants.PORT)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился!");

            new ClientHandler(socket, "Сервер");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new EchoServer();
    }


}




