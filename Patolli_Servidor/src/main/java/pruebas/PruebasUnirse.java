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
public class PruebasUnirse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try {
            // Datos para conectarse al servidor
            String servidorHost = "localhost";
            int puerto = 8080;

            // Código de acceso de la partida a la que queremos unirnos
            String codigoAcceso = "44384"; // Reemplaza con el código de acceso real de la partida

            // Crear socket de conexión al servidor
            Socket socket = new Socket(servidorHost, puerto);

            // Datos que se enviarán al servidor para unirse a la partida
            Map<String, Object> data = Map.of(
                    "accion", "UNIRSE_PARTIDA",
                    "codigoAcceso", codigoAcceso,
                    "nombre", "gogogo"
            );

            // Serializar el mapa a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(data);

            // Usar MessagePack para empaquetar el JSON como mensaje
            MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
            packer.packString(jsonData);
            packer.close();

            // Enviar el mensaje al servidor
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(packer.toByteArray());
            outputStream.flush();

            System.out.println("Datos enviados al servidor para unirse a la partida.");

            // Esperar y recibir la respuesta del servidor
            InputStream inputStream = socket.getInputStream();
            MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(inputStream);

            if (unpacker.hasNext()) {
                String responseJson = unpacker.unpackString();
                Map<String, Object> response = objectMapper.readValue(responseJson, Map.class);

                // Procesar la respuesta del servidor
                System.out.println("Respuesta del servidor:");
                response.forEach((key, value) -> System.out.println(key + ": " + value));
            } else {
                System.out.println("No se recibió respuesta del servidor.");
            }

            // Cerrar conexiones
            unpacker.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
    

