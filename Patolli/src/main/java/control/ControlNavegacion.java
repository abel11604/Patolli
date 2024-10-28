/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import vista.ConfigurarPartidaFrm;
import vista.ListaEspera;
import vista.MenuPrincipalFrm;
import vista.PartidaFrm;

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
    
    public void mostrarListaDeEspera(){
        ListaEspera listaEspera =new ListaEspera();
        listaEspera.mostrarPantalla();
    }
    
    public void mostrarPantallaJuego(){
        PartidaFrm partida=new PartidaFrm();
        partida.mostrarPantalla();
    }
}
