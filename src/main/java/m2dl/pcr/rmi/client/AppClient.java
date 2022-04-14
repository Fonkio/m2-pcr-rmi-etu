package m2dl.pcr.rmi.client;

import m2dl.pcr.rmi.Message;
import m2dl.pcr.rmi.interfaces.IClient;
import m2dl.pcr.rmi.interfaces.IMessagerie;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class AppClient {

    private AppClient() {}

    private static IMessagerie messagerie;

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            messagerie = (IMessagerie) registry.lookup("messagerie");

            IClient client = new ClientImpl();
            messagerie.connect(client);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrez votre nom :");
            String id = scanner.nextLine();

            registry.rebind(id, client);
            printMessages();
            String messageStr = scanner.nextLine();
            while (!messageStr.equals("exit")) {
                Message messageToSend = new Message(id, messageStr);
                messagerie.sendMessage(messageToSend);
                messageStr = scanner.nextLine();
            }
            messagerie.disconnect(client);
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }

    public static void printMessages() {
        List<Message> messageList;
        try {
            messageList = messagerie.getMessages();
            if (messageList.isEmpty()) {
                System.out.println("Aucun message");
            } else {
                System.out.println("Liste des messages");
                for(Message message : messageList) {
                    System.out.println(message);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}