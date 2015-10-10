package sid.t1.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import sid.t1.pkginterface.ClientInterface;
import sid.t1.pkginterface.SuperPeerInterface;

/*
Definição número das portas
3000 = Interna peers
20xx = PeerX para clientes
21xx = Client registry
*/

public class Control extends UnicastRemoteObject implements ClientInterface {
    static SuperPeerInterface superPeer;
    String endName;
    int endNumber;
    String fullName;
    
    @Override
    public void deliverMessage(String client, String message, String source) throws RemoteException {
         
    }
    
    public Control() throws RemoteException {
        try {
            
            // Modificar endNumber pra conectar num peer random ou sei la
            // endName tem que ser o login, "Paulo" por exemplo
            endName = "Paulo";
            endNumber = 0;
            superPeer = (SuperPeerInterface) Naming.lookup("//localhost:"+String.valueOf(SuperPeerInterface.port + endNumber)+"/"+SuperPeerInterface.baseName + endName);
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init() {
        try {
            // Cada instância de client precisa de um nome fixo
            // por exemplo ClientPaulo e ClientPedro
            // pois se deixar automático vai ter um Cliente1 para cada peer
            // pode ser escolher na hora que da play, como um login
            // nao precisa ter como contato, só saber o nome na hora de mandar, mas é detalhe isso
            
            // Se registra no Registry de porta 21xx que será lida pelo PeerXX
            fullName = "//localhost:"+String.valueOf(ClientInterface.port + endNumber)+"/"+ClientInterface.baseName + endName;
            Naming.rebind(fullName, this);
            System.out.println(fullName);   
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(){
        try {
            
            superPeer.sendMessageClient("ClientPaulo", "TestMessage", ClientInterface.baseName + endName);
            
        } catch (RemoteException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
