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
     */
    public Map<String, Object> unirseAPartida(Map<String, Object> data, String clientId) {
        validarCliente(clientId);

        String codigoAcceso = (String) data.get("codigoAcceso");
        String nombre = (String) data.get("nombre");

        Partida partida = partidasActivas.get(codigoAcceso);
        if (partida == null) {
            throw new IllegalArgumentException("No existe una partida con el código proporcionado.");
        }

        Jugador nuevoJugador = new Jugador(
                clientId,
                nombre,
                asignarColor(partida),
                partida.getJugadores().get(0).getFondoApuesta()
        );
        nuevoJugador.setFichas(crearFichas(partida.getJugadores().size(), nuevoJugador));

        partida.getJugadores().add(nuevoJugador);

        ClientManager.addClient(ClientManager.getClientSocket(clientId), clientId, nuevoJugador);
         System.out.println("si me uni we"+nombre+codigoAcceso);
        return Map.of(
                "accion", "UNIRSE_PARTIDA",
                "codigoAcceso", codigoAcceso,
                "idJugador", nuevoJugador.getId(),
                "color", nuevoJugador.getColor(),
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

        // Validar que la partida existe
        Partida partida = partidasActivas.get(codigoAcceso);
        if (partida == null) {
            throw new IllegalArgumentException("No existe una partida con el código proporcionado.");
        }

        // Validar que el cliente es el host
        Jugador host = partida.getJugadores().get(0); // El primer jugador es el host
        if (!host.getId().equals(clientId)) {
            throw new IllegalStateException("Solo el host puede iniciar la partida.");
        }

        // Cambiar el estado de la partida a activa
        partida.setEstado(EstadosPartida.ACTIVA);

        // Crear mensaje de notificación para todos los jugadores
        Map<String, Object> mensaje = Map.of(
                "accion", "PARTIDA_INICIADA",
                "codigoAcceso", codigoAcceso,
                "mensaje", "La partida ha comenzado."
        );

        // Enviar el mensaje a todos los jugadores
        for (Jugador jugador : partida.getJugadores()) {
            Socket clientSocket = ClientManager.getClientSocket(jugador.getId());
            if (clientSocket != null) {
                MessageUtil.enviarMensaje(clientSocket, mensaje);
            } else {
                System.err.println("No se encontró un socket para el jugador con ID: " + jugador.getId());
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
