package ma.game.tictactoeserver.Services;
import ma.game.tictactoeserver.Interfaces.IUserService;

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
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("UserService", userService);
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
