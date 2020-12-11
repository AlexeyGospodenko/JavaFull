package Chat;

import Server.Message;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class AuthController {

    public TextField login;
    public TextField password;
    public AnchorPane window;
    public TextField txtHost;
    public TextField txtPort;

    public void enter(ActionEvent actionEvent) throws IOException{
        boolean auth = MockAuthServiceImpl.getInstance().auth(login.getText(), password.getText());
        if (auth) {
            try {
                new ChatSocket(new Socket(txtHost.getText(), Integer.parseInt(txtPort.getText())));
                new CreateWindow("chat.fxml", "NetChat - User: " + login.getText(), true);
                window.getScene().getWindow().hide();
            } catch (ConnectException e) {
                txtHost.setStyle("-fx-border-radius: 5; -fx-border-color: red; -fx-background-insets: 0;");
                txtPort.setStyle("-fx-border-radius: 5; -fx-border-color: red; -fx-background-insets: 0;");
            }
        } else {
            login.setStyle("-fx-border-radius: 5; -fx-border-color: red; -fx-background-insets: 0;");
            password.setStyle("-fx-border-radius: 5; -fx-border-color: red; -fx-background-insets: 0;");
            password.clear();
        }
    }

    public void register(ActionEvent actionEvent) throws IOException {
        new CreateWindow("registration.fxml", "NetChat - Registration", false);
        window.getScene().getWindow().hide();
    }

}