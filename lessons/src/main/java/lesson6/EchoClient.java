package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoClient {

    private Socket socket;
    private final Scanner input= new Scanner(System.in);
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public EchoClient() {
        try{
            this.socket = new Socket(EchoConstants.HOST, EchoConstants.PORT);
            System.out.println("Подключился к серверу!");
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    readMessages();
                } catch (SocketException e) {
                    System.out.println("Соединение разорвано, поток на чтение закрыт.");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }

            }).start();

            new Thread(this::sendMsg).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String messageFromServer = inputStream.readUTF();
            System.out.println(messageFromServer);
            if (messageFromServer.contains(EchoConstants.STOP_WORD)) {
                closeConnection();
                return;
            }
        }
    }

    public void sendMsg()  {
        while (true) {
            if (input.hasNext()) {
                String message = input.next();
                try {
                    String name = "Клиент";
                    outputStream.writeUTF("От " + name + ": " + message);
                    if (message.equals(EchoConstants.STOP_WORD)) {
                        closeConnection();
                        return;
                    }
                } catch (SocketException e) {
                    System.out.println("Сервер разорвал соединение, работа клиента будет завершена.");
                    return;
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeConnection() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       new EchoClient();
    }
}
