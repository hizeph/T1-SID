package sid.t1.client;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
Definição número das portas
3000 = Interna peers
20xx = PeerX para clientes
21xx = Client registry
*/

public class Client extends javax.swing.JFrame {
    
    Control control;

    /**
     * Creates new form NewJFrame
     */
    public Client() {
        initComponents();
        try {
            control = new Control( this );
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        setDisconnected();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        clientPanel = new javax.swing.JPanel();
        buttonSend = new javax.swing.JButton();
        labelClientID = new javax.swing.JLabel();
        tfieldClientID = new javax.swing.JTextField();
        buttonConnect = new javax.swing.JButton();
        buttonDisconnect = new javax.swing.JButton();
        spClientOutput = new javax.swing.JScrollPane();
        tareaOutput = new javax.swing.JTextArea();
        labelReceiverID = new javax.swing.JLabel();
        tfieldReceiverID = new javax.swing.JTextField();
        separator1Client = new javax.swing.JSeparator();
        labelMessage = new javax.swing.JLabel();
        tfieldMessage = new javax.swing.JTextField();
        labelOutput = new javax.swing.JLabel();
        buttonOutputClear = new javax.swing.JButton();
        separator2Client = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlistFriends = new javax.swing.JList();
        labelFriends = new javax.swing.JLabel();
        buttonAdd = new javax.swing.JButton();
        buttonRemove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonSend.setText("Enviar");
        buttonSend.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonSendActionPerformed(evt);
            }
        });

        labelClientID.setText("ID do Cliente:");

        tfieldClientID.setText("Paulo");

        buttonConnect.setText("Conectar");
        buttonConnect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonConnectActionPerformed(evt);
            }
        });

        buttonDisconnect.setText("Desconectar");
        buttonDisconnect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonDisconnectActionPerformed(evt);
            }
        });

        tareaOutput.setEditable(false);
        tareaOutput.setColumns(20);
        tareaOutput.setRows(5);
        ( ( javax.swing.text.DefaultCaret ) this.tareaOutput.getCaret() ).setUpdatePolicy( javax.swing.text.DefaultCaret.ALWAYS_UPDATE );
        spClientOutput.setViewportView(tareaOutput);

        labelReceiverID.setText("ID do Receptor:");

        tfieldReceiverID.setText("Paulo");

        labelMessage.setText("Mensagem:");

        tfieldMessage.setText("TestMessage");

        labelOutput.setText("Saída:");

        buttonOutputClear.setText("Limpar");
        buttonOutputClear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonOutputClearActionPerformed(evt);
            }
        });

        jlistFriends.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jlistFriends);

        labelFriends.setText("Amigos:");

        buttonAdd.setText("Adicionar");
        buttonAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonAddActionPerformed(evt);
            }
        });

        buttonRemove.setText("Remover");
        buttonRemove.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clientPanelLayout = new javax.swing.GroupLayout(clientPanel);
        clientPanel.setLayout(clientPanelLayout);
        clientPanelLayout.setHorizontalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addComponent(labelClientID)
                        .addGap(21, 21, 21)
                        .addComponent(tfieldClientID, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonConnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDisconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelReceiverID)
                            .addComponent(labelMessage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clientPanelLayout.createSequentialGroup()
                                .addComponent(tfieldMessage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfieldReceiverID)))
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addComponent(labelOutput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonOutputClear, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spClientOutput)
                    .addComponent(separator1Client)
                    .addComponent(separator2Client))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFriends)
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addComponent(buttonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemove)))
                .addContainerGap())
        );
        clientPanelLayout.setVerticalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelClientID)
                    .addComponent(tfieldClientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonConnect)
                    .addComponent(buttonDisconnect)
                    .addComponent(labelFriends))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addComponent(separator1Client, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelReceiverID)
                            .addComponent(tfieldReceiverID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMessage)
                            .addComponent(tfieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSend))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separator2Client, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelOutput)
                            .addComponent(buttonOutputClear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spClientOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAdd)
                            .addComponent(buttonRemove))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clientPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clientPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
       control.sendMessage();
    }//GEN-LAST:event_buttonSendActionPerformed

    private void buttonConnectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonConnectActionPerformed
    {//GEN-HEADEREND:event_buttonConnectActionPerformed
        control.connect();
    }//GEN-LAST:event_buttonConnectActionPerformed

    private void buttonDisconnectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonDisconnectActionPerformed
    {//GEN-HEADEREND:event_buttonDisconnectActionPerformed
        control.disconnect();
    }//GEN-LAST:event_buttonDisconnectActionPerformed

    private void buttonOutputClearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonOutputClearActionPerformed
    {//GEN-HEADEREND:event_buttonOutputClearActionPerformed
        tareaOutput.setText( "" );
    }//GEN-LAST:event_buttonOutputClearActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonAddActionPerformed
    {//GEN-HEADEREND:event_buttonAddActionPerformed
        String name = JOptionPane.showInputDialog("Digite o ID do seu amigo:");
        if(name != null && !name.isEmpty()) {
            control.addFriend(name);
        }
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonRemoveActionPerformed
    {//GEN-HEADEREND:event_buttonRemoveActionPerformed
        control.removeFriend();
    }//GEN-LAST:event_buttonRemoveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
                
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonConnect;
    private javax.swing.JButton buttonDisconnect;
    private javax.swing.JButton buttonOutputClear;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonSend;
    private javax.swing.JPanel clientPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList jlistFriends;
    private javax.swing.JLabel labelClientID;
    private javax.swing.JLabel labelFriends;
    private javax.swing.JLabel labelMessage;
    private javax.swing.JLabel labelOutput;
    private javax.swing.JLabel labelReceiverID;
    private javax.swing.JSeparator separator1Client;
    private javax.swing.JSeparator separator2Client;
    private javax.swing.JScrollPane spClientOutput;
    private javax.swing.JTextArea tareaOutput;
    private javax.swing.JTextField tfieldClientID;
    private javax.swing.JTextField tfieldMessage;
    private javax.swing.JTextField tfieldReceiverID;
    // End of variables declaration//GEN-END:variables
    
    // <editor-fold defaultstate="collapsed" desc=" GETs / SETs ">
    public javax.swing.JTextField getClientID()
    {
        return tfieldClientID;
    }
    
    public javax.swing.JTextField getReceiverID()
    {
        return tfieldReceiverID;
    }
    
    public javax.swing.JTextField getMessage()
    {
        return tfieldMessage;
    }
    
    public javax.swing.JTextArea getOutput()
    {
        return tareaOutput;
    }
    
    public javax.swing.JList getFriends()
    {
        return jlistFriends;
    }
    //</editor-fold>
    
    public void setConnected()
    {
        tfieldClientID.setEnabled( false );
        buttonConnect.setEnabled( false );
        buttonDisconnect.setEnabled( true );
        tfieldReceiverID.setEnabled( true );
        tfieldMessage.setEnabled( true );
        buttonSend.setEnabled( true );
        jlistFriends.setEnabled( true );
        buttonAdd.setEnabled( true);
        buttonRemove.setEnabled( true );
    }
    
    public void setDisconnected()
    {
        tfieldClientID.setEnabled( true );
        buttonConnect.setEnabled( true );
        buttonDisconnect.setEnabled( false );
        tfieldReceiverID.setEnabled( false );
        tfieldMessage.setEnabled( false );
        buttonSend.setEnabled( false );
        jlistFriends.setEnabled( false );
        buttonAdd.setEnabled( false );
        buttonRemove.setEnabled( false );
    }
    
    // Imprime na área de texto da interface.
    public void println( String message )
    {
        tareaOutput.append( "> " + message + "\n" );
    }
    
    // Mostra uma MessageBox com uma mensagem de erro.
    public void showError( String message )
    {
        javax.swing.JOptionPane.showMessageDialog( this, message, "Error", javax.swing.JOptionPane.ERROR_MESSAGE );
    }
}
