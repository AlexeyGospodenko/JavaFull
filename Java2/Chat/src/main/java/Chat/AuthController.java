package Chat;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.SQLException;

public class AuthController {

    public TextField login;
    public TextField password;
    public AnchorPane window;
    public TextField txtHost;
    public TextField txtPort;

    public void enter(ActionEvent actionEvent) throws IOException, SQLException {
        String auth = AuthServiceImpl.getInstance().auth(login.getText(), password.getText());
        if (auth != null) {
            try {
                new ChatSocket(new Socket(txtHost.getText(), Integer.parseInt(txtPort.getText())));
                new CreateWindow("chat.fxml", "NetChat - User: " + auth, true);
                window.getScene().getWindow().hide();
            } catch (ConnectException e) {
                txtHost.setStyle(ClientConstants.getActionFail());
                txtPort.setStyle(ClientConstants.getActionFail());
            }
        } else {
            login.setStyle(ClientConstants.getActionFail());
            password.setStyle(ClientConstants.getActionFail());
            password.clear();
        }
    }

    public void register(ActionEvent actionEvent) throws IOException {
        new CreateWindow("registration.fxml", "NetChat - Registration", false);
        window.getScene().getWindow().hide();
    }

}