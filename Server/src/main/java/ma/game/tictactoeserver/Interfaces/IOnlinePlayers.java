package ma.game.tictactoeserver.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOnlinePlayers extends Remote {
    void incrementCount() throws RemoteException;
    void decrementCount() throws RemoteException;
    int getCount() throws RemoteException;
}
