package modelo;

/**
 *
 * @author abelc
 */
public class FichaModelo {

    private String id;
    private CasillaModelo casillaActual;
    private JugadorModelo jugador;

    public FichaModelo() {
    }

    public FichaModelo(String id, CasillaModelo casillaActual, JugadorModelo jugador) {
        this.id = id;
        this.casillaActual = casillaActual;
        this.jugador = jugador;
    }

    public FichaModelo(CasillaModelo casillaActual, JugadorModelo jugador) {
        this.casillaActual = casillaActual;
        this.jugador = jugador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CasillaModelo getCasillaActual() {
        return casillaActual;
    }

    public void setCasillaActual(CasillaModelo casillaActual) {
        this.casillaActual = casillaActual;
    }

    public JugadorModelo getJugador() {
        return jugador;
    }

    public void setJugador(JugadorModelo jugador) {
        this.jugador = jugador;
    }

}
