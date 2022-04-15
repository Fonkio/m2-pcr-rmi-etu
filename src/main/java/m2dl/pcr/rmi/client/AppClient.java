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

            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrez votre nom :");
            String username = scanner.nextLine();
            System.out.println("Entrez le nom du channel :");
            String channel = scanner.nextLine();

            messagerie.connect(client, channel);

            registry.rebind(username, client);
            printMessages(channel);
            String messageStr = scanner.nextLine();
            while (!messageStr.equals("exit")) {
                Message messageToSend = new Message(username, messageStr);
                messagerie.sendMessage(messageToSend, channel);
                messageStr = scanner.nextLine();
            }
            messagerie.disconnect(client, channel);
            System.exit(0);

        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }

    public static void printMessages(String channel) {
        List<Message> messageList;
        try {
            messageList = messagerie.getMessages(channel);
            if (messageList.isEmpty()) {
                System.out.println("Aucun message");
            } else {
                for(Message message : messageList) {
                    System.out.println(message);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}