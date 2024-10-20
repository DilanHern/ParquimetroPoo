/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.tec.parquimetro.parquimetro.GUI;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import com.tec.parquimetro.parquimetro.Clases.Correo;
import com.tec.parquimetro.parquimetro.Clases.Login;
import static com.tec.parquimetro.parquimetro.Clases.Login.cargarUsuarios;
import static com.tec.parquimetro.parquimetro.Clases.Login.guardarUsuarios;
import com.tec.parquimetro.parquimetro.Clases.Persona;
import com.tec.parquimetro.parquimetro.Clases.Tarjeta;
import com.tec.parquimetro.parquimetro.Clases.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author Dilan
 */
public class PanelRegistrarse extends javax.swing.JPanel {
    private LoginJFrame loginJFrame;
    /**
     * Creates new form PanelRegistrarse
     */
    public PanelRegistrarse(LoginJFrame loginJFrame) {
        this.loginJFrame = loginJFrame;
        initComponents();
    }
    
    public LoginJFrame getLoginJFrame(){
        return loginJFrame;
    }
    
    //*********************************************************
    //VALIDACIONES DATOS DE PERFIL*****************************
    //*********************************************************
    public boolean validarApellidos(String apellidos) {
        
        if(apellidos.length()>= 1 && apellidos.length()<=40){
            return true;
        }
        else
            return false;
        
    }

   public boolean validarNombre(String nombre) {
        
        if(nombre.length() >= 2 && nombre.length()<=20){
                 return true;
        }
        return false;
        
    }
   
  public boolean validarTelefono(String telefono) {
        try {
            int num = Integer.parseInt(telefono);
            //es caso de tener 8 digito su resultado
            if (num  / 100000000 == 0){
                return true;
            }
            else
                return false;
        }
        
        catch (NumberFormatException e){
            return false;
        }
    }
  
 public boolean setFechaIngreso(LocalDate fechaIngreso) {
        
        
        LocalDate hoy = LocalDate.now();
        if(fechaIngreso.isBefore(hoy) || fechaIngreso.isEqual(hoy)){
            return true;
        }
        else
            return false;
        
    }
 
 public boolean validacionIdentificacion(String identificacion, ArrayList<Persona> listaBuscar) {
        
        if(identificacion.length()>= 2 && identificacion.length() <= 25){
            for (Persona persona : listaBuscar){
                if (persona.getIdentificacion().equals(identificacion)){ //si la identificacion es igual, retorna false
                    JOptionPane.showMessageDialog(null, "Ya existe un usuario con esa identificacion!");
                    return false;
                }
            }
            return true; //la identificacion es unica
        }
        else{
            JOptionPane.showMessageDialog(null, "La identificacion debe tener entre 2 a 25 caracteres!");
            return false;
        }
    }
 
     public boolean  validacionDireccionFisica(String direccionFisica) {
        
        if(direccionFisica.length() >= 5 && direccionFisica.length() <= 50){
            
            return true;
            
        }else{
        
            return false;
        }
        
    }
     
    public boolean validarPin(String pin){
    
        if(pin.length() == 4){
            try {
                int prueba = Integer.valueOf(pin);
                return true;
            }
            catch (NumberFormatException e){
                return false;
            }
            
        }
        else{
            return false;
        }
    }   
    
