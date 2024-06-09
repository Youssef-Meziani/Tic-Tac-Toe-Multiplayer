package ma.game.tictactoeserver.Interfaces;

import ma.game.tictactoeserver.Objects.Message;
import ma.game.tictactoeserver.Objects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserService extends Remote {
    void registerUser(User user) throws RemoteException;

    boolean loginUser(String username, String password) throws RemoteException;
    String findOpponent(String username) throws RemoteException;
    void broadcastMessage(Message message) throws RemoteException;
}
