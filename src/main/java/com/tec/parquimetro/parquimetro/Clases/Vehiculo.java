/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;

/**
 *
 * @author Dilan
 */
public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private Usuario usuario;
    
    //constructores
    public Vehiculo(String placa, String marca, String modelo, Usuario usuario){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.usuario = usuario;
    }
    
    public Vehiculo(String placa, Usuario usuario){
        this.placa = placa;
        this.marca = "";
        this.modelo = "";
        this.usuario = usuario;
    }
}
