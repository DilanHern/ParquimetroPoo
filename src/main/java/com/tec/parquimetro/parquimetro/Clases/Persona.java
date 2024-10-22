
package com.tec.parquimetro.parquimetro.Clases;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.channels.OverlappingFileLockException;
import java.time.LocalDate;
import java.util.List;

public class Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    //atributos
    private String apellidos;
    private String nombre;
    private int telefono;
    private LocalDate  fechaIngreso;
    private String pin;
    private String identificacion;
    private String direccionFisica;
    private Correo correo;


    public Persona(){
    
        telefono = -1;
    }
    
    //Constructor
    public Persona(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin, Correo correo){
    
    
        this.apellidos = apellidos;
        this.direccionFisica =  direccionFisica;
        this.fechaIngreso = fechaIngreso;
        this.identificacion = identificacion;
        this.pin = pin;
        this.telefono = telefono;
        this.nombre =nombre;
        this.correo = correo;
        System.out.println(correo.getStr2());
    }
    
    
    
    // getters and setters
    
    public Correo getCorreo() {
        return correo;
    }

    public void setCorrero(Correo correo) {
        
            this.correo = correo;
    }
    
    
    
    public String getApellidos() {
        return apellidos;
    }

    public boolean setApellidos(String apellidos) {
        
        if(apellidos.length()>= 1 && apellidos.length()<=40){
            this.apellidos = apellidos;
            return true;
        }
        else
            return false;
        
    }


    public String getNombre() {
        return nombre;
    }


    public boolean setNombre(String nombre) {
        
        if(nombre.length() >= 2 && nombre.length()<=20){
                 this.nombre = nombre;
                 return true;
        }
        return false;
        
    }


    public int getTelefono() {
        return telefono;
    }


    public boolean setTelefono(int telefono) {
        
        //es caso de tener 8 digito su resultado
        if(telefono / 100000000 == 0){
            this.telefono = telefono;
            return true;
        }
        else
            return false;
        
        
    }


    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

  
    public boolean setFechaIngreso(LocalDate fechaIngreso) {
        
        
        LocalDate hoy = LocalDate.now();
        if(fechaIngreso.isBefore(hoy) || fechaIngreso.isEqual(hoy)){
            this.fechaIngreso = fechaIngreso;
            return true;
        
        }
        else
            return false;
        
    }
    
    public String getPin() {
        return pin;
    }

    public boolean setPin(String pin) {
        
        if(pin.length() == 4){
        
            this.pin = pin;
            return true;
            
        }
        else
            return false;
        
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public boolean setIdentificacion(String identificacion) {
        
        if(identificacion.length()>= 2 && identificacion.length() <= 25){
        
            this.identificacion = identificacion;
            return true;
            
        }
        else{
            
            return false;
        
        }
    }

    public String getDireccionFisica() {
        return direccionFisica;
    }

    public boolean  setDireccionFisica(String direccionFisica) {
        
        if(direccionFisica.length() >= 5 && direccionFisica.length() <= 50){
            
            this.direccionFisica = direccionFisica;
            return true;
            
        }else{
        
            return false;
        }
        
    }
    
    
    public boolean actualizaPin(String pin){
    
        if(pin.length() == 5){
            this.pin=pin; 
            return true;
        }
        else{
        
            return false;
        }
    }
    

   public void actualizarDatos(Persona persona){
       
       //validacion actualizar datos de una persona
       
       if(persona.getApellidos() != null){
       
           this.setApellidos(persona.getApellidos());
       }
       if(persona.getNombre() != null){
       
           this.setNombre(persona.getNombre());
       }
       if(persona.getIdentificacion() != null){
       
           this.setIdentificacion(persona.getIdentificacion());
       }
       if(persona.getDireccionFisica()!= null){
       
           this.setDireccionFisica(persona.getDireccionFisica());
       }
       if(persona.getTelefono()!= -1){
       
           this.setTelefono(persona.getTelefono());
       }
       if(persona.getFechaIngreso()!= null){
       
           this.setFechaIngreso(persona.getFechaIngreso());
       }
      if(persona.getCorreo() != null){
          this.correo = persona.getCorreo();
      }
   
   }
    
}
