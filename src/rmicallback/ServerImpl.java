package rmicallback;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

/**
 * A simple server.  To run, start this server from the top-level project directory like so:
 * <pre>
 * java -classpath classes -Djava.rmi.server.codebase=file:$PWD/classes/ -Djava.security.policy=policy rmicallback.ServerImpl
 * </pre>
 * <em>Be sure to include the slash after "classes"!</em>
 * On Windows, replace <code>$PWD</code> by the full path of the top-level project directory.
 * Alternatively, use the <code>server.bat</code> script to start the server.
 * Use <code>Ctrl-C</code> to stop the server.
 * In JBuilder, make sure the properties of this source file are set as follows:
 * <pre>
 * rmicallback/ServerImpl.java - right-click - Properties
 *   [ x ] generate RMI stub/skeleton
 *   options: -v1.2
 * </pre>
 */

public class ServerImpl extends UnicastRemoteObject implements Server {

  public static void main(String[] args) throws Exception {
    if (System.getSecurityManager() == null) {
      System.setSecurityManager(new RMISecurityManager());
    }
    // start a local RMI registry
    LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    System.out.println("RMI registry created");
    String name = "//localhost/myserver";
    Server server = new ServerImpl();
    // register this server instance
    Naming.rebind(name, server);
    System.out.println("Server bound");
  }

  public ServerImpl() throws RemoteException { }

  public void setClient(Client client) throws RemoteException {
    System.out.println("setClient(" + client + ")");
    client.someCallback(7);
    client.someOtherCallback("hello");
  }

  public void someService(String msg) {
    System.out.println("someService(\"" + msg + "\")");
  }

  public int someOtherService(int value) {
    System.out.println("someOtherService(" + value + ")");
    return 2 * value;
  }
}

