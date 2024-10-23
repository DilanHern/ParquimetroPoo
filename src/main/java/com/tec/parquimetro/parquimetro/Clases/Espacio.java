
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Espacio representa un espacio de parqueo.
 * Implementa {@link Serializable}.
 */
public class Espacio implements Serializable{
    //atributos
    private int numero; //numero del espacio
    private boolean estado; //Disponible o ocupado
    private List<Vehiculo> vehiculos; //Almacena el vehiculo que esta parqueado, de esta desocupado sera NULL
    private List<TicketParqueo> tickets; //Almacena el vehiculo que esta parqueado, de esta desocupado sera NULL
    
    /**
     * Constructor por defecto para la clase Espacio.
     * Crea un nuevo Espacio sin inicializar sus atributos.
     */
    public Espacio(){}
    
    /**
     * Constructor para crear un nuevo Espacio con los detalles especificados.
     *
     * @param numero El número del espacio.
     * @param estado El estado del espacio (disponible o ocupado).
     */
    public Espacio(int numero, boolean estado){
    
        this.numero = numero;
        this.estado = estado;
        vehiculos = new ArrayList<Vehiculo>();
        this.tickets = new ArrayList<TicketParqueo>();
    }

    
    //getters y setters
    /**
     * Obtiene el número del espacio.
     *
     * @return El número del espacio.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Establece el número del espacio.
     *
     * @param numero El nuevo número del espacio.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el estado del espacio.
     *
     * @return El estado del espacio (true si está ocupado, false si está disponible).
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Establece el estado del espacio.
     *
     * @param estado El nuevo estado del espacio (true si está ocupado, false si está disponible).
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    /**
     * Obtiene la lista de vehículos en el espacio.
     *
     * @return La lista de vehículos en el espacio.
     */
    public List<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    
    /**
     * Obtiene la lista de tickets de parqueo asociados al espacio.
     *
     * @return La lista de tickets de parqueo asociados al espacio.
     */
    public List<TicketParqueo> getTickets() {
        return tickets;
    }
    
    /**
     * Busca un vehículo en la lista de vehículos basado en su placa.
     *
     * @param placa La placa del vehículo a buscar.
     * @return El vehículo encontrado, o null si no se encuentra.
     */
    public Vehiculo buscarVehiculo(String placa){
    
        for(Vehiculo obj : getVehiculos()){
         
            if(obj.getPlaca().equals(placa)){
            
                return obj;
                
            }
            
        }
        return null;
        
    }
    
    /**
     * Agrega un vehículo a la lista de vehículos.
     *
     * @param vehiculo El vehículo a agregar.
     */
    public void agregarVehiculo(Vehiculo vehiculo){
    
        if(buscarVehiculo(vehiculo.getPlaca())== null){
        
            getVehiculos().add(vehiculo);
            vehiculo.setEspacio(this);
            
        }
            
        
    }
    
    /**
     * Agrega un ticket de parqueo a la lista de tickets.
     *
     * @param ticket El ticket de parqueo a agregar.
     */
    public void agregarTicket(TicketParqueo ticket){
    
        this.tickets.add(ticket);
        
    }

    
    /**
     * Remueve un vehículo de la lista de vehículos basado en su placa.
     *
     * @param placa La placa del vehículo a remover.
     */
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
