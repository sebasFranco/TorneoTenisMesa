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
import java.util.List;
import java.util.logging.Level;
import modelo.Apuesta;
import modelo.Usuario;
import services.DBManager;

/**
 *
 * @author rm-rf
 */
public class ApuestaDB {
    
    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(ApuestaDB.class.getName());
    
    private final String TABLE_NAME = "apuesta";
    private final String ID_FIELD = "idApuesta";
    private final String FIELDS = "idUsuario, idPartido, estado, valor, fechaCreacion, fechaCierre, ganador, puntaje";
    private final String SQL_INSERT = "INSERT INTO " + TABLE_NAME +" ("+FIELDS+") VALUES (?,?,?,?,?,?,?,?)";
    private final String SQL_INSERT_ID = "SELECT @@identity AS id";
    private final String SQL_UPDATE = "UPDATE "+TABLE_NAME+" SET idUsuario=?, idPartido=?, estado=?, valor=?, fechaCierre=?, ganador=?, puntaje=? WHERE "+ID_FIELD+"=?";
    private final String SQL_SELECT_ID = "SELECT "+FIELDS+" FROM " + TABLE_NAME + " WHERE "+ID_FIELD+"=?";
    private final String SQL_SELECT_USER = "SELECT "+ID_FIELD + "," +FIELDS+" FROM " + TABLE_NAME + " WHERE idUsuario=?";

    public ApuestaDB() {
    }
    
    public int insert(Apuesta apuesta){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
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
            statement.setString(7, apuesta.getGanador());
            statement.setString(8, apuesta.getPuntaje());
            
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
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return idApuesta;
    }
    
    public int update(Apuesta apuesta){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, apuesta.getIdUsuario());
            statement.setInt(2, apuesta.getIdPartido());
            statement.setString(3, apuesta.getEstado());
            statement.setInt(4, apuesta.getValor());
            statement.setObject(5, apuesta.getFechaCierre());
            statement.setString(6, apuesta.getGanador());
            statement.setString(7, apuesta.getPuntaje());
            
            statement.setInt(8, apuesta.getIdApuesta());
            LOGGER.log(Level.INFO, "Ejecutando query " + SQL_INSERT);
            rows = statement.executeUpdate();
            LOGGER.log(Level.INFO, "Registro afectados {0}", rows);
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "insert", ex);
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return rows;
    }
    
    
    public java.util.List<Apuesta> getApuestasByUser(int idUser){
        java.util.List<Apuesta> list = new java.util.Stack<Apuesta>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Apuesta apuesta = null;
        
        try {
            connection = DBManager.getConnection();
            LOGGER.log(Level.INFO, "Ejecutando el query " +SQL_SELECT_USER);
            statement = connection.prepareStatement(SQL_SELECT_USER);
            statement.setInt(1, idUser);
            rs = statement.executeQuery();
            while(rs.next()){
                //idUsuario, idPartido, estado, valor, fechaCreacion, fechaCierre
                apuesta = new Apuesta();
                apuesta.setEstado(rs.getString("estado"));
                apuesta.setFechaCierre(rs.getDate("fechaCierre"));
                apuesta.setFechaCreacion(rs.getDate("fechaCreacion"));
                apuesta.setIdApuesta(rs.getInt("idApuesta"));
                apuesta.setIdPartido(rs.getInt("idPartido"));
                apuesta.setIdUsuario(rs.getInt("idUsuario"));
                apuesta.setValor(rs.getInt("valor"));
                apuesta.setGanador(rs.getString("ganador"));
                apuesta.setPuntaje(rs.getString("puntaje"));
                
                list.add(apuesta);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "getApuestasByUser", ex);
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        
        return list;
    }
    
    public Apuesta getById(int idApuesta){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Apuesta apuesta = null;
        
        try {
            connection = DBManager.getConnection();
            LOGGER.log(Level.INFO, "Ejecutando el query " +SQL_SELECT_ID);
            statement = connection.prepareStatement(SQL_SELECT_ID);
            statement.setInt(1, idApuesta);
            rs = statement.executeQuery();
            while(rs.next()){
                //idUsuario, idPartido, estado, valor, fechaCreacion, fechaCierre
                apuesta = new Apuesta();
                apuesta.setEstado(rs.getString("estado"));
                apuesta.setFechaCierre(rs.getDate("fechaCierre"));
                apuesta.setFechaCreacion(rs.getDate("fechaCreacion"));
                apuesta.setIdApuesta(idApuesta);
                apuesta.setIdPartido(rs.getInt("idPartido"));
                apuesta.setIdUsuario(rs.getInt("idUsuario"));
                apuesta.setValor(rs.getInt("valor"));
                apuesta.setGanador(rs.getString("ganador"));
                apuesta.setPuntaje(rs.getString("puntaje"));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "getById", ex);
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        
        return apuesta;
    }

    public List<Usuario> infoPartido(int partido) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        java.util.List<Usuario> usuarios = new java.util.Stack<Usuario>();
        
        try {
            connection = DBManager.getConnection();
            String sql = "select ust.idUsuario idUsuario, ust.cedula cedula, ust.apellido apellido, ust.nombre nombre from usuariopartido usp left join usuario ust on usp.idUsuario = ust.idUsuario where usp.idPartido = ? and ust.tipo = 'Jugador'";
            LOGGER.log(Level.INFO, "Ejecutando el query {0}", sql);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, partido);
            rs = statement.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCedula(rs.getString("cedula"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "infoPartido", ex);
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        
        return usuarios;
    }
    
}
