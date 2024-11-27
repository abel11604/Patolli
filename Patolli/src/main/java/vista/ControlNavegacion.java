package vista;

/**
 *
 * @author abelc
 */
public class ControlNavegacion {

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
    
    public void mostrarInstrucciones(){
        InstruccionesDlg instrucciones = new InstruccionesDlg();
        instrucciones.setVisible(true);
    }
    
    
}
