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

public class SignupController {
    private final int minLength = 5;

    @FXML
    private ImageView image;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password, passwordConfirmation;

    private ValidationSupport validationSupport = new ValidationSupport();

    @FXML
    public void initialize() {
        image.setImage(new Image(getClass().getResourceAsStream("/images/lock.png")));

        TextService.setFieldMaxLength(username, 10);
        TextService.setFieldMaxLength(password, 10);
        TextService.setFieldMaxLength(passwordConfirmation, 10);

        validationSupport.registerValidator(username, (Control, value) -> ValidationResult.fromErrorIf(username, "Password must be at least " + minLength + " characters", value.toString().length() < minLength));
        validationSupport.registerValidator(password, (Control, value) -> ValidationResult.fromErrorIf(password, "Password must be at least " + minLength + " characters", value.toString().length() < minLength));
        validationSupport.registerValidator(passwordConfirmation, (Control, value) -> ValidationResult.fromErrorIf(passwordConfirmation, "Password must be at least " + minLength + " characters", value.toString().length() < minLength));

        validationSupport.registerValidator(passwordConfirmation, (Control, value) -> ValidationResult.fromErrorIf(passwordConfirmation, "Password confirmation does not match", !value.equals(password.getText())));
    }

    @FXML
    public void goToLogIn(ActionEvent event) {
        SceneService.changeScene(event, "login.fxml", "Tic Tac Toe", 400, 500);
    }

    @FXML
    public void signup(ActionEvent event){
        // todo: traiter singup, if all good login automaticaly and run the next line :
        SceneService.changeScene(event, "home.fxml", "Tic Tac Toe", 1000, 600);
    }
}
