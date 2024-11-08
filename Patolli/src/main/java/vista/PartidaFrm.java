/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import control.ControlConfigurarPartida;
import control.IControlConfigurarPartida;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author abelc
 */
public class PartidaFrm extends javax.swing.JFrame {

    private List<JLabel> casillas;
    private IControlConfigurarPartida confPartida;

    /**
     * Creates new form PartidaFrm
     */
    public PartidaFrm() {
        this.confPartida = ControlConfigurarPartida.getInstance();
        initComponents();
        casillas = new ArrayList<>();
        generarTablero();
        pintarFichas();
        apuestalbl.setText(Integer.toString(confPartida.getApuesta()));
        fondoApuestalbl1.setText("Fondo de apuesta: " + Integer.toString(confPartida.getFondo()));
        fondoApuestalbl2.setText("Fondo de apuesta: " + Integer.toString(confPartida.getFondo()));
        fondoApuestalbl3.setText("Fondo de apuesta: " + Integer.toString(confPartida.getFondo()));
        fondoApuestalbl4.setText("Fondo de apuesta: " + Integer.toString(confPartida.getFondo()));
      

    }

    private void pintarFichas() {
        if (confPartida.getNumFichas() == 2) {
            jLabel17.setVisible(false);
            jLabel19.setVisible(false);
            jLabel18.setVisible(false);
            jLabel16.setVisible(false);
            jLabel23.setVisible(false);
            jLabel24.setVisible(false);
            jLabel25.setVisible(false);
            jLabel22.setVisible(false);
            jLabel31.setVisible(false);
            jLabel30.setVisible(false);
            jLabel29.setVisible(false);
            jLabel28.setVisible(false);
            jLabel34.setVisible(false);
            jLabel37.setVisible(false);
            jLabel36.setVisible(false);
            jLabel35.setVisible(false);
        }
        if (confPartida.getNumFichas() == 4) {
            jLabel19.setVisible(false);
            jLabel17.setVisible(false);
            jLabel23.setVisible(false);
            jLabel24.setVisible(false);
            jLabel30.setVisible(false);
            jLabel31.setVisible(false);
            jLabel35.setVisible(false);
            jLabel36.setVisible(false);

        }
    }

