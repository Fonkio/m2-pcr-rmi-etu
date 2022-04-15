package m2dl.pcr.rmi.interfaces;

import m2dl.pcr.rmi.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    void notifyNewMessage(Message message) throws RemoteException;
}
