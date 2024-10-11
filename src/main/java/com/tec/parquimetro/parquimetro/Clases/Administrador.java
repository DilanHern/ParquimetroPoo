package com.tec.parquimetro.parquimetro.Clases;
import java.io.Serializable;
import java.time.LocalDate;


public class Administrador extends Persona implements Serializable{
    
    
    //Constructor
    public Administrador(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin ){
    
        super(nombre, apellidos,  telefono, direccionFisica, fechaIngreso, identificacion, pin);
    
    }
    
    public boolean actualizarAdministrador(Administrador administrador){
    
        return true;
    
    }
    
}
