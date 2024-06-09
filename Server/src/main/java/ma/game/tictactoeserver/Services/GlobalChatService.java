package ma.game.tictactoeserver.Services;

import ma.game.tictactoeserver.Interfaces.IGlobalChatService;
import ma.game.tictactoeserver.Objects.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class GlobalChatService extends UnicastRemoteObject implements IGlobalChatService {
    private List<Message> globalMessages = new ArrayList<>();

    protected GlobalChatService() throws RemoteException {
        super();
    }

    @Override
    public void sendGlobalMessage(Message message) throws RemoteException {
        globalMessages.add(message);
        System.out.println(message.getContent());
    }

    @Override
    public List<Message> receiveGlobalMessages() throws RemoteException {
        return globalMessages;
    }
}
