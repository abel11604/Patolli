package modelo;

import java.util.List;

/**
 *
 * @author abelc
 */
public class JugadorModelo {

    private String nombre;
    private String color;
    private List<FichaModelo> fichas;
    int fondoApuesta;

    public JugadorModelo() {
    }

    public JugadorModelo(String nombre, String color, List<FichaModelo> fichas, int fondoApuesta) {
        this.nombre = nombre;
        this.color = color;
        this.fichas = fichas;
        this.fondoApuesta = fondoApuesta;
    }

    public JugadorModelo(String nombre, int fondoApuesta) {
        this.nombre = nombre;
        this.fondoApuesta = fondoApuesta;
    }

    public JugadorModelo(String nombre, String color, int fondoApuesta) {
        this.nombre = nombre;
        this.color = color;
        this.fondoApuesta = fondoApuesta;
    }

    public JugadorModelo(String nombre) {
        this.nombre = nombre;
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

    public List<FichaModelo> getFichas() {
        return fichas;
    }

    public void setFichas(List<FichaModelo> fichas) {
        this.fichas = fichas;
    }

    public int getFondoApuesta() {
        return fondoApuesta;
    }

    public void setFondoApuesta(int fondoApuesta) {
        this.fondoApuesta = fondoApuesta;
    }

}
