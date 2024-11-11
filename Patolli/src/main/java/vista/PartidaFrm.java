package vista;

import control.ControlConfigurarPartida;
import control.ControlPartida;
import control.IControlConfigurarPartida;
import control.IControlPartida;
import entidades.Jugador;
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
    private IControlPartida partida;
    private int turnoActual = 0;
    

    /**
     * Creates new form PartidaFrm
     */
    public PartidaFrm() {
        this.confPartida = ControlConfigurarPartida.getInstance();
        this.partida = ControlPartida.getInstance();
        //partida.setPartida(confPartida.crearPartida());
        initComponents();

        // Ocultar todos los paneles al inicio
        panelJ1.setVisible(false);
        panelJ2.setVisible(false);
        panelJ3.setVisible(false);
        panelJ4.setVisible(false);

        casillas = new ArrayList<>();
        generarTablero();
        pintarFichas();

        // Obtener los jugadores configurados
        List<Jugador> jugadores = confPartida.getJugadores();
        int fondoApuesta = confPartida.getFondo();

        // Array de paneles y etiquetas de fondo de apuesta para iterar dinámicamente
        JPanel[] panels = {panelJ1, panelJ2, panelJ3, panelJ4};
        JLabel[] fondoApuestaLabels = {fondoApuestalbl1, fondoApuestalbl2, fondoApuestalbl3, fondoApuestalbl4};

        for (int i = 0; i < panels.length; i++) {
            if (i < jugadores.size()) {
                fondoApuestaLabels[i].setText("Fondo de apuesta: " + fondoApuesta);
                panels[i].setVisible(true);
            } else {
                fondoApuestaLabels[i].setVisible(false);
            }
        }
    }

    private void pintarFichas() {
        if (confPartida.getNumFichas() == 2) {
            ficha6Blanco.setVisible(false);
            ficha5Blanco.setVisible(false);
            ficha4Blanco.setVisible(false);
            ficha3Blanco.setVisible(false);
            ficha6Amarillo.setVisible(false);
            ficha5Amarillo.setVisible(false);
            ficha4Amarillo.setVisible(false);
            ficha3Amarillo.setVisible(false);
            ficha6Naranja.setVisible(false);
            ficha5Naranja.setVisible(false);
            ficha4Naranja.setVisible(false);
            Ficha3Naranja.setVisible(false);
            ficha3Cafe.setVisible(false);
            ficha4Cafe.setVisible(false);
            ficha5Cafe.setVisible(false);
            ficha6Cafe.setVisible(false);
        }
        if (confPartida.getNumFichas() == 4) {
            ficha5Blanco.setVisible(false);
            ficha6Blanco.setVisible(false);
            ficha6Amarillo.setVisible(false);
            ficha5Amarillo.setVisible(false);
            ficha5Naranja.setVisible(false);
            ficha6Naranja.setVisible(false);
            ficha6Cafe.setVisible(false);
            ficha5Cafe.setVisible(false);
        }
    }

    public static boolean darVerdaderoFalso() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100);
        return numeroAleatorio < 50;
    }

    private void generarCañas() {
        int casillasAvanzar = 0;
        JLabel[] cañas = {caña1, caña2, caña3, caña4, caña5};
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
        ficha1Blanco = new javax.swing.JLabel();
        ficha2Blanco = new javax.swing.JLabel();
        ficha3Blanco = new javax.swing.JLabel();
        ficha6Blanco = new javax.swing.JLabel();
        ficha4Blanco = new javax.swing.JLabel();
        ficha5Blanco = new javax.swing.JLabel();
        panelJ3 = new javax.swing.JPanel();
        jugadorNaranja = new javax.swing.JLabel();
        fondoApuestalbl3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ficha1Naranja = new javax.swing.JLabel();
        ficha2Naranja = new javax.swing.JLabel();
        Ficha3Naranja = new javax.swing.JLabel();
        ficha4Naranja = new javax.swing.JLabel();
        ficha5Naranja = new javax.swing.JLabel();
        ficha6Naranja = new javax.swing.JLabel();
        panelJ2 = new javax.swing.JPanel();
        jugadorAmarillo = new javax.swing.JLabel();
        fondoApuestalbl2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ficha1Amarillo = new javax.swing.JLabel();
        ficha2Amarillo = new javax.swing.JLabel();
        ficha3Amarillo = new javax.swing.JLabel();
        ficha6Amarillo = new javax.swing.JLabel();
        ficha5Amarillo = new javax.swing.JLabel();
        ficha4Amarillo = new javax.swing.JLabel();
        panelJ4 = new javax.swing.JPanel();
        JugadorCafe = new javax.swing.JLabel();
        fondoApuestalbl4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ficha1Cafe = new javax.swing.JLabel();
        ficha2Cafe = new javax.swing.JLabel();
        ficha3Cafe = new javax.swing.JLabel();
        ficha6Cafe = new javax.swing.JLabel();
        ficha5Cafe = new javax.swing.JLabel();
        ficha4Cafe = new javax.swing.JLabel();
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
        jLabel2 = new javax.swing.JLabel();
        lblApuesta = new javax.swing.JLabel();
        lblTurno = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1058, 549));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelJ1.setBackground(new java.awt.Color(255, 255, 255));
        panelJ1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelJ1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jugadorBlanco.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jugadorBlanco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugadorBlanco.setText("Jugador 1");
        panelJ1.add(jugadorBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 90, -1));

        fondoApuestalbl1.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        fondoApuestalbl1.setText("Fondo de apuestas:");
        panelJ1.add(fondoApuestalbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 45, 188, -1));

        jLabel4.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel4.setText("Fichas:");
        panelJ1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 76, -1, -1));

        ficha1Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(ficha1Blanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 76, -1, -1));

        ficha2Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(ficha2Blanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 76, -1, -1));

        ficha3Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(ficha3Blanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 76, -1, -1));

        ficha6Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(ficha6Blanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 112, -1, -1));

        ficha4Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(ficha4Blanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 112, -1, -1));

        ficha5Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaDos.png"))); // NOI18N
        panelJ1.add(ficha5Blanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 112, -1, -1));

        jPanel1.add(panelJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 160));

        panelJ3.setBackground(new java.awt.Color(255, 255, 255));
        panelJ3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelJ3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jugadorNaranja.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jugadorNaranja.setText("Jugador 3");
        panelJ3.add(jugadorNaranja, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        fondoApuestalbl3.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        fondoApuestalbl3.setText("Fondo de apuestas:");
        panelJ3.add(fondoApuestalbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 45, 183, -1));

        jLabel7.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel7.setText("Fichas:");
        panelJ3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 76, -1, -1));

        ficha1Naranja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(ficha1Naranja, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 76, -1, -1));

        ficha2Naranja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(ficha2Naranja, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 76, -1, -1));

        Ficha3Naranja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(Ficha3Naranja, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 76, -1, -1));

        ficha4Naranja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(ficha4Naranja, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 114, -1, -1));

        ficha5Naranja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(ficha5Naranja, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 114, -1, -1));

        ficha6Naranja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaCuatro.png"))); // NOI18N
        panelJ3.add(ficha6Naranja, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 114, -1, -1));

        jPanel1.add(panelJ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, -1, 160));

        panelJ2.setBackground(new java.awt.Color(255, 255, 255));
        panelJ2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelJ2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jugadorAmarillo.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jugadorAmarillo.setText("Jugador 2");
        panelJ2.add(jugadorAmarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 8, -1, -1));

        fondoApuestalbl2.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        fondoApuestalbl2.setText("Fondo de apuestas:");
        panelJ2.add(fondoApuestalbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 45, 174, -1));

        jLabel10.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel10.setText("Fichas:");
        panelJ2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 76, -1, -1));

        ficha1Amarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(ficha1Amarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 76, -1, -1));

        ficha2Amarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(ficha2Amarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 76, -1, -1));

        ficha3Amarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(ficha3Amarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 76, -1, -1));

        ficha6Amarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(ficha6Amarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 114, -1, -1));

        ficha5Amarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(ficha5Amarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 114, -1, -1));

        ficha4Amarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaTres.png"))); // NOI18N
        panelJ2.add(ficha4Amarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 114, -1, -1));

        jPanel1.add(panelJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 200, 160));

        panelJ4.setBackground(new java.awt.Color(255, 255, 255));
        panelJ4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JugadorCafe.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        JugadorCafe.setText("Jugador 4");
        panelJ4.add(JugadorCafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        fondoApuestalbl4.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        fondoApuestalbl4.setText("Fondo de apuestas:");
        panelJ4.add(fondoApuestalbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 43, 176, -1));

        jLabel13.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel13.setText("Fichas:");
        panelJ4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 74, -1, -1));

        ficha1Cafe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(ficha1Cafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 74, -1, -1));

        ficha2Cafe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(ficha2Cafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 74, -1, -1));

        ficha3Cafe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(ficha3Cafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 74, -1, -1));

        ficha6Cafe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(ficha6Cafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 112, -1, -1));

        ficha5Cafe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(ficha5Cafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 112, -1, -1));

        ficha4Cafe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fichaUno.png"))); // NOI18N
        panelJ4.add(ficha4Cafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 112, -1, -1));

        jPanel1.add(panelJ4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 420, 200, 160));

        caña1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 260, 30, 64));

        caña2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 260, 30, 64));

        caña3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 260, 30, 64));

        caña4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 260, 30, 64));

        caña5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cañaLisa.png"))); // NOI18N
        jPanel1.add(caña5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 260, 30, 64));

        btnLanzarCañas.setBackground(new java.awt.Color(213, 198, 86));
        btnLanzarCañas.setFont(new java.awt.Font("Bodoni MT", 0, 24)); // NOI18N
        btnLanzarCañas.setText("Lanzar Cañas");
        btnLanzarCañas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLanzarCañas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarCañasActionPerformed(evt);
            }
        });
        jPanel1.add(btnLanzarCañas, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 340, 190, 40));

        casillasCentrales.setBackground(new java.awt.Color(0, 51, 102));
        casillasCentrales.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout casillasCentralesLayout = new javax.swing.GroupLayout(casillasCentrales);
        casillasCentrales.setLayout(casillasCentralesLayout);
        casillasCentralesLayout.setHorizontalGroup(
            casillasCentralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
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
            .addGap(0, 100, Short.MAX_VALUE)
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

        jLabel2.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Turno:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 180, 140, 20));

        lblApuesta.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        lblApuesta.setForeground(new java.awt.Color(255, 255, 255));
        lblApuesta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblApuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 130, 20));

        lblTurno.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(255, 255, 255));
        lblTurno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTurno.setText("Jugador 1");
        jPanel1.add(lblTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 200, 140, -1));

        jLabel5.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Apuesta a pagar:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void cambiarTurno() {
        List<Jugador> jugadores = confPartida.getJugadores();
        turnoActual = (turnoActual + 1) % jugadores.size();
        lblTurno.setText("Jugador " + (turnoActual + 1));
    }

    public void setApuesta(int apuesta) {
        lblApuesta.setText("" + apuesta);
    }

    public void actualizarApuesta() {
        int apuesta = confPartida.getApuesta();
        lblApuesta.setText("" + apuesta);
    }

    private void btnLanzarCañasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarCañasActionPerformed
        generarCañas();
        cambiarTurno();
    }//GEN-LAST:event_btnLanzarCañasActionPerformed

    public void mostrarPantalla() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PartidaFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ficha3Naranja;
    private javax.swing.JLabel JugadorCafe;
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
    private javax.swing.JLabel ficha1Amarillo;
    private javax.swing.JLabel ficha1Blanco;
    private javax.swing.JLabel ficha1Cafe;
    private javax.swing.JLabel ficha1Naranja;
    private javax.swing.JLabel ficha2Amarillo;
    private javax.swing.JLabel ficha2Blanco;
    private javax.swing.JLabel ficha2Cafe;
    private javax.swing.JLabel ficha2Naranja;
    private javax.swing.JLabel ficha3Amarillo;
    private javax.swing.JLabel ficha3Blanco;
    private javax.swing.JLabel ficha3Cafe;
    private javax.swing.JLabel ficha4Amarillo;
    private javax.swing.JLabel ficha4Blanco;
    private javax.swing.JLabel ficha4Cafe;
    private javax.swing.JLabel ficha4Naranja;
    private javax.swing.JLabel ficha5Amarillo;
    private javax.swing.JLabel ficha5Blanco;
    private javax.swing.JLabel ficha5Cafe;
    private javax.swing.JLabel ficha5Naranja;
    private javax.swing.JLabel ficha6Amarillo;
    private javax.swing.JLabel ficha6Blanco;
    private javax.swing.JLabel ficha6Cafe;
    private javax.swing.JLabel ficha6Naranja;
    private javax.swing.JLabel fondoApuestalbl1;
    private javax.swing.JLabel fondoApuestalbl2;
    private javax.swing.JLabel fondoApuestalbl3;
    private javax.swing.JLabel fondoApuestalbl4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jugadorAmarillo;
    private javax.swing.JLabel jugadorBlanco;
    private javax.swing.JLabel jugadorNaranja;
    private javax.swing.JLabel lblApuesta;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JPanel panelJ1;
    private javax.swing.JPanel panelJ2;
    private javax.swing.JPanel panelJ3;
    private javax.swing.JPanel panelJ4;
    // End of variables declaration//GEN-END:variables
}
