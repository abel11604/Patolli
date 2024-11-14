/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;

import entidades.Casilla;
import entidades.Ficha;
import entidades.Juego;
import entidades.Jugador;
import java.util.ArrayList;
import java.util.List;

/**
 * //
 *
 *
 * @author abelc
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//       List<Jugador> jugadores = new ArrayList<>();
//jugadores.add(new Jugador("Jugador1", "Blanco", 1000)); // Fondo inicial 1000
//jugadores.add(new Jugador("Jugador2", "Amarillo", 1000));
//
// Crear la partida y establecer la lista de jugadores y el monto de apuesta
//Juego partida = new Juego(jugadores, new ArrayList<>(), 100, true);
//
// Establecer la partida en ControlPartida
//ControlPartida control = ControlPartida.getInstance();
//control.setPartida(partida);
//
// Obtener un jugador de la lista de la partida y pasarlo a cobrarApuesta
//Jugador jugador = partida.getJugadores().get(0); // Por ejemplo, el primer jugador
//control.cobrarApuesta(jugador);
//verifica si se actualiza la partida
//        for (Jugador j:control.getPartida().getJugadores()) {
//            System.out.println(j.getFondoApuesta());
//        }
        // Crear una instancia del controlador
        ControlConfigurarPartida configurador = ControlConfigurarPartida.getInstance();

        // Configurar la partida
        configurador.setNumFichas(3); // Ejemplo de número de fichas
        configurador.setCasillaPorAspa(8); // Configuración de casillas por aspa (8, 10, o 14)
        configurador.setFondo(100); // Fondo de apuesta inicial
        configurador.setApuesta(20); // Monto de la apuesta

        // Crear lista de jugadores y configurarla en el controlador
        List<Jugador> jugadores = new ArrayList<>();
       //jugador1
        List<Ficha> fichas = new ArrayList<>();
        Ficha ficha = new Ficha();
        Jugador jugador1 = new Jugador();
        ficha.setCasillaActual(null);
        jugador1.setColor("Cafe");
        jugador1.setFondoApuesta(100);
        jugador1.setNombre("j1");
        ficha.setJugador(jugador1);
        fichas.add(ficha);
        jugador1.setFichas(fichas);
        jugadores.add(jugador1);
        //jugador2
        List<Ficha> fichas2 = new ArrayList<>();
        Ficha ficha2 = new Ficha();
        Jugador jugador2 = new Jugador();
        ficha2.setCasillaActual(null);
        jugador2.setColor("Blanco");
        jugador2.setFondoApuesta(100);
        jugador2.setNombre("j2");
        ficha2.setJugador(jugador2);
        fichas2.add(ficha2);
        jugador2.setFichas(fichas2);
        jugadores.add(jugador2);
        configurador.setJugadores(jugadores);
        
        // Crear la partida
        Juego partida = configurador.crearPartida();
        ControlPartida c = ControlPartida.getInstance();
        c.setPartida(partida);
        c.reiniciarFicha(jugador1.getFichas().get(0));
//         Imprimir todas las casillas generadas
//        System.out.println("Antes de avanzar");
//        for (Casilla casilla : c.getPartida().getCasillas()) {
//            String ocupadaPor = (casilla.getOcupadoPor() != null)
//                    ? casilla.getOcupadoPor().getJugador().getNombre()
//                    : "no ocupado";
//            System.out.println("numC: " + casilla.getNumCasilla() + " tipo: " + casilla.getTipo() + " ocupadaPor: " + ocupadaPor);
//        }
//
//        c.avanzarCasillas(18, jugador1.getFichas().get(0));
//        System.out.println("Despues de avanzar");
//          for (Casilla casilla : c.getPartida().getCasillas()) {
//            String ocupadaPor = (casilla.getOcupadoPor() != null)
//                    ? casilla.getOcupadoPor().getJugador().getNombre()
//                    : "no ocupado";
//            System.out.println("numC: " + casilla.getNumCasilla() + " tipo: " + casilla.getTipo() + " ocupadaPor: " + ocupadaPor);
//        }
//        
//        System.out.println("otro jugador");
//        c.reiniciarFicha(ficha2);
//            for (Casilla casilla : c.getPartida().getCasillas()) {
//            String ocupadaPor = (casilla.getOcupadoPor() != null)
//                    ? casilla.getOcupadoPor().getJugador().getNombre()
//                    : "no ocupado";
//            System.out.println("numC: " + casilla.getNumCasilla() + " tipo: " + casilla.getTipo() + " ocupadaPor: " + ocupadaPor);
//        }
//            
//        c.avanzarCasillas(2, ficha2);
//        System.out.println("mover la ficha 2");
//            for (Casilla casilla : c.getPartida().getCasillas()) {
//            String ocupadaPor = (casilla.getOcupadoPor() != null)
//                    ? casilla.getOcupadoPor().getJugador().getNombre()
//                    : "no ocupado";
//            System.out.println("numC: " + casilla.getNumCasilla() + " tipo: " + casilla.getTipo() + " ocupadaPor: " + ocupadaPor);
//        }
//            c.avanzarCasillas(1, ficha);
//            System.out.println("prueba de reinicio de ficha");
//                 for (Casilla casilla : c.getPartida().getCasillas()) {
//            String ocupadaPor = (casilla.getOcupadoPor() != null)
//                    ? casilla.getOcupadoPor().getJugador().getNombre()
//                    : "no ocupado";
//            System.out.println("numC: " + casilla.getNumCasilla() + " tipo: " + casilla.getTipo() + " ocupadaPor: " + ocupadaPor);
//        }
//            
//    }
}
}
