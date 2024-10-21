/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.tec.parquimetro.parquimetro.GUI;

import com.tec.parquimetro.parquimetro.Clases.Login;
import com.tec.parquimetro.parquimetro.Clases.Persona;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Dilan
 */
public class PanelOlvidePin extends javax.swing.JPanel {
    private LoginJFrame loginJFrame;
    //ATRIBUTOS PARA ENVIAR REPORTES
    private static String emailDe = "paquimetrocartago@gmail.com";
    private static String contraseñaDe = "vofx ztal oawe yary";
    private static String emailPara;
    
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    //FIN DE ATRIBUTOS DE REPORTES
    /**
     * Creates new form PanelOlvidePin
     */
    public PanelOlvidePin(LoginJFrame loginJFrame) {
        this.loginJFrame = loginJFrame;
        initComponents();
    }

    //FUNCION PARA CREAR EL MAIL
     public void crearEmail(String cuerpo, String asunto, String correo){
         //Protocolo para el envio de correos
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailDe);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

       mSession = Session.getDefaultInstance(mProperties);


        try {        
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailDe));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(correo)); //correo del usuario
            mCorreo.setSubject(asunto); //Asunto
            mCorreo.setText(cuerpo, "ISO-8859-1", "html");

        } catch (AddressException ex) {
            Logger.getLogger(MenuInspector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MenuInspector.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void enviarEmail(){
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailDe, contraseñaDe);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            JOptionPane.showMessageDialog(null, "Correo enviado");
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(MenuInspector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MenuInspector.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(57, 54, 66));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("RECUPERAR MI PIN");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Ingrese su identificacion registrada");

        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });

        btnEnviar.setText("Enviar correo de recuperación");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(268, 268, 268))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(441, 441, 441)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(btnEnviar)
                        .addGap(102, 102, 102)
                        .addComponent(btnCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel1)
                .addGap(176, 176, 176)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(166, 166, 166)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar)
                    .addComponent(btnCancelar))
                .addContainerGap(178, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        loginJFrame.getPanelOlvidePin().setVisible(false);
        loginJFrame.getLabelCrearCuenta().setVisible(true);
        loginJFrame.getBotonRegistrarse().setVisible(true);
        loginJFrame.getPanelLogin().setVisible(true);
        
        loginJFrame.getPanelVentana().revalidate();
        loginJFrame.getPanelVentana().repaint();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        if (txtIdentificacion.getText().equals("")){ //no escribio ninguna identificacion
            JOptionPane.showMessageDialog(null, "Debe de ingresar una identificación en su respectivo campo");
        }
        else{ //escribio la identificacion
            //revisar la identificacion
            if (MenuInspector.validacionIdentificacion(txtIdentificacion.getText())){ //si la identificacion fue bien escrita
                ArrayList<Persona> listaUsuarios = Login.cargarUsuarios("listaUsuarios.txt");
                for (Persona cadaPersona : listaUsuarios){ //buscamos todas las identificaciones
                    if(cadaPersona.getIdentificacion().equals(txtIdentificacion.getText())){
                        //crear nuevo pin
                        Random random = new Random(); //Generar un número aleatorio entre 1000 y 9999
                        int numeroAleatorio = 1000 + random.nextInt(9000);
                        cadaPersona.setPin(String.valueOf(numeroAleatorio));
                        //enviar correo
                        String cuerpo = "TU NUEVO PIN: " + String.valueOf(numeroAleatorio);
                        crearEmail(cuerpo, "CAMBIO DE PIN", cadaPersona.getCorreo().getCorreo());
                        enviarEmail();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "No se encontró un usuario con esa identificación");
            }
            else{
                JOptionPane.showMessageDialog(null, "La identificacion debe tener entre 2 a 25 caracteres!");
            }
        }
    }//GEN-LAST:event_btnEnviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtIdentificacion;
    // End of variables declaration//GEN-END:variables
}
