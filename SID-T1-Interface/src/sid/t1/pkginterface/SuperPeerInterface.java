
package sid.t1.pkginterface;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SuperPeerInterface extends Remote {
    
    public static final int port = 2020;
    public static final String baseName = "Peer";
    
    /**
    * @return a string for testing
    * @throws java.rmi.RemoteException
    */
    public String testIface() throws RemoteException;

    
}
