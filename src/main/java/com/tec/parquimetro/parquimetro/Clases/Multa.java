/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    
    //constructor
    public Multa(int costo, String razon, LocalDateTime fechaMulta, Usuario usuario, String placa){
        this.costo = costo;
        this.razon = razon;
        this.fechaMulta = fechaMulta;
        this.usuario = usuario;
        this.placa = placa;
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
    
    
    
}
