package modelo;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @author Sebasti�n
 * @version 1.0
 * @created 10-abr.-2017 11:34:17 p. m.
 */
public class Partido {

	private int idPartido;
	private Date fechaHora;
	private int idPartidoTorneo;
	private int idJugador1;
	private int idJugador2;
	private int resultado1;
	private int resultado2;
	private int idArbitro;

	public Partido(){

	}

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdPartidoTorneo() {
        return idPartidoTorneo;
    }

    public void setIdPartidoTorneo(int idPartidoTorneo) {
        this.idPartidoTorneo = idPartidoTorneo;
    }

    public int getIdJugador1() {
        return idJugador1;
    }

    public void setIdJugador1(int idJugador1) {
        this.idJugador1 = idJugador1;
    }

    public int getIdJugador2() {
        return idJugador2;
    }

    public void setIdJugador2(int idJugador2) {
        this.idJugador2 = idJugador2;
    }

    public int getResultado1() {
        return resultado1;
    }

    public void setResultado1(int resultado1) {
        this.resultado1 = resultado1;
    }

    public int getResultado2() {
        return resultado2;
    }

    public void setResultado2(int resultado2) {
        this.resultado2 = resultado2;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }
    
    public String getFechaHoraF(){
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return dt1.format(this.getFechaHora());
    }
	
}//end Partido