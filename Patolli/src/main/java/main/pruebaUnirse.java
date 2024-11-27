/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import control.ControlUnirseAPartida;
import modelo.JugadorModelo;

/**
 *
 * @author abelc
 */
public class pruebaUnirse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // Crear la instancia del controlador para unirse a la partida
        ControlUnirseAPartida controlUnirse = ControlUnirseAPartida.getInstance();

        // Configurar los datos del jugador que desea unirse
        JugadorModelo jugador = new JugadorModelo();
        jugador.setNombre("Jugador 2");

        // Código de acceso proporcionado por la partida
        String codigoAcceso = "95586"; // Este código debe existir en el sistema

        // Llamar al método para unirse a la partida
        controlUnirse.UnirseAPartida(codigoAcceso, jugador);

        // Esperar activamente hasta que todos los datos sean procesados, con un tiempo límite de 10 segundos
        int tiempoEsperado = 0;
        while ((controlUnirse.getPartida().getCodigoAcceso() == null ||
                controlUnirse.getNumCasillas() == 0 ||
                controlUnirse.getNumFichas() == 0 ||
                controlUnirse.getJugadores().size() == 0) && tiempoEsperado < 10000) {
            try {
                Thread.sleep(100); // Esperar 100ms
                tiempoEsperado += 100;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Verificar si los datos de la partida fueron asignados dentro del tiempo límite
        if (controlUnirse.getPartida().getCodigoAcceso() != null &&
            controlUnirse.getNumCasillas() != 0 &&
            controlUnirse.getNumFichas() != 0 &&
            !controlUnirse.getJugadores().isEmpty()) {
            
            // Mostrar los datos de la partida
            System.out.println("Código de acceso de la partida: " + controlUnirse.getPartida().getCodigoAcceso());
            System.out.println("Número de casillas: " + controlUnirse.getNumCasillas());
            System.out.println("Número de fichas: " + controlUnirse.getNumFichas());

            // Mostrar la lista de jugadores
            System.out.println("\nJugadores en la partida:");
            for (JugadorModelo j : controlUnirse.getJugadores()) {
                System.out.println("Nombre: " + j.getNombre());
                System.out.println("Color: " + j.getColor());
                System.out.println("Fondo de apuesta: " + j.getFondoApuesta());
                System.out.println("---------------------------");
            }
        } else {
            System.out.println("Error: No se recibió respuesta completa del servidor dentro del tiempo límite.");
        }
    }
}
