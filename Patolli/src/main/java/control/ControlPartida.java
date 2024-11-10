package control;

import entidades.Casilla;
import entidades.Ficha;
import entidades.Juego;
import entidades.Jugador;

/**
 *
 * @author abelc
 */
public class ControlPartida implements IControlPartida {

    private Juego partida;
    public static ControlPartida instance;

    public static ControlPartida getInstance() {
        if (instance == null) {
            instance = new ControlPartida();
        }
        return instance;
    }

    @Override
    public void setPartida(Juego partida) {
        this.partida = partida;
    }

    @Override
    public Juego getPartida() {
        return partida;
    }

    @Override
    public void avanzarCasillas(int numCasillas, Ficha fichaSel) {
        Casilla casillaActual = fichaSel.getCasillaActual();
        int numCasillaActual = casillaActual.getNumCasilla();
        int nuevaCasilla = numCasillaActual + numCasillas;

        if (nuevaCasilla < 0 || nuevaCasilla >= partida.getCasillas().size()) {
            return;
        }

        Casilla casillaDestino = partida.getCasillas().get(nuevaCasilla);

        if (casillaDestino.getOcupadoPor() != null) {
            Jugador jugadorOcupante = casillaDestino.getOcupadoPor();
            if (!jugadorOcupante.equals(fichaSel.getJugador())) {
                //Si la casilla está ocupada por otra, se elimina 
                eliminarFicha(casillaDestino.getOcupadoPor().getFichas().get(0));
            }
        }

        fichaSel.setCasillaActual(casillaDestino);
        casillaDestino.setOcupadoPor(fichaSel.getJugador());

        partida.getCasillas().get(numCasillaActual).setOcupadoPor(null);
    }

    @Override
    public void cobrarApuesta(Jugador jugador) {
        if (jugador.getFondoApuesta() >= partida.getApuesta()) {
            jugador.setFondoApuesta(jugador.getFondoApuesta() - partida.getApuesta());
        } else {
            eliminarJugador(jugador);
        }
    }

    @Override
    public void cobrarApuestaDoble(Jugador jugador) {
        if (jugador.getFondoApuesta() >= 2 * partida.getApuesta()) {
            jugador.setFondoApuesta(jugador.getFondoApuesta() - 2 * partida.getApuesta());
        } else {
            eliminarJugador(jugador);
        }
    }

    @Override
    public void reiniciarFicha(Ficha ficha) {
        ficha.setCasillaActual(partida.getCasillas().get(ficha.getJugador().getFichas().indexOf(ficha)));
    }

    @Override
    public void eliminarFicha(Ficha ficha) {
        ficha.getJugador().getFichas().remove(ficha);
        //Si el jugador se queda sin fichas, se elimina del juego
        if (ficha.getJugador().getFichas().isEmpty()) {
            eliminarJugador(ficha.getJugador());
        }
    }

    @Override
    public void eliminarJugador(Jugador jugador) {
        partida.getJugadores().remove(jugador);
        for (Ficha ficha : jugador.getFichas()) {
            partida.getCasillas().get(ficha.getCasillaActual().getNumCasilla()).setOcupadoPor(null);
        }
    }

}
