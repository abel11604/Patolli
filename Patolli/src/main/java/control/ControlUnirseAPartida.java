/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import comunicacion.ClientConnection;
import comunicacion.ClientConnection.MessageListener;
import factory.CasillasFactory;
import factory.FichaFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.JugadorModelo;
import modelo.PartidaModelo;

/**
 *
 * @author abelc
 */
public class ControlUnirseAPartida implements IControlUnirseAPartida {

    private static ControlUnirseAPartida instance;
    private ClientConnection cliente;
    private PartidaModelo partida;
    private JugadorModelo jugadorAUnirse;
    private ClientConnection.MessageListener listener;
    private List<JugadorModelo> jugadores;
    private int numCasillas;
    private int numFichas;
    private String errorMessage;

    public ControlUnirseAPartida() {
        cliente = ClientConnection.getInstance();
        partida = new PartidaModelo();
        jugadorAUnirse = new JugadorModelo();
        jugadores = new ArrayList<>();
        this.listener = new MessageListener() {
            @Override
            public void onMessageReceived(Map<String, Object> message) {
                // Mostrar mensaje recibido para depuración
                System.out.println("Mensaje recibido del servidor: " + message);

                String accion = (String) message.get("accion");
                if (accion == null) {
                    System.err.println("Mensaje sin acción especificada.");
                    return;
                }

                switch (accion) {
                    case "ERROR" -> {
                        // Capturar el mensaje de error del servidor
                        errorMessage = (String) message.get("mensaje");
                    }
                    case "UNIRSE_PARTIDA" -> {
                        procesarUnirsePartida(message);
                    }
                    default -> {
                        System.err.println("Acción desconocida recibida: " + accion);
                    }
                }
            }
        };
        cliente.setMessageListener(listener);
        cliente.connect("localhost", 8080);

    }

    public static ControlUnirseAPartida getInstance() {
        if (instance == null) {
            instance = new ControlUnirseAPartida();
        }
        return instance;
    }

    @Override
    public void UnirseAPartida(String codigoAcceso, JugadorModelo jugadorAUnirse) {
        cliente.unirseAPartida(codigoAcceso, jugadorAUnirse);
    }

    @Override
    public PartidaModelo getPartida() {
        return partida;
    }

    @Override
    public void setJugador(JugadorModelo jugador) {
        this.jugadorAUnirse = jugador;
    }

    @Override
    public JugadorModelo getJugador() {
        return jugadorAUnirse;
    }

    public List<JugadorModelo> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorModelo> jugadores) {
        this.jugadores = jugadores;
    }

    public int getNumCasillas() {
        return numCasillas;
    }

    public void setNumCasillas(int numCasillas) {
        this.numCasillas = numCasillas;
    }

    private void asignarFichas(int cantidadFichas) {
        for (JugadorModelo jugador : jugadores) {
            jugador.setFichas(FichaFactory.generarFichas(numFichas, jugador));
        }
    }

    public int getNumFichas() {
        return numFichas;
    }

    public void setNumFichas(int numFichas) {
        this.numFichas = numFichas;
    }

    @Override
    public void generarPartida() {
        asignarFichas(numFichas);
        partida.setJugadores(jugadores);
        partida.setCasillas(CasillasFactory.crearCasillas(numCasillas));
    }

    private void procesarUnirsePartida(Map<String, Object> message) {
        // Procesar los datos de la respuesta
        String codigo = (String) message.get("codigoAcceso");
        if (codigo != null) {
            partida.setCodigoAcceso(codigo);
        }

        String colorJugador = (String) message.get("color");
        if (colorJugador != null) {
            jugadorAUnirse.setColor(colorJugador);
        }

        int fondoApuesta = (message.get("fondoApuesta") != null) ? (int) message.get("fondoApuesta") : 0;
        jugadorAUnirse.setFondoApuesta(fondoApuesta);

        // Obtener los datos de la partida, que están dentro del campo "partida"
        Map<String, Object> partidaData = (Map<String, Object>) message.get("partida");

        // Usar int en lugar de Integer, asignando 0 como valor predeterminado si el valor es nulo
        if (partidaData != null) {
            int numCasillasResponse = (partidaData.get("numCasillas") != null) ? (int) partidaData.get("numCasillas") : 0;
            numCasillas = numCasillasResponse;

            int numFichasResponse = (partidaData.get("numFichas") != null) ? (int) partidaData.get("numFichas") : 0;
            numFichas = numFichasResponse;

            int apuesta = (partidaData.get("cantidadAPagar") != null) ? (int) partidaData.get("cantidadAPagar") : 0;
            partida.setApuesta(apuesta);

            // Obtener la lista de jugadores
            List<Map<String, Object>> jugadoresRespuesta = (List<Map<String, Object>>) partidaData.get("jugadores");
            if (jugadoresRespuesta != null) {
                jugadores.clear();
                for (Map<String, Object> jugadorData : jugadoresRespuesta) {
                    JugadorModelo jugador = new JugadorModelo();
                    jugador.setNombre((String) jugadorData.get("nombre"));
                    jugador.setColor((String) jugadorData.get("color"));
                    jugador.setFondoApuesta((int) jugadorData.get("fondoApuesta")); // Usar int aquí también
                    jugadores.add(jugador);
                }
            }
        }
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
