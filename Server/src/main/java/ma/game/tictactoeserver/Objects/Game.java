package ma.game.tictactoeserver.Objects;

import ma.game.tictactoeserver.Interfaces.IGame;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable, IGame {
    private ArrayList<String> gamePlayers;

    public Game() {
        gamePlayers = new ArrayList<>();
    }

    @Override
    public synchronized boolean gameHasPlace() {
        return gamePlayers.size() < 2;
    }

    @Override
    public synchronized void registerPlayer(String playerName) {
        gamePlayers.add(playerName);
    }

    @Override
    public synchronized void unregisterPlayer(String playerName) {
        gamePlayers.remove(playerName);
    }
}
