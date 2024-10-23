package com.tec.parquimetro.parquimetro.Clases;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * La clase Administrador representa a un administrador del sistema.
 * Extiende la clase {@link Persona} e implementa {@link Serializable}.
 * 
 * @see Persona
 * @see Serializable
 */
public class Administrador extends Persona implements Serializable {
    //atributos
    private static final long serialVersionUID = 3L; //version que lo diferencia de las demás clases

    /**
     * Constructor por defecto para la clase Administrador.
     * Crea una nuevo Administrador sin inicializar sus atributos.
     */
    public Administrador() {
    }

    /**
     * Constructor para crear un nuevo Administrador con los detalles especificados.
     *
     * @param nombre El nombre del administrador.
     * @param apellidos Los apellidos del administrador.
     * @param telefono El número de teléfono del administrador.
     * @param direccionFisica La dirección física del administrador.
     * @param fechaIngreso La fecha de ingreso del administrador.
     * @param identificacion La identificación del administrador.
     * @param pin El PIN del administrador.
     * @param correo El correo electrónico del administrador.
     */
    public Administrador(String nombre, String apellidos, int telefono, String direccionFisica, LocalDate fechaIngreso, String identificacion, String pin, Correo correo) {
        super(nombre, apellidos, telefono, direccionFisica, fechaIngreso, identificacion, pin, correo);
    }
}
