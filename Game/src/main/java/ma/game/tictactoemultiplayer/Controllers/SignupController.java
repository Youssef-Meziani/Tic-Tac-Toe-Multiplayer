package ma.game.tictactoemultiplayer.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ma.game.tictactoemultiplayer.Services.AlertsService;
import ma.game.tictactoeserver.Interfaces.IUserService;
import ma.game.tictactoeserver.Objects.User;
import ma.game.tictactoemultiplayer.Services.SceneService;
import ma.game.tictactoemultiplayer.Services.TextService;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SignupController {
    private static final int MIN_LENGTH = 5;

    @FXML
    private ImageView image;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password, passwordConfirmation;
    private ValidationSupport validationSupport = new ValidationSupport();
    private IUserService userService;
    private AlertsService alertsService;
    public SignupController() {
        initializeUserService();
        this.alertsService = new AlertsService();
    }

    private void initializeUserService() {
        try {
            System.out.println("Attempting to connect to RMI registry...");
            Registry registry = LocateRegistry.getRegistry("localhost", 2002);
            this.userService = (IUserService) registry.lookup("UserService");
            System.out.println("Connected to UserService.");
        } catch (Exception e) {
            e.printStackTrace();
            alertsService.showAlert("Error", "Failed to connect to the user service. Please try again later.");
        }
    }

    @FXML
    public void initialize() {
        image.setImage(new Image(getClass().getResourceAsStream("/images/lock.png")));

        TextService.setFieldMaxLength(username, 10);
        TextService.setFieldMaxLength(password, 10);
        TextService.setFieldMaxLength(passwordConfirmation, 10);

        validationSupport.registerValidator(username, (Control, value) ->
                ValidationResult.fromErrorIf(username, "Username must be at least " + MIN_LENGTH + " characters", value.toString().length() < MIN_LENGTH));

        validationSupport.registerValidator(password, (Control, value) ->
                ValidationResult.fromErrorIf(password, "Password must be at least " + MIN_LENGTH + " characters", value.toString().length() < MIN_LENGTH));

        validationSupport.registerValidator(passwordConfirmation, (Control, value) ->
                ValidationResult.fromErrorIf(passwordConfirmation, "Password must be at least " + MIN_LENGTH + " characters", value.toString().length() < MIN_LENGTH));

        validationSupport.registerValidator(passwordConfirmation, Validator.createPredicateValidator(
                passwordConfirmationText -> passwordConfirmation.getText().equals(password.getText()), "Password confirmation does not match"));

        password.textProperty().addListener((observable, oldValue, newValue) -> validationSupport.redecorate());
        passwordConfirmation.textProperty().addListener((observable, oldValue, newValue) -> validationSupport.redecorate());
    }

    @FXML
    public void goToLogIn(ActionEvent event) {
        SceneService.changeScene(event, "login.fxml", "Tic Tac Toe", 400, 500);
    }

    @FXML
    public void signup(ActionEvent event) {
        try {
            String user_name = username.getText();
            String pass_word = password.getText();
            String passConfirm = passwordConfirmation.getText();

            if (user_name.isEmpty() || pass_word.isEmpty() || passConfirm.isEmpty()) {
                alertsService.showAlert("Error", "Please fill in all fields.");
                return;
            }

            if (!pass_word.equals(passConfirm)) {
                alertsService.showAlert("Error", "Passwords do not match.");
                return;
            }

            User newUser = new User(user_name, pass_word);
            userService.registerUser(newUser);
            alertsService.showAlert("Success", "User registered successfully.");

            // Close the signup window
            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();

            // Change to home scene
            SceneService.changeScene(event, "signup.fxml", "Tic Tac Toe", 400, 500);
        } catch (Exception e) {
            e.printStackTrace();
            alertsService.showAlert("Error", "Failed to register user. Please try again.");
        }
    }
}
