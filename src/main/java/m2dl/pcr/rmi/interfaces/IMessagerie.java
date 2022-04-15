package m2dl.pcr.rmi.interfaces;

import m2dl.pcr.rmi.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IMessagerie extends Remote {
    void sendMessage (Message message, String channel) throws RemoteException;

    List<Message> getMessages(String channel) throws RemoteException;

    void connect(IClient id, String channel) throws RemoteException;

    void disconnect(IClient id, String channel) throws RemoteException;
}
