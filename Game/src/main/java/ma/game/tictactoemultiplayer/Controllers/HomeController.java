package ma.game.tictactoemultiplayer.Controllers;

import com.almasb.fxgl.entity.action.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import ma.game.tictactoemultiplayer.Services.SceneService;
import ma.game.tictactoemultiplayer.Services.TextService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class HomeController {
    @FXML
    private AnchorPane menu, game;

    @FXML
    private TextField worldChatTextField, privateChatTextField;

    @FXML
    private ListView<HBox> worldChatListView, privateChatListView;

    @FXML
    private Label onlineUsersCount, matchesPlayedCount, matchesWonCount;

    @FXML
    public void initialize() {
        TextService.setFieldMaxLength(worldChatTextField, 30);
        TextService.setFieldMaxLength(privateChatTextField, 30);
        game.setVisible(false);
    }

    @FXML
    public void logout(ActionEvent event) {
        //todo : deconectih mn serveur
        SceneService.changeScene(event, "login.fxml", "Tic Tac Toe", 400, 500);
    }

    @FXML
    public void startGameVsOne(ActionEvent event) {
        //todo : lgalo joueur disponible, o moraha afichilo lpartial dyal game
        togglePanes();
    }

    @FXML
    public void startGameVsAI(ActionEvent event) {
        //todo : yl3eb m3a Bot, o moraha afichilo lpartial dyal game
        togglePanes();
    }

    @FXML
    public void sendPrivateMessage(ActionEvent event) {
        sendMessage(privateChatTextField, privateChatListView);
    }

    @FXML
    public void sendWorldMessage(ActionEvent event) {
        sendMessage(worldChatTextField, worldChatListView);
    }

    public void sendMessage(TextField messageField, ListView<HBox> chatScreen) {
        String username = "username"; // Replace with actual username
        String message = messageField.getText();
        if (!message.isEmpty()) {
            HBox messageBox = new HBox(10);
            messageBox.setAlignment(Pos.CENTER_LEFT);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timestamp = now.format(formatter);
            Label timestampLabel = new Label(timestamp);
            timestampLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray;");
            timestampLabel.setTextAlignment(TextAlignment.RIGHT);

            Label usernameLabel = new Label(username + ":");
            usernameLabel.setStyle("-fx-font-weight: bold;");

            Label messageLabel = new Label(message);
            messageLabel.setWrapText(true);

            messageBox.getChildren().addAll(timestampLabel, usernameLabel, messageLabel);

            chatScreen.getItems().add(messageBox);
            messageField.clear();
        }
    }

    @FXML
    public void quitGame(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Game");
        alert.setHeaderText("Confirmation Required");
        alert.setContentText("Are you sure you want to quit the game?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // todo : n3tiw lwin l opponent 3ad nrej3o lmenu
            togglePanes();
        }
    }

    public void togglePanes() {
        game.setVisible(!game.isVisible());
        menu.setVisible(!menu.isVisible());
    }

    public void updateOnlineUsersCount(int count) {
        onlineUsersCount.setText(String.valueOf(count));
    }

    public void updateMatchesPlayedCount(int count) {
        matchesPlayedCount.setText(String.valueOf(count));
    }

    public void updateMatchesWonCount(int count) {
        matchesWonCount.setText(String.valueOf(count));
    }
}
