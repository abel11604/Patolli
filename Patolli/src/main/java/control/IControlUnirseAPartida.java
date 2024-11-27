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
public interface IControlUnirseAPartida {

    public void UnirseAPartida(String codigoAcceso, JugadorModelo jugadorAUnirse);

    public PartidaModelo getPartida();

    public void setJugador(JugadorModelo jugador);

    public JugadorModelo getJugador();

    public void generarPartida();
    
    public String getErrorMessage();
}
