/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author Dilan
 */
public class Multa  implements Serializable{
    private int costo;
    private String razon;
    private LocalDateTime fechaMulta;
    private Usuario usuario;
    private String placa;
    private Inspector inspector;
    
    //constructor
    public Multa(int costo, String razon, LocalDateTime fechaMulta, Usuario usuario, String placa, Inspector inspector){
        this.costo = costo;
        this.razon = razon;
        this.fechaMulta = fechaMulta;
        this.usuario = usuario;
        this.placa = placa;
        this.inspector = inspector;
    }

    
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
    
    /**
     * @return the costo
     */
    public int getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }


    
    /**
     * @return the razon
     */
    public String getRazon() {
        return razon;
    }

    /**
     * @param razon the razon to set
     */
    public void setRazon(String razon) {
        this.razon = razon;
    }

    /**
     * @return the fechaMulta
     */
    public LocalDateTime getFechaMulta() {
        return fechaMulta;
    }

    /**
     * @param fechaMulta the fechaMulta to set
     */
    public void setFechaMulta(LocalDateTime fechaMulta) {
        this.fechaMulta = fechaMulta;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the inspector
     */
    public Inspector getInspector() {
        return inspector;
    }

    /**
     * @param inspector the inspector to set
     */
    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }
    
    
    
}
