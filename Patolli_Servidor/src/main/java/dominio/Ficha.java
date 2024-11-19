/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author abelc
 */
public class Ficha {
    private String id;
    private Casilla casillaActual;
    private Jugador jugador;

    public Ficha() {
    }

    public Ficha(String id, Casilla casillaActual, Jugador jugador) {
        this.id = id;
        this.casillaActual = casillaActual;
        this.jugador = jugador;
    }

    public Ficha(Casilla casillaActual, Jugador jugador) {
        this.casillaActual = casillaActual;
        this.jugador = jugador;
    }

    public Casilla getCasillaActual() {
        return casillaActual;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
