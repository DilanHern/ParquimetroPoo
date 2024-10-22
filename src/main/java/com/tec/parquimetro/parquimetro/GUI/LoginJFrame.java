/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.tec.parquimetro.parquimetro.GUI;

import com.tec.parquimetro.parquimetro.Clases.Administrador;
import com.tec.parquimetro.parquimetro.Clases.Inspector;
import com.tec.parquimetro.parquimetro.Clases.Login;
import static com.tec.parquimetro.parquimetro.Clases.Login.cargarUsuarios;
import com.tec.parquimetro.parquimetro.Clases.Parqueo;
import com.tec.parquimetro.parquimetro.Clases.Persona;
import com.tec.parquimetro.parquimetro.Clases.Usuario;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author carol_flgngfy
 */
public class LoginJFrame extends javax.swing.JFrame {
    private boolean MensajeErrorI = false; //booleano para saber si se puede mostrar el mensaje de error Identificacon
    private boolean MensajeErrorP = false; //booleano para saber si se puede mostrar el mensjae de error Pin
    private PanelRegistrarse panelRegistrarse;
    private PanelOlvidePin panelOlvidePin;
    /**
     * Creates new form LoginJFrame
     */
    
    //getter de botonCancelarDeRegistrarse
    public void getBotonCancelarDeRegistrarse(){
        botonCancelarDeRegistrarseActionPerformed(null);
    }
    
    //getter de panelVentana
    public javax.swing.JPanel getPanelVentana(){
        return panelVentana;
    }
    
    //getter de getLabelCrearCuenta
    public javax.swing.JLabel getLabelCrearCuenta(){
        return labelCrearCuenta;
    }
    
    //getter de getBotonRegistrarse
    public javax.swing.JButton getBotonRegistrarse(){
        return botonRegistrarse;
    }

    
    //getter de panelOlvidePin
    public javax.swing.JPanel getPanelOlvidePin(){
        return panelOlvidePin;
    }
    
    //getter de panelLogin
    public javax.swing.JPanel getPanelLogin(){
        return panelLogin;
    }
    //getter de existeMensajeErrorI
    public boolean getMensajeErrorI(){
        return MensajeErrorI;
    }
    //setter de existeMensajeErrorI
    public void setMensajeErrorI(boolean bool){
        MensajeErrorI = bool;
    }
    
    //getter de existeMensajeErrorP
    public boolean getMensajeErrorP(){
        return MensajeErrorP;
    }
    //setter de existeMensajeErrorI
    public void setMensajeErrorP(boolean bool){
        MensajeErrorP = bool;
    }
    
    //validarIdentPin(): Valida si el pin y la identificacion ingresada son correcta
    //retorna true si la identificacion y pin son validos, de lo contrario retorna false
    public boolean validarIdentPin(){
        //el pin debe de ser de 4 caracteres
        if (new String(campoPin.getPassword()).length() != 4){
            if (!getMensajeErrorI()) { //si no esta mostrando el error siga
                if (!getMensajeErrorI()){ //si no esta mostrando el error siga
                 labelErrorPin.setVisible(true);
                 setMensajeErrorP(true);
                }
            }
        }
        else { //el pin se escribio bien
            labelErrorPin.setVisible(false);
            setMensajeErrorP(false);
        }
        
        //identificacion debe de ser entre 2 y 25 caracteres
        if (campoIdentificacion.getText().length() < 2 || campoIdentificacion.getText().length() > 25) {
            if (!getMensajeErrorP()){ //si no se esta mostrando el error siga
                if (!getMensajeErrorI()){ //si no se esta mostrando el error siga
                    labelErrorIdentificacion.setVisible(true);
                    setMensajeErrorI(true);
                }
            }
        }
        else { //la identificacion se escribio bien
            labelErrorIdentificacion.setVisible(false);
            setMensajeErrorI(false);
        }
        
        //SE REALIZA UNA SEGUNDA VERIFICACION A PIN EN CASO DE QUE SE HAYA CAMBIADO LA IDENTIFICACION PERO EL PIN ESTÉ MALO
        if (new String(campoPin.getPassword()).length() != 4){
            if (!getMensajeErrorI()) { //si no esta mostrando el error siga
                if (!getMensajeErrorI()){ //si no esta mostrando el error siga
                 labelErrorPin.setVisible(true);
                 setMensajeErrorP(true);
                }
            }
        }
        else { //el pin se escribio bien
            labelErrorPin.setVisible(false);
            setMensajeErrorP(false);
        }
        
        if (getMensajeErrorP() || getMensajeErrorI()) { //si hay errores retorna false
            return false;
        }
        else {
            return true;
        }
    }
     
