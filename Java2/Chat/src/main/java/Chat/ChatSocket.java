package Chat;

import java.net.Socket;

public class ChatSocket {
    private static Socket socket;

    public ChatSocket(Socket socket) {
        this.socket = socket;
    }

    public static Socket getSocket() {
        return socket;
    }
}
