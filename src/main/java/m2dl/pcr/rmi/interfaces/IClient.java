package m2dl.pcr.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    void notifyNewMessage() throws RemoteException;
}
