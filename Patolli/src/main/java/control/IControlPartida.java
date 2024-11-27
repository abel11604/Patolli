
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control;

import modelo.FichaModelo;
import modelo.PartidaModelo;
import modelo.JugadorModelo;

/**
 * Interface para el control de una partida en el juego.
 * Define las operaciones necesarias para manipular fichas, jugadores y el estado de la partida.
 * 
 * @author adria
 */
public interface IControlPartida {

    /**
     * Establece la partida actual.
     *
     * @param partida La instancia de PartidaModelo que representa la partida actual.
     */
    public void setPartida(PartidaModelo partida);

    /**
     * Obtiene la partida actual.
     *
     * @return La instancia de PartidaModelo que representa la partida actual.
     */
    public PartidaModelo getPartida();

    /**
     * Avanza una ficha una cantidad específica de casillas en el tablero,
     * aplicando las reglas del juego al llegar a la casilla de destino.
     *
     * @param numCasillas La cantidad de casillas a avanzar.
     * @param fichaSel La ficha que se va a mover.
     */
    public void avanzarCasillas(int numCasillas, FichaModelo fichaSel);

    /**
     * Cobra una apuesta al jugador, deduciendo el monto de la apuesta de su fondo de apuestas.
     * Si el jugador no tiene suficiente fondo, maneja la situación según las reglas del juego.
     *
     * @param jugador El jugador al que se le cobrará la apuesta.
     */
    public void cobrarApuesta(JugadorModelo jugador);

    /**
     * Cobra una apuesta doble al jugador, deduciendo el doble del monto
     * de la apuesta de su fondo de apuestas. Si el jugador no tiene suficiente fondo,
     * maneja la situación según las reglas del juego.
     *
     * @param jugador El jugador al que se le cobrará la apuesta doble.
     */
    public void cobrarApuestaDoble(JugadorModelo jugador);

    /**
     * Reinicia la posición de una ficha, colocándola en la casilla inicial
     * correspondiente según las reglas del juego.
     *
     * @param ficha La ficha que se va a reiniciar.
     */
    public void reiniciarFicha(FichaModelo ficha);

    /**
     * Elimina una ficha del tablero, removiéndola de su casilla actual.
     *
     * @param ficha La ficha que se va a eliminar.
     */
    public void eliminarFicha(FichaModelo ficha);

    /**
     * Elimina un jugador de la partida, aplicando las reglas necesarias para su
     * remoción y ajustando el estado del juego si es necesario.
     *
     * @param jugador El jugador que se va a eliminar de la partida.
     */
    public void eliminarJugador(JugadorModelo jugador);
}
