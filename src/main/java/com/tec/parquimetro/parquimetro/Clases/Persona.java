
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

/**
 * La clase Persona representa a una persona en el sistema.
 * Implementa {@link Serializable}.
 */
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

    /**
     * La clase Persona representa a una persona en el sistema.
     * Implementa {@link Serializable}.
     */
    public Persona(){
    
        telefono = -1;
    }
    
    /**
     * Constructor para crear una nueva Persona con los detalles especificados.
     *
     * @param nombre El nombre de la persona.
     * @param apellidos Los apellidos de la persona.
     * @param telefono El número de teléfono de la persona.
     * @param direccionFisica La dirección física de la persona.
     * @param fechaIngreso La fecha de ingreso de la persona.
     * @param identificacion La identificación de la persona.
     * @param pin El PIN de la persona.
     * @param correo El correo electrónico de la persona.
     */
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
    
    
    
    // getters y setters
    
     /**
     * Obtiene el correo electrónico de la persona.
     *
     * @return El correo electrónico de la persona.
     */
    public Correo getCorreo() {
        return correo;
    }

     /**
     * Establece el correo electrónico de la persona.
     *
     * @param correo El nuevo correo electrónico de la persona.
     */
    public void setCorrero(Correo correo) {
        
            this.correo = correo;
    }
    
    /**
     * Obtiene los apellidos de la persona.
     *
     * @return Los apellidos de la persona.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos de la persona.
     *
     * @param apellidos Los nuevos apellidos de la persona.
     * @return true si los apellidos son válidos, false en caso contrario.
     */
    public boolean setApellidos(String apellidos) {
        
        if(apellidos.length()>= 1 && apellidos.length()<=40){
            this.apellidos = apellidos;
            return true;
        }
        else
            return false;
        
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre El nuevo nombre de la persona.
     * @return true si el nombre es válido, false en caso contrario.
     */
    public boolean setNombre(String nombre) {
        
        if(nombre.length() >= 2 && nombre.length()<=20){
                 this.nombre = nombre;
                 return true;
        }
        return false;
        
    }

    /**
     * Obtiene el número de teléfono de la persona.
     *
     * @return El número de teléfono de la persona.
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono de la persona.
     *
     * @param telefono El nuevo número de teléfono de la persona.
     * @return true si el número de teléfono es válido, false en caso contrario.
     */
    public boolean setTelefono(int telefono) {
        
        //es caso de tener 8 digito su resultado
        if(telefono / 100000000 == 0){
            this.telefono = telefono;
            return true;
        }
        else
            return false;
        
        
    }

    /**
     * Obtiene la fecha de ingreso de la persona.
     *
     * @return La fecha de ingreso de la persona.
     */
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Establece la fecha de ingreso de la persona.
     *
     * @param fechaIngreso La nueva fecha de ingreso de la persona.
     * @return true si la fecha de ingreso es válida, false en caso contrario.
     */
    public boolean setFechaIngreso(LocalDate fechaIngreso) {
        
        
        LocalDate hoy = LocalDate.now();
        if(fechaIngreso.isBefore(hoy) || fechaIngreso.isEqual(hoy)){
            this.fechaIngreso = fechaIngreso;
            return true;
        
        }
        else
            return false;
        
    }

    /**
     * Obtiene el PIN de la persona.
     *
     * @return El PIN de la persona.
     */
    public String getPin() {
        return pin;
    }
    
    /**
     * Establece el PIN de la persona.
     *
     * @param pin El nuevo PIN de la persona.
     * @return true si el PIN es válido, false en caso contrario.
     */
    public boolean setPin(String pin) {
        
        if(pin.length() == 4){
        
            this.pin = pin;
            return true;
            
        }
        else
            return false;
        
    }

    /**
     * Obtiene la identificación de la persona.
     *
     * @return La identificación de la persona.
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Establece la identificación de la persona.
     *
     * @param identificacion La nueva identificación de la persona.
     * @return true si la identificación es válida, false en caso contrario.
     */
    public boolean setIdentificacion(String identificacion) {
        
        if(identificacion.length()>= 2 && identificacion.length() <= 25){
        
            this.identificacion = identificacion;
            return true;
            
        }
        else{
            
            return false;
        
        }
    }


    /**
     * Obtiene la dirección física de la persona.
     *
     * @return La dirección física de la persona.
     */
    public String getDireccionFisica() {
        return direccionFisica;
    }

    /**
     * Establece la dirección física de la persona.
     *
     * @param direccionFisica La nueva dirección física de la persona.
     * @return true si la dirección física es válida, false en caso contrario.
     */
    public boolean  setDireccionFisica(String direccionFisica) {
        
        if(direccionFisica.length() >= 5 && direccionFisica.length() <= 50){
            
            this.direccionFisica = direccionFisica;
            return true;
            
        }else{
        
            return false;
        }
        
    }
    
    /**
     * Actualiza el PIN de la persona.
     *
     * @param pin El nuevo PIN de la persona.
     * @return true si el PIN es válido, false en caso contrario.
     */
    public boolean actualizaPin(String pin){
    
        if(pin.length() == 5){
            this.pin=pin; 
            return true;
        }
        else{
        
            return false;
        }
    }
    
    /**
     * Actualiza los datos de la persona con los datos de otra persona.
     *
     * @param persona La persona con los nuevos datos.
     */
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
