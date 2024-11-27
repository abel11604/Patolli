/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import enums.EstadosPartida;
import java.util.List;

/**
 *
 * @author abelc
 */
public class Partida {

    private List<Jugador> jugadores;
    private List<Casilla> casillas;
    private int apuesta;
    private EstadosPartida estado;
    private String codigoAcceso;
    private Jugador turnoActual;

    public Partida() {
    }

    public Partida(List<Jugador> jugadores, List<Casilla> casillas, int apuesta, EstadosPartida estado, String codigoAcceso, Jugador turnoActual) {
        this.jugadores = jugadores;
        this.casillas = casillas;
        this.apuesta = apuesta;
        this.estado = estado;
        this.codigoAcceso = codigoAcceso;
        this.turnoActual = turnoActual;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Jugador getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(Jugador turnoActual) {
        this.turnoActual = turnoActual;
    }

    public List<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(List<Casilla> casillas) {
        this.casillas = casillas;
    }

    public EstadosPartida getEstado() {
        return estado;
    }

    public void setEstado(EstadosPartida estado) {
        this.estado = estado;
    }

    

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public String getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

}
