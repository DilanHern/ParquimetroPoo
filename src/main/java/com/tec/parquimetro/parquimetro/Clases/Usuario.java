
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.time.LocalDate;

public class Usuario extends Persona implements Serializable{
    
    //atributos
    private int tiempoAcumulado;
   // private Tarjeta tarjeta; 
    
    
    //Constructor
        public Usuario(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin, int tiempoAcumulado){
    
        super(nombre, apellidos,  telefono, direccionFisica, fechaIngreso, identificacion, pin);
        this.tiempoAcumulado = tiempoAcumulado;
    }
    
    
    //getters and setters
    void setTiempoAcumulado(int tiempo){
    
        this.tiempoAcumulado = tiempo;
    }
    
    int getTiempoAcumulado(){
    
        return this.tiempoAcumulado;
    
    }
    
}
