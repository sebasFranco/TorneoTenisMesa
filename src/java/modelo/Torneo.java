package modelo;
import services.Partidos;
import modelo.Estructura;



/**
 * @author Sebasti�n
 * @version 1.0
 * @created 10-abr.-2017 11:34:17 p. m.
 */
public class Torneo {

	private int idTorneo;
	private String nombre;
	private Estructura estructura;
	private int cantidadJugadores;
	private int cantidadMesas;
	private Partidos partidos;

	public Torneo(){

	}

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estructura getEstructura() {
        return estructura;
    }

    public void setEstructura(Estructura estructura) {
        this.estructura = estructura;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public int getCantidadMesas() {
        return cantidadMesas;
    }

    public void setCantidadMesas(int cantidadMesas) {
        this.cantidadMesas = cantidadMesas;
    }

    public Partidos getPartidos() {
        return partidos;
    }

    public void setPartidos(Partidos partidos) {
        this.partidos = partidos;
    }
        

}//end Torneo