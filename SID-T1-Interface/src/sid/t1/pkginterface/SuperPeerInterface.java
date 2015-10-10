
package sid.t1.pkginterface;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SuperPeerInterface extends Remote {
    
    public String testIface() throws RemoteException;
    
}
