package modelo;

/**
 * @author Sebasti�n
 * @version 1.0
 * @created 10-abr.-2017 11:34:17 p. m.
 */
public abstract class Estructura {

	private int idEstructura;
	private String nombre;

	public Estructura(){

	}
	/**
	 * 
	 * @param modalidad
	 * @param cantidadJugadores
	 */
	public abstract void crearEstructura(int cantidadJugadores);
}//end Estructura