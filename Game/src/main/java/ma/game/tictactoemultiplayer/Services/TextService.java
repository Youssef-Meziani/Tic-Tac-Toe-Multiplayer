package ma.game.tictactoemultiplayer.Services;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class TextService {
    public static void setFieldMaxLength(TextField textField, int maxLength) {
        textField.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= maxLength ? change : null));
    }
}
