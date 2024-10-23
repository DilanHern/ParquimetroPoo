
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * La clase Inspector representa a un inspector del sistema.
 * Extiende la clase {@link Persona} e implementa {@link Serializable}.
 */
public class Inspector  extends Persona implements Serializable{
    //atributos
    private static final long serialVersionUID = 4L;
    private String terminalInspeccion;
    
        
    /**
     * Constructor por defecto para la clase Inspector.
     * Crea un nuevo Inspector sin inicializar sus atributos.
     */
    public Inspector(){}
    
    /**
     * Constructor para crear un nuevo Inspector con los detalles especificados.
     *
     * @param nombre El nombre del inspector.
     * @param apellidos Los apellidos del inspector.
     * @param telefono El número de teléfono del inspector.
     * @param direccionFisica La dirección física del inspector.
     * @param fechaIngreso La fecha de ingreso del inspector.
     * @param identificacion La identificación del inspector.
     * @param pin El PIN del inspector.
     * @param terminalInspeccion La terminal de inspección del inspector.
     * @param correo El correo electrónico del inspector.
     */
    public Inspector(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin, String terminalInspeccion, Correo correo ){
    
        super(nombre, apellidos,  telefono, direccionFisica, fechaIngreso, identificacion, pin, correo);
        this.terminalInspeccion = terminalInspeccion;
    }
    
    //getters y setters

     /**
     * Obtiene la terminal de inspección del inspector.
     *
     * @return La terminal de inspección del inspector.
     */
    public String getTerminalInspeccion() {
        return terminalInspeccion;
    }

    /**
     * Establece la terminal de inspección del inspector.
     *
     * @param terminal La nueva terminal de inspección del inspector.
     */
    public void setTerminalInspeccion(String terminal) {
        this.terminalInspeccion = terminal;
    }
    
    /**
     * Actualiza la información del inspector con los datos de otro inspector.
     *
     * @param persona El inspector con los nuevos datos.
     */
    public void actualizarInspector(Inspector persona){
       
        super.actualizarDatos(persona);
        
        if(persona.getTerminalInspeccion()!=null){
        
            this.setTerminalInspeccion(persona.getTerminalInspeccion());
        }

   }
    
}
