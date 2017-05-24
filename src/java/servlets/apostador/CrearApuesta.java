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
@WebServlet(name = "CrearApuesta", urlPatterns = {"/Apostador/CrearApuesta"})
public class CrearApuesta extends HttpServlet {
    
    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CrearApuesta.class.getName());
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
        HttpSession session = request.getSession();
        boolean sessionValida = ValidarSesion.validar(session);
        LOGGER.log(Level.INFO, "Tipo de usuario {0}", sessionValida);
        request.setAttribute("tipo",sessionValida);
        if (sessionValida){
            String partido = request.getParameter("idPartido");
            LOGGER.log(Level.INFO, "Partido {0}", partido);

            if (request.getParameter("apuesta") != null){
                String valApuesta = request.getParameter("apuesta");
                String valGanador = request.getParameter("ganador");
                String valPuntaje = request.getParameter("puntaje");
                LOGGER.log(Level.INFO, "Valor de la apuesta {0}, ganador {1} y puntaje {2}", new Object[]{valApuesta, valGanador, valPuntaje});
                Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
                Apuesta apuesta = new Apuesta();
                //enum('Abierta','Cerrada','Anulada')
                apuesta.setEstado("Abierta");
                apuesta.setFechaCreacion(new java.util.Date());
                apuesta.setIdPartido(Integer.parseInt(partido));
                apuesta.setIdUsuario(usuario.getIdUsuario());
                apuesta.setValor(Integer.parseInt(valApuesta));
                apuesta.setGanador(valGanador);
                apuesta.setPuntaje(valPuntaje);
                
                int idAp = apuestaDB.insert(apuesta);
                
                request.setAttribute("idApuesta", idAp);
                request.getRequestDispatcher("/Apostador/Apuestas").forward(request, response);
            }else{
                java.util.List<Usuario> data = apuestaDB.infoPartido(Integer.parseInt(partido));
                request.setAttribute("usuario1", data.get(0));
                request.setAttribute("usuario2", data.get(1));
                request.getRequestDispatcher("/apostador/crearApuesta.jsp").forward(request, response);
            }

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
