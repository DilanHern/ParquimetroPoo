/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;
import java.time.LocalDateTime;
/**
 *
 * @author Dilan
 */
public class TicketParqueo {
    private int tiempoParqueo;
    private LocalDateTime horaSistema;
    private boolean estado;
    
    //constructor
    public TicketParqueo(int tiempoParqueo, LocalDateTime horaSistema, boolean estado){
        this.tiempoParqueo = tiempoParqueo;
        this.horaSistema = horaSistema;
        this.estado = estado;
    }
    
    public void desaparcar(){
        //logica
    }
    
    public void extenderTiempo(int tiempoExtra, boolean estado){
        //logica
    }
}
