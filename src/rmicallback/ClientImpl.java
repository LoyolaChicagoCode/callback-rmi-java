package rmicallback;

import java.rmi.*;
import java.rmi.server.*;

/**
 * A simple client.  To run, first start the server as described in its javadoc.
 * Then run instances of this client from the top-level project directory like so:
 * <pre>
 * java -classpath classes -Djava.rmi.server.codebase=file:$PWD/classes/ -Djava.security.policy=policy rmicallback.ClientImpl host
 * </pre>
 * <em>Be sure to include the slash after "classes"!</em>
 * On Windows, replace <code>$PWD</code> by the full path of the top-level project directory.
 * Alternatively, use the <code>client.bat</code> script to start the client.
 * The optional <code>host</code> argument indicates the name or IP number of
 * the host on which the <em>server</em> runs.  If it is omitted, <code>localhost</code>
 * will be used.
 * In JBuilder, make sure the properties of this source file are set as follows:
 * <pre>
 * rmicallback/ClientImpl.java - right-click - Properties
 *   [ x ] generate RMI stub/skeleton
 *   options: -v1.2
 * </pre>
 */

public class ClientImpl extends UnicastRemoteObject implements Client {

  public static void main(String[] args) throws Exception {
    if (System.getSecurityManager() == null) {
        System.setSecurityManager(new RMISecurityManager());
    }
    String name = "//" + ( args.length == 0 ? "localhost" : args[0]) + "/myserver";
    Server server = (Server) Naming.lookup(name);
    System.out.println("Found server at " + name);
    new ClientImpl().doStuff(server);
    System.exit(0);
  }

  public ClientImpl() throws RemoteException { }

  public void doStuff(Server server) throws RemoteException {
    server.setClient(this);
    server.someService("Hello there!");
    System.out.println("2 * 7 computed remotely is " + server.someOtherService(7));
  }

  public void someCallback(int arg) {
    System.out.println("someCallback " + arg);
  }

  public void someOtherCallback(String arg) {
    System.out.println("someOtherCallback " + arg);
  }
}