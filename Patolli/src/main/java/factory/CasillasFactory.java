/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.util.ArrayList;
import java.util.List;
import modelo.CasillaModelo;

/**
 *
 * @author abelc
 */
public class CasillasFactory {
     public static List<CasillaModelo> crearCasillas(int numCasillas) {
        List<CasillaModelo> casillas = new ArrayList<>();
        int contadorCasilla = 1;
        
        // Dependiendo del número de casillas, se genera el tablero
        if (numCasillas == 68||numCasillas == 8) {
            int casillasPorAspa = 16;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialBlanco", 5, 12, 8, 9);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialAmarillo", 5, 12, 8, 9);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialNaranja", 5, 12, 8, 9);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialCafe", 5, 12, 8, 9);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
        } else if (numCasillas == 84||numCasillas == 10) {
            int casillasPorAspa = 20;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialBlanco", 7, 14, 10, 11);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialAmarillo", 7, 14, 10, 11);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialNaranja", 7, 14, 10, 11);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialCafe", 7, 14, 10, 11);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
        } else if (numCasillas == 116||numCasillas == 14) {
            int casillasPorAspa = 28;
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialBlanco", 11, 18, 14, 15);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialAmarillo", 11, 18, 14, 15);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialNaranja", 11, 18, 14, 15);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
            contadorCasilla = generarCasillasAspa(casillas, contadorCasilla, casillasPorAspa, "inicialCafe", 11, 18, 14, 15);
            casillas.add(new CasillaModelo("Central", contadorCasilla++));
        }

        return casillas;
    }

    private static int generarCasillasAspa(List<CasillaModelo> casillas, int contadorCasilla, int casillasPorAspa,
            String inicialJugador, int apuestaPos1, int apuestaPos2, int dobleTurnoPos1, int dobleTurnoPos2) {
        for (int i = 1; i <= casillasPorAspa; i++) {
            if (i == 1) {
                casillas.add(new CasillaModelo(inicialJugador, contadorCasilla)); // CasillaModelo inicial del jugador
            } else if (i == apuestaPos1 || i == apuestaPos2) {
                casillas.add(new CasillaModelo("Apuesta", contadorCasilla)); // Casillas de Apuesta
            } else if (i == dobleTurnoPos1 || i == dobleTurnoPos2) {
                casillas.add(new CasillaModelo("DobleTurno", contadorCasilla)); // Casillas de DobleTurno
            } else {
                casillas.add(new CasillaModelo("normal", contadorCasilla)); // Casillas normales
            }
            contadorCasilla++;
        }
        return contadorCasilla;
    }
}
