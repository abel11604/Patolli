/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import vista.ControlNavegacion;
import vista.PartidaFrm;

/**
 *
 * @author abelc
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              try {
            // Configura el look and feel a Nimbus si está disponible
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            // Registra cualquier excepción que se produzca al configurar el look and feel
            java.util.logging.Logger.getLogger(PartidaFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Inicia la ventana de inicio en el hilo de eventos
        java.awt.EventQueue.invokeLater(() -> {
            PartidaFrm nav = new PartidaFrm();
            nav.mostrarPantalla();
        });
    }
    
}
