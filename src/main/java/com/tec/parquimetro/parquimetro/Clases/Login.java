/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File; //verificar si un archivo existe
//guardar archivos
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
//cargar archivos
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author carol_flgngfy
 */
public class Login {
    private ArrayList<Persona> listaUsuarios;
    
    //constructor
    public Login() {
     listaUsuarios = new ArrayList<>(); 
    }
    
    //setters 
    public void setListaUsuarios(ArrayList<Persona> listaUsuarios){
        this.listaUsuarios = listaUsuarios;
    }
    
    //getters
    public ArrayList<Persona> getListaUsuarios(){
        return listaUsuarios;
    }
    
    //funcion existeParametros.txt: esta funcion comprueba si existe el archivo Parametros.txt
    public boolean existeParametros(){
        File file = new File("Parametros.txt");
        return file.exists();
    }
    
    //funcion verificarIdentificacion: esta funcion se encarga de verificar si la identificacion ingresada existe:
    public Persona verificarIdentificacion(String identificacion){
        for (Persona persona: listaUsuarios){
            if (persona.getIdentificacion() == null){ //no hacer nada
            }
            else if (persona.getIdentificacion().equals(identificacion)){
                return persona;
            }
        }
        return null;
    }
    
    //funcion verificarContraseña: esta funcion se encarga verificar si el pin ingresado coincide con el del usuario:
    public boolean verificarPin(String pin, Persona persona){
        if (persona.getPin().equals(pin)){
            return true;
        }
        return false;
        }

    
    //funcion registrarse(): permite registrar a los usuarios
    public void registrarse(String nombre, String apellido, Correo correo, String direccionFisica, int telefono, Tarjeta tarjeta, String identificacion, String pin){
        //Usuario usuario = new Usuario(nombre, apellido, correo, direccionFisica, telefono, tarjeta, identificacion, pin);
        //listaUsuarios.add(usuario);
        //inicia sesion automaticamente despues de registrarse
        //
    }
    //BORRARRR
    public void crear(){
        Administrador adminOficial = new Administrador("Admin prueba", "apellido", 123456, "vive en su casa", LocalDate.now(), "1234", "1234");

        listaUsuarios.add(adminOficial);
        try{
            guardarUsuarios("listaUsuarios.txt", listaUsuarios);
        }
        catch (IOException e){
            System.out.println("malmamlal");
        }
        
    }
    
    //funcion iniciarSesion(): permite al usuario/admins/inspectores ingresar a la app
    public void iniciarSesion(){
        //llama a la app del usuario
    }
    
    
    //funcion guardarUsuarios: guarda la lista de personas
    //recibe el nombre del archivo donde se guardará la lista y la lista a guardar, no retorna nada
    public static void guardarUsuarios(String nombreArchivo, ArrayList<Persona> listaAGuardar) throws IOException {
        ObjectOutputStream oos = null; //crea el objeto ObjectOutPutStream util para cargar archivos
        try {
            oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo)); //Se abre o crea nombreArchivo, ObjectOutputStream convierte el objeto en bytes y FileOutputStream escribe bytes en el archivo. 
            oos.writeObject(listaAGuardar); //Escribe sobre el archivo abierto la lista: listaAGuardar
            System.out.println("El archivo se guardo correctamente");
        } 
        finally { //captura de la excepcion EOF
            if (oos != null) { //si se llega al fin del archivo:
                try {
                    oos.close(); //se cierra
                } 
                catch (IOException e) { //hubo un error
                    System.out.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
    }
    
    //funcion cargarUsuarios: carga la lista de estudiantes. Retorna la lista de personas
    public static ArrayList<Persona> cargarUsuarios(String nombreArchivo) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null; //crea el objeto ObjectInputStream util para leer archivos
        try {
            ois = new ObjectInputStream(new FileInputStream(nombreArchivo)); //se inicializa el objeto y guarda el archivo "nombreArchivo"
            Object objeto = ois.readObject(); //lee el archivo guardado en ois y devuelve un tipo "Object"
            ArrayList<Persona> resultado = (ArrayList<Persona>) objeto; //se realiza un casting de objeto a ArrayList<Estudiante>
            System.out.println("El archivo se cargo correctamente");
            return resultado;
                    
        } 
        finally {
            if (ois != null) { //si ois se utilizó
                try {
                    ois.close(); //lo cierra
                } 
                catch (IOException e) {
                    System.out.println("Error al cerrar ObjectInputStream: " + e.getMessage());
                }
            }
        }
    }
    
    public void actualizarPersona(Persona usuario, String identificacion){
        
         try{
             try {
                 this.setListaUsuarios(cargarUsuarios("listaUsuarios.dat"));
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        catch (IOException e){
            System.out.println("malmamlal");
        }
        
        for(Persona obj : listaUsuarios){
        
            
            if(obj.getIdentificacion().equals(usuario.getIdentificacion())){
                System.out.println("holaaaa");
                listaUsuarios.remove(obj);
                listaUsuarios.add(usuario);
                break;
            }
        
        }
        try{
            guardarUsuarios("listaUsuarios.dat", listaUsuarios);
        }
        catch (IOException e){
            System.out.println("malmamlal");
        }
        
    }
    
}
