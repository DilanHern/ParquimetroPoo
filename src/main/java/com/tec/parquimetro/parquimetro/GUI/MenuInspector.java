/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.tec.parquimetro.parquimetro.GUI;
import com.tec.parquimetro.parquimetro.Clases.Administrador;
import com.tec.parquimetro.parquimetro.Clases.Correo;
import com.tec.parquimetro.parquimetro.Clases.Espacio;
import com.tec.parquimetro.parquimetro.Clases.Inspector;
import com.tec.parquimetro.parquimetro.Clases.Login;
import com.tec.parquimetro.parquimetro.Clases.Multa;
import com.tec.parquimetro.parquimetro.Clases.Parqueo;
import com.tec.parquimetro.parquimetro.Clases.Persona;
import com.tec.parquimetro.parquimetro.Clases.TicketParqueo;
import com.tec.parquimetro.parquimetro.Clases.Usuario;
import com.tec.parquimetro.parquimetro.Clases.Vehiculo;
import static com.tec.parquimetro.parquimetro.GUI.MenuAdministrador.administrador;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author carol_flgngfy
 */
public class MenuInspector extends javax.swing.JFrame {
    //Atributos
    //ATRIBUTOS PARA ENVIAR REPORTES
    private static String emailDe = "paquimetrocartago@gmail.com";
    private static String contraseñaDe = "vofx ztal oawe yary";
    private static String emailPara;
    
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    //FIN DE ATRIBUTOS DE REPORTES
    public  static Inspector inspector = new Inspector();
    public static Parqueo parqueo;
    DefaultTableModel mdlEspaciosGeneral = new DefaultTableModel();//formato para tabal espacios en reporte general de espacios
    
