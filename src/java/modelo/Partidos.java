package modelo;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;



/**
 * @author DELL
 * @version 1.0
 * @created 10-abr.-2017 11:34:17 p. m.
 */
public class Partidos implements Iterator {

	private Vector data;
	private Enumeration ec;
	private Partido nextPartido;
	public Partido m_Partido;

	public Partidos(){

	}

	public boolean hasNext(){
		return false;
	}

	public void initialize(){

	}

	public Partido next(){
		return null;
	}

	public void remove(){

	}

	/**
	 * 
	 * @param partido
	 */
	public void agregar(Partido partido){

	}
}//end Partidos