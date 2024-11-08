/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author abelc
 */
public class Casilla {
    private String tipo;
    private Jugador ocupadoPor;
    private int numCasilla;

    public Casilla() {
    }

    public Casilla(String tipo, Jugador ocupadoPor, int numCasilla) {
        this.tipo = tipo;
        this.ocupadoPor = ocupadoPor;
        this.numCasilla = numCasilla;
    }

    
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Jugador getOcupadoPor() {
        return ocupadoPor;
    }

    public void setOcupadoPor(Jugador ocupadoPor) {
        this.ocupadoPor = ocupadoPor;
    }

    public int getNumCasilla() {
        return numCasilla;
    }

    public void setNumCasilla(int numCasilla) {
        this.numCasilla = numCasilla;
    }
    
    
    
}