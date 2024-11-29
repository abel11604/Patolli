/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.List;

/**
 *
 * @author abelc
 */
public class Jugador {

    private String id;
    private String nombre;
    private String color;
    private List<Ficha> fichas;
    int fondoApuesta;
    private boolean esTurno;

    public Jugador() {
    }

    public Jugador(String id, String nombre, String color, List<Ficha> fichas, int fondoApuesta) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.fichas = fichas;
        this.fondoApuesta = fondoApuesta;
    }

    public Jugador(String nombre, String color, List<Ficha> fichas, int fondoApuesta) {
        this.nombre = nombre;
        this.color = color;
        this.fichas = fichas;
        this.fondoApuesta = fondoApuesta;
    }

    public Jugador(String id, String nombre, String color, int fondoApuesta) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.fondoApuesta = fondoApuesta;
    }

    public Jugador(String nombre, String color, int fondoApuesta) {
        this.nombre = nombre;
        this.color = color;
        this.fondoApuesta = fondoApuesta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isEsTurno() {
        return esTurno;
    }

    public void setEsTurno(boolean esTurno) {
        this.esTurno = esTurno;
    }
    
    public Ficha getFichaById(String idFicha) {
    for (Ficha ficha : fichas) {
        if (ficha.getId().equals(idFicha)) {
            return ficha;
        }
    }
    throw new IllegalArgumentException("No se encontró una ficha con el ID: " + idFicha);
}
}
