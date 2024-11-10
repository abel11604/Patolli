package entidades;

import java.util.List;

/**
 *
 * @author abelc
 */
public class Juego {

    private List<Jugador> jugadores;
    private List<Casilla> casillas;
    private int apuesta;
    Boolean estado;

    public Juego() {
    }
    
    public Juego(List<Jugador> jugadores, List<Casilla> casillas, int apuesta, Boolean estado) {
        this.jugadores = jugadores;
        this.casillas = casillas;
        this.apuesta = apuesta;
        this.estado = estado;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
    
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(List<Casilla> casillas) {
        this.casillas = casillas;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

}
