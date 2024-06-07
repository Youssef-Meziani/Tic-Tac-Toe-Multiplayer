package ma.game.tictactoemultiplayer.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ma.game.tictactoemultiplayer.Services.SceneService;

public class LoginController {
    @FXML
    private ImageView image;

    @FXML
    public void initialize() {
        image.setImage(new Image(getClass().getResourceAsStream("/images/lock.png")));
    }

    @FXML
    public void goToSignUp(ActionEvent event) {
        SceneService.changeScene(event, "signup.fxml", "Tic Tac Toe", 400, 500);
    }
}
