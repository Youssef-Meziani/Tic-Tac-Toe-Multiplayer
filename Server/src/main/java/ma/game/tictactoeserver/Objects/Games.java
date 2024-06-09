package ma.game.tictactoeserver.Objects;

import ma.game.tictactoeserver.Interfaces.IGames;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Games implements IGames, Serializable {

    private ArrayList<Game> games;

    public Games() throws RemoteException {
        games = new ArrayList<>();
    }

    @Override
    public synchronized void createGame(Game game) throws RemoteException {
        games.add(game);

        System.out.println("haha");
        System.out.println(games);
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
