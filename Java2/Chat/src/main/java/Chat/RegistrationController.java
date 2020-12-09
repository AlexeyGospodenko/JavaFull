package Chat;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegistrationController {
    public TextField login;
    public TextField password;
    public AnchorPane window;

    public void register(ActionEvent actionEvent) throws Exception {
        if (login.getText().equals("") || password.getText().equals("")) {
            login.setStyle("-fx-border-radius: 5; -fx-border-color: red; -fx-background-insets: 0;");
            password.setStyle("-fx-border-radius: 5; -fx-border-color: red; -fx-background-insets: 0;");
            password.clear();
        } else {
            MockAuthServiceImpl.getInstance().addUser(login.getText(), password.getText());
            new CreateWindow("auth.fxml", "NetChat - Authorization",false);
            window.getScene().getWindow().hide();
        }
    }
}