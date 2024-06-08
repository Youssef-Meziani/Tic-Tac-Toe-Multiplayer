package ma.game.tictactoemultiplayer.Interfaces;

import ma.game.tictactoemultiplayer.Objects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGlobalChatService extends Remote {
    void sendGlobalMessage(Message message) throws RemoteException;
    List<Message> receiveGlobalMessages() throws RemoteException;
}