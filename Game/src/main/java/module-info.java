module ma.game.tictactoemultiplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.rmi;
    requires ma.game.tictactoeserver;

    opens ma.game.tictactoemultiplayer to javafx.fxml;
    exports ma.game.tictactoemultiplayer;
    exports ma.game.tictactoemultiplayer.Controllers;
    opens ma.game.tictactoemultiplayer.Controllers to javafx.fxml;
    //opens javafx.scene to org.controlsfx.controls;
    //opens ma.game.tictactoemultiplayer.Controllers to javafx.fxml, org.controlsfx.controls;

}