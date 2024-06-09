package ma.game.tictactoeserver.Objects;

import ma.game.tictactoeserver.Interfaces.IGames;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Games extends UnicastRemoteObject implements IGames, Serializable {

    private ArrayList<Game> games;

    public Games() throws RemoteException {
        games = new ArrayList<>();
    }

    @Override
    public synchronized void createGame(Game game) throws RemoteException {
        games.add(game);
    }

    @Override
    public synchronized Game getGame(Game game) throws RemoteException {
        return games.getLast().getGame(game);
    }

    @Override
    public synchronized void removeGame(Game game) throws RemoteException {
        games.remove(game);
    }
    @Override
    public synchronized ArrayList<Game> getGames(){
        return games;
    }
}
