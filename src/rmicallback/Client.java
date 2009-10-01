package rmicallback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
  void someCallback(int arg) throws RemoteException;
  void someOtherCallback(String arg) throws RemoteException;
}