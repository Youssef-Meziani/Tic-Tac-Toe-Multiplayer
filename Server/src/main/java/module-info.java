module ma.game.tictactoeserver {
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

    opens ma.game.tictactoeserver to javafx.fxml;
    exports ma.game.tictactoeserver;
    exports ma.game.tictactoeserver.Controllers;
    exports ma.game.tictactoeserver.Interfaces;
    exports ma.game.tictactoeserver.Objects;

    opens ma.game.tictactoeserver.Controllers to javafx.fxml;
}