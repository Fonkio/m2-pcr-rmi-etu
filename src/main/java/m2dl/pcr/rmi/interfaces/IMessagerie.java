package m2dl.pcr.rmi.interfaces;

import m2dl.pcr.rmi.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IMessagerie extends Remote {
    void sendMessage (Message message) throws RemoteException;

    List<Message> getMessages() throws RemoteException;

    void connect(IClient id) throws RemoteException;

    void disconnect(IClient id) throws RemoteException;
}
