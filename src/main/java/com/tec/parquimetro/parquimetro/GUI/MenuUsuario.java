/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.tec.parquimetro.parquimetro.GUI;

import com.tec.parquimetro.parquimetro.Clases.Correo;
import com.tec.parquimetro.parquimetro.Clases.Espacio;
import com.tec.parquimetro.parquimetro.Clases.Login;
import com.tec.parquimetro.parquimetro.Clases.Parqueo;
import com.tec.parquimetro.parquimetro.Clases.Tarjeta;
import com.tec.parquimetro.parquimetro.Clases.TicketParqueo;
import com.tec.parquimetro.parquimetro.Clases.Usuario;
import com.tec.parquimetro.parquimetro.Clases.Vehiculo;
import com.tec.parquimetro.parquimetro.GUI.Componentes.RenderTable;
import static com.tec.parquimetro.parquimetro.GUI.MenuInspector.inspector;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
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



public class MenuUsuario extends javax.swing.JFrame {

    /**
     * Creates new form MenuUsuario
     */
     DefaultTableModel mdlEspaciosGeneral = new DefaultTableModel();//formato para tabal espacios en reporte general de espacios
     int precioMinuto = 0;
     public static Usuario usuario = new Usuario();
     public static int columna, row;
     
    //ATRIBUTOS PARA ENVIAR REPORTES
    private static String emailDe = "paquimetrocartago@gmail.com";
    private static String contraseñaDe = "vofx ztal oawe yary";
    private static String emailPara;
    
    private Properties mProperties = new Properties();
    private Session mSession;
    private MimeMessage mCorreo;
    //FIN DE ATRIBUTOS DE REPORTES
             
