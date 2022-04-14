package m2dl.pcr.rmi.client;

import m2dl.pcr.rmi.interfaces.IClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements IClient {

    protected ClientImpl() throws RemoteException {
    }

    @Override
    public void notifyNewMessage() throws RemoteException {
        AppClient.printMessages();
    }
}
