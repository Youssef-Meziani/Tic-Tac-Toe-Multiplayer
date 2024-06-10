package ma.game.tictactoeserver.Interfaces;

import ma.game.tictactoeserver.Objects.Game;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGames extends Remote {
    Game createGame(String username);
    void removeGame(Game game);
    ArrayList<Game> getGames();
}
