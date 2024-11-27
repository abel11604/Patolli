/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.util.ArrayList;
import java.util.List;
import modelo.FichaModelo;
import modelo.JugadorModelo;

/**
 *
 * @author abelc
 */
public class FichaFactory {

    /**
     * Genera una lista de fichas asociadas a un jugador.
     *
     * @param numFichas Número de fichas a generar.
     * @param jugador Jugador al que estarán asociadas las fichas.
     * @return Lista de fichas creadas.
     */
    public static List<FichaModelo> generarFichas(int numFichas, JugadorModelo jugador) {

        List<FichaModelo> fichas = new ArrayList<>();
        String color = jugador.getColor().toLowerCase();

        for (int i = 1; i <= numFichas; i++) {
            String idFicha = "f" + i + color.substring(0, 1).toUpperCase() + color.substring(1); // Ej: f1Blanco
            fichas.add(new FichaModelo(idFicha, null, jugador));
        }

        return fichas;
    }
}
