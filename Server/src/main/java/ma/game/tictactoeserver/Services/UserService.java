package ma.game.tictactoeserver.Services;

import ma.game.tictactoeserver.Interfaces.IUserService;
import ma.game.tictactoeserver.Objects.Message;
import ma.game.tictactoeserver.Objects.OnlinePlayers;
import ma.game.tictactoeserver.Objects.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService extends UnicastRemoteObject implements IUserService {
    private List<User> users = new ArrayList<>();
    private Map<String, String> sessions = new HashMap<>();

    public UserService() throws RemoteException {
        super();
    }

    @Override
    public synchronized void registerUser(User user) throws RemoteException {
        if (users.stream().noneMatch(u -> u.getUsername().equals(user.getUsername()))) {
            users.add(user);
            System.out.println("User registered: " + user.getUsername());
        } else {
            throw new RemoteException("User already exists.");
        }
    }

    @Override
    public synchronized boolean loginUser(String username, String password) throws RemoteException {
         return users.stream().anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password));
    }

    @Override
    public String findOpponent(String username) throws RemoteException {
        for (User user : users) {
            if (!user.getUsername().equals(username) && !sessions.containsKey(user.getUsername())) {
                sessions.put(username, user.getUsername());
                sessions.put(user.getUsername(), username);
                return user.getUsername();
            }
        }
        return null;
    }

    @Override
    public void broadcastMessage(Message message) throws RemoteException {
        System.out.println("Broadcast message: " + message.getContent());
    }
}