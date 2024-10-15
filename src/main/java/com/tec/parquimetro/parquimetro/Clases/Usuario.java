
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona implements Serializable{
    
    //atributos
    private static final long serialVersionUID = 2L;
    private int tiempoAcumulado;
    private Tarjeta tarjeta; 
    private List<Vehiculo> vehiculos; //Almacena el vehiculo que esta parqueado, de esta desocupado sera NULL
  
    public Usuario(){
        super();
        this.tiempoAcumulado = -1;
    }
    //Constructor
   public Usuario(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin, int tiempoAcumulado, Tarjeta tarjeta){
    
        super(nombre, apellidos,  telefono, direccionFisica, fechaIngreso, identificacion, pin);
        this.tiempoAcumulado = tiempoAcumulado;
        this.tarjeta = tarjeta;
        vehiculos = new ArrayList<Vehiculo>();
    }
    
    
    //getters and setters
    public Tarjeta getTarjeta(){
        return tarjeta;
    }
    
    public void setTiempoAcumulado(int tiempo){
    
        this.tiempoAcumulado = tiempo;
    }
    
    public int getTiempoAcumulado(){
    
        return this.tiempoAcumulado;
    
    }
    
        public List<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    
    public int actualizarTiempoAcumulado(int tiempoExtra){
    
       return  this.tiempoAcumulado += tiempoExtra;
    }
    
    public void actualizarUsuario(Usuario persona){
       
        super.actualizarDatos(persona);
        
        if(persona.getTiempoAcumulado()!=-1){
        
            this.setTiempoAcumulado(persona.getTiempoAcumulado());
        }

   }
    
   public Vehiculo buscarVehiculo(String placa){
    
        for(Vehiculo obj : vehiculos){
            if(obj.getPlaca().equals(placa)){
                return obj;
            }
        }
        return null;
        
    }
    
    public void agregarVehiculo(Vehiculo vehiculo){
        if(buscarVehiculo(vehiculo.getPlaca())== null){
            vehiculo.setUsuario(this);
            vehiculos.add(vehiculo);
        }
    }
    
}
