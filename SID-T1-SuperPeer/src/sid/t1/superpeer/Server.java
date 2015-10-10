package sid.t1.superpeer;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Definição número das portas
3000 = Interna peers
20xx = PeerX para clientes
21xx = Client registry
*/


public class Server  {
    
    public static void main(String[] args) {
        
        try {
            SuperPeer superPeer = new SuperPeer();
            superPeer.init();
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
