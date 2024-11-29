/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.Jugador;
import dominio.Partida;
import dominio.Ficha;
import comunicacion.ClientManager;
import comunicacion.MessageUtil;
import enums.EstadosPartida;
import exceptions.PatolliServerException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author abelc
 */
public class GestionarPartidaBO {

    private static GestionarPartidaBO instance; // Instancia única de la clase

    private final Map<String, Partida> partidasActivas; // Almacena las partidas activas

    /**
     * Constructor privado para aplicar el patrón Singleton.
     */
    private GestionarPartidaBO() {
        partidasActivas = new HashMap<>();
    }

    /**
     * Obtiene la instancia única de la clase.
     *
     * @return Instancia Singleton de GestionarPartidaBO.
     */
    public static synchronized GestionarPartidaBO getInstance() {
        if (instance == null) {
            instance = new GestionarPartidaBO();
        }
        return instance;
    }

    /**
     * Registra una nueva partida en el sistema.
     *
     * @param partida Partida creada.
     */
    public void registrarPartida(Partida partida) {
        if (partida == null || partida.getCodigoAcceso() == null || partida.getCodigoAcceso().isEmpty()) {
            throw new IllegalArgumentException("La partida no tiene un código de acceso válido.");
        }
        partidasActivas.put(partida.getCodigoAcceso(), partida);
    }

    /**
     * Permite a un jugador unirse a una partida existente.
     *
     * @param data Datos enviados por el cliente en un HashMap (clave-valor).
     * @param clientId Identificador del cliente que se une.
     * @return Respuesta con los detalles de la partida y el jugador.
     * @throws PatolliServerException Si ocurre algún problema durante el
     * proceso.
     */
    public Map<String, Object> unirseAPartida(Map<String, Object> data, String clientId) throws PatolliServerException {
        validarCliente(clientId); // Validación inicial del cliente

        String codigoAcceso = (String) data.get("codigoAcceso");
        String nombre = (String) data.get("nombre");

        // Validaciones de entrada
        if (codigoAcceso == null || codigoAcceso.isEmpty()) {
            throw new PatolliServerException("El código de acceso no puede estar vacío.");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new PatolliServerException("El nombre no puede estar vacío.");
        }

        // Buscar partida por código de acceso
        Partida partida = partidasActivas.get(codigoAcceso);
        if (partida == null) {
            throw new PatolliServerException("No existe una partida con el código proporcionado.");
        }

        // Validar si la partida ya tiene 4 jugadores
        if (partida.getJugadores().size() >= 4) {
            throw new PatolliServerException("La partida ya está completa. No se pueden unir más jugadores.");
        }

        // Validar si el estado de la partida es ACTIVA
        if ("ACTIVA".equalsIgnoreCase(partida.getEstado().name())) {
            throw new PatolliServerException("No puedes unirte a una partida que ya está en progreso.");
        }

        // Validar nombre de jugador único
        if (partida.getJugadores().stream().anyMatch(jugador -> jugador.getNombre().equalsIgnoreCase(nombre))) {
            throw new PatolliServerException("El nombre del jugador ya está en uso en esta partida.");
        }

        // Validar existencia de un jugador base
        Jugador jugadorBase = partida.getJugadores().isEmpty() ? null : partida.getJugadores().get(0);
        if (jugadorBase == null) {
            throw new PatolliServerException("No se puede unir un jugador a una partida sin jugadores iniciales.");
        }

        // Crear y configurar el nuevo jugador
        Jugador nuevoJugador = crearNuevoJugador(clientId, nombre, partida, jugadorBase);

        // Agregar jugador a la partida y registrar cliente
        partida.getJugadores().add(nuevoJugador);
        ClientManager.addClient(ClientManager.getClientSocket(clientId), clientId, nuevoJugador);

        // Notificar a los jugadores existentes
        notificarJugadores(partida, nuevoJugador);

        // Log detallado
        System.out.printf("Jugador unido: %s, Código de acceso: %s, ID: %s%n", nombre, codigoAcceso, clientId);

        // Responder con detalles de la partida
        return crearRespuestaUnirsePartida(partida, nuevoJugador, jugadorBase);
    }

    private Jugador crearNuevoJugador(String clientId, String nombre, Partida partida, Jugador jugadorBase) {
        Jugador nuevoJugador = new Jugador(
                clientId,
                nombre,
                asignarColor(partida),
                jugadorBase.getFondoApuesta()
        );
        nuevoJugador.setFichas(crearFichas(jugadorBase.getFichas().size(), nuevoJugador));
        return nuevoJugador;
    }

    private void notificarJugadores(Partida partida, Jugador nuevoJugador) {
        Map<String, Object> mensajeNotificacion = Map.of(
                "accion", "JUGADOR_UNIDO",
                "nombre", nuevoJugador.getNombre(),
                "color", nuevoJugador.getColor(),
                "mensaje", "Un nuevo jugador se ha unido a la partida."
        );

        for (Jugador jugador : partida.getJugadores()) {
            if (!jugador.getId().equals(nuevoJugador.getId())) {
                try {
                    Socket clientSocket = ClientManager.getClientSocket(jugador.getId());
                    if (clientSocket != null) {
                        MessageUtil.enviarMensaje(clientSocket, mensajeNotificacion);
                    }
                } catch (Exception e) {
                    System.err.printf("Error notificando a jugador %s: %s%n", jugador.getNombre(), e.getMessage());
                }
            }
        }
    }

