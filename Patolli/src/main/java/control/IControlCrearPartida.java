/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control;

import modelo.JugadorModelo;
import modelo.PartidaModelo;

/**
 *
 * @author abelc
 */
public interface IControlCrearPartida {

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
     * Asigna el jugador anfitrión de la partida.
     *
     * @param hostPartida el jugador anfitrión que se configurará para la
     * partida.
     */
    public void setJugador(JugadorModelo hostPartida);

    /**
     * Obtiene el jugador anfitrión de la partida.
     *
     * @return el jugador anfitrión de la partida.
     */
    public JugadorModelo getJugador();

    /**
     * Crea una nueva instancia de un juego configurado según los parámetros
     * actuales.
     *
     */
    public void crearPartida();

    /**
     * Obtiene la apuesta configurada para la partida.
     *
     * @return el monto de la apuesta inicial.
     */
    public int getApuesta();

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
     * Establece el monto de la apuesta inicial para la partida.
     *
     * @param apuesta monto de la apuesta.
     */
    public void setApuesta(int apuesta);

    public PartidaModelo getPartida();

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
