package rmicallback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
  void setClient(Client client) throws RemoteException;
  void someService(String msg) throws RemoteException;
  int someOtherService(int value) throws RemoteException;
}