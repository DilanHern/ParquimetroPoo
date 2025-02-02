package com.tec.parquimetro.parquimetro.Clases;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*; //manejo de archivos
import java.nio.channels.OverlappingFileLockException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase Parqueo maneja la gestión de espacios de parqueo, multas y generación de reportes.
 * Implementa {@link Serializable}.
 */
public class Parqueo implements Serializable {
    
    //atributos
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private int precioHora;
    private int tiempoMinimo;
    private int costoMulta;
    private ArrayList<Espacio> espacios;
    private ArrayList<Multa> multas;
    
    /**
     * Constructor por defecto para la clase Parqueo.
     * Inicializa las listas de espacios y multas.
     */
    public Parqueo(){
    
        this.espacios = new ArrayList<Espacio>();
         this.multas = new ArrayList<Multa>();
    }
    
    /**
     * Constructor para crear un nuevo Parqueo con los detalles especificados.
     *
     * @param horaInicio La hora de inicio del parqueo.
     * @param horaFinal La hora de finalización del parqueo.
     * @param precioHora El precio por hora del parqueo.
     * @param tiempoMinimo El tiempo mínimo de parqueo.
     * @param costoMulta El costo de la multa.
     */
    public Parqueo(LocalTime horaInicio, LocalTime horaFinal, int precioHora, int tiempoMinimo, int costoMulta){
    
        this.costoMulta = costoMulta;
        this.horaFinal = horaFinal;
        this.horaInicio = horaInicio;
        this.precioHora = precioHora;
        this.tiempoMinimo = tiempoMinimo;
        this.espacios = new ArrayList<Espacio>();
        this.multas = new ArrayList<Multa>();

    }


    //getters y setters

    /**
     * Obtiene la hora de inicio del parqueo.
     *
     * @return La hora de inicio del parqueo.
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Establece la hora de inicio del parqueo.
     *
     * @param horaInicio La nueva hora de inicio del parqueo.
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Obtiene la hora de finalización del parqueo.
     *
     * @return La hora de finalización del parqueo.
     */
    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    /**
     * Establece la hora de finalización del parqueo.
     *
     * @param horaFinal La nueva hora de finalización del parqueo.
     */
    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * Obtiene el precio por hora del parqueo.
     *
     * @return El precio por hora del parqueo.
     */
    public int getPrecioHora() {
        return precioHora;
    }
    
    /**
     * Establece el horario de regulación del parqueo.
     *
     * @param horaIncio La hora de inicio de la regulación.
     * @param horaFinal La hora de finalización de la regulación.
     * @return true si las horas son válidas y se establecieron correctamente, false en caso contrario.
     */
   public boolean setHorarioRegulacion(LocalTime horaIncio, LocalTime horaFinal){
        
        if(horaIncio!= horaFinal){ //validacion si las horas son distintas
        
            if(horaFinal.isAfter(horaInicio)){
                    return true; //horas anadidas exitosamente
            
            }else{
                return false; //la hora final es menor que la hora de inicio
            }
        
        }
        else
            return false;//las horas son iguales
    }

    /**
     * Valida si el precio por hora es par.
     *
     * @param precioHora El precio por hora a validar.
     * @return true si el precio por hora es par, false en caso contrario.
     */
    public boolean validarPrecioHora(int precioHora) {
        
        if(precioHora%2 ==  0){
        
            return true;
        }
        else{
            return false;
        }
        
        
    }
    
    /**
     * Establece el precio por hora del parqueo.
     *
     * @param precioHora El nuevo precio por hora.
     */
    public void setPrecioHora(int precioHora) {
        
        this.precioHora = precioHora;
    }

    /**
     * Obtiene el tiempo mínimo de parqueo.
     *
     * @return El tiempo mínimo de parqueo.
     */
    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

    /**
     * Valida si el tiempo mínimo de parqueo es múltiplo de 30.
     *
     * @param tiempoMinimo El tiempo mínimo a validar.
     * @return true si el tiempo mínimo es múltiplo de 30, false en caso contrario.
     */
    public boolean validarTiempoMinimo(int tiempoMinimo) {
        
        if(tiempoMinimo%30 == 0){
            return true;
        }
        else{
            return false;
        }
        
        
    }
    
    /**
     * Establece el tiempo mínimo de parqueo.
     *
     * @param tiempoMinimo El nuevo tiempo mínimo de parqueo.
     */
   public void setTiempoMinimo(int tiempoMinimo) {
        
        this.tiempoMinimo = tiempoMinimo;
        
    }

    /**
     * Obtiene el costo de la multa.
     *
     * @return El costo de la multa.
     */
    public int getCostoMulta() {
        return costoMulta;
    }

