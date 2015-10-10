package sid.t1.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sid.t1.pkginterface.SuperPeerInterface;


public class Client {

    /**
     * @param args the command line arguments
     */
    static SuperPeerInterface obj;
    static String s = "";
    public static void main(String[] args) {
       
        try {
            obj = (SuperPeerInterface) Naming.lookup("//localhost:2020/TESTE1");
            s = obj.testIface();
            System.out.println("Server: " + s);
            
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
