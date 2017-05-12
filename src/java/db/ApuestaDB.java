/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author rm-rf
 */
public class ApuestaDB {
    
    private final String TABLE_NAME = "apuesta";
    private final String ID_FIELD = "idApuesta";
    private final String FIELDS = "idUsuario, idPartido, estado, valor, fechaCreacion, fechaCierre";
    private final String SQL_INSERT = "INSERT INTO " + TABLE_NAME +" ("+FIELDS+") VALUES (?,?,?,?,?,?)";
    private final String SQL_INSERT_ID = "SELECT @@identity AS id";
    private final String SQL_UPDATE = "UPDATE "+TABLE_NAME+" SET estado=?, valor=?, fechaCreacion=?, fechaCierre=? WHERE "+ID_FIELD+"=?";
    private final String SQL_SELECT_ID = "SELECT "+FIELDS+" FROM " + TABLE_NAME + " WHERE "+ID_FIELD+"=?";

    public ApuestaDB() {
    }
    
    
    
}
