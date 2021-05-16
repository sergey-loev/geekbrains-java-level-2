package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {

    private Socket socket;
    private Scanner input;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String name;

    public ClientHandler(Socket socket, String name) {
        try {
            this.socket = socket;
            this.input = new Scanner(System.in);
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            this.name = name;
            new Thread(() -> {
                try {
                    readMessages();
                } catch (IOException e) {
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
                return;
            }
        }
    }


    public void sendMsg()  {
        while (true) {
            if (input.hasNext()) {
                String message = input.next();
                try {
                    outputStream.writeUTF("От " + name + ": " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (message.equals(EchoConstants.STOP_WORD)) {
                    return;
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
