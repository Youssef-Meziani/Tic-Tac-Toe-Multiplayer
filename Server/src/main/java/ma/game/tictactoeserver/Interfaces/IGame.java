package ma.game.tictactoeserver.Interfaces;

public interface IGame {
    boolean gameHasPlace();
    void registerPlayer(String playerName);
    void unregisterPlayer(String playerName);}
