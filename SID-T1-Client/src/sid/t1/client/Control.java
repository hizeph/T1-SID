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
    Client userInterface;
    static SuperPeerInterface superPeer;
    String endName;
    int endNumber;
    String fullName;
    
    @Override
    public void deliverMessage(String client, String message, String source) throws RemoteException {
        userInterface.println("De '" + source + "' Para '" + client + "': " + message );
    }
    
    public Control( Client userInterface ) throws RemoteException {
        this.userInterface = userInterface;
    }

    // [BASTOS] coloquei o init() aqui pra ser chamado pelo botão 'Conectar'
    public void connect() {
        try {
            // Cada instância de client precisa de um nome fixo
            // por exemplo ClientPaulo e ClientPedro
            // pois se deixar automático vai ter um Cliente1 para cada peer
            // pode ser escolher na hora que da play, como um login
            // nao precisa ter como contato, só saber o nome na hora de mandar, mas é detalhe isso
            
            // Se registra no Registry de porta 21xx que será lida pelo PeerXX
            endName = userInterface.getClientID().getText();
            //Se mudar pra 1 funciona se tiver 2 Peer rodando
            endNumber = 0;
            String serverFullName = "//localhost:" + String.valueOf(SuperPeerInterface.port + endNumber) + "/" + SuperPeerInterface.baseName + endNumber;
            superPeer = (SuperPeerInterface) Naming.lookup(serverFullName);
            
            fullName = "//localhost:" + String.valueOf(ClientInterface.port + endNumber) + "/" + ClientInterface.baseName + endName;
            Naming.rebind(fullName, this);
            userInterface.println(fullName);
            userInterface.println(endName + " conectado.");
            userInterface.setConnected();
        } catch (NotBoundException | RemoteException | MalformedURLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect() {
        try {
            fullName = "//localhost:" + String.valueOf(ClientInterface.port + endNumber) + "/" + ClientInterface.baseName + endName;
            Naming.unbind( fullName );
            userInterface.println(endName + " desconectado.");
            userInterface.setDisconnected();
        }
        catch (NotBoundException | RemoteException | MalformedURLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(){
        try {
            String clientName = ClientInterface.baseName + endName;
            String message = userInterface.getMessage().getText();
            String receiver = ClientInterface.baseName + userInterface.getReceiverID().getText();
            
            superPeer.sendMessageClient(receiver, message, clientName);
            
            //userInterface.getMessage().setText( "" ); // LIMPA A CAIXA DA MENSAGEM DEPOIS DE ENVIAR
            
        } catch (RemoteException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
