/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.parquimetro.parquimetro.Clases;
import static com.tec.parquimetro.parquimetro.GUI.MenuUsuario.usuario;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File; //verificar si un archivo existe
//guardar archivos
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
//cargar archivos
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * La clase Login maneja la autenticación de usuarios y la gestión de la lista de usuarios.
 */
public class Login {
    //atributos
    private ArrayList<Persona> listaUsuarios;
    
    /**
     * Constructor por defecto para la clase Login.
     * Inicializa la lista de usuarios y carga los usuarios desde el archivo "listaUsuarios.txt" si existe.
     */
    public Login() {
     listaUsuarios = new ArrayList<>(); 
     if (cargarUsuarios("listaUsuarios.txt") != null){
        listaUsuarios= cargarUsuarios("listaUsuarios.txt");
     }
    }
    
    //setters y getters

     /**
     * Establece la lista de usuarios.
     *
     * @param listaUsuarios La nueva lista de usuarios.
     */
    public void setListaUsuarios(ArrayList<Persona> listaUsuarios){
        this.listaUsuarios = listaUsuarios;
    }
     
    /**
     * Obtiene la lista de usuarios.
     *
     * @return La lista de usuarios.
     */
    public ArrayList<Persona> getListaUsuarios(){
        return listaUsuarios;
    }
    
    /**
     * Comprueba si existe el archivo "Parametros.txt".
     *
     * @return true si el archivo existe, false en caso contrario.
     */
    public boolean existeParametros(){
        File file = new File("Parametros.txt");
        return file.exists();
    }
    
    /**
     * Verifica si la identificación ingresada existe en la lista de usuarios.
     *
     * @param identificacion La identificación a verificar.
     * @return La persona con la identificación especificada, o null si no se encuentra.
     */
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
    
    /**
     * Verifica si el correo ingresado existe en la lista de usuarios.
     *
     * @param correo El correo a verificar.
     * @return La persona con el correo especificado, o null si no se encuentra.
     */
    public Persona verificarCorreo(Correo correo){
        for (Persona persona: listaUsuarios){
            if (persona.getCorreo()== null){ //no hacer nada
            }
            else if (persona.getCorreo().getCorreo().equals(correo.getCorreo())){
                return persona;
            }
        }
        return null;
    }
    
    /**
     * Verifica si la placa ingresada ya existe en la lista de usuarios.
     *
     * @param placa La placa a verificar.
     * @return true si la placa ya existe, false en caso contrario.
     */
    public boolean verificarPlaca(String placa){
        for (Persona persona: listaUsuarios){
            if (persona instanceof Usuario usuarioCast){
            
                if(usuarioCast.getVehiculos()!=null){
                
                    for(Vehiculo v : usuarioCast.getVehiculos()){
                        if(v.getPlaca().equals(placa))
                            return true;
                    }
                }
                
            }
        }
        return false;
    }
    
    /**
     * Verifica si el PIN ingresado coincide con el del usuario.
     *
     * @param pin El PIN a verificar.
     * @param persona La persona cuyo PIN se va a verificar.
     * @return true si el PIN coincide, false en caso contrario.
     */
    public boolean verificarPin(String pin, Persona persona){
        if (persona.getPin().equals(pin)){
            return true;
        }
        System.out.println(pin + " " + persona.getPin());

        return false;
        }

    
    /**
     * Crea un administrador de prueba y lo guarda en la lista de usuarios. BORRAR
     */
    public void crear(){
        Correo correo = new Correo("dilanhernandez48", "gmail.com");
        Administrador adminOficial = new Administrador("Admin prueba", "apellido del admin", 123456, "vive en su casa", LocalDate.now(), "1234", "1234", correo);

        listaUsuarios.add(adminOficial);
        try{
            guardarUsuarios("listaUsuarios.txt", listaUsuarios);
        }
        catch (IOException e){
            System.out.println("malmamlal");
        }
        
    }
    
