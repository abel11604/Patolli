/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunicacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Jugador;
import exceptions.PatolliServerException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;

/**
 *
 * @author abelc
 */
public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final HandlerActions handlerActions; // No Singleton, instancia pasada por constructor
    private final String clientId;

    public ClientHandler(Socket clientSocket, String clientId, HandlerActions handlerActions) {
        this.clientSocket = clientSocket;
        this.handlerActions = handlerActions; // Instancia específica de HandlerActions
        this.clientId = clientId;
    }

    @Override
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream(); MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(inputStream)) {

            // Agregar cliente a ClientManager
            ClientManager.addClient(clientSocket, clientId, new Jugador());

            // Ciclo principal para escuchar mensajes
            while (!clientSocket.isClosed()) {
                if (unpacker.hasNext()) {
                    String json = unpacker.unpackString();
                    Map<String, Object> data = new ObjectMapper().readValue(json, Map.class);
                    data.put("clientId", clientId); // Añadir clientId al mensaje

                    try {
                        // Delegar la acción al HandlerActions
                        handlerActions.handle(clientSocket, data);
                    } catch (Exception ex) {
                        // Manejo general de excepciones inesperadas
                        System.err.printf("Error inesperado en el cliente %s: %s%n", clientId, ex.getMessage());
                    }
                    // Manejo controlado de PatolliServerException

                }
            }
        } catch (IOException e) {
            System.err.printf("Error de conexión con el cliente %s: %s%n", clientId, e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    private void cerrarConexion() {
        try {
            ClientManager.removeClient(clientSocket); // Eliminar cliente de ClientManager
            if (!clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
        }
    }
}
