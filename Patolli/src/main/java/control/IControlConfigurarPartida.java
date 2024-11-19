package control;

import modelo.Juego;
import modelo.Jugador;
import java.util.List;
/**
 * Interfaz que define los métodos necesarios para la configuración de una partida.
 * Permite establecer y recuperar valores relacionados con el número de fichas,
 * jugadores, casillas por aspa, la apuesta inicial y el fondo de apuestas.
 * Además, proporciona la funcionalidad para crear una partida de juego.
 *
 * @author abelc
 */
public interface IControlConfigurarPartida {

    /**
     * Obtiene el número de fichas configurado para cada jugador.
     *
     * @return el número de fichas por jugador.
     */
    public int getNumFichas();

    /**
     * Establece el número de fichas por jugador.
     *
     * @param numFichas número de fichas que tendrá cada jugador.
     */
    public void setNumFichas(int numFichas);

    /**
     * Obtiene la lista de jugadores configurados para la partida.
     *
     * @return una lista de objetos {@link Jugador} que representan los jugadores.
     */
    public List<Jugador> getJugadores();

    /**
     * Establece la lista de jugadores para la partida.
     *
     * @param jugadores lista de jugadores a asignar en la partida.
     */
    public void setJugadores(List<Jugador> jugadores);

    /**
     * Obtiene el número de casillas configuradas por aspa en el tablero.
     *
     * @return el número de casillas por aspa.
     */
    public int getCasillaPorAspa();

    /**
     * Establece el número de casillas por aspa en el tablero.
     *
     * @param casillaPorAspa número de casillas por aspa.
     */
    public void setCasillaPorAspa(int casillaPorAspa);

    /**
     * Crea una nueva instancia de un juego configurado según los parámetros actuales.
     *
     * @return un objeto {@link Juego} con la configuración definida.
     */
    public Juego crearPartida();

    /**
     * Obtiene la apuesta configurada para la partida.
     *
     * @return el monto de la apuesta inicial.
     */
    public int getApuesta();

    /**
     * Establece el monto de la apuesta inicial para la partida.
     *
     * @param apuesta monto de la apuesta.
     */
    public void setApuesta(int apuesta);

    /**
     * Obtiene el fondo de apuesta configurado para cada jugador.
     *
     * @return el fondo de apuesta por jugador.
     */
    public int getFondo();

    /**
     * Establece el fondo de apuesta inicial para cada jugador.
     *
     * @param fondoApuesta fondo de apuesta inicial por jugador.
     */
    public void setFondo(int fondoApuesta);
}
