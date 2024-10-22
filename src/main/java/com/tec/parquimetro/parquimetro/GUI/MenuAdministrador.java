
package com.tec.parquimetro.parquimetro.GUI;

import com.tec.parquimetro.parquimetro.Clases.Administrador;
import com.tec.parquimetro.parquimetro.Clases.Correo;
import com.tec.parquimetro.parquimetro.Clases.Espacio;
import com.tec.parquimetro.parquimetro.Clases.Inspector;
import com.tec.parquimetro.parquimetro.Clases.Login;
import com.tec.parquimetro.parquimetro.Clases.Parqueo;
import com.tec.parquimetro.parquimetro.Clases.Persona;
import com.tec.parquimetro.parquimetro.Clases.TicketParqueo;
import com.tec.parquimetro.parquimetro.Clases.Usuario;
import com.tec.parquimetro.parquimetro.Clases.Vehiculo;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class MenuAdministrador extends javax.swing.JFrame {

    /**
     * Creates new form MenuAdministrador
     */
    DefaultTableModel tableFormato = new DefaultTableModel();//formato para tabla espacios en la pestana de configuracion
    DefaultTableModel mdlEspaciosGeneral = new DefaultTableModel();//formato para tabal espacios en reporte general de espacios
    public static Administrador administrador = new Administrador();
    
        //ATRIBUTOS PARA ENVIAR REPORTES
    private static String emailDe = "paquimetrocartago@gmail.com";
    private static String contraseñaDe = "vofx ztal oawe yary";
    private static String emailPara;
    
    private Properties mProperties = new Properties();
    private Session mSession;
    private MimeMessage mCorreo;
    //FIN DE ATRIBUTOS DE REPORTES
    
    
    public MenuAdministrador(Administrador padministrador) {
        

       
        SwingUtilities.updateComponentTreeUI(this);
        initComponents();
        
        
         administrador = padministrador;
        labelBienvenido.setText(administrador.getNombre() + " " + administrador.getApellidos());
        lblId.setText(administrador.getIdentificacion());
        SpinnerNumberModel sTiempo = new SpinnerNumberModel();
        sTiempo.setMinimum(0);
        sTiempo.setStepSize(30);
        spnTiempoMinimo.setModel(sTiempo);
        
        //Define el minimo del numero a escoger dentro del spinner es decir no se podra enviar un numero menor a 0
        SpinnerNumberModel mdIncial = new SpinnerNumberModel();
        SpinnerNumberModel mdFinal = new SpinnerNumberModel();
        mdFinal.setMinimum(0);
        mdFinal.setMaximum(99999);
        mdIncial.setMinimum(0);
        mdIncial.setMaximum(99999);
        spnRangoInicial.setModel(mdIncial);
        spnRangoFinal.setModel(mdFinal);
        
        String identificadores [] = {"Espacios", "Estado"};
        tableFormato.setColumnIdentifiers(identificadores);
        
        tblEspacios.setModel(tableFormato);
        
        
        //TAB PANEL CONFIGURACION
         pbTabl.setSelectedIndex(1);
         tpReportes.setSelectedIndex(1);
        
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
         
         tpReportes.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
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
         
         lblTerminal.setVisible(false);
         txtTerminal.setVisible(false);
         

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner3 = new javax.swing.JSpinner();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        panelRedondo1 = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        jLabel1 = new javax.swing.JLabel();
        btnPerfil = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnConfinguracion = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnUsuarios = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        rondedBordes5 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnReportes = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        labelBienvenido = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        pbTabl = new javax.swing.JTabbedPane();
        panelConfiguracion = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        jLabel3 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMinutos = new javax.swing.JLabel();
        lblCosto = new javax.swing.JLabel();
        Date dateIncio = new Date();
        SpinnerDateModel sIncio = new SpinnerDateModel(dateIncio, null, null, Calendar.HOUR_OF_DAY);
        spnHoraInicio = new javax.swing.JSpinner(sIncio);
        txtPrecio = new javax.swing.JTextField();
        Date dateFinal= new Date();
        SpinnerDateModel sFinal = new SpinnerDateModel(dateFinal, null, null, Calendar.HOUR_OF_DAY);
        spnHoraFinal = new javax.swing.JSpinner(sFinal);
        lblHora1 = new javax.swing.JLabel();
        lblHora2 = new javax.swing.JLabel();
        spnTiempoMinimo = new javax.swing.JSpinner();
        txtCostoMulta = new javax.swing.JTextField();
        btnActualizarParametros = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        panelRedondo2 = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEspacios = new javax.swing.JTable();
        btnEliminar = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnAgregarEspacio = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        spnRangoFinal = new javax.swing.JSpinner();
        spnRangoInicial = new javax.swing.JSpinner();
        lblHora3 = new javax.swing.JLabel();
        lblHora4 = new javax.swing.JLabel();
        lblHora5 = new javax.swing.JLabel();
        panelRedondo3 = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        jLabel4 = new javax.swing.JLabel();
        pnPerfil = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        btnRestablecerContra = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        lblTelefono1 = new javax.swing.JLabel();
        lblTelefono2 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtPt2Mail = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtNombre1 = new javax.swing.JTextField();
        txtPt1Mail = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDireccionFisica = new javax.swing.JTextArea();
        lblApellidos1 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        lblApellidos2 = new javax.swing.JLabel();
        lblIdentificacion = new javax.swing.JLabel();
        lblApellidos3 = new javax.swing.JLabel();
        lblApellidos = new javax.swing.JLabel();
        btnActualizarPerfil = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        pnlUsuarios = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        btnActualizarPerfil2 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        btnEliminarUsuario = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        txtIdEliminar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pnReportes = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        jLabel8 = new javax.swing.JLabel();
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
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnDescargarMultasPDF = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        dcFinMulta = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dcInicioMulta = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btnDescargarEspaciosIngPDF = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        dcFinEspacio = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        dcInicioEspacio = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btnDescargarEspaciosUsadosPDF = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        dcFinEspacioUse = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        dcInicioEspacioUse = new com.toedter.calendar.JDateChooser();
        jLabel28 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        btnDescargarMultaHechaPDF = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        dcFinMultaHecha = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        dcInicioMultaHecha = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        cbReportes = new javax.swing.JComboBox<>();
        pnAnadirUsuario = new com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo();
        lblPerfil1 = new javax.swing.JLabel();
        txtTelefonoU = new javax.swing.JTextField();
        txtPt2MailU = new javax.swing.JTextField();
        txtApellidosU = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        taDireccionFisicaU = new javax.swing.JTextArea();
        txtIdentificacionU = new javax.swing.JTextField();
        lblIdentificacion1 = new javax.swing.JLabel();
        lblApellidos4 = new javax.swing.JLabel();
        lblNombre2 = new javax.swing.JLabel();
        lblTelefono3 = new javax.swing.JLabel();
        lblTelefono4 = new javax.swing.JLabel();
        txtNombreU = new javax.swing.JTextField();
        txtPt1MailU = new javax.swing.JTextField();
        lblApellidos5 = new javax.swing.JLabel();
        lblApellidos6 = new javax.swing.JLabel();
        lblApellidos7 = new javax.swing.JLabel();
        btnAgregarUsuario = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        cbTipoUsuario = new javax.swing.JComboBox<>();
        lblApellidos8 = new javax.swing.JLabel();
        txtTerminal = new javax.swing.JTextField();
        lblTerminal = new javax.swing.JLabel();
        btnActualizarPerfil3 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        lblApellidos9 = new javax.swing.JLabel();
        dcFechaIngreso = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtPinAnterior = new javax.swing.JTextField();
        txtPinNuevo = new javax.swing.JTextField();
        rondedBordes1 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();
        rondedBordes2 = new com.tec.parquimetro.parquimetro.GUI.RondedBordes();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(29, 24, 39));

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

        btnConfinguracion.setForeground(new java.awt.Color(0, 0, 51));
        btnConfinguracion.setText("Configuracion");
        btnConfinguracion.setColor1(new java.awt.Color(255, 255, 255));
        btnConfinguracion.setColor2(new java.awt.Color(255, 255, 255));
        btnConfinguracion.setColor3(new java.awt.Color(204, 204, 204));
        btnConfinguracion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnConfinguracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfinguracionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfinguracionMouseExited(evt);
            }
        });
        btnConfinguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfinguracionActionPerformed(evt);
            }
        });

        btnUsuarios.setForeground(new java.awt.Color(0, 0, 51));
        btnUsuarios.setText("Usuarios");
        btnUsuarios.setColor1(new java.awt.Color(255, 255, 255));
        btnUsuarios.setColor2(new java.awt.Color(255, 255, 255));
        btnUsuarios.setColor3(new java.awt.Color(204, 204, 204));
        btnUsuarios.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseExited(evt);
            }
        });
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
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
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondo1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRedondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rondedBordes5, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(btnConfinguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(btnConfinguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(rondedBordes5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        pbTabl.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pbTabl.setForeground(new java.awt.Color(57, 54, 66));
        pbTabl.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        panelConfiguracion.setBackground(new java.awt.Color(57, 54, 66));
        panelConfiguracion.setRoundBottomLeft(15);
        panelConfiguracion.setRoundBottomRight(15);
        panelConfiguracion.setRoundTopLeft(15);
        panelConfiguracion.setRoundTopRight(15);
        panelConfiguracion.setVisible(false);
        panelConfiguracion.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                panelConfiguracionAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Configuracion");

        lblHora.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("Horario de regulacion");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Precio por hora");

        lblMinutos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblMinutos.setForeground(new java.awt.Color(255, 255, 255));
        lblMinutos.setText("Tiempo minimo");

        lblCosto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblCosto.setForeground(new java.awt.Color(255, 255, 255));
        lblCosto.setText("Costo de la multa");

        JSpinner.DateEditor si = new JSpinner.DateEditor(spnHoraInicio, "HH:mm");
        spnHoraInicio.setEditor(si);

        txtPrecio.setText("₡0.00");

        JSpinner.DateEditor dFinal = new JSpinner.DateEditor(spnHoraFinal, "HH:mm");
        spnHoraFinal.setEditor(dFinal);

        lblHora1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblHora1.setForeground(new java.awt.Color(255, 255, 255));
        lblHora1.setText("Hora de inicio");

        lblHora2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblHora2.setForeground(new java.awt.Color(255, 255, 255));
        lblHora2.setText("Hora de final");

        spnTiempoMinimo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTiempoMinimoStateChanged(evt);
            }
        });

        txtCostoMulta.setText("₡0.00");

        btnActualizarParametros.setBackground(new java.awt.Color(255, 145, 77));
        btnActualizarParametros.setText("Actualizar");
        btnActualizarParametros.setColor1(new java.awt.Color(204, 102, 0));
        btnActualizarParametros.setColor2(new java.awt.Color(204, 102, 0));
        btnActualizarParametros.setColor3(new java.awt.Color(204, 102, 0));
        btnActualizarParametros.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnActualizarParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarParametrosActionPerformed(evt);
            }
        });

        panelRedondo2.setBackground(new java.awt.Color(29, 24, 39));
        panelRedondo2.setRoundBottomLeft(20);
        panelRedondo2.setRoundBottomRight(20);
        panelRedondo2.setRoundTopLeft(20);
        panelRedondo2.setRoundTopRight(20);

        tblEspacios.setForeground(new java.awt.Color(51, 51, 51));
        tblEspacios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Espacio", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEspacios.setGridColor(new java.awt.Color(124, 176, 56));
        jScrollPane1.setViewportView(tblEspacios);

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setForeground(new java.awt.Color(0, 0, 51));
        btnEliminar.setText("Eliminar");
        btnEliminar.setColor1(new java.awt.Color(255, 255, 255));
        btnEliminar.setColor2(new java.awt.Color(255, 255, 255));
        btnEliminar.setColor3(new java.awt.Color(204, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAgregarEspacio.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarEspacio.setForeground(new java.awt.Color(0, 0, 51));
        btnAgregarEspacio.setText("Agregar");
        btnAgregarEspacio.setColor1(new java.awt.Color(255, 255, 255));
        btnAgregarEspacio.setColor2(new java.awt.Color(255, 255, 255));
        btnAgregarEspacio.setColor3(new java.awt.Color(204, 204, 204));
        btnAgregarEspacio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAgregarEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEspacioActionPerformed(evt);
            }
        });

        spnRangoFinal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnRangoFinalStateChanged(evt);
            }
        });

        spnRangoInicial.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnRangoInicialStateChanged(evt);
            }
        });
        spnRangoInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spnRangoInicialMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                spnRangoInicialMousePressed(evt);
            }
        });

        lblHora3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblHora3.setForeground(new java.awt.Color(255, 255, 255));
        lblHora3.setText("Indique un rango de numero de espacios");

        lblHora4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblHora4.setForeground(new java.awt.Color(255, 255, 255));
        lblHora4.setText("Inicio");

        lblHora5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblHora5.setForeground(new java.awt.Color(255, 255, 255));
        lblHora5.setText("Final");

        javax.swing.GroupLayout panelRedondo2Layout = new javax.swing.GroupLayout(panelRedondo2);
        panelRedondo2.setLayout(panelRedondo2Layout);
        panelRedondo2Layout.setHorizontalGroup(
            panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondo2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRedondo2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondo2Layout.createSequentialGroup()
                                .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAgregarEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(101, 101, 101))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondo2Layout.createSequentialGroup()
                                .addComponent(spnRangoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))
                    .addGroup(panelRedondo2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(lblHora3)
                        .addGap(0, 50, Short.MAX_VALUE))
                    .addGroup(panelRedondo2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lblHora4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHora5)
                        .addGap(83, 83, 83))))
            .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondo2Layout.createSequentialGroup()
                    .addContainerGap(395, Short.MAX_VALUE)
                    .addComponent(spnRangoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(195, 195, 195)))
        );
        panelRedondo2Layout.setVerticalGroup(
            panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondo2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRedondo2Layout.createSequentialGroup()
                        .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRedondo2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(spnRangoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHora3))
                        .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRedondo2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHora4)
                                .addGap(51, 51, 51))
                            .addGroup(panelRedondo2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblHora5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(btnAgregarEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(panelRedondo2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(panelRedondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRedondo2Layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(spnRangoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(210, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelConfiguracionLayout = new javax.swing.GroupLayout(panelConfiguracion);
        panelConfiguracion.setLayout(panelConfiguracionLayout);
        panelConfiguracionLayout.setHorizontalGroup(
            panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHora)
                    .addComponent(jLabel7)
                    .addComponent(lblCosto))
                .addGap(18, 18, 18)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblHora1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHora2)
                        .addGap(152, 152, 152))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracionLayout.createSequentialGroup()
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                .addComponent(spnHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracionLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblMinutos)))
                        .addGap(18, 18, 18)
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnTiempoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracionLayout.createSequentialGroup()
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCostoMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizarParametros, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracionLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(panelRedondo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(51, 51, 51))
        );
        panelConfiguracionLayout.setVerticalGroup(
            panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(40, 40, 40)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHora)
                    .addComponent(spnHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHora1)
                    .addComponent(lblHora2))
                .addGap(28, 28, 28)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMinutos)
                    .addComponent(spnTiempoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCosto)
                    .addComponent(txtCostoMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarParametros, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(panelRedondo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        pbTabl.addTab("tab1", panelConfiguracion);

        panelRedondo3.setBackground(new java.awt.Color(57, 54, 66));
        panelRedondo3.setRoundBottomLeft(15);
        panelRedondo3.setRoundBottomRight(15);
        panelRedondo3.setRoundTopLeft(15);
        panelRedondo3.setRoundTopRight(15);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PARQUIMETRO");

        javax.swing.GroupLayout panelRedondo3Layout = new javax.swing.GroupLayout(panelRedondo3);
        panelRedondo3.setLayout(panelRedondo3Layout);
        panelRedondo3Layout.setHorizontalGroup(
            panelRedondo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondo3Layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRedondo3Layout.setVerticalGroup(
            panelRedondo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondo3Layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pbTabl.addTab("tab2", panelRedondo3);

        pnPerfil.setBackground(new java.awt.Color(57, 54, 66));

        lblPerfil.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil.setText("Mi Perfil");

        lblNombre1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre1.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre1.setText("Nombre");

        btnRestablecerContra.setBackground(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setText("Restablecer contrasena");
        btnRestablecerContra.setColor1(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setColor2(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setColor3(new java.awt.Color(111, 158, 94));
        btnRestablecerContra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnRestablecerContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestablecerContraActionPerformed(evt);
            }
        });

        lblTelefono1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTelefono1.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono1.setText("Telefono");

        lblTelefono2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTelefono2.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono2.setText("Direccion Fisica");

        txtTelefono.setText("00000000");
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        txtPt2Mail.setText("dominio");

        txtApellidos.setText("Apellidos");
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        txtNombre1.setText("Nombre");

        txtPt1Mail.setText("nombre");

        taDireccionFisica.setColumns(20);
        taDireccionFisica.setRows(5);
        jScrollPane2.setViewportView(taDireccionFisica);

        lblApellidos1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos1.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos1.setText("Dominio de correo electronico");

        txtIdentificacion.setText("0-0000-0000");

        lblApellidos2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos2.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos2.setText("@");

        lblIdentificacion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblIdentificacion.setForeground(new java.awt.Color(255, 255, 255));
        lblIdentificacion.setText("Identificacion");

        lblApellidos3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos3.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos3.setText("Encabezado de correo electronico");

        lblApellidos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos.setText("Apellidos");

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

        javax.swing.GroupLayout pnPerfilLayout = new javax.swing.GroupLayout(pnPerfil);
        pnPerfil.setLayout(pnPerfilLayout);
        pnPerfilLayout.setHorizontalGroup(
            pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerfilLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPerfilLayout.createSequentialGroup()
                        .addComponent(txtPt1Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(lblApellidos2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnPerfilLayout.createSequentialGroup()
                        .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnPerfilLayout.createSequentialGroup()
                                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIdentificacion)
                                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnPerfilLayout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(btnRestablecerContra, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTelefono2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblApellidos1)
                                    .addComponent(txtPt2Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnActualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnPerfilLayout.createSequentialGroup()
                                .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(73, 73, 73))
                    .addGroup(pnPerfilLayout.createSequentialGroup()
                        .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefono1)
                            .addComponent(lblNombre1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblApellidos)
                        .addGap(296, 296, 296))
                    .addGroup(pnPerfilLayout.createSequentialGroup()
                        .addComponent(lblApellidos3)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(pnPerfilLayout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(lblPerfil)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnPerfilLayout.setVerticalGroup(
            pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerfilLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(lblPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre1)
                    .addComponent(lblApellidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono1)
                    .addComponent(lblTelefono2))
                .addGap(12, 12, 12)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnPerfilLayout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdentificacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblApellidos3)
                            .addComponent(lblApellidos1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPt1Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApellidos2)
                            .addComponent(txtPt2Mail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(129, 129, 129)
                .addGroup(pnPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestablecerContra, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        pbTabl.addTab("tab3", pnPerfil);

        pnlUsuarios.setBackground(new java.awt.Color(57, 54, 66));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Usuarios");

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificacion", "Nombre", "Telefono", "Terminal", "Correo", "Fecha de ingreso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblUsuarios);

        btnActualizarPerfil2.setBackground(new java.awt.Color(255, 145, 77));
        btnActualizarPerfil2.setText("Agregar Usuario");
        btnActualizarPerfil2.setColor1(new java.awt.Color(204, 102, 0));
        btnActualizarPerfil2.setColor2(new java.awt.Color(204, 102, 0));
        btnActualizarPerfil2.setColor3(new java.awt.Color(204, 102, 0));
        btnActualizarPerfil2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnActualizarPerfil2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPerfil2ActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setBackground(new java.awt.Color(195, 69, 69));
        btnEliminarUsuario.setText("Eliminar Usuario");
        btnEliminarUsuario.setColor1(new java.awt.Color(195, 69, 69));
        btnEliminarUsuario.setColor2(new java.awt.Color(195, 69, 69));
        btnEliminarUsuario.setColor3(new java.awt.Color(195, 69, 69));
        btnEliminarUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Identificacion");

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizarPerfil2, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlUsuariosLayout.createSequentialGroup()
                            .addComponent(txtIdEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(3, 3, 3)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnActualizarPerfil2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pbTabl.addTab("tab4", pnlUsuarios);

        pnReportes.setBackground(new java.awt.Color(57, 54, 66));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Reportes");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEspaciosGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(10, 10, 10)))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(lblCantidadEspacios)
                                .addGap(266, 266, 266)
                                .addComponent(btnDescargarEspaciosPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 31, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEspaciosGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblCantidadEspacios)
                    .addComponent(btnDescargarEspaciosPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tpReportes.addTab("Espacios", jPanel2);

        jPanel3.setBackground(new java.awt.Color(57, 54, 66));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );

        tpReportes.addTab("principal", jPanel3);

        jPanel4.setBackground(new java.awt.Color(29, 24, 39));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Ingreso de multas");

        btnDescargarMultasPDF.setBackground(new java.awt.Color(153, 255, 51));
        btnDescargarMultasPDF.setText("Descargar PDF");
        btnDescargarMultasPDF.setColor1(new java.awt.Color(126, 217, 87));
        btnDescargarMultasPDF.setColor2(new java.awt.Color(126, 217, 87));
        btnDescargarMultasPDF.setColor3(new java.awt.Color(126, 217, 87));
        btnDescargarMultasPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarMultasPDFActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Inicio del periodo");

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Fin del periodo");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 111, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(dcInicioMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(dcFinMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(101, 101, 101))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDescargarMultasPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(298, 298, 298))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel14)
                .addGap(69, 69, 69)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcInicioMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFinMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnDescargarMultasPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );

        tpReportes.addTab("Ingreso de multas", jPanel4);

        jPanel5.setBackground(new java.awt.Color(29, 24, 39));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ingreso de espacios");

        btnDescargarEspaciosIngPDF.setBackground(new java.awt.Color(153, 255, 51));
        btnDescargarEspaciosIngPDF.setText("Descargar PDF");
        btnDescargarEspaciosIngPDF.setColor1(new java.awt.Color(126, 217, 87));
        btnDescargarEspaciosIngPDF.setColor2(new java.awt.Color(126, 217, 87));
        btnDescargarEspaciosIngPDF.setColor3(new java.awt.Color(126, 217, 87));
        btnDescargarEspaciosIngPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarEspaciosIngPDFActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Inicio del periodo");

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Fin del periodo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 111, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(dcInicioEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(dcFinEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(101, 101, 101))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDescargarEspaciosIngPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(298, 298, 298))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel15)
                .addGap(69, 69, 69)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcInicioEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFinEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnDescargarEspaciosIngPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );

        tpReportes.addTab("Ingreso de espacios", jPanel5);

        jPanel6.setBackground(new java.awt.Color(29, 24, 39));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Espacios utilizados");

        btnDescargarEspaciosUsadosPDF.setBackground(new java.awt.Color(153, 255, 51));
        btnDescargarEspaciosUsadosPDF.setText("Descargar PDF");
        btnDescargarEspaciosUsadosPDF.setColor1(new java.awt.Color(126, 217, 87));
        btnDescargarEspaciosUsadosPDF.setColor2(new java.awt.Color(126, 217, 87));
        btnDescargarEspaciosUsadosPDF.setColor3(new java.awt.Color(126, 217, 87));
        btnDescargarEspaciosUsadosPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarEspaciosUsadosPDFActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Inicio del periodo");

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Fin del periodo");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 111, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel28))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(dcInicioEspacioUse, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(dcFinEspacioUse, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(101, 101, 101))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDescargarEspaciosUsadosPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(298, 298, 298))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel16)
                .addGap(69, 69, 69)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcInicioEspacioUse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFinEspacioUse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnDescargarEspaciosUsadosPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );

        tpReportes.addTab("Espacios Usados", jPanel6);

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
                .addGap(0, 111, Short.MAX_VALUE)
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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDescargarMultaHechaPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(298, 298, 298))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel17)
                .addGap(70, 70, 70)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcInicioMultaHecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFinMultaHecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnDescargarMultaHechaPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );

        tpReportes.addTab("Multas Hechas", jPanel7);

        cbReportes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estadisticas detalladas de espacios", "Estadisticas resumidas de espacios", "Ingresos de dinero por estacionamiento", "Ingresos de dinero por multas", "Historial de los espacios usados", "Historial de multas", "Espacios de parqueo" }));
        cbReportes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbReportesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnReportesLayout = new javax.swing.GroupLayout(pnReportes);
        pnReportes.setLayout(pnReportesLayout);
        pnReportesLayout.setHorizontalGroup(
            pnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnReportesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pnReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tpReportes)
                    .addComponent(cbReportes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnReportesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(366, 366, 366))
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
                .addGap(16, 16, 16))
        );

        tpReportes.getAccessibleContext().setAccessibleName("Espacios");

        pbTabl.addTab("tab5", pnReportes);

        pnAnadirUsuario.setBackground(new java.awt.Color(57, 54, 66));
        pnAnadirUsuario.setRoundBottomLeft(15);
        pnAnadirUsuario.setRoundBottomRight(15);
        pnAnadirUsuario.setRoundTopLeft(15);
        pnAnadirUsuario.setRoundTopRight(15);

        lblPerfil1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPerfil1.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil1.setText("Anadir Usuario");

        txtTelefonoU.setText("00000000");
        txtTelefonoU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoUActionPerformed(evt);
            }
        });

        txtPt2MailU.setText("dominio");

        txtApellidosU.setText("Apellidos");
        txtApellidosU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosUActionPerformed(evt);
            }
        });

        taDireccionFisicaU.setColumns(20);
        taDireccionFisicaU.setRows(5);
        jScrollPane4.setViewportView(taDireccionFisicaU);

        lblIdentificacion1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblIdentificacion1.setForeground(new java.awt.Color(255, 255, 255));
        lblIdentificacion1.setText("Identificacion");

        lblApellidos4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos4.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos4.setText("Apellidos");

        lblNombre2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre2.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre2.setText("Nombre");

        lblTelefono3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTelefono3.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono3.setText("Telefono");

        lblTelefono4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTelefono4.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono4.setText("Direccion Fisica");

        txtNombreU.setText("Nombre");

        txtPt1MailU.setText("nombre");

        lblApellidos5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos5.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos5.setText("Dominio de correo electronico");

        lblApellidos6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos6.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos6.setText("@");

        lblApellidos7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos7.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos7.setText("Encabezado de correo electronico");

        btnAgregarUsuario.setBackground(new java.awt.Color(255, 145, 77));
        btnAgregarUsuario.setText("Anadir");
        btnAgregarUsuario.setColor1(new java.awt.Color(204, 102, 0));
        btnAgregarUsuario.setColor2(new java.awt.Color(204, 102, 0));
        btnAgregarUsuario.setColor3(new java.awt.Color(204, 102, 0));
        btnAgregarUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuarioActionPerformed(evt);
            }
        });

        cbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Inspector" }));
        cbTipoUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoUsuarioItemStateChanged(evt);
            }
        });

        lblApellidos8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos8.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos8.setText("Tipo de usuario");

        txtTerminal.setToolTipText("Terminal de inspector");

        lblTerminal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTerminal.setForeground(new java.awt.Color(255, 255, 255));
        lblTerminal.setText("Terminal");

        btnActualizarPerfil3.setBackground(new java.awt.Color(102, 102, 102));
        btnActualizarPerfil3.setText("Volver");
        btnActualizarPerfil3.setColor1(new java.awt.Color(102, 102, 102));
        btnActualizarPerfil3.setColor2(new java.awt.Color(102, 102, 102));
        btnActualizarPerfil3.setColor3(new java.awt.Color(102, 102, 102));
        btnActualizarPerfil3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnActualizarPerfil3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPerfil3ActionPerformed(evt);
            }
        });

        lblApellidos9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellidos9.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidos9.setText("Fecha de ingreso");

        javax.swing.GroupLayout pnAnadirUsuarioLayout = new javax.swing.GroupLayout(pnAnadirUsuario);
        pnAnadirUsuario.setLayout(pnAnadirUsuarioLayout);
        pnAnadirUsuarioLayout.setHorizontalGroup(
            pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAnadirUsuarioLayout.createSequentialGroup()
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnAnadirUsuarioLayout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(lblPerfil1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAnadirUsuarioLayout.createSequentialGroup()
                        .addContainerGap(101, Short.MAX_VALUE)
                        .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnActualizarPerfil3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblApellidos7)
                                .addComponent(lblApellidos8)
                                .addGroup(pnAnadirUsuarioLayout.createSequentialGroup()
                                    .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbTipoUsuario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPt1MailU, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                                    .addGap(67, 67, 67)
                                    .addComponent(lblApellidos6))
                                .addComponent(lblTerminal)
                                .addComponent(txtIdentificacionU, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblIdentificacion1)
                                .addComponent(txtTelefonoU, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTelefono3)
                                .addComponent(txtNombreU, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNombre2)))
                        .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnAnadirUsuarioLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnAnadirUsuarioLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblApellidos4)
                                    .addComponent(lblTelefono4)
                                    .addComponent(lblApellidos5)
                                    .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtApellidosU)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                        .addComponent(txtPt2MailU))
                                    .addComponent(lblApellidos9))))))
                .addGap(85, 85, 85))
        );
        pnAnadirUsuarioLayout.setVerticalGroup(
            pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAnadirUsuarioLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblPerfil1)
                .addGap(18, 18, 18)
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidos4)
                    .addComponent(lblNombre2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidosU, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreU, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefono3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTelefono4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnAnadirUsuarioLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                        .addGap(36, 36, 36))
                    .addGroup(pnAnadirUsuarioLayout.createSequentialGroup()
                        .addComponent(txtTelefonoU, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblIdentificacion1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdentificacionU, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellidos7)
                    .addComponent(lblApellidos5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPt1MailU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPt2MailU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidos6))
                .addGap(27, 27, 27)
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidos8)
                    .addComponent(lblApellidos9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTipoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTerminal)
                .addGap(18, 18, 18)
                .addComponent(txtTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(pnAnadirUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarPerfil3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pbTabl.addTab("", pnAnadirUsuario);

        jPanel8.setBackground(new java.awt.Color(57, 54, 66));

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Restablecer mi PIN");

        jLabel20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel20.setText("Ingrese el PIN anterior:");

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel21.setText("Ingrese el nuevo PIN:");

        txtPinAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPinAnteriorActionPerformed(evt);
            }
        });

        txtPinNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPinNuevoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(jLabel19))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(97, 97, 97)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPinAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPinNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(rondedBordes1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(rondedBordes2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(282, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel19)
                .addGap(112, 112, 112)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtPinAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtPinNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rondedBordes1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rondedBordes2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89))
        );

        pbTabl.addTab("tab7", jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(panelRedondo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pbTabl)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRedondo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pbTabl, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pbTabl.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

      private void actualizarInformacion(Administrador admin, String identificacion){
    
        //Utilizado para actualizar los usuarios al modificar su informacion
        Login login = new Login();
         login.actualizarPersona(admin,identificacion);
        
    }
    
    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        pbTabl.setSelectedIndex(4);
        tpReportes.setSelectedIndex(1);
    }//GEN-LAST:event_btnReportesActionPerformed

    private void rondedBordes5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rondedBordes5ActionPerformed
        LoginJFrame login = new LoginJFrame();
        
        actualizarInformacion(administrador, administrador.getIdentificacion()); //actualiza la informacion que fue generada por el usuario
        
        administrador=null; //elimina la informacion que apuntaba el usuario
        this.setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_rondedBordes5ActionPerformed

    private void cargarDatosPerfil(){
         //coloca la informacion cactual del usuario dentro de los campos de texto
        txtNombre1.setText(administrador.getNombre());
        txtApellidos.setText(administrador.getApellidos());
        txtTelefono.setText(String.valueOf(administrador.getTelefono()));
        taDireccionFisica.setText(administrador.getDireccionFisica());
        txtIdentificacion.setText(administrador.getIdentificacion());
        txtPt1Mail.setText(administrador.getCorreo().getStr1());
        txtPt2Mail.setText(administrador.getCorreo().getStr2());
    
    
    }
    
    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        pbTabl.setSelectedIndex(2);
        cargarDatosPerfil();
        
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void spnTiempoMinimoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTiempoMinimoStateChanged

        
    }//GEN-LAST:event_spnTiempoMinimoStateChanged

    private void btnActualizarParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarParametrosActionPerformed
        
        if(validarConversion(String.valueOf(spnTiempoMinimo.getValue()))){
            if(validarConversion(txtPrecio.getText())){
                if(validarConversion(txtCostoMulta.getText())){
                    
                        Date date = (Date) spnHoraInicio.getValue(); // Asumiendo que obtienes un objeto Date
                        Instant instant = date.toInstant();
                        ZoneId zoneId = ZoneId.systemDefault(); // O cualquier zona horaria que desees
                        LocalTime horaInicio= instant.atZone(zoneId).toLocalTime();
                        
                        Date dateFinal= (Date) spnHoraFinal.getValue(); // Asumiendo que obtienes un objeto Date
                        instant = dateFinal.toInstant();
                        zoneId = ZoneId.systemDefault(); // O cualquier zona horaria que desees
                        LocalTime horaFinal= instant.atZone(zoneId).toLocalTime();
                        
                        
                        if(horaFinal.isAfter(horaInicio)){
                                 int tiempoMinimo = Integer.valueOf(String.valueOf(spnTiempoMinimo.getValue()));             
                                 int precioHora = Integer.valueOf(txtPrecio.getText());
                                 int costoMulta = Integer.valueOf(txtCostoMulta.getText());
                                 
                                 Parqueo parqueo = new Parqueo();
                                 parqueo.lecturaArchivo();
                                 parqueo.setCostoMulta(costoMulta);
                                 parqueo.setPrecioHora(precioHora);
                                 parqueo.setTiempoMinimo(tiempoMinimo);
                                 parqueo.setHoraFinal(horaFinal);
                                 parqueo.setHoraInicio(horaInicio);
                                 
                                 parqueo.actualizarParametros(parqueo);
                                 //envio de correo
                                 String cuerpo = "Se le informa la actualizacion de parametros dentro del sistema -> \n COSTO DE LA MULTA: " + parqueo.getCostoMulta() + "\nPRECIO POR HORA: " + parqueo.getPrecioHora() +
                                 "\nTIEMPO MINIMO: " + parqueo.getTiempoMinimo() + "\nHORA DE INICIO: " + parqueo.getHoraInicio() + "\nHORA FINAL: " + parqueo.getHoraFinal();
                                crearEmail(cuerpo, "PARAMETROS ACTUAILIZADOS", administrador.getCorreo().getCorreo());
                                enviarEmail();
                                 JOptionPane.showMessageDialog(null, "Parametros actualizados exitosamente!");
                        
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "La hora final es menor a la hora de inicio");
                        }
                }else{
                    JOptionPane.showMessageDialog(null, "Costo de multa posee caracteres alfabeticos");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Precio por hora posee caracteres alfabeticos");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Tiempo minimo posee caracteres alfabeticos");
        }
        
    }//GEN-LAST:event_btnActualizarParametrosActionPerformed
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
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(MenuInspector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MenuInspector.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
     public void cambiarPin(){
         if (txtPinAnterior.getText().equals(administrador.getPin())){
             try{ //verificar si el pin ingresado es un entero
                 int pinNuevo = Integer.parseInt(txtPinAnterior.getText());
                 if (pinNuevo <= 9999 && pinNuevo >= 1000){
                     administrador.setPin(txtPinAnterior.getText());
                     JOptionPane.showMessageDialog(null, "Su PIN ha sido cambiado con éxito!");
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
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    String[] options = { "Si", "No" };
    var selection = JOptionPane.showOptionDialog(null, "Esta segur@ de eliminar los espacios?", "Alerta!!", 
                                                      0, 3, null, options, options[0]);
    if (selection == 0) {

                if(validarRangos()){

                     int rInicial = Integer.parseInt(String.valueOf(spnRangoInicial.getValue()));
                     int rFinal = Integer.parseInt(String.valueOf(spnRangoFinal.getValue()));

                     Parqueo parqueo = new Parqueo();

                     parqueo.lecturaArchivo();

                     //Validar la existencia de estos campos
                     String camposNoExistentes="";
                     String camposExistentes="";

                     for(int i = rInicial ; i<= rFinal ; i++){

                         if(parqueo.buscarEspacio(i) == null){

                              camposNoExistentes+= String.valueOf(i) + "   "; 

                         }
                         else{
                             camposExistentes+= String.valueOf(i) + "   "; 
                         }

                     }

                     parqueo.eliminarEspacios(rInicial, rFinal);
                     parqueo.cargarArchivo();

                     if(camposNoExistentes!="" && camposExistentes == ""){

                         JOptionPane.showMessageDialog(null, "Los espacios  " + camposNoExistentes + "no existen.");
                     }
                     else if(camposNoExistentes!="" && camposExistentes != ""){

                     JOptionPane.showMessageDialog(null, "Los espacios  " + camposNoExistentes + "no existen. Se ha eliminado exitosamente " + camposExistentes );

                     }
                     else{
                         JOptionPane.showMessageDialog(null, "Se ha eliminado exitosamente " + camposExistentes );
                     }
                     cargarTablaEspacios(parqueo.getEspacios());

                 }
                 else{
                     JOptionPane.showMessageDialog(null, "El rango inicial es mayor al rango final");
                 }
      }
    else{
        JOptionPane.showMessageDialog(null, "No se ha eliminado!");
    }
    
    }//GEN-LAST:event_btnEliminarActionPerformed

    private boolean validarRangos(){
    
        int rInicial = Integer.parseInt(String.valueOf(spnRangoInicial.getValue()));
        int rFinal = Integer.parseInt(String.valueOf(spnRangoFinal.getValue()));
        
        if(rInicial<= rFinal){
        
            return true;
        }
        else
            return false;
    }
    
    private void btnAgregarEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEspacioActionPerformed
        // TODO add your handling code here:
        
         if(validarRangos()){
        
            int rInicial = Integer.parseInt(String.valueOf(spnRangoInicial.getValue()));
            int rFinal = Integer.parseInt(String.valueOf(spnRangoFinal.getValue()));
            
            Parqueo parqueo = new Parqueo();
            
            parqueo.lecturaArchivo();
            
            //Validar la existencia de estos campos
            String camposNoExistentes="";
            String camposExistentes="";
            
            for(int i = rInicial ; i<= rFinal ; i++){
            
                if(parqueo.buscarEspacio(i) == null){
                
                     camposNoExistentes+= String.valueOf(i) + "   "; 
                    
                }
                else{
                    camposExistentes+= String.valueOf(i) + "   "; 
                }
            
            }
            
            parqueo.agregarEspacios(rInicial, rFinal);
            parqueo.cargarArchivo();
            
            if(camposExistentes!="" && camposNoExistentes == ""){
            
                JOptionPane.showMessageDialog(null, "Los espacios  " + camposExistentes + "ya existen.");
            }
            else if(camposNoExistentes!="" && camposExistentes != ""){
            
                JOptionPane.showMessageDialog(null, "Los espacios  " + camposExistentes + "ya existen. Se ha agregadado exitosamente " + camposNoExistentes );
                String cuerpo = "Se le informa la actualizacion de los espacios en Parquimetro cartago-> Se ha añadido los espacios " + camposExistentes;
                                        crearEmail(cuerpo, "ACTUALIZACION DE ESPACIOS PARQUIMETRO CARTAGO", administrador.getCorreo().getCorreo());
                                        enviarEmail();
            
            }
            else{
                
                   String cuerpo = "Se le informa la actualizacion de los espacios en Parquimetro cartago-> Se ha añadido los espacios " + camposExistentes;
                                    crearEmail(cuerpo, "ACTUALIZACION DE ESPACIOS PARQUIMETRO CARTAGO", administrador.getCorreo().getCorreo());
                                    enviarEmail();
                
                JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente " + camposNoExistentes );
            }
            cargarTablaEspacios(parqueo.getEspacios());
        
        }
        else{
            JOptionPane.showMessageDialog(null, "El rango inicial es mayor al rango final");
        }
        
        
    }//GEN-LAST:event_btnAgregarEspacioActionPerformed

    private void spnRangoFinalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnRangoFinalStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spnRangoFinalStateChanged

    private void spnRangoInicialStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnRangoInicialStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spnRangoInicialStateChanged

    private void cargarTablaEspacios(List<Espacio> espacios){
    
            tableFormato.getDataVector().removeAllElements();
            String estado;

            
            for(Espacio obj : espacios){
                
                if(obj.getEstado())
                    estado="Libre";
                else
                    estado="Ocupado";
                
                tableFormato.addRow(new Object[]{obj.getNumero(), estado});
                
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
    
    private void cargarParametros(Parqueo parqueo){

         // Formatear el LocalTime a "HH:mm"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaInicio = parqueo.getHoraInicio().format(timeFormatter);
        String horaFinal = parqueo.getHoraFinal().format(timeFormatter);
        
        try {
            // Convertir el string a Date usando SimpleDateFormat
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date inicio = dateFormat.parse(horaInicio);
            Date hFinal = dateFormat.parse(horaFinal);
           spnHoraInicio.setValue(inicio);
           spnHoraFinal.setValue(hFinal);
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        txtPrecio.setText(String.valueOf(parqueo.getPrecioHora()));
        txtCostoMulta.setText(String.valueOf(parqueo.getCostoMulta()));
        spnTiempoMinimo.setValue(parqueo.getTiempoMinimo());
    
    }
    
    private Parqueo obtenerParametros(){
    
            Parqueo parqueo = new Parqueo();
            parqueo.lecturaArchivo();
            return parqueo;
    }
    
    private void btnConfinguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfinguracionActionPerformed
    
        
        pbTabl.setSelectedIndex(0);
        
        Parqueo parqueo = obtenerParametros();
            
        cargarParametros(parqueo);
            
        panelConfiguracion.setVisible(true);
        cargarTablaEspacios( parqueo.getEspacios());
            
        
        
        
    }//GEN-LAST:event_btnConfinguracionActionPerformed

    private void panelConfiguracionAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_panelConfiguracionAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_panelConfiguracionAncestorAdded

    
    
    private void btnConfinguracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfinguracionMouseEntered
       
        
        btnConfinguracion.setColor1(Color.orange);
        btnConfinguracion.setColor2(Color.orange);
        btnConfinguracion.setColor3(Color.orange);
        btnConfinguracion.setForeground(Color.white);
    }//GEN-LAST:event_btnConfinguracionMouseEntered

    private void btnConfinguracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfinguracionMouseExited
        btnConfinguracion.setColor1(Color.white);
        btnConfinguracion.setColor2(Color.white);
        btnConfinguracion.setColor3(Color.white);
        btnConfinguracion.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_btnConfinguracionMouseExited

    private void spnRangoInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnRangoInicialMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_spnRangoInicialMouseClicked

    private void spnRangoInicialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnRangoInicialMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_spnRangoInicialMousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       pbTabl.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void cargarTablaUsuarios() throws ClassNotFoundException{
    
        List<Persona> personas = new ArrayList<Persona>();
        
        personas = Login.cargarUsuarios("listaUsuarios.txt");
        
            
        
        //carga de la tabla
        DefaultTableModel tableUsuarios = new DefaultTableModel();//formato para tabla espacios en la pestana de configuracion
        String identificadores [] = {"Identificacion", "Nombre", "Telefono", "Terminal","Correo","Fecha de ingreso"};
        tableUsuarios.setColumnIdentifiers(identificadores);
        tblUsuarios.setModel(tableUsuarios);
        for(Persona persona : personas){
            if(persona instanceof Administrador administradorCast){
                
                tableUsuarios.addRow(new Object[]{persona.getIdentificacion(), persona.getNombre()+" "+persona.getApellidos(),persona.getTelefono(), "Administrador", persona.getCorreo().getCorreo(), persona.getFechaIngreso().toString()});
                
             }else if(persona instanceof Inspector inspectorCast){
                 
                  tableUsuarios.addRow(new Object[]{persona.getIdentificacion(), persona.getNombre()+" "+persona.getApellidos(),persona.getTelefono(), ((Inspector) persona).getTerminalInspeccion(), persona.getCorreo().getCorreo(), persona.getFechaIngreso().toString()});
             }
            
        }
    }
    
    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        pbTabl.setSelectedIndex(3);
         try{
           cargarTablaUsuarios();
        } catch (ClassNotFoundException ex) {
             System.out.println("");
        }
        
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseEntered
        btnUsuarios.setColor1(Color.orange);
        btnUsuarios.setColor2(Color.orange);
        btnUsuarios.setColor3(Color.orange);
        btnUsuarios.setForeground(Color.white);
    }//GEN-LAST:event_btnUsuariosMouseEntered

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
        btnReportes.setColor1(Color.orange);
        btnReportes.setColor2(Color.orange);
        btnReportes.setColor3(Color.orange);
        btnReportes.setForeground(Color.white);
    }//GEN-LAST:event_btnReportesMouseEntered

    private void btnUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseExited
        btnUsuarios.setColor1(Color.white);
        btnUsuarios.setColor2(Color.white);
        btnUsuarios.setColor3(Color.white);
        btnUsuarios.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_btnUsuariosMouseExited

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
        btnReportes.setColor1(Color.white);
        btnReportes.setColor2(Color.white);
        btnReportes.setColor3(Color.white);
        btnReportes.setForeground(new Color(0,0,51));
    }//GEN-LAST:event_btnReportesMouseExited

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

    private void btnRestablecerContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestablecerContraActionPerformed
        pbTabl.setSelectedIndex(6);
    }//GEN-LAST:event_btnRestablecerContraActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

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
    
    private void btnActualizarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPerfilActionPerformed
       
        String nombre;
        String apellidos;
        String identificacion;
        String direccionFisica;
        String pt1Correo;
        String pt2Correo;
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
                                       pt1Correo = txtPt1Mail.getText();
                                       pt2Correo =  txtPt2Mail.getText();
                                       telefono = Integer.valueOf(txtTelefono.getText());
                                       Correo correo = new Correo(pt1Correo, pt2Correo);
                                       fechaIngreso = LocalDate.now();
                                       
                                       //Almacena la identificacion del usuario anteriormente de ser modificada
                                       identificacionGeneral = administrador.getIdentificacion();
                                       
                                       Administrador  admin = new Administrador(nombre, apellidos,telefono, direccionFisica, fechaIngreso, identificacion,"", correo);
                                       
                                       //modifica la informacion modificada en el archivo de datos
                                       administrador.actualizarDatos(admin);
                                        
                                        actualizarInformacion(administrador, identificacionGeneral);
                                         labelBienvenido.setText(administrador.getNombre() + " " + administrador.getApellidos());
                                        lblId.setText(administrador.getIdentificacion());
                                       JOptionPane.showMessageDialog(null, "Datos actualizados existosamente!");
                                       
                                         
                                         String cuerpo = "Se le informa la actualizacion de sus datos personales en Parquimetro cartago->\n Nombre: " + administrador.getNombre() + "\n" + "Apellidos: " + administrador.getApellidos() + "\n" + "Direccion fisica: " + administrador.getDireccionFisica() + "\n" + 
                                    "Identificacion: " + administrador.getIdentificacion() + "\n" + "Telefono: " + administrador.getTelefono();
                                    crearEmail(cuerpo, "ACTUALIZACION DE SUS DATOS PERSONALES", administrador.getCorreo().getCorreo());
                                    enviarEmail();
                                         
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

    private void btnActualizarPerfil2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPerfil2ActionPerformed
        pbTabl.setSelectedIndex(5);
        
        txtNombreU.setText("");
        txtApellidosU.setText("");
        taDireccionFisicaU.setText("");
        txtIdentificacionU.setText("");
        txtPt1MailU.setText("");
        txtPt2MailU.setText("");
        txtTelefonoU.setText("");
        txtTerminal.setText("");
    }//GEN-LAST:event_btnActualizarPerfil2ActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
           
            String[] options = { "Si", "No" };
            var selection = JOptionPane.showOptionDialog(null, "Esta segur@ de eliminar el usuario?", "Alerta!!", 
                                                              0, 3, null, options, options[0]);
            if (selection == 0) {

                Login login = new Login();
                if(login.eliminarPersona(txtIdEliminar.getText())){
                    JOptionPane.showMessageDialog(null, "Eliminado exitosamente!");
                        try{
                          cargarTablaUsuarios();
                       } catch (ClassNotFoundException ex) {
                            System.out.println("");
                       }
                }else{
                    JOptionPane.showMessageDialog(null, "No se eliminara!");
                }
                txtIdEliminar.setText("");
                
              }
            else{
                JOptionPane.showMessageDialog(null, "No se actualizara!");
            }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void txtTelefonoUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoUActionPerformed

    private void txtApellidosUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosUActionPerformed

    private boolean validarTerminalInspeccion(String terminal){
        
        if(terminal.length() == 6)
            return true;
        else
            return false;
    
    }
    
    private String generarPin(){
    
        Random random = new Random();
        int pinNumerico = random.nextInt(1000);
        return String.format("%04d", pinNumerico);
    }
    
    private void btnAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioActionPerformed

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
        int tipoUsuario;
        Login login = new Login();
        Correo correo;
        
        if(validarNombre(txtNombreU.getText())){
        
                if(validarApellidos(txtApellidosU.getText())){
                    
                   if(validacionDireccionFisica(taDireccionFisicaU.getText())){
                       
                       if(validacionIdentificacion(txtIdentificacionU.getText())){
                           
                           if(validarConversion(txtTelefonoU.getText())){
                           
                               if(validarTelefono(Integer.parseInt(txtTelefonoU.getText()))){
                               
                                            String pin = generarPin();

                                          //Almacena los datos de los campos de texto en las variables para ser alamacenadas
                                           nombre = txtNombreU.getText();
                                           apellidos= txtApellidosU.getText();
                                           direccionFisica = taDireccionFisicaU.getText();
                                           identificacion = txtIdentificacionU.getText();
                                           pt1Correo = txtPt1MailU.getText();
                                           pt2Correo =  txtPt2MailU.getText();
                                           telefono = Integer.valueOf(txtTelefonoU.getText());
                                           correo = new Correo(pt1Correo, pt2Correo);
                                           fechaIngreso =  dcFechaIngreso.getDate().toInstant()
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate();
                                           
                                            if(cbTipoUsuario.getSelectedItem() == "Administrador"){

                                                Persona  admin = new Administrador(nombre, apellidos,telefono, direccionFisica, fechaIngreso, identificacion,pin, correo);
                                                login.agregarPersona(admin);
                                                String cuerpo = "Se le informa el registro como administrador en el sistema Parquimetro Cartago, su PIN es -> "+pin;
                                                 crearEmail(cuerpo, "REGISTRO EN EL SISTEMA", correo.getCorreo());
                                                 enviarEmail();
                                                  JOptionPane.showMessageDialog(null, "Usuario guardado existosamente!");
                                                    pbTabl.setSelectedIndex(3);
                                                    
                                                    try{
                                                        cargarTablaUsuarios();
                                                     } catch (ClassNotFoundException ex) {
                                                          System.out.println("");
                                                     }
                                            }
                                            else{

                                                if(validarTerminalInspeccion(txtTerminal.getText())){

                                                    terminal = txtTerminal.getText();
                                                    Persona  inspector = new Inspector(nombre, apellidos,telefono, direccionFisica, fechaIngreso, identificacion,pin,terminal, correo);
                                                    login.agregarPersona(inspector);
                                                    String cuerpo = "Se le informa el registro como inspector en el sistema Parquimetro Cartago, su PIN es -> "+pin;
                                                    crearEmail(cuerpo, "REGISTRO EN EL SISTEMA", correo.getCorreo());
                                                    enviarEmail();
                                                    JOptionPane.showMessageDialog(null, "Usuario guardado existosamente!");
                                                    pbTabl.setSelectedIndex(3);
                                               }
                                                else{
                                                     JOptionPane.showMessageDialog(null, "La terminal debe tener 6 caracteres!");
                                                }
                                             try{
                                                cargarTablaUsuarios();
                                             } catch (ClassNotFoundException ex) {
                                                  System.out.println("");
                                             }
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
        
        
    }//GEN-LAST:event_btnAgregarUsuarioActionPerformed

    private void btnActualizarPerfil3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPerfil3ActionPerformed
       pbTabl.setSelectedIndex(3);
    }//GEN-LAST:event_btnActualizarPerfil3ActionPerformed

    private void cbTipoUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoUsuarioItemStateChanged
        if(cbTipoUsuario.getSelectedItem() == "Inspector"){
        
                    lblTerminal.setVisible(true);
                    txtTerminal.setVisible(true);
        }
        else{
        
            lblTerminal.setVisible(false);
            txtTerminal.setVisible(false);
        
        }
    }//GEN-LAST:event_cbTipoUsuarioItemStateChanged

    private void cbReportesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbReportesItemStateChanged
       
        
        if(cbReportes.getSelectedItem() == "Espacios de parqueo")
        {
             tpReportes.setSelectedIndex(0);
        } else if(cbReportes.getSelectedItem() == "Ingresos de dinero por multas")
        {
             tpReportes.setSelectedIndex(2);
        }else if(cbReportes.getSelectedItem() == "Ingresos de dinero por estacionamiento")
        {
             tpReportes.setSelectedIndex(3);
        }else if(cbReportes.getSelectedItem() == "Historial de los espacios usados")
        {
             tpReportes.setSelectedIndex(4);
        }else if(cbReportes.getSelectedItem() == "Historial de multas")
        {
             tpReportes.setSelectedIndex(5);
        }
        
    }//GEN-LAST:event_cbReportesItemStateChanged

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

    private void cbEspaciosGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEspaciosGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEspaciosGeneralActionPerformed

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

    private void btnDescargarMultasPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarMultasPDFActionPerformed
        Date fechaInicio = dcInicioMulta.getDate();
        Date fechaFinal = dcFinMulta.getDate();
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
                    File f = new File("IngresosMultas");
                    String rutaArchivo = "";
                    fileChoo.setSelectedFile(f);
                    Parqueo parqueo = new Parqueo();
                    parqueo.lecturaArchivo();
                    int option = fileChoo.showSaveDialog(this);

                    if(option == JFileChooser.APPROVE_OPTION){

                        f = fileChoo.getSelectedFile();
                        rutaArchivo = f.toString();
                    }
                    parqueo.generaIngresosMultasPDF(rutaArchivo, inicio, finalF);
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
    }//GEN-LAST:event_btnDescargarMultasPDFActionPerformed

    private void btnDescargarEspaciosIngPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarEspaciosIngPDFActionPerformed
        Date fechaInicio = dcInicioEspacio.getDate();
        Date fechaFinal = dcFinEspacio.getDate();
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
                    File f = new File("IngresosEstacionamiento");
                    String rutaArchivo = "";
                    fileChoo.setSelectedFile(f);
                    Parqueo parqueo = new Parqueo();
                    parqueo.lecturaArchivo();
                    int option = fileChoo.showSaveDialog(this);

                    if(option == JFileChooser.APPROVE_OPTION){

                        f = fileChoo.getSelectedFile();
                        rutaArchivo = f.toString();
                    }
                    parqueo.generaIngresosEstacionamientoPDF(rutaArchivo, inicio, finalF);
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
    }//GEN-LAST:event_btnDescargarEspaciosIngPDFActionPerformed

    private void btnDescargarEspaciosUsadosPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarEspaciosUsadosPDFActionPerformed
        Date fechaInicio = dcInicioEspacioUse.getDate();
        Date fechaFinal = dcFinEspacioUse.getDate();
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
                    File f = new File("EspaciosUsados");
                    String rutaArchivo = "";
                    fileChoo.setSelectedFile(f);
                    Parqueo parqueo = new Parqueo();
                    parqueo.lecturaArchivo();
                    int option = fileChoo.showSaveDialog(this);

                    if(option == JFileChooser.APPROVE_OPTION){

                        f = fileChoo.getSelectedFile();
                        rutaArchivo = f.toString();
                    }
                    parqueo.generarEspaciosUsadosPDF(rutaArchivo, inicio, finalF);
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
    }//GEN-LAST:event_btnDescargarEspaciosUsadosPDFActionPerformed

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
                    parqueo.generarMultasHechasPDF(rutaArchivo, inicio, finalF);
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

    private void txtPinAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPinAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPinAnteriorActionPerformed

    private void txtPinNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPinNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPinNuevoActionPerformed

    private void rondedBordes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rondedBordes1ActionPerformed
        cambiarPin();
    }//GEN-LAST:event_rondedBordes1ActionPerformed

    private void rondedBordes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rondedBordes2ActionPerformed
        pbTabl.setSelectedIndex(2);
    }//GEN-LAST:event_rondedBordes2ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuAdministrador(administrador).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnActualizarParametros;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnActualizarPerfil;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnActualizarPerfil2;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnActualizarPerfil3;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnAgregarEspacio;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnAgregarUsuario;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnConfinguracion;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnDescargarEspaciosIngPDF;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnDescargarEspaciosPDF;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnDescargarEspaciosUsadosPDF;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnDescargarMultaHechaPDF;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnDescargarMultasPDF;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnEliminar;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnEliminarUsuario;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnPerfil;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnReportes;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnRestablecerContra;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes btnUsuarios;
    private javax.swing.JComboBox<String> cbEspaciosGeneral;
    private javax.swing.JComboBox<String> cbReportes;
    private javax.swing.JComboBox<String> cbTipoUsuario;
    private com.toedter.calendar.JDateChooser dcFechaIngreso;
    private com.toedter.calendar.JDateChooser dcFinEspacio;
    private com.toedter.calendar.JDateChooser dcFinEspacioUse;
    private com.toedter.calendar.JDateChooser dcFinMulta;
    private com.toedter.calendar.JDateChooser dcFinMultaHecha;
    private com.toedter.calendar.JDateChooser dcInicioEspacio;
    private com.toedter.calendar.JDateChooser dcInicioEspacioUse;
    private com.toedter.calendar.JDateChooser dcInicioMulta;
    private com.toedter.calendar.JDateChooser dcInicioMultaHecha;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JLabel labelBienvenido;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblApellidos1;
    private javax.swing.JLabel lblApellidos2;
    private javax.swing.JLabel lblApellidos3;
    private javax.swing.JLabel lblApellidos4;
    private javax.swing.JLabel lblApellidos5;
    private javax.swing.JLabel lblApellidos6;
    private javax.swing.JLabel lblApellidos7;
    private javax.swing.JLabel lblApellidos8;
    private javax.swing.JLabel lblApellidos9;
    private javax.swing.JLabel lblCantidadEspacios;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblHora1;
    private javax.swing.JLabel lblHora2;
    private javax.swing.JLabel lblHora3;
    private javax.swing.JLabel lblHora4;
    private javax.swing.JLabel lblHora5;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblIdentificacion1;
    private javax.swing.JLabel lblMinutos;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblPerfil1;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JLabel lblTelefono2;
    private javax.swing.JLabel lblTelefono3;
    private javax.swing.JLabel lblTelefono4;
    private javax.swing.JLabel lblTerminal;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo panelConfiguracion;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo panelRedondo1;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo panelRedondo2;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo panelRedondo3;
    private javax.swing.JTabbedPane pbTabl;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnAnadirUsuario;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnPerfil;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnReportes;
    private com.tec.parquimetro.parquimetro.GUI.Componentes.PanelRedondo pnlUsuarios;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes rondedBordes1;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes rondedBordes2;
    private com.tec.parquimetro.parquimetro.GUI.RondedBordes rondedBordes5;
    private javax.swing.JSpinner spnHoraFinal;
    private javax.swing.JSpinner spnHoraInicio;
    private javax.swing.JSpinner spnRangoFinal;
    private javax.swing.JSpinner spnRangoInicial;
    private javax.swing.JSpinner spnTiempoMinimo;
    private javax.swing.JTextArea taDireccionFisica;
    private javax.swing.JTextArea taDireccionFisicaU;
    private javax.swing.JTable tblEspacios;
    private javax.swing.JTable tblEspaciosGeneral;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTabbedPane tpReportes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosU;
    private javax.swing.JTextField txtCostoMulta;
    private javax.swing.JTextField txtIdEliminar;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtIdentificacionU;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombreU;
    private javax.swing.JTextField txtPinAnterior;
    private javax.swing.JTextField txtPinNuevo;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPt1Mail;
    private javax.swing.JTextField txtPt1MailU;
    private javax.swing.JTextField txtPt2Mail;
    private javax.swing.JTextField txtPt2MailU;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoU;
    private javax.swing.JTextField txtTerminal;
    // End of variables declaration//GEN-END:variables
}
