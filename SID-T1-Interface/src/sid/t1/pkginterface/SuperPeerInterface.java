
package sid.t1.pkginterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
Definição número das portas
3000 = Interna peers
20xx = PeerX para clientes
21xx = Client registry
*/


public interface SuperPeerInterface extends Remote {
    
    public static final int peerPort = 3000;
    public static final int port = 2000;
    public static final String baseName = "Peer";
    
    /**
    * @param client Destination client name
    * @param message Message to deliver
    * @param source Client who wants to send a message
    * @return True if message has been delivered
    * @throws java.rmi.RemoteException
    */
    public boolean sendMessageClient(String client, String message, String source) throws RemoteException;
    
    /**
    * @param client Destination client name
    * @param message Message to deliver
    * @param source Client who wants to send a message
    * @return True if message has been delivered
    * @throws java.rmi.RemoteException
    */
    public boolean sendMessagePeer(String client, String message, String source) throws RemoteException;
}
