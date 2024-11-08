/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import control.ControlConfigurarPartida;
import control.IControlConfigurarPartida;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author abelc
 */
public class ConfigurarPartidaFrm extends javax.swing.JFrame {

    ControlNavegacion nav;
    IControlConfigurarPartida confPartida;

    /**
     * Creates new form ConfigurarPartidaFrm
     */
    public ConfigurarPartidaFrm() {
        this.confPartida = ControlConfigurarPartida.getInstance();
        this.nav = ControlNavegacion.getInstance();
        initComponents();
        casillasRadioButton2.setSelected(true);
        fichaRadioButton2.setSelected(true);
        ButtonGroup grupoJugadores = new ButtonGroup();
        ButtonGroup grupoFichas = new ButtonGroup();
        grupoJugadores.add(casillasRadioButton2);
        grupoJugadores.add(casillasRadioButton3);
        grupoJugadores.add(casillasRadioButton4);

        grupoFichas.add(fichaRadioButton2);
        grupoFichas.add(fichaRadioButton4);
        grupoFichas.add(fichaRadioButton6);
        SpinnerNumberModel fondoModel = new SpinnerNumberModel(0, 0, null, 50);
        fondoApuestaSpinner.setModel(fondoModel);
        fondoApuestaSpinner.setEditor(new JSpinner.NumberEditor(fondoApuestaSpinner, "#"));
        SpinnerNumberModel cantidadModel = new SpinnerNumberModel(0, 0, null, 100);
        apuestaSpinner.setModel(cantidadModel);
        apuestaSpinner.setEditor(new JSpinner.NumberEditor(apuestaSpinner, "#"));

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
        jLabel1 = new javax.swing.JLabel();
        casillasRadioButton2 = new javax.swing.JRadioButton();
        casillasRadioButton3 = new javax.swing.JRadioButton();
        casillasRadioButton4 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        fichaRadioButton2 = new javax.swing.JRadioButton();
        fichaRadioButton4 = new javax.swing.JRadioButton();
        fichaRadioButton6 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        apuestaSpinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        fondoApuestaSpinner = new javax.swing.JSpinner();
        btnCrearPartida = new javax.swing.JButton();
        btnCrearPartida1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configurar Partida");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel2.setBackground(new java.awt.Color(221, 209, 121));
        jLabel2.setFont(new java.awt.Font("Bodoni MT", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(213, 198, 86));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CONFIGURA TU PARTIDA");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 100));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Bodoni MT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("APUESTAS");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 700, 40));

        casillasRadioButton2.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        casillasRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        casillasRadioButton2.setText("8 por aspa");
        casillasRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casillasRadioButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(casillasRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 130, -1));

        casillasRadioButton3.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        casillasRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        casillasRadioButton3.setText("10 por aspa");
        casillasRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casillasRadioButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(casillasRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        casillasRadioButton4.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        casillasRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        casillasRadioButton4.setText("14 por aspa");
        jPanel2.add(casillasRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Bodoni MT", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tamaño del tablero(num. de casillas)");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 40));

        fichaRadioButton2.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        fichaRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        fichaRadioButton2.setText("2 Fichas");
        jPanel2.add(fichaRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 90, -1));

        fichaRadioButton4.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        fichaRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        fichaRadioButton4.setText("4 fichas");
        jPanel2.add(fichaRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));

        fichaRadioButton6.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        fichaRadioButton6.setForeground(new java.awt.Color(255, 255, 255));
        fichaRadioButton6.setText("6 fichas");
        jPanel2.add(fichaRadioButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 100, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Bodoni MT", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("FICHAS");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 119, 700, 40));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cantidad a pagar:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, -1, -1));

        apuestaSpinner.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jPanel2.add(apuestaSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 110, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fondo de apuesta:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        fondoApuestaSpinner.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        fondoApuestaSpinner.setToolTipText("");
        fondoApuestaSpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(fondoApuestaSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 110, -1));

        btnCrearPartida.setBackground(new java.awt.Color(213, 198, 86));
        btnCrearPartida.setFont(new java.awt.Font("Bodoni MT", 0, 24)); // NOI18N
        btnCrearPartida.setText("Crear partida");
        btnCrearPartida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPartidaActionPerformed(evt);
            }
        });
        jPanel2.add(btnCrearPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 170, 40));

        btnCrearPartida1.setBackground(new java.awt.Color(213, 198, 86));
        btnCrearPartida1.setFont(new java.awt.Font("Bodoni MT", 0, 24)); // NOI18N
        btnCrearPartida1.setText("Regresar");
        btnCrearPartida1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearPartida1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPartida1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnCrearPartida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 160, 40));

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 40));

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 700, 40));

        jPanel5.setBackground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 700, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 700, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void establecerCasillas() {
        if (casillasRadioButton2.isSelected()) {
            confPartida.setCasillaPorAspa(8);
        }
        if (casillasRadioButton3.isSelected()) {
            confPartida.setCasillaPorAspa(10);
        }
        if (casillasRadioButton4.isSelected()) {
            confPartida.setCasillaPorAspa(14);
        }
    }

    private void establecerNumFichas() {
        if (fichaRadioButton2.isSelected()) {
            confPartida.setNumFichas(2);
        }
        if (fichaRadioButton4.isSelected()) {
            confPartida.setNumFichas(4);
        }
        if (fichaRadioButton6.isSelected()) {
            confPartida.setNumFichas(6);
        }
    }

    private void establecerApuestas() {
        int apuesta = (int) apuestaSpinner.getValue();
        int fondo = (int) fondoApuestaSpinner.getValue();
        confPartida.setApuesta(apuesta);
        confPartida.setFondo(fondo);
    }


    private void btnCrearPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPartidaActionPerformed

        if (!casillasRadioButton2.isSelected() && !casillasRadioButton3.isSelected() && !casillasRadioButton4.isSelected()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un número de casillas.", "Error de selección", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!fichaRadioButton2.isSelected() && !fichaRadioButton4.isSelected() && !fichaRadioButton6.isSelected()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un número de fichas.", "Error de selección", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int apuesta = (int) apuestaSpinner.getValue();
        int fondo = (int) fondoApuestaSpinner.getValue();

        if (fondo > apuesta) {
            JOptionPane.showMessageDialog(this, "El fondo de apuesta no puede ser menor que la apuesta.", "Error de apuesta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        establecerCasillas();
        establecerNumFichas();
        establecerApuestas();
        System.out.println(confPartida.getNumFichas());
        this.dispose();
        nav.mostrarListaDeEspera();
    }//GEN-LAST:event_btnCrearPartidaActionPerformed

    private void casillasRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casillasRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_casillasRadioButton3ActionPerformed

    private void casillasRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casillasRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_casillasRadioButton2ActionPerformed

    private void btnCrearPartida1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPartida1ActionPerformed
        nav.mostrarMenu();
    }//GEN-LAST:event_btnCrearPartida1ActionPerformed

    public void mostrarPantalla() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfigurarPartidaFrm().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner apuestaSpinner;
    private javax.swing.JButton btnCrearPartida;
    private javax.swing.JButton btnCrearPartida1;
    private javax.swing.JRadioButton casillasRadioButton2;
    private javax.swing.JRadioButton casillasRadioButton3;
    private javax.swing.JRadioButton casillasRadioButton4;
    private javax.swing.JRadioButton fichaRadioButton2;
    private javax.swing.JRadioButton fichaRadioButton4;
    private javax.swing.JRadioButton fichaRadioButton6;
    private javax.swing.JSpinner fondoApuestaSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