    public static boolean darVerdaderoFalso() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100);
        return numeroAleatorio < 50;
    }

    private void generarCañas() {
        JLabel[] cañas = {caña1, caña2, caña3, caña4, caña5};
        int casillasAvanzar = 0;
        for (JLabel caña : cañas) {
            boolean resultado = darVerdaderoFalso();
            if (resultado) {
                caña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/punto.png")));
                casillasAvanzar++; // Incrementa el contador si es true
            } else {
                caña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png")));
            }
        }

        // Muestra el número total de casillas a avanzar
        System.out.println("Casillas a avanzar: " + casillasAvanzar);
    }

    private void generarTablero() {
        pintarTablero(casillasArriba, confPartida.getCasillaPorAspa(), 2, false);
        pintarTablero(casillasAbajo, confPartida.getCasillaPorAspa(), 2, true);
        pintarTablero(casillasDer, 2, confPartida.getCasillaPorAspa(), true);
        pintarTablero(casillasIzq, 2, confPartida.getCasillaPorAspa(), false);
        pintarTablero(casillasCentrales, 2, 2, true);
    }

    private void pintarTablero(JPanel tablero, int filas, int columnas, boolean invertir) {
        tablero.setLayout(new GridLayout(filas, columnas));
        tablero.setPreferredSize(tablero.getSize());
        tablero.setMinimumSize(tablero.getSize());
        tablero.setMaximumSize(tablero.getSize());

        for (int i = 1; i <= filas * columnas; i++) {
            JLabel label = new JLabel("");
            label.setBorder(new LineBorder(Color.BLACK, 1));
            label.setOpaque(true);
            label.setBackground(Color.WHITE);

            if (filas * columnas > 6) {
                if (invertir) {
                    if (columnas > filas) {
                        if (i == columnas + 1) {
                            label.setBackground(Color.YELLOW);
                        }
                    } else {
                        if (i == 1) {
                            label.setBackground(Color.YELLOW);
                        }
                    }
                } else {
                    if (columnas > filas) {
                        if (i == columnas) {
                            label.setBackground(Color.YELLOW);
                        }
                    } else {
                        if (i == filas * columnas) {
                            label.setBackground(Color.YELLOW);
                        }
                    }
                }

                if (invertir) {
                    if (columnas > filas) {
                        if (i == columnas - 3 || i == columnas * filas - 3) {
                            label.setBackground(Color.RED);
                        }
                    } else {
                        if (i == filas * columnas - 7 || i == filas * columnas - 6) {
                            label.setBackground(Color.RED);
                        }
                    }
                } else {
                    if (columnas > filas) {
                        if (i == 4 || i == columnas + 4) {
                            label.setBackground(Color.RED);
                        }
                    } else {
                        if (i == 7 || i == 8) {
                            label.setBackground(Color.RED);
                        }
                    }
                }
            }

            tablero.add(label);
            casillas.add(label);
        }
       
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
        panelJ1 = new javax.swing.JPanel();
        jugadorBlanco = new javax.swing.JLabel();
        fondoApuestalbl1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        panelJ3 = new javax.swing.JPanel();
        jugadorNaranja = new javax.swing.JLabel();
        fondoApuestalbl3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        panelJ2 = new javax.swing.JPanel();
        jugadorAmarillo = new javax.swing.JLabel();
        fondoApuestalbl2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        panelJ4 = new javax.swing.JPanel();
        JugadorCafe = new javax.swing.JLabel();
        fondoApuestalbl4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        caña1 = new javax.swing.JLabel();
        caña2 = new javax.swing.JLabel();
        caña3 = new javax.swing.JLabel();
        caña4 = new javax.swing.JLabel();
        caña5 = new javax.swing.JLabel();
        btnLanzarCañas = new javax.swing.JButton();
        casillasCentrales = new javax.swing.JPanel();
        casillasAbajo = new javax.swing.JPanel();
        casillasIzq = new javax.swing.JPanel();
        casillasArriba = new javax.swing.JPanel();
        casillasDer = new javax.swing.JPanel();
        apuestalbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1058, 549));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelJ1.setBackground(new java.awt.Color(255, 255, 255));
        panelJ1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelJ1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jugadorBlanco.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jugadorBlanco.setForeground(new java.awt.Color(0, 0, 0));
        jugadorBlanco.setText("Jugador 1");
        panelJ1.add(jugadorBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 8, -1, -1));

        fondoApuestalbl1.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        fondoApuestalbl1.setForeground(new java.awt.Color(0, 0, 0));
        fondoApuestalbl1.setText("Fondo de apuestas:");
        panelJ1.add(fondoApuestalbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 45, 188, -1));

        jLabel4.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Fichas:");
        panelJ1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 76, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 76, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 76, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 76, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 112, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 112, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 112, -1, -1));

        jPanel1.add(panelJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 150));

        panelJ3.setBackground(new java.awt.Color(255, 255, 255));
        panelJ3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelJ3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jugadorNaranja.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jugadorNaranja.setForeground(new java.awt.Color(0, 0, 0));
        jugadorNaranja.setText("Jugador 3");
        panelJ3.add(jugadorNaranja, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 8, -1, -1));

        fondoApuestalbl3.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        fondoApuestalbl3.setForeground(new java.awt.Color(0, 0, 0));
        fondoApuestalbl3.setText("Fondo de apuestas:");
        panelJ3.add(fondoApuestalbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 45, 183, -1));

        jLabel7.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Fichas:");
        panelJ3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 76, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 76, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 76, -1, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 76, -1, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 114, -1, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 114, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 114, -1, -1));

        jPanel1.add(panelJ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, -1, 170));

        panelJ2.setBackground(new java.awt.Color(255, 255, 255));
        panelJ2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelJ2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jugadorAmarillo.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jugadorAmarillo.setForeground(new java.awt.Color(0, 0, 0));
        jugadorAmarillo.setText("Jugador 2");
        panelJ2.add(jugadorAmarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 8, -1, -1));

        fondoApuestalbl2.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        fondoApuestalbl2.setForeground(new java.awt.Color(0, 0, 0));
        fondoApuestalbl2.setText("Fondo de apuestas:");
        panelJ2.add(fondoApuestalbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 45, 174, -1));

        jLabel10.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Fichas:");
        panelJ2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 76, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 76, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 76, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 76, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 114, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 114, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 114, -1, -1));

        jPanel1.add(panelJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(856, 0, 200, 170));

        panelJ4.setBackground(new java.awt.Color(255, 255, 255));
        panelJ4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JugadorCafe.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        JugadorCafe.setForeground(new java.awt.Color(0, 0, 0));
        JugadorCafe.setText("Jugador 4");
        panelJ4.add(JugadorCafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 6, -1, -1));

        fondoApuestalbl4.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        fondoApuestalbl4.setForeground(new java.awt.Color(0, 0, 0));
        fondoApuestalbl4.setText("Fondo de apuestas:");
        panelJ4.add(fondoApuestalbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 43, 176, -1));

        jLabel13.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Fichas:");
        panelJ4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 74, -1, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 74, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 74, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 74, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 112, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 112, -1, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 112, -1, -1));

        jPanel1.add(panelJ4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 410, 210, 160));

        caña1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(863, 261, 30, 64));

        caña2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 260, 30, 64));

        caña3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, 30, 64));

        caña4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 260, 30, 64));

        caña5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 260, 30, 64));

        btnLanzarCañas.setBackground(new java.awt.Color(213, 198, 86));
        btnLanzarCañas.setFont(new java.awt.Font("Bodoni MT", 0, 24)); // NOI18N
        btnLanzarCañas.setForeground(new java.awt.Color(0, 0, 0));
        btnLanzarCañas.setText("LanzarCañas");
        btnLanzarCañas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLanzarCañas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarCañasActionPerformed(evt);
            }
        });
        jPanel1.add(btnLanzarCañas, new org.netbeans.lib.awtextra.AbsoluteConstraints(863, 343, 190, 40));

        casillasCentrales.setBackground(new java.awt.Color(0, 51, 102));
        casillasCentrales.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout casillasCentralesLayout = new javax.swing.GroupLayout(casillasCentrales);
        casillasCentrales.setLayout(casillasCentralesLayout);
        casillasCentralesLayout.setHorizontalGroup(
            casillasCentralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        casillasCentralesLayout.setVerticalGroup(
            casillasCentralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(casillasCentrales, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 251, 102, 102));

        casillasAbajo.setBackground(new java.awt.Color(0, 51, 102));
        casillasAbajo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout casillasAbajoLayout = new javax.swing.GroupLayout(casillasAbajo);
        casillasAbajo.setLayout(casillasAbajoLayout);
        casillasAbajoLayout.setHorizontalGroup(
            casillasAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        casillasAbajoLayout.setVerticalGroup(
            casillasAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        jPanel1.add(casillasAbajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 359, -1, -1));

        casillasIzq.setBackground(new java.awt.Color(0, 51, 102));
        casillasIzq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout casillasIzqLayout = new javax.swing.GroupLayout(casillasIzq);
        casillasIzq.setLayout(casillasIzqLayout);
        casillasIzqLayout.setHorizontalGroup(
            casillasIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        casillasIzqLayout.setVerticalGroup(
            casillasIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(casillasIzq, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 251, -1, 102));

        casillasArriba.setBackground(new java.awt.Color(0, 51, 102));
        casillasArriba.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout casillasArribaLayout = new javax.swing.GroupLayout(casillasArriba);
        casillasArriba.setLayout(casillasArribaLayout);
        casillasArribaLayout.setHorizontalGroup(
            casillasArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        casillasArribaLayout.setVerticalGroup(
            casillasArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        jPanel1.add(casillasArriba, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 49, 102, -1));

        casillasDer.setBackground(new java.awt.Color(0, 51, 102));
        casillasDer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout casillasDerLayout = new javax.swing.GroupLayout(casillasDer);
        casillasDer.setLayout(casillasDerLayout);
        casillasDerLayout.setHorizontalGroup(
            casillasDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        casillasDerLayout.setVerticalGroup(
            casillasDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(casillasDer, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 251, -1, -1));

        apuestalbl.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        apuestalbl.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(apuestalbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 70, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Turno:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apuesta a pagar:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLanzarCañasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarCañasActionPerformed
        generarCañas();
    }//GEN-LAST:event_btnLanzarCañasActionPerformed

    public void mostrarPantalla() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PartidaFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JugadorCafe;
    private javax.swing.JLabel apuestalbl;
    private javax.swing.JButton btnLanzarCañas;
    private javax.swing.JPanel casillasAbajo;
    private javax.swing.JPanel casillasArriba;
    private javax.swing.JPanel casillasCentrales;
    private javax.swing.JPanel casillasDer;
    private javax.swing.JPanel casillasIzq;
    private javax.swing.JLabel caña1;
    private javax.swing.JLabel caña2;
    private javax.swing.JLabel caña3;
    private javax.swing.JLabel caña4;
    private javax.swing.JLabel caña5;
    private javax.swing.JLabel fondoApuestalbl1;
    private javax.swing.JLabel fondoApuestalbl2;
    private javax.swing.JLabel fondoApuestalbl3;
    private javax.swing.JLabel fondoApuestalbl4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jugadorAmarillo;
    private javax.swing.JLabel jugadorBlanco;
    private javax.swing.JLabel jugadorNaranja;
    private javax.swing.JPanel panelJ1;
    private javax.swing.JPanel panelJ2;
    private javax.swing.JPanel panelJ3;
    private javax.swing.JPanel panelJ4;
    // End of variables declaration//GEN-END:variables
}
