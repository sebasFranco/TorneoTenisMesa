/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.apostador;

import db.PartidoDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Partido;
import services.ValidarSesion;

/**
 *
 * @author rm-rf
 */
@WebServlet(name = "ConsultarPartidos", urlPatterns = {"/Apostador/ConsultarPartidos"})
public class ConsultarPartidos extends HttpServlet {
    
    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(ConsultarPartidos.class.getName());
    
    private PartidoDB partidoDB = new PartidoDB();

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
        LOGGER.log(Level.INFO, "Consultando los torneos de apostador");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int tipo = ValidarSesion.getTipoUsuarioSesion(session);
        LOGGER.log(Level.INFO, "Tipo de usuario {0}", tipo);
        request.setAttribute("tipo",tipo);
        String torneo = request.getParameter("idTorneo");
        LOGGER.log(Level.INFO, "Torneo {0}", torneo);
        
        ArrayList<Partido> torneos = partidoDB.getAllPartidos(Integer.parseInt(torneo));
        LOGGER.log(Level.INFO, "Se retorna la lista con los siguientes valores {0}", torneos);
        request.setAttribute("partidos", torneos);
        request.getRequestDispatcher("/apostador/consultarPartidos.jsp").forward(request, response);
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
