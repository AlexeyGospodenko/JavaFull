package lesson4;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AuthController {

    public TextField login;
    public TextField password;
    public AnchorPane window;

    public void enter(ActionEvent actionEvent) throws IOException {
        boolean auth = MockAuthServiceImpl.getInstance()
                .auth(login.getText(), password.getText());
        if (auth) {
            new CreateWindow("chat.fxml", "NetChat - Chat");
            window.getScene().getWindow().hide();
        } else {
            login.setStyle("-fx-border-radius: 5; -fx-border-color: red; -fx-background-insets: 0;");
            password.setStyle("-fx-border-radius: 5; -fx-border-color: red; -fx-background-insets: 0;");
            password.clear();
        }
    }

    public void register(ActionEvent actionEvent) throws IOException {
        new CreateWindow("registration.fxml", "NetChat - Registration");
        window.getScene().getWindow().hide();
    }
}