    //iniciarSesion(): Esta funcion permite iniciar sesion a cada usuario con su debida app
    public void iniciarSesion(){
        if (validarIdentPin()) { //si la informacion ingresada cumple las validaciones:
            //creamos un objeto LoginJFrame
            Login login1 = new Login();
            //login1.crear();
            //cargamos la lista de cuentas
            login1.setListaUsuarios(cargarUsuarios("listaUsuarios.txt"));
            //comprobar que el usuario con la identificacion
            
            Persona usuarioAIngresar = login1.verificarIdentificacion(campoIdentificacion.getText()); //Busca la identificacion en el login
            if (usuarioAIngresar == null){ //si la persona no existe
                JOptionPane.showMessageDialog(null, "No existe una cuenta con esta identificacion", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else{ //la identificacion le pertenece a un usuario
                if (login1.verificarPin(new String(campoPin.getPassword()), usuarioAIngresar)) { //si retorna true quiere decir que el pin es correcto
                    //verificar que tipo de cuenta es
                    if (usuarioAIngresar instanceof Administrador){
                        Administrador administrador = (Administrador) usuarioAIngresar; //convertimos de persona a administrador
                        //abrir app del Administrador
                        MenuAdministrador menuAdmin = new MenuAdministrador(administrador);
                        menuAdmin.setVisible(true); 
                        this.setVisible(false);
                    }
                    else if (usuarioAIngresar instanceof Inspector){
                        Inspector inspector = (Inspector) usuarioAIngresar;
                        //abrir app del inspector
                        Parqueo parqueo = new Parqueo();
                        parqueo.lecturaArchivo();
                        MenuInspector menuInspector = new MenuInspector(inspector, parqueo);
                        menuInspector.setVisible(false);
                    }
                    else if (usuarioAIngresar instanceof Usuario){
                        if (new File("Parametros.txt").exists()){ //comprobar si los parametros ya fueron configurados por el administrador
                            //abrir app del Usuario
                            Usuario usuario = (Usuario) usuarioAIngresar;
                            MenuUsuario menuUsuario = new MenuUsuario(usuario);
                            menuUsuario.setVisible(true);
                            this.setVisible(false);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "El parquímetro no ha sido configurado por un administrador, ponerse en contacto con alguno", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Hubo un error en la lógica del programa, ponerse en contacto con los creadores", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "El pin ingresado es incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    //registrarse(): Este boton abre la opcion para que el usuario pueda crearse una cuenta
    public void registrarse(){
        
    }
    
    public LoginJFrame() {
        initComponents();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelVentana = new javax.swing.JPanel();
        panelLogin = new javax.swing.JPanel();
        labelBienvenido = new javax.swing.JLabel();
        campoIdentificacion = new javax.swing.JTextField();
        botonIniciar = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        botonOlvidePin = new javax.swing.JButton();
        campoPin = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelErrorIdentificacion = new javax.swing.JLabel();
        labelErrorPin = new javax.swing.JLabel();
        labelCrearCuenta = new javax.swing.JLabel();
        botonRegistrarse = new javax.swing.JButton();
        botonCancelarDeRegistrarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelVentana.setBackground(new java.awt.Color(29, 24, 39));
        panelVentana.setPreferredSize(new java.awt.Dimension(1080, 720));

        panelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBienvenido.setText("Bienvenido(a)!");
        panelLogin.add(labelBienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 79, 85, -1));

        campoIdentificacion.setForeground(new java.awt.Color(153, 153, 153));
        campoIdentificacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoIdentificacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoIdentificacionFocusLost(evt);
            }
        });
        campoIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIdentificacionActionPerformed(evt);
            }
        });
        panelLogin.add(campoIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 145, 385, 52));

        botonIniciar.setText("Iniciar sesión");
        botonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarActionPerformed(evt);
            }
        });
        panelLogin.add(botonIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 114, 30));
        panelLogin.add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 139, -1, -1));
        panelLogin.add(filler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 63, -1, -1));
        panelLogin.add(filler3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 109, -1, -1));
        panelLogin.add(filler4, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 139, -1, -1));
        panelLogin.add(filler5, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 115, -1, -1));
        panelLogin.add(filler6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 116, -1, -1));
        panelLogin.add(filler7, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 378, -1, -1));
        panelLogin.add(filler8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 432, -1, -1));

        botonOlvidePin.setText("Olvidé mi PIN");
        botonOlvidePin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOlvidePinActionPerformed(evt);
            }
        });
        panelLogin.add(botonOlvidePin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, -1, 30));

        campoPin.setForeground(new java.awt.Color(153, 153, 153));
        campoPin.setActionCommand("<Not Set>");
        campoPin.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                campoPinComponentAdded(evt);
            }
        });
        campoPin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoPinFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoPinFocusLost(evt);
            }
        });
        campoPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPinActionPerformed(evt);
            }
        });
        panelLogin.add(campoPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 241, 385, 52));

        jLabel1.setText("Identificación");
        panelLogin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        jLabel2.setText("PIN");
        panelLogin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 34, -1));

        labelErrorIdentificacion.setForeground(new java.awt.Color(255, 51, 51));
        labelErrorIdentificacion.setText("La identificacion debe de contener entre 2 y 25 caracteres");
        labelErrorIdentificacion.setVisible(false);
        panelLogin.add(labelErrorIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 308, -1));

        labelErrorPin.setForeground(new java.awt.Color(255, 51, 51));
        labelErrorPin.setText("El PIN debe de contener 4 caracteres");
        labelErrorPin.setVisible(false);
        panelLogin.add(labelErrorPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 311, 219, -1));

        labelCrearCuenta.setBackground(new java.awt.Color(255, 255, 255));
        labelCrearCuenta.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        labelCrearCuenta.setForeground(new java.awt.Color(204, 204, 204));
        labelCrearCuenta.setText("Para crear una cuenta:");

        botonRegistrarse.setText("Presione aquí");
        botonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarseActionPerformed(evt);
            }
        });

        botonCancelarDeRegistrarse.setText("Cancelar");
        botonCancelarDeRegistrarse.setVisible(false);
        botonCancelarDeRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarDeRegistrarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVentanaLayout = new javax.swing.GroupLayout(panelVentana);
        panelVentana.setLayout(panelVentanaLayout);
        panelVentanaLayout.setHorizontalGroup(
            panelVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentanaLayout.createSequentialGroup()
                .addGroup(panelVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVentanaLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVentanaLayout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(labelCrearCuenta)
                        .addGap(4, 4, 4)
                        .addComponent(botonRegistrarse)))
                .addGap(195, 195, 195))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentanaLayout.createSequentialGroup()
                .addComponent(botonCancelarDeRegistrarse)
                .addGap(32, 32, 32))
        );
        panelVentanaLayout.setVerticalGroup(
            panelVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentanaLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(panelVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVentanaLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(labelCrearCuenta))
                    .addComponent(botonRegistrarse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(botonCancelarDeRegistrarse)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIdentificacionActionPerformed

    }//GEN-LAST:event_campoIdentificacionActionPerformed

    private void botonIniciarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        iniciarSesion();
    }
    
    private void campoIdentificacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoIdentificacionFocusGained

    }//GEN-LAST:event_campoIdentificacionFocusGained

    private void campoIdentificacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoIdentificacionFocusLost

    }//GEN-LAST:event_campoIdentificacionFocusLost

    private void botonOlvidePinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOlvidePinActionPerformed
        panelOlvidePin = new PanelOlvidePin(this);
        panelOlvidePin.setBounds(0, 0, 1080, 720);
        panelLogin.setVisible(false);
        labelCrearCuenta.setVisible(false);
        botonRegistrarse.setVisible(false);
        
        panelVentana.add(panelOlvidePin);
        panelVentana.revalidate();
        panelVentana.repaint();
    }//GEN-LAST:event_botonOlvidePinActionPerformed

    private void botonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarseActionPerformed
        
        panelRegistrarse = new PanelRegistrarse(this);
        panelRegistrarse.setBounds(0, 0, 1080, 720);
        panelLogin.setVisible(false);
        labelCrearCuenta.setVisible(false);
        botonRegistrarse.setVisible(false);
        botonCancelarDeRegistrarse.setVisible(true);
        panelVentana.add(panelRegistrarse);
        panelVentana.revalidate();
        panelVentana.repaint();
        

    }//GEN-LAST:event_botonRegistrarseActionPerformed

    private void campoPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPinActionPerformed
        
    }//GEN-LAST:event_campoPinActionPerformed

    private void campoPinComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_campoPinComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_campoPinComponentAdded

    private void campoPinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPinFocusLost

    }//GEN-LAST:event_campoPinFocusLost

    private void campoPinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPinFocusGained
 
    }//GEN-LAST:event_campoPinFocusGained

    private void botonCancelarDeRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarDeRegistrarseActionPerformed
        panelRegistrarse.setVisible(false);
        labelCrearCuenta.setVisible(true);
        botonRegistrarse.setVisible(true);
        panelLogin.setVisible(true);
        botonCancelarDeRegistrarse.setVisible(false);
        panelVentana.revalidate();
        panelVentana.repaint();
    }//GEN-LAST:event_botonCancelarDeRegistrarseActionPerformed

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
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelarDeRegistrarse;
    private javax.swing.JButton botonIniciar;
    private javax.swing.JButton botonOlvidePin;
    private javax.swing.JButton botonRegistrarse;
    private javax.swing.JTextField campoIdentificacion;
    private javax.swing.JPasswordField campoPin;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelBienvenido;
    private javax.swing.JLabel labelCrearCuenta;
    private javax.swing.JLabel labelErrorIdentificacion;
    private javax.swing.JLabel labelErrorPin;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel panelVentana;
    // End of variables declaration//GEN-END:variables
}
