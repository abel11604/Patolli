/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author abelc
 */
public class Casilla {

    private String tipo;
    private Ficha ocupadoPor;
    private int numCasilla;

    public Casilla() {
    }

    public Casilla(String tipo, int numCasilla) {
        this.tipo = tipo;
        this.ocupadoPor = null;
        this.numCasilla = numCasilla;

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Ficha getOcupadoPor() {
        return ocupadoPor;
    }

    public void setOcupadoPor(Ficha ocupadoPor) {
        this.ocupadoPor = ocupadoPor;
    }

    public int getNumCasilla() {
        return numCasilla;
    }

    public void setNumCasilla(int numCasilla) {
        this.numCasilla = numCasilla;
    }
    
}