    private Map<String, Object> crearRespuestaUnirsePartida(Partida partida, Jugador nuevoJugador, Jugador jugadorBase) {
        List<Map<String, Object>> jugadoresExistentes = new ArrayList<>();
        for (Jugador jugador : partida.getJugadores()) {
            jugadoresExistentes.add(Map.of(
                    "id", jugador.getId(),
                    "nombre", jugador.getNombre(),
                    "color", jugador.getColor(),
                    "fondoApuesta", jugador.getFondoApuesta()
            ));
        }

        Map<String, Object> detallesPartida = Map.of(
                "codigoAcceso", partida.getCodigoAcceso(),
                "numCasillas", partida.getCasillas().size(),
                "jugadores", jugadoresExistentes,
                "numFichas", jugadorBase.getFichas().size(),
                "cantidadAPagar", partida.getApuesta()
        );

        return Map.of(
                "accion", "UNIRSE_PARTIDA",
                "codigoAcceso", partida.getCodigoAcceso(),
                "idJugador", nuevoJugador.getId(),
                "color", nuevoJugador.getColor(),
                "fondoApuesta", nuevoJugador.getFondoApuesta(),
                "partida", detallesPartida,
                "mensaje", "Te has unido a la partida exitosamente."
        );
    }

    /**
     * Inicia una partida cuando el host lo decide.
     *
     * @param data Datos enviados por el cliente en un HashMap (clave-valor).
     * @param clientId Identificador del cliente host que inicia la partida.
     * @return Respuesta indicando que la partida ha iniciado.
     */
    public Map<String, Object> iniciarPartida(Map<String, Object> data, String clientId) {
        // Validar que el cliente está conectado
        validarCliente(clientId);

        // Extraer datos del mapa
        String codigoAcceso = (String) data.get("codigoAcceso");

        Partida partida = partidasActivas.get(codigoAcceso);
        if (partida == null) {
            throw new IllegalArgumentException("No existe una partida con el código proporcionado.");
        }

        // Validar que el cliente es el host
        Jugador host = partida.getJugadores().get(0);
        if (!host.getId().equals(clientId)) {
            throw new IllegalStateException("Solo el host puede iniciar la partida.");
        }

        // Cambiar el estado de la partida a activa
        partida.setEstado(EstadosPartida.ACTIVA);

        // Crear mensaje de notificación para todos los jugadores menos el host
        Map<String, Object> mensaje = Map.of(
                "accion", "PARTIDA_INICIADA",
                "codigoAcceso", codigoAcceso,
                "mensaje", "La partida ha comenzado."
        );

        // Enviar el mensaje a todos los jugadores excepto al host
        for (Jugador jugador : partida.getJugadores()) {
            if (!jugador.getId().equals(clientId)) { // Omitir al host
                Socket clientSocket = ClientManager.getClientSocket(jugador.getId());
                if (clientSocket != null) {
                    MessageUtil.enviarMensaje(clientSocket, mensaje);
                } else {
                    System.err.println("No se encontró un socket para el jugador con ID: " + jugador.getId());
                }
            }
        }

        // Respuesta para el cliente host
        return Map.of(
                "accion", "PARTIDA_INICIADA",
                "estado", "ACTIVA",
                "mensaje", "La partida ha sido iniciada exitosamente."
        );
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
     * Asigna un color único a un nuevo jugador en una partida.
     *
     * @param partida Partida en curso.
     * @return Color asignado al jugador.
     */
    private String asignarColor(Partida partida) {
        List<String> coloresDisponibles = List.of("Blanco", "Amarillo", "Naranja", "Café");
        Set<String> coloresUsados = new HashSet<>();
        for (Jugador jugador : partida.getJugadores()) {
            coloresUsados.add(jugador.getColor());
        }
        for (String color : coloresDisponibles) {
            if (!coloresUsados.contains(color)) {
                return color;
            }
        }
        throw new IllegalStateException("No hay colores disponibles para asignar.");
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
        String color = jugador.getColor().toLowerCase(); // Convertir color a minúsculas para consistencia

        for (int i = 1; i <= numFichas; i++) {
            String idFicha = "f" + i + color.substring(0, 1).toUpperCase() + color.substring(1); // Ej: f1Blanco
            fichas.add(new Ficha(idFicha, null, jugador));
        }
        return fichas;
    }

    /**
     * Obtiene una partida activa por su código de acceso.
     *
     * @param codigoAcceso Código de acceso de la partida.
     * @return La partida correspondiente.
     */
    public Partida obtenerPartida(String codigoAcceso) {
        return partidasActivas.get(codigoAcceso);
    }

    /**
     * Elimina una partida de la lista de partidas activas.
     *
     * @param codigoAcceso Código de acceso de la partida a eliminar.
     */
    public void eliminarPartida(String codigoAcceso) {
        partidasActivas.remove(codigoAcceso);
    }

    /**
     * Devuelve el listado de partidas activas.
     *
     * @return Map de las partidas activas.
     */
    public Map<String, Partida> obtenerPartidasActivas() {
        return partidasActivas;
    }
}
