/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import control.ControlNavegacion;
import vista.PartidaFrm;

/**
 *
 * @author abelc
 */
public class Principal {

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
            java.util.logging.Logger.getLogger(ControlNavegacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Inicia la ventana de inicio en el hilo de eventos
        java.awt.EventQueue.invokeLater(() -> {
            ControlNavegacion nav = new ControlNavegacion();
            nav.mostrarMenu();
        });

    }

}
