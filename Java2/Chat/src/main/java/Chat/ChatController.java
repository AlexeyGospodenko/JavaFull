package Chat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import Server.Message;
import Server.ServerConstans;

public class ChatController implements Initializable {

    public TextArea txtChat;
    public TextField txtSend;
    public AnchorPane window;
    public Button btnSend;
    private final String userName = MockAuthServiceImpl.getInstance().getUserName();

    private Thread readThread;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    public void send(ActionEvent actionEvent) {
        Message message = Message.of(userName, txtSend.getText());
        try {
            os.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtSend.clear();
    }

    public void exit(ActionEvent actionEvent) throws Exception {
        FileHistoryService.getInstance().save(Arrays.asList(txtChat.getText().split("\n").clone()));
        new CreateWindow("auth.fxml", "NetChat - Authorization", false);
        window.getScene().getWindow().hide();
    }

    public void close(ActionEvent actionEvent) throws IOException {
        FileHistoryService.getInstance().save(Arrays.asList(txtChat.getText().split("\n").clone()));
        window.getScene().getWindow().hide();
    }

    public void initialize(URL location, ResourceBundle resources) {
        FileHistoryService.getInstance().load().forEach(historyLine -> {
            txtChat.appendText(historyLine + "\n");
        });
        createStreams();
        try {
            os.writeObject(Message.of(ServerConstans.SYSTEM_USER, ServerConstans.PREFIX_GET_USER_NAME + userName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStreams() {
        try {
            os = new ObjectOutputStream(ChatSocket.getSocket().getOutputStream());
            is = new ObjectInputStream(ChatSocket.getSocket().getInputStream());
            createReadThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createReadThread() {
        readThread = new Thread(() -> {
            try {
                while (true) {
                    Message message = (Message) is.readObject();
                    txtChat.appendText(message.toString());
                }
            } catch (Exception e) {
                System.out.println("Server was broken");
            }
        });
        readThread.setDaemon(true);
        readThread.start();
    }

}