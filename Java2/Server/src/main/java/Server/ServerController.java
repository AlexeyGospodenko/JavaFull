package Server;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ServerController {
    public Button btnStart;
    public Button btnSend;
    public TextField txtPort;
    public TextArea txtLog;
    public TextField txtSend;
    public TextArea txtChat;
    private SerialHandler serialHandler;
    private boolean running;
    private ConcurrentLinkedDeque<SerialHandler> clients = new ConcurrentLinkedDeque<>();


    public void serverStart() {
        createServerThread();
        btnStart.setDisable(true);
    }

    private void createServerThread() {
        Thread serverThread = new Thread(() -> {
            running = true;
            try (ServerSocket server = new ServerSocket(Integer.parseInt(txtPort.getText()))) {
                txtLog.appendText(Message.of(ServerConstans.SERVER_USER, "Server started on port " + txtPort.getText()).toString());
                while (running) {
                    txtLog.appendText(Message.of(ServerConstans.SERVER_USER, "Waiting for connection").toString());
                    Socket socket = server.accept();
                    serialHandler = new SerialHandler(socket, this);
                    clients.add(serialHandler);
                    new Thread(serialHandler).start();
                    txtLog.appendText(Message.of(ServerConstans.SERVER_USER, "Client accepted").toString());
                    txtLog.appendText(Message.of(ServerConstans.SERVER_USER, "Client info: " + socket.getInetAddress()).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
    }

    public TextArea getTxtChat() {
        return txtChat;
    }

    public void send() throws IOException {
        Message message = Message.of(ServerConstans.SYSTEM_USER, txtSend.getText());
        txtSend.clear();
        txtChat.appendText(message.toString());
        broadCast(message);
    }

    public void broadCast(Message message) throws IOException {
        for (SerialHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void sendMessageTo(Message message, String userName) throws IOException {
        for (SerialHandler client : clients) {
            if (client.getUserName().equals(userName)) {
                message.setUserName("");
                client.sendMessage(message);
            }
        }
    }

    public void kickClient(SerialHandler client) {
        clients.remove(client);
    }

}
