package services;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import modelo.Partido;
import modelo.Usuario;



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

        public Partidos(int cantidadPartidos, ArrayList<Usuario> jugadores,ArrayList<Usuario> arbitros, Date fechaHoraInicial){
            data = new Vector();
            Date date;
            int contadorPartidos = 0;
            for (int i = 0; i < jugadores.size(); i=i+2) {
                if (i > 0) {
                    date = this.getLast().getFechaHora();
                } else {
                    date = fechaHoraInicial;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.MINUTE, 30);  // number of days to add
                date = c.getTime();  // dt is now the new date
                
                Usuario jugador1 = jugadores.get(i);
                Usuario jugador2 = jugadores.get(i+1);
                contadorPartidos++;
                m_Partido = new Partido();
                m_Partido.setIdJugador1(jugador1.getIdUsuario());
                m_Partido.setIdJugador2(jugador2.getIdUsuario());
                m_Partido.setFechaHora(date);
                m_Partido.setIdPartidoTorneo(contadorPartidos);
                this.agregar(m_Partido);
            }
            int partidosIniciales = contadorPartidos;
            for (int i = 0; i < (cantidadPartidos-partidosIniciales); i++) {
                date = this.getLast().getFechaHora();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.MINUTE, 30);  // number of days to add
                date = c.getTime();  // dt is now the new date
                contadorPartidos++;
                m_Partido = new Partido();
                m_Partido.setFechaHora(date);
                m_Partido.setIdPartidoTorneo(contadorPartidos);
                this.agregar(m_Partido);
            }
        }
        @Override
	public boolean hasNext(){
		return false;
	}

	public void initialize(){

	}

        @Override
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
            if (partido instanceof Partido) {
                data.add(partido);
            }
	}

    /**
     *
     * @return 
     */
    public Partido getLast(){
        return (Partido) data.lastElement();
    }
    
    public Vector getData(){
        return data;
    }
}//end Partidos