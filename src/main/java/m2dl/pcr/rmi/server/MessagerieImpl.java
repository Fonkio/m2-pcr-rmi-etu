package m2dl.pcr.rmi.server;

import m2dl.pcr.rmi.Message;
import m2dl.pcr.rmi.interfaces.IClient;
import m2dl.pcr.rmi.interfaces.IMessagerie;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagerieImpl extends UnicastRemoteObject implements IMessagerie {

    protected MessagerieImpl() throws RemoteException {
    }

    private final Map<String, List<Message>> channelMessageMap = new HashMap<>();
    public static Map<String, List<IClient>> channelClientMap = new HashMap<>();
    public static Map<String, IClient> pseudoClientMap = new HashMap<>();


    @Override
    public void sendMessage(Message message, String channel) {
        System.out.println("Message reçu dans le channel "+channel+"! "+ message);
        if (!channelMessageMap.containsKey(channel)) {
            channelMessageMap.put(channel, new ArrayList<>());
        }
        channelMessageMap.get(channel).add(message);
        notifyNewMessage(message, channel);
    }

    private void notifyNewMessage(Message message, String channel) {
        for(IClient cli : channelClientMap.get(channel)) {
            try {
                cli.notifyNewMessage(message);
            } catch (RemoteException e) {
                System.err.println("Impossible de notifier le client");
            }
        }
    }

    @Override
    public List<Message> getMessages(String channel) {
        System.out.println("La liste des messages du channel "+ channel +" a été demandée");
        if (!channelMessageMap.containsKey(channel)) {
            channelMessageMap.put(channel, new ArrayList<>());
        }
        return channelMessageMap.get(channel);
    }

    @Override
    public void connect(IClient client, String channel) throws RemoteException {
        if (!channelClientMap.containsKey(channel)) {
            channelClientMap.put(channel, new ArrayList<>());
        }
        channelClientMap.get(channel).add(client);
    }

    @Override
    public void disconnect(IClient client, String channel) throws RemoteException {
        channelClientMap.get(channel).remove(client);
    }
}
