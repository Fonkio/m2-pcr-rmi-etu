package m2dl.pcr.rmi.server;
import m2dl.pcr.rmi.interfaces.IMessagerie;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class AppServer {

    public AppServer() {}

    public static Registry registry;

    public static void main(String[] args) {

        try {
            registry = LocateRegistry.createRegistry(1099);

            IMessagerie mssgrie = new MessagerieImpl();
            registry.rebind("Messagerie", mssgrie);

            System.out.println("Serveur de messagerie prêt !");
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }




}