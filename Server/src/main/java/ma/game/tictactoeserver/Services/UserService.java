package ma.game.tictactoeserver.Services;

import javafx.beans.property.SimpleObjectProperty;
import ma.game.tictactoeserver.Interfaces.IUserService;
import ma.game.tictactoeserver.Objects.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService extends UnicastRemoteObject implements IUserService {
    private List<String> users;
    private SimpleObjectProperty<Map<String, String>> sessions = new SimpleObjectProperty<Map<String, String>>(this, "sessions", new HashMap<>());

    protected UserService() throws RemoteException {
        super();
        users = new ArrayList<>();
    }

    @Override
    public void registerUser(String username) throws RemoteException {
        if (!users.contains(username)) {
            users.add(username);
        }
    }

    @Override
    public boolean loginUser(String username) throws RemoteException {
        return users.contains(username);
    }

    @Override
    public String findOpponent(String username) throws RemoteException {
        for (String user : users) {
            if (!user.equals(username) && !sessions.get().containsKey(user)) {
                sessions.get().put(username, user);
                sessions.get().put(user, username);
                return user;
            }
        }
        return null;
    }

    @Override
    public void broadcastMessage(Message message) throws RemoteException {
        System.out.println("Broadcast message: " + message.getContent());
    }
}