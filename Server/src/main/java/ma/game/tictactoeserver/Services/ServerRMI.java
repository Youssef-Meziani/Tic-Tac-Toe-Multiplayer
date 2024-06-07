package ma.game.tictactoeserver.Services;
import ma.game.tictactoeserver.Objects.OnlinePlayers;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class ServerRMI {
    private static Registry registry;
    private static final int port = 2002;

    public static boolean start() {
        try {
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("OnlinePlayers", new OnlinePlayers());
            return true;
        } catch (RemoteException e) {
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
