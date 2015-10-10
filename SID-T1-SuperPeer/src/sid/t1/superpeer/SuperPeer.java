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
    
    
    /************************************************
    ** Implementar funções aqui *********************
    ** Definições na Interface **********************
    ************************************************/
    
    public SuperPeer() throws RemoteException {}
    
    @Override
    public String testIface() throws RemoteException{
        System.out.println("called");
        return "TESTE 1";
    }
    
    public static void main(String[] args) {
        
        try { LocateRegistry.createRegistry(SuperPeer.port); } catch (RemoteException ex) { }
        
        try {
            
            SuperPeer obj = new SuperPeer();
            int size = Naming.list("//localhost:"+String.valueOf(SuperPeer.port)+"/").length;            
            Naming.rebind("//localhost:"+String.valueOf(SuperPeer.port)+"/"+SuperPeer.baseName+String.valueOf(size+1), obj);
            
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(SuperPeer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
