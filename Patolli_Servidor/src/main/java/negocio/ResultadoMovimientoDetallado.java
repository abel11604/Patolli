/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.Jugador;
import enums.ResultadoMovimiento;

/**
 *
 * @author abelc
 */
public class ResultadoMovimientoDetallado {
     private ResultadoMovimiento resultado;
    private Jugador jugadorEliminado;

    public ResultadoMovimientoDetallado() {
    }

    public ResultadoMovimientoDetallado(ResultadoMovimiento resultado, Jugador jugadorEliminado) {
        this.resultado = resultado;
        this.jugadorEliminado = jugadorEliminado;
    }

    public ResultadoMovimiento getResultado() {
        return resultado;
    }

    public Jugador getJugadorEliminado() {
        return jugadorEliminado;
    }
}
