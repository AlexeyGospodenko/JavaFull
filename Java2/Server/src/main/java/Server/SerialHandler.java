package Server;

import java.io.*;
import java.net.Socket;

public class SerialHandler implements Runnable, Closeable {

    private final ObjectOutputStream os;
    private final ObjectInputStream is;
    private boolean running;
    private final ServerController serverController;
    private String nickname;

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
                //Идентицикация имени и сообщение о присоединение к чату
                if (message.getUserName().equals(ServerConstants.getSystemUser())) {
                    if (message.getMessage().startsWith(ServerConstants.getPrefixGetUserName())) {
                        nickname = message.getMessage().substring(ServerConstants.getPrefixGetUserName().length());
                        Message messageJoin = Message.of(ServerConstants.getSystemUser(), "User \"" + nickname + "\" has joined the channel");
                        serverController.broadCast(messageJoin);
                        serverController.getTxtChat().appendText(messageJoin.getFormattedMessage());
                        continue;
                    }
                }

                //Личные сообщения
                if (message.getMessage().toLowerCase().startsWith(ServerConstants.getPrefixPrivateMessage())) {
                    String[] data = message.getMessage().substring(ServerConstants.getPrefixPrivateMessage().length()).split(" ", 2);
                    if (data.length == 2) {
                        Message messageTo = Message.of(data[0], "PM from " + message.getUserName() + ": " + data[1]);
                        serverController.sendMessageTo(messageTo, data[0]);
                        serverController.getTxtChat().appendText(messageTo.getFormattedMessage());

                        Message messageFrom = Message.of(message.getUserName(), "PM to " + data[0] + ": " + data[1]);
                        serverController.sendMessageTo(messageFrom, message.getUserName());
                        serverController.getTxtChat().appendText(messageFrom.getFormattedMessage());
                        continue;
                    }
                }

                //Смена ника
                if (message.getMessage().toLowerCase().startsWith(ServerConstants.getPrefixChangeNicknameMessage())) {
                    String[] data = message.getMessage().substring(ServerConstants.getPrefixChangeNicknameMessage().length()).split(" ", 2);
                    if (data.length == 1) {
                        Message messageChangeNickname = Message.of(ServerConstants.getSystemUser(),
                                "User \"" + nickname + "\" change nickname to \"" + data[0] +"\"");
                        nickname = data[0];
                        serverController.broadCast(messageChangeNickname);
                        serverController.getTxtChat().appendText(messageChangeNickname.getFormattedMessage());
                        continue;
                    }
                }

                serverController.broadCast(message);
                serverController.getTxtChat().appendText(message.getFormattedMessage());

            } catch (IOException | ClassNotFoundException e) {
                Message messageLeft = Message.of(ServerConstants.getSystemUser(), "User \"" + nickname + "\" has left the channel");
                serverController.getTxtChat().appendText(messageLeft.getFormattedMessage());
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

    public String getNickname() {
        return nickname;
    }

    @Override
    public void close() throws IOException {
        os.close();
        is.close();
    }
}