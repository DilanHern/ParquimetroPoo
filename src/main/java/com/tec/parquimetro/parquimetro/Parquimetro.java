/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tec.parquimetro.parquimetro;

import com.tec.parquimetro.parquimetro.Clases.Parqueo;
import com.tec.parquimetro.parquimetro.Clases.*;
import static com.tec.parquimetro.parquimetro.Clases.Login.guardarUsuarios;
import com.tec.parquimetro.parquimetro.GUI.MenuAdministrador;
import com.tec.parquimetro.parquimetro.GUI.MenuInspector;
import com.tec.parquimetro.parquimetro.GUI.MenuUsuario;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Parquimetro {/*

    public static void main(String[] args) throws ClassNotFoundException {
        
          Parqueo parqueo = new Parqueo(LocalTime.parse("08:30"), LocalTime.parse("10:30"), 1000, 30, 200);
            parqueo.agregarEspacios(100, 110);
            List<Espacio> espacios = parqueo.getEspacios();
            espacios.getFirst().setEstado(false);
            Parqueo parqueo1 = new Parqueo();
            
           parqueo.cargarArchivo();

            
          parqueo.lecturaArchivo();
            
            
            parqueo1.lecturaArchivo();
            parqueo1.toStrin();
            
            
         ArrayList<Persona> personas = new ArrayList<Persona>();
         Correo correo = new Correo("camila", "gmail.com");
         
         Usuario usuario = new Usuario("Camila", "Araya Conejo", 61963811, "Cartago  Guadalupe", LocalDate.of(2024, 10, 8), "305610469", "2410", 85,correo);
         Persona inspector = new Inspector("Teodoro", "Araya Conejo", 78456456, "Cartago  Guadalupe", LocalDate.of(2024, 10, 8), "308950456", "2410", "78", correo);
         Persona admin = new Administrador("Antonio", "Araya Conejo", 89667412, "Cartago  Guadalupe", LocalDate.of(2023, 10, 8), "30964089", "8952", correo);
         
         Vehiculo v1 = new Vehiculo("123456", (Usuario)usuario);
         Vehiculo v2 = new Vehiculo("234678", (Usuario)usuario);
         
         usuario.agregarVehiculo(v2);
        usuario.agregarVehiculo(v1);
         
         System.out.println(usuario.getVehiculos().isEmpty());
         long numero = 1234567891234567L;
         Tarjeta tarjeta = new Tarjeta(numero, 12,2024,123);
         usuario.setTarjeta(tarjeta);
         
         personas.add(usuario);
         personas.add(inspector);
         personas.add(admin);
         
         Persona.grabarArchivo(personas);
         
         personas = new ArrayList<Persona>();
         
         Persona.lecturaArchivo(personas);
         

         
        //ArrayList<Persona> personas = new ArrayList<Persona>();
        //Usuario usuario = new Usuario();
       Login login = new Login();
       /*try{
            Login.guardarUsuarios("listaUsuarios.dat", personas);
        }
        catch (IOException e){
            System.out.println("malmamlal");
        }*/
        
        try{
           personas = Login.cargarUsuarios("listaUsuarios.dat");
            System.out.println("hola si");
        }
        catch (IOException e){
            System.out.println("malmamlal");
        }
         
        for(Persona ob : personas){
         
            System.out.println(ob.getApellidos());
             if(ob instanceof Usuario usuarioCast){
                 System.out.println("hola");
                 usuario = usuarioCast;
             }
             
         }
        
         MenuUsuario menuUser = new MenuUsuario(usuario);
         menuUser.setVisible(true);
         
         
         
        // MenuAdministrador menuIns = new MenuAdministrador((Administrador)admin);
       //  menuIns.setVisible(true);
    }*/
}
