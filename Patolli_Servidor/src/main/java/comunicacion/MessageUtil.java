/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunicacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author abelc
 */
public class MessageUtil {

    public static void enviarMensaje(Socket clientSocket, Map<String, Object> mensajeMap) {
        try {
            // Convertir el Map a una cadena JSON usando Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMensaje = objectMapper.writeValueAsString(mensajeMap);

            // Crear un MessageBufferPacker para empacar el JSON como una cadena
            MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
            packer.packString(jsonMensaje);  // Empacar el JSON como una cadena

            // Cerrar el packer para finalizar la escritura
            packer.close();

            // Enviar los datos empaquetados al cliente a través del socket
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(packer.toByteArray());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
