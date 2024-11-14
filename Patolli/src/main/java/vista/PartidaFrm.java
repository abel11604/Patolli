package vista;

import control.ControlConfigurarPartida;
import control.ControlPartida;
import control.IControlConfigurarPartida;
import control.IControlPartida;
import entidades.Casilla;
import entidades.Jugador;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author abelc
 */
public class PartidaFrm extends javax.swing.JFrame {

    private List<JLabel> casillas;
    private IControlConfigurarPartida confPartida;
    private IControlPartida partida;
    private Jugador turnoActual;
    private Map<Casilla, JLabel> casillaVistaMap = new HashMap<>();

    /**
     * Creates new form PartidaFrm
     */
    public PartidaFrm() {
        this.confPartida = ControlConfigurarPartida.getInstance();
        this.partida = ControlPartida.getInstance();
        partida.setPartida(confPartida.crearPartida());
        initComponents();
        // Ocultar todos los paneles al inicio
        panelJ1.setVisible(false);
        panelJ2.setVisible(false);
        panelJ3.setVisible(false);
        panelJ4.setVisible(false);
        casillas = new ArrayList<>();
        generarTablero();
        pintarJugadores();
        pintarFichas();
        iniciarTurno();
        vincularCasillasConVista(casillas);

        setApuesta(partida.getPartida().getApuesta());
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

    private int pintarCañas() {
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

        System.out.println("Casillas a avanzar: " + casillasAvanzar);
        return casillasAvanzar;
    }

    public void vincularCasillasConVista(List<JLabel> casillasVista) {
        List<Casilla> casillasModelo = partida.getPartida().getCasillas();
        int casillasPorAspa = confPartida.getCasillaPorAspa();

        int[] indicesMapeo;
        switch (casillasPorAspa) {
            case 8:
                indicesMapeo = new int[]{
                    15, 13, 11, 9, 7, 5, 3, 1, 0,
                    2, 4, 6, 8, 10, 12, 14, 64,
                    55, 54, 53, 52, 51, 50, 49, 48,
                    56, 57, 58, 59, 60, 61, 62, 63, 66,
                    16, 18, 20, 22, 24, 26, 28, 30, 31, 29,
                    27, 25, 23, 21, 19, 17, 67, 40,
                    41, 42, 43, 44, 45, 46, 47, 39,
                    38, 37, 36, 35, 34, 33, 32, 65
                };
                break;

            case 10:
                indicesMapeo = new int[]{
                    19, 17, 15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8,
                    10, 12, 14, 16, 18, 83,
                    61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48,
                    62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 82,
                    20, 22, 24, 26, 28, 30, 32, 34, 35, 33, 31, 29, 27, 25, 23, 21, 84,
                    80, 79, 78, 77, 76, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 81
                };
                break;

            case 14:

                indicesMapeo = new int[]{
                    27, 25, 23, 21, 19, 17, 15, 13, 11, 9, 7, 5, 3, 1, 0,
                    2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 112, 97,
                    96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 88,
                    99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 114, 28, 30,
                    32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 55, 53, 51, 49,
                    47, 45, 43, 41, 39, 37, 35, 33, 31, 29, 115, 70, 71, 72, 73, 74,
                    75, 76, 77, 78, 79, 80, 81, 82, 83, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 113

                };
                break;

            default:
                throw new IllegalArgumentException("Tamaño de casillas por aspa no soportado: " + casillasPorAspa);
        }

        // Crear el mapeo entre el modelo y la vista basado en el arreglo de índices
        for (int i = 0; i < casillasModelo.size(); i++) {
            int indiceVista = indicesMapeo[i];
            casillaVistaMap.put(casillasModelo.get(i), casillasVista.get(indiceVista));
        }
    }

    public void actualizarVistaCasilla(Casilla casilla) {
        JLabel casillaLabel = casillaVistaMap.get(casilla);
        if (casillaLabel != null) {
            if (casilla.getOcupadoPor() != null) {
                casillaLabel.setBackground(Color.BLUE); // Cambiar el color según el estado
            } else {
                casillaLabel.setBackground(Color.WHITE); // Restablecer si está vacía
            }
        }

    }

    private void generarTablero() {
        int contadorCasillas = 1; // Contador para enumerar todas las casillas
        contadorCasillas = pintarTablero(casillasArriba, confPartida.getCasillaPorAspa(), 2, false, contadorCasillas);
        contadorCasillas = pintarTablero(casillasAbajo, confPartida.getCasillaPorAspa(), 2, true, contadorCasillas);
        contadorCasillas = pintarTablero(casillasDer, 2, confPartida.getCasillaPorAspa(), true, contadorCasillas);
        contadorCasillas = pintarTablero(casillasIzq, 2, confPartida.getCasillaPorAspa(), false, contadorCasillas);
        pintarTablero(casillasCentrales, 2, 2, true, contadorCasillas);
    }

    private int pintarTablero(JPanel tablero, int filas, int columnas, boolean invertir, int contadorCasillas) {
        tablero.setLayout(new GridLayout(filas, columnas));
        tablero.setPreferredSize(tablero.getSize());
        tablero.setMinimumSize(tablero.getSize());
        tablero.setMaximumSize(tablero.getSize());

        for (int i = 1; i <= filas * columnas; i++) {
            JLabel label = new JLabel(String.valueOf(contadorCasillas), SwingConstants.CENTER); // Etiqueta con el número de casilla
            label.setBorder(new LineBorder(Color.BLACK, 1));
            label.setOpaque(true);
            label.setBackground(Color.WHITE);

            // Colorear las casillas de acuerdo con su posición
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
            casillas.add(label); // Añadir el JLabel al listado general de casillas
            contadorCasillas++; // Incrementar el contador para el siguiente JLabel
        }

        return contadorCasillas; // Retornar el contador actualizado para la siguiente sección del tablero
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
        jugadorCafe = new javax.swing.JLabel();
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

        jugadorCafe.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jugadorCafe.setText("Jugador 4");
        panelJ4.add(jugadorCafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

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
        casillasDer.setPreferredSize(new java.awt.Dimension(196, 102));

        javax.swing.GroupLayout casillasDerLayout = new javax.swing.GroupLayout(casillasDer);
        casillasDer.setLayout(casillasDerLayout);
        casillasDerLayout.setHorizontalGroup(
            casillasDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        casillasDerLayout.setVerticalGroup(
            casillasDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jPanel1.add(casillasDer, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 251, -1, 100));

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

    public void iniciarTurno() {
        List<Jugador> jugadores = confPartida.getJugadores();
        turnoActual = jugadores.get(0); // Inicializar con el primer jugador al iniciar el juego
        actualizarEtiquetaTurno();
    }

    public void cambiarTurno() {
        List<Jugador> jugadores = confPartida.getJugadores();

        // Encontrar el índice del jugador actual y mover al siguiente jugador de manera cíclica
        int indiceActual = jugadores.indexOf(turnoActual);
        int siguienteIndice = (indiceActual + 1) % jugadores.size();
        turnoActual = jugadores.get(siguienteIndice);

        // Actualizar la etiqueta de turno
        actualizarEtiquetaTurno();
    }

    private void actualizarEtiquetaTurno() {
        lblTurno.setText(turnoActual.getNombre());
    }

    public void setApuesta(int apuesta) {
        lblApuesta.setText("" + apuesta);
    }

    public void actualizarApuesta() {
        int apuesta = partida.getPartida().getApuesta();
        lblApuesta.setText("" + apuesta);
    }

    public void actualizarFondoApuesta() {
        JLabel[] etiquetasFondoApuesta = {fondoApuestalbl1, fondoApuestalbl2, fondoApuestalbl3, fondoApuestalbl4};

        List<Jugador> jugadores = partida.getPartida().getJugadores();

        // Recorrer los jugadores y actualizar el texto de su respectiva etiqueta
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            etiquetasFondoApuesta[i].setText("Fondo de apuesta: " + jugador.getFondoApuesta());
        }
    }

    private void pintarJugadores() {
        // Obtener los jugadores configurados
        List<Jugador> jugadores = partida.getPartida().getJugadores();

        // Arreglos de etiquetas y paneles
        JLabel[] etiquetasJugadores = {jugadorBlanco, jugadorAmarillo, jugadorNaranja, jugadorCafe};
        JLabel[] fondoApuestaLabels = {fondoApuestalbl1, fondoApuestalbl2, fondoApuestalbl3, fondoApuestalbl4};
        JPanel[] panels = {panelJ1, panelJ2, panelJ3, panelJ4};

        // Recorrer los jugadores y actualizar etiquetas y visibilidad de paneles
        for (int i = 0; i < panels.length; i++) {
            if (i < jugadores.size()) {
                Jugador jugador = jugadores.get(i);
                panels[i].setVisible(true); // Mostrar panel del jugador
                etiquetasJugadores[i].setText(jugador.getNombre()); // Actualizar el nombre del jugador
                fondoApuestaLabels[i].setText("Fondo de apuesta: " + jugador.getFondoApuesta()); // Actualizar el fondo de apuesta
                fondoApuestaLabels[i].setVisible(true); // Asegurarse de que la etiqueta de fondo esté visible
            } else {
                panels[i].setVisible(false); // Ocultar panel si no hay jugador
                fondoApuestaLabels[i].setVisible(false); // Ocultar etiqueta de fondo si no hay jugador
            }
        }
    }

    private void btnLanzarCañasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarCañasActionPerformed
        int resultado = pintarCañas();
        if (resultado == 1) {
            partida.reiniciarFicha(turnoActual.getFichas().get(1));
            actualizarVistaCasilla(turnoActual.getFichas().get(1).getCasillaActual());
            for (Casilla casilla : partida.getPartida().getCasillas()) {
                String ocupadaPor = (casilla.getOcupadoPor() != null)
                        ? casilla.getOcupadoPor().getJugador().getNombre()
                        : "no ocupado";
                System.out.println("numC: " + casilla.getNumCasilla() + " tipo: " + casilla.getTipo() + " ocupadaPor: " + ocupadaPor);
            }

        }
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
    private javax.swing.JLabel jugadorCafe;
    private javax.swing.JLabel jugadorNaranja;
    private javax.swing.JLabel lblApuesta;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JPanel panelJ1;
    private javax.swing.JPanel panelJ2;
    private javax.swing.JPanel panelJ3;
    private javax.swing.JPanel panelJ4;
    // End of variables declaration//GEN-END:variables
}
