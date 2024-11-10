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


    private static ControlPartida instance;

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

      // Obtener la casilla actual de la ficha
    Casilla casillaActual = fichaSel.getCasillaActual();

    // Encontrar la posición actual de la casilla en la lista de casillas
    int posicionActual = partida.getCasillas().indexOf(casillaActual);

    // Calcular la nueva posición
    int nuevaPosicion = posicionActual + numCasillas;

    // Verificar si la nueva posición está dentro del tablero
    if (nuevaPosicion >= partida.getCasillas().size()) {
        nuevaPosicion = partida.getCasillas().size() - 1; // Asegurar que no salga del tablero
    }

    // Obtener la casilla de destino
    Casilla casillaDestino = partida.getCasillas().get(nuevaPosicion);

    // Manejar el caso si la casilla de destino está ocupada y es de tipo "Central"
    if (casillaDestino.getOcupadoPor() != null && casillaDestino.getTipo().equals("Central")) {
        eliminarFicha(casillaDestino.getOcupadoPor()); // Elimina la ficha contraria
        return; // Sale del método después de eliminar la ficha
    }

    // Manejar el caso si la casilla de destino está ocupada y es de tipo "Normal"
    if (casillaDestino.getOcupadoPor() != null && casillaDestino.getTipo().equals("Normal")) {
        reiniciarFicha(fichaSel); // Reinicia la posición de la ficha actual
        return; // Sale del método, ya que la ficha ha sido reiniciada
    }
    
    // Liberar la casilla actual
    casillaActual.setOcupadoPor(null);
        
    // Colocar la ficha en la nueva casilla
    fichaSel.setCasillaActual(casillaDestino);
    casillaDestino.setOcupadoPor(fichaSel);


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


        int apuesta = partida.getApuesta();
        int fondoActual = jugador.getFondoApuesta(); 

        if (fondoActual >= apuesta) {
            jugador.setFondoApuesta(fondoActual - apuesta);
        } else {
            System.out.println("El jugador " + jugador.getNombre() + " no tiene suficiente fondo para cubrir la apuesta.");
        }


        if (jugador.getFondoApuesta() >= partida.getApuesta()) {
            jugador.setFondoApuesta(jugador.getFondoApuesta() - partida.getApuesta());
        } else {
            eliminarJugador(jugador);
        }

    }

    @Override
    public void cobrarApuestaDoble(Jugador jugador) {

    
        int apuesta = partida.getApuesta();
        int fondoActual = jugador.getFondoApuesta(); 

        if (fondoActual >= apuesta) {
            jugador.setFondoApuesta(fondoActual - apuesta*2);
        } else {
            System.out.println("El jugador " + jugador.getNombre() + " no tiene suficiente fondo para cubrir la apuesta.");
        }    

        if (jugador.getFondoApuesta() >= 2 * partida.getApuesta()) {
            jugador.setFondoApuesta(jugador.getFondoApuesta() - 2 * partida.getApuesta());
        } else {
            eliminarJugador(jugador);
        }

    }

    @Override
    public void reiniciarFicha(Ficha ficha) {


        String tipoInicial = "";
        switch (ficha.getJugador().getColor()) {
            case "Blanco" ->
                tipoInicial = "inicialJ1";
            case "Amarillo" ->
                tipoInicial = "inicialJ2";
            case "Naranja" ->
                tipoInicial = "inicialJ3";
            case "Cafe" ->
                tipoInicial = "inicialJ4";
        }

        for (Casilla casilla : partida.getCasillas()) {
            if (casilla.getTipo().equals(tipoInicial)) {
                casilla.setOcupadoPor(ficha);
                ficha.setCasillaActual(casilla);
                break;
            }
        }

        ficha.setCasillaActual(partida.getCasillas().get(ficha.getJugador().getFichas().indexOf(ficha)));

    }

    @Override
    public void eliminarFicha(Ficha ficha) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

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