    private boolean validarConversion(String elemento){
            
        try{
        
            int prueba = Integer.valueOf(elemento);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
        
    }
    private boolean validarCorreo(String str1, String str2){
        if (str1.length() < 3 || str2.length() < 3){
            return false;
        }
        return true;
    }
    
    private ArrayList<Persona> validarTarjeta(String numTar, String codigo, String mesVencimiento, String anoVencimiento){
        try {
            long numTarInt = Long.parseLong(numTar);
            int codigoInt = Integer.parseInt(codigo);
            int mesVencimientoInt = Integer.parseInt(mesVencimiento);
            int anoVencimientoInt = Integer.parseInt(anoVencimiento);
            //en caso de cumplir todos los requisitos
            if (numTar.length() == 16 && codigo.length() == 3 && (mesVencimientoInt > 0 && mesVencimientoInt < 13) && anoVencimiento.length() == 4 ) {
                //validar que la tarjeta es unica
                Tarjeta tarjetaNueva = new Tarjeta(numTarInt,mesVencimientoInt, anoVencimientoInt, codigoInt);
                //cargamos la lista de cuentas
                try {
                    ArrayList<Persona> buscarTarjeta = cargarUsuarios("listaUsuarios.txt");  //le asignamos al login la lista de usuarios cargada
                    System.out.println("NO dio error buscando la tarjeta");
                    //verificamos que no exista el numero de tarjeta para ningun usuario
                    boolean existe = false;
                    for (Persona persona : buscarTarjeta){
                        if (persona instanceof Usuario){
                            Usuario usuario = (Usuario) persona; // Hacer el cast a Usuario
                            //comprobar numero de la tarjeta
                            if (usuario.getTarjeta() == null){
                                //nada
                            }
                            
                            else {
                                
                                if (usuario.getTarjeta().getNumeroTarjeta() == tarjetaNueva.getNumeroTarjeta()) {
                                    JOptionPane.showMessageDialog(null, "Ya existe un usuario con esta tarjeta de crédito", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                                    existe = true;
                                    return null; 
                                }
                            }
                            return buscarTarjeta;//borrar y descomentar
                        }
                    }
                    //si no existe una persona con esta tarjeta:
                    if(!existe){
                        return buscarTarjeta;
                    }
                    else{
                        return null;
                    }
                }
                catch (IOException e){
                    System.out.println("dio error buscando la tarjeta");
                    return null;
                }
                catch (ClassNotFoundException e1){
                    JOptionPane.showMessageDialog(null, "No se pudo cargar la lista de cuentas, error con las clases", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    return null;   
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El numero de tarjeta debe tener 16 digitos, el código 3 dígitos, el mes debe de ser entre 1 y 12 y el año debe de tener 4 digitos!");
                return null;
            }
        
        catch (NumberFormatException e){
            return null;
        }
        //return false;
    }
    //*********************************************************
    //FIN DEVALIDACIONES DATOS DE PERFIL***********************
    //*********************************************************
    
    //funcion crearUsuario(): esta funcion se encarga de verificar la informacion ingresada y crear una cuenta al usuario
    public void crearUsuario(){        
        if(validarNombre(txtNombre1.getText())){
                if(validarApellidos(txtApellidos.getText())){
                    if(validacionDireccionFisica(taDireccionFisica.getText())){
                            if(validarConversion(txtTelefono.getText())){
                                if(validarTelefono(txtTelefono.getText())){
                                    if(validarCorreo(txtPt1Mail.getText(), txtPt2Mail.getText())){
                                        ArrayList<Persona> listaRevisar = validarTarjeta(txtNumTar.getText(), txtCodigo.getText(), txtMes.getText(), txtAno.getText()); //si retorna la lista, quiere decir que no hay usuarios con esta tarjeta, si retorna null ya hay usuarios
                                        if ( listaRevisar != null){
                                            if ( validacionIdentificacion(txtIdentificacion.getText(), listaRevisar)) {
                                                if (validarPin(new String(txtPin.getPassword())) ){
                                                    //crear cuenta
                                                    Correo correoAgregar = new Correo(txtPt1Mail.getText(), txtPt2Mail.getText());
                                                    Tarjeta tarjetaAgregar = new Tarjeta(Long.parseLong(txtNumTar.getText()), Integer.parseInt(txtMes.getText()), Integer.parseInt(txtAno.getText()), Integer.parseInt(txtCodigo.getText()) );
                                                    Usuario usuarioAgregar = new Usuario(txtNombre1.getText(), txtApellidos.getText(), Integer.parseInt(txtTelefono.getText()), taDireccionFisica.getText(), LocalDate.now(), txtIdentificacion.getText(), new String(txtPin.getPassword()), 0, correoAgregar); //agregar parametros de usuario
                                                    usuarioAgregar.setTarjeta(tarjetaAgregar);
                                                    //cargar y guardar la listaUsuarios
                                                    listaRevisar.add(usuarioAgregar);
                                                    try {
                                                        Login.guardarUsuarios("listaUsuarios.txt", listaRevisar);
                                                        JOptionPane.showMessageDialog(null, "Su cuenta fue creada exitosamente!");
                                                        getLoginJFrame().getBotonCancelarDeRegistrarse(); //cerramos esta ventana de registrarse
                                                        
                                                    }
                                                    catch (IOException e){
                                                        JOptionPane.showMessageDialog(null, "Hubo un error guardando su cuenta en el sistema!" + e.getMessage());
                                                        e.printStackTrace(); // Imprime la traza de la excepción en la consola);
                                                    }
                                                }

                                                else {
                                                   JOptionPane.showMessageDialog(null, "El pin debe de contener 4 dígitos!");
                                                }
                                            }
                                            else{
                                                //nada
                                            }
                                        }
                                       
                                       else{
                                           JOptionPane.showMessageDialog(null, "Hubo un error cargando las tarjetas!");
                                       }
                                    }
                                    else{
                                       JOptionPane.showMessageDialog(null, "El encabezado y dominio del correo debe tener minimo 3 caracteres!");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "El telefono debe tener 8 digitos!");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "El telefono posee caracteres alfabeticos o no lo haz ingresado!");
                            }
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "La direccion fisica debe tener entre 5 a 60 caracteres!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Los apellidos deben tener entre 1 a 40 caracteres!");
                }
                
        }
        else {
            JOptionPane.showMessageDialog(null, "El nombre debe tener entre 2 a 20 caracteres!");
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

        panelRegistrarse = new javax.swing.JPanel();
        lbRegistrarse = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblTelefono1 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblTelefono2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDireccionFisica = new javax.swing.JTextArea();
        lblIdentificacion = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        lblApellidos3 = new javax.swing.JLabel();
        txtPt1Mail = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        lblApellidos2 = new javax.swing.JLabel();
        txtPt2Mail = new javax.swing.JTextField();
        lbPin = new javax.swing.JLabel();
        lbNumTar = new javax.swing.JLabel();
        txtNumTar = new javax.swing.JTextField();
        txtPin = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAno = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1080, 720));

        panelRegistrarse.setBackground(new java.awt.Color(57, 54, 66));
        panelRegistrarse.setPreferredSize(new java.awt.Dimension(1080, 720));
        panelRegistrarse.setVisible(true);

        lbRegistrarse.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        lbRegistrarse.setText("Ingrese sus datos");

        lblNombre1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre1.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre1.setText("Nombre");

        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });

        lblApellidos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos.setText("Apellidos");

        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        lblTelefono1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTelefono1.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono1.setText("Telefono");

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        lblTelefono2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTelefono2.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono2.setText("Direccion Fisica");

        taDireccionFisica.setColumns(20);
        taDireccionFisica.setRows(5);
        jScrollPane2.setViewportView(taDireccionFisica);

        lblIdentificacion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblIdentificacion.setForeground(new java.awt.Color(255, 255, 255));
        lblIdentificacion.setText("Identificacion");

        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });

        lblApellidos3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos3.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos3.setText("Correo electrónico");

        txtPt1Mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPt1MailActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lblApellidos2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos2.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos2.setText("@");

        txtPt2Mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPt2MailActionPerformed(evt);
            }
        });

        lbPin.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lbPin.setText("PIN");

        lbNumTar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lbNumTar.setText("Tarjeta de credito");

        txtNumTar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumTarActionPerformed(evt);
            }
        });

        txtPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPinActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Codigo de validación");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Fecha de vencimiento");

        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("/");

        txtAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRegistrarseLayout = new javax.swing.GroupLayout(panelRegistrarse);
        panelRegistrarse.setLayout(panelRegistrarseLayout);
        panelRegistrarseLayout.setHorizontalGroup(
            panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(lbRegistrarse))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(lblNombre1)
                .addGap(240, 240, 240)
                .addComponent(lblApellidos))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(lblTelefono1)
                .addGap(233, 233, 233)
                .addComponent(lblApellidos3))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(txtPt1Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblApellidos2)
                .addGap(6, 6, 6)
                .addComponent(txtPt2Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(lblIdentificacion)
                .addGap(207, 207, 207)
                .addComponent(lblTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addGroup(panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(lbNumTar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170)
                .addComponent(jLabel1)
                .addGap(96, 96, 96)
                .addComponent(jLabel2))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(txtNumTar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(btnAceptar))
        );
        panelRegistrarseLayout.setVerticalGroup(
            panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrarseLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lbRegistrarse)
                .addGap(36, 36, 36)
                .addGroup(panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre1)
                    .addComponent(lblApellidos))
                .addGap(21, 21, 21)
                .addGroup(panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefono1)
                    .addGroup(panelRegistrarseLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblApellidos3)))
                .addGap(11, 11, 11)
                .addGroup(panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPt1Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRegistrarseLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblApellidos2))
                    .addComponent(txtPt2Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistrarseLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblIdentificacion))
                    .addComponent(lblTelefono2))
                .addGap(1, 1, 1)
                .addGroup(panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistrarseLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(lbPin))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNumTar)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(21, 21, 21)
                .addGroup(panelRegistrarseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumTar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(btnAceptar))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelRegistrarse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelRegistrarse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void txtPt1MailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPt1MailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPt1MailActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        crearUsuario();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtPt2MailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPt2MailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPt2MailActionPerformed

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void txtNumTarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumTarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumTarActionPerformed

    private void txtPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPinActionPerformed

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesActionPerformed

    private void txtAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbNumTar;
    private javax.swing.JLabel lbPin;
    private javax.swing.JLabel lbRegistrarse;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblApellidos2;
    private javax.swing.JLabel lblApellidos3;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JLabel lblTelefono2;
    private javax.swing.JPanel panelRegistrarse;
    private javax.swing.JTextArea taDireccionFisica;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNumTar;
    private javax.swing.JPasswordField txtPin;
    private javax.swing.JTextField txtPt1Mail;
    private javax.swing.JTextField txtPt2Mail;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
