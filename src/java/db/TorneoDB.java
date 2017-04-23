/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Estructura;
import modelo.FactoriaEstructura;
import modelo.Torneo;
import modelo.Usuario;
import services.DBManager;

/**
 *
 * @author DELL
 */
public class TorneoDB {
    private final String TABLE_NAME = "Torneo";
    private final String SQL_INSERT = "INSERT INTO " + TABLE_NAME +" (nombre, idEstructura, cantidadJugadores, cantidadMesas) VALUES (?,?,?,?)";
    private final String SQL_INSERT_ID = "SELECT @@identity AS id";
    private final String SQL_TORNEOS = "SELECT t.*,e.nombre FROM " + TABLE_NAME + " t JOIN estructura e ON e.idEstructura = t.idEstructura";

    public TorneoDB() {
    }
    
    public int insert(Torneo torneo) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs;
        int rows, idTorneo = 0;
        try {
            int index = 1;
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(index++, torneo.getNombre());
            statement.setInt(index++, torneo.getEstructura().getIdEstructura());
            statement.setInt(index++, torneo.getCantidadJugadores());
            statement.setInt(index++, torneo.getCantidadMesas());
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = statement.executeUpdate();
            System.out.println("Registros Afectados :" + rows);
            /**
             * Obtiene el id del torneo que se acabo de insertar
             */
            statement = connection.prepareStatement(SQL_INSERT_ID);
            rs = statement.executeQuery();
            rs.next();
            idTorneo = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return idTorneo;
    }
    
    public int definirUsuarios(ArrayList<Usuario> jugadores,ArrayList<Usuario> arbitros) {
        return 0;
    }
    
    public ArrayList<Torneo> getAllTorneos(){
        ArrayList<Torneo> torneos = new ArrayList<Torneo>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Torneo torneo;
        Estructura estructura;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_TORNEOS);
            statement = connection.prepareStatement(SQL_TORNEOS);
            rs = statement.executeQuery();
            int i;
            while(rs.next()){
                i=1;
                torneo = new Torneo();
                torneo.setIdTorneo(rs.getInt(i++));
                torneo.setNombre(rs.getString(i++));
                estructura = FactoriaEstructura.getEstructura(rs.getInt(i++));
                torneo.setCantidadJugadores(rs.getInt(i++));
                torneo.setCantidadMesas(rs.getInt(i++));
                estructura.setNombre(rs.getString(rs.getInt(i++)));
                torneo.setEstructura(estructura);
                
                torneos.add(torneo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return torneos;
    }
}
