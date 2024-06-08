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
    public static void changeScene(ActionEvent event, String fxmlfile, String title, int width, int height){
        Parent root = null;
//        if (username != null && password != null) {
//            try {
//                FXMLLoader loader = new FXMLLoader(SceneService.class.getResource(fxmlfile));
//                root = loader.load();
//                HomeController homeController = loader.getController();
//                homeController.setUserInfornation(username, password);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
            try{
                root = FXMLLoader.load(Application.class.getResource(fxmlfile));
            } catch(IOException e){
                e.printStackTrace();
            }
//        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.centerOnScreen();
        stage.show();
    }
}