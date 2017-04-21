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
import modelo.Torneo;
import services.DBManager;

/**
 *
 * @author DELL
 */
public class TorneoDB {
    private final String TABLE_NAME = "Torneo";
    private final String SQL_INSERT = "INSERT INTO " + TABLE_NAME +" (nombre,apellido,cedula,estado,nombreUsuario,clave,tipo,telefono) VALUES (?,?,?,?,?,?,?,?)";
    private final String SQL_INSERT_ID = "SELECT @@identity AS id";

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
            statement.setString(index++, "");
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = statement.executeUpdate();
            System.out.println("Registros Afectados :" + rows);
            /**
             * Obtiene el id del cliente que se acabo de insertar
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
}
