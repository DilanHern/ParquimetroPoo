/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tec.parquimetro.parquimetro;

import com.tec.parquimetro.parquimetro.Clases.Parqueo;
import com.tec.parquimetro.parquimetro.Clases.*;
import static com.tec.parquimetro.parquimetro.Clases.Login.guardarUsuarios;
import com.tec.parquimetro.parquimetro.GUI.LoginJFrame;
import com.tec.parquimetro.parquimetro.GUI.MenuAdministrador;
import com.tec.parquimetro.parquimetro.GUI.MenuInspector;
import com.tec.parquimetro.parquimetro.GUI.MenuUsuario;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Parquimetro {

    public static void main(String[] args) throws ClassNotFoundException {
     
 /* Parqueo parqueo = new Parqueo(LocalTime.parse("08:30"), LocalTime.parse("17:30"), 1000, 30, 200);
            parqueo.agregarEspacios(100, 110);
            List<Espacio> espacios = parqueo.getEspacios();
            Parqueo parqueo1 = new Parqueo();
            
           parqueo.cargarArchivo();

            
          parqueo.lecturaArchivo();
            
            
            parqueo1.lecturaArchivo();
            parqueo1.toStrin();
            
            
         ArrayList<Persona> personas = new ArrayList<Persona>();
         Correo correo = new Correo("carayacn", "gmail.com");
         
         Usuario usuario = new Usuario("Camila", "Araya Conejo", 61963811, "Cartago  Guadalupe", LocalDate.of(2024, 10, 8), "305610469", "2410", 85,correo);
         Inspector inspector = new Inspector("Teodoro", "Araya Conejo", 78456456, "Cartago  Guadalupe", LocalDate.of(2024, 10, 8), "308950456", "2410", "78", correo);
         Persona admin = new Administrador("Antonio", "Araya Conejo", 89667412, "Cartago  Guadalupe", LocalDate.of(2023, 10, 8), "30964089", "8952", correo);
         
         
         Vehiculo v1 = new Vehiculo("123456", (Usuario)usuario);
         Vehiculo v2 = new Vehiculo("234678", (Usuario)usuario);
         
         Multa multa = new Multa(1000, "aaa", LocalDateTime.now(),usuario,"123456",inspector);
         Multa multa2 = new Multa(2000, "addddaa", LocalDateTime.now(),usuario,"123456",inspector);
         parqueo.agregarMulta(multa);
         parqueo.agregarMulta(multa2);
         v1.agregarMulta(multa);
         v1.agregarMulta(multa2);
         
         usuario.agregarVehiculo(v2);
        usuario.agregarVehiculo(v1);
         
         System.out.println(usuario.getVehiculos().isEmpty());
         long numero = 1234567891234567L;
         Tarjeta tarjeta = new Tarjeta(numero, 12,2024,123);
         usuario.setTarjeta(tarjeta);
         
         personas.add(usuario);
         personas.add(inspector);
         personas.add(admin);*/
        

         
 /*ArrayList<Persona> personas = new ArrayList<Persona>();
 Administrador  usuaario = new Administrador();
    Inspector ins = new Inspector();
    Usuario user= new Usuario();
       Login login = new Login();*/
  /** try{
            Login.guardarUsuarios("listaUsuarios.txt", personas);
        }
        catch (IOException e){
            System.out.println("El archivo no se logro abrir");
        }*/
        
      /* personas = Login.cargarUsuarios("listaUsuarios.txt");
         
        for(Persona ob : personas){
         
            System.out.println(ob.getApellidos());
             if(ob instanceof Administrador usuarioCast){
                 usuaario = usuarioCast;
             }
             if(ob instanceof Usuario usuarioCast){
                 user = usuarioCast;
             }
              if(ob instanceof Inspector usuarioCast){
                 ins = usuarioCast;
             }
         }*/
        
   // MenuUsuario menuUser = new MenuUsuario(user);
  //menuUser.setVisible(true);
         
         
         
//MenuAdministrador menuIns = new MenuAdministrador(usuaario);
//menuIns.setVisible(true);
     /*Parqueo p = new Parqueo(); 
      p.lecturaArchivo();
      MenuInspector menuIns  = new MenuInspector(ins,p);
      menuIns.setVisible(true);*/
     //Correo correo = new Correo("correo", "gmail.com");
  //   Persona admin = new Administrador("Usuario", "Administrador", 12345678, "-", LocalDate.now(), "123456789", "8952", correo);
     
     LoginJFrame login = new LoginJFrame();
     login.setVisible(true);
     
     
    }
}
