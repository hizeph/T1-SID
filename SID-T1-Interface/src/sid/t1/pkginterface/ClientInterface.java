
package sid.t1.pkginterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
Definição número das portas
3000 = Interna peers
20xx = PeerX para clientes
21xx = Client registry
*/

public interface ClientInterface extends Remote {
    
    public static final int port = 2100;
    public static final String baseName = "Client";
    
    /**
    * @param client Destination client name
    * @param message Message to deliver
    * @param source Client who sent message
    * @throws java.rmi.RemoteException
    */
    public void deliverMessage(String client, String message, String source) throws RemoteException;
    
}
