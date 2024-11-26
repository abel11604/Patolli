package vista;

import modelo.JugadorModelo;
import modelo.PartidaModelo;

/**
 *
 * @author abelc
 */
public class ControlNavegacion {
    private PartidaModelo partida;
    private JugadorModelo jugador;
    private static ControlNavegacion instance;

    public static ControlNavegacion getInstance() {
        if (instance == null) {
            instance = new ControlNavegacion();
        }
        return instance;
    }

    public void mostrarMenu() {
        MenuPrincipalFrm menu = new MenuPrincipalFrm();
        menu.mostrarPantalla();
    }

    public void QuieroUnasConfiguracioneeees() {
        ConfigurarPartidaFrm configurar = new ConfigurarPartidaFrm();
        configurar.mostrarPantalla();
    }

    public void mostrarListaDeEspera() {
        ListaEsperaFrm listaEspera = new ListaEsperaFrm();
        listaEspera.mostrarPantalla();
    }

    public void mostrarPantallaJuego() {
        PartidaFrm partida = new PartidaFrm();
        partida.mostrarPantalla();
    }
    
    public void mostrarUnirsePartida(){
        UnirsePartidaFrm unirse = new UnirsePartidaFrm();
        unirse.setVisible(true);
    }

    public PartidaModelo getPartida() {
        return partida;
    }

    public void setPartida(PartidaModelo partida) {
        this.partida = partida;
    }

    public JugadorModelo getJugador() {
        return jugador;
    }

    public void setJugador(JugadorModelo jugador) {
        this.jugador = jugador;
    }
    
    
}
