package lesson6.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private ClientHandler clientHandler;

    public Server() {
        createWriteThread();
        try (ServerSocket server = new ServerSocket(8189)) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = server.accept();
                clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
                System.out.println("Client accepted");
                System.out.println("Client info:" + socket.getInetAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createWriteThread() {
        Thread writeThread = new Thread(() -> {
            try {
                Scanner in = new Scanner(System.in);
                while (in.hasNextLine()) {
                    String message = in.nextLine();
                    if (clientHandler == null) {
                        System.out.println("Waiting for connection");
                        while (clientHandler == null) {
                            Thread.sleep(500);
                        }
                    }
                    if (clientHandler.isRunning()) {
                        clientHandler.sendMessage(message);
                        if (message.equals("/quit")) {
                            clientHandler.setRunning(false);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        writeThread.setDaemon(true);
        writeThread.start();
    }

}
