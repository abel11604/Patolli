/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;

import entidades.Juego;
import entidades.Jugador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abelc
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       List<Jugador> jugadores = new ArrayList<>();
jugadores.add(new Jugador("Jugador1", "Blanco", 1000)); // Fondo inicial 1000
jugadores.add(new Jugador("Jugador2", "Amarillo", 1000));

// Crear la partida y establecer la lista de jugadores y el monto de apuesta
Juego partida = new Juego(jugadores, new ArrayList<>(), 100, true);

// Establecer la partida en ControlPartida
ControlPartida control = ControlPartida.getInstance();
control.setPartida(partida);

// Obtener un jugador de la lista de la partida y pasarlo a cobrarApuesta
Jugador jugador = partida.getJugadores().get(0); // Por ejemplo, el primer jugador
control.cobrarApuesta(jugador);
//verifica si se actualiza la partida
        for (Jugador j:control.getPartida().getJugadores()) {
            System.out.println(j.getFondoApuesta());
        }
    }
    
}
