/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import db.TorneoDB;
import db.UsuarioDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Torneo;
import services.ValidarSesion;

/**
 *
 * @author DELL
 */
public class ConsultarTorneoCtrl extends HttpServlet {
    TorneoDB torneoDB = new TorneoDB();

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
        HttpSession session = request.getSession();
        int idTorneo = 0;
        int tipo = 0;
        Torneo torneo;
        try {
            idTorneo = Integer.parseInt(request.getParameter("idTorneo"));
        } catch (NumberFormatException e) {
            tipo = ValidarSesion.getTipoUsuarioSesion(session);
            request.setAttribute("tipo",tipo);
        }
        if (idTorneo > 0) {
            torneo = torneoDB.buscarTorneo(idTorneo);
        } else {
            response.sendRedirect("/TorneoTenisMesa/Admin/ConsultarTorneosCtrl");
            return;
        }
        
        request.setAttribute("torneo",torneo);
        request.getRequestDispatcher("/consultarTorneoVista.jsp").forward(request, response);
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
