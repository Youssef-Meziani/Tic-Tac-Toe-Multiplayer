package ma.game.tictactoeserver.Objects;

import ma.game.tictactoeserver.Interfaces.IOnlinePlayers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OnlinePlayers extends UnicastRemoteObject implements IOnlinePlayers {
    private static int count = 0;

    public OnlinePlayers() throws RemoteException {
        super();
    }

    @Override
    public synchronized void incrementCount() throws RemoteException {
        count++;
        System.out.println(getCount());

    }

    @Override
    public synchronized void decrementCount() throws RemoteException {
        count--;
        System.out.println(getCount());
    }

    @Override
    public int getCount() throws RemoteException {
        return count;
    }
}
