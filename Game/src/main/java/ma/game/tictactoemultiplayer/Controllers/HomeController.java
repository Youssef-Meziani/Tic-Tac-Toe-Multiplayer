package ma.game.tictactoemultiplayer.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import ma.game.tictactoemultiplayer.Objects.AuthenticatedUser;
import ma.game.tictactoemultiplayer.Services.AlertsService;
import ma.game.tictactoemultiplayer.Services.SceneService;
import ma.game.tictactoemultiplayer.Services.TextService;
import ma.game.tictactoeserver.Interfaces.IGlobalChatService;
import ma.game.tictactoeserver.Interfaces.IOnlinePlayers;
import ma.game.tictactoeserver.Objects.Message;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController {
    @FXML
    private AnchorPane menu, game;

    @FXML
    private TextField worldChatTextField, privateChatTextField;

    @FXML
    private ListView<HBox> worldChatListView, privateChatListView;

    @FXML
    private Label onlineUsersCount, matchesPlayedCount, matchesWonCount;
    private IGlobalChatService globalChatService;
    private AlertsService alertsService;
    private IOnlinePlayers onlinePlayers;
    private Timer messageRefreshTimer;

    public HomeController(){
        initializeGlobalChatService();
        initializeOnlinePlayers();
        this.alertsService = new AlertsService();
        this.messageRefreshTimer = new Timer();

    }
    @FXML
    public void initialize() {
        TextService.setFieldMaxLength(worldChatTextField, 30);
        TextService.setFieldMaxLength(privateChatTextField, 30);
        startMessageRefreshTimer();
        game.setVisible(false);
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
    private void initializeGlobalChatService() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2002);
            this.globalChatService = (IGlobalChatService) registry.lookup("GlobalChatService");
        } catch (Exception e) {
            e.printStackTrace();
            alertsService.showAlert("Error", "Failed to connect to the user service. Please try again later.");
        }
    }
    @FXML
    public void logout(ActionEvent event) throws RemoteException {
        this.onlinePlayers.decrementCount();
        SceneService.changeScene(event, "login.fxml", "Tic Tac Toe", 400, 500);
    }
    private void startMessageRefreshTimer() {
        messageRefreshTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    refreshMessages();
                    refreshUserCount();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    alertsService.showAlert("Error", "Failed to refresh messages. Please try again later.");
                }
            }
        }, 0, 1000);
    }

    private void refreshMessages() throws RemoteException {
        List<Message> messages = globalChatService.receiveGlobalMessages();

        Platform.runLater(() -> updateChatUI(messages));
    }

    public void refreshUserCount(){
        Platform.runLater(() -> {
            try {
                updateOnlineUsersCount();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void updateChatUI(List<Message> messages) {
        worldChatListView.getItems().clear();

        for (Message message : messages) {
            addMessageToUI(message);
        }
    }

    private void addMessageToUI(Message message) {
        String username = message.getSender();
        String content = message.getContent();
        String timestamp = message.getTimeStamp();

        // Create HBox to display the message
        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.CENTER_LEFT);

        // Create labels for username, timestamp, and message content
        Label timestampLabel = new Label(timestamp);
        timestampLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray;");
        timestampLabel.setTextAlignment(TextAlignment.RIGHT);

        Label usernameLabel = new Label(username + ":");
        usernameLabel.setStyle("-fx-font-weight: bold;");

        Label messageLabel = new Label(content);
        messageLabel.setWrapText(true);

        // Add labels to the message box
        messageBox.getChildren().addAll(timestampLabel, usernameLabel, messageLabel);

        // Add the message box to the ListView
        worldChatListView.getItems().add(messageBox);
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
    public void sendPrivateMessage(ActionEvent event) throws RemoteException {
        sendMessage(privateChatTextField, privateChatListView);
    }

    @FXML
    public void sendWorldMessage(ActionEvent event) throws RemoteException {
        sendMessage(worldChatTextField, worldChatListView);
    }

    public void sendMessage(TextField messageField, ListView<HBox> chatScreen) throws RemoteException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timestamp = now.format(formatter);
        String username = AuthenticatedUser.username; // Replace with actual username
        String message = messageField.getText();
        Message messageObject = new Message(username, "",message, timestamp);
        this.globalChatService.sendGlobalMessage(messageObject);
        if (!message.isEmpty()) {
            HBox messageBox = new HBox(10);
            messageBox.setAlignment(Pos.CENTER_LEFT);

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

    public void updateOnlineUsersCount() throws RemoteException {
        onlineUsersCount.setText(String.valueOf(onlinePlayers.getCount()));
    }

    public void updateMatchesPlayedCount(int count) {
        matchesPlayedCount.setText(String.valueOf(count));
    }

    public void updateMatchesWonCount(int count) {
        matchesWonCount.setText(String.valueOf(count));
    }
}
