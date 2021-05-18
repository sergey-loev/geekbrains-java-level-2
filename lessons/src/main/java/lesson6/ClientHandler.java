package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ClientHandler {

    private Socket socket;
    private final Scanner input= new Scanner(System.in);
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String serverName;

    public ClientHandler(EchoServer server,Socket socket) {
        try {
            this.socket = socket;
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            this.serverName = server.getName();
            new Thread(() -> {
                try {
                    readMessages();
                } catch (SocketException e) {
                    System.out.println("Соединение разорвано, поток на чтение закрыт.");
                }catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }

            }).start();

            new Thread(this::sendMsg).start();

        } catch (IOException ex) {
            System.out.println("Проблема при создании клиента");
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String messageFromClient = inputStream.readUTF();
            System.out.println(messageFromClient);
            if (messageFromClient.contains(EchoConstants.STOP_WORD)) {
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
                    outputStream.writeUTF("От " + serverName + ": " + message);
                    if (message.equals(EchoConstants.STOP_WORD)) {
                        closeConnection();
                        return;
                    }
                }  catch (SocketException e) {
                    System.out.println("Клиент уже отключен, работа сервера будет завершена.");
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


}
