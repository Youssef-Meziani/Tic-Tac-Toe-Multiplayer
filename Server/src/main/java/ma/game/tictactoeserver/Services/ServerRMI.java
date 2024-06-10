package ma.game.tictactoeserver.Services;
import ma.game.tictactoeserver.Interfaces.*;
import ma.game.tictactoeserver.Objects.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI {
    private static Registry registry;
    private static final int port = 2002;

    public static boolean start() {
        try {
            IUserService userService = new UserService();
            IGames games = new Games();
            IOnlinePlayers onlinePlayers = new OnlinePlayers();
            IGlobalChatService globalChatService = new GlobalChatService();
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("Games", games);
            registry.rebind("UserService", userService);
            registry.rebind("OnlinePlayers", onlinePlayers);
            registry.rebind("GlobalChatService", globalChatService);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean stop() {
        try {
            if (registry != null) {
                UnicastRemoteObject.unexportObject(registry, true);
            }
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }
}
