package ma.game.tictactoemultiplayer.Services;

import javafx.scene.control.Alert;

public class AlertsService {
    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
