package ma.game.tictactoeserver;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("control-panel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        stage.getIcons().add(icon);
        stage.setTitle("Tic Tac Toe Server");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}