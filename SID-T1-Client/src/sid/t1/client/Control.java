package sid.t1.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        userInterface.println("Recebido de " + source + ": " + message);
    }

    public Control(Client userInterface) throws RemoteException {
        this.userInterface = userInterface;
    }

    public void connect() {
        try {
            String peers[] = Naming.list("//localhost:" + SuperPeerInterface.peerPort + "/");
            Random rn = new Random();
            endNumber = rn.nextInt(peers.length);
            endName = userInterface.getClientID().getText();
            fullName = "//localhost:" + String.valueOf(ClientInterface.port + endNumber) + "/" + ClientInterface.baseName + endName;
        
            Naming.lookup(fullName);
            //already exists
            JOptionPane.showMessageDialog(userInterface, "Username Already Bound!");
        } catch (NotBoundException ex) {
            try {
                fullName = "//localhost:" + String.valueOf(ClientInterface.port + endNumber) + "/" + ClientInterface.baseName + endName;
                Naming.rebind(fullName, this);
                userInterface.println(fullName);
                userInterface.println(endName + " conectado.");

                
                String serverFullName = "//localhost:" + String.valueOf(SuperPeerInterface.port + endNumber) + "/" + SuperPeerInterface.baseName + endNumber;
                superPeer = (SuperPeerInterface) Naming.lookup(serverFullName);
                userInterface.println("Conectado ao peer: " + serverFullName);
                
                userInterface.setConnected();
                
            } catch (RemoteException | MalformedURLException | NotBoundException ex1) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            fullName = "//localhost:" + String.valueOf(ClientInterface.port + endNumber) + "/" + ClientInterface.baseName + endName;
            Naming.unbind(fullName);
            userInterface.println(endName + " desconectado.");
            userInterface.setDisconnected();
        } catch (NotBoundException | RemoteException | MalformedURLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage() {
        try {
            String clientName = ClientInterface.baseName + endName;
            String message = userInterface.getMessage().getText();
            String receiver = ClientInterface.baseName + userInterface.getReceiverID().getText();

            boolean delivered = superPeer.sendMessageClient(receiver, message, clientName);
            
            if (delivered){
                userInterface.println("Mensagem entregue para: " + receiver);
            } else {
                userInterface.println("Cliente " + receiver + " não disponível");
            }
            
            //userInterface.getMessage().setText( "" ); // LIMPA A CAIXA DA MENSAGEM DEPOIS DE ENVIAR
        } catch (RemoteException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
