/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import comunicacion.ClientConnection;
import comunicacion.ClientConnection.MessageListener;
import factory.FichaFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.CasillaModelo;
import modelo.JugadorModelo;
import modelo.PartidaModelo;

/**
 *
 * @author abelc
 */
public class ControlCrearPartida implements IControlCrearPartida {

    private static ControlCrearPartida instance; // Instancia Singleton
    private int numFichas; // Número de fichas por jugador
    private List<JugadorModelo> jugadores; // Lista de jugadores configurados
    private int casillaPorAspa; // Número de casillas por aspa
    private int fondoApuesta; // Fondo inicial para cada jugador
    private int apuesta;
    private JugadorModelo hostPartida;
    private ClientConnection cliente;
    private MessageListener listener;
    private PartidaModelo partida;

    public ControlCrearPartida() {
        this.jugadores = new ArrayList<>();
        this.cliente = ClientConnection.getInstance();
        this.partida = new PartidaModelo();
        this.listener = new MessageListener() {
            @Override
            public void onMessageReceived(Map<String, Object> message) {
                // Lógica para manejar la respuesta
                String codigo = (String) message.get("codigoAcceso");
                if (codigo != null) {
                    partida.setCodigoAcceso(codigo);
                }

            }
        };
        cliente.setMessageListener(listener);
        cliente.connect("localhost", 8080);
    }

    /**
     * Obtiene la única instancia de la clase ControlCrearPartida. Si no existe
     * una instancia, la crea.
     *
     * @return la instancia Singleton de ControlCrearPartida.
     */
    public static ControlCrearPartida getInstance() {
        if (instance == null) {
            instance = new ControlCrearPartida();
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
    public void setJugador(JugadorModelo hostPartida) {
        this.hostPartida = hostPartida;
    }

    @Override
    public JugadorModelo getJugador() {
        return hostPartida;
    }

    @Override
    public void crearPartida() {
        asignarHost();
        this.partida.setApuesta(apuesta);
        this.partida.setCasillas(crearCasillas());
        this.partida.setJugadores(jugadores);
        cliente.crearPartida(partida);
    }

    private void asignarHost() {

        hostPartida.setColor("Blanco");
        hostPartida.setFondoApuesta(fondoApuesta);
        hostPartida.setFichas(FichaFactory.generarFichas(numFichas, hostPartida));
        jugadores.add(hostPartida);
    }

    /**
     * Método auxiliar que genera las casillas necesarias para el tablero de
     * juego. Crea las casillas iniciales, normales, de apuesta y de doble
     * turno, organizadas por aspas y una casilla central.
     *
     * @return lista de casillas configuradas para la partida.
     */
    private List<CasillaModelo> crearCasillas() {
        List<CasillaModelo> casillas = new ArrayList<>();
        int contadorCasilla = 1;
        // Generar las casillas para las cuatro aspas
        if (casillaPorAspa == 8) {
            int casillasPorAspa = 16;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialBlanco", 5, 12, 8, 9);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialAmarillo", 5, 12, 8, 9);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialNaranja", 5, 12, 8, 9);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialCafe", 5, 12, 8, 9);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
        } else if (casillaPorAspa == 10) {
            int casillasPorAspa = 20;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialBlanco", 7, 14, 10, 11);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialAmarillo", 7, 14, 10, 11);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialNaranja", 7, 14, 10, 11);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialCafe", 7, 14, 10, 11);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
        } else if (casillaPorAspa == 14) {
            int casillasPorAspa = 28;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialBlanco", 11, 18, 14, 15);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialAmarillo", 11, 18, 14, 15);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialNaranja", 11, 18, 14, 15);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialCafe", 11, 18, 14, 15);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
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
    private int generarCasillasAspa(List<CasillaModelo> casillas, int contadorCasilla, int casillasPorAspa,
            String inicialJugador, int apuestaPos1, int apuestaPos2, int dobleTurnoPos1, int dobleTurnoPos2) {
        for (int i = 1; i <= casillasPorAspa; i++) {
            if (i == 1) {
                casillas.add(new CasillaModelo(inicialJugador, contadorCasilla)); // CasillaModelo inicial del jugador
            } else if (i == apuestaPos1 || i == apuestaPos2) {
                casillas.add(new CasillaModelo("Apuesta", contadorCasilla)); // Casillas de Apuesta
            } else if (i == dobleTurnoPos1 || i == dobleTurnoPos2) {
                casillas.add(new CasillaModelo("DobleTurno", contadorCasilla)); // Casillas de DobleTurno
            } else {
                casillas.add(new CasillaModelo("normal", contadorCasilla)); // Casillas normales
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
    public int getCasillaPorAspa() {
        return casillaPorAspa;
    }

    @Override
    public void setCasillaPorAspa(int casillaPorAspa) {
        this.casillaPorAspa = casillaPorAspa;
    }

    @Override
    public PartidaModelo getPartida() {
        return partida;
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
