/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;

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

            // Crear datos para enviar al servidor (simula crear una partida)
            Map<String, Object> data = Map.of(
                    "accion", "CREAR_PARTIDA",
                    "tamanoTablero", 10,
                    "numFichas", 4,
                    "fondoApuesta", 1000,
                    "cantidadAPagar", 100,
                    "nombreHost", "Jugador1"
            );

            // Serializar el mapa a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(data);

            // Usar MessagePack para empacar el JSON como mensaje
            MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
            packer.packString(jsonData);
            packer.close();

            // Enviar el mensaje al servidor
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(packer.toByteArray());
            outputStream.flush();

            System.out.println("Datos enviados al servidor para crear la partida.");

            // Mantener la conexión abierta para esperar respuesta (opcional)
            Thread.sleep(5000);

            // Cerrar el socket después de la espera
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
