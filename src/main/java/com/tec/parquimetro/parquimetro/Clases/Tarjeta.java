package com.tec.parquimetro.parquimetro.Clases;
import java.io.Serializable;
import java.time.LocalDate;

 /**
 * La clase Tarjeta representa una tarjeta de crédito o débito.
 * Implementa {@link Serializable}.
 * 
 */
public class Tarjeta implements Serializable{
    //atributos
    private static final long serialVersionUID = 5L;
    private long numeroTarjeta;
    private int mesVencimiento;
    private int anoVencimiento;
    private int codigoValidacion;
    
    /**
     * Constructor para crear una nueva Tarjeta con los detalles especificados.
     *
     * @param numeroTarjeta El número de la tarjeta.
     * @param mesVencimiento El mes de vencimiento de la tarjeta.
     * @param anoVencimiento El año de vencimiento de la tarjeta.
     * @param codigoValidacion El código de validación de la tarjeta.
     */
    public Tarjeta (long numeroTarjeta, int mesVencimiento, int anoVencimiento, int codigoValidacion){
        this.numeroTarjeta = numeroTarjeta;
        this.mesVencimiento = mesVencimiento;
        this.anoVencimiento = anoVencimiento;
        this.codigoValidacion = codigoValidacion;
    }
    
    //getters y setters

    /**
     * Obtiene el número de la tarjeta.
     *
     * @return El número de la tarjeta.
     */
    public long getNumeroTarjeta(){
        return numeroTarjeta;
    }

    /**
     * Obtiene el código de validación de la tarjeta.
     *
     * @return El código de validación de la tarjeta.
     */
    public int getCodigoValidacion(){
        return codigoValidacion;
    }

    /**
     * Obtiene el año de vencimiento de la tarjeta.
     *
     * @return El año de vencimiento de la tarjeta.
     */
    public int getAnoVencimiento(){
        return anoVencimiento;
    }

    /**
     * Obtiene el mes de vencimiento de la tarjeta.
     *
     * @return El mes de vencimiento de la tarjeta.
     */
    public int getMesVencimiento(){
        return mesVencimiento;
    }

    /**
     * Establece los detalles de la tarjeta.
     *
     * @param numeroTarjeta El nuevo número de la tarjeta.
     * @param mesVencimiento El nuevo mes de vencimiento de la tarjeta.
     * @param anoVencimiento El nuevo año de vencimiento de la tarjeta.
     * @param codigoValidacion El nuevo código de validación de la tarjeta.
     */
    public void setTarjeta(long numeroTarjeta, int mesVencimiento, int anoVencimiento, int codigoValidacion){
        this.numeroTarjeta = numeroTarjeta;
        this.mesVencimiento = mesVencimiento;
        this.anoVencimiento = anoVencimiento;
        this.codigoValidacion = codigoValidacion;
    }
}
