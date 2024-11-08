/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

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