    public MenuUsuario(Usuario pusuario) {
        
        initComponents();
        

        
        //Inicializa los elementos de tipo tab panel, elimina los tabs visibles al usuario
         pbTabl.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0; // Hacer la altura de las pestañas cero para ocultarlas
            }
         });
         
        tpReportes.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0; // Hacer la altura de las pestañas cero para ocultarlas
            }
         });
         tpReportes.setBorder(BorderFactory.createEmptyBorder());
         
         
         tpParquearEspacio.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0; // Hacer la altura de las pestañas cero para ocultarlas
            }
         });
         tpParquearEspacio.setBorder(BorderFactory.createEmptyBorder());
         //Finaliza la modificacion de los tab
         
         //Actualia el usuario global para poder acceder a el desde otros metodos
        usuario = pusuario;
        //Actualiza los datos del nombre e identificacion del usuario dentro del menu del usuario
        labelBienvenido.setText(pusuario.getNombre() + " " + pusuario.getApellidos());
        lblId.setText(pusuario.getIdentificacion());
         
        
        pbTabl.setSelectedIndex(3); //muestra el tab 3 (inicio)
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
     
     //FUNCION PARA ENVIAR LOS EMAILS
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

        textArea1 = new java.awt.TextArea();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        panelRedondo1 = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblTiuloPrincipal = new javax.swing.JLabel();
        btnPerfil = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnParquear = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        BtnVehiculos = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnCerrarSesion = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnReportes = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        labelBienvenido = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        BtnParqueos1 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        pbTabl = new javax.swing.JTabbedPane();
        pnVehiculos = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVehiculo = new javax.swing.JTable();
        btnAgregarVehiculo = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        tpPanelModificaciones = new javax.swing.JTabbedPane();
        pnAgregarVehiculo2 = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        btnVolverVehiculos2 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnConfirmarAgregarVehiculo = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        txtPlacaAgregar = new javax.swing.JTextField();
        txtModeloAgregar = new javax.swing.JTextField();
        txtMarcaAgregar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        panelRedondo2 = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        btnVolverVehiculos3 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnConfirmarActualzacionVehiculo = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        jLabel9 = new javax.swing.JLabel();
        txtPlacaActualizar = new javax.swing.JTextField();
        txtMarcaActualizar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtModeloActualizar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblPlaccaActualizar = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        pnReportes = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        jLabel8 = new javax.swing.JLabel();
        cbReportes = new javax.swing.JComboBox<>();
        tpReportes = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblEspaciosGeneral = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        lblCantidadEspacios = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pnMisParqueos = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMisParqueos = new javax.swing.JTable();
        pnlAgregarTiempo = new javax.swing.JPanel();
        spnAcumuladoExtra = new javax.swing.JSpinner();
        btnConfirmarTiempoExtra = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnConsultarOtroEspacio1 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        jLabel4 = new javax.swing.JLabel();
        lblEspacioExtra = new javax.swing.JLabel();
        lblTexto = new javax.swing.JLabel();
        lblTextoTiempo5 = new javax.swing.JLabel();
        spnTiempoExtra = new javax.swing.JSpinner();
        lblTiempoTotal = new javax.swing.JLabel();
        lblVehiculoExtra = new javax.swing.JLabel();
        pnPrincipal = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil5 = new javax.swing.JLabel();
        pnPerfil1 = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil6 = new javax.swing.JLabel();
        lblNombre2 = new javax.swing.JLabel();
        txtNumeroTarjeta = new javax.swing.JTextField();
        btnActualizarTarjeta = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnVolverPerfil = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        lblNombre3 = new javax.swing.JLabel();
        lblNombre4 = new javax.swing.JLabel();
        lblNombre5 = new javax.swing.JLabel();
        txtCodigoTarjeta = new javax.swing.JTextField();
        lblNombre6 = new javax.swing.JLabel();
        spnAnioTarjeta = new com.toedter.calendar.JYearChooser();
        spnMesTarjeta = new com.toedter.calendar.JMonthChooser();
        pnParquear = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        jLabel3 = new javax.swing.JLabel();
        txtEspacioConsultado = new javax.swing.JTextField();
        btnConsultarEspacio = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        tpParquearEspacio = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        lblEspacioDisponible = new javax.swing.JLabel();
        btnParquearEspacio = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnConsultarOtroEspacio = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        cbPlacasVehiculo = new javax.swing.JComboBox<>();
        lblPlaca = new javax.swing.JLabel();
        lblTotaltITULO = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        spnTiempoParqueo = new javax.swing.JSpinner();
        lblTextoTiempo2 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        spnTiempoAcumulado = new javax.swing.JSpinner();
        lblTextoTiempo1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTiempoAcumulado = new javax.swing.JLabel();
        lblTextoTiempo3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblPerfil16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblPerfil1 = new javax.swing.JLabel();
        lblEspacio = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnRepostarEspacio = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        jPanel6 = new javax.swing.JPanel();
        lblPerfil17 = new javax.swing.JLabel();
        pnPerfil = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil = new javax.swing.JLabel();
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
        btnMetodoPago = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Parquimetro");
        setBackground(new java.awt.Color(29, 24, 39));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(29, 24, 39));

        panelRedondo1.setBackground(new java.awt.Color(57, 54, 66));
        panelRedondo1.setRoundBottomLeft(30);
        panelRedondo1.setRoundBottomRight(30);
        panelRedondo1.setRoundTopLeft(30);
        panelRedondo1.setRoundTopRight(30);

        lblTiuloPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        lblTiuloPrincipal.setFont(new java.awt.Font("Leelawadee", 1, 24)); // NOI18N
        lblTiuloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTiuloPrincipal.setText("Parquimetro");
        lblTiuloPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTiuloPrincipalMouseClicked(evt);
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

        btnParquear.setForeground(new java.awt.Color(0, 0, 51));
        btnParquear.setText("Parquear");
        btnParquear.setColor1(new java.awt.Color(255, 255, 255));
        btnParquear.setColor2(new java.awt.Color(255, 255, 255));
        btnParquear.setColor3(new java.awt.Color(204, 204, 204));
        btnParquear.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnParquear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnParquearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnParquearMouseExited(evt);
            }
        });
        btnParquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParquearActionPerformed(evt);
            }
        });

        BtnVehiculos.setForeground(new java.awt.Color(0, 0, 51));
        BtnVehiculos.setText("Mis Vehiculos");
        BtnVehiculos.setColor1(new java.awt.Color(255, 255, 255));
        BtnVehiculos.setColor2(new java.awt.Color(255, 255, 255));
        BtnVehiculos.setColor3(new java.awt.Color(204, 204, 204));
        BtnVehiculos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BtnVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnVehiculosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnVehiculosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnVehiculosMouseExited(evt);
            }
        });
        BtnVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVehiculosActionPerformed(evt);
            }
        });

        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.setToolTipText("");
        btnCerrarSesion.setColor1(new java.awt.Color(126, 217, 87));
        btnCerrarSesion.setColor2(new java.awt.Color(126, 217, 87));
        btnCerrarSesion.setColor3(new java.awt.Color(126, 217, 87));
        btnCerrarSesion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
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

        BtnParqueos1.setForeground(new java.awt.Color(0, 0, 51));
        BtnParqueos1.setText("Mis Parqueos");
        BtnParqueos1.setColor1(new java.awt.Color(255, 255, 255));
        BtnParqueos1.setColor2(new java.awt.Color(255, 255, 255));
        BtnParqueos1.setColor3(new java.awt.Color(204, 204, 204));
        BtnParqueos1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BtnParqueos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnParqueos1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnParqueos1MouseExited(evt);
            }
        });
        BtnParqueos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnParqueos1ActionPerformed(evt);
            }
        });

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
                        .addComponent(lblTiuloPrincipal)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondo1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRedondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReportes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRedondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addComponent(btnParquear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnVehiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnParqueos1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        panelRedondo1Layout.setVerticalGroup(
            panelRedondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondo1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblTiuloPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblId)
                .addGap(33, 33, 33)
                .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnParquear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(BtnParqueos1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(BtnVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        pbTabl.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        pbTabl.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        pbTabl.setToolTipText("");

        pnVehiculos.setBackground(new java.awt.Color(57, 54, 66));
        pnVehiculos.setRoundBottomLeft(15);
        pnVehiculos.setRoundBottomRight(15);
        pnVehiculos.setRoundTopLeft(15);
        pnVehiculos.setRoundTopRight(15);

        lblPerfil2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil2.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil2.setText("Vehiculos");

        tblVehiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Estado", "Actualizar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVehiculoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVehiculo);

        btnAgregarVehiculo.setText("Agregar");
        btnAgregarVehiculo.setColor1(new java.awt.Color(204, 102, 0));
        btnAgregarVehiculo.setColor2(new java.awt.Color(204, 102, 0));
        btnAgregarVehiculo.setColor3(new java.awt.Color(204, 102, 0));
        btnAgregarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVehiculoActionPerformed(evt);
            }
        });

        pnAgregarVehiculo2.setBackground(new java.awt.Color(29, 24, 39));
        pnAgregarVehiculo2.setRoundBottomLeft(15);
        pnAgregarVehiculo2.setRoundBottomRight(15);
        pnAgregarVehiculo2.setRoundTopLeft(15);
        pnAgregarVehiculo2.setRoundTopRight(15);

        btnVolverVehiculos2.setBackground(new java.awt.Color(102, 102, 102));
        btnVolverVehiculos2.setText("Volver");
        btnVolverVehiculos2.setColor1(new java.awt.Color(102, 102, 102));
        btnVolverVehiculos2.setColor2(new java.awt.Color(102, 102, 102));
        btnVolverVehiculos2.setColor3(new java.awt.Color(102, 102, 102));
        btnVolverVehiculos2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVolverVehiculos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverVehiculosActionPerformed(evt);
            }
        });

        btnConfirmarAgregarVehiculo.setBackground(new java.awt.Color(0, 102, 153));
        btnConfirmarAgregarVehiculo.setText("Agregar");
        btnConfirmarAgregarVehiculo.setColor1(new java.awt.Color(0, 102, 153));
        btnConfirmarAgregarVehiculo.setColor2(new java.awt.Color(0, 102, 153));
        btnConfirmarAgregarVehiculo.setColor3(new java.awt.Color(0, 102, 153));
        btnConfirmarAgregarVehiculo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnConfirmarAgregarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarTiempoExtra1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Marca:");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Placa:");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Modelo:");

        jLabel16.setText("Opcional");

        jLabel19.setText("Opcional");

        javax.swing.GroupLayout pnAgregarVehiculo2Layout = new javax.swing.GroupLayout(pnAgregarVehiculo2);
        pnAgregarVehiculo2.setLayout(pnAgregarVehiculo2Layout);
        pnAgregarVehiculo2Layout.setHorizontalGroup(
            pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAgregarVehiculo2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAgregarVehiculo2Layout.createSequentialGroup()
                        .addGroup(pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(23, 23, 23)
                        .addGroup(pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMarcaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlacaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModeloAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAgregarVehiculo2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(208, 208, 208)))
                .addGroup(pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmarAgregarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolverVehiculos2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
            .addGroup(pnAgregarVehiculo2Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnAgregarVehiculo2Layout.setVerticalGroup(
            pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAgregarVehiculo2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlacaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnAgregarVehiculo2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmarAgregarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVolverVehiculos2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))
                    .addGroup(pnAgregarVehiculo2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMarcaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(11, 11, 11)
                        .addGroup(pnAgregarVehiculo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtModeloAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tpPanelModificaciones.addTab("tab1", pnAgregarVehiculo2);

        panelRedondo2.setBackground(new java.awt.Color(29, 24, 39));
        panelRedondo2.setRoundBottomLeft(15);
        panelRedondo2.setRoundBottomRight(15);
        panelRedondo2.setRoundTopLeft(15);
        panelRedondo2.setRoundTopRight(15);

        btnVolverVehiculos3.setBackground(new java.awt.Color(102, 102, 102));
        btnVolverVehiculos3.setText("Volver");
        btnVolverVehiculos3.setColor1(new java.awt.Color(102, 102, 102));
        btnVolverVehiculos3.setColor2(new java.awt.Color(102, 102, 102));
        btnVolverVehiculos3.setColor3(new java.awt.Color(102, 102, 102));
        btnVolverVehiculos3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVolverVehiculos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverVehiculos3btnVolverVehiculosActionPerformed(evt);
            }
        });

        btnConfirmarActualzacionVehiculo.setBackground(new java.awt.Color(0, 102, 153));
        btnConfirmarActualzacionVehiculo.setText("Actualizar");
        btnConfirmarActualzacionVehiculo.setColor1(new java.awt.Color(0, 102, 153));
        btnConfirmarActualzacionVehiculo.setColor2(new java.awt.Color(0, 102, 153));
        btnConfirmarActualzacionVehiculo.setColor3(new java.awt.Color(0, 102, 153));
        btnConfirmarActualzacionVehiculo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnConfirmarActualzacionVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActualzacionVehiculobtnConfirmarTiempoExtra1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Placa:");

        txtPlacaActualizar.setText("jTextField1");

        txtMarcaActualizar.setText("jTextField3");

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Marca:");
        jLabel12.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Modelo:");

        txtModeloActualizar.setText("jTextField2");

        jLabel14.setText("Opcional");

        jLabel15.setText("Opcional");

        lblPlaccaActualizar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblPlaccaActualizar.setForeground(new java.awt.Color(255, 255, 255));
        lblPlaccaActualizar.setText("00");

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Placa:");

        javax.swing.GroupLayout panelRedondo2Layout = new javax.swing.GroupLayout(panelRedondo2);
        panelRedondo2.setLayout(panelRedondo2Layout);
        panelRedondo2Layout.setHorizontalGroup(
            panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondo2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondo2Layout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelRedondo2Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(197, 197, 197)
                            .addComponent(btnVolverVehiculos3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRedondo2Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPlaccaActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnConfirmarActualzacionVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(84, 84, 84))
            .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRedondo2Layout.createSequentialGroup()
                    .addGap(73, 73, 73)
                    .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
                        .addComponent(jLabel9))
                    .addGap(32, 32, 32)
                    .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMarcaActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlacaActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtModeloActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(348, Short.MAX_VALUE)))
        );
        panelRedondo2Layout.setVerticalGroup(
            panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondo2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlaccaActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConfirmarActualzacionVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverVehiculos3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRedondo2Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlacaActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                    .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMarcaActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(33, 33, 33)
                    .addComponent(txtModeloActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(46, 46, 46)))
        );

        tpPanelModificaciones.addTab("tab2", panelRedondo2);

        javax.swing.GroupLayout pnVehiculosLayout = new javax.swing.GroupLayout(pnVehiculos);
        pnVehiculos.setLayout(pnVehiculosLayout);
        pnVehiculosLayout.setHorizontalGroup(
            pnVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnVehiculosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPerfil2)
                .addGap(379, 379, 379))
            .addGroup(pnVehiculosLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(pnVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnVehiculosLayout.createSequentialGroup()
                        .addComponent(tpPanelModificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(pnVehiculosLayout.createSequentialGroup()
                        .addGroup(pnVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAgregarVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(64, 64, 64))))
        );
        pnVehiculosLayout.setVerticalGroup(
            pnVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVehiculosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblPerfil2)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpPanelModificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pbTabl.addTab("", pnVehiculos);

        pnReportes.setBackground(new java.awt.Color(57, 54, 66));
        pnReportes.setRoundBottomLeft(15);
        pnReportes.setRoundBottomRight(15);
        pnReportes.setRoundTopLeft(15);
        pnReportes.setRoundTopRight(15);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Reportes");

        cbReportes.setBackground(new java.awt.Color(70, 70, 70));
        cbReportes.setForeground(new java.awt.Color(255, 255, 255));
        cbReportes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Parqueos Disponibles", "Espacios utilizados", "Multas generadas" }));
        cbReportes.setSelectedIndex(-1);
        cbReportes.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        cbReportes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbReportesItemStateChanged(evt);
            }
        });
        cbReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbReportesActionPerformed(evt);
            }
        });

        tpReportes.setBackground(new java.awt.Color(57, 54, 66));

        jPanel2.setBackground(new java.awt.Color(29, 24, 39));

        tblEspaciosGeneral.setBackground(new java.awt.Color(204, 204, 204));
        tblEspaciosGeneral.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        tblEspaciosGeneral.setEnabled(false);
        tblEspaciosGeneral.setGridColor(new java.awt.Color(126, 217, 87));
        jScrollPane5.setViewportView(tblEspaciosGeneral);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Espacios considerados en el reporte:");

        lblCantidadEspacios.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidadEspacios.setText("-");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Parqueos Disponibles");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(lblCantidadEspacios))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 52, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblCantidadEspacios))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        tpReportes.addTab("Espacios", jPanel2);

        jPanel3.setBackground(new java.awt.Color(57, 54, 66));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );

        tpReportes.addTab("principal", jPanel3);

        tpReportes.setSelectedIndex(1);

        javax.swing.GroupLayout pnReportesLayout = new javax.swing.GroupLayout(pnReportes);
        pnReportes.setLayout(pnReportesLayout);
        pnReportesLayout.setHorizontalGroup(
            pnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnReportesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tpReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(cbReportes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnReportesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addGap(339, 339, 339)))
                .addGap(22, 22, 22))
        );
        pnReportesLayout.setVerticalGroup(
            pnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnReportesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpReportes)
                .addGap(19, 19, 19))
        );

        pbTabl.addTab("", pnReportes);

        pnMisParqueos.setBackground(new java.awt.Color(57, 54, 66));
        pnMisParqueos.setRoundBottomLeft(15);
        pnMisParqueos.setRoundBottomRight(15);
        pnMisParqueos.setRoundTopLeft(15);
        pnMisParqueos.setRoundTopRight(15);

        lblPerfil4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil4.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil4.setText("Mis parqueos");

        tblMisParqueos = new javax.swing.JTable(){

            public boolean isCellEditable(int row, int column){
                return false;
            }

        };
        tblMisParqueos.setBackground(new java.awt.Color(102, 102, 102));
        tblMisParqueos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tblMisParqueos.setForeground(new java.awt.Color(255, 255, 255));
        tblMisParqueos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Espacio", "Tiempo Restante", "AgregarTiempo", "Desaparcar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMisParqueos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMisParqueosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMisParqueos);

        pnlAgregarTiempo.setBackground(new java.awt.Color(29, 24, 39));

        spnAcumuladoExtra.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnAcumuladoExtraStateChanged(evt);
            }
        });

        btnConfirmarTiempoExtra.setBackground(new java.awt.Color(0, 102, 153));
        btnConfirmarTiempoExtra.setText("Agregar");
        btnConfirmarTiempoExtra.setColor1(new java.awt.Color(0, 102, 153));
        btnConfirmarTiempoExtra.setColor2(new java.awt.Color(0, 102, 153));
        btnConfirmarTiempoExtra.setColor3(new java.awt.Color(0, 102, 153));
        btnConfirmarTiempoExtra.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnConfirmarTiempoExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarTiempoExtraActionPerformed(evt);
            }
        });

        btnConsultarOtroEspacio1.setBackground(new java.awt.Color(102, 102, 102));
        btnConsultarOtroEspacio1.setText("Volver");
        btnConsultarOtroEspacio1.setColor1(new java.awt.Color(102, 102, 102));
        btnConsultarOtroEspacio1.setColor2(new java.awt.Color(102, 102, 102));
        btnConsultarOtroEspacio1.setColor3(new java.awt.Color(102, 102, 102));
        btnConsultarOtroEspacio1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnConsultarOtroEspacio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarOtroEspacio1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Agregue el tiempo extra:");

        lblEspacioExtra.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblEspacioExtra.setForeground(new java.awt.Color(255, 255, 255));
        lblEspacioExtra.setText("Espacio:");

        lblTexto.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTexto.setForeground(new java.awt.Color(255, 255, 255));
        lblTexto.setText("Placa:");

        lblTextoTiempo5.setForeground(new java.awt.Color(255, 255, 255));
        lblTextoTiempo5.setText("Tiempo acumulado a utilizar");

        spnTiempoExtra.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTiempoExtraStateChanged(evt);
            }
        });

        lblTiempoTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTiempoTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTiempoTotal.setText("Tiempo total:");

        lblVehiculoExtra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblVehiculoExtra.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlAgregarTiempoLayout = new javax.swing.GroupLayout(pnlAgregarTiempo);
        pnlAgregarTiempo.setLayout(pnlAgregarTiempoLayout);
        pnlAgregarTiempoLayout.setHorizontalGroup(
            pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarTiempoLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEspacioExtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTextoTiempo5, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addGroup(pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlAgregarTiempoLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnAcumuladoExtra, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(spnTiempoExtra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConfirmarTiempoExtra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsultarOtroEspacio1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlAgregarTiempoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTexto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVehiculoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTiempoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        pnlAgregarTiempoLayout.setVerticalGroup(
            pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarTiempoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEspacioExtra)
                    .addComponent(lblTexto)
                    .addComponent(lblTiempoTotal)
                    .addComponent(lblVehiculoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spnTiempoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConfirmarTiempoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(pnlAgregarTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnAcumuladoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTextoTiempo5)
                    .addComponent(btnConsultarOtroEspacio1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout pnMisParqueosLayout = new javax.swing.GroupLayout(pnMisParqueos);
        pnMisParqueos.setLayout(pnMisParqueosLayout);
        pnMisParqueosLayout.setHorizontalGroup(
            pnMisParqueosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMisParqueosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPerfil4)
                .addGap(368, 368, 368))
            .addGroup(pnMisParqueosLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pnMisParqueosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(pnlAgregarTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        pnMisParqueosLayout.setVerticalGroup(
            pnMisParqueosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMisParqueosLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblPerfil4)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAgregarTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pbTabl.addTab("", pnMisParqueos);

        pnPrincipal.setBackground(new java.awt.Color(57, 54, 66));
        pnPrincipal.setRoundBottomLeft(15);
        pnPrincipal.setRoundBottomRight(15);
        pnPrincipal.setRoundTopLeft(15);
        pnPrincipal.setRoundTopRight(15);

        lblPerfil5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil5.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil5.setText("PARQUIMETRO");

        javax.swing.GroupLayout pnPrincipalLayout = new javax.swing.GroupLayout(pnPrincipal);
        pnPrincipal.setLayout(pnPrincipalLayout);
        pnPrincipalLayout.setHorizontalGroup(
            pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrincipalLayout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(lblPerfil5)
                .addContainerGap(386, Short.MAX_VALUE))
        );
        pnPrincipalLayout.setVerticalGroup(
            pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrincipalLayout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(lblPerfil5)
                .addContainerGap(388, Short.MAX_VALUE))
        );

        pbTabl.addTab("", pnPrincipal);

        pnPerfil1.setBackground(new java.awt.Color(57, 54, 66));
        pnPerfil1.setRoundBottomLeft(15);
        pnPerfil1.setRoundBottomRight(15);
        pnPerfil1.setRoundTopLeft(15);
        pnPerfil1.setRoundTopRight(15);

        lblPerfil6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil6.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil6.setText("Metodo de pago");

        lblNombre2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre2.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre2.setText("Año");

        txtNumeroTarjeta.setBackground(new java.awt.Color(70, 70, 70));
        txtNumeroTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        txtNumeroTarjeta.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        btnActualizarTarjeta.setBackground(new java.awt.Color(255, 145, 77));
        btnActualizarTarjeta.setText("Actualizar");
        btnActualizarTarjeta.setColor1(new java.awt.Color(204, 102, 0));
        btnActualizarTarjeta.setColor2(new java.awt.Color(204, 102, 0));
        btnActualizarTarjeta.setColor3(new java.awt.Color(204, 102, 0));
        btnActualizarTarjeta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnActualizarTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTarjetaActionPerformed(evt);
            }
        });

        btnVolverPerfil.setBackground(new java.awt.Color(0, 102, 153));
        btnVolverPerfil.setText("Volver");
        btnVolverPerfil.setColor1(new java.awt.Color(0, 102, 153));
        btnVolverPerfil.setColor2(new java.awt.Color(0, 102, 153));
        btnVolverPerfil.setColor3(new java.awt.Color(0, 102, 153));
        btnVolverPerfil.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnVolverPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverPerfilActionPerformed(evt);
            }
        });

        lblNombre3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre3.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre3.setText("Numero de tarjeta");

        lblNombre4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre4.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre4.setText("Fecha de vencimiento");

        lblNombre5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre5.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre5.setText("Mes");

        txtCodigoTarjeta.setBackground(new java.awt.Color(70, 70, 70));
        txtCodigoTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        txtCodigoTarjeta.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        lblNombre6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre6.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre6.setText("Codigo de validacion");

        javax.swing.GroupLayout pnPerfil1Layout = new javax.swing.GroupLayout(pnPerfil1);
        pnPerfil1.setLayout(pnPerfil1Layout);
        pnPerfil1Layout.setHorizontalGroup(
            pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerfil1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfil1Layout.createSequentialGroup()
                        .addGroup(pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNombre3)
                            .addComponent(txtNumeroTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfil1Layout.createSequentialGroup()
                                .addGroup(pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnPerfil1Layout.createSequentialGroup()
                                        .addComponent(spnAnioTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(spnMesTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(112, 112, 112))
                                    .addGroup(pnPerfil1Layout.createSequentialGroup()
                                        .addComponent(lblNombre4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre6)
                                    .addComponent(txtCodigoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(94, 94, 94))
                    .addGroup(pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfil1Layout.createSequentialGroup()
                            .addComponent(lblPerfil6)
                            .addGap(323, 323, 323))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfil1Layout.createSequentialGroup()
                            .addComponent(btnVolverPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnActualizarTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(248, 248, 248)))
                    .addGroup(pnPerfil1Layout.createSequentialGroup()
                        .addComponent(lblNombre2)
                        .addGap(181, 181, 181)
                        .addComponent(lblNombre5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnPerfil1Layout.setVerticalGroup(
            pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerfil1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblPerfil6)
                .addGap(30, 30, 30)
                .addComponent(lblNombre3)
                .addGap(18, 18, 18)
                .addComponent(txtNumeroTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addGroup(pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre4)
                    .addComponent(lblNombre6))
                .addGap(18, 18, 18)
                .addGroup(pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnAnioTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnMesTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre2)
                    .addComponent(lblNombre5))
                .addGap(74, 74, 74)
                .addGroup(pnPerfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(201, Short.MAX_VALUE))
        );

        pbTabl.addTab("", pnPerfil1);

        pnParquear.setBackground(new java.awt.Color(57, 54, 66));
        pnParquear.setRoundBottomLeft(15);
        pnParquear.setRoundBottomRight(15);
        pnParquear.setRoundTopLeft(15);
        pnParquear.setRoundTopRight(15);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ingrese el numero de espacio en el que desea parquear");

        txtEspacioConsultado.setBackground(new java.awt.Color(70, 70, 70));
        txtEspacioConsultado.setForeground(new java.awt.Color(255, 255, 255));
        txtEspacioConsultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEspacioConsultado.setToolTipText("");
        txtEspacioConsultado.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));

        btnConsultarEspacio.setBackground(new java.awt.Color(255, 102, 0));
        btnConsultarEspacio.setText("Consultar espacio");
        btnConsultarEspacio.setColor1(new java.awt.Color(255, 102, 0));
        btnConsultarEspacio.setColor2(new java.awt.Color(255, 102, 0));
        btnConsultarEspacio.setColor3(new java.awt.Color(255, 102, 0));
        btnConsultarEspacio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnConsultarEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarEspacioActionPerformed(evt);
            }
        });

        tpParquearEspacio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel5.setBackground(new java.awt.Color(29, 24, 39));

        lblEspacioDisponible.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblEspacioDisponible.setForeground(new java.awt.Color(255, 255, 255));
        lblEspacioDisponible.setText("Espacio:");

        btnParquearEspacio.setBackground(new java.awt.Color(0, 102, 153));
        btnParquearEspacio.setText("Ocupar espacio");
        btnParquearEspacio.setColor1(new java.awt.Color(0, 102, 153));
        btnParquearEspacio.setColor2(new java.awt.Color(0, 102, 153));
        btnParquearEspacio.setColor3(new java.awt.Color(0, 102, 153));
        btnParquearEspacio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnParquearEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParquearEspacioActionPerformed(evt);
            }
        });

        btnConsultarOtroEspacio.setBackground(new java.awt.Color(102, 102, 102));
        btnConsultarOtroEspacio.setText("Consultar otro espacio");
        btnConsultarOtroEspacio.setColor1(new java.awt.Color(102, 102, 102));
        btnConsultarOtroEspacio.setColor2(new java.awt.Color(102, 102, 102));
        btnConsultarOtroEspacio.setColor3(new java.awt.Color(102, 102, 102));
        btnConsultarOtroEspacio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnConsultarOtroEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarOtroEspacioActionPerformed(evt);
            }
        });

        cbPlacasVehiculo.setBackground(new java.awt.Color(51, 51, 51));
        cbPlacasVehiculo.setForeground(new java.awt.Color(255, 255, 255));

        lblPlaca.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblPlaca.setForeground(new java.awt.Color(255, 255, 255));
        lblPlaca.setText("Vehiculo a parquear");

        lblTotaltITULO.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTotaltITULO.setForeground(new java.awt.Color(255, 255, 255));
        lblTotaltITULO.setText("Total: ₡");

        jLabel22.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Tiempo de parqueo");

        spnTiempoParqueo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTiempoParqueoStateChanged(evt);
            }
        });

        lblTextoTiempo2.setForeground(new java.awt.Color(255, 255, 255));
        lblTextoTiempo2.setText("Tiempo acumulado a utilizar");

        lblTotal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("0");

        spnTiempoAcumulado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTiempoAcumuladoStateChanged(evt);
            }
        });

        lblTextoTiempo1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTextoTiempo1.setForeground(new java.awt.Color(255, 255, 255));
        lblTextoTiempo1.setText("minutos");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Minutos");

        lblTiempoAcumulado.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTiempoAcumulado.setForeground(new java.awt.Color(255, 255, 255));
        lblTiempoAcumulado.setText("0");

        lblTextoTiempo3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTextoTiempo3.setForeground(new java.awt.Color(255, 255, 255));
        lblTextoTiempo3.setText("Tiempo acumulado");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(spnTiempoAcumulado, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                .addGap(344, 344, 344)
                                .addComponent(btnConsultarOtroEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTextoTiempo2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lblTextoTiempo3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTiempoAcumulado, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTextoTiempo1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                                .addComponent(btnParquearEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(lblPlaca)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addGap(22, 22, 22)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbPlacasVehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spnTiempoParqueo, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                                .addGap(58, 58, 58))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(122, 122, 122)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEspacioDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblTotaltITULO, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPlaca)
                            .addComponent(cbPlacasVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEspacioDisponible))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(lblTotaltITULO)
                            .addComponent(lblTotal)
                            .addComponent(spnTiempoParqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTiempoAcumulado)
                            .addComponent(lblTextoTiempo1)
                            .addComponent(lblTextoTiempo3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTextoTiempo2))
                    .addComponent(btnParquearEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnTiempoAcumulado, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnConsultarOtroEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        tpParquearEspacio.addTab("tab2", jPanel5);

        jPanel4.setBackground(new java.awt.Color(29, 24, 39));

        lblPerfil16.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblPerfil16.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil16.setText("Alerta");

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("El espacio esta siendio ocupado por un vehiculo en este momento");

        lblPerfil1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPerfil1.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil1.setText("Espacio");

        lblEspacio.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblEspacio.setForeground(new java.awt.Color(255, 255, 255));
        lblEspacio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEspacio.setText(".");

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("¿El espacio esta libre? Reportelo y podrá parquear en el");

        btnRepostarEspacio.setText("Reportar");
        btnRepostarEspacio.setColor1(new java.awt.Color(204, 0, 0));
        btnRepostarEspacio.setColor2(new java.awt.Color(204, 0, 0));
        btnRepostarEspacio.setColor3(new java.awt.Color(204, 0, 0));
        btnRepostarEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepostarEspacioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(265, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblPerfil16)
                        .addGap(334, 334, 334))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(179, 179, 179))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblPerfil1)
                        .addGap(339, 339, 339))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(206, 206, 206))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnRepostarEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(317, 317, 317))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(320, 320, 320))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblPerfil16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPerfil1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEspacio)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(btnRepostarEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpParquearEspacio.addTab("tab1", jPanel4);

        jPanel6.setBackground(new java.awt.Color(29, 24, 39));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tpParquearEspacio.addTab("tab3", jPanel6);

        lblPerfil17.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblPerfil17.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil17.setText("Parquear");

        javax.swing.GroupLayout pnParquearLayout = new javax.swing.GroupLayout(pnParquear);
        pnParquear.setLayout(pnParquearLayout);
        pnParquearLayout.setHorizontalGroup(
            pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParquearLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnParquearLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(258, 258, 258))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnParquearLayout.createSequentialGroup()
                        .addComponent(lblPerfil17)
                        .addGap(356, 356, 356))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnParquearLayout.createSequentialGroup()
                        .addGroup(pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnConsultarEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnParquearLayout.createSequentialGroup()
                                .addComponent(txtEspacioConsultado, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGap(327, 327, 327))))
            .addGroup(pnParquearLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tpParquearEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        pnParquearLayout.setVerticalGroup(
            pnParquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParquearLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lblPerfil17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtEspacioConsultado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsultarEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tpParquearEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pbTabl.addTab("", pnParquear);

        pnPerfil.setBackground(new java.awt.Color(57, 54, 66));
        pnPerfil.setRoundBottomLeft(15);
        pnPerfil.setRoundBottomRight(15);
        pnPerfil.setRoundTopLeft(15);
        pnPerfil.setRoundTopRight(15);

        lblPerfil.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil.setText("Mi Perfil");

        txtTelefono.setBackground(new java.awt.Color(70, 70, 70));
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.setText("00000000");
        txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtTelefono.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        txtPt2Mail.setBackground(new java.awt.Color(70, 70, 70));
        txtPt2Mail.setForeground(new java.awt.Color(255, 255, 255));
        txtPt2Mail.setText("dominio");
        txtPt2Mail.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtPt2Mail.setCaretColor(new java.awt.Color(255, 255, 255));

        txtApellidos.setBackground(new java.awt.Color(70, 70, 70));
        txtApellidos.setForeground(new java.awt.Color(255, 255, 255));
        txtApellidos.setText("Apellidos");
        txtApellidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtApellidos.setCaretColor(new java.awt.Color(255, 255, 255));
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        taDireccionFisica.setBackground(new java.awt.Color(70, 70, 70));
        taDireccionFisica.setColumns(20);
        taDireccionFisica.setForeground(new java.awt.Color(255, 255, 255));
        taDireccionFisica.setRows(5);
        taDireccionFisica.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        taDireccionFisica.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(taDireccionFisica);

        txtIdentificacion.setBackground(new java.awt.Color(70, 70, 70));
        txtIdentificacion.setForeground(new java.awt.Color(255, 255, 255));
        txtIdentificacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtIdentificacion.setCaretColor(new java.awt.Color(255, 255, 255));

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

        txtNombre1.setBackground(new java.awt.Color(70, 70, 70));
        txtNombre1.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre1.setText("Nombre");
        txtNombre1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));
        txtNombre1.setCaretColor(new java.awt.Color(204, 204, 204));

        txtPt1Mail.setBackground(new java.awt.Color(70, 70, 70));
        txtPt1Mail.setForeground(new java.awt.Color(255, 255, 255));
        txtPt1Mail.setText("nombre");
        txtPt1Mail.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtPt1Mail.setCaretColor(new java.awt.Color(255, 255, 255));

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
        btnRestablecerContra.setText("Restablecer PIN");
        btnRestablecerContra.setColor1(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setColor2(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setColor3(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnRestablecerContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestablecerContraActionPerformed(evt);
            }
        });

        btnMetodoPago.setBackground(new java.awt.Color(0, 102, 153));
        btnMetodoPago.setText("Metodo de pago");
        btnMetodoPago.setColor1(new java.awt.Color(0, 102, 153));
        btnMetodoPago.setColor2(new java.awt.Color(0, 102, 153));
        btnMetodoPago.setColor3(new java.awt.Color(0, 102, 153));
        btnMetodoPago.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMetodoPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnPerfilLayout = new javax.swing.GroupLayout(pnPerfil);
        pnPerfil.setLayout(pnPerfilLayout);
        pnPerfilLayout.setHorizontalGroup(
            pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerfilLayout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfilLayout.createSequentialGroup()
                        .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnPerfilLayout.createSequentialGroup()
                                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIdentificacion)
                                    .addComponent(lblTelefono1)
                                    .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                        .addComponent(txtIdentificacion))
                                    .addComponent(lblApellidos3)
                                    .addComponent(lblNombre1)
                                    .addGroup(pnPerfilLayout.createSequentialGroup()
                                        .addComponent(txtPt1Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(lblApellidos2))
                                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblApellidos)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblApellidos1)
                                        .addComponent(lblTelefono2)
                                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                            .addComponent(txtPt2Mail)))))
                            .addGroup(pnPerfilLayout.createSequentialGroup()
                                .addComponent(btnRestablecerContra, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(184, 184, 184)))
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfilLayout.createSequentialGroup()
                        .addComponent(btnMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(319, 319, 319))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfilLayout.createSequentialGroup()
                        .addComponent(lblPerfil)
                        .addGap(374, 374, 374))))
        );
        pnPerfilLayout.setVerticalGroup(
            pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerfilLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lblPerfil)
                .addGap(18, 18, 18)
                .addComponent(btnMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre1)
                    .addComponent(lblApellidos))
                .addGap(8, 8, 8)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnPerfilLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono1)
                    .addComponent(lblTelefono2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPerfilLayout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdentificacion)
                        .addGap(12, 12, 12)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidos3)
                    .addComponent(lblApellidos1))
                .addGap(18, 18, 18)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPt2Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPt1Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidos2))
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPerfilLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnRestablecerContra, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnPerfilLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnActualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(72, 72, 72))
        );

        pbTabl.addTab("", pnPerfil);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(panelRedondo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pbTabl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pbTabl, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelRedondo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pbTabl.getAccessibleContext().setAccessibleName("principal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actualizarInformacion(Usuario usuario, String identificacion){
    
        //Utilizado para actualizar los usuarios al modificar su informacion
        Login login = new Login();
         login.actualizarPersona(usuario,identificacion);
        
    }
    
    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed

        pbTabl.setSelectedIndex(6); //lleva a la pestana de actualizar informacion del perfil'
        
        //coloca la informacion cactual del usuario dentro de los campos de texto
        txtNombre1.setText(usuario.getNombre());
        txtApellidos.setText(usuario.getApellidos());
        txtTelefono.setText(String.valueOf(usuario.getTelefono()));
        taDireccionFisica.setText(usuario.getDireccionFisica());
        txtIdentificacion.setText(usuario.getIdentificacion());
        txtPt1Mail.setText(usuario.getCorreo().getStr1());
        txtPt2Mail.setText(usuario.getCorreo().getStr2());
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnParquearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParquearMouseEntered

        //modifica el color del boton al posicionar el mouse sobre el boton
        btnParquear.setColor1(Color.orange);
        btnParquear.setColor2(Color.orange);
        btnParquear.setColor3(Color.orange);
        btnParquear.setForeground(Color.white);
    }//GEN-LAST:event_btnParquearMouseEntered

    private void btnParquearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParquearMouseExited

         //modifica el color del boton al posicionar el mouse sobre el botonbtnParquear.setColor1(Color.white);
        btnParquear.setColor1(Color.white);
         btnParquear.setColor2(Color.white);
        btnParquear.setColor3(Color.white);
        btnParquear.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_btnParquearMouseExited

    private void btnParquearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParquearActionPerformed

        //valida que el usuario haya registrado un metodo de pago para poder reservar un espacio
        if(usuario.getTarjeta() != null){
            pbTabl.setSelectedIndex(5); //muestra la pestana 5 correspondiente al reservar un espacio
            tpParquearEspacio.setSelectedIndex(2);//cambia la pestana del tab panel interno a la pestana inicial
            txtEspacioConsultado.setEnabled(true);
            btnConsultarEspacio.setVisible(true);
        }else{
            
            JOptionPane.showMessageDialog(null, "Debe registar un metodo de pago antes de utilizar un espacio");
            
        }
    }//GEN-LAST:event_btnParquearActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
       
        LoginJFrame login = new LoginJFrame();
        
        actualizarInformacion(usuario, usuario.getIdentificacion()); //actualiza la informacion que fue generada por el usuario
        
        usuario=null; //elimina la informacion que apuntaba el usuario
        this.setVisible(false);
        login.setVisible(true);
        
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
       pbTabl.setSelectedIndex(1); //muestra la pestana del tab en la que se puede generar reportes
    }//GEN-LAST:event_btnReportesActionPerformed

    private void inicializarTabPanelVehiculos(){
    
        //inicializa el tab que muestra las acciones que manipula los vehiculos
        //oculta las pestana del panel
        tpPanelModificaciones.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0; // Hacer la altura de las pestañas cero para ocultarlas
            }
         });
    
    
    }
    
    private void inicializarTablaVehiculos(){
        
        String placa, modeloAuto,  marca,  estado; 
        int espacio;
        
        tblVehiculo.setDefaultRenderer(Object.class, new RenderTable()); //genera un modelo de tabla qe permite modificar los espacios de la misma
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String identificadores [] = {"Placa","Estado", "Espacio", "Modelo", "Serie", "Actualizar", "Eliminar"}; //se define el nombre de las columnas
        modelo.setColumnIdentifiers(identificadores);//se agrega los identificadores de las columnas al modelo
        

        //ESTILIZANDO BOTONES
        RondedBordes actualizar = new RondedBordes();
        actualizar.setText("+");
        actualizar.setName("btnActualizarVehiculo");
        actualizar.setColor1(new Color(114,245,66));
        actualizar.setColor2(new Color(114,245,66));
        actualizar.setColor3(new Color(114,245,66));
        actualizar.setForeground(Color.white);
        actualizar.setMinimumSize(new Dimension(2,2));
        actualizar.setMaximumSize(new Dimension(2,2));
        actualizar.setPreferredSize(new Dimension(2,2));
        
        
        RondedBordes eliminar = new RondedBordes();
        eliminar.setText("-");
        eliminar.setName("btnEliminarVehiculo");
        eliminar.setColor1(new Color(156, 50, 57));
        eliminar.setColor2(new Color(156, 50, 57));
        eliminar.setColor3(new Color(156, 50, 57));
        eliminar.setForeground(Color.white);
        eliminar.setSize(10,30);
        //---------------------------------
        
        modelo.getDataVector().removeAllElements(); //elimina los datos que tiene la tabla
        
        for(Vehiculo obj : usuario.getVehiculos()){ //recorre los vehiculos que posee los usuarios
        
            //almacena la informacion del vehiculo en las variables
            placa= obj.getPlaca();
            marca=obj.getMarca();
            modeloAuto= obj.getModelo();
           
            //valida que el vehiculo este parqueado, de se asi define el espacio en el que se encuentra el vehiculo
            if(obj.getTicketVigente()!=null){
            
                estado = "Parqueado";
                espacio = obj.getTicketVigente().getEspacio().getNumero();
                modelo.addRow(new Object[]{placa, estado, espacio, modeloAuto, marca,actualizar, eliminar});
            }
            else{
                //al no estar parqueado el numero de espacio no se muestra
                 estado = "Desaparcado"; 
                 modelo.addRow(new Object[]{placa, estado, "-", modeloAuto, marca,actualizar, eliminar});
            }
        }
        
        tblVehiculo.setModel(modelo); //se establece el modelo a la tabla, con los datos y nombres de las columnas cargadas
        tblVehiculo.setRowHeight(40); //Estable la altura de las filas
        
        
    }
    
    private void BtnVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVehiculosActionPerformed
        pbTabl.setSelectedIndex(0); //muestra la pestana del panel principal, permite manipular a los vehiculo
        tpPanelModificaciones.setVisible(false);
        inicializarTabPanelVehiculos();
        inicializarTablaVehiculos();
        
    }//GEN-LAST:event_BtnVehiculosActionPerformed

    private void BtnParqueos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnParqueos1ActionPerformed
       pbTabl.setSelectedIndex(2); //muestra la pestana del panel principal la cual permite mostrar los parqueos activos que posee el usuario
       pnlAgregarTiempo.setVisible(false);
       inicializarTBMisParqueos();
    }//GEN-LAST:event_BtnParqueos1ActionPerformed

    private void lblTiuloPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTiuloPrincipalMouseClicked
        pbTabl.setSelectedIndex(4); //Representa el titulo principal que muestra la pestana principal de la aplicacion
    }//GEN-LAST:event_lblTiuloPrincipalMouseClicked

    private void btnPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerfilMouseExited
        //cambia el color cuando el mouse se posiciona sobre el boton
        btnPerfil.setColor1(Color.white);
        btnPerfil.setColor2(Color.white);
        btnPerfil.setColor3(Color.white);
        btnPerfil.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_btnPerfilMouseExited

    private void BtnParqueos1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnParqueos1MouseExited
        //cambia el color cuando el mouse se posiciona sobre el boton
        BtnParqueos1.setColor1(Color.white);
        BtnParqueos1.setColor2(Color.white);
        BtnParqueos1.setColor3(Color.white);
        BtnParqueos1.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_BtnParqueos1MouseExited

    private void BtnVehiculosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnVehiculosMouseExited
        //cambia el color cuando el mouse se posiciona sobre el boton
        BtnVehiculos.setColor1(Color.white);
        BtnVehiculos.setColor2(Color.white);
        BtnVehiculos.setColor3(Color.white);
        BtnVehiculos.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_BtnVehiculosMouseExited

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
        //cambia el color cuando el mouse se posiciona sobre el boton
        btnReportes.setColor1(Color.white);
        btnReportes.setColor2(Color.white);
        btnReportes.setColor3(Color.white);
        btnReportes.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_btnReportesMouseExited

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
        //cambia el color cuando el mouse se posiciona sobre el boton
        btnReportes.setColor1(Color.orange);
        btnReportes.setColor2(Color.orange);
        btnReportes.setColor3(Color.orange);
        btnReportes.setForeground(Color.white);
    }//GEN-LAST:event_btnReportesMouseEntered

    private void BtnVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnVehiculosMouseClicked
      
    }//GEN-LAST:event_BtnVehiculosMouseClicked

    private void BtnVehiculosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnVehiculosMouseEntered
        //cambia el color cuando el mouse se posiciona sobre el boton
        BtnVehiculos.setColor1(Color.orange);
        BtnVehiculos.setColor2(Color.orange);
        BtnVehiculos.setColor3(Color.orange);
        BtnVehiculos.setForeground(Color.white);
    }//GEN-LAST:event_BtnVehiculosMouseEntered

    private void BtnParqueos1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnParqueos1MouseEntered
       //cambia el color cuando el mouse se posiciona sobre el boton
        BtnParqueos1.setColor1(Color.orange);
        BtnParqueos1.setColor2(Color.orange);
        BtnParqueos1.setColor3(Color.orange);
        BtnParqueos1.setForeground(Color.white);
    }//GEN-LAST:event_BtnParqueos1MouseEntered

    private void btnPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerfilMouseEntered
        //cambia el color cuando el mouse se posiciona sobre el boton
        btnPerfil.setColor1(Color.orange);
        btnPerfil.setColor2(Color.orange);
        btnPerfil.setColor3(Color.orange);
        btnPerfil.setForeground(Color.white);
    }//GEN-LAST:event_btnPerfilMouseEntered

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
       
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
      
    }//GEN-LAST:event_txtApellidosActionPerformed

    //-------------------------------------VALIDACIONES DATOS DE PERFIL----------------------
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
 
 public boolean validacionIdentificacion(String identificacion) {
        
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
   
   private boolean validarCorreo(String pt1, String pt2){
   
       if(pt1.length() < 3){
       
            JOptionPane.showMessageDialog(null, "El nombre de usuario de la direccion electronica debe tener minimo 3 caracteres!");
            return false;
       }else{
       
           if(pt2.length() <3){
           
                JOptionPane.showMessageDialog(null, "El dominio del correo electronico debe tener minimo 3 caracteres!");
                return false;
           }
           else
               return true;
       }
   
   }
   
   //----------------FIN VALIDACION DATOS PERFIL-------------------------------------------------
    
    private void btnActualizarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPerfilActionPerformed
        
        String nombre;
        String apellidos;
        String identificacion;
        String direccionFisica;
        String parte1Correo;
        String parte2Correo;
        String identificacionGeneral;
        LocalDate fechaIngreso;
        int telefono;
        
        
        if(validarNombre(txtNombre1.getText())){
        
                if(validarApellidos(txtApellidos.getText())){
                    
                   if(validacionDireccionFisica(taDireccionFisica.getText())){
                       
                       if(validacionIdentificacion(txtIdentificacion.getText())){
                           if(validarConversion(txtTelefono.getText())){
                           
                               if(validarTelefono(Integer.parseInt(txtTelefono.getText()))){
                               
                                   if(validarCorreo(txtPt1Mail.getText(), txtPt2Mail.getText())){
                                   
                                       //Almacena los datos de los campos de texto en las variables para ser alamacenadas
                                       nombre = txtNombre1.getText();
                                       apellidos= txtApellidos.getText();
                                       direccionFisica = taDireccionFisica.getText();
                                       identificacion = txtIdentificacion.getText();
                                       parte1Correo = txtPt1Mail.getText();
                                       parte2Correo =  txtPt2Mail.getText();
                                       telefono = Integer.valueOf(txtTelefono.getText());
                                       Correo correo = new Correo(parte1Correo, parte2Correo);
                                       fechaIngreso = LocalDate.now();
                                       System.out.println(fechaIngreso);
                                       
                                       //Almacena la identificacion del usuario anteriormente de ser modificada
                                       identificacionGeneral = usuario.getIdentificacion();
                                       Usuario usuarioActualizado = new Usuario(nombre, apellidos,telefono, direccionFisica, fechaIngreso, identificacion,"",0, correo);
                                       
                                       //modifica la informacion modificada en el archivo de datos
                                       usuario.actualizarDatos(usuarioActualizado);
                                        
                                        actualizarInformacion(usuario, identificacionGeneral);
                                        //envio de correo
                                        String cuerpo = "Nombre: " + usuario.getNombre() + "\n" + "Apellidos: " + usuario.getApellidos() + "\n" + "Direccion fisica: " + usuario.getDireccionFisica() + "\n" + 
                                        "Identificacion: " + usuario.getIdentificacion() + "\n" + "Telefono: " + usuario.getTelefono();
                                        crearEmail(cuerpo, "PARAMETROS ACTUALIZADOS", usuario.getCorreo().getCorreo());
                                        enviarEmail();
                                         JOptionPane.showMessageDialog(null, "Datos actualizados existosamente!");
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

    private void btnRestablecerContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestablecerContraActionPerformed
            
            String[] options = { "Si", "No" };
            var selection = JOptionPane.showOptionDialog(null, "Esta segur@ de restablecer la contrasena?", "Alerta!!", 
                                                              0, 3, null, options, options[0]);
            if (selection == 0) {


              }
            else{
                JOptionPane.showMessageDialog(null, "No se actualizara!");
            }
    }//GEN-LAST:event_btnRestablecerContraActionPerformed

    private void btnMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMetodoPagoActionPerformed
       pbTabl.setSelectedIndex(4); //lleva la pestana del panel del tab principal al metodo de pago
       
       Tarjeta tarjeta = usuario.getTarjeta(); //obtiene los datos de la tarjeta
       if(tarjeta!=null){ //valida que tenga una tarjeta registarda
           
           //coloca los datos de la tarjeta actuales en los campos de texto que le permiten actualizarlos
            txtNumeroTarjeta.setText(String.valueOf(tarjeta.getNumeroTarjeta()));
            txtCodigoTarjeta.setText(String.valueOf(tarjeta.getCodigoValidacion()));
            spnAnioTarjeta.setYear(tarjeta.getAnoVencimiento());
            spnMesTarjeta.setMonth(tarjeta.getMesVencimiento());
           
       }

    }//GEN-LAST:event_btnMetodoPagoActionPerformed

    private void btnVolverPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverPerfilActionPerformed
        pbTabl.setSelectedIndex(6);//boton presente en metodo de pago, le permite devolverse a actualizar el perfil
    }//GEN-LAST:event_btnVolverPerfilActionPerformed

    //------------------------VALIDACIONES DE DATOS DE TARJETA--------------------------------
    private boolean validarConversionLong(String numero){
        //valida que el numero pueda convertirse a long
        try{
        
            Long prueba = Long.valueOf(numero);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    
    private boolean validarNumeroTarjeta(String numero){
    
        if(numero.length() == 16)
            return true;
        else
            return false;
    }
    
    private boolean validarAnio(int anio){
    
        if(anio < LocalDate.now().getYear()){
        
            return false;
        }
        else
            return true;
    }
    
    private boolean validarMes(int mes, int anio){
    
        System.out.println(mes);
        System.out.println(LocalDate.now().getMonthValue());
        if(anio==LocalDate.now().getYear() && mes+1 < LocalDate.now().getMonthValue()){
            return false;
        }
        else
            return true;
    }
    
    private boolean validarCodigo (String codigo){
    
        if(codigo.length() == 3)
            return true;
        else
            return false;
    }
    //-----------------------------------------------FIN DE VALIDACIONES  DE NUMERO DE TARJETA---------------------
    
    private void btnActualizarTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTarjetaActionPerformed
        
       //Datos que almacenan los datos a actualizar
        long numeroTarjeta;
        int anio;
        int mes;
        int codigoValidacion;
        
        if(validarNumeroTarjeta(txtNumeroTarjeta.getText())){
             
            if(validarConversionLong(txtNumeroTarjeta.getText())){
            
                if(validarAnio((int)spnAnioTarjeta.getValue())){
                
                    if(validarMes((int)spnMesTarjeta.getMonth(),(int)spnAnioTarjeta.getValue())){
                    
                        
                        if(validarCodigo(txtCodigoTarjeta.getText())){
                            
                            //almacena los datos que fueron ingresados en los campos de texto
                            numeroTarjeta= Long.valueOf(txtNumeroTarjeta.getText());
                            anio = (int)spnAnioTarjeta.getValue();
                            mes=(int)spnMesTarjeta.getMonth();
                            codigoValidacion = Integer.valueOf(txtCodigoTarjeta.getText());
                            
                             Tarjeta tarjeta = new Tarjeta(numeroTarjeta,anio,mes,codigoValidacion);
                             
                             //actualiza la tarjeta del usuario
                             usuario.setTarjeta(tarjeta);
                             
                             //modifica los datos del usuario en el archivo de texto
                             actualizarInformacion(usuario, usuario.getIdentificacion());
                        
                             JOptionPane.showMessageDialog(null, "Tarjeta actualizada exitosamente");
                        }
                        else{

                            JOptionPane.showMessageDialog(null, "El codigo de validacion debe ser de 3 digitos");
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null, "El mes de vencimiento ya ha expirado este año!");
                }
                else
                    JOptionPane.showMessageDialog(null, "El año de vencimiento ya ha expirado!");
            }
            else
                JOptionPane.showMessageDialog(null, "El numero de tarjeta debe ser un numero natural!");
        }
        else
            JOptionPane.showMessageDialog(null, "El numero de tarjeta debe tener 16 digitos!");
        
    }//GEN-LAST:event_btnActualizarTarjetaActionPerformed

    
     private void generarReporteGeneralEspaciosVacios(List<Espacio>  espacios){
        
         //Inicializa la tabla de espacios vacios que puede utilizar el usuario
        int cantidadEspacios=0;
        String estado = "Vacio";
        String identificadores [] = {"Numero", "Estado"};
        
        //genera y modifica los identificadores de las columnas en la tabla
        mdlEspaciosGeneral.setColumnIdentifiers(identificadores);
        tblEspaciosGeneral.setModel(mdlEspaciosGeneral);
        mdlEspaciosGeneral.getDataVector().removeAllElements();
         
       //recorre los espacios para validar los espacios que estan vacios
        for(Espacio obj : espacios){
            mdlEspaciosGeneral.addRow(new Object[]{obj.getNumero(), estado});
            cantidadEspacios++; //controla la cantidad de espacios tomados en cuenta
        }
        //modifica la cantidad de espacios tomados en cuenta en el reporte
        lblCantidadEspacios.setText(String.valueOf(cantidadEspacios));
    }
    
    private void cbReportesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbReportesItemStateChanged

        //Se genera cada vez que el combo box cambia de valor
        //valida el valor seleccionado, y genera una respuesta en relacion a lo seleccionado
        if(cbReportes.getSelectedItem() == "Parqueos Disponibles")
        {
            tpReportes.setSelectedIndex(0);//muestra la pestana del panel que tiene la tabla a cargar
            Parqueo parqueo = new Parqueo();
            parqueo.lecturaArchivo();//obtiene los datos que posee el parqueo

            generarReporteGeneralEspaciosVacios(parqueo.getEspacios());
        
            //Ordena los datos de la tabla basados en la columna 0 de manera ascendente
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mdlEspaciosGeneral);
            tblEspaciosGeneral.setRowSorter(sorter);
            sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
        }
    }//GEN-LAST:event_cbReportesItemStateChanged

    private void cbReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbReportesActionPerformed
      
    }//GEN-LAST:event_cbReportesActionPerformed

    
    private void actualizarDatosTicket(){
    
       //actualiza la tabla de los datos al generar un ticket del espacio
       
       //valida si el usuario tiene vehiculos registrados
        if(usuario.getVehiculos().isEmpty()){ 
        
            JOptionPane.showMessageDialog(null, "Debe registrar un vehiculo para reservar un espacio!");
            txtEspacioConsultado.enable(true);
            tpParquearEspacio.setSelectedIndex(2);
            btnConsultarEspacio.setVisible(true);
        }
        else{
            
            lblTotal.setText("0"); //tendra el total del ticket en relacion a los minutos solicitados
            
            Parqueo parqueo = new Parqueo();
            parqueo.lecturaArchivo();
            
            precioMinuto = parqueo.getPrecioHora()/2; //modifica el precio por minuto del usuario, definiendolo cada 30 min
            
            //----------------------Define las caracterisiticas del spiner que controla los minutos solicitados
            SpinnerNumberModel mdTiempo= new SpinnerNumberModel();
            mdTiempo.setMinimum(parqueo.getTiempoMinimo()); //define el tiempo minimo que puede solicitarse
            mdTiempo.setMaximum(99999); //define el tiempo maximo
            mdTiempo.setStepSize(30);// define los pasos entre el tiempo que se puede seleccionar, ej 30-60-90
            
            spnTiempoParqueo.setModel(mdTiempo); //definer el modelo al spinner
            spnTiempoParqueo.setValue(parqueo.getTiempoMinimo()); //seleccuiona el valor minimo
            
            //valida si el usuario tiene tiempo minimo y es posible de ser utilizado
            if(usuario.getTiempoAcumulado()!=0 && usuario.getTiempoAcumulado() >= 30){
            
                //muestra los elementos relacionados al tiempo acumulado
                lblTextoTiempo1.setVisible(true);
                lblTextoTiempo2.setVisible(true);
                lblTiempoAcumulado.setText(String.valueOf(usuario.getTiempoAcumulado()));
                
                //Actualizar el tiempo acumulado con con los datos que posee el usuario
                SpinnerNumberModel mdAcumulado= new SpinnerNumberModel();
                mdAcumulado.setMinimum(0); //define el valor minimo que se tiene como valor minimo
                mdAcumulado.setStepSize(30);
                spnTiempoAcumulado.setModel(mdAcumulado);
                spnTiempoAcumulado.setValue(0);
            }
        
            //--------------VALIDACION DE LOS VEHICULOS DEL USUARIO-----------------------
            
            //El usuario solo tiene un vehiculo registrado
            if(usuario.getVehiculos().size() == 1){
            
                //el vehiculo ya esta parqueado, por lo que no se puede parquear en otro espacio
                if(usuario.getVehiculos().getFirst().getEspacio()!= null){
                
                    JOptionPane.showMessageDialog(null, "El vehiculo con placa " + usuario.getVehiculos().getFirst().getPlaca() + " se encuentra en un espacio de parqueo!");
                    txtEspacioConsultado.enable(true);
                    tpParquearEspacio.setSelectedIndex(3);
                    btnConsultarEspacio.setVisible(true);
                    
                }else{
                    cbPlacasVehiculo.addItem( usuario.getVehiculos().getFirst().getPlaca()); //anade la placa del vehiculo al combo box
                    cbPlacasVehiculo.setEnabled(false); //al ser solo uno el combo box se inhabilita
                }
            }
            else{
            
                 DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel(); //al ser mas de un vehiculo valida los carga al combo box

                for(Vehiculo obj : usuario.getVehiculos()) //recorre los vehiculos del usuario
                {
                    if(obj.getEspacio()==null) //valida que el vehiculo se encuentre en un espacio disponible
                        comboModel.addElement(obj.getPlaca()); //anade le vehiculo al combo box, es decir, anade la placa
                }

                cbPlacasVehiculo.setModel(comboModel); // selecciona el modelo y lo establece en el combo box
            
            }
        }
    
    }
    private void btnConsultarEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarEspacioActionPerformed
        
        //valida que se haya ingresado un numero de paso
        if(!"".equals(txtEspacioConsultado.getText())){
        
            if(validarConversion(txtEspacioConsultado.getText())){ //valida que el espacio ingresado sea numerico
                
                Parqueo parqueo = new Parqueo();
                parqueo.lecturaArchivo(); //obtiene los datos del parqueo
                
                Espacio espacio = parqueo.buscarEspacio(Integer.valueOf(txtEspacioConsultado.getText()));
                if(espacio!=null){
                    //validar si el espacio esta dicponible o no
                    if(espacio.getEstado()){ //es espacio esta libre y puede parquear
                    
                           txtEspacioConsultado.enable(false);
                           tpParquearEspacio.setSelectedIndex(0);
                           lblEspacioDisponible.setText("Espacio: " + txtEspacioConsultado.getText());
                           btnConsultarEspacio.setVisible(false);
                           actualizarDatosTicket();
                        
                    }
                    else{ //el espacio esta ocupado pero puede reportar que esta libre
                        
                       boolean encontrado=false;
                        
                        for(Vehiculo obj : espacio.getVehiculos()){
                        
                            System.out.println("hola");
                            if(usuario.buscarVehiculo(obj.getPlaca())!= null){ //valdia si el espacio es uno de los vehiculos del usuario
                                
                                JOptionPane.showMessageDialog(null, "El espacio ingresado ya se encuentra ocupado por uno de sus vehiculos placa: " + obj.getPlaca());
                                txtEspacioConsultado.setText("");
                                tpParquearEspacio.setSelectedIndex(2);
                                encontrado=true;
                                break;
                                
                            }
                        }
                        if(!encontrado){ //el vehiculo parqueado es de otro usuario por lo que se puede liberar del parqueo (desaparcar)
                            tpParquearEspacio.setSelectedIndex(1); //muestra la pestana que permite reportar el espacio y liberarlo
                            lblEspacio.setText(txtEspacioConsultado.getText());
                        }
                    }
                }
                else{
                
                     JOptionPane.showMessageDialog(null, "El espacio ingresado no existe!");
                     txtEspacioConsultado.setText("");
                     tpParquearEspacio.setSelectedIndex(2);
                }
                
            }
            else
                JOptionPane.showMessageDialog(null, "El espacio contiene caracteres alfabeticos!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Ingrese un numero de espacio!");
        
        }
        
    }//GEN-LAST:event_btnConsultarEspacioActionPerformed

    private void btnRepostarEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepostarEspacioActionPerformed
            
            //genera una confirmacion al usuario para liberar el espacio
            String[] options = { "Si", "No" };
            var selection = JOptionPane.showOptionDialog(null, "Esta segur@ de liberar el espacio?", "Alerta!!", 
                                                              0, 3, null, options, options[0]);
            if (selection == 0) {

                //lee los datos reservados en los parametros
                Parqueo parqueo = new Parqueo();
                parqueo.lecturaArchivo();
                
                parqueo.liberarEspacio(Integer.valueOf(txtEspacioConsultado.getText())); //libera el espacio
                 
                
                JOptionPane.showMessageDialog(null, "El espacio ha sido liberado exitosamente, ahora podrá parquear en el!");
                 txtEspacioConsultado.enable(false);
                 tpParquearEspacio.setSelectedIndex(0);
                 lblEspacioDisponible.setText("Espacio: " + txtEspacioConsultado.getText());
                 btnConsultarEspacio.setVisible(false);
                 actualizarDatosTicket();
                 
                 //actualiza la informacion del usuario dentro del archivo de texto
                 actualizarInformacion(usuario, usuario.getIdentificacion()); 
              }
            else{
                JOptionPane.showMessageDialog(null, "No se liberará!");
            }
    }//GEN-LAST:event_btnRepostarEspacioActionPerformed

    private void btnParquearEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParquearEspacioActionPerformed
     
        //valida que el tiempo a parquear en minutos no sea 0
        if((int)spnTiempoParqueo.getValue() !=0){
        
            //lee los datos que son alamacenados en el archivo de parametros
            Parqueo parqueo = new Parqueo();
            parqueo.lecturaArchivo();
            
            //obtiene el vehiculo que fue seleccionado para ser parqueado
            Vehiculo vehiculo = usuario.buscarVehiculo((String)cbPlacasVehiculo.getSelectedItem());
            //obtiene el espacio dentro del parqueo para modificarlo
            Espacio espacio = parqueo.buscarEspacio(Integer.valueOf(txtEspacioConsultado.getText()));
            TicketParqueo ticket = new TicketParqueo();
            
            //GENERA UN TICKET. almacena los espacios
            ticket.setTiempoParqueo((int)spnTiempoParqueo.getValue());
            ticket.setUsuario(usuario);
            ticket.setVehiculo(vehiculo);
            ticket.setTotal(Integer.valueOf(lblTotal.getText()));
            ticket.setEstado(true);
            ticket.setHoraSistema(LocalDateTime.now());
            ticket.setEspacio(espacio);
            
            //Agrega el vehiculo al espacio que fue reservado
            espacio.agregarVehiculo(vehiculo);
            espacio.setEstado(false);
            
            //Define el espacio en el que se encuentra el vehiculo
            vehiculo.setEspacio(espacio);
            
            //define el tickete vigente del vehiculo
            vehiculo.establecerTicketVigente(ticket);
            //guarda los datos al archivo Parametros.txt
            parqueo.cargarArchivo();
            
            //enviar correo
            String cuerpo = "Su vehiculo " + vehiculo.getMarca() + " " + vehiculo.getMarca() + "de placa " + vehiculo.getPlaca() + 
            "fue parqueado exitosamente con un tiempo de " + spnTiempoParqueo.getValue() + "minutos.";
            crearEmail(cuerpo, "SU PARQUEO DE VEHICULO FUE REGISTRADO", usuario.getCorreo().getCorreo());
            enviarEmail();
            JOptionPane.showMessageDialog(null, "Espacio registrado exitosamente!");
            txtEspacioConsultado.enable(true);
            btnConsultarEspacio.setVisible(true);
            tpParquearEspacio.setSelectedIndex(2);
            
            actualizarInformacion(usuario, usuario.getIdentificacion());
            
        }else{
        
             JOptionPane.showMessageDialog(null, "Ingrese un tiempo de parqueo");
        }
        
        
        
        
        
    }//GEN-LAST:event_btnParquearEspacioActionPerformed

    private void btnConsultarOtroEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarOtroEspacioActionPerformed
         txtEspacioConsultado.enable(true);
         btnConsultarEspacio.setVisible(true);
         tpParquearEspacio.setSelectedIndex(2);
    }//GEN-LAST:event_btnConsultarOtroEspacioActionPerformed

    private void spnTiempoParqueoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTiempoParqueoStateChanged
        //se genera cada vez que en spinner que define los minutos del parqueo cambia su valor
        
        int tiempoAcumulado =  (int)spnTiempoAcumulado.getValue();
        int tiempoParqueo = (int) spnTiempoParqueo.getValue();
        //valida que el tiempo de parqueo no sobrepase el tiempo acumulado
        if(tiempoParqueo<tiempoAcumulado){ 
                JOptionPane.showMessageDialog(null, "El tiempo de parqueo es menor al tiempo acumulado que se piensa utilzar");
                spnTiempoParqueo.setValue(tiempoParqueo + 30);

        }else{
                //Actualiza el precio total 
                int total = (tiempoParqueo-tiempoAcumulado)/30 * precioMinuto;
                lblTotal.setText(String.valueOf(total));
        }

    }//GEN-LAST:event_spnTiempoParqueoStateChanged

    private void spnTiempoAcumuladoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTiempoAcumuladoStateChanged
        
        //se genera cada vez que en spinner que define los minutos del parqueo cambia su valor
        int tiempoAcumulado =  (int)spnTiempoAcumulado.getValue();
        int tiempoParqueo = (int) spnTiempoParqueo.getValue();
        
        //valida que el tiempo acumulado no sea mayor al tiempo de parqueo, asi como qel tiempo debe ser mayor o igual al 30
        if(tiempoAcumulado>tiempoParqueo && tiempoAcumulado>=30){
            JOptionPane.showMessageDialog(null, "El tiempo acumulado es mayor al tiempo en que se ocupara el espacio!");
            spnTiempoAcumulado.setValue(tiempoAcumulado-30); //se resta el valor que fue aumentado
        }
        //valida que el tiempo acumulado sea el que el usuario tiene disponible
        else if(tiempoAcumulado > usuario.getTiempoAcumulado() && tiempoAcumulado>=30){
            JOptionPane.showMessageDialog(null, "El tiempo ingresado es mayor al tiempo acumulado que posee disponible!");
            spnTiempoAcumulado.setValue(tiempoAcumulado-30);
        }
        else{
                //modifica el precio total en relacion a los minutos a utilizar y los que tienen acumulados
                 int total = (tiempoParqueo-tiempoAcumulado)/30 * precioMinuto;
                 lblTotal.setText(String.valueOf(total));
                 lblTiempoAcumulado.setText(String.valueOf(usuario.getTiempoAcumulado() - tiempoAcumulado));
        }

        
    }//GEN-LAST:event_spnTiempoAcumuladoStateChanged

    
    private void agregarTiempo(String placa){
    
            //Confirma la extension del tiempo
            String[] options = { "Si", "No" };
            var selection = JOptionPane.showOptionDialog(null, "Esta segur@ de agregar el tiempo al vehiculo con placa "+placa+"?", "Alerta!!", 
                                                              0, 3, null, options, options[0]);
            if (selection == 0) {
                
                //muestra el panel del tiempo para agregar el tiempo extra
                pnlAgregarTiempo.setVisible(true);
                //Obtiene el vehiculo al que se desea extender su ticket
                Vehiculo vehiculo = usuario.buscarVehiculo(placa);
                
                Parqueo parqueo = new Parqueo();
                parqueo.lecturaArchivo();
                
                lblEspacioExtra.setText("Espacio: " + String.valueOf(vehiculo.getEspacio().getNumero()));
                lblVehiculoExtra.setText(placa);
                
                //modifica las caracteristicas del spinner que define el tiempo minimo a agregar
                SpinnerNumberModel mdTiempo= new SpinnerNumberModel();
                mdTiempo.setMinimum(parqueo.getTiempoMinimo());
                mdTiempo.setValue(30);
                mdTiempo.setStepSize(30);
                spnTiempoExtra.setModel(mdTiempo);
                
                //modifica las caracteristicas del spinner que define el tiempo acumulado a utilizar
                SpinnerNumberModel mdTiempoAcumulado= new SpinnerNumberModel();
                mdTiempoAcumulado.setStepSize(30);
                spnAcumuladoExtra.setModel(mdTiempoAcumulado);
                lblTiempoTotal.setText("Tiempo extra:");
                
                //Modifica los datos de la tabla, refresca
                inicializarTBMisParqueos();
                actualizarInformacion(usuario, usuario.getIdentificacion());
              }
            else{
                JOptionPane.showMessageDialog(null, "No se agregara tiempo extra!");
            }
    }
    
    private void desaparcar(String placa){
    
            //Confirma que se puede desaparcar el vehiculo en el espacio
           String[] options = { "Si", "No" };
            var selection = JOptionPane.showOptionDialog(null, "Esta segur@ de desaparcar el vehiculo con placa "+placa+"?", "Alerta!!", 
                                                              0, 3, null, options, options[0]);
            if (selection == 0) {

                Parqueo parqueo = new Parqueo();
                parqueo.lecturaArchivo();
                Vehiculo vehiculo = usuario.buscarVehiculo(placa);
                
                //calcula si queda tiempo extra que puede ser acumulado en el usuario
                TicketParqueo ticket = vehiculo.getTicketVigente();
                //obtiene el tiempo entre la hora de registro del ticket y la hora actual al sistema
                int tiempoRestante = (int)ChronoUnit.MINUTES.between( LocalDateTime.now(),ticket.getHoraSistema().plusMinutes(ticket.getTiempoParqueo()));
                //en caso se haber tiempo sobrante se acumula el tiempo
                if(tiempoRestante >0)
                    usuario.actualizarTiempoAcumulado(tiempoRestante);
                
                //Actualiza el ticket dentro del vehiculo
                vehiculo.agregarTicket(ticket);
                vehiculo.establecerTicketVigente(null);
                
                Espacio espacio = parqueo.buscarEspacio(vehiculo.getEspacio().getNumero());
                
                espacio.setEstado(true);
                espacio.removerVehiculo(placa);
                
                vehiculo.setEspacio(null);
                
                parqueo.cargarArchivo();
                
                if(tiempoRestante>0)
                     JOptionPane.showMessageDialog(null, "Vehiculo desaparcado exitosamente, ha acumulado " +tiempoRestante+" minutos !");
                else
                    JOptionPane.showMessageDialog(null, "Vehiculo desaparcado!");
                
                inicializarTBMisParqueos();
                actualizarInformacion(usuario, usuario.getIdentificacion());
              }
            else{
                JOptionPane.showMessageDialog(null, "No se libera el espacio!");
            }
        
        
    }
    
    private void tblMisParqueosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMisParqueosMouseClicked
        
        //registra algun click realizado sobre la tabla, con el fin de saber si fue dentro de un boton
        //obtiene la columna en la que fue presionado el boton
        columna=tblMisParqueos.getColumnModel().getColumnIndexAtX(evt.getX());
        //obtiene la fila
        row=evt.getY() /tblMisParqueos.getRowHeight();
        
        //valida si la fila y la columna esta dentro de las columnas y filas que posee la tabla
        if(columna <= tblMisParqueos.getColumnCount() && columna >= 0 && row <= tblMisParqueos.getRowCount() && row>=0){
            
            //Obtiene el objeto al que fue presionada
            Object objeto = tblMisParqueos.getValueAt(row, columna);
            
            //valida si el objeto es de la clase botones
            if(objeto instanceof RondedBordes){
            
                //Genera un click sobre el boton presionado
                ((RondedBordes)objeto).doClick();
                
                RondedBordes botones = (RondedBordes)objeto;
                
                //valida el nombre del boton al que fue presionado
                if(botones.getName().equals("btnDesaparcar")){
                    String placa = (String)tblMisParqueos.getValueAt(row, columna-4);
                    desaparcar(placa);
                }
                else if(botones.getName().equals("btnAgregarTiempo")){
                
                    String placa = (String)tblMisParqueos.getValueAt(row, columna-3);
                    agregarTiempo(placa);
                }
            }
            
        }
    }//GEN-LAST:event_tblMisParqueosMouseClicked

    private void spnAcumuladoExtraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnAcumuladoExtraStateChanged
        
        //se genera cada vez que el spinner que controla el tiempo acumulado cambia de valor
        int tiempoAcumulado = (int)spnAcumuladoExtra.getValue();
        int tiempoExtra = (int)spnTiempoExtra.getValue();
        
        //valida que el tiempo acumulado coincida con el que tiene el usuario
        if(tiempoAcumulado<= usuario.getTiempoAcumulado()){
        
                lblTiempoTotal.setText("Tiempo a agregar: " + (tiempoExtra+tiempoAcumulado) +" minutos");
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Tiene "+usuario.getTiempoAcumulado()+" minutos acumulados!");
            spnAcumuladoExtra.setValue(tiempoAcumulado-30);
        }
    }//GEN-LAST:event_spnAcumuladoExtraStateChanged

    private void btnConfirmarTiempoExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarTiempoExtraActionPerformed
        
        int tiempoAcumulado = (int)spnAcumuladoExtra.getValue();
        int tiempoExtra = (int)spnTiempoExtra.getValue();
        String placa = lblVehiculoExtra.getText();
        
        if(true){
        
           //genera un nuevo ticket al ser tiempo extra
           int total = (tiempoAcumulado + tiempoExtra) * precioMinuto; //genera el total del tiempo extra
           
           Vehiculo vehiculo = usuario.buscarVehiculo(placa);
            
           //El vehiculo cambia a su nuevo ticket vigente
            vehiculo.generarTicketTiempoExtra(tiempoExtra, total); 
            
            //enviar correo
            String cuerpo = "TIEMPO AGREGADO: " + String.valueOf(spnTiempoExtra) + "TIEMPO ACUMULADO UTILIZADO: " + String.valueOf(spnAcumuladoExtra) +
            "TIEMPO TOTAL: " + String.valueOf(total) + "PLACA DEL VEHICULO APLICADO: " + vehiculo.getPlaca() + "VEHICULO APLICADO: " + vehiculo.getMarca() + " " + vehiculo.getModelo();
            crearEmail(cuerpo, "TIEMPO EXTRA AGREGADO", usuario.getCorreo().getCorreo());
            enviarEmail();
            JOptionPane.showMessageDialog(null, "Tiempo actualizado!");
            inicializarTBMisParqueos();
            //oculta el panel
            pnlAgregarTiempo.setVisible(false);
            actualizarInformacion(usuario, usuario.getIdentificacion());
            
        }
    }//GEN-LAST:event_btnConfirmarTiempoExtraActionPerformed

    private void btnConsultarOtroEspacio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarOtroEspacio1ActionPerformed
        pnlAgregarTiempo.setVisible(false);
    }//GEN-LAST:event_btnConsultarOtroEspacio1ActionPerformed

    private void spnTiempoExtraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTiempoExtraStateChanged
        //se genera cada vez que el tiempo en minutos extras cambia su valor
        int tiempoAcumulado = (int)spnAcumuladoExtra.getValue();
        int tiempoExtra = (int)spnTiempoExtra.getValue();
        //valida que el tiempo siga siendo menor o igual al que se tiene diponible
        if(tiempoAcumulado<= usuario.getTiempoAcumulado()){
        
                lblTiempoTotal.setText("Tiempo a agregar: " + (tiempoExtra+tiempoAcumulado)  +" minutos");
            
        }
    }//GEN-LAST:event_spnTiempoExtraStateChanged

    private void btnAgregarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVehiculoActionPerformed
        btnAgregarVehiculo.setVisible(false);
        tpPanelModificaciones.setVisible(true);
        tpPanelModificaciones.setSelectedIndex(0);
    }//GEN-LAST:event_btnAgregarVehiculoActionPerformed

    private void btnConfirmarTiempoExtra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarTiempoExtra1ActionPerformed

        //AGREGA UN VEHICULO, AL BOTON BTNAGREGARVEHICULO
        //No se ha modificado el nombre de la funcion
       String placa, modelo, marca;
        
        placa = txtPlacaAgregar.getText();
        marca = txtMarcaAgregar.getText();
        modelo = txtModeloAgregar.getText();
        
        //valida
        if(validarPlaca(placa)){
                if(true){
                if(validarModeloMarca(marca)){

                    if(validarModeloMarca(modelo)){

                            Vehiculo vehiculo = new Vehiculo(placa, modelo, marca, usuario);
                            //agrega el vehiculo al usuario
                            usuario.agregarVehiculo(vehiculo);
                            JOptionPane.showMessageDialog(null, "Vehiculo agregado exitosamente");

                            tpPanelModificaciones.setVisible(false);
                            //refresca la tabla de vehiculos
                            inicializarTablaVehiculos();
                            btnAgregarVehiculo.setVisible(true);
                            actualizarInformacion(usuario, usuario.getIdentificacion());
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Al ingresar un modelo debe tener menos de 15 caracteres");
                }
                else
                    JOptionPane.showMessageDialog(null, "Al ingresar una marca debe tener menos de 15 caracteres");
            }
        }    
        else
             JOptionPane.showMessageDialog(null, "Debe ingresar la placa con 6 digitos");
        
    }//GEN-LAST:event_btnConfirmarTiempoExtra1ActionPerformed

    private void btnVolverVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverVehiculosActionPerformed
        btnAgregarVehiculo.setVisible(true);
        tpPanelModificaciones.setVisible(false);
    }//GEN-LAST:event_btnVolverVehiculosActionPerformed

    private void btnVolverVehiculos3btnVolverVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverVehiculos3btnVolverVehiculosActionPerformed
        btnAgregarVehiculo.setVisible(true);
        tpPanelModificaciones.setVisible(false);
    }//GEN-LAST:event_btnVolverVehiculos3btnVolverVehiculosActionPerformed

    
    //---------------VALIDACIONES MODIFICAR VEHICULO--------------------
    private boolean validarPlaca(String placa){
    
         return (placa.length()==6);
    }
    
    private boolean validarModeloMarca(String elemento){
    
        if(elemento.length() > 15)
            return false;
        else
            return true;
    }
   
        //---------------FIN VALIDACIONES MODIFICAR VEHICULO--------------------
    
    private void btnConfirmarActualzacionVehiculobtnConfirmarTiempoExtra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActualzacionVehiculobtnConfirmarTiempoExtra1ActionPerformed
        String placa, modelo, marca;
        
        placa = txtPlacaActualizar.getText();
        marca = txtMarcaActualizar.getText();
        modelo = txtModeloActualizar.getText();
        
        if(validarPlaca(placa)){
        
            if(validarModeloMarca(marca)){
            
                if(validarModeloMarca(modelo)){
                    
                        Vehiculo vehiculo = usuario.buscarVehiculo(lblPlaccaActualizar.getText());
                        //actualiza el vehiculo con sus datos
                        vehiculo.actualizarVehiculo(placa, modelo, marca);
                        JOptionPane.showMessageDialog(null, "Vehiculo actualizado exitosamente");
                        
                        tpPanelModificaciones.setVisible(false);
                        inicializarTablaVehiculos(); //refresca los datos de la tabla
                        btnAgregarVehiculo.setVisible(true);
                        actualizarInformacion(usuario, usuario.getIdentificacion());
                }
                else
                    JOptionPane.showMessageDialog(null, "Al ingresar un modelo debe tener menos de 15 caracteres");
            }
            else
                JOptionPane.showMessageDialog(null, "Al ingresar una marca debe tener menos de 15 caracteres");
        }
        else
             JOptionPane.showMessageDialog(null, "Debe ingresar la placa con 6 digitos");
        
    }//GEN-LAST:event_btnConfirmarActualzacionVehiculobtnConfirmarTiempoExtra1ActionPerformed

    
    private void eliminarVehiculo(String placa){
    
            //genera una confirmacion de eliminar el vehiculo
            String[] options = { "Si", "No" };
            var selection = JOptionPane.showOptionDialog(null, "Esta segur@ de eliminar el vehiculo con placa "+placa+"?", "Alerta!!", 
                                                              0, 3, null, options, options[0]);
            if (selection == 0) {
                //obtiene el vehiculo que fue buscado
                Vehiculo vehiculo = usuario.buscarVehiculo(placa);
                //valida que el vehiculo no este parqueado y por lo tanto no tiene un ticket asocioado
                if(vehiculo.getTicketVigente()==null){
                    //elimina el vehiculo del usuario
                    usuario.removerVehiculo(placa);
                    JOptionPane.showMessageDialog(null, "Vehiculo eliminado exitosamente!");
                    inicializarTablaVehiculos();
                    actualizarInformacion(usuario, usuario.getIdentificacion());
                }
                else{
                    JOptionPane.showMessageDialog(null, "El vehiculo se encuentra parqueado en un espacio!");
                }
              }
            else{
                JOptionPane.showMessageDialog(null, "No se eliminara el vehiculo!");
            }
        
    }
    
    private void inicializaPanelActualiza(String placa){
    
           btnAgregarVehiculo.setVisible(false);
           tpPanelModificaciones.setVisible(true);
           tpPanelModificaciones.setSelectedIndex(1);
           
           //busca en vehiculo a actualizar
           Vehiculo vehiculo = usuario.buscarVehiculo(placa);
           lblPlaccaActualizar.setText(vehiculo.getPlaca());
           txtPlacaActualizar.setText(vehiculo.getPlaca());
           txtMarcaActualizar.setText(vehiculo.getMarca());
           txtModeloActualizar.setText(vehiculo.getModelo());
        
    }
    
    private void tblVehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVehiculoMouseClicked
        //genera este metodo cada vez que la tabla de los vehiculos es presionada
        
        columna=tblVehiculo.getColumnModel().getColumnIndexAtX(evt.getX());
        row=evt.getY() /tblVehiculo.getRowHeight();
        if(columna <= tblVehiculo.getColumnCount() && columna >= 0 && row <= tblVehiculo.getRowCount() && row>=0){
            
            Object objeto = tblVehiculo.getValueAt(row, columna);
            if(objeto instanceof RondedBordes){
            
                ((RondedBordes)objeto).doClick();
                
                RondedBordes botones = (RondedBordes)objeto;
                
                if(botones.getName().equals("btnEliminarVehiculo")){
                    String placa = (String)tblVehiculo.getValueAt(row, columna-6);
                    eliminarVehiculo(placa);
                }
                else if(botones.getName().equals("btnActualizarVehiculo")){
                
                    String placa = (String)tblVehiculo.getValueAt(row, columna-5);
                    inicializaPanelActualiza(placa);

                }
            }
            
        }
        
    }//GEN-LAST:event_tblVehiculoMouseClicked

    
    //------------------------Carga de parqueos en la tabla mis parqueos-------------------------------
    
    public void inicializarTBMisParqueos(){
    
        tblMisParqueos.setDefaultRenderer(Object.class, new RenderTable());
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String identificadores [] = {"Vehiculo","Espacio", "Tiempo restante", "Agregar Tiempo", "Desaparcar"};
        modelo.setColumnIdentifiers(identificadores);
        

        //ESTILIZANDO BOTONES
        RondedBordes agregarTiempo = new RondedBordes();
        agregarTiempo.setText("+");
        agregarTiempo.setName("btnAgregarTiempo");
        agregarTiempo.setColor1(new Color(114,245,66));
        agregarTiempo.setColor2(new Color(114,245,66));
        agregarTiempo.setColor3(new Color(114,245,66));
        agregarTiempo.setForeground(Color.white);
        agregarTiempo.setMinimumSize(new Dimension(2,2));
        agregarTiempo.setMaximumSize(new Dimension(2,2));
        agregarTiempo.setPreferredSize(new Dimension(2,2));
        
        
        RondedBordes desaparcar = new RondedBordes();
        desaparcar.setText("-");
        desaparcar.setName("btnDesaparcar");
        desaparcar.setColor1(new Color(156, 50, 57));
        desaparcar.setColor2(new Color(156, 50, 57));
        desaparcar.setColor3(new Color(156, 50, 57));
        desaparcar.setForeground(Color.white);
        desaparcar.setSize(10,30);
        //---------------------------------
        
        modelo.getDataVector().removeAllElements();
        
        for(Vehiculo obj : usuario.getVehiculos()){
        
            if(obj.getTicketVigente()!= null){
            
                TicketParqueo ticket = obj.getTicketVigente();
                Long tiempoRestante = ChronoUnit.MINUTES.between( LocalDateTime.now(),ticket.getHoraSistema().plusMinutes(ticket.getTiempoParqueo()));
                System.out.println(ticket.getHoraSistema());
                System.out.println( LocalDateTime.now());
                modelo.addRow(new Object[]{obj.getPlaca(),ticket.getEspacio().getNumero(), tiempoRestante + " minutos", agregarTiempo,desaparcar});
                
            }
            
        }
        
        
        tblMisParqueos.setModel(modelo);
        tblMisParqueos.setRowHeight(40);

        
    }
    
    //------------------------Fin de parqueso en la tabla mis parqueos-----------------------------------

    
    
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
            java.util.logging.Logger.getLogger(MenuUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUsuario(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes BtnParqueos1;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes BtnVehiculos;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnActualizarPerfil;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnActualizarTarjeta;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnAgregarVehiculo;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnCerrarSesion;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnConfirmarActualzacionVehiculo;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnConfirmarAgregarVehiculo;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnConfirmarTiempoExtra;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnConsultarEspacio;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnConsultarOtroEspacio;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnConsultarOtroEspacio1;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnMetodoPago;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnParquear;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnParquearEspacio;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnPerfil;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnReportes;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnRepostarEspacio;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnRestablecerContra;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnVolverPerfil;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnVolverVehiculos2;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnVolverVehiculos3;
    private javax.swing.JComboBox<String> cbPlacasVehiculo;
    private javax.swing.JComboBox<String> cbReportes;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelBienvenido;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblApellidos1;
    private javax.swing.JLabel lblApellidos2;
    private javax.swing.JLabel lblApellidos3;
    private javax.swing.JLabel lblCantidadEspacios;
    private javax.swing.JLabel lblEspacio;
    private javax.swing.JLabel lblEspacioDisponible;
    private javax.swing.JLabel lblEspacioExtra;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JLabel lblNombre3;
    private javax.swing.JLabel lblNombre4;
    private javax.swing.JLabel lblNombre5;
    private javax.swing.JLabel lblNombre6;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblPerfil1;
    private javax.swing.JLabel lblPerfil16;
    private javax.swing.JLabel lblPerfil17;
    private javax.swing.JLabel lblPerfil2;
    private javax.swing.JLabel lblPerfil4;
    private javax.swing.JLabel lblPerfil5;
    private javax.swing.JLabel lblPerfil6;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblPlaccaActualizar;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JLabel lblTelefono2;
    private javax.swing.JLabel lblTexto;
    private javax.swing.JLabel lblTextoTiempo1;
    private javax.swing.JLabel lblTextoTiempo2;
    private javax.swing.JLabel lblTextoTiempo3;
    private javax.swing.JLabel lblTextoTiempo5;
    private javax.swing.JLabel lblTiempoAcumulado;
    private javax.swing.JLabel lblTiempoTotal;
    private javax.swing.JLabel lblTiuloPrincipal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotaltITULO;
    private javax.swing.JLabel lblVehiculoExtra;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo panelRedondo1;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo panelRedondo2;
    private javax.swing.JTabbedPane pbTabl;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnAgregarVehiculo2;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnMisParqueos;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnParquear;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnPerfil;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnPerfil1;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnPrincipal;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnReportes;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnVehiculos;
    private javax.swing.JPanel pnlAgregarTiempo;
    private javax.swing.JSpinner spnAcumuladoExtra;
    private com.toedter.calendar.JYearChooser spnAnioTarjeta;
    private com.toedter.calendar.JMonthChooser spnMesTarjeta;
    private javax.swing.JSpinner spnTiempoAcumulado;
    private javax.swing.JSpinner spnTiempoExtra;
    private javax.swing.JSpinner spnTiempoParqueo;
    private javax.swing.JTextArea taDireccionFisica;
    private javax.swing.JTable tblEspaciosGeneral;
    private javax.swing.JTable tblMisParqueos;
    private javax.swing.JTable tblVehiculo;
    private java.awt.TextArea textArea1;
    private javax.swing.JTabbedPane tpPanelModificaciones;
    private javax.swing.JTabbedPane tpParquearEspacio;
    private javax.swing.JTabbedPane tpReportes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCodigoTarjeta;
    private javax.swing.JTextField txtEspacioConsultado;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtMarcaActualizar;
    private javax.swing.JTextField txtMarcaAgregar;
    private javax.swing.JTextField txtModeloActualizar;
    private javax.swing.JTextField txtModeloAgregar;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNumeroTarjeta;
    private javax.swing.JTextField txtPlacaActualizar;
    private javax.swing.JTextField txtPlacaAgregar;
    private javax.swing.JTextField txtPt1Mail;
    private javax.swing.JTextField txtPt2Mail;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
