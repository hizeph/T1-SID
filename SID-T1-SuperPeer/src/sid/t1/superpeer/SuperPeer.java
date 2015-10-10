package sid.t1.superpeer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sid.t1.pkginterface.ClientInterface;
import sid.t1.pkginterface.SuperPeerInterface;

public class SuperPeer extends UnicastRemoteObject implements SuperPeerInterface {

    ArrayList<ClientInterface> clientList;
    int offset = 0; // PeerNumber
    int clientsPort; // Port to lookup for clients
    String peerOnlyURL; // URL to lookup for other peers
    
    /**
    ***********************************************
    ** Implementar funções aqui *******************
    ** Definições na Interface ********************
    ***********************************************
    */
    @Override
    public void sendMessageClient(String client, String message, String source) throws RemoteException {

        try {
            ClientInterface ci = (ClientInterface) Naming.lookup("//localhost:" + clientsPort + "/" + client);
            ci.deliverMessage(client, message, source);
        } catch (NotBoundException ex) {
            
            try {
                String str[] = Naming.list(peerOnlyURL);
                for (String s : str){
                    SuperPeerInterface peer = (SuperPeerInterface) Naming.lookup(s);
                    peer.sendMesssagePeer(client, message, source);
                }
                 
            } catch (MalformedURLException | NotBoundException ex1) {
                Logger.getLogger(SuperPeer.class.getName()).log(Level.SEVERE, null, ex1);
            }
           
        } catch (MalformedURLException ex) {
            Logger.getLogger(SuperPeer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void sendMesssagePeer(String client, String message, String source) throws RemoteException {
        try {
            
            ClientInterface ci = (ClientInterface) Naming.lookup("//localhost:" + clientsPort + "/" + client);
            ci.deliverMessage(client, message, source);
            
        } catch (NotBoundException | MalformedURLException ex) {
            
        }
    }
    
    public SuperPeer() throws RemoteException {
        
    }
    
    public void init() {
        clientList = new ArrayList<>();
        
        boolean cont = true;
        do {
            try {                
                LocateRegistry.createRegistry(SuperPeer.port + offset);
                cont = false;
            } catch (RemoteException ex) {
                offset++;
            }
        } while (cont);
        
        try {
            //Client registry (21xx)
            //offset 1 port 2101
            //Max offset 99  port 2199
            clientsPort = ClientInterface.port + offset;
            LocateRegistry.createRegistry(clientsPort);

            // PeerX to ClientYY
            SuperPeer obj = new SuperPeer();            
            Naming.rebind("//localhost:" + String.valueOf(SuperPeer.port + offset) + "/" + SuperPeer.baseName + String.valueOf(offset), obj);
            System.out.println("//localhost:" + String.valueOf(SuperPeer.port + offset) + "/" + SuperPeer.baseName + String.valueOf(offset));

            //Mais um registry exclusivo para peers
            //Isso pra poder encaminhar as mensagens com Naming.list ao inves de salvar numa lista a parte
            //Port 3000
            peerOnlyURL = "//localhost:" + String.valueOf(SuperPeer.peerPort) + "/";
            Naming.rebind(peerOnlyURL + SuperPeer.baseName + String.valueOf(offset), obj);
            System.out.println("//localhost:" + String.valueOf(SuperPeer.peerPort) + "/" + SuperPeer.baseName + String.valueOf(offset));
            
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
