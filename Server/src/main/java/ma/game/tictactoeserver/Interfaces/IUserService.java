package ma.game.tictactoeserver.Interfaces;

import ma.game.tictactoeserver.Objects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserService extends Remote {
    void registerUser(String username) throws RemoteException;
    boolean loginUser(String username) throws RemoteException;
    String findOpponent(String username) throws RemoteException;
    void broadcastMessage(Message message) throws RemoteException;
}
