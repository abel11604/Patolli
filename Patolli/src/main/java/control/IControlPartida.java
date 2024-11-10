/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control;

import entidades.Ficha;
import entidades.Juego;
import entidades.Jugador;

/**
 *
 * @author abelc
 */
public interface IControlPartida {

    public void setPartida(Juego partida);

    public Juego getPartida();

    public void avanzarCasillas(int numCasillas, Ficha fichaSel);

    public void cobrarApuesta(Jugador jugador);

    public void cobrarApuestaDoble(Jugador jugador);

    public void reiniciarFicha(Ficha ficha);

    public void eliminarFicha(Ficha ficha);
}
