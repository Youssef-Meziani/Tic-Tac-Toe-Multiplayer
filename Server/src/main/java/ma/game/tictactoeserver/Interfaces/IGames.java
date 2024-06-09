package ma.game.tictactoeserver.Interfaces;

import ma.game.tictactoeserver.Objects.Game;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGames extends Remote {
    void createGame(Game game) throws RemoteException;
    void removeGame(Game game) throws RemoteException;
    ArrayList<Game> getGames();
}
