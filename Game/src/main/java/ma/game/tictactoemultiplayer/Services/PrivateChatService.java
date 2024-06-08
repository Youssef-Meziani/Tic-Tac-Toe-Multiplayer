package ma.game.tictactoemultiplayer.Services;

import ma.game.tictactoeserver.Interfaces.IPrivateChatService;
import ma.game.tictactoeserver.Objects.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrivateChatService extends UnicastRemoteObject implements IPrivateChatService {
    private Map<String, List<Message>> privateMessages = new HashMap<>();

    protected PrivateChatService() throws RemoteException {
        super();
    }

    @Override
    public void sendPrivateMessage(Message message) throws RemoteException {
        String key = message.getSender() + ":" + message.getReceiver();
        privateMessages.putIfAbsent(key, new ArrayList<>());
        privateMessages.get(key).add(message);
        System.out.println("Private message from " + message.getSender() + " to " + message.getReceiver() + ": " + message.getContent());
    }

    @Override
    public List<Message> receivePrivateMessages(String sender, String receiver) throws RemoteException {
        String key = sender + ":" + receiver;
        return privateMessages.getOrDefault(key, new ArrayList<>());
    }
}