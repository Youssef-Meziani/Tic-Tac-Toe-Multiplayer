package ma.game.tictactoeserver.Services;
import ma.game.tictactoeserver.Interfaces.IGame;
import ma.game.tictactoeserver.Interfaces.IUserService;
import ma.game.tictactoeserver.Objects.Game;

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
            IGame game = new Game();
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("UserService", userService);
            registry.rebind("Game", game);
            System.out.println("UserService is bound to registry.");
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
