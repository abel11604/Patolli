/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunicacion;

import dominio.Ficha;
import dominio.Jugador;
import dominio.Partida;
import enums.ResultadoMovimiento;
import java.net.Socket;
import java.util.Map;
import negocio.CrearPartidaBO;
import negocio.GestionarPartidaBO;
import negocio.PartidaLogicaBO;

/**
 *
 * @author abelc
 */
public class HandlerActions {
    
    private final GestionarPartidaBO gestionarPartidaBO;

    /**
     * Constructor para inicializar dependencias.
     */
    public HandlerActions() {
        this.gestionarPartidaBO = GestionarPartidaBO.getInstance();
    }

    /**
     * Método principal para manejar las acciones de los clientes.
     *
     * @param clientSocket El socket del cliente que envía la acción.
     * @param data         El mapa de datos enviado por el cliente.
     */
    public void handle(Socket clientSocket, Map<String, Object> data) {
        try {
            // Validar la acción solicitada
            String accion = (String) data.get("accion");
            if (accion == null || accion.isEmpty()) {
                throw new IllegalArgumentException("La acción solicitada no es válida.");
            }

            // Identificar y procesar la acción
            switch (accion) {
                case "CREAR_PARTIDA":
                    handleCrearPartida(clientSocket, data);
                    break;

                case "UNIRSE_PARTIDA":
                    handleUnirsePartida(clientSocket, data);
                    break;

                case "INICIAR_PARTIDA":
                    handleIniciarPartida(clientSocket, data);
                    break;

                case "TIRAR_CAÑA":
                    handleTirarCaña(clientSocket, data);
                    break;

                case "MOVER_FICHA":
                    handleMoverFicha(clientSocket, data);
                    break;

                default:
                    throw new IllegalArgumentException("Acción desconocida: " + accion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            enviarError(clientSocket, e.getMessage());
        }
    }

    /**
     * Maneja la creación de una partida.
     */
    private void handleCrearPartida(Socket clientSocket, Map<String, Object> data) {
        String clientId = obtenerClientId(clientSocket);
        CrearPartidaBO crearPartidaBO = new CrearPartidaBO();
        Map<String, Object> response = crearPartidaBO.crearPartida(data, clientId);
        gestionarPartidaBO.registrarPartida(crearPartidaBO.getPartida());
        MessageUtil.enviarMensaje(clientSocket, response);
    }

    /**
     * Maneja la unión a una partida existente.
     */
    private void handleUnirsePartida(Socket clientSocket, Map<String, Object> data) {
        String clientId = obtenerClientId(clientSocket);
        Map<String, Object> response = gestionarPartidaBO.unirseAPartida(data, clientId);
        MessageUtil.enviarMensaje(clientSocket, response);
    }

    /**
     * Maneja el inicio de una partida.
     */
    private void handleIniciarPartida(Socket clientSocket, Map<String, Object> data) {
        String clientId = obtenerClientId(clientSocket);
        Map<String, Object> response = gestionarPartidaBO.iniciarPartida(data, clientId);
        MessageUtil.enviarMensaje(clientSocket, response);
    }

    /**
     * Maneja el lanzamiento de las cañas.
     */
    private void handleTirarCaña(Socket clientSocket, Map<String, Object> data) {
        String clientId = obtenerClientId(clientSocket);
        Partida partida = gestionarPartidaBO.obtenerPartida((String) data.get("codigoAcceso"));
        PartidaLogicaBO partidaLogicaBO = new PartidaLogicaBO(partida);
        Map<String, Object> response = partidaLogicaBO.lanzamientoCañas(clientId);
        MessageUtil.enviarMensaje(clientSocket, response);
    }

    /**
     * Maneja el movimiento de una ficha.
     */
    private void handleMoverFicha(Socket clientSocket, Map<String, Object> data) {
        String clientId = obtenerClientId(clientSocket);
        Partida partida = gestionarPartidaBO.obtenerPartida((String) data.get("codigoAcceso"));
        PartidaLogicaBO partidaLogicaBO = new PartidaLogicaBO(partida);

        String idFicha = (String) data.get("idFicha");
        int numCasillas = (int) data.get("numCasillas");
        Jugador jugador = partidaLogicaBO.obtenerJugadorPorId(clientId);
        Ficha ficha = jugador.getFichaById(idFicha);

        ResultadoMovimiento resultado = partidaLogicaBO.avanzarCasillas(numCasillas, ficha);

        // Crear mensaje para notificar a todos los jugadores
        Map<String, Object> mensaje = Map.of(
                "accion", "MOVER_FICHA",
                "jugador", jugador.getNombre(),
                "idFicha", idFicha,
                "resultado", resultado.name()
        );

        for (Jugador jugadorEnPartida : partida.getJugadores()) {
            Socket socketJugador = ClientManager.getClientSocket(jugadorEnPartida.getId());
            if (socketJugador != null) {
                MessageUtil.enviarMensaje(socketJugador, mensaje);
            }
        }
    }

    /**
     * Envía un mensaje de error al cliente.
     */
    private void enviarError(Socket clientSocket, String mensajeError) {
        Map<String, Object> mensaje = Map.of(
                "accion", "ERROR",
                "mensaje", mensajeError
        );
        MessageUtil.enviarMensaje(clientSocket, mensaje);
    }

    /**
     * Obtiene el identificador único del cliente a partir de su socket.
     */
    private String obtenerClientId(Socket clientSocket) {
        return ClientManager.getClientId(clientSocket);
    }
}
