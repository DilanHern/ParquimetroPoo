
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
    
    
    //atributos
    private String apellidos;
    private String nombre;
    private int telefono;
    private LocalDate  fechaIngreso;
    private String pin;
    private String identificacion;
    private String direccionFisica;
    //private Correo correo;
    //private Tarjeta tarjeta;

    public Persona(){}
    
    //Constructor
    public Persona(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin){
    
    
        this.apellidos = apellidos;
        this.direccionFisica =  direccionFisica;
        this.fechaIngreso = fechaIngreso;
        this.identificacion = identificacion;
        this.pin = pin;
        this.telefono = telefono;
        this.nombre =nombre;
        
    }
    
    
    
    // getters and setters
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
        
        if(pin.length() == 5){
        
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
    

   public boolean actualizarDatos(Persona persona){
       
       //respuesta
       
       if(persona.getApellidos() != null){
       
           this.setApellidos(persona.getApellidos());
           
           
       }
       
       return false;
   
   }
    
    
    
   public static void grabarArchivo(List<Persona> personas){

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Personas.txt"))) {
                    // Escribir cada estudiante en el archivo
                    for (Persona obj : personas) {
                        oos.writeObject(obj); // se cargan los datos del objeto a parametros
                    }
                    System.out.println("Archivo escrito con éxito.");
                    

       } catch (OverlappingFileLockException  e) { //valida que el objeto no haya sido abierto o que se este utilizando en otra parte
             System.out.println("Error: el archivo ya esta en uso.");
             e.printStackTrace();
      } catch (IOException e) {
             System.out.println("Error al escribir el archivo.");
             e.printStackTrace();
      }
  }
   
   //Lee el archivo de Parametros.txt, 
   public static void lecturaArchivo(List<Persona> personas){
   
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Personas.txt"))) { 

                while (true) {
                    try {
                        
                        Persona auxiliar = (Persona) ois.readObject();
                        personas.add(auxiliar);
                        
                    } catch (EOFException e) { //validacion para que no llegue al final de laist, se genera un excepcion y esta hace que pare el ciclo
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println("Clase no encontrada: " + e.getMessage()); //valida que la clase estudiante si se ecuenttre
                    }

                }
            }catch (FileNotFoundException  e) { //excepcion el archivo no existe
                 System.out.println("Error: el archivo no existe.");
                 e.printStackTrace();
          } 
            catch(IOException  e){ //Se ha generado otras excepciones

                System.out.println("ERROR: Error generado al intentar leer el archivo");
                e.printStackTrace();
            }
   
   }
}
