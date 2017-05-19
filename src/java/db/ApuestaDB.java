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
import java.util.logging.Level;
import modelo.Apuesta;
import services.DBManager;

/**
 *
 * @author rm-rf
 */
public class ApuestaDB {
    
    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(ApuestaDB.class.getName());
    
    private final String TABLE_NAME = "apuesta";
    private final String ID_FIELD = "idApuesta";
    private final String FIELDS = "idUsuario, idPartido, estado, valor, fechaCreacion, fechaCierre";
    private final String SQL_INSERT = "INSERT INTO " + TABLE_NAME +" ("+FIELDS+") VALUES (?,?,?,?,?,?)";
    private final String SQL_INSERT_ID = "SELECT @@identity AS id";
    private final String SQL_UPDATE = "UPDATE "+TABLE_NAME+" SET estado=?, valor=?, fechaCreacion=?, fechaCierre=? WHERE "+ID_FIELD+"=?";
    private final String SQL_SELECT_ID = "SELECT "+FIELDS+" FROM " + TABLE_NAME + " WHERE "+ID_FIELD+"=?";

    public ApuestaDB() {
    }
    
    public int insert(Apuesta apuesta){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs;
        int rows, idApuesta = 0;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setInt(1, apuesta.getIdUsuario());
            statement.setInt(2, apuesta.getIdPartido());
            statement.setString(3, apuesta.getEstado());
            statement.setInt(4, apuesta.getValor());
            statement.setObject(5, apuesta.getFechaCreacion());
            statement.setObject(6, apuesta.getFechaCierre());
            
            LOGGER.log(Level.INFO, "Ejecutando query " + SQL_INSERT);
            rows = statement.executeUpdate();
            LOGGER.log(Level.INFO, "Registro afectados {0}", rows);
            
            statement = connection.prepareStatement(SQL_INSERT_ID);
            rs = statement.executeQuery();
            rs.next();
            idApuesta = rs.getInt(1);
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "insert", ex);
        }finally{
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return idApuesta;
    }
    
}