    /**
     * Guarda la lista de usuarios en un archivo.
     *
     * @param nombreArchivo El nombre del archivo en el cual guardar los usuarios.
     * @param listaAGuardar La lista de usuarios a guardar.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
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
    
    /**
     * Carga la lista de usuarios desde un archivo.
     *
     * @param nombreArchivo El nombre del archivo desde el cual cargar los usuarios.
     * @return La lista de usuarios cargada desde el archivo.
     */
    public static ArrayList<Persona> cargarUsuarios(String nombreArchivo){
        ObjectInputStream ois = null; //crea el objeto ObjectInputStream util para leer archivos
        try {
            ois = new ObjectInputStream(new FileInputStream(nombreArchivo)); //se inicializa el objeto y guarda el archivo "nombreArchivo"
            Object objeto = ois.readObject(); //lee el archivo guardado en ois y devuelve un tipo "Object"
            ArrayList<Persona> resultado = (ArrayList<Persona>) objeto; //se realiza un casting de objeto a ArrayList<Estudiante>
            System.out.println("El archivo se cargo correctamente");
            return resultado;
                    
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }        finally {
            if (ois != null) { //si ois se utilizó
                try {
                    ois.close(); //lo cierra
                } 
                catch (IOException e) {
                    System.out.println("Error al cerrar ObjectInputStream: " + e.getMessage());
                }
            }
        }
        return null;
    }
    
    /**
     * Actualiza la información de una persona en la lista de usuarios.
     *
     * @param usuario La persona con la información actualizada.
     * @param identificacion La identificación de la persona a actualizar.
     */
    public void actualizarPersona(Persona usuario, String identificacion){
        
         this.setListaUsuarios(cargarUsuarios("listaUsuarios.txt"));
        
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Persona obj = listaUsuarios.get(i);

            // Busca el usuario que coincide con la identificación
            if (obj.getIdentificacion().equals(usuario.getIdentificacion())) {
                // Actualiza el objeto en la lista
                listaUsuarios.set(i, usuario);  // Reemplaza el objeto en la lista
                System.out.println("Usuario encontrado y actualizado:");
                System.out.println(usuario.getApellidos());
                break;  // Rompe el bucle después de encontrar el usuario
            }
        }

        
        
        try{
            guardarUsuarios("listaUsuarios.txt", listaUsuarios);
            
        }
        catch (IOException e){
            System.out.println("malmamlal");
        }
        
    }
    
    /**
     * Elimina una persona de la lista de usuarios basado en su identificación.
     *
     * @param identificacion La identificación de la persona a eliminar.
     * @return true si la persona fue eliminada, false en caso contrario.
     */
    public boolean eliminarPersona(String identificacion){
        
       this.setListaUsuarios(cargarUsuarios("listaUsuarios.txt"));
        
         Persona persona = new Persona();
         //busca dentro de la lista el usuario
        for(Persona obj : listaUsuarios){
            //busca el usuario que coincide con la identificacion
            if(obj.getIdentificacion().equals(identificacion)){
                //remueve el usuario de la lista
                persona=obj;
                break;
            }
        
        }
        if(persona!=null){
            listaUsuarios.remove(persona);
            
             try{
                guardarUsuarios("listaUsuarios.txt", listaUsuarios);
            }
            catch (IOException e){
                System.out.println("El archivo no se logro abrir");
            }
            
            return true;
        }

        return false;
    }
    
    /**
     * Busca si una placa ya existe en la lista de usuarios.
     *
     * @param placa La placa a buscar.
     * @return true si la placa ya existe, false en caso contrario.
     */
   public boolean buscarPlaca(String placa){
        
       this.setListaUsuarios(cargarUsuarios("listaUsuarios.txt"));
        
         Persona persona = new Persona();
         //busca dentro de la lista el usuario
        for(Persona obj : listaUsuarios){

             if(obj instanceof Usuario usuarioCast){
                 
                 for(Vehiculo v : usuarioCast.getVehiculos()){
                     if(v.getPlaca().equals(placa)){
                         return true;
                     }
                 }
                 
             }
        
        }
       
        return false;
    }
    
    /**
     * Agrega una persona a la lista de usuarios.
     *
     * @param usuario La persona a agregar.
     */
    public void agregarPersona(Persona usuario){
        
        this.setListaUsuarios(cargarUsuarios("listaUsuarios.txt")); 
         
        this.listaUsuarios.add(usuario);
        
        try{
            System.out.println(usuario.getCorreo().getCorreo() + "dd");
            System.out.println("yaa");
            guardarUsuarios("listaUsuarios.txt", listaUsuarios);
        }
        catch (IOException e){
            System.out.println("malmamlal");
        }
        
    }
}
