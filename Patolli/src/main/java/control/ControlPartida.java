package control;

import dominio.Casilla;
import dominio.Ficha;
import dominio.Juego;
import dominio.Jugador;

/**
 * Clase que implementa el control de la partida del juego, manejando las
 * operaciones relacionadas con la manipulación de fichas, jugadores y el estado
 * de la partida. Utiliza el patrón Singleton para asegurar que solo exista una
 * instancia del controlador.
 *
 * @author abelc
 */
public class ControlPartida implements IControlPartida {

    private Juego partida;
    private static ControlPartida instance;

    /**
     * Obtiene la instancia única de ControlPartida (Singleton).
     *
     * @return La instancia única de ControlPartida.
     */
    public static ControlPartida getInstance() {
        if (instance == null) {
            instance = new ControlPartida();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPartida(Juego partida) {
        this.partida = partida;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Juego getPartida() {
        return partida;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void avanzarCasillas(int numCasillas, Ficha fichaSel) {
       // Obtener la casilla actual de la ficha
    Casilla casillaActual = fichaSel.getCasillaActual();

    // Encontrar la posición actual de la casilla en la lista de casillas
    int posicionActual = partida.getCasillas().indexOf(casillaActual);

    // Calcular la nueva posición
    int nuevaPosicion = posicionActual + numCasillas;

    // Si la nueva posición supera el tamaño de la lista, reiniciarla al inicio
    if (nuevaPosicion >= partida.getCasillas().size()) {
        nuevaPosicion = nuevaPosicion % partida.getCasillas().size(); // Hacer un ciclo hacia el inicio
    }

    // Obtener la casilla de destino
    Casilla casillaDestino = partida.getCasillas().get(nuevaPosicion);

    // Manejar el caso si la casilla de destino está ocupada y es de tipo "Central"
    if (casillaDestino.getOcupadoPor() != null && casillaDestino.getTipo().equalsIgnoreCase("Central")) {
        eliminarFicha(casillaDestino.getOcupadoPor()); // Elimina la ficha contraria
    }

    // Manejar el caso si la casilla de destino está ocupada y es de tipo "Normal"
    if (casillaDestino.getOcupadoPor() != null && casillaDestino.getTipo().equalsIgnoreCase("Normal")) {
        casillaActual.setOcupadoPor(null);
        reiniciarFicha(fichaSel); // Reinicia la posición de la ficha actual
        return; // Sale del método, ya que la ficha ha sido reiniciada
    }

    // Liberar la casilla actual
    casillaActual.setOcupadoPor(null);
    
    // Colocar la ficha en la nueva casilla
    fichaSel.setCasillaActual(casillaDestino);
    casillaDestino.setOcupadoPor(fichaSel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cobrarApuesta(Jugador jugador) {
         if (jugador.getFondoApuesta() >= partida.getApuesta()) {
            jugador.setFondoApuesta(jugador.getFondoApuesta() - partida.getApuesta());
        } else {
            eliminarJugador(jugador);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cobrarApuestaDoble(Jugador jugador) {
             if (jugador.getFondoApuesta() >= 2 * partida.getApuesta()) {
            jugador.setFondoApuesta(jugador.getFondoApuesta() - 2 * partida.getApuesta());
        } else {
            eliminarJugador(jugador);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reiniciarFicha(Ficha ficha) {
       String tipoInicial = "";
    switch (ficha.getJugador().getColor().toLowerCase()) { // Convertir a minúsculas para mayor consistencia
        case "blanco" ->
            tipoInicial = "inicialBlanco";
        case "amarillo" ->
            tipoInicial = "inicialAmarillo";
        case "naranja" ->
            tipoInicial = "inicialNaranja";
        case "cafe" ->
            tipoInicial = "inicialCafe";
    }

    for (Casilla casilla : partida.getCasillas()) {
        if (casilla.getTipo().equalsIgnoreCase(tipoInicial)) { // Comparación sin distinción de mayúsculas y minúsculas
            casilla.setOcupadoPor(ficha);
            ficha.setCasillaActual(casilla);
            break;
        }
    }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminarFicha(Ficha ficha) {
        ficha.getJugador().getFichas().remove(ficha);
        //Si el jugador se queda sin fichas, se elimina del juego
        if (ficha.getJugador().getFichas().isEmpty()) {
            eliminarJugador(ficha.getJugador());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminarJugador(Jugador jugador) {
        partida.getJugadores().remove(jugador);
        for (Ficha ficha : jugador.getFichas()) {
            partida.getCasillas().get(ficha.getCasillaActual().getNumCasilla()).setOcupadoPor(null);
        }
    }

}
