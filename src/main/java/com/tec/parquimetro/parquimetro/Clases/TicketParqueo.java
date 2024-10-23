package com.tec.parquimetro.parquimetro.Clases;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * La clase TicketParqueo representa un ticket de parqueo.
 * Implementa {@link Serializable}.
 */
public class TicketParqueo implements Serializable{
    //atributos
    private int tiempoParqueo;
    private LocalDateTime horaSistema;
    private boolean estado;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private int total;
    private Espacio espacio;
    private short tipoTicket; //1->Ticket nuevo 2-> Ticket con tiempo extra
    
    /**
     * Constructor por defecto de la clase TicketParqueo.
     */
    public TicketParqueo(){}
    
    /**
     * Constructor de la clase TicketParqueo.
     *
     * @param tiempoParqueo El tiempo de parqueo en minutos.
     * @param horaSistema La hora del sistema cuando se genera el ticket.
     * @param estado El estado del ticket (activo o inactivo).
     * @param vehiculo El vehículo asociado al ticket.
     * @param usuario El usuario asociado al ticket.
     * @param total El costo total del parqueo.
     */
    public TicketParqueo(int tiempoParqueo, LocalDateTime horaSistema, boolean estado, Vehiculo vehiculo, Usuario usuario, int total){
        this.tiempoParqueo = tiempoParqueo;
        this.horaSistema = horaSistema;
        this.estado = estado;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.total = total;
        this.tipoTicket = 1;
    }
    
    
    /**
     * Método para marcar el ticket como desaparcado.
     */
    public void desaparcar(){
        //logica
    }
    
    /**
     * Método para extender el tiempo de parqueo.
     *
     * @param tiempoExtra El tiempo extra en minutos.
     * @param estado El nuevo estado del ticket.
     */
    public void extenderTiempo(int tiempoExtra, boolean estado){
        //logica
    }

    /**
     * Obtiene el tiempo de parqueo.
     *
     * @return El tiempo de parqueo en minutos.
     */
    public int getTiempoParqueo() {
        return tiempoParqueo;
    }

    /**
     * Establece el tiempo de parqueo.
     *
     * @param tiempoParqueo El tiempo de parqueo en minutos.
     */
    public void setTiempoParqueo(int tiempoParqueo) {
        this.tiempoParqueo = tiempoParqueo;
    }

    /**
     * Obtiene la hora del sistema.
     *
     * @return La hora del sistema.
     */
    public LocalDateTime getHoraSistema() {
        return horaSistema;
    }

    /**
     * Establece la hora del sistema.
     *
     * @param horaSistema La hora del sistema.
     */
    public void setHoraSistema(LocalDateTime horaSistema) {
        this.horaSistema = horaSistema;
    }

    /**
     * Obtiene el estado del ticket.
     *
     * @return El estado del ticket.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado del ticket.
     *
     * @param estado El estado del ticket.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el usuario asociado al ticket.
     *
     * @return El usuario asociado al ticket.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al ticket.
     *
     * @param usuario El usuario asociado al ticket.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Obtiene el espacio de parqueo asociado al ticket.
     *
     * @return El espacio de parqueo asociado al ticket.
     */
    public Espacio getEspacio() {
        return espacio;
    }

    /**
     * Establece el espacio de parqueo asociado al ticket.
     *
     * @param espacio El espacio de parqueo asociado al ticket.
     */
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    /**
     * Obtiene el vehículo asociado al ticket.
     *
     * @return El vehículo asociado al ticket.
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Establece el vehículo asociado al ticket.
     *
     * @param vehiculo El vehículo asociado al ticket.
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * Obtiene el costo total del parqueo.
     *
     * @return El costo total del parqueo.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Establece el costo total del parqueo.
     *
     * @param total El costo total del parqueo.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Obtiene el tipo de ticket.
     *
     * @return El tipo de ticket.
     */
    public short getTipoTicket() {
        return tipoTicket;
    }

    /**
     * Establece el tipo de ticket.
     *
     * @param tipoTicket El tipo de ticket.
     */
    public void setTipoTicket(short tipoTicket) {
        this.tipoTicket = tipoTicket;
    }
    
    

    
}
