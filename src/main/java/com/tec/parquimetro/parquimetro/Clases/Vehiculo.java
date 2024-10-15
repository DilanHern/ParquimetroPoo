/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vehiculo implements Serializable {
    private String placa;
    private String marca;
    private String modelo;
    private Usuario usuario;
    private Espacio espacio;
    private TicketParqueo ticketVigente;
    private List<TicketParqueo> ticketsUtilizados;
    
    
    
    
    //constructores
    public Vehiculo(String placa, String marca, String modelo, Usuario usuario){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.usuario = usuario;
        ticketsUtilizados = new ArrayList<TicketParqueo>();
    }
    
    public Vehiculo(String placa, Usuario usuario){
        this.placa = placa;
        this.marca = "";
        this.modelo = "";
        this.usuario = usuario;
    }

 
    public String getPlaca() {
        return placa;
    }


    public void setPlaca(String placa) {
        this.placa = placa;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getModelo() {
        return modelo;
    }


    public void setModelo(String modelo) {
        this.modelo = modelo;
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
    
    public List<TicketParqueo>  getTickets(){
        return ticketsUtilizados;
    }
    
    public void agregarTicket(TicketParqueo  ticket){
    
        ticketsUtilizados.add(ticket);
    }
    
    public void establecerTicketVigente(TicketParqueo  ticket){
    
        ticketVigente = ticket;
    
    }
}
