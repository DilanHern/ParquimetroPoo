/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;

/**
 *
 * @author Dilan
 */
public class Correo {
    private String str1;
    private String str2;
    
    public Correo(String str1, String str2){
        this.str1 = str1;
        this.str2 = str2;
    }
    
    public String getCorreo(){
        return str1+"@"+str2;
    }
    
    public void setCorreo(String str1, String str2){
        this.str1 = str1;
        this.str2 = str2;
    }
}
