package ma.game.tictactoeserver.Objects;

import ma.game.tictactoeserver.Interfaces.IGames;
import java.io.Serializable;
import java.util.ArrayList;

public class Games implements IGames, Serializable {
    private ArrayList<Game> games;

    public Games() {
        games = new ArrayList<>();
    }

    @Override
    public synchronized Game createGame(String username) {
        Game game = new Game();
        game.registerPlayer(username);
        games.add(game);
        return game;
    }

    @Override
    public synchronized void removeGame(Game game) {
        games.remove(game);
    }
    @Override
    public synchronized ArrayList<Game> getGames(){
        return games;
    }

//    @Override
//    public synchronized Game getGame(Game game){
//        return games.;
//    }
}
