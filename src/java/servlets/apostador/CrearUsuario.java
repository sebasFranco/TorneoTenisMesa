/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.apostador;

import db.UsuarioDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author rm-rf
 */
@WebServlet(
        name = "APCrearUsuario",
        urlPatterns = {"/Apostador/CrearUsuario"},
        description = "Servlet to create a user Apostador"
)
public class CrearUsuario extends HttpServlet {

    private final static UsuarioDB USUARIO_DB = new UsuarioDB();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String tipo = Usuario.TIPO_APOSTADOR;
        if (tipo != null) {
            Usuario usuario = new Usuario();
            usuario.setCedula(request.getParameter("cc"));
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setNombreUsuario(request.getParameter("nombreUsuario"));
            usuario.setClave(request.getParameter("clave"));
            usuario.setTelefono(request.getParameter("telefono"));
            usuario.setTipo(tipo);
            int idUsuario = USUARIO_DB.insert(usuario);
            request.setAttribute("idUsuario", idUsuario);
        }
        request.getRequestDispatcher("/apostador/crearUsuarioVista.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    

}
