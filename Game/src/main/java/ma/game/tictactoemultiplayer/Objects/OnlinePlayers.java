package ma.game.tictactoemultiplayer.Objects;

import ma.game.tictactoeserver.Interfaces.IOnlinePlayers;

import java.io.Serializable;

public class OnlinePlayers implements Serializable, IOnlinePlayers {
    public static Long count = 0L;
}
