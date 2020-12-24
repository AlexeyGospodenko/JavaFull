package Chat;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RegistrationController {
    public TextField txtLogin;
    public TextField txtPassword;
    public AnchorPane window;
    public TextField txtNickname;
    public Button btnExit;
    private boolean checkLogin = false;
    private boolean checkNickname = false;

    public void register(ActionEvent actionEvent) throws Exception {
        if (txtLogin.getText().equals("") || txtPassword.getText().equals("") || txtNickname.getText().equals("")) {
            txtLogin.setStyle(ClientConstants.getActionFail());
            txtPassword.setStyle(ClientConstants.getActionFail());
            txtNickname.setStyle(ClientConstants.getActionFail());
            txtPassword.clear();
        } else {
            txtPassword.setStyle(ClientConstants.getActionSuccess());
            if (AuthServiceImpl.getInstance().isNicknameExists(txtNickname.getText())) {
                txtNickname.setStyle(ClientConstants.getActionFail());
                txtNickname.clear();
                txtNickname.setPromptText("Nickname is busy");
            } else {
                txtNickname.setStyle(ClientConstants.getActionSuccess());
                checkNickname = true;
            }
            if (AuthServiceImpl.getInstance().isLoginExists(txtLogin.getText())) {
                txtLogin.setStyle(ClientConstants.getActionFail());
                txtLogin.clear();
                txtLogin.setPromptText("Login is busy");
            } else {
                txtLogin.setStyle(ClientConstants.getActionSuccess());
                checkLogin = true;
            }

        }
        if (checkLogin && checkNickname) {
            AuthServiceImpl.getInstance().addUser(txtLogin.getText(), txtPassword.getText(), txtNickname.getText());
            new CreateWindow("auth.fxml", "NetChat - Authorization", false);
            window.getScene().getWindow().hide();
        }
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        new CreateWindow("auth.fxml", "NetChat - Authorization", false);
        window.getScene().getWindow().hide();
    }
}