package control;

import entidades.Juego;
import entidades.Jugador;
import java.util.List;

/**
 *
 * @author abelc
 */
public interface IControlConfigurarPartida {

    public int getNumFichas();

    public void setNumFichas(int numFichas);

    public List<Jugador> getJugadores();

    public void setJugadores(List<Jugador> jugadores);

    public int getCasillaPorAspa();

    public void setCasillaPorAspa(int casillaPorAspa);

    public Juego crearPartida();

    public int getApuesta();

    public void setApuesta(int apuesta);

    public int getFondo();

    public void setFondo(int fondoApuesta);

}
