/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import control.ControlCrearPartida;

import modelo.JugadorModelo;

/**
 *
 * @author abelc
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear la instancia del controlador de la partida
        ControlCrearPartida controlPartida = ControlCrearPartida.getInstance();

        // Configurar los parámetros de la partida
        controlPartida.setCasillaPorAspa(8);
        controlPartida.setNumFichas(2);  // Por ejemplo, 2 fichas por jugador
        controlPartida.setJugador(new JugadorModelo("Jugador 1")); // Configurar el jugador host
        controlPartida.setApuesta(100);

        // Crear la partida
        controlPartida.crearPartida();

        // Esperar activamente hasta que el código de acceso esté disponible, con un tiempo límite de 10 segundos
        int tiempoEsperado = 0;
        while (controlPartida.getPartida().getCodigoAcceso() == null && tiempoEsperado < 10000) {
            try {
                Thread.sleep(100); // Esperar 100ms
                tiempoEsperado += 100;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Verificar si el código de acceso fue asignado dentro del tiempo límite
        if (controlPartida.getPartida().getCodigoAcceso() != null) {
            System.out.println("Código de acceso de la partida: " + controlPartida.getPartida().getCodigoAcceso());
            System.out.println("Apuesta de la partida: " + controlPartida.getPartida().getApuesta());
        } else {
            System.out.println("Error: No se recibió el código de acceso dentro del tiempo límite.");
        }
    }
}
