package m2dl.pcr.rmi.server;

import m2dl.pcr.rmi.Message;
import m2dl.pcr.rmi.interfaces.IClient;
import m2dl.pcr.rmi.interfaces.IMessagerie;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MessagerieImpl extends UnicastRemoteObject implements IMessagerie {

    protected MessagerieImpl() throws RemoteException {
    }

    private final List<Message> listDesMessages = new ArrayList<>();
    public static List<IClient> listClient = new ArrayList<>();


    @Override
    public void sendMessage(Message message) {
        System.out.println("Message reçu ! "+ message);
        listDesMessages.add(message);
        notifyNewMessage(message);
    }

    private void notifyNewMessage(Message message) {
        for(IClient cli : listClient) {
            try {
                cli.notifyNewMessage(message);
            } catch (RemoteException e) {
                System.err.println("Impossible de notifier le client");
            }
        }
    }

    @Override
    public List<Message> getMessages() {
        System.out.println("La liste des messages a été demandée");
        return listDesMessages;
    }

    @Override
    public void connect(IClient client) throws RemoteException {
        listClient.add(client);
    }

    @Override
    public void disconnect(IClient id) throws RemoteException {
    }
}
