package sid.t1.superpeer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import sid.t1.pkginterface.ClientInterface;
import sid.t1.pkginterface.SuperPeerInterface;

public class SuperPeer extends UnicastRemoteObject implements SuperPeerInterface {
    
    Server userInterface;

    static int offset = 0; // PeerNumber
    static int clientsPort; // Port to lookup for clients
    static String peerOnlyURL; // URL to lookup for other peers
    
    @Override
    public boolean sendMessageClient(String client, String message, String source) throws RemoteException {
        if(message != null) {
            userInterface.println("[ENVIANDO] " + source + " -> " + client + ": " + message );
        }
        else {
            userInterface.println("[CHECANDO STATUS] -> " + client );
        }

        System.out.println(getClientsPort());
        try {
            ClientInterface ci = (ClientInterface) Naming.lookup("//localhost:" + getClientsPort() + "/" + client);
            ci.deliverMessage(client, message, source);
            return true;
        } catch (NotBoundException ex) {
            
            try {
                String str[] = Naming.list(peerOnlyURL);
                for (String s : str){
                    SuperPeerInterface peer = (SuperPeerInterface) Naming.lookup(s);
                    if (peer.sendMessagePeer(client, message, source)){
                        return true;
                    }
                }
                 
            } catch (MalformedURLException | NotBoundException ex1) {
                Logger.getLogger(SuperPeer.class.getName()).log(Level.SEVERE, null, ex1);
            }
           
        } catch (MalformedURLException ex) {
            Logger.getLogger(SuperPeer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean sendMessagePeer(String client, String message, String source) throws RemoteException {
        try {
            ClientInterface ci = (ClientInterface) Naming.lookup("//localhost:" + clientsPort + "/" + client);
            ci.deliverMessage(client, message, source);
            return true;
            
        } catch (NotBoundException | MalformedURLException ex) {
            return false;
        }
    }
    
    public SuperPeer( Server userInterface ) throws RemoteException {
        this.userInterface = userInterface;
    }
    
    public int getClientsPort(){
        return clientsPort;
    }
    
    // [BASTOS] coloquei o init() aqui pra ser chamado pelo botão 'Iniciar'
    public void start() {

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
            SuperPeer obj = new SuperPeer( userInterface );
            Naming.rebind("//localhost:" + String.valueOf(SuperPeer.port + offset) + "/" + SuperPeer.baseName + String.valueOf(offset), obj);
            userInterface.println("//localhost:" + String.valueOf(SuperPeer.port + offset) + "/" + SuperPeer.baseName + String.valueOf(offset));

            //Mais um registry exclusivo para peers
            //Isso pra poder encaminhar as mensagens com Naming.list ao inves de salvar numa lista a parte
            //Port 3000
            try { LocateRegistry.createRegistry(SuperPeer.peerPort); } catch (RemoteException ex) { }
            peerOnlyURL = "//localhost:" + String.valueOf(SuperPeer.peerPort) + "/";

            Naming.rebind(peerOnlyURL + SuperPeer.baseName + String.valueOf(offset), obj);

            
            /*[BASTOS] Não funciona aqui se eu tirar o comment, não cheguei a testar muito. */
            //Naming.rebind(peerOnlyURL + SuperPeer.baseName + String.valueOf(offset), obj);

            userInterface.println("//localhost:" + String.valueOf(SuperPeer.peerPort) + "/" + SuperPeer.baseName + String.valueOf(offset));
            userInterface.println("Servidor iniciado.");
            userInterface.setStarted();
            
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(clientsPort);
    }
    
    // BUG: unbind não remove o Registry, então o offset volta pro último valor
    // antes de parar o servidor.
    public void stop() {
        try
        {
            String fullName = "//localhost:" + String.valueOf(SuperPeer.port + offset) + "/" + SuperPeer.baseName + String.valueOf(offset);
            Naming.unbind( fullName );
            offset = 0;
            userInterface.println("Servidor terminado.");
            userInterface.setStopped();
        }
        catch (NotBoundException | RemoteException | MalformedURLException ex) {
            Logger.getLogger(SuperPeer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
