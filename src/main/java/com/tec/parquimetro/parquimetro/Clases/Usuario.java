
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Usuario representa a un usuario del sistema.
 * Extiende la clase {@link Persona} e implementa {@link Serializable}.
 */
public class Usuario extends Persona implements Serializable{
    
    //atributos
    private static final long serialVersionUID = 2L;
    private int tiempoAcumulado;
    private Tarjeta tarjeta; 
    private List<Vehiculo> vehiculos; //Almacena el vehiculo que esta parqueado, de esta desocupado sera NULL
  
    /**
     * La clase Usuario representa a un usuario del sistema.
     * Extiende la clase {@link Persona} e implementa {@link Serializable}.
     */
    public Usuario(){
        super();
        this.tiempoAcumulado = -1;
    }
    
     /**
     * Constructor para crear un nuevo Usuario con los detalles especificados.
     *
     * @param nombre El nombre del usuario.
     * @param apellidos Los apellidos del usuario.
     * @param telefono El número de teléfono del usuario.
     * @param direccionFisica La dirección física del usuario.
     * @param fechaIngreso La fecha de ingreso del usuario.
     * @param identificacion La identificación del usuario.
     * @param pin El PIN del usuario.
     * @param tiempoAcumulado El tiempo acumulado de parqueo del usuario.
     * @param correo El correo electrónico del usuario.
     */
   public Usuario(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin, int tiempoAcumulado, Correo correo){
    
        super(nombre, apellidos,  telefono, direccionFisica, fechaIngreso, identificacion, pin, correo);
        this.tiempoAcumulado = tiempoAcumulado;
        this.tarjeta = tarjeta;
        vehiculos = new ArrayList<Vehiculo>();
    }
    
    
    //getters y setters

    /**
     * Establece la tarjeta del usuario.
     *
     * @param tarjeta La nueva tarjeta del usuario.
     */    

       public void setTarjeta(Tarjeta tarjeta){
    
        this.tarjeta = tarjeta;
    }
    
    /**
     * Obtiene la tarjeta del usuario.
     *
     * @return La tarjeta del usuario.
     */
    public Tarjeta getTarjeta(){
    
        return this.tarjeta;
    
    }
    
       /**
     * Establece el tiempo acumulado de parqueo del usuario.
     *
     * @param tiempo El nuevo tiempo acumulado de parqueo del usuario.
     */
    public void setTiempoAcumulado(int tiempo){
    
        this.tiempoAcumulado = tiempo;
    }
    
    /**
     * Obtiene el tiempo acumulado de parqueo del usuario.
     *
     * @return El tiempo acumulado de parqueo del usuario.
     */
    public int getTiempoAcumulado(){
    
        return this.tiempoAcumulado;
    
    }
    
     /**
     * Obtiene la lista de vehículos del usuario.
     *
     * @return La lista de vehículos del usuario.
     */
    public List<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    
     /**
     * Actualiza el tiempo acumulado de parqueo del usuario.
     *
     * @param tiempoExtra El tiempo extra a agregar al tiempo acumulado.
     * @return El nuevo tiempo acumulado de parqueo del usuario.
     */
    public int actualizarTiempoAcumulado(int tiempoExtra){
    
       return  this.tiempoAcumulado += tiempoExtra;
    }
    
    /**
     * Actualiza la información del usuario con los datos de otro usuario.
     *
     * @param persona El usuario con los nuevos datos.
     */
    public void actualizarUsuario(Usuario persona){
       
        super.actualizarDatos(persona);
        if(persona.getTiempoAcumulado()!=-1){
        
            this.setTiempoAcumulado(persona.getTiempoAcumulado());
        }

   }
    
    /**
     * Busca un vehículo en la lista de vehículos del usuario basado en su placa.
     *
     * @param placa La placa del vehículo a buscar.
     * @return El vehículo encontrado, o null si no se encuentra.
     */
   public Vehiculo buscarVehiculo(String placa){
    
        for(Vehiculo obj : vehiculos){
            if(obj.getPlaca().equals(placa)){
                return obj;
            }
        }
        return null;
        
    }
    
    /**
     * Agrega un vehículo a la lista de vehículos del usuario.
     *
     * @param vehiculo El vehículo a agregar.
     */
    public void agregarVehiculo(Vehiculo vehiculo){
        //valida que el vehiculo no este registrado
        if(buscarVehiculo(vehiculo.getPlaca())== null){
            vehiculo.setUsuario(this);
            vehiculos.add(vehiculo);
        }
    }
    
     /**
     * Remueve un vehículo de la lista de vehículos del usuario basado en su placa.
     *
     * @param placa La placa del vehículo a remover.
     */
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

    /**
     * Lista los tickets de parqueo utilizados por el usuario en un rango de fechas.
     *
     * @param inicio La fecha de inicio del rango.
     * @param finalF La fecha de fin del rango.
     * @return La lista de tickets de parqueo utilizados por el usuario en el rango de fechas.
     */
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

    /**
     * Lista las multas recibidas por el usuario en un rango de fechas.
     *
     * @param inicio La fecha de inicio del rango.
     * @param finalF La fecha de fin del rango.
     * @return La lista de multas recibidas por el usuario en el rango de fechas.
     */
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
