/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import db.TorneoDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Estructura;
import modelo.FactoriaEstructura;
import modelo.Torneo;

/**
 *
 * @author DELL
 */
public class CrearTorneoCtrl extends HttpServlet {
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
        Torneo torneo = new Torneo();
        String nombre,tipoEstructura,numeroJugadores,numeroMesas;
        int cantidadJugadores = 0, cantidadMesas = 0, tipoEstructuraInt = 0, idTorneo;
        nombre = request.getParameter("nombre");
        tipoEstructura = request.getParameter("estructura");
        numeroJugadores = request.getParameter("numeroJugadores");
        numeroMesas = request.getParameter("numeroMesas");
        try {
            cantidadJugadores = Integer.parseInt(numeroJugadores);
            cantidadMesas = Integer.parseInt(numeroMesas);
            tipoEstructuraInt = Integer.parseInt(tipoEstructura);
        } catch (NumberFormatException e) {
        }
        
        torneo.setNombre(nombre);
        torneo.setCantidadJugadores(cantidadJugadores);
        torneo.setCantidadMesas(cantidadMesas);
        Estructura estructura = FactoriaEstructura.getEstructura(tipoEstructuraInt);
        torneo.setEstructura(estructura);
        
        idTorneo = torneoDB.insert(torneo);
        
        if (idTorneo > 0) {
            session.setAttribute("torneoSession", torneo);
            response.sendRedirect("/TorneoTenisMesa/admin/UsuariosTorneoCtrl");
            return;
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
