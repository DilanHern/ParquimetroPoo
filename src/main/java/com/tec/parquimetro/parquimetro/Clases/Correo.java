/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;
import java.io.Serializable;

/**
 * La clase Correo representa un correo electrónico.
 * Implementa {@link Serializable}.
 */
public class Correo implements Serializable {
    //atributos
    private String str1;
    private String str2;

    /**
     * Constructor para crear un nuevo Correo con los detalles especificados.
     *
     * @param str1 El primer string del correo.
     * @param str2 El segundo string del correo.
     */
    public Correo(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    /**
     * Obtiene el primer string del correo.
     *
     * @return El primer string del correo.
     */
    public String getStr1() {
        return str1;
    }

    /**
     * Establece el primer string del correo.
     *
     * @param str1 El nuevo primer string del correo.
     */
    public void setStr1(String str1) {
        this.str1 = str1;
    }

    /**
     * Obtiene el segundo string del correo.
     *
     * @return El segundo string del correo.
     */
    public String getStr2() {
        return str2;
    }

    /**
     * Establece el segundo string del correo.
     *
     * @param str2 El nuevo segundo string del correo.
     */
    public void setStr2(String str2) {
        this.str2 = str2;
    }

    /**
     * Obtiene el correo completo en formato "str1@str2".
     *
     * @return El correo completo.
     */
    public String getCorreo() {
        return getStr1() + "@" + getStr2();
    }

    /**
     * Establece el correo completo usando dos strings.
     *
     * @param str1 El primer string del correo.
     * @param str2 El segundo string del correo.
     */
    public void setCorreo(String str1, String str2) {
        this.setStr1(str1);
        this.setStr2(str2);
    }
}
