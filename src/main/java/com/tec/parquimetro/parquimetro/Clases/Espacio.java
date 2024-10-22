
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Espacio implements Serializable{
    
    private int numero;
    private boolean estado; //Disponible o ocupado
    private List<Vehiculo> vehiculos; //Almacena el vehiculo que esta parqueado, de esta desocupado sera NULL
    private List<TicketParqueo> tickets; //Almacena el vehiculo que esta parqueado, de esta desocupado sera NULL
    public Espacio(){}
    
    //constructor
    public Espacio(int numero, boolean estado){
    
        this.numero = numero;
        this.estado = estado;
        vehiculos = new ArrayList<Vehiculo>();
        this.tickets = new ArrayList<TicketParqueo>();
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
    
        /**
     * @return the tickets
     */
    public List<TicketParqueo> getTickets() {
        return tickets;
    }
    

    
    public Vehiculo buscarVehiculo(String placa){
    
        for(Vehiculo obj : getVehiculos()){
         
            if(obj.getPlaca().equals(placa)){
            
                return obj;
                
            }
            
        }
        return null;
        
    }
    
    public void agregarVehiculo(Vehiculo vehiculo){
    
        if(buscarVehiculo(vehiculo.getPlaca())== null){
        
            getVehiculos().add(vehiculo);
            vehiculo.setEspacio(this);
            
        }
            
        
    }
    
    public void agregarTicket(TicketParqueo ticket){
    
        this.tickets.add(ticket);
        
    }
    
   public void removerVehiculo(String placa){
    
       Vehiculo objEncontrado = null;
       
        for(Vehiculo obj : getVehiculos()){
         
            if(obj.getPlaca().equals(placa)){
                objEncontrado = obj;
                break;
            }
            
        }
        if(objEncontrado!=null)
            getVehiculos().remove(objEncontrado);
        
    }


    
    
}
