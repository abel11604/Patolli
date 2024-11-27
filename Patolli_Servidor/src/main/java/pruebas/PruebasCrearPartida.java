/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;

/**
 *
 * @author abelc
 */
public class PruebasCrearPartida {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {            
            // Conexión al servidor
            String servidorHost = "localhost";
            int puerto = 8080;
            Socket socket = new Socket(servidorHost, puerto);

            System.out.println("Conectado al servidor en " + servidorHost + ":" + puerto);

            // Hilo para escuchar mensajes del servidor
            Thread listenerThread = new Thread(() -> {
                try {
                    // Configuración para deserializar mensajes del servidor
                    ObjectMapper objectMapper = new ObjectMapper();
                    InputStream inputStream = socket.getInputStream();
                    MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(inputStream);

                    // Escuchar mensajes mientras la conexión esté activa
                    while (!socket.isClosed()) {
                        if (unpacker.hasNext()) {
                            String responseJson = unpacker.unpackString();
                            Map<String, Object> response = objectMapper.readValue(responseJson, Map.class);

                            // Procesar mensaje recibido
                            System.out.println("Mensaje recibido del servidor:");
                            response.forEach((key, value) -> System.out.println(key + ": " + value));
                        } else {
                            // Esperar brevemente para evitar consumir CPU innecesariamente
                            Thread.sleep(100);
                        }
                    }
                } catch (Exception e) {
                    if (!socket.isClosed()) {
                        System.err.println("Error al escuchar mensajes del servidor: " + e.getMessage());
                    }
                }
            });

            listenerThread.start();

            // Código para enviar datos al servidor
            ObjectMapper objectMapper = new ObjectMapper();
            OutputStream outputStream = socket.getOutputStream();

            // Ejemplo de enviar datos al servidor (Crear partida)
            Map<String, Object> crearPartida = Map.of(
                    "accion", "CREAR_PARTIDA",
                    "tamanoTablero", 68,
                    "numFichas", 4,
                    "fondoApuesta", 1000,
                    "cantidadAPagar", 100,
                    "nombreHost", "Jugador1"
            );

            String jsonData = objectMapper.writeValueAsString(crearPartida);
            MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
            packer.packString(jsonData);
            packer.close();

            outputStream.write(packer.toByteArray());
            outputStream.flush();

            System.out.println("Datos enviados al servidor para crear la partida.");

            // Mantén la conexión abierta para escuchar mensajes
            System.out.println("Presiona ENTER para salir.");
            System.in.read();

            // Cerrar la conexión cuando termine
            socket.close();
            listenerThread.interrupt();
            System.out.println("Conexión cerrada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
