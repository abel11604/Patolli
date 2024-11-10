package control;

import entidades.Ficha;
import entidades.Juego;
import entidades.Jugador;


/**
 *
 * @author adria
 */
public interface IControlPartida {
    
    public void setPartida(Juego partida);
    public Juego getPartida();
    public void avanzarCasillas(int numCasillas, Ficha fichaSel);
    public void cobrarApuesta(Jugador jugador);
    public void cobrarApuestaDoble(Jugador jugador);
    public void reiniciarFicha(Ficha ficha);
    public void eliminarFicha(Ficha ficha);
    public void eliminarJugador(Jugador jugador);
}
