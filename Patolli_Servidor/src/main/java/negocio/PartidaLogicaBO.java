/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import comunicacion.ClientManager;
import comunicacion.MessageUtil;
import dominio.Casilla;
import dominio.Ficha;
import dominio.Jugador;
import dominio.Partida;
import enums.ResultadoMovimiento;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author abelc
 */
public class PartidaLogicaBO {

    private Partida partida;

    public PartidaLogicaBO() {
    }

    public PartidaLogicaBO(Partida partida) {
        this.partida = partida;
    }

    /**
     * Realiza el movimiento de una ficha en el tablero y notifica a todos los
     * jugadores.
     *
     * @param data Mapa con los datos enviados por el cliente (idFicha,
     * numCasillas).
     * @param clientId Identificador del cliente que realiza el movimiento.
     * @return Resultado del movimiento.
     */
    public Map<String, Object> moverFicha(Map<String, Object> data, String clientId) {
        validarCliente(clientId);

        // Extraer datos del mapa
        String idFicha = (String) data.get("idFicha");
        int numCasillas = (int) data.get("numCasillas");

        // Buscar el jugador
        Jugador jugador = obtenerJugadorPorId(clientId);

        // Buscar la ficha seleccionada
        Ficha fichaSel = jugador.getFichas().stream()
                .filter(ficha -> ficha.getId().equals(idFicha))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una ficha con el id proporcionado."));

        // Aplicar la lógica del movimiento
        ResultadoMovimiento resultadoMovimiento = avanzarCasillas(numCasillas, fichaSel);

        // Crear mensaje de notificación para todos los jugadores
        Map<String, Object> mensaje = Map.of(
                "accion", "MOVER_FICHA",
                "jugador", jugador.getNombre(),
                "idFicha", fichaSel.getId(),
                "numCasillas", numCasillas,
                "resultado", resultadoMovimiento.name()
        );

        // Notificar a todos los jugadores en la partida
        for (Jugador jugadorEnPartida : partida.getJugadores()) {
            Socket clientSocket = ClientManager.getClientSocket(jugadorEnPartida.getId());
            if (clientSocket != null) {
                MessageUtil.enviarMensaje(clientSocket, mensaje);
            } else {
                System.err.println("No se encontró un socket para el jugador con ID: " + jugadorEnPartida.getId());
            }
        }

        // Retornar el resultado al cliente que realizó el movimiento
        return mensaje;

    }

    public Map<String, Object> lanzamientoCañas(String clientId) {
        validarCliente(clientId);

        // Verificar si el cliente tiene el turno
        Jugador jugador = obtenerJugadorPorId(clientId);

        int casillasAvanzar = tirarCañas();

        // Crear un mensaje para notificar a todos los jugadores
        Map<String, Object> mensaje = Map.of(
                "accion", "TIRAR_CAÑA",
                "jugador", jugador.getNombre(),
                "resultado", casillasAvanzar
        );

        // Notificar a todos los jugadores en la partida
        for (Jugador jugadorEnPartida : partida.getJugadores()) {
            Socket clientSocket = ClientManager.getClientSocket(jugadorEnPartida.getId());
            if (clientSocket != null) {
                MessageUtil.enviarMensaje(clientSocket, mensaje);
            } else {
                System.err.println("No se encontró un socket para el jugador con ID: " + jugadorEnPartida.getId());
            }
        }

        return mensaje;
    }

    /**
     * Avanza una ficha un número determinado de casillas.
     *
     * @param numCasillas Número de casillas a avanzar.
     * @param fichaSel Ficha que realizará el movimiento.
     * @return Resultado del movimiento.
     */
    public ResultadoMovimiento avanzarCasillas(int numCasillas, Ficha fichaSel) {
        Casilla casillaActual = fichaSel.getCasillaActual();
        int posicionActual = partida.getCasillas().indexOf(casillaActual);
        int nuevaPosicion = (posicionActual + numCasillas) % partida.getCasillas().size();

        Casilla casillaDestino = partida.getCasillas().get(nuevaPosicion);

        // Manejo de casilla ocupada
        if (casillaDestino.getOcupadoPor() != null) {
            if (casillaDestino.getTipo().equalsIgnoreCase("Central")) {
                eliminarFicha(casillaDestino.getOcupadoPor());
                return ResultadoMovimiento.FICHA_ELIMINADA;
            } else {
                reiniciarFicha(fichaSel);
                return ResultadoMovimiento.FICHA_REINICIADA;
            }
        }

        // Movimiento normal
        casillaActual.setOcupadoPor(null);
        fichaSel.setCasillaActual(casillaDestino);
        casillaDestino.setOcupadoPor(fichaSel);

        // Verificar tipo de casilla de destino
        switch (casillaDestino.getTipo().toLowerCase()) {
            case "dobleturno":
                return ResultadoMovimiento.TURNO_EXTRA;

            case "apuesta":
                if (!cobrarApuesta(fichaSel.getJugador())) {
                    return ResultadoMovimiento.JUGADOR_ELIMINADO; // El jugador no pudo pagar y fue eliminado
                }
                return ResultadoMovimiento.CAIDA_EN_CASILLA_APUESTA;

            default:
                return ResultadoMovimiento.MOVIMIENTO_EXITOSO;
        }
    }

