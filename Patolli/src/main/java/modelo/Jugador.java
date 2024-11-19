package modelo;

import java.util.List;

/**
 *
 * @author abelc
 */
public class Jugador {
    private String nombre;
    private String color;
    private List<Ficha> fichas;
    int fondoApuesta;

    public Jugador() {
    }

    public Jugador(String nombre, String color, List<Ficha> fichas, int fondoApuesta) {
        this.nombre = nombre;
        this.color = color;
        this.fichas = fichas;
        this.fondoApuesta = fondoApuesta;
    }

    public Jugador(String nombre, String color, int fondoApuesta) {
        this.nombre = nombre;
        this.color = color;
        this.fondoApuesta = fondoApuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    public int getFondoApuesta() {
        return fondoApuesta;
    }

    public void setFondoApuesta(int fondoApuesta) {
        this.fondoApuesta = fondoApuesta;
    }
    
}
