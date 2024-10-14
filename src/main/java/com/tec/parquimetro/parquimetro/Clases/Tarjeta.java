/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;
import java.time.LocalDate;
/**
 *
 * @author Dilan
 */
public class Tarjeta {
    private long numeroTarjeta;
    private int mesVencimiento;
    private int anoVencimiento;
    private int codigoValidacion;
    
    //constructor
    public Tarjeta (long numeroTarjeta, int mesVencimiento, int anoVencimiento, int codigoValidacion){
        this.numeroTarjeta = numeroTarjeta;
        this.mesVencimiento = mesVencimiento;
        this.anoVencimiento = anoVencimiento;
        this.codigoValidacion = codigoValidacion;
    }
    
    //getters
    public long getNumeroTarjeta(){
        return numeroTarjeta;
    }
    public int getCodigoValidacion(){
        return codigoValidacion;
    }
    public int getAnoVencimiento(){
        return anoVencimiento;
    }
    
    //setters
    public void setTarjeta(long numeroTarjeta, int mesVencimiento, int anoVencimiento, int codigoValidacion){
        this.numeroTarjeta = numeroTarjeta;
        this.mesVencimiento = mesVencimiento;
        this.anoVencimiento = anoVencimiento;
        this.codigoValidacion = codigoValidacion;
    }
}
