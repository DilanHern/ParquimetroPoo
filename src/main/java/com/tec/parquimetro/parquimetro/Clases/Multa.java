/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;
import java.time.LocalDateTime;
/**
 *
 * @author Dilan
 */
public class Multa {
    int costo;
    String razon;
    LocalDateTime fechaMulta;
    
    //constructor
    public Multa(int costo, String razon, LocalDateTime fechaMulta){
        this.costo = costo;
        this.razon = razon;
        this.fechaMulta = fechaMulta;
    }
}
