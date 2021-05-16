package lesson6;

import java.io.IOException;
import java.net.Socket;

public class EchoClient {
    public EchoClient() {
        try{
            Socket socket = new Socket(EchoConstants.HOST, EchoConstants.PORT);
            System.out.println("Подключился к серверу!");
            new ClientHandler(socket, "Клиент");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       new EchoClient();
    }
}
