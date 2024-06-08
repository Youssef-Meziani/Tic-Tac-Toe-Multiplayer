package ma.game.tictactoemultiplayer.Interfaces;

import java.rmi.Remote;

public interface IMessages {
    String getSender();
    String getReceiver();
    String getContent();
}