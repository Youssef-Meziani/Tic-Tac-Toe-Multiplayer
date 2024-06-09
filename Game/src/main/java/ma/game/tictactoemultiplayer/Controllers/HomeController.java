package ma.game.tictactoemultiplayer.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import ma.game.tictactoemultiplayer.Services.GameService;
import ma.game.tictactoemultiplayer.Services.SceneService;
import ma.game.tictactoemultiplayer.Services.TextService;
import ma.game.tictactoeserver.Interfaces.IGame;
import ma.game.tictactoeserver.Interfaces.IUserService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class HomeController {
    @FXML
    private AnchorPane menu, game;

    @FXML
    private TextField worldChatTextField, privateChatTextField;

    @FXML
    private ListView<HBox> worldChatListView, privateChatListView;

    @FXML
    private Label onlineUsersCount, matchesPlayedCount, matchesWonCount, gameInfoLabel;

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    private ArrayList<Button> buttons;

    private Random random = new Random();

    private boolean isAI;

    private IGame onlineGame;

    @FXML
    public void initialize() {
        TextService.setFieldMaxLength(worldChatTextField, 30);
        TextService.setFieldMaxLength(privateChatTextField, 30);
        game.setVisible(false);

        buttons = new ArrayList<>(Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9));

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2002);
            this.onlineGame = (IGame) registry.lookup("Game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logout(ActionEvent event) {
        //todo : deconectih mn serveur
        SceneService.changeScene(event, "login.fxml", "Tic Tac Toe", 400, 500);
    }

    @FXML
    public void startGameVsOne(ActionEvent event) {
        isAI = false;
        GameService.clearBoardButtonsContent(buttons);
        disableBoard();
        togglePanes();
        //todo : start looking for other players, if found proceed
        onlineGame.registerPlayer("ttt");
        if (onlineGame.isPlayerAvailableFor1vs1()) {
            enableBoard();
        } else {
            gameInfoLabel.setText("Looking for the second player, please wait!");
        }
    }

    @FXML
    public void startGameVsAI(ActionEvent event) {
        isAI = true;
        GameService.clearBoardButtonsContent(buttons);
        gameInfoLabel.setText("You play with O");
        togglePanes();
        GameService.makeMove(buttons.get(random.nextInt(9)), "X");
    }

    @FXML
    public void onSquareClick(ActionEvent event){
        if (isAI) {
            GameService.makeMove((Button) event.getSource(),"O");
            GameService.makeMoveAI(buttons);
            checkForWinner();
        } else {

        }
    }

    public void checkForWinner(){
        switch (GameService.checkGameOver(buttons)){
            case "X" :
                if (isAI) {
                    showLoseAlert();
                } else {
                    //todo
                }
                togglePanes();
                break;
            case "O" :
                if (isAI) {
                    showWinAlert();
                } else {
                    //todo
                }
                togglePanes();
                break;
            case "Tie" :
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText("It's a Tie!");
                alert.setContentText("The game ended in a tie.");
                alert.showAndWait();
                togglePanes();
                break;
            default:
                break;
        }
    }

    public void showWinAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Congratulations!");
        alert.setContentText("You have won the game!");
        alert.showAndWait();
    }

    public void showLoseAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Game Over");
        alert.setContentText("You have lost the game. Better luck next time!");
        alert.showAndWait();
    }

    public void disableBoard(){
        buttons.forEach(button -> {
            if (button.getText().isEmpty()) {
                button.setDisable(true);
            }
        });
    }

    public void enableBoard(){
        buttons.forEach(button -> {
            if (button.getText().isEmpty()) {
                button.setDisable(false);
            }
        });
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
