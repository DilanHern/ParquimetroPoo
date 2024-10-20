
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
   public Usuario(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin, int tiempoAcumulado, Correo correo){
    
        super(nombre, apellidos,  telefono, direccionFisica, fechaIngreso, identificacion, pin, correo);
        this.tiempoAcumulado = tiempoAcumulado;
        this.tarjeta = tarjeta;
        vehiculos = new ArrayList<Vehiculo>();
    }
    
    
    //getters and setters
   
       public void setTarjeta(Tarjeta tarjeta){
    
        this.tarjeta = tarjeta;
    }
    
    public Tarjeta getTarjeta(){
    
        return this.tarjeta;
    
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
        //valida que el vehiculo no este registrado
        if(buscarVehiculo(vehiculo.getPlaca())== null){
            vehiculo.setUsuario(this);
            vehiculos.add(vehiculo);
        }
    }
    
    //remueve el vehiculo en relacion a la placa recibida
    public void removerVehiculo(String placa){
    
       Vehiculo objEncontrado = null;
       
        for(Vehiculo obj : vehiculos){
         
            if(obj.getPlaca().equals(placa)){
                objEncontrado = obj;
                break;
            }
            
        }
        if(objEncontrado!=null)
            vehiculos.remove(objEncontrado);
        
    }
    
    public List<TicketParqueo> listarParqueosUtilizados(LocalDate inicio, LocalDate finalF){
    
        List<TicketParqueo> lista = new ArrayList<TicketParqueo>();
        List<TicketParqueo> listaTicketVehiculo; //Almacena los ticket de los vehiculos que se recorrera
        
        for(Vehiculo vehiculo : vehiculos){
        
            listaTicketVehiculo = vehiculo.getTickets();
            for(TicketParqueo ticket : listaTicketVehiculo){
                LocalDate fechaTicket = ticket.getHoraSistema().toLocalDate();
                
                if((fechaTicket.isAfter(inicio) || fechaTicket.isEqual(inicio)) && (fechaTicket.isBefore(finalF) || fechaTicket.isEqual(finalF)) )
                {
                    lista.add(ticket);
                }
                
            }
            
        }
        
        return lista;
    }
    
    public List<Multa> listarMultas(LocalDate inicio, LocalDate finalF){
    
        List<Multa> lista = new ArrayList<Multa>();
        List<Multa> multasVehiculo; //Almacena las multas  de los vehiculos que se recorrera
        
        if(vehiculos!= null){
            for(Vehiculo vehiculo : vehiculos){

                multasVehiculo = vehiculo.getMultas();
                for(Multa multa : multasVehiculo){
                    LocalDate fechaMulta= multa.getFechaMulta().toLocalDate();

                    if((fechaMulta.isAfter(inicio) || fechaMulta.isEqual(inicio)) && (fechaMulta.isBefore(finalF) || fechaMulta.isEqual(finalF)) )
                    {
                        lista.add(multa);
                    }

                }

            }
        }
        
        return lista;
    }
    
}
