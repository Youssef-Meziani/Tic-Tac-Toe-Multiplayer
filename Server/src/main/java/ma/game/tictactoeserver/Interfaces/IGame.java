package ma.game.tictactoeserver.Interfaces;

import java.rmi.Remote;

public interface IGame extends Remote {
    boolean gameHasPlace();
    void registerPlayer(String playerName);
    void unregisterPlayer(String playerName);}
