package lesson4;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    public TextArea output;
    public TextField input;
    public AnchorPane window;
    public Button btnSend;

    public void send(ActionEvent actionEvent) {
        output.appendText(input.getText() + "\n");
        input.clear();
    }

    public void exit(ActionEvent actionEvent) throws Exception {
        FileHistoryService.getInstance().save(Arrays.asList(output.getText().split("\n").clone()));
        new CreateWindow("auth.fxml", "NetChat - Authorization");
        window.getScene().getWindow().hide();
    }

    public void close(ActionEvent actionEvent) {
        FileHistoryService.getInstance().save(Arrays.asList(output.getText().split("\n").clone()));
        window.getScene().getWindow().hide();
    }

    public void initialize(URL location, ResourceBundle resources) {
        btnSend.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("send2.png"))));

        FileHistoryService.getInstance().load().forEach(historyLine -> {
            output.appendText(historyLine + "\n");
        });
    }
}