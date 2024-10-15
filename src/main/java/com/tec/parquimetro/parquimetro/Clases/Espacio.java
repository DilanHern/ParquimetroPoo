
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Espacio implements Serializable{
    
    private int numero;
    private boolean estado; //Disponible o ocupado
    private List<Vehiculo> vehiculos; //Almacena el vehiculo que esta parqueado, de esta desocupado sera NULL

    public Espacio(){}
    
    //constructor
    public Espacio(int numero, boolean estado){
    
        this.numero = numero;
        this.estado = estado;
        vehiculos = new ArrayList<Vehiculo>();
    
    }

    
    //getters and setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public List<Vehiculo> getVehiculos(){
        return vehiculos;
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
        
            vehiculos.add(vehiculo);
            vehiculo.setEspacio(this);
            
        }
            
        
    }
    
    
}
