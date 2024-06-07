package ma.game.tictactoeserver.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ma.game.tictactoeserver.Services.ServerRMI;

public class ControlPanelController {
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        Image serverImage = new Image(getClass().getResourceAsStream("/images/server.png"));
        imageView.setImage(serverImage);
    }

    @FXML
    private void handleToggleButtonAction() {
        if (toggleButton.isSelected()) {
            ServerRMI.start();
            toggleButton.setText("Running");
            toggleButton.setStyle("-fx-text-fill: green;");
        } else {
            ServerRMI.stop();
            toggleButton.setText("Stopped");
            toggleButton.setStyle("-fx-text-fill: red;");
        }
    }
}
