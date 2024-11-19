package modelo;

/**
 *
 * @author abelc
 */
public class Ficha {
    private Casilla casillaActual;
    private Jugador jugador;

    public Ficha() {
    }

    public Ficha(Casilla casillaActual, Jugador jugador) {
        this.casillaActual = casillaActual;
        this.jugador = jugador;
    }

    public Casilla getCasillaActual() {
        return casillaActual;
    }

    public void setCasillaActual(Casilla casillaActual) {
        this.casillaActual = casillaActual;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
}
