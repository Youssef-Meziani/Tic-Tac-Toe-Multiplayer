package ma.game.tictactoeserver.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGame extends Remote {
    boolean isPlayerAvailableFor1vs1();
    void registerPlayer(String playerName);
    void unregisterPlayer(String playerName);
}
