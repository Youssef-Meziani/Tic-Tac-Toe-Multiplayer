package ma.game.tictactoeserver.Objects;

import ma.game.tictactoeserver.Interfaces.IGame;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable, IGame {
    private List<String> availablePlayers;

    public Game() throws RemoteException {
        availablePlayers = new ArrayList<>();
    }

    @Override
    public synchronized boolean isPlayerAvailableFor1vs1() {
        return availablePlayers.size() >= 2;
    }

    @Override
    public synchronized void registerPlayer(String playerName) {
        availablePlayers.add(playerName);
    }

    @Override
    public synchronized void unregisterPlayer(String playerName) {
        availablePlayers.remove(playerName);
    }
}
