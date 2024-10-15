
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.time.LocalDate;

public class Inspector  extends Persona implements Serializable{
    private static final long serialVersionUID = 4L;
    private String terminalInspeccion;
    
    
    
    //getters and setters

    public String getTerminalInspeccion() {
        return terminalInspeccion;
    }

    public void setTerminalInspeccion(String terminal) {
        this.terminalInspeccion = terminal;
    }
    
    
    //Constructor
    public Inspector(){}
    
    public Inspector(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin, String terminalInspeccion ){
    
        super(nombre, apellidos,  telefono, direccionFisica, fechaIngreso, identificacion, pin);
        this.terminalInspeccion = terminalInspeccion;
    }
    
    public void actualizarInspector(Inspector persona){
       
        super.actualizarDatos(persona);
        
        if(persona.getTerminalInspeccion()!=null){
        
            this.setTerminalInspeccion(persona.getTerminalInspeccion());
        }

   }
    
}