    /**
     * Cobra la apuesta al jugador.
     *
     * @param jugador Jugador que debe pagar la apuesta.
     * @return true si se pudo cobrar la apuesta, false si fue eliminado.
     */
    public boolean cobrarApuesta(Jugador jugador) {
        int apuesta = partida.getApuesta();
        if (jugador.getFondoApuesta() >= apuesta) {
            jugador.setFondoApuesta(jugador.getFondoApuesta() - apuesta);
            return true;
        } else {
            eliminarJugador(jugador);
            return false;
        }
    }

    /**
     * Cobra una apuesta doble al jugador.
     *
     * @param jugador Jugador que debe pagar la apuesta.
     * @return true si se pudo cobrar la apuesta, false si fue eliminado.
     */
    public boolean cobrarApuestaDoble(Jugador jugador) {
        int apuestaDoble = partida.getApuesta() * 2;
        if (jugador.getFondoApuesta() >= apuestaDoble) {
            jugador.setFondoApuesta(jugador.getFondoApuesta() - apuestaDoble);
            return true;
        } else {
            eliminarJugador(jugador);
            return false;
        }
    }

    /**
     * Reinicia una ficha a su casilla inicial.
     *
     * @param ficha Ficha a reiniciar.
     */
    public void reiniciarFicha(Ficha ficha) {
        String tipoInicial = "inicial" + ficha.getJugador().getColor();
        for (Casilla casilla : partida.getCasillas()) {
            if (casilla.getTipo().equalsIgnoreCase(tipoInicial)) {
                casilla.setOcupadoPor(ficha);
                ficha.setCasillaActual(casilla);
                return;
            }
        }
    }

    /**
     * Elimina una ficha del tablero.
     *
     * @param ficha Ficha a eliminar.
     */
    public void eliminarFicha(Ficha ficha) {
        ficha.getJugador().getFichas().remove(ficha);
        if (ficha.getJugador().getFichas().isEmpty()) {
            eliminarJugador(ficha.getJugador());
        }
    }

    /**
     * Elimina un jugador de la partida.
     *
     * @param jugador Jugador a eliminar.
     */
    public void eliminarJugador(Jugador jugador) {
        partida.getJugadores().remove(jugador);
        for (Ficha ficha : jugador.getFichas()) {
            ficha.getCasillaActual().setOcupadoPor(null);
        }
    }

    /**
     * Determina el siguiente jugador en turno.
     *
     * @return Jugador que tiene el siguiente turno.
     */
    public Jugador determinarTurno() {
        List<Jugador> jugadores = partida.getJugadores();
        int indiceActual = jugadores.indexOf(partida.getTurnoActual());
        int siguienteIndice = (indiceActual + 1) % jugadores.size();
        Jugador siguienteJugador = jugadores.get(siguienteIndice);
        partida.setTurnoActual(siguienteJugador);
        return siguienteJugador;
    }

    /**
     * Valida si la partida ha terminado.
     *
     * @return true si la partida ha terminado, false en caso contrario.
     */
    public boolean partidaFinalizada() {
        return partida.getJugadores().size() <= 1;
    }

    /**
     * Simula el lanzamiento de 5 cañas y calcula el número de casillas a
     * avanzar.
     *
     * @return Número de casillas a avanzar según las cañas marcadas.
     */
    public int tirarCañas() {
        Random random = new Random();
        int casillasAvanzar = 0;

        for (int i = 0; i < 5; i++) { // Itera por las 5 cañas
            boolean esMarcada = random.nextBoolean(); // true si está marcada
            if (esMarcada) {
                casillasAvanzar++; // Incrementa por cada caña marcada
            }
        }

        // Devuelve el número de cañas marcadas como las casillas a avanzar
        return casillasAvanzar;
    }

    /**
     * Valida si un cliente está conectado al servidor.
     *
     * @param clientId Identificador del cliente.
     */
    private void validarCliente(String clientId) {
        if (ClientManager.getClientSocket(clientId) == null) {
            throw new IllegalStateException("El cliente no está conectado.");
        }
    }

    /**
     * Retorna el estado actual de la partida.
     *
     * @return Mapa con información sobre la partida.
     */
    public Map<String, Object> obtenerEstadoPartida() {
        Map<String, Object> estado = new HashMap<>();
        estado.put("turnoActual", partida.getTurnoActual().getNombre());
        estado.put("jugadores", partida.getJugadores().stream()
                .map(j -> Map.of(
                "nombre", j.getNombre(),
                "fichas", j.getFichas().size(),
                "fondoApuesta", j.getFondoApuesta()
        )).toList());
        return estado;
    }

    /**
     * Obtiene un jugador en la partida actual utilizando su clientId.
     *
     * @param clientId Identificador único del cliente.
     * @return El jugador asociado al clientId.
     * @throws IllegalArgumentException Si el jugador no está en la partida.
     */
    public Jugador obtenerJugadorPorId(String clientId) {
        for (Jugador jugador : partida.getJugadores()) {
            if (jugador.getId().equals(clientId)) {
                return jugador;
            }
        }
        throw new IllegalArgumentException("No se encontró un jugador con el clientId proporcionado.");
    }
}
