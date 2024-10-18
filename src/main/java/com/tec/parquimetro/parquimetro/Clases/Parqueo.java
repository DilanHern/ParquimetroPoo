
package com.tec.parquimetro.parquimetro.Clases;
import java.io.*; //manejo de archivos
import java.nio.channels.OverlappingFileLockException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Properties;


public class Parqueo implements Serializable {
    
    
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private int precioHora;
    private int tiempoMinimo;
    private int costoMulta;
    private ArrayList<Espacio> espacios;
    
    
    public Parqueo(){
    
        this.espacios = new ArrayList<Espacio>();
    
    }
    
    //constructor
    public Parqueo(LocalTime horaInicio, LocalTime horaFinal, int precioHora, int tiempoMinimo, int costoMulta){
    
        this.costoMulta = costoMulta;
        this.horaFinal = horaFinal;
        this.horaInicio = horaInicio;
        this.precioHora = precioHora;
        this.tiempoMinimo = tiempoMinimo;

        this.espacios = new ArrayList<Espacio>();

    }


    //getters and setters
    public LocalTime getHoraInicio() {
        return horaInicio;
    }


    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }


    public LocalTime getHoraFinal() {
        return horaFinal;
    }


    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getPrecioHora() {
        return precioHora;
    }
    
   public boolean setHorarioRegulacion(LocalTime horaIncio, LocalTime horaFinal){
        
        if(horaIncio!= horaFinal){ //validacion si las horas son distintas
        
            if(horaFinal.isAfter(horaInicio)){
                    return true; //horas anadidas exitosamente
            
            }else{
                return false; //la hora final es menor que la hora de inicio
            }
        
        }
        else
            return false;//las horas son iguales
    }


    public boolean validarPrecioHora(int precioHora) {
        
        if(precioHora%2 ==  0){
        
            return true;
        }
        else{
            return false;
        }
        
        
    }
    
    public void setPrecioHora(int precioHora) {
        
        this.precioHora = precioHora;
    }


    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

  
    public boolean validarTiempoMinimo(int tiempoMinimo) {
        
        if(tiempoMinimo%30 == 0){
            return true;
        }
        else{
            return false;
        }
        
        
    }
    
   public void setTiempoMinimo(int tiempoMinimo) {
        
        this.tiempoMinimo = tiempoMinimo;
        
    }

 
    public int getCostoMulta() {
        return costoMulta;
    }


    public boolean validarCostoMulta(int costoMulta) {
        
        if(costoMulta>=0){
            this.costoMulta = costoMulta;
            return true;
        }
        else{
            return false;
        }
    }
    
    public void setCostoMulta(int costoMulta) {
        
        this.costoMulta = costoMulta;
    }
    
   public List<Espacio> getEspacios(){
    
        return this.espacios;
    }
    
    public void setEspacios(ArrayList<Espacio> espacios){
    
        this.espacios = espacios;
    }
    
    //metodos
    
    public void actualizarParametros(Parqueo parqueo){
    
        setCostoMulta(parqueo.getCostoMulta());
        setPrecioHora(parqueo.getPrecioHora());
        setTiempoMinimo(parqueo.getTiempoMinimo());
        setHoraInicio(parqueo.getHoraInicio());
        setHoraFinal(parqueo.getHoraFinal());
        
        cargarArchivo();
    
    }
    

   
   public void cargarArchivo(){
   
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Parametros.txt"))) {
                        // Escribir cada estudiante en el archivo
                       oos.writeObject(this);
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
   public void lecturaArchivo(){
   
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Parametros.txt"))) { 

                while (true) {
                    try {
                        
                        Parqueo auxiliar = (Parqueo) ois.readObject();
                        
                        this.setCostoMulta(auxiliar.costoMulta);
                        this.setHoraFinal(auxiliar.horaFinal);
                        this.setHoraInicio(auxiliar.horaInicio);
                        this.setPrecioHora(auxiliar.precioHora);
                        this.setTiempoMinimo(auxiliar.tiempoMinimo);
                        this.setEspacios(auxiliar.espacios);
                        
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
    
    
   
   
   
   public void toStrin(){
   
        System.out.println(this.costoMulta);
        System.out.println(this.horaFinal);
        System.out.println(this.horaInicio);
        System.out.println(this.precioHora);
        System.out.println(this.tiempoMinimo);
        
        for(Espacio espacio: espacios){
            System.out.println(espacio.getNumero() + " " + espacio.getEstado());
        }
   }
   
    public ArrayList<Espacio> listarEspacios(){
        
        return espacios;
    
    }
    
    public ArrayList<Espacio> listarEspaciosVacios(){
        
        ArrayList<Espacio> espaciosVacios = new ArrayList<Espacio>();
        
        for(Espacio espacio : espacios){
            
            if(espacio.getEstado())
                espaciosVacios.add(espacio);
            
        }
        
        return espaciosVacios;
    
    }
    
   public ArrayList<Espacio> listarEspaciosOcupados(){
        
        ArrayList<Espacio> espaciosOcupados = new ArrayList<Espacio>();
        
        for(Espacio espacio : espacios){
            
            if(!espacio.getEstado())
                espaciosOcupados.add(espacio);
            
        }
        
        return espaciosOcupados;
    
    }
   
   public void liberarEspacio(int numero){
   
   
       Espacio espacio = buscarEspacio(numero);
       espacio.setEstado(true);
       cargarArchivo();
   
   }
   
     public void ocuparEspacio(int numero){
   
   
       Espacio espacio = buscarEspacio(numero);
       espacio.setEstado(false);
       cargarArchivo();
   
   }
    
    public boolean agregarEspacios(int rangoInicio, int rangoFinal){
    
        
        if(rangoInicio<= rangoFinal){
        
                //anade los espacios dentro del rango indicado
                for(int i = rangoInicio; i<= rangoFinal;  i++){

                        if(buscarEspacio(i) == null){ //valida que el numero de espacio a anadir no exista

                            Espacio espacio = new Espacio(i, true);  //crea el espacio nuevo
                            espacios.add(espacio); //anade a la lista el espacio instanciado

                        }

                }
                return true;
        
        }
        else{
        
            return false;
        }

    
    }
    
    public void eliminarEspacios(int rangoInicio, int rangoFinal){
    
        
        Espacio espacio;
        //anade los espacios dentro del rango indicado
        for(int i = rangoInicio;i<= rangoFinal; i++){
        
                espacio = buscarEspacio(i); //busca el espacio que se desea eliminar
                if(espacio != null){ //valida que el numero de espacio a anadir no exista
                
                    espacios.remove(espacio); //remueve de la lista el espacio a eliminar
                
                }
                    
        }
    
    }
    
    public Espacio buscarEspacio(int numero){
        
        
        //Busca entre los espacios registrados un espacio que coincida con el numero que se busca
        //E: Numero de tipo entero, valor a comparar
        //S: Espacio encontrado o NULL en caso de no encontrarlo
    
        for(Espacio espacio : espacios){
        
            if(espacio.getNumero() == numero){
            
                return espacio; //el espacio ya esta registrado
            }
            
        }
        
        return null;
    
    }

   

   
}
