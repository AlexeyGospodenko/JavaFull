package lesson6.Server;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable, Closeable {

    private final DataInputStream is;
    private final DataOutputStream os;
    private boolean running;

    public ClientHandler(Socket socket) throws IOException {
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void sendMessage (String message) throws IOException {
        os.writeUTF(message);
        os.flush();
    }

    @Override
    public void run() {
        while (running) {
            String message;
            try {
                message = is.readUTF();
                if (message.equals("/quit")) {
                    os.writeUTF(message);
                    os.flush();
                    running = false;
                    break;
                }
            } catch (IOException e) {
                System.out.println("Client kicked");
                break;
            }
            System.out.println("Received from client: " + message);
        }
    }

    @Override
    public void close() throws IOException {
        os.close();
        is.close();
    }
}
