package ma.game.tictactoemultiplayer.Interfaces;

import ma.game.tictactoemultiplayer.Objects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPrivateChatService extends Remote {
    void sendPrivateMessage(Message message) throws RemoteException;
    List<Message> receivePrivateMessages(String sender, String receiver) throws RemoteException;
}