    /**
     * Valida si el costo de la multa es mayor o igual a 0.
     *
     * @param costoMulta El costo de la multa a validar.
     * @return true si el costo de la multa es mayor o igual a 0, false en caso contrario.
     */
    public boolean validarCostoMulta(int costoMulta) {
        
        if(costoMulta>=0){
            this.costoMulta = costoMulta;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Establece el costo de la multa.
     *
     * @param costoMulta El nuevo costo de la multa.
     */
    public void setCostoMulta(int costoMulta) {
        
        this.costoMulta = costoMulta;
    }
    
    /**
     * Obtiene la lista de espacios de parqueo.
     *
     * @return La lista de espacios de parqueo.
     */
   public List<Espacio> getEspacios(){
    
        return this.espacios;
    }
    
    /**
     * Establece la lista de espacios de parqueo.
     *
     * @param espacios La nueva lista de espacios de parqueo.
     */
    public void setEspacios(ArrayList<Espacio> espacios){
    
        this.espacios = espacios;
    }
    
    /**
     * Establece la lista de multas.
     *
     * @param multas La nueva lista de multas.
     */
    public void setMultas(ArrayList<Multa> multas){
    
        this.multas = multas;
    }
    
    //metodos
    
    /**
     * Verifica y actualiza los espacios de parqueo automáticamente si se ha cumplido el tiempo de parqueo.
     */
    public void verificarReservados() {
    
        for(Espacio e : espacios){
        
            if(!e.getEstado()){ //esta ocupado
            
                TicketParqueo t =e.getTickets().getLast();
                LocalDateTime fechaFinal = t.getHoraSistema().plusMinutes(t.getTiempoParqueo());
                
                if(LocalDateTime.now().isBefore(fechaFinal)){
                
                    //desaparcar el vehiculo
                    e.setEstado(false);
                    
                    List<Persona> personas = Login.cargarUsuarios("listaUsuarios.txt");
                    
                    for (Persona persona: personas){
                        if (persona instanceof Usuario usuarioCast){

                            if(usuarioCast.getVehiculos()!=null){

                                for(Vehiculo v : usuarioCast.getVehiculos()){
                                    if(v.getPlaca().equals(t.getVehiculo().getPlaca())){
                                    
                                        v.agregarTicket(t);
                                        v.establecerTicketVigente(null);

                                        e.removerVehiculo(v.getPlaca());

                                        v.setEspacio(null);

                                        this.cargarArchivo();
                                    }
                                  }
                             }

                        }
                    }
                    try {
                        Login.guardarUsuarios("listaUsuarios.txt", (ArrayList<Persona>) personas);
                    } catch (IOException ex) {
                        Logger.getLogger(Parqueo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
            }
        
        }
    
    }
    
    /**
     * Actualiza los parámetros del parqueo.
     *
     * @param parqueo El objeto Parqueo con los nuevos parámetros.
     */
    public void actualizarParametros(Parqueo parqueo){
    
        System.out.println("yaaaa");
        setCostoMulta(parqueo.getCostoMulta());
        setPrecioHora(parqueo.getPrecioHora());
        setTiempoMinimo(parqueo.getTiempoMinimo());
        setHoraInicio(parqueo.getHoraInicio());
        setHoraFinal(parqueo.getHoraFinal());
        
        this.cargarArchivo();
    
    }
    

    /**
     * Guarda los parámetros del parqueo en un archivo.
     */
   public void cargarArchivo(){
   
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Parametros.txt"))) {
                        // Escribir cada estudiante en el archivo
                       oos.writeObject(this);
                        


        } catch (OverlappingFileLockException  e) { //valida que el objeto no haya sido abierto o que se este utilizando en otra parte
                 System.out.println("Error: el archivo ya esta en uso.");
                 e.printStackTrace();
       } catch (IOException e) {
                 System.out.println("Error al escribir el archivo.");
                 e.printStackTrace();
       }
}
   
    /**
     * Lee los parámetros del parqueo desde un archivo.
     */
   public void lecturaArchivo(){
   
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Parametros.txt"))) { 

                while (true) {
                    try {
                        
                        Parqueo auxiliar = (Parqueo) ois.readObject();
                        
                        this.setCostoMulta(auxiliar.costoMulta);
                        this.setHoraFinal(auxiliar.horaFinal);
                        this.setHoraInicio(auxiliar.horaInicio);
                        this.setPrecioHora(auxiliar.precioHora);
                        this.setTiempoMinimo(auxiliar.tiempoMinimo);
                        this.setEspacios(auxiliar.espacios);
                        this.setMultas(auxiliar.multas);
                        verificarReservados();
                    } catch (EOFException e) { //validacion para que no llegue al final de laist, se genera un excepcion y esta hace que pare el ciclo
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println("Clase no encontrada: " + e.getMessage()); //valida que la clase estudiante si se ecuenttre
                    }

                }
            }catch (FileNotFoundException  e) { //excepcion el archivo no existe
                 System.out.println("Error: el archivo no existe.");
                 e.printStackTrace();
          } 
            catch(IOException  e){ //Se ha generado otras excepciones

                System.out.println("ERROR: Error generado al intentar leer el archivo");
                e.printStackTrace();
            }
   
   }
    
    /**
     * Imprime los parámetros del parqueo.
     */
   public void toStrin(){
   
        System.out.println(this.costoMulta);
        System.out.println(this.horaFinal);
        System.out.println(this.horaInicio);
        System.out.println(this.precioHora);
        System.out.println(this.tiempoMinimo);
        
        for(Espacio espacio: espacios){
            System.out.println(espacio.getNumero() + " " + espacio.getEstado());
        }
   }

    /**
     * Lista todos los espacios de parqueo.
     *
     * @return La lista de espacios de parqueo.
     */
    public ArrayList<Espacio> listarEspacios(){
        verificarReservados();
        return espacios;
    
    }
    
    /**
     * Lista los espacios de parqueo vacíos.
     *
     * @return La lista de espacios de parqueo vacíos.
     */
    public ArrayList<Espacio> listarEspaciosVacios(){
        verificarReservados();
        ArrayList<Espacio> espaciosVacios = new ArrayList<Espacio>();
        
        for(Espacio espacio : espacios){
            
            if(espacio.getEstado())
                espaciosVacios.add(espacio);
            
        }
        
        return espaciosVacios;
    
    }
    
    /**
     * Lista los espacios de parqueo ocupados.
     *
     * @return La lista de espacios de parqueo ocupados.
     */
   public ArrayList<Espacio> listarEspaciosOcupados(){
        verificarReservados();
        ArrayList<Espacio> espaciosOcupados = new ArrayList<Espacio>();
        
        for(Espacio espacio : espacios){
            System.out.println(espacio.getVehiculos().toString());
            if(!espacio.getEstado())
                espaciosOcupados.add(espacio);
            
        }
        
        return espaciosOcupados;
    
    }
   
    /**
     * Libera un espacio de parqueo.
     *
     * @param numero El número del espacio a liberar.
     */
   public void liberarEspacio(int numero){
   
   
       Espacio espacio = buscarEspacio(numero);
       espacio.setEstado(true);
       cargarArchivo();
   
   }

    /**
     * Ocupa un espacio de parqueo.
     *
     * @param numero El número del espacio a ocupar.
     */
     public void ocuparEspacio(int numero){
   
   
       Espacio espacio = buscarEspacio(numero);
       espacio.setEstado(false);
       cargarArchivo();
   
   }
    
    /**
     * Agrega espacios de parqueo en un rango especificado.
     *
     * @param rangoInicio El número de inicio del rango.
     * @param rangoFinal El número de finalización del rango.
     * @return true si los espacios se agregaron correctamente, false en caso contrario.
     */
    public boolean agregarEspacios(int rangoInicio, int rangoFinal){
    
        
        if(rangoInicio<= rangoFinal){
        
                //anade los espacios dentro del rango indicado
                for(int i = rangoInicio; i<= rangoFinal;  i++){

                        if(buscarEspacio(i) == null){ //valida que el numero de espacio a anadir no exista

                            Espacio espacio = new Espacio(i, true);  //crea el espacio nuevo
                            espacios.add(espacio); //anade a la lista el espacio instanciado

                        }

                }
                return true;
        
        }
        else{
        
            return false;
        }

    
    }
    
    /**
     * Elimina espacios de parqueo en un rango especificado.
     *
     * @param rangoInicio El número de inicio del rango.
     * @param rangoFinal El número de finalización del rango.
     */
    public void eliminarEspacios(int rangoInicio, int rangoFinal){
    
        
        Espacio espacio;
        //anade los espacios dentro del rango indicado
        for(int i = rangoInicio;i<= rangoFinal; i++){
        
                espacio = buscarEspacio(i); //busca el espacio que se desea eliminar
                if(espacio != null){ //valida que el numero de espacio a anadir no exista
                
                    espacios.remove(espacio); //remueve de la lista el espacio a eliminar
                
                }
                    
        }
    
    }
    
    /**
     * Busca un espacio de parqueo por su número.
     *
     * @param numero El número del espacio a buscar.
     * @return El espacio encontrado, o null si no se encuentra.
     */
    public Espacio buscarEspacio(int numero){
        verificarReservados();
        
        //Busca entre los espacios registrados un espacio que coincida con el numero que se busca
        //E: Numero de tipo entero, valor a comparar
        //S: Espacio encontrado o NULL en caso de no encontrarlo
    
        for(Espacio espacio : espacios){
        
            if(espacio.getNumero() == numero){
            
                return espacio; //el espacio ya esta registrado
            }
            
        }
        
        return null;
    
    }

    /**
     * Agrega una multa a la lista de multas.
     *
     * @param multa La multa a agregar.
     */
   public void agregarMulta(Multa multa){
       
       this.multas.add(multa);
       cargarArchivo();
       System.out.println(multa.getCosto());
   }

   /**
     * Genera un archivo PDF con los espacios de parqueo vacíos.
     *
     * @param rutaArchivo La ruta donde se guardará el archivo PDF.
     */
   public void generarEspaciosVaciosPDF(String rutaArchivo){
       verificarReservados();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

            document.open();
            document.addAuthor("Parquimetro");

            //Titulo del documento
            Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
            tituloPrincipal.setAlignment(1);
            document.add(tituloPrincipal);
            Paragraph titulo = new Paragraph("Espacios Vacios");
            titulo.setAlignment(1);
            document.add(titulo);

            //FECHAS  DEL REPORTE
            document.add(Chunk.NEWLINE);//SALTO DE LINEA
            Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
            fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
            document.add(fecha);
            //FIN FECHAS  DEL REPORTE

            document.add(Chunk.NEWLINE);//SALTO DE LINEA

            //Genera la tabla por argumentos envia el numero de celdas por fila
            PdfPTable tabla = new PdfPTable(1);
            tabla.setWidthPercentage(50);

            //ENCABEZADO DE LA TABLA
            Phrase frase = new Phrase("Numero de espacio", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
            PdfPCell espacio = new PdfPCell(frase);
            espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
            espacio.setPaddingTop(10);
            espacio.setPaddingBottom(10);
            espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

            tabla.addCell(espacio);
            
            
            //FIN DEL ENCABEZADO DE LA TABLA

            //Controla la cantidad de elementos considerados en el reporte
            int cant=0;

            Parqueo parqueo = new Parqueo();
            parqueo.lecturaArchivo();

            ArrayList<Espacio> espacios =  parqueo.listarEspaciosVacios();
            //carga los elementos a la tabla
            for(Espacio obj : espacios){
                
                tabla.addCell(String.valueOf(obj.getNumero()));
                cant++;
            }

            //Pie de la tabla, informa el total de los elementos
            PdfPCell footerCell = new PdfPCell(new Phrase("Total de espacios: " + cant, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
            footerCell.setBackgroundColor(new BaseColor(184,102,20));
            footerCell.setBorderColor(new BaseColor(184,102,20));
            footerCell.setColspan(1);  // Combina las 3 columnas
            footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
            footerCell.setPaddingTop(5);
            footerCell.setPaddingRight(10);
            footerCell.setPaddingBottom(7);
            tabla.addCell(footerCell);

            document.add(tabla);

            document.close();

        } catch (FileNotFoundException ex) {

        } catch (DocumentException ex) {

        }
       
   }
   
   /**
     * Genera un archivo PDF con los espacios de parqueo generales.
     *
     * @param rutaArchivo La ruta donde se guardará el archivo PDF.
     */
   public void generarEspaciosGeneralPDF(String rutaArchivo){
       verificarReservados();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

            document.open();
            document.addAuthor("Parquimetro");

            //Titulo del documento
            Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
            tituloPrincipal.setAlignment(1);
            document.add(tituloPrincipal);
            Paragraph titulo = new Paragraph("Espacios Generales");
            titulo.setAlignment(1);
            document.add(titulo);

            //FECHAS  DEL REPORTE
            document.add(Chunk.NEWLINE);//SALTO DE LINEA
            Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
            fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
            document.add(fecha);
            //FIN FECHAS  DEL REPORTE

            document.add(Chunk.NEWLINE);//SALTO DE LINEA

            //Genera la tabla por argumentos envia el numero de celdas por fila
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(50);

            //ENCABEZADO DE LA TABLA
            Phrase frase = new Phrase("Numero de espacio", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
            PdfPCell espacio = new PdfPCell(frase);
            espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
            espacio.setPaddingTop(10);
            espacio.setPaddingBottom(10);
            espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

            tabla.addCell(espacio);

            Phrase estadoF = new Phrase("Estado del espacio", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
            PdfPCell estado = new PdfPCell(estadoF);
            estado.setHorizontalAlignment(Element.ALIGN_CENTER);
            estado.setPaddingTop(10);
            estado.setPaddingBottom(10);
            estado.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

            tabla.addCell(estado);
            
            
            //FIN DEL ENCABEZADO DE LA TABLA

            //Controla la cantidad de elementos considerados en el reporte
            int cant=0;

            Parqueo parqueo = new Parqueo();
            parqueo.lecturaArchivo();

            ArrayList<Espacio> espacios =  parqueo.listarEspacios();
            //carga los elementos a la tabla
            String estadoEspacio = "";
            for(Espacio obj : espacios){

                if(obj.getEstado())
                    estadoEspacio = "Libre";
                else
                    estadoEspacio = "Ocupado";
                
                tabla.addCell(String.valueOf(obj.getNumero()));
                tabla.addCell(estadoEspacio);
                cant++;
            }

            //Pie de la tabla, informa el total de los elementos
            PdfPCell footerCell = new PdfPCell(new Phrase("Total de espacios: " + cant, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
            footerCell.setBackgroundColor(new BaseColor(184,102,20));
            footerCell.setBorderColor(new BaseColor(184,102,20));
            footerCell.setColspan(3);  // Combina las 3 columnas
            footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
            footerCell.setPaddingTop(5);
            footerCell.setPaddingRight(10);
            footerCell.setPaddingBottom(7);
            tabla.addCell(footerCell);

            document.add(tabla);

            document.close();

        } catch (FileNotFoundException ex) {

        } catch (DocumentException ex) {

        }
       
   }
 
    /**
     * Genera un archivo PDF con los detalles de los espacios ocupados.
     *
     * @param rutaArchivo La ruta donde se guardará el archivo PDF.
     */
    public void generarEspaciosOcupadosPDF(String rutaArchivo){
       verificarReservados();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

            document.open();
            document.addAuthor("Parquimetro");

            //Titulo del documento
            Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
            tituloPrincipal.setAlignment(1);
            document.add(tituloPrincipal);
            Paragraph titulo = new Paragraph("Espacios Ocupados");
            titulo.setAlignment(1);
            document.add(titulo);

            //FECHAS  DEL REPORTE
            document.add(Chunk.NEWLINE);//SALTO DE LINEA
            Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
            fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
            document.add(fecha);
            //FIN FECHAS  DEL REPORTE

            document.add(Chunk.NEWLINE);//SALTO DE LINEA

            //Genera la tabla por argumentos envia el numero de celdas por fila
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);

            //ENCABEZADO DE LA TABLA
            Phrase frase = new Phrase("Numero de espacio", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
            PdfPCell espacio = new PdfPCell(frase);
            espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
            espacio.setPaddingTop(10);
            espacio.setPaddingBottom(10);
            espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

            tabla.addCell(espacio);

            frase = new Phrase("Placa", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
            PdfPCell placa = new PdfPCell(frase);
            placa.setHorizontalAlignment(Element.ALIGN_CENTER);
            placa.setPaddingTop(10);
            placa.setPaddingBottom(10);
            placa.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

            tabla.addCell(placa);
            
            frase = new Phrase("Costo", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
            PdfPCell costo = new PdfPCell(frase);
            costo.setHorizontalAlignment(Element.ALIGN_CENTER);
            costo.setPaddingTop(10);
            costo.setPaddingBottom(10);
            costo.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

            tabla.addCell(costo);
            
            frase = new Phrase("Tiempo inicial", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
            PdfPCell tInicial = new PdfPCell(frase);
            tInicial.setHorizontalAlignment(Element.ALIGN_CENTER);
            tInicial.setPaddingTop(10);
            tInicial.setPaddingBottom(10);
            tInicial.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

            tabla.addCell(tInicial);
            
             frase = new Phrase("Tiempo final", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
            PdfPCell tFinal = new PdfPCell(frase);
            tFinal.setHorizontalAlignment(Element.ALIGN_CENTER);
            tFinal.setPaddingTop(10);
            tFinal.setPaddingBottom(10);
            tFinal.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

            tabla.addCell(tFinal);
            
            //FIN DEL ENCABEZADO DE LA TABLA

            //Controla la cantidad de elementos considerados en el reporte
            int cant=0;

            Parqueo parqueo = new Parqueo();
            parqueo.lecturaArchivo();

            ArrayList<Espacio> espacios =  parqueo.listarEspaciosOcupados();
            //carga los elementos a la tabla
            String estadoEspacio = "";
            TicketParqueo ticket;
            for(Espacio obj : espacios){

                ticket = obj.getVehiculos().getFirst().getTicketVigente();
                
                tabla.addCell(String.valueOf(obj.getNumero()));
                tabla.addCell(obj.getVehiculos().getFirst().getPlaca());
                tabla.addCell(String.valueOf(ticket.getTotal()));
                tabla.addCell(ticket.getHoraSistema().toString());
                tabla.addCell(ticket.getHoraSistema().plusMinutes(ticket.getTipoTicket()).toString());
                cant++;
            }

            //Pie de la tabla, informa el total de los elementos
            PdfPCell footerCell = new PdfPCell(new Phrase("Total de espacios: " + cant, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
            footerCell.setBackgroundColor(new BaseColor(184,102,20));
            footerCell.setBorderColor(new BaseColor(184,102,20));
            footerCell.setColspan(5);  // Combina las 5 columnas
            footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
            footerCell.setPaddingTop(5);
            footerCell.setPaddingRight(10);
            footerCell.setPaddingBottom(7);
            tabla.addCell(footerCell);

            document.add(tabla);

            document.close();

        } catch (FileNotFoundException ex) {

        } catch (DocumentException ex) {

        }
       
   }

    /**
     * Obtiene el ingreso por multas en un día específico.
     *
     * @param dia El día para el cual se desea obtener el ingreso por multas.
     * @return El ingreso total por multas en el día especificado.
     */  
    public long obtenerIngresoMultaDiario(LocalDate dia){
          verificarReservados();
          long ingresos =0;
          if(this.multas!=null)
            for(Multa multa : this.multas){
                System.out.println(10000);
                if(multa.getFechaMulta().toLocalDate().isEqual(dia)){
                    ingresos+= multa.getCosto();
                }
            }
          
          return ingresos;
      }
      
    /**
     * Genera un archivo PDF con los ingresos por multas en un período específico.
     *
     * @param rutaArchivo La ruta donde se guardará el archivo PDF.
     * @param inicio La fecha de inicio del período.
     * @param finalF La fecha de finalización del período.
     */
      public void generaIngresosMultasPDF(String rutaArchivo, LocalDate inicio, LocalDate finalF){
       verificarReservados();
        try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

                document.open();
                document.addAuthor("Parquimetro");

                //Titulo del documento
                Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
                tituloPrincipal.setAlignment(1);
                document.add(tituloPrincipal);
                Paragraph titulo = new Paragraph("Ingresos de multas");
                titulo.setAlignment(1);
                document.add(titulo);

                //FECHAS  DEL REPORTE
                document.add(Chunk.NEWLINE);//SALTO DE LINEA
                Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
                fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                document.add(fecha);

                Paragraph periodo = new Paragraph("Periodo: " + inicio.toString() +" - " + finalF.toString());
                periodo.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                document.add(periodo);
                 // Agregar el párrafo al documento
                 //FIN FECHAS Y PERIODOS DEL REPORTE 

                document.add(Chunk.NEWLINE);//SALTO DE LINEA

                //Genera la tabla por argumentos envia el numero de celdas por fila
                PdfPTable tabla = new PdfPTable(2);
                tabla.setWidthPercentage(100);

                //ENCABEZADO DE LA TABLA
                Phrase frase = new Phrase("Dia", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                PdfPCell espacio = new PdfPCell(frase);
                espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
                espacio.setPaddingTop(10);
                espacio.setPaddingBottom(10);
                espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

                tabla.addCell(espacio);

                frase = new Phrase("Ingresos", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                PdfPCell ingresos = new PdfPCell(frase);
                ingresos.setHorizontalAlignment(Element.ALIGN_CENTER);
                ingresos.setPaddingTop(10);
                ingresos.setPaddingBottom(10);
                ingresos.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

                tabla.addCell(ingresos);

                //FIN DEL ENCABEZADO DE LA TABLA
                long ingresoTotal =0;
                //carga los elementos a la tabla
                Parqueo parqueo = new Parqueo();
                parqueo.lecturaArchivo();
               LocalDate dia=inicio;
               while(!dia.isAfter(finalF)){
                   System.out.println(dia.toString());
                    long ingresoDiario=parqueo.obtenerIngresoMultaDiario(dia);
                    tabla.addCell(dia.toString());
                    tabla.addCell(String.valueOf(ingresoDiario));
                    ingresoTotal+=ingresoDiario;
                    dia = dia.plusDays(1);
               }

                //Pie de la tabla, informa el total de los elementos
                PdfPCell footerCell = new PdfPCell(new Phrase("Total de ingresos: " + ingresoTotal, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
                footerCell.setBackgroundColor(new BaseColor(184,102,20));
                footerCell.setBorderColor(new BaseColor(184,102,20));
                footerCell.setColspan(2);  // Combina las 3 columnas
                footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
                footerCell.setPaddingTop(2);
                footerCell.setPaddingRight(10);
                footerCell.setPaddingBottom(7);
                tabla.addCell(footerCell);

                document.add(tabla);

                document.close();

        } catch (FileNotFoundException ex) {

        } catch (DocumentException ex) {

        }
       
   }
      
    /**
     * Calcula el monto total de ingresos por estacionamiento en un día específico.
     *
     * @param dia El día para el cual se desea calcular el monto de ingresos por estacionamiento.
     * @return El monto total de ingresos por estacionamiento en el día especificado.
     */
      private long calcularMontoEstacionamiento(LocalDate dia){
      verificarReservados();
       long ingresos=0;
          
       List<Persona> personas = new ArrayList<Persona>();
       personas = Login.cargarUsuarios("listaUsuarios.txt");
          
       for(Persona ob : personas){
         
            System.out.println(ob.getApellidos());
             if(ob instanceof Usuario usuarioCast){
                 
                 for(Vehiculo v : usuarioCast.getVehiculos()){
                 
                     for(TicketParqueo t : v.getTickets()){
                     
                         if(t.getHoraSistema().toLocalDate().isEqual(dia)){
                             ingresos+= t.getTotal();
                          }
                     }
                 }
             }
             
         }
          return ingresos;
      }
      
     /**
     * Genera un archivo PDF con los ingresos por estacionamiento en un período específico.
     *
     * @param rutaArchivo La ruta donde se guardará el archivo PDF.
     * @param inicio La fecha de inicio del período.
     * @param finalF La fecha de finalización del período.
     */
   public void generaIngresosEstacionamientoPDF(String rutaArchivo, LocalDate inicio, LocalDate finalF){
       verificarReservados();
        try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

                document.open();
                document.addAuthor("Parquimetro");

                //Titulo del documento
                Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
                tituloPrincipal.setAlignment(1);
                document.add(tituloPrincipal);
                Paragraph titulo = new Paragraph("Ingresos de estacionamientos");
                titulo.setAlignment(1);
                document.add(titulo);

                //FECHAS  DEL REPORTE
                document.add(Chunk.NEWLINE);//SALTO DE LINEA
                Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
                fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                document.add(fecha);

                Paragraph periodo = new Paragraph("Periodo: " + inicio.toString() +" - " + finalF.toString());
                periodo.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                document.add(periodo);
                 // Agregar el párrafo al documento
                 //FIN FECHAS Y PERIODOS DEL REPORTE 

                document.add(Chunk.NEWLINE);//SALTO DE LINEA

                //Genera la tabla por argumentos envia el numero de celdas por fila
                PdfPTable tabla = new PdfPTable(2);
                tabla.setWidthPercentage(100);

                //ENCABEZADO DE LA TABLA
                Phrase frase = new Phrase("Dia", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                PdfPCell espacio = new PdfPCell(frase);
                espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
                espacio.setPaddingTop(10);
                espacio.setPaddingBottom(10);
                espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

                tabla.addCell(espacio);

                frase = new Phrase("Ingresos", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                PdfPCell ingresos = new PdfPCell(frase);
                ingresos.setHorizontalAlignment(Element.ALIGN_CENTER);
                ingresos.setPaddingTop(10);
                ingresos.setPaddingBottom(10);
                ingresos.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

                tabla.addCell(ingresos);

                //FIN DEL ENCABEZADO DE LA TABLA
                long ingresoTotal =0;
                //carga los elementos a la tabla
               LocalDate dia=inicio;
               while(!dia.isAfter(finalF)){
                    long ingresoDiario=calcularMontoEstacionamiento(dia);
                    tabla.addCell(dia.toString());
                    tabla.addCell(String.valueOf(ingresoDiario));
                    ingresoTotal+=ingresoDiario;
                    dia = dia.plusDays(1);
               }

                //Pie de la tabla, informa el total de los elementos
                PdfPCell footerCell = new PdfPCell(new Phrase("Total de ingresos: " + ingresoTotal, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
                footerCell.setBackgroundColor(new BaseColor(184,102,20));
                footerCell.setBorderColor(new BaseColor(184,102,20));
                footerCell.setColspan(2);  // Combina las 3 columnas
                footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
                footerCell.setPaddingTop(2);
                footerCell.setPaddingRight(10);
                footerCell.setPaddingBottom(7);
                tabla.addCell(footerCell);

                document.add(tabla);

                document.close();

        } catch (FileNotFoundException ex) {

        } catch (DocumentException ex) {

        }
       
   }
   
    /**
     * Obtiene el tiempo de uso y el ingreso de un espacio en un día específico.
     *
     * @param espacio El número del espacio.
     * @param dia El día para el cual se desea obtener el tiempo de uso y el ingreso.
     * @return Un arreglo de enteros donde el primer elemento es el ingreso y el segundo el tiempo de uso.
     */
   private int[] obtenerTiempoUso(int espacio, LocalDate dia){
       verificarReservados();
       int[] calculos = new int[2];
       
       int ingresos=0;
       int tiempo=0;
       
       Espacio espacioEncontrado = buscarEspacio(espacio);
       
       for(TicketParqueo t : espacioEncontrado.getTickets()){
       
           if(t.getHoraSistema().toLocalDate().isEqual(dia)){
                             ingresos+= t.getTotal();
                             tiempo+= t.getTiempoParqueo();
            }
           
       }
       
       calculos[0] = ingresos;
       calculos[1] = tiempo;
       
       return calculos;
   }
   

     /**
     * Genera un archivo PDF con los espacios utilizados en un rango de fechas.
     *
     * @param rutaArchivo La ruta donde se guardará el archivo PDF.
     * @param inicio La fecha de inicio del periodo.
     * @param finalF La fecha de fin del periodo.
     */
      public void generarEspaciosUsadosPDF(String rutaArchivo, LocalDate inicio, LocalDate finalF){
       verificarReservados();
        try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

                document.open();
                document.addAuthor("Parquimetro");

                //Titulo del documento
                Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
                tituloPrincipal.setAlignment(1);
                document.add(tituloPrincipal);
                Paragraph titulo = new Paragraph("Espacios utilizados");
                titulo.setAlignment(1);
                document.add(titulo);

                //FECHAS  DEL REPORTE
                document.add(Chunk.NEWLINE);//SALTO DE LINEA
                Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
                fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                document.add(fecha);

                Paragraph periodo = new Paragraph("Periodo: " + inicio.toString() +" - " + finalF.toString());
                periodo.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                document.add(periodo);
                 // Agregar el párrafo al documento
                 //FIN FECHAS Y PERIODOS DEL REPORTE 

                document.add(Chunk.NEWLINE);//SALTO DE LINEA

                //Genera la tabla por argumentos envia el numero de celdas por fila
                PdfPTable tabla = new PdfPTable(4);
                tabla.setWidthPercentage(100);

                //ENCABEZADO DE LA TABLA
                Phrase frase = new Phrase("Dia", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                PdfPCell dia = new PdfPCell(frase);
                dia.setHorizontalAlignment(Element.ALIGN_CENTER);
                dia.setPaddingTop(10);
                dia.setPaddingBottom(10);
                dia.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

                tabla.addCell(dia);

                frase = new Phrase("Espacio", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                PdfPCell espacio = new PdfPCell(frase);
                espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
                espacio.setPaddingTop(10);
                espacio.setPaddingBottom(10);
                espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

                tabla.addCell(espacio);

                 frase = new Phrase("Tiempo", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                PdfPCell tiempo = new PdfPCell(frase);
                tiempo.setHorizontalAlignment(Element.ALIGN_CENTER);
                tiempo.setPaddingTop(10);
                tiempo.setPaddingBottom(10);
                tiempo.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

                tabla.addCell(tiempo);
                 
                frase = new Phrase("Ingreso", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                PdfPCell ingresos = new PdfPCell(frase);
                ingresos.setHorizontalAlignment(Element.ALIGN_CENTER);
                ingresos.setPaddingTop(10);
                ingresos.setPaddingBottom(10);
                ingresos.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro

                tabla.addCell(ingresos);
                //FIN DEL ENCABEZADO DE LA TABLA
                long ingresoTotal =0;
                //carga los elementos a la tabla
               LocalDate diaE=inicio;
               while(!diaE.isAfter(finalF)){
                    
                   for(Espacio e : espacios){
                   
                    int[] calculos = obtenerTiempoUso(e.getNumero(), diaE);
                    
                   if(calculos[1] != 0){
                    tabla.addCell(diaE.toString());
                    tabla.addCell(String.valueOf(e.getNumero()));
                    tabla.addCell(String.valueOf(calculos[1]));
                    tabla.addCell(String.valueOf(calculos[0]));
                    ingresoTotal+=calculos[0];
                   }
                   
                   }
                   diaE = diaE.plusDays(1);
               }

                //Pie de la tabla, informa el total de los elementos
                PdfPCell footerCell = new PdfPCell(new Phrase("Total de ingresos: " + ingresoTotal, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
                footerCell.setBackgroundColor(new BaseColor(184,102,20));
                footerCell.setBorderColor(new BaseColor(184,102,20));
                footerCell.setColspan(4);  // Combina las 3 columnas
                footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
                footerCell.setPaddingTop(2);
                footerCell.setPaddingRight(10);
                footerCell.setPaddingBottom(7);
                tabla.addCell(footerCell);

                document.add(tabla);

                document.close();

        } catch (FileNotFoundException ex) {

        } catch (DocumentException ex) {

        }
       
   }
      
     /**
     * Genera un archivo PDF con las multas hechas en un rango de fechas.
     *
     * @param rutaArchivo La ruta donde se guardará el archivo PDF.
     * @param inicio La fecha de inicio del periodo.
     * @param finalF La fecha de fin del periodo.
     */
    public void generarMultasHechasPDF(String rutaArchivo, LocalDate inicio, LocalDate finalF){
       verificarReservados();
       List<Multa> lista = this.multas;
            
            if(lista!=null){
                
                try {
                     Document document = new Document();
                     PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

                     document.open();
                     document.addAuthor("Parquimetro");

                     //Titulo del documento
                     Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
                     tituloPrincipal.setAlignment(1);
                      document.add(tituloPrincipal);
                     Paragraph titulo = new Paragraph("Multas elaboradas");
                     titulo.setAlignment(5);
                     document.add(titulo);

                     //FECHAS Y PERIODOS DEL REPORTE 
                     document.add(Chunk.NEWLINE);//SALTO DE LINEA
                     Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
                     fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                     document.add(fecha);

                     Paragraph periodo = new Paragraph("Periodo: " + inicio.toString() +" - " + finalF.toString());
                     periodo.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                     document.add(periodo);
                     // Agregar el párrafo al documento
                      //FIN FECHAS Y PERIODOS DEL REPORTE 

                     document.add(Chunk.NEWLINE);//SALTO DE LINEA

                     //Genera la tabla por argumentos envia el numero de celdas por fila
                     PdfPTable tabla = new PdfPTable(4);
                     tabla.setWidthPercentage(100);

                     //ENCABEZADO DE LA TABLA
                     Phrase frase = new Phrase("Placa", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell espacio = new PdfPCell(frase);
                     espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
                     espacio.setPaddingTop(10);
                     espacio.setPaddingBottom(10);
                     espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                      tabla.addCell(espacio);
                      
                     Phrase fraseTiempo= new Phrase("Fecha", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell tiempo = new PdfPCell(fraseTiempo);
                     tiempo.setHorizontalAlignment(Element.ALIGN_CENTER);
                     tiempo.setPaddingTop(10);
                     tiempo.setPaddingBottom(10);
                     tiempo.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(tiempo);
                     
                     Phrase fraseTotal= new Phrase("Razon", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell total = new PdfPCell(fraseTotal);
                     total.setHorizontalAlignment(Element.ALIGN_CENTER);
                     total.setPaddingTop(10);
                     total.setPaddingBottom(10);
                     total.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(total);
                     
                     Phrase fraseFecha= new Phrase("Costo", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell fechaC = new PdfPCell(fraseFecha);
                     fechaC.setHorizontalAlignment(Element.ALIGN_CENTER);
                     fechaC.setPaddingTop(10);
                     fechaC.setPaddingBottom(10);
                     fechaC.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(fechaC);

                     //FIN DEL ENCABEZADO DE LA TABLA

                     //Controla la cantidad de elementos considerados en el reporte
                     int cant=0;
                    
                     //carga los elementos a la tabla
                     for(Multa multa : this.multas){
                         System.out.println("ssasasa");
                       LocalDate fechaMulta= multa.getFechaMulta().toLocalDate();
                         System.out.println(fechaMulta.toString());
                       if((fechaMulta.isAfter(inicio) || fechaMulta.isEqual(inicio)) && (fechaMulta.isBefore(finalF) || fechaMulta.isEqual(finalF)) ){
                                  tabla.addCell(String.valueOf(multa.getPlaca()));
                                   tabla.addCell(String.valueOf(multa.getFechaMulta().toString()));
                                   tabla.addCell(multa.getRazon());
                                   tabla.addCell(String.valueOf(multa.getCosto()));
                                   cant++;
                            }
                     }
      
                     
                   //Pie de la tabla, informa el total de los elementos
                   PdfPCell footerCell = new PdfPCell(new Phrase("Total de multas: " + cant, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
                   footerCell.setBackgroundColor(new BaseColor(184,102,20)); 
                   footerCell.setBorderColor(new BaseColor(184,102,20));
                   footerCell.setColspan(4);  // Combina las 5 columnas
                   footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
                   footerCell.setPaddingTop(5);
                   footerCell.setPaddingRight(10);
                   footerCell.setPaddingBottom(7);
                    tabla.addCell(footerCell);                       

                    document.add(tabla);

                     System.out.println("Listo");

                     document.close();

                 } catch (FileNotFoundException ex) {

                 } catch (DocumentException ex) {

                 }
                
            }
   }
       
    /**
     * Genera un archivo PDF con las multas hechas por un inspector en un rango de fechas.
     *
     * @param rutaArchivo La ruta donde se guardará el archivo PDF.
     * @param inicio La fecha de inicio del periodo.
     * @param finalF La fecha de fin del periodo.
     * @param identificacion La identificación del inspector.
     */
    public void generarMultasHechasInspectorPDF(String rutaArchivo, LocalDate inicio, LocalDate finalF, String identificacion){
       
       List<Multa> lista = this.multas;
            
            if(lista!=null){
                
                try {
                     Document document = new Document();
                     PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

                     document.open();
                     document.addAuthor("Parquimetro");

                     //Titulo del documento
                     Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
                     tituloPrincipal.setAlignment(1);
                      document.add(tituloPrincipal);
                     Paragraph titulo = new Paragraph("Multas elaboradas");
                     titulo.setAlignment(5);
                     document.add(titulo);

                     //FECHAS Y PERIODOS DEL REPORTE 
                     document.add(Chunk.NEWLINE);//SALTO DE LINEA
                     Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
                     fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                     document.add(fecha);

                     Paragraph periodo = new Paragraph("Periodo: " + inicio.toString() +" - " + finalF.toString());
                     periodo.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                     document.add(periodo);
                     // Agregar el párrafo al documento
                      //FIN FECHAS Y PERIODOS DEL REPORTE 

                     document.add(Chunk.NEWLINE);//SALTO DE LINEA

                     //Genera la tabla por argumentos envia el numero de celdas por fila
                     PdfPTable tabla = new PdfPTable(4);
                     tabla.setWidthPercentage(100);

                     //ENCABEZADO DE LA TABLA
                     Phrase frase = new Phrase("Placa", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell espacio = new PdfPCell(frase);
                     espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
                     espacio.setPaddingTop(10);
                     espacio.setPaddingBottom(10);
                     espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                      tabla.addCell(espacio);
                      
                     Phrase fraseTiempo= new Phrase("Fecha", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell tiempo = new PdfPCell(fraseTiempo);
                     tiempo.setHorizontalAlignment(Element.ALIGN_CENTER);
                     tiempo.setPaddingTop(10);
                     tiempo.setPaddingBottom(10);
                     tiempo.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(tiempo);
                     
                     Phrase fraseTotal= new Phrase("Razon", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell total = new PdfPCell(fraseTotal);
                     total.setHorizontalAlignment(Element.ALIGN_CENTER);
                     total.setPaddingTop(10);
                     total.setPaddingBottom(10);
                     total.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(total);
                     
                     Phrase fraseFecha= new Phrase("Costo", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell fechaC = new PdfPCell(fraseFecha);
                     fechaC.setHorizontalAlignment(Element.ALIGN_CENTER);
                     fechaC.setPaddingTop(10);
                     fechaC.setPaddingBottom(10);
                     fechaC.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(fechaC);

                     //FIN DEL ENCABEZADO DE LA TABLA

                     //Controla la cantidad de elementos considerados en el reporte
                     int cant=0;
                    
                     //carga los elementos a la tabla
                     for(Multa multa : this.multas){
                        if(multa.getInspector().getIdentificacion().equals(identificacion)){
                            
                                LocalDate fechaMulta= multa.getFechaMulta().toLocalDate();
                                if((fechaMulta.isAfter(inicio) || fechaMulta.isEqual(inicio)) && (fechaMulta.isBefore(finalF) || fechaMulta.isEqual(finalF)) ){
                                           tabla.addCell(String.valueOf(multa.getPlaca()));
                                            tabla.addCell(String.valueOf(multa.getFechaMulta().toString()));
                                            tabla.addCell(multa.getRazon());
                                            tabla.addCell(String.valueOf(multa.getCosto()));
                                            cant++;
                                 }
                        }}
      
                     
                   //Pie de la tabla, informa el total de los elementos
                   PdfPCell footerCell = new PdfPCell(new Phrase("Total de multas: " + cant, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
                   footerCell.setBackgroundColor(new BaseColor(184,102,20)); 
                   footerCell.setBorderColor(new BaseColor(184,102,20));
                   footerCell.setColspan(4);  // Combina las 5 columnas
                   footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
                   footerCell.setPaddingTop(5);
                   footerCell.setPaddingRight(10);
                   footerCell.setPaddingBottom(7);
                    tabla.addCell(footerCell);                       

                    document.add(tabla);


                     document.close();

                 } catch (FileNotFoundException ex) {

                 } catch (DocumentException ex) {

                 }
                
            }
   }

    /**
     * Genera una tabla PDF con estadísticas detalladas de un espacio en un día específico.
     *
     * @param espacio El número del espacio.
     * @param dia El día específico.
     * @param tabla La tabla PDF a la que se agregarán las estadísticas.
     * @return La tabla PDF con las estadísticas agregadas.
     */
     public PdfPTable generarEstadisticaDetallada(int espacio, LocalDate dia, PdfPTable tabla){
        verificarReservados();
        double minOcupadas=0;
        double horasVacio=0;
        Duration horasParqueoAbierto = Duration.between(horaInicio, horaFinal);
        double horasAbiertos = (double)horasParqueoAbierto.toHours();
        
        Espacio espacioEncontrado = buscarEspacio(espacio);
       
       for(TicketParqueo t : espacioEncontrado.getTickets()){
       
           if(t.getHoraSistema().toLocalDate().isEqual(dia)){
                             minOcupadas+= t.getTiempoParqueo();
            }
           
       }
        //de minutos a horas
         double horasOcupadas = minOcupadas/60;
         horasVacio= horasAbiertos-horasOcupadas;
         double porcentajeOcupacion = (horasOcupadas / horasAbiertos) * 100;
         double porcentajeVacio = 100 - porcentajeOcupacion; 
         DecimalFormat df = new DecimalFormat("#.##");
         String porcentajeOcupacionStr = df.format(porcentajeOcupacion);
         String porcentajeVacioStr = df.format(porcentajeVacio);
        
       tabla.addCell(String.valueOf(espacio));
       tabla.addCell(dia.toString());
       tabla.addCell(String.valueOf(horasOcupadas));
       tabla.addCell(String.valueOf(porcentajeOcupacionStr) + "%");
       tabla.addCell(String.valueOf(horasVacio));
       tabla.addCell(String.valueOf(porcentajeVacioStr) + "%");
       
       
       return tabla;
    }
    
    public void generarEspaciosDetalladosPDF(String rutaArchivo, LocalDate inicio, LocalDate finalF, int inicioEsp, int finEsp){
       verificarReservados();
       List<Multa> lista = this.multas;
            
            if(lista!=null){
                
                try {
                     Document document = new Document();
                     PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

                     document.open();
                     document.addAuthor("Parquimetro");

                     //Titulo del documento
                     Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
                     tituloPrincipal.setAlignment(1);
                      document.add(tituloPrincipal);
                     Paragraph titulo = new Paragraph("Uso de los espacios detallado");
                     titulo.setAlignment(5);
                     document.add(titulo);

                     //FECHAS Y PERIODOS DEL REPORTE 
                     document.add(Chunk.NEWLINE);//SALTO DE LINEA
                     Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
                     fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                     document.add(fecha);

                     Paragraph periodo = new Paragraph("Periodo: " + inicio.toString() +" - " + finalF.toString());
                     periodo.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                     document.add(periodo);
                     // Agregar el párrafo al documento
                      //FIN FECHAS Y PERIODOS DEL REPORTE 

                     document.add(Chunk.NEWLINE);//SALTO DE LINEA

                     //Genera la tabla por argumentos envia el numero de celdas por fila
                     PdfPTable tabla = new PdfPTable(6);
                     tabla.setWidthPercentage(100);

                     //ENCABEZADO DE LA TABLA
                     Phrase frase = new Phrase("Espacio", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell espacio = new PdfPCell(frase);
                     espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
                     espacio.setPaddingTop(10);
                     espacio.setPaddingBottom(10);
                     espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                      tabla.addCell(espacio);
                      
                     Phrase frasedias= new Phrase("Dia", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell dia = new PdfPCell(frasedias);
                     dia.setHorizontalAlignment(Element.ALIGN_CENTER);
                     dia.setPaddingTop(10);
                     dia.setPaddingBottom(10);
                     dia.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(dia);
                     
                     Phrase fraseHOcupadas= new Phrase("Horas Ocupadas", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell hOcupadas = new PdfPCell(fraseHOcupadas);
                     hOcupadas.setHorizontalAlignment(Element.ALIGN_CENTER);
                     hOcupadas.setPaddingTop(10);
                     hOcupadas.setPaddingBottom(10);
                     hOcupadas.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(hOcupadas);
                     
                     Phrase frasePOcupadas= new Phrase("Porcentaje ocupado", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell pOcupadas = new PdfPCell(frasePOcupadas);
                     pOcupadas.setHorizontalAlignment(Element.ALIGN_CENTER);
                     pOcupadas.setPaddingTop(10);
                     pOcupadas.setPaddingBottom(10);
                     pOcupadas.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(pOcupadas);
                      
                     Phrase frasehVacias= new Phrase("Horas Vacias", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell hVacias = new PdfPCell(frasehVacias);
                     hVacias.setHorizontalAlignment(Element.ALIGN_CENTER);
                     hVacias.setPaddingTop(10);
                     hVacias.setPaddingBottom(10);
                     hVacias.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(hVacias);
                     
                     Phrase frasepVacias= new Phrase("Porcentaje Vacio", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell pVacias = new PdfPCell(frasepVacias);
                     pVacias.setHorizontalAlignment(Element.ALIGN_CENTER);
                     pVacias.setPaddingTop(10);
                     pVacias.setPaddingBottom(10);
                     pVacias.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(pVacias);

                     //FIN DEL ENCABEZADO DE LA TABLA

                     //Controla la cantidad de elementos considerados en el reporte
                     int cant=0;
                    
                     LocalDate diaE = inicio;
                     //carga los elementos a la tabla
                     while(!diaE.isAfter(finalF)){

                         for(int i=inicioEsp; i<=finEsp; i++){
                         
                              tabla = generarEstadisticaDetallada(i,diaE,tabla);
                         }
                            
                            diaE = diaE.plusDays(1);
                    }
                     
                   //Pie de la tabla, informa el total de los elementos
                   PdfPCell footerCell = new PdfPCell(new Phrase("Total de espacios: " + espacios.size(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
                   footerCell.setBackgroundColor(new BaseColor(184,102,20)); 
                   footerCell.setBorderColor(new BaseColor(184,102,20));
                   footerCell.setColspan(4);  // Combina las 5 columnas
                   footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
                   footerCell.setPaddingTop(5);
                   footerCell.setPaddingRight(10);
                   footerCell.setPaddingBottom(7);
                    tabla.addCell(footerCell);                       

                    document.add(tabla);

                     System.out.println("Listo");

                     document.close();

                 } catch (FileNotFoundException ex) {

                 } catch (DocumentException ex) {

                 }
                
            }
   }
    
   /**
     * Genera una tabla PDF con estadísticas resumidas de ocupación y vacantes de un espacio de parqueo en un día específico.
     *
     * @param espacio El número del espacio de parqueo.
     * @param dia El día para el cual se generarán las estadísticas.
     * @param tabla La tabla PDF a la cual se agregarán las estadísticas.
     * @return La tabla PDF con las estadísticas agregadas.
     */
   public PdfPTable generarEstadisticaResumida(int espacio, LocalDate dia, PdfPTable tabla){
                verificarReservados();
                double minOcupadas=0;
                double horasVacio=0;
                Duration horasParqueoAbierto = Duration.between(horaInicio, horaFinal);
                double horasAbiertos = (double) horasParqueoAbierto.toHours();

                Espacio espacioEncontrado = buscarEspacio(espacio);

                if(espacioEncontrado!=null){
                    
                   for(TicketParqueo t : espacioEncontrado.getTickets()){
                       System.out.println(t.getHoraSistema().toLocalDate());
                        if(t.getHoraSistema().toLocalDate().isEqual(dia)){
                                  minOcupadas+= t.getTiempoParqueo();
                         }

                    }
                    //de minutos a horas
                    double horasOcupadas = minOcupadas/60;
                    horasVacio= horasAbiertos-horasOcupadas;
                    double porcentajeOcupacion = (horasOcupadas / horasAbiertos) * 100;
                    double porcentajeVacio = 100 - porcentajeOcupacion; 
                    DecimalFormat df = new DecimalFormat("#.##");
                    String porcentajeOcupacionStr = df.format(porcentajeOcupacion);
                    String porcentajeVacioStr = df.format(porcentajeVacio);
                    
                    tabla.addCell(String.valueOf(espacio));
                    tabla.addCell(dia.toString());
                    tabla.addCell(String.valueOf(porcentajeOcupacionStr) + "%");
                    tabla.addCell(String.valueOf(porcentajeVacioStr) + "%");
                    
                }



               return tabla;
    }
    
        /**
         * Genera un archivo PDF con un resumen del uso de los espacios de parqueo en un rango de fechas.
         *
         * @param rutaArchivo La ruta donde se guardará el archivo PDF.
         * @param inicio La fecha de inicio del periodo a considerar.
         * @param finalF La fecha de fin del periodo a considerar.
         * @param inicioEsp El número del primer espacio de parqueo a considerar.
         * @param finEsp El número del último espacio de parqueo a considerar.
         */
        public void generarEspaciosResumidoPDF(String rutaArchivo, LocalDate inicio, LocalDate finalF, int inicioEsp, int finEsp){
       
       List<Multa> lista = this.multas;
            
            if(lista!=null){
                
                try {
                     Document document = new Document();
                     PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

                     document.open();
                     document.addAuthor("Parquimetro");

                     //Titulo del documento
                     Paragraph tituloPrincipal = new Paragraph("Reporte Parquimetro", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
                     tituloPrincipal.setAlignment(1);
                      document.add(tituloPrincipal);
                     Paragraph titulo = new Paragraph("Uso de los espacios resumido");
                     titulo.setAlignment(5);
                     document.add(titulo);

                     //FECHAS Y PERIODOS DEL REPORTE 
                     document.add(Chunk.NEWLINE);//SALTO DE LINEA
                     Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
                     fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                     document.add(fecha);

                     Paragraph periodo = new Paragraph("Periodo: " + inicio.toString() +" - " + finalF.toString());
                     periodo.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
                     document.add(periodo);
                     // Agregar el párrafo al documento
                      //FIN FECHAS Y PERIODOS DEL REPORTE 

                     document.add(Chunk.NEWLINE);//SALTO DE LINEA

                     //Genera la tabla por argumentos envia el numero de celdas por fila
                     PdfPTable tabla = new PdfPTable(4);
                     tabla.setWidthPercentage(100);

                     //ENCABEZADO DE LA TABLA
                     Phrase frase = new Phrase("Espacio", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell espacio = new PdfPCell(frase);
                     espacio.setHorizontalAlignment(Element.ALIGN_CENTER);
                     espacio.setPaddingTop(10);
                     espacio.setPaddingBottom(10);
                     espacio.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                      tabla.addCell(espacio);
                      
                     Phrase frasedias= new Phrase("Dia", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell dia = new PdfPCell(frasedias);
                     dia.setHorizontalAlignment(Element.ALIGN_CENTER);
                     dia.setPaddingTop(10);
                     dia.setPaddingBottom(10);
                     dia.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(dia);
                     
                     
                     Phrase frasePOcupadas= new Phrase("Porcentaje ocupado", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell pOcupadas = new PdfPCell(frasePOcupadas);
                     pOcupadas.setHorizontalAlignment(Element.ALIGN_CENTER);
                     pOcupadas.setPaddingTop(10);
                     pOcupadas.setPaddingBottom(10);
                     pOcupadas.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(pOcupadas);
                      
                     
                     Phrase frasepVacias= new Phrase("Porcentaje Vacio", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))); // Texto en blanco
                     PdfPCell pVacias = new PdfPCell(frasepVacias);
                     pVacias.setHorizontalAlignment(Element.ALIGN_CENTER);
                     pVacias.setPaddingTop(10);
                     pVacias.setPaddingBottom(10);
                     pVacias.setBackgroundColor(new BaseColor(14, 41, 75));  // Fondo azul oscuro
                     tabla.addCell(pVacias);

                     //FIN DEL ENCABEZADO DE LA TABLA

                     //Controla la cantidad de elementos considerados en el reporte
                     int cant=0;
                    
                     LocalDate diaE = inicio;
                     //carga los elementos a la tabla
                     while(!diaE.isAfter(finalF)){

                           while(!diaE.isAfter(finalF)){

                                for(int i=inicioEsp; i<=finEsp; i++){

                                     tabla = generarEstadisticaResumida(i,diaE,tabla);
                                }
                                diaE = diaE.plusDays(1);
                            }
                    }
                     
                   //Pie de la tabla, informa el total de los elementos
                   PdfPCell footerCell = new PdfPCell(new Phrase("Total de espacios: " + espacios.size(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255, 255, 255))));
                   footerCell.setBackgroundColor(new BaseColor(184,102,20)); 
                   footerCell.setBorderColor(new BaseColor(184,102,20));
                   footerCell.setColspan(4);  // Combina las 5 columnas
                   footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Alinear el texto a la derecha
                   footerCell.setPaddingTop(5);
                   footerCell.setPaddingRight(10);
                   footerCell.setPaddingBottom(7);
                    tabla.addCell(footerCell);                       

                    document.add(tabla);

                     System.out.println("Listo");

                     document.close();

                 } catch (FileNotFoundException ex) {

                 } catch (DocumentException ex) {

                 }
                
            }
   }
}
