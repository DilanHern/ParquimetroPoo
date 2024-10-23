package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Vehiculo representa un vehículo en el sistema.
 * Implementa {@link Serializable}.
 */
public class Vehiculo implements Serializable {
    //atributos
    private String placa;
    private String marca;
    private String modelo;
    private Usuario usuario;
    private Espacio espacio;
    private TicketParqueo ticketVigente;
    private List<TicketParqueo> ticketsUtilizados;
    private ArrayList<Multa> listaMultas;
    
    /**
     * Constructor para crear un nuevo Vehiculo con los detalles especificados.
     *
     * @param placa La placa del vehículo.
     * @param marca La marca del vehículo.
     * @param modelo El modelo del vehículo.
     * @param usuario El usuario asociado al vehículo.
     */
    public Vehiculo(String placa, String marca, String modelo, Usuario usuario){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.usuario = usuario;
        this.ticketVigente = null;
        this.espacio = null;
        ticketsUtilizados = new ArrayList<TicketParqueo>();
        listaMultas = new ArrayList<Multa>();
    }
    
     /**
     * Constructor para crear un nuevo Vehiculo con la placa y el usuario especificados.
     *
     * @param placa La placa del vehículo.
     * @param usuario El usuario asociado al vehículo.
     */
    public Vehiculo(String placa, Usuario usuario){
        this.placa = placa;
        this.marca = "";
        this.modelo = "";
        this.usuario = usuario;
        ticketsUtilizados = new ArrayList<TicketParqueo>();
        listaMultas = new ArrayList<Multa>();
    }

     /**
     * Agrega una nueva multa a la lista de multas del vehículo.
     *
     * @param nuevaMulta La nueva multa a agregar.
     */
    public void setNuevaMulta(Multa nuevaMulta){
        listaMultas.add(nuevaMulta);
    }
 
    /**
     * Obtiene la placa del vehículo.
     *
     * @return La placa del vehículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Establece la placa del vehículo.
     *
     * @param placa La nueva placa del vehículo.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtiene la marca del vehículo.
     *
     * @return La marca del vehículo.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del vehículo.
     *
     * @param marca La nueva marca del vehículo.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el modelo del vehículo.
     *
     * @return El modelo del vehículo.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo del vehículo.
     *
     * @param modelo El nuevo modelo del vehículo.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el usuario asociado al vehículo.
     *
     * @return El usuario asociado al vehículo.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al vehículo.
     *
     * @param usuario El nuevo usuario asociado al vehículo.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el espacio de parqueo asociado al vehículo.
     *
     * @return El espacio de parqueo asociado al vehículo.
     */
    public Espacio getEspacio() {
        return espacio;
    }

    /**
     * Establece el espacio de parqueo asociado al vehículo.
     *
     * @param espacio El nuevo espacio de parqueo asociado al vehículo.
     */
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }
    
    /**
     * Obtiene la lista de tickets de parqueo utilizados por el vehículo.
     *
     * @return La lista de tickets de parqueo utilizados por el vehículo.
     */
    public List<TicketParqueo>  getTickets(){
        return ticketsUtilizados;
    }
    
    /**
     * Obtiene la lista de multas del vehículo.
     *
     * @return La lista de multas del vehículo.
     */
   public List<Multa>  getMultas(){
        return listaMultas;
    }
    
    /**
     * Obtiene el ticket de parqueo vigente del vehículo.
     *
     * @return El ticket de parqueo vigente del vehículo.
     */
    public TicketParqueo  getTicketVigente(){
        return ticketVigente;
    }

    /**
     * Agrega un ticket de parqueo a la lista de tickets utilizados por el vehículo.
     *
     * @param ticket El ticket de parqueo a agregar.
     */
    public void agregarTicket(TicketParqueo  ticket){
    
        ticketsUtilizados.add(ticket);
    }
    
    /**
     * Agrega una multa a la lista de multas del vehículo.
     *
     * @param multa La multa a agregar.
     */
    public void agregarMulta(Multa  multa){
    
        listaMultas.add(multa);
    }
    
    /**
     * Establece el ticket de parqueo vigente del vehículo.
     *
     * @param ticket El nuevo ticket de parqueo vigente del vehículo.
     */
    public void establecerTicketVigente(TicketParqueo  ticket){
    
        ticketVigente = ticket;
    
    }
    
    /**
     * Genera un nuevo ticket de parqueo con tiempo extra.
     *
     * @param tiempoExtra El tiempo extra en minutos.
     * @param total El costo total del parqueo.
     */
     public  void  generarTicketTiempoExtra(int tiempoExtra, int total){
    
         //al ser un ticket generado desde otro ticket se genera un ticker con un tiempo extra generado
         Parqueo  parqueo = new Parqueo();
         parqueo.lecturaArchivo();
         
         Espacio espacio = parqueo.buscarEspacio(ticketVigente.getEspacio().getNumero());
         espacio.agregarTicket(ticketVigente);
         
         parqueo.cargarArchivo();
         
        TicketParqueo ticketNuevo = new TicketParqueo();
        ticketNuevo.setEspacio(ticketVigente.getEspacio());
        ticketNuevo.setHoraSistema(ticketVigente.getHoraSistema());
        ticketNuevo.setTiempoParqueo(ticketVigente.getTiempoParqueo()+ tiempoExtra);
        ticketNuevo.setVehiculo(ticketVigente.getVehiculo());
        ticketNuevo.setUsuario(ticketVigente.getUsuario());
        ticketNuevo.setTotal(ticketVigente.getTotal() + total);
        ticketNuevo.setTipoTicket((short)2);
        ticketNuevo.setEstado(true);
        
        ticketVigente.setEstado(false);
        
        agregarTicket(ticketVigente);
        
        //define el nuevo ticker vigente
        ticketVigente=ticketNuevo;
        
        
    }

     /**
     * Actualiza los detalles del vehículo.
     *
     * @param placa La nueva placa del vehículo.
     * @param modelo El nuevo modelo del vehículo.
     * @param marca La nueva marca del vehículo.
     */
    public void actualizarVehiculo(String placa, String modelo, String marca){
    
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
    
    }
}
