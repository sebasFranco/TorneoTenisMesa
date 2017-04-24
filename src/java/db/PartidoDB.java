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
import java.util.Arrays;
import java.util.Scanner;
import modelo.Estructura;
import modelo.FactoriaEstructura;
import modelo.Partido;
import modelo.Torneo;
import services.DBManager;

/**
 *
 * @author DELL
 */
public class PartidoDB {
    private final String TABLE_NAME = "Partido";
    private final String SQL_INSERT = "INSERT INTO " + TABLE_NAME +" (nombre, idEstructura, cantidadJugadores, cantidadMesas) VALUES (?,?,?,?)";
    private final String SQL_PARTIDOS = "SELECT p.idPartido, p.fechaHora, p.idPartidoTorneo," +
"	group_concat(up.idUsuario order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') usuarios," +
"    group_concat(up.resultado order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') resultados," +
"    group_concat(u.tipo order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') tipos" +
" FROM " + TABLE_NAME + " p" +
" join usuarioPartido up on up.idPartido = p.idPartido" +
" join usuario u on u.idUsuario = up.idUsuario" +
" where p.idTorneo = ?" +
" group by p.idPartido";
    private final String SQL_PARTIDO = "SELECT p.idPartido, p.fechaHora, p.idPartidoTorneo," +
"	group_concat(up.idUsuario order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') usuarios," +
"    group_concat(up.resultado order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') resultados," +
"    group_concat(u.tipo order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') tipos" +
" FROM " + TABLE_NAME + " p" +
" join usuarioPartido up on up.idPartido = p.idPartido" +
" join usuario u on u.idUsuario = up.idUsuario" +
" where up.idPartido = ?" +
" group by p.idPartido";
/**
 * Crear consulta para traer un partido
 */
    private final String SQL_SELECT_ID = "SELECT t.*,e.nombre FROM " + TABLE_NAME + "  t JOIN estructura e ON e.idEstructura = t.idEstructura WHERE t.idTorneo = ?";

    public PartidoDB() {
    }
    
    public int insert(Partido partido){
        return 0;
    }
    
    public ArrayList<Partido> getAllPartidos(int idTorneo){
        ArrayList<Partido> partidos = new ArrayList<Partido>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Partido partido;
        ArrayList<Integer> idUsuarios, resultados;
        ArrayList<String> tipos;
        String resultadosString;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_PARTIDOS);
            statement = connection.prepareStatement(SQL_PARTIDOS);
            statement.setInt(1, idTorneo);
            rs = statement.executeQuery();
            int i;
            while(rs.next()){
                idUsuarios = new ArrayList<Integer>();
                resultados = new ArrayList<Integer>();
                i = 1;
                partido = new Partido();
                partido.setIdPartido(rs.getInt(i++));
                partido.setFechaHora(rs.getDate(i++));
                partido.setIdPartidoTorneo(rs.getInt(i++));
                Scanner scanner = new Scanner(rs.getString(i++));
                while (scanner.hasNextInt()) {
                    idUsuarios.add(scanner.nextInt());
                }
                resultadosString = rs.getString(i++);
                if (resultadosString != null && !resultadosString.equals("")) {
                    scanner = new Scanner(resultadosString);
                    while (scanner.hasNextInt()) {
                        resultados.add(scanner.nextInt());
                    }
                }
                tipos = new ArrayList<String>(Arrays.asList(rs.getString(i++).split(" ")));
                for (int j = 0; j < idUsuarios.size(); j++) {
                    String tipo = tipos.get(j);
                    int resultado = 0;
                    if (!resultados.isEmpty()) {
                        resultado = resultados.get(j);
                    }
                    int idUsuario = idUsuarios.get(j);
                    if (tipo.equals("Jugador")) {
                        if (partido.getIdJugador1() == 0) {
                            partido.setIdJugador1(idUsuario);
                            partido.setResultado1(resultado);
                        } else if (partido.getIdJugador2() == 0) {
                            partido.setIdJugador2(idUsuario);
                            partido.setResultado2(resultado);
                        }
                    } else if (tipo.equals("Arbitro")) {
                        if (partido.getIdArbitro() == 0) {
                            partido.setIdArbitro(idUsuario);
                        }
                    }
                }
                partidos.add(partido);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return partidos;
    }
    
    /**
     * @param idPartido
     * @return 
     */
    public Partido buscarPartido(int idPartido){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Integer> idUsuarios, resultados;
        ArrayList<String> tipos;
        String resultadosString;
        Partido partido = null;
        try {
            int i = 1;
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_PARTIDO);
            statement = connection.prepareStatement(SQL_PARTIDO);
            statement.setInt(i++, idPartido);
            rs = statement.executeQuery();
            if (rs.next()) {
                idUsuarios = new ArrayList<Integer>();
                resultados = new ArrayList<Integer>();
                i = 1;
                partido = new Partido();
                partido.setIdPartido(rs.getInt(i++));
                partido.setFechaHora(rs.getDate(i++));
                partido.setIdPartidoTorneo(rs.getInt(i++));
                Scanner scanner = new Scanner(rs.getString(i++));
                while (scanner.hasNextInt()) {
                    idUsuarios.add(scanner.nextInt());
                }
                resultadosString = rs.getString(i++);
                if (resultadosString != null && !resultadosString.equals("")) {
                    scanner = new Scanner(resultadosString);
                    while (scanner.hasNextInt()) {
                        resultados.add(scanner.nextInt());
                    }
                }
                tipos = new ArrayList<String>(Arrays.asList(rs.getString(i++).split(" ")));
                for (int j = 0; j < idUsuarios.size(); j++) {
                    String tipo = tipos.get(j);
                    int resultado = 0;
                    if (!resultados.isEmpty()) {
                        resultado = resultados.get(j);
                    }
                    int idUsuario = idUsuarios.get(j);
                    if (tipo.equals("Jugador")) {
                        if (partido.getIdJugador1() == 0) {
                            partido.setIdJugador1(idUsuario);
                            partido.setResultado1(resultado);
                        } else if (partido.getIdJugador2() == 0) {
                            partido.setIdJugador2(idUsuario);
                            partido.setResultado2(resultado);
                        }
                    } else if (tipo.equals("Arbitro")) {
                        if (partido.getIdArbitro() == 0) {
                            partido.setIdArbitro(idUsuario);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return partido;
    }
}
