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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * La clase Multa representa una multa emitida a un usuario.
 * Implementa {@link Serializable}.
 */
public class Multa  implements Serializable{
    //atributos
    private int costo;
    private String razon;
    private LocalDateTime fechaMulta;
    private Usuario usuario;
    private String placa;
    private Inspector inspector;
    
     /**
     * Constructor para crear una nueva Multa con los detalles especificados.
     *
     * @param costo El costo de la multa.
     * @param razon La razón de la multa.
     * @param fechaMulta La fecha en que se emitió la multa.
     * @param usuario El usuario al que se le emitió la multa.
     * @param placa La placa del vehículo multado.
     * @param inspector El inspector que emitió la multa.
     */
    public Multa(int costo, String razon, LocalDateTime fechaMulta, Usuario usuario, String placa, Inspector inspector){
        this.costo = costo;
        this.razon = razon;
        this.fechaMulta = fechaMulta;
        this.usuario = usuario;
        this.placa = placa;
        this.inspector = inspector;
    }

    /**
     * Obtiene el costo de la multa.
     *
     * @return El costo de la multa.
     */
    public int getCosto() {
        return costo;
    }

    /**
     * Establece el costo de la multa.
     *
     * @param costo El nuevo costo de la multa.
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }

    /**
     * Obtiene la razón de la multa.
     *
     * @return La razón de la multa.
     */
    public String getRazon() {
        return razon;
    }

    /**
     * Establece la razón de la multa.
     *
     * @param razon La nueva razón de la multa.
     */
    public void setRazon(String razon) {
        this.razon = razon;
    }

    /**
     * Obtiene la fecha en que se emitió la multa.
     *
     * @return La fecha en que se emitió la multa.
     */
    public LocalDateTime getFechaMulta() {
        return fechaMulta;
    }

    /**
     * Establece la fecha en que se emitió la multa.
     *
     * @param fechaMulta La nueva fecha en que se emitió la multa.
     */
    public void setFechaMulta(LocalDateTime fechaMulta) {
        this.fechaMulta = fechaMulta;
    }

    /**
     * Obtiene el usuario al que se le emitió la multa.
     *
     * @return El usuario al que se le emitió la multa.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario al que se le emitió la multa.
     *
     * @param usuario El nuevo usuario al que se le emitió la multa.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la placa del vehículo multado.
     *
     * @return La placa del vehículo multado.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Establece la placa del vehículo multado.
     *
     * @param placa La nueva placa del vehículo multado.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtiene el inspector que emitió la multa.
     *
     * @return El inspector que emitió la multa.
     */
    public Inspector getInspector() {
        return inspector;
    }

    /**
     * Establece el inspector que emitió la multa.
     *
     * @param inspector El nuevo inspector que emitió la multa.
     */
    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }
    
    /**
     * Genera un archivo PDF con los detalles de la multa.
     *
     * @param rutaArchivo La ruta donde se guardará el archivo PDF.
     */
    public void generarPDF(String rutaArchivo){
    
        try {
       Document document = new Document();
       PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo+".pdf"));

       document.open();
       document.addAuthor("Parquimetro Cartago");

       //Titulo del documento
       Paragraph tituloPrincipal = new Paragraph("Multa generada", FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Font.BOLD,new BaseColor(14, 41, 75)));
       tituloPrincipal.setAlignment(1);
       document.add(tituloPrincipal);

       //FECHAS  DEL REPORTE
       document.add(Chunk.NEWLINE);//SALTO DE LINEA
       Paragraph fecha = new Paragraph("Fecha de elaboracion: " + new Date().toString());
       fecha.setAlignment(Element.ALIGN_LEFT);  // Alinear a la derecha
       document.add(fecha);
       //FIN FECHAS  DEL REPORTE

       document.add(Chunk.NEWLINE);//SALTO DE LINEA

       //Genera la tabla por argumentos envia el numero de celdas por fila
       PdfPTable tabla = new PdfPTable(4);
       tabla.setWidthPercentage(50);

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
               tabla.addCell(String.valueOf(getPlaca()));
               tabla.addCell(String.valueOf(getFechaMulta().toString()));
                tabla.addCell(getRazon());
                tabla.addCell(String.valueOf(getCosto()));

       document.add(tabla);

       document.close();

        } catch (FileNotFoundException ex) {

        } catch (DocumentException ex) {

        }
    }

}
