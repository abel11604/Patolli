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
        this.partida.setFondo(fondoApuesta);
        this.partida.setCasillas(CasillasFactory.crearCasillas(casillaPorAspa));
        this.partida.setJugadores(jugadores);
        cliente.crearPartida(partida);
    }

    private void asignarHost() {
        hostPartida.setColor("Blanco");
        hostPartida.setFondoApuesta(fondoApuesta);
        hostPartida.setFichas(FichaFactory.generarFichas(numFichas, hostPartida));
        jugadores.add(hostPartida);
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
