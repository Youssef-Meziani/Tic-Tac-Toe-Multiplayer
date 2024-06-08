package ma.game.tictactoemultiplayer.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ma.game.tictactoemultiplayer.Services.SceneService;
import ma.game.tictactoemultiplayer.Services.TextService;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class LoginController {
    private final int minLength = 5;

    @FXML
    private ImageView image;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private ValidationSupport validationSupport = new ValidationSupport();

    @FXML
    public void initialize() {
        image.setImage(new Image(getClass().getResourceAsStream("/images/lock.png")));

        TextService.setFieldMaxLength(username, 10);
        TextService.setFieldMaxLength(password, 10);

        validationSupport.registerValidator(username, (Control, value) -> ValidationResult.fromErrorIf(username, "Password must be at least " + minLength + " characters", value.toString().length() < minLength));
        validationSupport.registerValidator(password, (Control, value) -> ValidationResult.fromErrorIf(password, "Password must be at least " + minLength + " characters", value.toString().length() < minLength));
    }

    @FXML
    public void goToSignUp(ActionEvent event) {
        SceneService.changeScene(event, "signup.fxml", "Tic Tac Toe", 400, 500);
    }

    @FXML
    public void login(ActionEvent event){
        // todo: traiter login, if all good run the next line :
        SceneService.changeScene(event, "home.fxml", "Tic Tac Toe", 1000, 600);
    }
}
