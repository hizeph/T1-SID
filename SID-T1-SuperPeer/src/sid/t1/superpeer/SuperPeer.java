package sid.t1.superpeer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import sid.t1.pkginterface.SuperPeerInterface;


public class SuperPeer extends UnicastRemoteObject implements SuperPeerInterface {
    
    public SuperPeer() throws RemoteException { 
    }
    
    /************************************************
    ** Implementar funções aqui *********************
    ** Definições na Interface **********************
    ************************************************/
    
    public String testIface() throws RemoteException{
        System.out.println("called");
        return "TESTE 1";
    }
    
      public static void main(String[] args) {
        try {
            
            LocateRegistry.createRegistry(2020);
            SuperPeer obj = new SuperPeer();
            Naming.rebind("//localhost:2020/TESTE1", obj);
            
        } catch (RemoteException ex) {
            Logger.getLogger(SuperPeer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SuperPeer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
