/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunicacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;
import modelo.JugadorModelo;
import modelo.PartidaModelo;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessagePacker;
import org.msgpack.core.MessageUnpacker;

/**
 *
 * @author abelc
 */
public class ClientConnection {

    private static ClientConnection instance = null;

    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Thread listeningThread;
    private boolean running = false;

    // ObjectMapper for JSON serialization/deserialization
    private ObjectMapper objectMapper;

    // Listener for incoming messages
    private MessageListener messageListener;

    // Private constructor for singleton
    private ClientConnection() {
        objectMapper = new ObjectMapper();
    }

    // Method to get the singleton instance
    public static synchronized ClientConnection getInstance() {
        if (instance == null) {
            instance = new ClientConnection();
        }
        return instance;
    }

    // Method to establish the connection to the server
    public boolean connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            startListening();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to close the connection
    public void disconnect() {
        running = false;
        if (listeningThread != null) {
            listeningThread.interrupt();
        }
        try {
            if (socket != null) {
                socket.close();
            }
            socket = null;
            outputStream = null;
            inputStream = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to start listening for incoming messages
    private void startListening() {
        running = true;
        listeningThread = new Thread(() -> {
            try {
                MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(inputStream);
                while (running) {
                    if (unpacker.hasNext()) {
                        String jsonMessage = unpacker.unpackString();
                        // Deserialize the JSON message to a Map
                        Map<String, Object> message = objectMapper.readValue(jsonMessage, Map.class);
                        // Notify the listener on the Event Dispatch Thread
                        if (messageListener != null) {
                            SwingUtilities.invokeLater(() -> {
                                messageListener.onMessageReceived(message);
                            });
                        }
                    } else {
                        // Sleep briefly to avoid busy waiting
                        Thread.sleep(100);
                    }
                }
                unpacker.close();
            } catch (IOException e) {
                if (running) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                // Thread interrupted, exit gracefully
            }
        });
        listeningThread.start();
    }

    // Method to send a message to the server
    public synchronized void sendMessage(Map<String, Object> data) {
        if (socket == null || outputStream == null) {
            System.out.println("Not connected to server.");
            return;
        }
        try {
            // Serialize the data to JSON
            String json = objectMapper.writeValueAsString(data);
            // Pack the JSON string with MessagePack
            MessagePacker packer = MessagePack.newDefaultPacker(outputStream);
            packer.packString(json);
            packer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Envía una solicitud para crear una nueva partida con el nombre del
     * jugador especificado.
     *
     * @param nombreJugador el nombre del jugador que crea la partida
     */
    public void crearPartida(PartidaModelo partida) {
        Map<String, Object> data = new HashMap<>();
        data.put("accion", "CREAR_PARTIDA");
        data.put("tamanoTablero", partida.getCasillas().size());
        data.put("numFichas", partida.getJugadores().get(0).getFichas().size());
        data.put("fondoApuesta", partida.getJugadores().get(0).getFondoApuesta());
        data.put("cantidadAPagar", partida.getApuesta());
        data.put("nombreHost", partida.getJugadores().get(0).getNombre());
        sendMessage(data);
    }

    public void unirseAPartida(String codigoAcceso, JugadorModelo jugadorAUnirse) {
        Map<String, Object> data = new HashMap<>();
        data.put("accion", "UNIRSE_PARTIDA");
        data.put("codigoAcceso", codigoAcceso);
        data.put("nombre", jugadorAUnirse.getNombre());
        sendMessage(data);
    }

    public void iniciarPartida(String codigoAcceso) {
        Map<String, Object> data = new HashMap<>();
        data.put("accion", "INICIAR_PARTIDA");
        data.put("codigoAcceso", codigoAcceso);

        sendMessage(data);
    }

    /**
     * Establece el oyente de mensajes para los mensajes entrantes del servidor.
     *
     * @param listener el oyente que manejará los mensajes entrantes
     */
    public void setMessageListener(MessageListener listener) {
        this.messageListener = listener;
    }

    /**
     * Interfaz que representa un oyente para los mensajes entrantes del
     * servidor.
     */
    public interface MessageListener {

        /**
         * Se llama cuando se recibe un mensaje del servidor.
         *
         * @param message el mensaje recibido, representado como un Map
         */
        void onMessageReceived(Map<String, Object> message);
    }
}
