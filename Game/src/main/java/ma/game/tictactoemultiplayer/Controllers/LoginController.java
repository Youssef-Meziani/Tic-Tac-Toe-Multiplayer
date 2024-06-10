package ma.game.tictactoemultiplayer.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ma.game.tictactoemultiplayer.Objects.AuthenticatedUser;
import ma.game.tictactoemultiplayer.Services.AlertsService;
import ma.game.tictactoemultiplayer.Services.SceneService;
import ma.game.tictactoemultiplayer.Services.TextService;
import ma.game.tictactoeserver.Interfaces.IOnlinePlayers;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import ma.game.tictactoeserver.Interfaces.IUserService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoginController {
    private final int minLength = 5;

    @FXML
    private ImageView image;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private ValidationSupport validationSupport = new ValidationSupport();
    private IUserService userService;
    private IOnlinePlayers onlinePlayers;

    private AlertsService alertsService;

    public LoginController() {
        initializeUserService();
        initializeOnlinePlayers();
        this.alertsService = new AlertsService();
    }

    private void initializeUserService() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2002);
            this.userService = (IUserService) registry.lookup("UserService");
        } catch (Exception e) {
            e.printStackTrace();
            alertsService.showAlert("Error", "Failed to connect to the user service. Please try again later.");
        }
    }
    private void initializeOnlinePlayers() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2002);
            this.onlinePlayers = (IOnlinePlayers) registry.lookup("OnlinePlayers");
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

        validationSupport.registerValidator(username, (Control, value) -> ValidationResult.fromErrorIf(username, "Password must be at least " + minLength + " characters", value.toString().length() < minLength));
        validationSupport.registerValidator(password, (Control, value) -> ValidationResult.fromErrorIf(password, "Password must be at least " + minLength + " characters", value.toString().length() < minLength));
    }

    @FXML
    public void goToSignUp(ActionEvent event) {
        SceneService.changeScene(event, "signup.fxml", "Tic Tac Toe", 400, 500);
    }

    @FXML
    public void login(ActionEvent event) {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();
        try {
            if (userService.loginUser(enteredUsername, enteredPassword)) {
                AuthenticatedUser.username = enteredUsername;
                SceneService.changeScene(event, "home.fxml", "Tic Tac Toe", 1000, 600);
                onlinePlayers.incrementCount();
            } else {
                alertsService.showAlert("Error", "Invalid username or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            alertsService.showAlert("Error", "Failed to authenticate user. Please try again.");
        }
    }

}
