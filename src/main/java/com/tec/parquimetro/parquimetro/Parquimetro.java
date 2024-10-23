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
 
     LoginJFrame login = new LoginJFrame();
     login.setVisible(true);
    }
}
