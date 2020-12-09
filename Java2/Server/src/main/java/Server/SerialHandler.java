package Server;

import java.io.*;
import java.net.Socket;

public class SerialHandler implements Runnable, Closeable {

    private final ObjectOutputStream os;
    private final ObjectInputStream is;
    private boolean running;
    private final ServerController serverController;
    private String userName;

    public SerialHandler(Socket socket, ServerController serverController) throws IOException {
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());
        running = true;
        this.serverController = serverController;
    }

    public void sendMessage(Message message) throws IOException {
        os.writeObject(message);
        os.flush();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Message message = (Message) is.readObject();

                //Управляющие сообщения
                if (message.getUserName().equals(ServerConstans.SYSTEM_USER)) {
                    if (message.getMessage().startsWith(ServerConstans.PREFIX_GET_USER_NAME)) {
                        userName = message.getMessage().substring(ServerConstans.PREFIX_GET_USER_NAME.length());
                        Message messageJoin = Message.of(ServerConstans.SYSTEM_USER, "User \"" + userName + "\" has joined the channel");
                        serverController.broadCast(messageJoin);
                        serverController.getTxtChat().appendText(messageJoin.toString());
                        continue;
                    }
                }

                if (message.getMessage().toLowerCase().startsWith(ServerConstans.PREFIX_PRIVATE_MESSAGE)) {
                    String[] data = message.getMessage().substring(ServerConstans.PREFIX_PRIVATE_MESSAGE.length()).split(" ", 2);
                    if (data.length == 2) {
                        Message messageTo = Message.of(data[0], "PM from " + message.getUserName() + ": " + data[1]);
                        serverController.sendMessageTo(messageTo, data[0]);
                        serverController.getTxtChat().appendText(messageTo.toString());

                        Message messageFrom = Message.of(message.getUserName(), "PM to " + data[0] + ": " + data[1]);
                        serverController.sendMessageTo(messageFrom, message.getUserName());
                        serverController.getTxtChat().appendText(messageFrom.toString());
                        continue;
                    }
                }
                serverController.broadCast(message);
                serverController.getTxtChat().appendText(message.toString());

            } catch (IOException | ClassNotFoundException e) {
                Message messageLeft = Message.of(ServerConstans.SYSTEM_USER, "User \"" + userName + "\" has left the channel");
                serverController.getTxtChat().appendText(messageLeft.toString());
                serverController.kickClient(this);
                try {
                    serverController.broadCast(messageLeft);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                break;
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void close() throws IOException {
        os.close();
        is.close();
    }
}
