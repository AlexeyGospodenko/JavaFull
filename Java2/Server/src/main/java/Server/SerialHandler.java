package Server;

import Server.Services.DatabaseServiceImpl;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

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
                if (message.getUserName().equals(ServerConstants.getSystemUser())) {

                    //Регистрация
                    if (message.getMessage().startsWith(ServerConstants.getPrefixRegisterMessage())) {
                        String[] data = message.getMessage().split(" ", 4);
                        if (data.length == 4) {
                            boolean isLoginExists = DatabaseServiceImpl.getInstance().isLoginExists(data[1]);
                            boolean isNicknameExists = DatabaseServiceImpl.getInstance().isNicknameExists(data[3]);
                            String registrationMessage = "";
                            registrationMessage = registrationMessage + ((isLoginExists) ? ServerConstants.getLoginBusyMessage() : "");
                            registrationMessage = registrationMessage + ((isNicknameExists) ? ServerConstants.getNicknameBusyMessage() : "");
                            if (registrationMessage.equals("")) {
                                registrationMessage = ServerConstants.getIsRegistrationMessage();
                                DatabaseServiceImpl.getInstance().addUser(data[1], data[2], data[3]);
                            }
                            os.writeObject(Message.of(ServerConstants.getSystemUser(), ServerConstants.getPrefixRegisterMessage() + registrationMessage));
                            continue;
                        }
                    }

                    //Аутентификация и сообщение о присоединение к чату
                    if (message.getMessage().startsWith(ServerConstants.getPrefixAuthMessage())) {
                        String[] data = message.getMessage().split(" ", 3);
                        if (data.length == 3) {
                            nickname = DatabaseServiceImpl.getInstance().auth(data[1], data[2]);
                            if (nickname == null) {
                                os.writeObject(Message.of(ServerConstants.getSystemUser(),
                                        ServerConstants.getPrefixAuthMessage() + ServerConstants.getAuthFailedMessage()));
                            } else {
                                os.writeObject(Message.of(ServerConstants.getSystemUser(), ServerConstants.getPrefixAuthMessage() + nickname));
                                Message messageJoin = Message.of(ServerConstants.getSystemUser(), "User \"" + nickname + "\" has joined the channel");
                                serverController.broadCast(messageJoin);
                                serverController.getTxtChat().appendText(messageJoin.getFormattedMessage());
                            }
                        }
                        continue;
                    }
                }

                //Личные сообщения
                if (message.getMessage().startsWith(ServerConstants.getPrefixPrivateMessage())) {
                    String[] data = message.getMessage().substring(ServerConstants.getPrefixPrivateMessage().length()).split(" ", 2);
                    if (data.length == 2) {
                        Message messageTo = Message.of(data[0], "PM from " + nickname + ": " + data[1]);
                        serverController.sendMessageTo(messageTo, data[0]);
                        serverController.getTxtChat().appendText(messageTo.getFormattedMessage());

                        Message messageFrom = Message.of(nickname, "PM to " + data[0] + ": " + data[1]);
                        serverController.sendMessageTo(messageFrom, nickname);
                        serverController.getTxtChat().appendText(messageFrom.getFormattedMessage());
                        continue;
                    }
                }

                //Смена ника
                if (message.getMessage().toLowerCase().startsWith(ServerConstants.getPrefixChangeNicknameMessage())) {
                    String[] data = message.getMessage().substring(ServerConstants.getPrefixChangeNicknameMessage().length()).split(" ", 2);
                    if (data.length == 1) {
                        if (DatabaseServiceImpl.getInstance().isNicknameExists(data[0])) {
                            Message messageNicknameReserved = Message.of(ServerConstants.getSystemUser(), "Nickname \"" + data[0] + "\" is reserved");
                            os.writeObject(messageNicknameReserved);
                        } else {
                            DatabaseServiceImpl.getInstance().changeNickname(nickname, data[0]);
                            Message messageChangeNickname = Message.of(ServerConstants.getSystemUser(),
                                    "User \"" + nickname + "\" change nickname to \"" + data[0] + "\"");
                            nickname = data[0];
                            serverController.broadCast(messageChangeNickname);
                            serverController.getTxtChat().appendText(messageChangeNickname.getFormattedMessage());
                        }
                    }
                    continue;
                }

                //Если обычное сообщение то бродкастим его
                if (message.getUserName().equals(ServerConstants.getCurrentUser())) {
                    message.setUserName(nickname);
                    serverController.broadCast(message);
                    serverController.getTxtChat().appendText(message.getFormattedMessage());
                }


            } catch (IOException | ClassNotFoundException e) {
                if (nickname != null) {
                    Message messageLeft = Message.of(ServerConstants.getSystemUser(), "User \"" + nickname + "\" has left the channel");
                    serverController.getTxtChat().appendText(messageLeft.getFormattedMessage());
                    try {
                        serverController.broadCast(messageLeft);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                serverController.kickClient(this);
                break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
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