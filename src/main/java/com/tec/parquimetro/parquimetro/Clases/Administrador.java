package com.tec.parquimetro.parquimetro.Clases;
import java.io.Serializable;
import java.time.LocalDate;


public class Administrador extends Persona implements Serializable{
    
    
    public Administrador(){
    }
    //Constructor
    public Administrador(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin, Correo correo ){
    
        super(nombre, apellidos,  telefono, direccionFisica, fechaIngreso, identificacion, pin, correo);
    
    }
    
    public boolean actualizarAdministrador(Administrador administrador){
    
        return true;
    
    }
    
}
