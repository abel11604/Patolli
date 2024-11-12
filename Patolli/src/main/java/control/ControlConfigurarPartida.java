/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entidades.Casilla;
import entidades.Juego;
import entidades.Jugador;
import java.util.ArrayList;
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

    private ControlConfigurarPartida() {
        jugadores = new ArrayList<>();
    }

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
        Juego juego = new Juego(jugadores, crearCasillas(), apuesta, true);
        return juego;
    }

    private List<Casilla> crearCasillas() {
        List<Casilla> casillas = new ArrayList<>();
        int contadorCasilla = 1;
        // Generar las casillas para las cuatro aspas
        if (casillaPorAspa == 8) {
            int casillasPorAspa = 16;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ1", 5, 12, 8, 9);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ2", 5, 12, 8, 9);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ3", 5, 12, 8, 9);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ4", 5, 12, 8, 9);
            casillas.add(new Casilla("Central", contadorCasilla++));
        } else if (casillaPorAspa == 10) {
            int casillasPorAspa = 20;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ1", 7, 14, 10, 11);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ2", 7, 14, 10, 11);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ3", 7, 14, 10, 11);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ4", 7, 14, 10, 11);
            casillas.add(new Casilla("Central", contadorCasilla++));
        } else if (casillaPorAspa == 14) {
            int casillasPorAspa = 28;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ1", 11, 18, 14, 15);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ2", 11, 18, 14, 15);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ3", 11, 18, 14, 15);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialJ4", 11, 18, 14, 15);
            casillas.add(new Casilla("Central", contadorCasilla++));
        }

        return casillas;
    }
    
// Método auxiliar para generar una aspa completa
    private int generarCasillasAspa(List<Casilla> casillas, int contadorCasilla, int casillasPorAspa,
            String inicialJugador, int apuestaPos1, int apuestaPos2, int dobleTurnoPos1, int dobleTurnoPos2) {
        for (int i = 1; i <= casillasPorAspa; i++) {
            if (i == 1) {
                casillas.add(new Casilla(inicialJugador, contadorCasilla)); // Casilla inicial del jugador
            } else if (i == apuestaPos1 || i == apuestaPos2) {
                casillas.add(new Casilla("Apuesta", contadorCasilla)); // Casillas de Apuesta
            } else if (i == dobleTurnoPos1 || i == dobleTurnoPos2) {
                casillas.add(new Casilla("DobleTurno", contadorCasilla)); // Casillas de DobleTurno
            } else {
                casillas.add(new Casilla("normal", contadorCasilla)); // Casillas normales
            }
            contadorCasilla++;
        }
        return contadorCasilla;
    }

    @Override
    public int getApuesta() {
        return apuesta;
    }

    @Override
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    @Override
    public int getFondo() {
        return fondoApuesta;
    }

    @Override
    public void setFondo(int fondoApuesta) {
        this.fondoApuesta = fondoApuesta;
    }

}
