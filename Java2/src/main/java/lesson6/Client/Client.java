package lesson6.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private DataInputStream is;
    private DataOutputStream os;

    public Client() {
        try {
            Socket socket = new Socket("localhost", 8189);
            os = new DataOutputStream(socket.getOutputStream());
            is = new DataInputStream(socket.getInputStream());
            createReadThread();
            createWriteThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createWriteThread() {
        Thread writeThread = new Thread(() -> {
            Scanner in = new Scanner(System.in);
            while (in.hasNextLine()) {
                String message = in.nextLine();
                try {
                    os.writeUTF(message);
                    os.flush();
                    if (message.equals("/quit")) {
                        System.out.println("Write thread closed");
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        writeThread.setDaemon(true);
        writeThread.start();
   }

    private void createReadThread() {
        Thread readThread = new Thread(() -> {
            try {
                while (true) {
                    String message = is.readUTF();
                    System.out.println("Message from server: " + message);
                    if (message.equals("/quit")) {
                        System.out.println("Read thread closed");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Server was broken");
            }
        });
        readThread.start();
    }

}
