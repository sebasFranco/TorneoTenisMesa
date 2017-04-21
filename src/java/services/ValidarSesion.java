/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author Sebastián
 */
public class ValidarSesion {
    
    public static Boolean validar(HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        
        if(usuario==null){
          return false ;     
        }else{
            return true;
        }
        
    }
    
    public static int getTipoUsuarioSesion(HttpSession session){
        int tipo = 0;
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario != null) {
            if (usuario.getTipo().equals("Jugador")) tipo=1;
            if (usuario.getTipo().equals("Arbitro")) tipo=2;
            if (usuario.getTipo().equals("Administrador")) tipo=3;
            if (usuario.getTipo().equals("Apostador")) tipo=4;
        }
        return tipo;
    }
}
