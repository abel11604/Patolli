package modelo;

import enums.EstadosPartida;
import java.util.List;
/**
 *
 * @author abelc
 */
public class PartidaModelo {
 
    private List<JugadorModelo> jugadores;
    private List<CasillaModelo> casillas;
    private int apuesta;
    private int fondo;
    private EstadosPartida estado;
    private String codigoAcceso;
    private JugadorModelo turnoActual;
 
    public PartidaModelo() {
    }

    public PartidaModelo(List<JugadorModelo> jugadores, List<CasillaModelo> casillas, int apuesta, int fondo) {
        this.jugadores = jugadores;
        this.casillas = casillas;
        this.apuesta = apuesta;
        this.fondo = fondo;
    }

    public List<JugadorModelo> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorModelo> jugadores) {
        this.jugadores = jugadores;
    }

    public List<CasillaModelo> getCasillas() {
        return casillas;
    }

    public void setCasillas(List<CasillaModelo> casillas) {
        this.casillas = casillas;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public EstadosPartida getEstado() {
        return estado;
    }

    public void setEstado(EstadosPartida estado) {
        this.estado = estado;
    }

    public String getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

    public JugadorModelo getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(JugadorModelo turnoActual) {
        this.turnoActual = turnoActual;
    }

    public int getFondo() {
        return fondo;
    }

    public void setFondo(int fondo) {
        this.fondo = fondo;
    }
    
}
