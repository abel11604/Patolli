/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import comunicacion.ClientConnection;
import factory.FichaFactory;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;
import modelo.JugadorModelo;
import modelo.PartidaModelo;

/**
 *
 * @author abelc
 */
public class ListaEsperaFrm extends javax.swing.JFrame {

    private ControlNavegacion nav;
    private PartidaModelo partida;
    private JugadorModelo jugador;

    /**
     * Creates new form ListaEspera
     */
    public ListaEsperaFrm() {

        this.nav = ControlNavegacion.getInstance();
        this.partida = nav.getPartida();
        this.jugador = nav.getJugador();
        initComponents();
        inicializarPantalla();
        ClientConnection.getInstance().setMessageListener(message -> {
            String accion = (String) message.get("accion");
            if (accion == null) {
                return;
            }

            SwingUtilities.invokeLater(() -> {
                switch (accion) {
                    case "JUGADOR_UNIDO" ->
                        procesarNuevoJugador(message);
                    case "PARTIDA_INICIADA" ->
                        procesarPartidaIniciada(message);
                }
            });
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblJugadores = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cafePanel = new javax.swing.JPanel();
        blancoPanel = new javax.swing.JPanel();
        amarilloPanel = new javax.swing.JPanel();
        naranjaPanel = new javax.swing.JPanel();
        btnIniciarPartida = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jugadorCafelbl = new javax.swing.JLabel();
        jugadorBlancolbl = new javax.swing.JLabel();
        jugadorAmarillolbl = new javax.swing.JLabel();
        jugadorNaranjalbl = new javax.swing.JLabel();
        codigoLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 477));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel2.setBackground(new java.awt.Color(221, 209, 121));
        jLabel2.setFont(new java.awt.Font("Bodoni MT", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(213, 198, 86));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Esperando jugadores");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJugadores.setFont(new java.awt.Font("Bodoni MT", 1, 24)); // NOI18N
        lblJugadores.setText("1/4");
        jPanel3.add(lblJugadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Bodoni MT", 1, 24)); // NOI18N
        jLabel3.setText("Jugadores:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 30));

        cafePanel.setBackground(new java.awt.Color(102, 51, 0));
        cafePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout cafePanelLayout = new javax.swing.GroupLayout(cafePanel);
        cafePanel.setLayout(cafePanelLayout);
        cafePanelLayout.setHorizontalGroup(
            cafePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        cafePanelLayout.setVerticalGroup(
            cafePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.add(cafePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, -1, -1));

        blancoPanel.setBackground(new java.awt.Color(255, 255, 255));
        blancoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout blancoPanelLayout = new javax.swing.GroupLayout(blancoPanel);
        blancoPanel.setLayout(blancoPanelLayout);
        blancoPanelLayout.setHorizontalGroup(
            blancoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        blancoPanelLayout.setVerticalGroup(
            blancoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.add(blancoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        amarilloPanel.setBackground(new java.awt.Color(204, 204, 0));
        amarilloPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout amarilloPanelLayout = new javax.swing.GroupLayout(amarilloPanel);
        amarilloPanel.setLayout(amarilloPanelLayout);
        amarilloPanelLayout.setHorizontalGroup(
            amarilloPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        amarilloPanelLayout.setVerticalGroup(
            amarilloPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.add(amarilloPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        naranjaPanel.setBackground(new java.awt.Color(255, 153, 0));
        naranjaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout naranjaPanelLayout = new javax.swing.GroupLayout(naranjaPanel);
        naranjaPanel.setLayout(naranjaPanelLayout);
        naranjaPanelLayout.setHorizontalGroup(
            naranjaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        naranjaPanelLayout.setVerticalGroup(
            naranjaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.add(naranjaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, -1));

        btnIniciarPartida.setBackground(new java.awt.Color(213, 198, 86));
        btnIniciarPartida.setFont(new java.awt.Font("Bodoni MT", 0, 24)); // NOI18N
        btnIniciarPartida.setText("Empezar");
        btnIniciarPartida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarPartidaActionPerformed(evt);
            }
        });
        jPanel2.add(btnIniciarPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 160, 40));

        btnCancelar.setBackground(new java.awt.Color(213, 198, 86));
        btnCancelar.setFont(new java.awt.Font("Bodoni MT", 0, 24)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 160, 40));

        jugadorCafelbl.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jugadorCafelbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugadorCafelbl.setText("Jugador 4");
        jugadorCafelbl.setVisible(false);
        jPanel2.add(jugadorCafelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 180, 100, -1));

        jugadorBlancolbl.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jugadorBlancolbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugadorBlancolbl.setText("Jugador 1");
        jPanel2.add(jugadorBlancolbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 180, 100, -1));

        jugadorAmarillolbl.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jugadorAmarillolbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugadorAmarillolbl.setText("Jugador 2");
        jugadorAmarillolbl.setVisible(false);
        jPanel2.add(jugadorAmarillolbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 100, -1));

        jugadorNaranjalbl.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jugadorNaranjalbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugadorNaranjalbl.setText("Jugador 3");
        jugadorNaranjalbl.setVisible(false);
        jPanel2.add(jugadorNaranjalbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 180, 100, -1));

        codigoLabel.setFont(new java.awt.Font("Bodoni MT", 1, 24)); // NOI18N
        codigoLabel.setText("123");
        jPanel2.add(codigoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 90, -1));

        jLabel4.setFont(new java.awt.Font("Bodoni MT", 1, 24)); // NOI18N
        jLabel4.setText("Codigo:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnIniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarPartidaActionPerformed
        ClientConnection.getInstance().iniciarPartida(partida.getCodigoAcceso());
    }//GEN-LAST:event_btnIniciarPartidaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void inicializarPantalla() {
        // Mostrar el nombre del jugador blanco
        if (!partida.getJugadores().isEmpty()) {
            jugadorBlancolbl.setText(partida.getJugadores().get(0).getNombre());
        }

        // Ocultar los paneles de colores y botones inicialmente
        amarilloPanel.setVisible(false);
        naranjaPanel.setVisible(false);
        cafePanel.setVisible(false);
        btnIniciarPartida.setVisible(false);
        btnCancelar.setVisible(false);

        // Mostrar el código de la partida
        codigoLabel.setText(partida.getCodigoAcceso());

        // Si el jugador actual es el blanco, mostrar los botones correspondientes
        if (jugador.getColor().equalsIgnoreCase("Blanco")) {
            btnIniciarPartida.setVisible(true);
            btnCancelar.setVisible(true);
        }

        // Cargar los jugadores en la interfaz
        cargarJugadores();
    }

    private void cargarJugadores() {
        List<JugadorModelo> jugadores = partida.getJugadores();
        if (jugadores == null || jugadores.isEmpty()) {
            lblJugadores.setText("0/4");
            return;
        }

        int totalJugadores = jugadores.size();
        lblJugadores.setText(totalJugadores + "/4");

        // Actualizar la interfaz en función del número de jugadores
        if (totalJugadores > 1) {
            amarilloPanel.setVisible(true);
            jugadorAmarillolbl.setText(jugadores.get(1).getNombre());
            jugadorAmarillolbl.setVisible(true);
        }
        if (totalJugadores > 2) {
            naranjaPanel.setVisible(true);
            jugadorNaranjalbl.setText(jugadores.get(2).getNombre());
            jugadorNaranjalbl.setVisible(true);
        }
        if (totalJugadores > 3) {
            cafePanel.setVisible(true);
            jugadorCafelbl.setText(jugadores.get(3).getNombre());
            jugadorCafelbl.setVisible(true);
        }
    }

    public void procesarNuevoJugador(Map<String, Object> mensajeNotificacion) {
        if ("JUGADOR_UNIDO".equals(mensajeNotificacion.get("accion"))) {
            // Crear un nuevo jugador usando los datos de la notificación
            String nombre = (String) mensajeNotificacion.get("nombre");
            String color = (String) mensajeNotificacion.get("color");
            JugadorModelo nuevoJugador = new JugadorModelo(nombre, color, partida.getJugadores().get(0).getFondoApuesta());
            nuevoJugador.setFichas(FichaFactory.generarFichas(partida.getJugadores().get(0).getFichas().size(), nuevoJugador));
            // Añadir el nuevo jugador a la lista de jugadores de la partida
            partida.getJugadores().add(nuevoJugador);

            // Actualizar la interfaz para reflejar el cambio
            cargarJugadores();
        }
    }

    public void procesarPartidaIniciada(Map<String, Object> mensajeNotificacion) {
        // Mostrar el mensaje recibido (opcional)
        System.out.println("Partida iniciada: " + mensajeNotificacion);

        // Cambiar a la pantalla del juego
        nav.mostrarPantallaJuego();
        dispose(); // Cerrar la ventana actual
    }

    public void mostrarPantalla() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaEsperaFrm().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel amarilloPanel;
    private javax.swing.JPanel blancoPanel;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIniciarPartida;
    private javax.swing.JPanel cafePanel;
    private javax.swing.JLabel codigoLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jugadorAmarillolbl;
    private javax.swing.JLabel jugadorBlancolbl;
    private javax.swing.JLabel jugadorCafelbl;
    private javax.swing.JLabel jugadorNaranjalbl;
    private javax.swing.JLabel lblJugadores;
    private javax.swing.JPanel naranjaPanel;
    // End of variables declaration//GEN-END:variables
}
