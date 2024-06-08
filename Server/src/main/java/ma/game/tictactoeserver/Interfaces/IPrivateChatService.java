package ma.game.tictactoeserver.Interfaces;

import ma.game.tictactoeserver.Objects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPrivateChatService extends Remote {
    void sendPrivateMessage(Message message) throws RemoteException;
    List<Message> receivePrivateMessages(String sender, String receiver) throws RemoteException;
}
