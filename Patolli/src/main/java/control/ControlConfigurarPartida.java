/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import modelo.Casilla;
import modelo.Juego;
import modelo.Jugador;
import java.util.ArrayList;
import java.util.List;

/* Se encarga de gestionar los parámetros de configuración como jugadores,
 * número de fichas, casillas por aspa, apuestas y fondo inicial.
 *
 * @author abelc
 */
public class ControlConfigurarPartida implements IControlConfigurarPartida {

    private static ControlConfigurarPartida instance; // Instancia Singleton
    private int numFichas; // Número de fichas por jugador
    private List<Jugador> jugadores; // Lista de jugadores configurados
    private int casillaPorAspa; // Número de casillas por aspa
    private int fondoApuesta; // Fondo inicial para cada jugador
    private int apuesta; // Apuesta inicial de la partida

    /**
     * Constructor privado para implementar el patrón Singleton. Inicializa la
     * lista de jugadores.
     */
    private ControlConfigurarPartida() {
        jugadores = new ArrayList<>();
    }

    /**
     * Obtiene la única instancia de la clase ControlConfigurarPartida. Si no
     * existe una instancia, la crea.
     *
     * @return la instancia Singleton de ControlConfigurarPartida.
     */
    public static ControlConfigurarPartida getInstance() {
        if (instance == null) {
            instance = new ControlConfigurarPartida();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumFichas() {
        return numFichas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNumFichas(int numFichas) {
        this.numFichas = numFichas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCasillaPorAspa() {
        return casillaPorAspa;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCasillaPorAspa(int casillaPorAspa) {
        this.casillaPorAspa = casillaPorAspa;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Juego crearPartida() {
        return new Juego(jugadores, crearCasillas(), apuesta, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getApuesta() {
        return apuesta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFondo() {
        return fondoApuesta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFondo(int fondoApuesta) {
        this.fondoApuesta = fondoApuesta;
    }

    /**
     * Método auxiliar que genera las casillas necesarias para el tablero de
     * juego. Crea las casillas iniciales, normales, de apuesta y de doble
     * turno, organizadas por aspas y una casilla central.
     *
     * @return lista de casillas configuradas para la partida.
     */
    private List<Casilla> crearCasillas() {
        List<Casilla> casillas = new ArrayList<>();
        int contadorCasilla = 1;
        // Generar las casillas para las cuatro aspas
        if (casillaPorAspa == 8) {
            int casillasPorAspa = 16;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialBlanco", 5, 12, 8, 9);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialAmarillo", 5, 12, 8, 9);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialNaranja", 5, 12, 8, 9);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialCafe", 5, 12, 8, 9);
            casillas.add(new Casilla("Central", contadorCasilla++));
        } else if (casillaPorAspa == 10) {
            int casillasPorAspa = 20;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialBlanco", 7, 14, 10, 11);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialAmarillo", 7, 14, 10, 11);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialNaranja", 7, 14, 10, 11);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialCafe", 7, 14, 10, 11);
            casillas.add(new Casilla("Central", contadorCasilla++));
        } else if (casillaPorAspa == 14) {
            int casillasPorAspa = 28;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialBlanco", 11, 18, 14, 15);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialAmarillo", 11, 18, 14, 15);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialNaranja", 11, 18, 14, 15);
            casillas.add(new Casilla("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialCafe", 11, 18, 14, 15);
            casillas.add(new Casilla("Central", contadorCasilla++));
        }

        return casillas;
    }

    /**
     * Método auxiliar para generar una aspa completa del tablero.
     *
     * @param casillas lista de casillas actualizada.
     * @param contadorCasilla contador de casillas para asignar identificadores
     * únicos.
     * @param casillasPorAspa número de casillas por aspa.
     * @param inicialJugador tipo de casilla inicial según el jugador.
     * @param apuestaPos1 primera posición de una casilla de apuesta.
     * @param apuestaPos2 segunda posición de una casilla de apuesta.
     * @param dobleTurnoPos1 primera posición de una casilla de doble turno.
     * @param dobleTurnoPos2 segunda posición de una casilla de doble turno.
     * @return contador actualizado después de generar el aspa.
     */
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

}
