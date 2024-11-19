/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.Casilla;
import dominio.Ficha;

import dominio.Jugador;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;
import dominio.Partida;
import java.util.Map;

/**
 *
 * @author abelc
 */
public class CrearPartidaBO {

    private Partida partida;

    public CrearPartidaBO() {
        this.partida = new Partida();
    }

    /**
     * Método para crear una partida con los parámetros enviados desde el
     * cliente.
     *
     * @param data Datos enviados por el cliente en formato Map.
     * @param clientId Identificador del cliente que solicita la creación.
     * @return Respuesta para el cliente con la información de la partida
     * creada.
     */
    public Map<String, Object> crearPartida(Map<String, Object> data, String clientId) {
        // Validar datos y cliente
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("El cliente no está identificado.");
        }

        // Extraer datos del cliente
        int tamanoTablero = (int) data.get("tamanoTablero");
        int numFichas = (int) data.get("numFichas");
        int fondoApuesta = (int) data.get("fondoApuesta");
        int cantidadAPagar = (int) data.get("cantidadAPagar");
        String nombreHost = (String) data.get("nombreHost");

        // Configurar la partida
        partida.setApuesta(cantidadAPagar);
        partida.setCasillas(crearCasillas(tamanoTablero));
        partida.setEstado(true);

        // Crear jugador anfitrión
        Jugador host = crearJugador(clientId, nombreHost, fondoApuesta, numFichas);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(host);
        partida.setJugadores(jugadores);

        // Generar código único de acceso
        String codigoAcceso = UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
        partida.setCodigoAcceso(codigoAcceso);

        // Respuesta al cliente
        return Map.of(
                "accion", "CREAR_PARTIDA",
                "codigoAcceso", codigoAcceso,
                "idHost", host.getId(),
                "mensaje", "Partida creada exitosamente"
        );
    }

    /**
     * Método para crear las casillas del tablero.
     *
     * @param tamanoTablero Número de casillas por aspa (8, 10, 14).
     * @return Lista de casillas configuradas.
     */
    private List<Casilla> crearCasillas(int casillaPorAspa) {
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
     * Genera una aspa completa para el tablero.
     *
     * @param casillas Lista de casillas actual.
     * @param contadorCasilla Contador para identificar las casillas.
     * @param casillasPorAspa Número total de casillas en el aspa.
     * @param inicialJugador Tipo de la casilla inicial del jugador.
     * @param apuestaPos1 Primera casilla de tipo "Apuesta".
     * @param apuestaPos2 Segunda casilla de tipo "Apuesta".
     * @param dobleTurnoPos1 Primera casilla de tipo "DobleTurno".
     * @param dobleTurnoPos2 Segunda casilla de tipo "DobleTurno".
     * @return Contador actualizado después de agregar el aspa.
     */
    private int generarCasillasAspa(List<Casilla> casillas, int contadorCasilla, int casillasPorAspa,
            String inicialJugador, int apuestaPos1, int apuestaPos2, int dobleTurnoPos1, int dobleTurnoPos2) {
        for (int i = 1; i <= casillasPorAspa; i++) {
            if (i == 1) {
                casillas.add(new Casilla(inicialJugador, contadorCasilla)); // Casilla inicial
            } else if (i == apuestaPos1 || i == apuestaPos2) {
                casillas.add(new Casilla("Apuesta", contadorCasilla)); // Casilla de apuesta
            } else if (i == dobleTurnoPos1 || i == dobleTurnoPos2) {
                casillas.add(new Casilla("DobleTurno", contadorCasilla)); // Casilla de doble turno
            } else {
                casillas.add(new Casilla("Normal", contadorCasilla)); // Casilla normal
            }
            contadorCasilla++;
        }
        return contadorCasilla;
    }

    /**
     * Método para crear un jugador con las fichas correspondientes.
     *
     * @param clientId Identificador único del cliente.
     * @param nombre Nombre del jugador.
     * @param fondo Fondo de apuesta inicial.
     * @param numFichas Número de fichas del jugador.
     * @return Jugador configurado.
     */
    private Jugador crearJugador(String clientId, String nombre, int fondo, int numFichas) {
        Jugador jugador = new Jugador(clientId,nombre,"Blanco",fondo);
        jugador.setFichas(crearFichas(numFichas, jugador));
        return jugador;
    }

    /**
     * Genera las fichas para un jugador.
     *
     * @param numFichas Número de fichas a generar.
     * @param jugador Jugador asociado a las fichas.
     * @return Lista de fichas creadas.
     */
    private List<Ficha> crearFichas(int numFichas, Jugador jugador) {
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i < numFichas; i++) {
            fichas.add(new Ficha(null, jugador));
        }
        return fichas;
    }

}
