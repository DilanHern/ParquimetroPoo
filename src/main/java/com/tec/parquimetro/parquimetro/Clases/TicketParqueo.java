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
public class TicketParqueo implements Serializable{
    private int tiempoParqueo;
    private LocalDateTime horaSistema;
    private boolean estado;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private int total;
    private Espacio espacio;
    private short tipoTicket; //1->Ticket nuevo 2-> Ticket con tiempo extra
    
    //constructor
    
    public TicketParqueo(){}
    
    public TicketParqueo(int tiempoParqueo, LocalDateTime horaSistema, boolean estado, Vehiculo vehiculo, Usuario usuario, int total){
        this.tiempoParqueo = tiempoParqueo;
        this.horaSistema = horaSistema;
        this.estado = estado;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.total = total;
        this.tipoTicket = 1;
    }
    
    
    
    public void desaparcar(){
        //logica
    }
    
    public void extenderTiempo(int tiempoExtra, boolean estado){
        //logica
    }


    public int getTiempoParqueo() {
        return tiempoParqueo;
    }


    public void setTiempoParqueo(int tiempoParqueo) {
        this.tiempoParqueo = tiempoParqueo;
    }


    public LocalDateTime getHoraSistema() {
        return horaSistema;
    }


    public void setHoraSistema(LocalDateTime horaSistema) {
        this.horaSistema = horaSistema;
    }


    public boolean isEstado() {
        return estado;
    }

  
    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Espacio getEspacio() {
        return espacio;
    }


    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }


    public Vehiculo getVehiculo() {
        return vehiculo;
    }


    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    public int getTotal() {
        return total;
    }


    public void setTotal(int total) {
        this.total = total;
    }


    public short getTipoTicket() {
        return tipoTicket;
    }


    public void setTipoTicket(short tipoTicket) {
        this.tipoTicket = tipoTicket;
    }
    
    

    
}
