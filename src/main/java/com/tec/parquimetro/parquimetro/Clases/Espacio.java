
package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;


public class Espacio implements Serializable{
    
    private int numero;
    private boolean estado; //Disponible o ocupado
    //private Vehiculo vehiculo; //Almacena el vehiculo que esta parqueado, de esta desocupado sera NULL

    public Espacio(){}
    
    //constructor
    public Espacio(int numero, boolean estado){
    
        this.numero = numero;
        this.estado = estado;
    
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
    
    
    
    
}
