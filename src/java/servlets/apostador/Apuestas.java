/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.apostador;

import db.ApuestaDB;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Apuesta;
import modelo.Usuario;
import services.ValidarSesion;

/**
 *
 * @author rm-rf
 */
@WebServlet(name = "Apuestas", urlPatterns = {"/Apostador/Apuestas"})
public class Apuestas extends HttpServlet {
    
    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Apuestas.class.getName());
    
    private ApuestaDB apuestaDB = new ApuestaDB();

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
        LOGGER.log(Level.INFO, "Consultando las apuestas");
        HttpSession session = request.getSession();
        boolean isValid = ValidarSesion.validar(session);
        LOGGER.log(Level.INFO, "Tipo de usuario {0}", isValid);
        if (isValid){
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            java.util.List<Apuesta> lista = apuestaDB.getApuestasByUser(usuario.getIdUsuario());
            LOGGER.log(Level.INFO, "Se retorna la lista con los siguientes valores {0}", lista);
            request.setAttribute("apuestas", lista);
            request.getRequestDispatcher("/apostador/consultarApuestas.jsp").forward(request, response);
        }else{
            request.getSession().invalidate();
            request.getRequestDispatcher("/").forward(request, response);
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
        //request.getRequestDispatcher("/apostador/consultarApuestas.jsp").forward(request, response);
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
