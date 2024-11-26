/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comunicacion;

import dominio.Jugador;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author abelc
 */
public class Servidor {

    private static final int PUERTO = 8080; 
    private static final int MAX_HILOS = 10; 

    private final ExecutorService threadPool;
    private final HandlerActions handlerActions; 
    private int clientCounter;

    public Servidor() {
        this.threadPool = Executors.newFixedThreadPool(MAX_HILOS); 
        this.handlerActions = new HandlerActions();
        this.clientCounter = 1; 
    }

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado. Escuchando en el puerto " + PUERTO);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Aceptar nuevas conexiones
                System.out.println("Nueva conexión de cliente desde: " + clientSocket.getInetAddress());

                // Generar un clientId único
                String clientId =Integer.toString(clientCounter++);

                ClientManager.addClient(clientSocket, clientId, new Jugador());

                
                threadPool.execute(new ClientHandler(clientSocket, clientId, handlerActions));
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            detener();
        }
    }

    public void detener() {
        try {
            System.out.println("Deteniendo el servidor...");
            threadPool.shutdown(); // Detener el ThreadPool de forma controlada
            System.out.println("Servidor detenido.");
        } catch (Exception e) {
            System.err.println("Error al detener el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }
}



