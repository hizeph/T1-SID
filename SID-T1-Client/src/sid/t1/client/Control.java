package sid.t1.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sid.t1.pkginterface.SuperPeerInterface;

public class Control {
    static SuperPeerInterface obj;
    
    public Control(){
        try {
            
            obj = (SuperPeerInterface) Naming.lookup("//localhost:2020/Peer1");
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        try {
            
            String s = obj.testIface();
            System.out.println("Server: " + s);
            
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
