/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tec.parquimetro.parquimetro;

import com.tec.parquimetro.parquimetro.Clases.Parqueo;
import com.tec.parquimetro.parquimetro.Clases.*;
import com.tec.parquimetro.parquimetro.GUI.MenuAdministrador;
import com.tec.parquimetro.parquimetro.GUI.MenuInspector;
import com.tec.parquimetro.parquimetro.GUI.MenuUsuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Parquimetro {/*

    public static void main(String[] args) {
        
            Parqueo parqueo = new Parqueo(LocalTime.parse("08:30"), LocalTime.parse("10:30"), 1000, 30, 200);
            parqueo.agregarEspacios(100, 110);
            List<Espacio> espacios = parqueo.getEspacios();
            espacios.getFirst().setEstado(false);
            Parqueo parqueo1 = new Parqueo();
            
           parqueo.cargarArchivo();

            
          parqueo.lecturaArchivo();
            
            
            parqueo1.lecturaArchivo();
            parqueo1.toStrin();
            
            
         List<Persona> personas = new ArrayList<Persona>();
         
         Usuario usuario = new Usuario("Camila", "Araya Conejo", 61963811, "Cartago  Guadalupe", LocalDate.of(2024, 10, 8), "305610469", "2410", 85);
         Persona inspector = new Inspector("Teodoro", "Araya Conejo", 78456456, "Cartago  Guadalupe", LocalDate.of(2024, 10, 8), "308950456", "2410", "78");
         Persona admin = new Administrador("Antonio", "Araya Conejo", 89667412, "Cartago  Guadalupe", LocalDate.of(2023, 10, 8), "30964089", "8952");
         
         Vehiculo v1 = new Vehiculo("123456", (Usuario)usuario);
         Vehiculo v2 = new Vehiculo("234678", (Usuario)usuario);
         
         usuario.agregarVehiculo(v2);
        usuario.agregarVehiculo(v1);
         
         System.out.println(usuario.getVehiculos().isEmpty());
         
         personas.add(usuario);
         personas.add(inspector);
         personas.add(admin);
         
         Persona.grabarArchivo(personas);
         
         personas = new ArrayList<Persona>();
         
         Persona.lecturaArchivo(personas);
         
         for(Persona ob : personas){
         
             System.out.println(ob.getApellidos());
             System.out.println(ob.getNombre());
             System.out.println(ob.getDireccionFisica());
             System.out.println(ob.getFechaIngreso());
             if(ob instanceof Usuario)
                 System.out.println("Usuario");
             if(ob instanceof Inspector)
                 System.out.println("Inspector");
             
         }
         
         MenuUsuario menuUser = new MenuUsuario(usuario);
         menuUser.setVisible(true);
         
        // MenuAdministrador menuIns = new MenuAdministrador((Administrador)admin);
       //  menuIns.setVisible(true);
    }*/
}
