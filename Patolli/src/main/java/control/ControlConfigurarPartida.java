/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entidades.Juego;
import entidades.Jugador;
import java.util.List;

/**
 *
 * @author abelc
 */
public class ControlConfigurarPartida implements IControlConfigurarPartida {
    private static ControlConfigurarPartida instance;
    private int numFichas;
    private List<Jugador> jugadores;
    private int casillaPorAspa;
    private int fondoApuesta;
    private int apuesta;
    
    public static ControlConfigurarPartida getInstance() {
        if (instance == null) {
            instance = new ControlConfigurarPartida();
        }
        return instance;
    }

    @Override
    public int getNumFichas() {
        return numFichas;
    }

    @Override
    public void setNumFichas(int numFichas) {
        this.numFichas = numFichas;
    }

    @Override
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    @Override
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public int getCasillaPorAspa() {
        return casillaPorAspa;
    }

    @Override
    public void setCasillaPorAspa(int casillaPorAspa) {
        this.casillaPorAspa = casillaPorAspa;
    }

    @Override
    public Juego crearPartida() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
    }

    @Override
    public int getApuesta() {
        return apuesta;
    }

    @Override
    public void setApuesta(int apuesta) {
       this.apuesta=apuesta;
    }

    @Override
    public int getFondo() {
        return fondoApuesta;
    }

    @Override
    public void setFondo(int fondoApuesta) {
       this.fondoApuesta=fondoApuesta;
    }  
    
}
