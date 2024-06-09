package ma.game.tictactoemultiplayer.Services;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ma.game.tictactoemultiplayer.Application;
import ma.game.tictactoemultiplayer.Controllers.HomeController;

import java.io.IOException;

public class SceneService {
    private static HomeController homeController;

    public static void changeScene(ActionEvent event, String fxmlfile, String title, int width, int height) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlfile));
            root = loader.load();
            if (fxmlfile.equals("home.fxml")) {
                homeController = loader.getController();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.centerOnScreen();
        stage.show();
    }

    public static HomeController getHomeController() {
        return homeController;
    }
}
