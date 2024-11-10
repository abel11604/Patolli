/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
