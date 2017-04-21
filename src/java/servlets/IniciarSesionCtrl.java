/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import db.UsuarioDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author DELL
 */
public class IniciarSesionCtrl extends HttpServlet {
    UsuarioDB usuarioDB =  new UsuarioDB();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String tipo = request.getParameter("tipo");
        HttpSession session = request.getSession();
        if (tipo != null ){
            int tipoUsuario = Integer.parseInt(tipo);
            String nombreUsuario = request.getParameter("nombreUsuario");
            String clave = request.getParameter("clave");
            String url = null;
            switch(tipoUsuario){
                case 1://Jugador
                    tipo = Usuario.TIPO_JUGADOR;
                    url = "/jugador/homeJugadorVista.jsp";
                    break;
                case 2://Arbitro
                    tipo = Usuario.TIPO_ARBITRO;
                    url = "/arbitro/homeArbitroVista.jsp";
                break;
                case 3://Administrador
                    tipo = Usuario.TIPO_ADMINISTRADOR;
                    url = "/admin/homeAdminVista.jsp";
                break;
                    case 4://Apostador
                    tipo = Usuario.TIPO_APOSTADOR;
                    url = "/apostador/homeApostadorVista.jsp";
                break;
                default:
                    break;
            }
            Usuario usuario = usuarioDB.buscarUsuario(nombreUsuario, clave, tipo);
            if (usuario instanceof Usuario){
                session.setAttribute("usuarioLogueado", usuario);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                request.setAttribute("tipoUsr", request.getParameter("tipoUsuario"));
                request.setAttribute("errorLogin", "Nombre de usuario o clave incorrectos, intente de nuevo");
                request.getRequestDispatcher("/iniciarSesion.jsp").forward(request, response);
            }
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