     /**
     * Constructor de la clase MenuInspector.
     * Inicializa los componentes de la interfaz gráfica y configura el inspector y el parqueo.
     *
     * @param pinspector El inspector que inicia sesión.
     * @param pParqueo El objeto Parqueo que contiene la información del parqueo.
     */
    public MenuInspector(Inspector pinspector, Parqueo pParqueo) {
        initComponents();
        mProperties = new Properties();
        pbTabl.setSelectedIndex(4);
        parqueo = pParqueo;
        inspector = pinspector;
        labelBienvenido.setText(inspector.getNombre() + " " + inspector.getApellidos());
        lblId.setText(inspector.getIdentificacion());
         pbTabl.setSelectedIndex(3);
        
         pbTabl.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0; // Hacer la altura de las pestañas cero para ocultarlas
            }
         });
         pbTabl.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0; // Hacer la altura de las pestañas cero para ocultarlas
            }
        });
         pbTabl.setBorder(BorderFactory.createEmptyBorder());
         pbTabl.setSelectedIndex(4);
    }

    /**
     * Verifica si la placa está en el parqueo.
     * Retorna true si la placa está en el parqueo, false si no lo está.
     *
     * @param placa La placa del vehículo a verificar.
     * @param numParqueo El número del espacio de parqueo.
     * @param parqueo El objeto Parqueo que contiene la información del parqueo.
     * @return true si la placa está en el parqueo, false en caso contrario.
     */
    public static boolean verificarPlacaParqueo(int placa, int numParqueo, Parqueo parqueo){
         //2. verificar en esta lista si el parqueo ingresado existe, si no retorna FALSE, si si, continua
         Espacio espacioVerificar = parqueo.buscarEspacio(numParqueo);
         if (espacioVerificar == null){ //no existe ese espacio
             JOptionPane.showMessageDialog(null, "NO existe un espacio con ese número de parqueo", "ERROR", JOptionPane.INFORMATION_MESSAGE);
             return true;
         }
         else{ //si existe ese espacio, revisar si el espacio está ocupado y si la placa corresponde al parqueo
             if (espacioVerificar.getEstado()){ //si el estado es true, está disponible
                 JOptionPane.showMessageDialog(null, "El espacio está disponible, no hay ningun auto registrado a este espacio", "MULTA", JOptionPane.INFORMATION_MESSAGE);
                 return false;
             } 
             else{ //el espacio está ocupado
                 for (Vehiculo vehiculo : espacioVerificar.getVehiculos()){
                     if (vehiculo.getPlaca().equals(placa)){ //si la placa es igual al del vehiculo estacionado, no hace una multa
                         JOptionPane.showMessageDialog(null, "La placa del vehiculo en el espacio corresponde al pago", "TODO EN ORDEN", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                     }
                 }
                 //caso contrario de que las placas sean diferentes
                 JOptionPane.showMessageDialog(null, "El vehiculo pago no corresponde con la placa ingresada", "MULTA", JOptionPane.INFORMATION_MESSAGE);
                 return false;
             }
         }
     }    
     
     /**
     * Crea un correo electrónico configurando la sesión y el mensaje.
     * Establece los parámetros necesarios para enviar el correo electrónico.
     *
     * @param correo El correo electrónico del destinatario.
     * @param asunto El asunto del correo electrónico.
     * @param cuerpo El cuerpo del correo electrónico.
     */
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
     
     /**
     * Envía un correo electrónico utilizando la configuración de la sesión.
     * Establece la conexión con el servidor SMTP y envía el mensaje.
     */
    private void actualizarInformacion(Inspector inspector, String identificacion){
    
        //Utilizado para actualizar los usuarios al modificar su informacion
        Login login = new Login();
         login.actualizarPersona(inspector,identificacion);
        
    }
     public void enviarEmail(){
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailDe, contraseñaDe);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(MenuInspector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MenuInspector.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
     * Crea una multa para un vehículo en un espacio de parqueo específico.
     * Registra la multa en el sistema y la asocia al vehículo y al espacio de parqueo.
     *
     * @param placa La placa del vehículo a multar.
     * @param costo El costo de la multa
     */
     public void crearMulta(int placa, int costo){
        
        try{
            //buscar todos los usuarios
            ArrayList<Persona> personas = Login.cargarUsuarios("listaUsuarios.txt");
            
            for (Persona cadaPersona : personas){
                
                if (cadaPersona instanceof Usuario){
                    Usuario usuario = (Usuario) cadaPersona; // Hacer el cast a Usuario
                    if (usuario.getVehiculos() != null){ //si el usuario tiene vehiculos:
                        
                        for (Vehiculo cadaVehiculo : usuario.getVehiculos()){ //revisa cada vehiculo
                            if (usuario.buscarVehiculo(String.valueOf(placa))!= null){ //si la placa es la misma
                                //genera la multa
                                Multa nuevaMulta = new Multa (costo, txtRazonMulta.getText(), LocalDateTime.now(),(Usuario)cadaPersona,cadaVehiculo.getPlaca(),inspector);
                                cadaVehiculo.setNuevaMulta(nuevaMulta);
                                
                                Parqueo pq = new Parqueo();
                                pq.lecturaArchivo();
                                pq.agregarMulta(nuevaMulta);
                                 JOptionPane.showMessageDialog(null, "Espere un momento por favor, estamos procesando su informacion.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                                   JFileChooser fileChoo = new JFileChooser();
                                  File f = new File("Multa");
                                  String rutaArchivo = "";
                                  fileChoo.setSelectedFile(f);
                                  int option = fileChoo.showSaveDialog(this);

                                  if(option == JFileChooser.APPROVE_OPTION){

                                      f = fileChoo.getSelectedFile();
                                      rutaArchivo = f.toString();
                                  }
                              nuevaMulta.generarPDF(rutaArchivo);
                                //se envia el correo
                                emailPara = usuario.getCorreo().getCorreo();
                                crearEmail(txtRazonMulta.getText() + "\nCOSTO: " + parqueo.getCostoMulta(), "MULTA", emailPara);
                                enviarEmail();
                                pbTabl.setSelectedIndex(0);
                                JOptionPane.showMessageDialog(null, "Guardado exitosamente", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        }
                    }
                }
            }
            Login.guardarUsuarios("listaUsuarios.txt", personas);
            //no encontro ningun usuario con esa 
            
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "No se pudo cargar la lista de usuarios, no se podrá enviar un correo", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            
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

        jPanel1 = new javax.swing.JPanel();
        panelRedondo1 = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        jLabel1 = new javax.swing.JLabel();
        btnPerfil = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnRevisarParqueos = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        rondedBordes5 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnReportes = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        labelBienvenido = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        pbTabl = new javax.swing.JTabbedPane();
        pnParquear = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNumParqueo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnReportes = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil3 = new javax.swing.JLabel();
        tpReportes = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        cbEspaciosGeneral = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblEspaciosGeneral = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        lblCantidadEspacios = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnDescargarEspaciosPDF = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        btnDescargarMultaHechaPDF = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        dcFinMultaHecha = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        dcInicioMultaHecha = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        pnPrincipal = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRazonMulta = new javax.swing.JTextArea();
        btnEnviarMulta = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnPerfil = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtPt2Mail = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDireccionFisica = new javax.swing.JTextArea();
        txtIdentificacion = new javax.swing.JTextField();
        lblIdentificacion = new javax.swing.JLabel();
        lblApellidos = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        lblTelefono1 = new javax.swing.JLabel();
        lblTelefono2 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        txtPt1Mail = new javax.swing.JTextField();
        lblApellidos1 = new javax.swing.JLabel();
        lblApellidos2 = new javax.swing.JLabel();
        lblApellidos3 = new javax.swing.JLabel();
        btnActualizarPerfil = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnRestablecerContra = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        jLabel3 = new javax.swing.JLabel();
        txtTerminal = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPinAnterior = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPinNuevo = new javax.swing.JTextField();
        rondedBordes1 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        rondedBordes2 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(29, 24, 39));

        panelRedondo1.setBackground(new java.awt.Color(57, 54, 66));
        panelRedondo1.setRoundBottomLeft(30);
        panelRedondo1.setRoundBottomRight(30);
        panelRedondo1.setRoundTopLeft(30);
        panelRedondo1.setRoundTopRight(30);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Leelawadee", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Parquimetro");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        btnPerfil.setBackground(new java.awt.Color(255, 255, 255));
        btnPerfil.setForeground(new java.awt.Color(0, 0, 51));
        btnPerfil.setText("Mi Perfil");
        btnPerfil.setColor1(new java.awt.Color(255, 255, 255));
        btnPerfil.setColor2(new java.awt.Color(255, 255, 255));
        btnPerfil.setColor3(new java.awt.Color(204, 204, 204));
        btnPerfil.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPerfilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPerfilMouseExited(evt);
            }
        });
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });

        btnRevisarParqueos.setForeground(new java.awt.Color(0, 0, 51));
        btnRevisarParqueos.setText("Revisar parqueos");
        btnRevisarParqueos.setColor1(new java.awt.Color(255, 255, 255));
        btnRevisarParqueos.setColor2(new java.awt.Color(255, 255, 255));
        btnRevisarParqueos.setColor3(new java.awt.Color(204, 204, 204));
        btnRevisarParqueos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnRevisarParqueos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRevisarParqueosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRevisarParqueosMouseExited(evt);
            }
        });
        btnRevisarParqueos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarParqueosActionPerformed(evt);
            }
        });

        rondedBordes5.setText("Cerrar Sesion");
        rondedBordes5.setToolTipText("");
        rondedBordes5.setColor1(new java.awt.Color(126, 217, 87));
        rondedBordes5.setColor2(new java.awt.Color(126, 217, 87));
        rondedBordes5.setColor3(new java.awt.Color(126, 217, 87));
        rondedBordes5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rondedBordes5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rondedBordes5ActionPerformed(evt);
            }
        });

        btnReportes.setForeground(new java.awt.Color(0, 0, 51));
        btnReportes.setText("Reportes");
        btnReportes.setColor1(new java.awt.Color(255, 255, 255));
        btnReportes.setColor2(new java.awt.Color(255, 255, 255));
        btnReportes.setColor3(new java.awt.Color(204, 204, 204));
        btnReportes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportesMouseExited(evt);
            }
        });
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        labelBienvenido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelBienvenido.setForeground(new java.awt.Color(255, 255, 255));
        labelBienvenido.setText("Camila Araya Conejo");

        lblId.setForeground(new java.awt.Color(255, 255, 255));
        lblId.setText("305610469");

        javax.swing.GroupLayout panelRedondo1Layout = new javax.swing.GroupLayout(panelRedondo1);
        panelRedondo1.setLayout(panelRedondo1Layout);
        panelRedondo1Layout.setHorizontalGroup(
            panelRedondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondo1Layout.createSequentialGroup()
                .addGroup(panelRedondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRedondo1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(labelBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRedondo1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblId))
                    .addGroup(panelRedondo1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondo1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRedondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rondedBordes5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRedondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRevisarParqueos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        panelRedondo1Layout.setVerticalGroup(
            panelRedondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondo1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblId)
                .addGap(33, 33, 33)
                .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnRevisarParqueos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rondedBordes5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        pbTabl.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        pbTabl.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        pbTabl.setToolTipText("");

        pnParquear.setBackground(new java.awt.Color(57, 54, 66));
        pnParquear.setRoundBottomLeft(15);
        pnParquear.setRoundBottomRight(15);
        pnParquear.setRoundTopLeft(15);
        pnParquear.setRoundTopRight(15);

        lblPerfil1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil1.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil1.setText("Revisar parqueos");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingrese el número de placa:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingrese el número del parqueo:");

        txtNumParqueo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumParqueoActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(204, 102, 0));
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnParquearLayout = new javax.swing.GroupLayout(pnParquear);
        pnParquear.setLayout(pnParquearLayout);
        pnParquearLayout.setHorizontalGroup(
            pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParquearLayout.createSequentialGroup()
                .addGroup(pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParquearLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPlaca)
                            .addComponent(txtNumParqueo, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                    .addGroup(pnParquearLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(lblPerfil1))
                    .addGroup(pnParquearLayout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(272, Short.MAX_VALUE))
        );
        pnParquearLayout.setVerticalGroup(
            pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParquearLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(lblPerfil1)
                .addGap(97, 97, 97)
                .addGroup(pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNumParqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );

        pbTabl.addTab("", pnParquear);

        pnReportes.setBackground(new java.awt.Color(57, 54, 66));
        pnReportes.setRoundBottomLeft(15);
        pnReportes.setRoundBottomRight(15);
        pnReportes.setRoundTopLeft(15);
        pnReportes.setRoundTopRight(15);

        lblPerfil3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil3.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil3.setText("Reportes");

        tpReportes.setBackground(new java.awt.Color(57, 54, 66));

        jPanel2.setBackground(new java.awt.Color(29, 24, 39));

        cbEspaciosGeneral.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los espacios", "Espacios ocupados", "Espacios vacios" }));
        cbEspaciosGeneral.setSelectedIndex(-1);
        cbEspaciosGeneral.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEspaciosGeneralItemStateChanged(evt);
            }
        });
        cbEspaciosGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEspaciosGeneralActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Filtrar");

        tblEspaciosGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tblEspaciosGeneral);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Espacios considerados en el reporte:");

        lblCantidadEspacios.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidadEspacios.setText("-");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Espacios");

        btnDescargarEspaciosPDF.setBackground(new java.awt.Color(153, 255, 51));
        btnDescargarEspaciosPDF.setText("Descargar PDF");
        btnDescargarEspaciosPDF.setColor1(new java.awt.Color(126, 217, 87));
        btnDescargarEspaciosPDF.setColor2(new java.awt.Color(126, 217, 87));
        btnDescargarEspaciosPDF.setColor3(new java.awt.Color(126, 217, 87));
        btnDescargarEspaciosPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarEspaciosPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(15, 15, 15))
                            .addComponent(cbEspaciosGeneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(lblCantidadEspacios)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDescargarEspaciosPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbEspaciosGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblCantidadEspacios)
                    .addComponent(btnDescargarEspaciosPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tpReportes.addTab("Espacios", jPanel2);

        jPanel7.setBackground(new java.awt.Color(29, 24, 39));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Multas hechas");

        btnDescargarMultaHechaPDF.setBackground(new java.awt.Color(153, 255, 51));
        btnDescargarMultaHechaPDF.setText("Descargar PDF");
        btnDescargarMultaHechaPDF.setColor1(new java.awt.Color(126, 217, 87));
        btnDescargarMultaHechaPDF.setColor2(new java.awt.Color(126, 217, 87));
        btnDescargarMultaHechaPDF.setColor3(new java.awt.Color(126, 217, 87));
        btnDescargarMultaHechaPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarMultaHechaPDFActionPerformed(evt);
            }
        });

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Inicio del periodo");

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Fin del periodo");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 75, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(dcInicioMultaHecha, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(dcFinMultaHecha, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(101, 101, 101))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDescargarMultaHechaPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(298, 298, 298))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel17)
                .addGap(69, 69, 69)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcInicioMultaHecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFinMultaHecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDescargarMultaHechaPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );

        tpReportes.addTab("Multas Hechas", jPanel7);

        javax.swing.GroupLayout pnReportesLayout = new javax.swing.GroupLayout(pnReportes);
        pnReportes.setLayout(pnReportesLayout);
        pnReportesLayout.setHorizontalGroup(
            pnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnReportesLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(pnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnReportesLayout.createSequentialGroup()
                        .addComponent(lblPerfil3)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnReportesLayout.createSequentialGroup()
                        .addComponent(tpReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        pnReportesLayout.setVerticalGroup(
            pnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnReportesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblPerfil3)
                .addGap(18, 18, 18)
                .addComponent(tpReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pbTabl.addTab("", pnReportes);

        pnPrincipal.setBackground(new java.awt.Color(57, 54, 66));
        pnPrincipal.setRoundBottomLeft(15);
        pnPrincipal.setRoundBottomRight(15);
        pnPrincipal.setRoundTopLeft(15);
        pnPrincipal.setRoundTopRight(15);

        lblPerfil5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil5.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil5.setText("Escriba la razón de la multa");

        txtRazonMulta.setColumns(20);
        txtRazonMulta.setRows(5);
        jScrollPane2.setViewportView(txtRazonMulta);

        btnEnviarMulta.setText("Enviar multa");
        btnEnviarMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarMultaActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnPrincipalLayout = new javax.swing.GroupLayout(pnPrincipal);
        pnPrincipal.setLayout(pnPrincipalLayout);
        pnPrincipalLayout.setHorizontalGroup(
            pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrincipalLayout.createSequentialGroup()
                .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPrincipalLayout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(lblPerfil5))
                    .addGroup(pnPrincipalLayout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(btnEnviarMulta)
                        .addGap(104, 104, 104)
                        .addComponent(btnCancelar))
                    .addGroup(pnPrincipalLayout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(227, Short.MAX_VALUE))
        );
        pnPrincipalLayout.setVerticalGroup(
            pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrincipalLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(lblPerfil5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168)
                .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviarMulta)
                    .addComponent(btnCancelar))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pbTabl.addTab("", pnPrincipal);

        pnPerfil.setBackground(new java.awt.Color(57, 54, 66));
        pnPerfil.setRoundBottomLeft(15);
        pnPerfil.setRoundBottomRight(15);
        pnPerfil.setRoundTopLeft(15);
        pnPerfil.setRoundTopRight(15);

        lblPerfil6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil6.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil6.setText("Mi Perfil");

        txtTelefono.setText("00000000");
        txtTelefono.setToolTipText("Telefono");
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        txtPt2Mail.setToolTipText("Dominio");

        txtApellidos.setText("Apellidos");
        txtApellidos.setToolTipText("Apellidos");
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        taDireccionFisica.setColumns(20);
        taDireccionFisica.setRows(5);
        taDireccionFisica.setToolTipText("Direccion Fisica");
        jScrollPane1.setViewportView(taDireccionFisica);

        txtIdentificacion.setToolTipText("Identificacion");

        lblIdentificacion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblIdentificacion.setForeground(new java.awt.Color(255, 255, 255));
        lblIdentificacion.setText("Identificacion");

        lblApellidos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos.setText("Apellidos");

        lblNombre1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre1.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre1.setText("Nombre");

        lblTelefono1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTelefono1.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono1.setText("Telefono");

        lblTelefono2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTelefono2.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono2.setText("Direccion Fisica");

        txtNombre1.setToolTipText("Nombre");
        txtNombre1.setCaretColor(new java.awt.Color(102, 255, 102));
        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });

        txtPt1Mail.setToolTipText("Nombre");

        lblApellidos1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos1.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos1.setText("Dominio de correo electronico");

        lblApellidos2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos2.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos2.setText("@");

        lblApellidos3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos3.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos3.setText("Encabezado de correo electronico");

        btnActualizarPerfil.setBackground(new java.awt.Color(255, 145, 77));
        btnActualizarPerfil.setText("Actualizar");
        btnActualizarPerfil.setToolTipText("Actualizar");
        btnActualizarPerfil.setColor1(new java.awt.Color(204, 102, 0));
        btnActualizarPerfil.setColor2(new java.awt.Color(204, 102, 0));
        btnActualizarPerfil.setColor3(new java.awt.Color(204, 102, 0));
        btnActualizarPerfil.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnActualizarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPerfilActionPerformed(evt);
            }
        });

        btnRestablecerContra.setBackground(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setText("Restablecer contrasena");
        btnRestablecerContra.setToolTipText("Restablecer contrasena");
        btnRestablecerContra.setColor1(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setColor2(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setColor3(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnRestablecerContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestablecerContraActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Terminal");

        javax.swing.GroupLayout pnPerfilLayout = new javax.swing.GroupLayout(pnPerfil);
        pnPerfil.setLayout(pnPerfilLayout);
        pnPerfilLayout.setHorizontalGroup(
            pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerfilLayout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfilLayout.createSequentialGroup()
                        .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdentificacion)
                            .addComponent(lblTelefono1)
                            .addGroup(pnPerfilLayout.createSequentialGroup()
                                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTelefono)
                                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                    .addComponent(txtPt1Mail))
                                .addGap(35, 35, 35)
                                .addComponent(lblApellidos2))
                            .addComponent(lblApellidos3)
                            .addComponent(lblNombre1)
                            .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblApellidos)
                            .addComponent(lblTelefono2)
                            .addComponent(lblApellidos1)
                            .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtApellidos)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                .addComponent(txtPt2Mail)))
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfilLayout.createSequentialGroup()
                        .addComponent(btnRestablecerContra, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(229, 229, 229))))
            .addGroup(pnPerfilLayout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(lblPerfil6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnPerfilLayout.setVerticalGroup(
            pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerfilLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblPerfil6)
                .addGap(28, 28, 28)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidos)
                    .addComponent(lblNombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono1)
                    .addComponent(lblTelefono2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnPerfilLayout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblIdentificacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(36, 36, 36)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidos1)
                    .addComponent(lblApellidos3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPt1Mail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPt2Mail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidos2))
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestablecerContra, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        pbTabl.addTab("", pnPerfil);

        jPanel3.setBackground(new java.awt.Color(57, 54, 66));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 615, Short.MAX_VALUE)
        );

        pbTabl.addTab("tab5", jPanel3);

        jPanel4.setBackground(new java.awt.Color(57, 54, 66));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Restablecer mi PIN");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ingrese el PIN anterior:");

        txtPinAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPinAnteriorActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ingrese el nuevo PIN:");

        rondedBordes1.setText("Restablecer PIN");
        rondedBordes1.setColor1(new java.awt.Color(126, 217, 87));
        rondedBordes1.setColor2(new java.awt.Color(126, 217, 87));
        rondedBordes1.setColor3(new java.awt.Color(126, 217, 87));
        rondedBordes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rondedBordes1ActionPerformed(evt);
            }
        });

        rondedBordes2.setText("Cancelar");
        rondedBordes2.setColor1(new java.awt.Color(204, 102, 0));
        rondedBordes2.setColor2(new java.awt.Color(204, 102, 0));
        rondedBordes2.setColor3(new java.awt.Color(204, 102, 0));
        rondedBordes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rondedBordes2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(144, 144, 144)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPinAnterior)
                            .addComponent(txtPinNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(rondedBordes1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addComponent(rondedBordes2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel5)
                .addGap(92, 92, 92)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPinAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPinNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rondedBordes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rondedBordes1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94))
        );

        pbTabl.addTab("tab6", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(panelRedondo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(pbTabl)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pbTabl)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelRedondo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(46, 46, 46))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        pbTabl.setSelectedIndex(4);
    }//GEN-LAST:event_jLabel1MouseClicked

        private void cargarDatosPerfil(){
         //coloca la informacion cactual del usuario dentro de los campos de texto
        txtNombre1.setText(inspector.getNombre());
        txtApellidos.setText(inspector.getApellidos());
        txtTelefono.setText(String.valueOf(inspector.getTelefono()));
        taDireccionFisica.setText(inspector.getDireccionFisica());
        txtIdentificacion.setText(inspector.getIdentificacion());
        txtPt1Mail.setText(inspector.getCorreo().getStr1());
        txtPt2Mail.setText(inspector.getCorreo().getStr2());
        txtTerminal.setText(inspector.getTerminalInspeccion());
    
    }
    
    
    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        // TODO add your handling code here:
        pbTabl.setSelectedIndex(3);
        cargarDatosPerfil();
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnRevisarParqueosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRevisarParqueosMouseEntered
        btnRevisarParqueos.setColor1(Color.orange);
        btnRevisarParqueos.setColor2(Color.orange);
        btnRevisarParqueos.setColor3(Color.orange);
        btnRevisarParqueos.setForeground(Color.white);
    }//GEN-LAST:event_btnRevisarParqueosMouseEntered

    private void btnRevisarParqueosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRevisarParqueosMouseExited
        btnRevisarParqueos.setColor1(Color.white);
        btnRevisarParqueos.setColor2(Color.white);
        btnRevisarParqueos.setColor3(Color.white);
        btnRevisarParqueos.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_btnRevisarParqueosMouseExited

    private void btnRevisarParqueosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevisarParqueosActionPerformed
        pbTabl.setSelectedIndex(0);
        
    }//GEN-LAST:event_btnRevisarParqueosActionPerformed

    private void rondedBordes5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rondedBordes5ActionPerformed
         LoginJFrame login = new LoginJFrame();
        
        actualizarInformacion(inspector, inspector.getIdentificacion()); //actualiza la informacion que fue generada por el usuario
        
        inspector=null; //elimina la informacion que apuntaba el usuario
        this.setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_rondedBordes5ActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        pbTabl.setSelectedIndex(1);
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
        btnReportes.setColor1(Color.orange);
        btnReportes.setColor2(Color.orange);
        btnReportes.setColor3(Color.orange);
        btnReportes.setForeground(Color.white);
    }//GEN-LAST:event_btnReportesMouseEntered

    private void btnPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerfilMouseEntered
        btnPerfil.setColor1(Color.orange);
        btnPerfil.setColor2(Color.orange);
        btnPerfil.setColor3(Color.orange);
        btnPerfil.setForeground(Color.white);
    }//GEN-LAST:event_btnPerfilMouseEntered

    private void btnPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerfilMouseExited
        btnPerfil.setColor1(Color.white);
        btnPerfil.setColor2(Color.white);
        btnPerfil.setColor3(Color.white);
        btnPerfil.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_btnPerfilMouseExited

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
        btnReportes.setColor1(Color.white);
        btnReportes.setColor2(Color.white);
        btnReportes.setColor3(Color.white);
        btnReportes.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_btnReportesMouseExited

    private void btnRestablecerContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestablecerContraActionPerformed
            pbTabl.setSelectedIndex(5);
    }//GEN-LAST:event_btnRestablecerContraActionPerformed

        //VALIDACIONES DATOS DE PERFIL
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
   
  public boolean validarTelefono(int telefono) {
        
        //es caso de tener 8 digito su resultado
        if(telefono / 100000000 == 0){
            return true;
        }
        else
            return false;
        
        
    }
  
 public boolean setFechaIngreso(LocalDate fechaIngreso) {
        
        
        LocalDate hoy = LocalDate.now();
        if(fechaIngreso.isBefore(hoy) || fechaIngreso.isEqual(hoy)){
            return true;
        }
        else
            return false;
        
    }
 
 public static boolean validacionIdentificacion(String identificacion) {
        
        if(identificacion.length()>= 2 && identificacion.length() <= 25){
            return true;
        }
        else{
            
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
    
        if(pin.length() == 5){
            return true;
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
   
       private boolean validarTerminalInspeccion(String terminal){
        
        if(terminal.length() == 6)
            return true;
        else
            return false;
    
    }
       
    /**
     * Cambia el PIN del inspector si el PIN anterior es correcto y el nuevo PIN es válido.
     * Verifica que el PIN ingresado sea un número entero de 4 dígitos.
     */
    public void cambiarPin(){
         if (txtPinAnterior.getText().equals(inspector.getPin())){
             try{ //verificar si el pin ingresado es un entero
                 int pinNuevo = Integer.parseInt(txtPinAnterior.getText());
                 if (pinNuevo <= 9999 && pinNuevo >= 1000){
                     //cargamos el pin
                     ArrayList<Persona> listaUsuarios = Login.cargarUsuarios("listaUsuarios.txt");
                     for (Persona cadaUsuario : listaUsuarios){
                         if (cadaUsuario.getIdentificacion().equals(inspector.getIdentificacion())){ //buscamos la cuenta
                             //asignamos el pin al usuario
                             cadaUsuario.setPin(txtPinAnterior.getText());
                             inspector.setPin(txtPinAnterior.getText());
                             //guardamos el pin en los archivos
                             try {
                             Login.guardarUsuarios("listaUsuarios.txt", listaUsuarios);
                             } 
                             catch (IOException ex) {
                               Logger.getLogger(PanelOlvidePin.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             JOptionPane.showMessageDialog(null, "Su PIN ha sido cambiado con éxito!");
                             return;
                         }
                     }
                 }
                 else{
                     JOptionPane.showMessageDialog(null, "El PIN nuevo debe de ser de 4 digitos");
                 }
             }
             catch(NumberFormatException e){
                 JOptionPane.showMessageDialog(null, "El PIN nuevo debe de ser numeros enteros");
             }
         }
         else{
             JOptionPane.showMessageDialog(null, "El PIN anterior ingresado es erróneo");
         }
     }
    
    
    private void btnActualizarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPerfilActionPerformed
        String nombre;
        String apellidos;
        String identificacion;
        String direccionFisica;
        String pt1Correo;
        String pt2Correo;
       String identificacionGeneral;
       String terminal;
        LocalDate fechaIngreso;
        int telefono;
        Login login = new Login();
        if(validarNombre(txtNombre1.getText())){
        
                if(validarApellidos(txtApellidos.getText())){
                    
                   if(validacionDireccionFisica(taDireccionFisica.getText())){
                       
                       if(validacionIdentificacion(txtIdentificacion.getText())){
                           if(validarConversion(txtTelefono.getText())){
                           
                               if(validarTelefono(Integer.parseInt(txtTelefono.getText()))){
                               
                                    if(validarTerminalInspeccion(txtTerminal.getText())){
                                       
                                         if(login.verificarIdentificacion(txtIdentificacion.getText())==null){
                                       
                                            //Almacena los datos de los campos de texto en las variables para ser alamacenadas
                                            nombre = txtNombre1.getText();
                                            apellidos= txtApellidos.getText();
                                            direccionFisica = taDireccionFisica.getText();
                                            identificacion = txtIdentificacion.getText();
                                            pt1Correo = txtPt1Mail.getText();
                                            pt2Correo =  txtPt2Mail.getText();
                                            telefono = Integer.valueOf(txtTelefono.getText());
                                            Correo correo = new Correo(pt1Correo, pt2Correo);
                                            fechaIngreso = LocalDate.now();
                                            terminal =txtTerminal.getText();
                                            if(login.verificarCorreo(correo) == null){
                                                //Almacena la identificacion del usuario anteriormente de ser modificada
                                                identificacionGeneral = inspector.getIdentificacion();

                                                Inspector  inspector = new Inspector(nombre, apellidos,telefono, direccionFisica, fechaIngreso, identificacion,"", terminal,correo);

                                                //modifica la informacion modificada en el archivo de datos
                                                inspector.actualizarDatos(inspector);

                                                 actualizarInformacion(inspector, identificacionGeneral);
                                                  labelBienvenido.setText(inspector.getNombre() + " " + inspector.getApellidos());
                                                 lblId.setText(inspector.getIdentificacion());
                                                JOptionPane.showMessageDialog(null, "Datos actualizados existosamente!");
                                        
                                                String cuerpo = "Nombre: " + inspector.getNombre() + "\n" + "Apellidos: " + inspector.getApellidos() + "\n" + "Direccion fisica: " + inspector.getDireccionFisica() + "\n" + 
                                                "Identificacion: " + inspector.getIdentificacion() + "\n" + "Telefono: " + inspector.getTelefono();
                                                crearEmail(cuerpo, "DATOS ACTUALIZADOS", inspector.getCorreo().getCorreo());
                                                enviarEmail();
                                        
                                            }else{
                                            JOptionPane.showMessageDialog(null, "El correo ya existe!");
                                       }
                                     
                                            }else{
                                            JOptionPane.showMessageDialog(null, "La identificacion ya existe!");
                                       }
                                       }
                                       else{
                                            JOptionPane.showMessageDialog(null, "La terminal debe tener 6 caracteres!");
                                       }
                                   
                               }
                               else{
                                    JOptionPane.showMessageDialog(null, "El telefono debe tener 8 digitos!");
                               }
                           }
                           else{
                                JOptionPane.showMessageDialog(null, "El telefono posee caracteres alfabeticos!");
                           }
                       }
                       else{
                            JOptionPane.showMessageDialog(null, "La identificacion debe tener entre 2 a 25 caracteres!");
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
        else{
             JOptionPane.showMessageDialog(null, "El nombre debe tener entre 2 a 20 caracteres!");
        }
    }//GEN-LAST:event_btnActualizarPerfilActionPerformed

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtNumParqueoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumParqueoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumParqueoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try{
            int placa = Integer.parseInt(txtPlaca.getText());
            int numParqueo = Integer.parseInt(txtNumParqueo.getText());
            if(!verificarPlacaParqueo(placa, numParqueo, parqueo)){ //si es false, quiere decir que se debe de realizar la multa
                pbTabl.setSelectedIndex(2);
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Debe de ingresar numeros enteros");
        }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        pbTabl.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEnviarMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMultaActionPerformed
        int placa = Integer.parseInt(txtPlaca.getText());
        int costo = parqueo.getCostoMulta();
        try {
            Login login = new Login();
            ArrayList<Persona> personas = Login.cargarUsuarios("listaUsuarios.txt");
            login.setListaUsuarios(personas);
            if(login.buscarPlaca(String.valueOf(placa))){
                crearMulta(placa, costo);
            }else{
            
                Multa nuevaMulta = new Multa (costo, txtRazonMulta.getText(), LocalDateTime.now(),null,String.valueOf(placa),inspector);
                 Parqueo pq = new Parqueo();
                 pq.lecturaArchivo();
                 pq.agregarMulta(nuevaMulta);
                 
                 JFileChooser fileChoo = new JFileChooser();
                    File f = new File("Multa");
                    String rutaArchivo = "";
                    fileChoo.setSelectedFile(f);
                    int option = fileChoo.showSaveDialog(this);

                    if(option == JFileChooser.APPROVE_OPTION){

                        f = fileChoo.getSelectedFile();
                        rutaArchivo = f.toString();
                    }
                nuevaMulta.generarPDF(rutaArchivo);
                 JOptionPane.showMessageDialog(null, "Multa generada exitosamente");
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Debe de ingresar numeros enteros");}
        
    }//GEN-LAST:event_btnEnviarMultaActionPerformed

    private void btnDescargarMultaHechaPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarMultaHechaPDFActionPerformed

        Date fechaInicio = dcInicioMultaHecha.getDate();
        Date fechaFinal = dcFinMultaHecha.getDate();
        if (fechaInicio != null) {
            if(fechaFinal!=null){

                if(fechaFinal.after(fechaInicio) || fechaFinal.equals(fechaInicio)){
                    // Convertimos la fecha a LocalDate
                    LocalDate inicio = fechaInicio.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

                    LocalDate finalF = fechaFinal.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

                    JFileChooser fileChoo = new JFileChooser();
                    File f = new File("MultasGeneradas");
                    String rutaArchivo = "";
                    fileChoo.setSelectedFile(f);
                    Parqueo parqueo = new Parqueo();
                    parqueo.lecturaArchivo();
                    int option = fileChoo.showSaveDialog(this);

                    if(option == JFileChooser.APPROVE_OPTION){

                        f = fileChoo.getSelectedFile();
                        rutaArchivo = f.toString();
                    }
                    parqueo.generarMultasHechasInspectorPDF(rutaArchivo, inicio, finalF, inspector.getIdentificacion());
                    JOptionPane.showMessageDialog(null, "Archivo descargado exitosamente!");

                }else{
                    JOptionPane.showMessageDialog(null, "La fecha final debe ser igual o mayor a la fecha de inicio.");
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fecha final.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fecha de inicio.");
        }
    }//GEN-LAST:event_btnDescargarMultaHechaPDFActionPerformed

    private void btnDescargarEspaciosPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarEspaciosPDFActionPerformed

        String opcion = cbEspaciosGeneral.getSelectedItem().toString();
        String nombreArchivo="";
        String ruta;
        Parqueo parqueo = new Parqueo();

        int tipoReporte = 0; //1->general 2->ocupados 3->vacios

        if(opcion == "Todos los espacios"){

            nombreArchivo="EspaciosGeneral";
            tipoReporte=1;

        }else if (opcion == "Espacios ocupados"){

            nombreArchivo="EspaciosOcupados";
            tipoReporte=2;

        }else if(opcion == "Espacios vacios"){

            nombreArchivo = "EspacioVacios";
            tipoReporte=3;
        }

        JFileChooser fileChoo = new JFileChooser();
        File f = new File(nombreArchivo);
        String rutaArchivo = "";
        fileChoo.setSelectedFile(f);
        int option = fileChoo.showSaveDialog(this);

        if(option == JFileChooser.APPROVE_OPTION){

            f = fileChoo.getSelectedFile();
            rutaArchivo = f.toString();
        }

        if(tipoReporte==1)
        parqueo.generarEspaciosGeneralPDF(rutaArchivo);
        else if(tipoReporte ==2)
        parqueo.generarEspaciosOcupadosPDF(rutaArchivo);
        else if (tipoReporte==3)
        parqueo.generarEspaciosVaciosPDF(rutaArchivo);

        if(tipoReporte!= 0)
        JOptionPane.showMessageDialog(null, "Archivo descargado exitosamente!");
    }//GEN-LAST:event_btnDescargarEspaciosPDFActionPerformed

    private void cbEspaciosGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEspaciosGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEspaciosGeneralActionPerformed

    private void cbEspaciosGeneralItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEspaciosGeneralItemStateChanged

        Parqueo parqueo = new Parqueo();
        parqueo.lecturaArchivo();

        if(cbEspaciosGeneral.getSelectedItem() == "Todos los espacios"){

            generarReporteGeneralEspacios(parqueo.getEspacios());

        }
        else  if(cbEspaciosGeneral.getSelectedItem() == "Espacios vacios"){

            generarReporteGeneralEspaciosVacios(parqueo.listarEspaciosVacios());

        }else  if(cbEspaciosGeneral.getSelectedItem() == "Espacios ocupados"){

            generarReporteGeneralEspaciosOcupados(parqueo.listarEspaciosOcupados());

        }

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mdlEspaciosGeneral);
        tblEspaciosGeneral.setRowSorter(sorter);
        sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
    }//GEN-LAST:event_cbEspaciosGeneralItemStateChanged

    private void txtPinAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPinAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPinAnteriorActionPerformed

    private void rondedBordes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rondedBordes1ActionPerformed
        cambiarPin();
    }//GEN-LAST:event_rondedBordes1ActionPerformed

    private void rondedBordes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rondedBordes2ActionPerformed
        pbTabl.setSelectedIndex(3);
    }//GEN-LAST:event_rondedBordes2ActionPerformed

    /**
     * Genera un reporte general de los espacios de parqueo.
     * Carga la información de los espacios en la tabla de reporte general.
     */
     private void generarReporteGeneralEspacios(List<Espacio>  espacios){
        
        int cantidadEspacios=0;
        String estado;
        String identificadores [] = {"Numero", "Estado"};
        mdlEspaciosGeneral.setColumnIdentifiers(identificadores);
        
        tblEspaciosGeneral.setModel(mdlEspaciosGeneral);

        mdlEspaciosGeneral.getDataVector().removeAllElements();
            
        for(Espacio obj : espacios){
                
            if(obj.getEstado())
                  estado="Libre";
            else
                  estado="Ocupado";
                
            mdlEspaciosGeneral.addRow(new Object[]{obj.getNumero(), estado});
            cantidadEspacios++;
                
        }
    
        lblCantidadEspacios.setText(String.valueOf(cantidadEspacios));
    }
    
        /**
         * Genera un reporte general de los espacios de parqueo vacíos.
         * Carga la información de los espacios vacíos en la tabla de reporte general.
         *
         * @param espacios La lista de espacios de parqueo a reportar.
         */
        private void generarReporteGeneralEspaciosVacios(List<Espacio>  espacios){
        
        int cantidadEspacios=0;
        String estado = "Vacio";
        String identificadores [] = {"Numero", "Estado"};
        mdlEspaciosGeneral.setColumnIdentifiers(identificadores);
        
        tblEspaciosGeneral.setModel(mdlEspaciosGeneral);

        mdlEspaciosGeneral.getDataVector().removeAllElements();
            
        for(Espacio obj : espacios){
                
            mdlEspaciosGeneral.addRow(new Object[]{obj.getNumero(), estado});
            cantidadEspacios++;
                
        }
    
        lblCantidadEspacios.setText(String.valueOf(cantidadEspacios));
    }
    
        /**
         * Genera un reporte general de los espacios de parqueo ocupados.
         * Carga la información de los espacios ocupados en la tabla de reporte general.
         *
         * @param espacios La lista de espacios de parqueo a reportar.
         */ 
        private void generarReporteGeneralEspaciosOcupados(List<Espacio>  espacios){
        
        int cantidadEspacios=0;
        String identificadores [] = {"Numero", "Placa","Costo", "TiempoInicial","TiempoFinal"};
        
         mdlEspaciosGeneral.getDataVector().removeAllElements();
        mdlEspaciosGeneral.setColumnIdentifiers(identificadores);
        
        tblEspaciosGeneral.setModel(mdlEspaciosGeneral);

        mdlEspaciosGeneral.getDataVector().removeAllElements();
            
        
        for(Espacio obj : espacios){
             if(obj.getVehiculos()!=null){
                  Vehiculo vehiculo = obj.getVehiculos().getFirst();
                    TicketParqueo ticket = vehiculo.getTicketVigente();
                    mdlEspaciosGeneral.addRow(new Object[]{obj.getNumero(), vehiculo.getPlaca(), vehiculo.getTicketVigente().getTotal(), ticket.getHoraSistema(), ticket.getHoraSistema().plusMinutes(ticket.getTiempoParqueo())});
                    cantidadEspacios++;
                 
             }
           
                
        }
    
        lblCantidadEspacios.setText(String.valueOf(cantidadEspacios));
    }
    
    
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
            java.util.logging.Logger.getLogger(MenuInspector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuInspector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuInspector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuInspector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuInspector(inspector, parqueo).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnActualizarPerfil;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnDescargarEspaciosPDF;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnDescargarMultaHechaPDF;
    private javax.swing.JButton btnEnviarMulta;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnPerfil;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnReportes;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnRestablecerContra;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnRevisarParqueos;
    private javax.swing.JComboBox<String> cbEspaciosGeneral;
    private com.toedter.calendar.JDateChooser dcFinMultaHecha;
    private com.toedter.calendar.JDateChooser dcInicioMultaHecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelBienvenido;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblApellidos1;
    private javax.swing.JLabel lblApellidos2;
    private javax.swing.JLabel lblApellidos3;
    private javax.swing.JLabel lblCantidadEspacios;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblPerfil1;
    private javax.swing.JLabel lblPerfil3;
    private javax.swing.JLabel lblPerfil5;
    private javax.swing.JLabel lblPerfil6;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JLabel lblTelefono2;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo panelRedondo1;
    private javax.swing.JTabbedPane pbTabl;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnParquear;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnPerfil;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnPrincipal;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnReportes;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes rondedBordes1;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes rondedBordes2;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes rondedBordes5;
    private javax.swing.JTextArea taDireccionFisica;
    private javax.swing.JTable tblEspaciosGeneral;
    private javax.swing.JTabbedPane tpReportes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNumParqueo;
    private javax.swing.JTextField txtPinAnterior;
    private javax.swing.JTextField txtPinNuevo;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtPt1Mail;
    private javax.swing.JTextField txtPt2Mail;
    private javax.swing.JTextArea txtRazonMulta;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTerminal;
    // End of variables declaration//GEN-END:variables
}
