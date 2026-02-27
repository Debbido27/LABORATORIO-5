/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio_5_comandos;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*; 


public class Gui extends JFrame {
    
    private JTextArea   areaOutput;
    private JTextArea areaConsola;
    private JLabel    labelRuta;
    private JLabel    labelInfo;
    
    CMD1_5 logicaBase = new CMD1_5(this);
    comandosp2 logicaP2 = new comandosp2(this);
    private File dirActual = new File(System.getProperty("user.dir"));
    private boolean modoEscritura = false;
    private String archivoEscritura ="";
    private String rutaActual = System.getProperty("user.dir");
    private int    inicioLinea = 0;

    private static final Color C_FONDO  = new Color(12,  12,  12);
    private static final Color C_TEXTO  = new Color(204, 204, 204);
    private static final Color C_TITULO = new Color(0,   80,  160);
    private static final Color C_ESTADO = new Color(0,  100,  180);

    private static final Font FUENTE_CONSOLA = new Font("Courier New", Font.PLAIN, 14);
    private static final Font FUENTE_UI      = new Font("Segoe UI",    Font.PLAIN, 11);

    public Gui(){
        ConfigurarVentana();
        Construir_UI();
        MostrarBienvenida();
        
        
    }
    
    
    private void ConfigurarVentana(){
        setTitle("Administrator: Command Prompt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 580);
        setMinimumSize(new Dimension(640, 400));
        setLocationRelativeTo(null);
        setUndecorated(true);      
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));

    }
            public void wr(String nombreArchivo){
                if(nombreArchivo.isEmpty()){
                    EscribirSalida("Uso: wr <archivo.ext>");
                    return;
                }
                iniciarModoEscritura(nombreArchivo);
            }
    private void Construir_UI(){
        setLayout(new BorderLayout());
        add(CrearBarraTitulo(), BorderLayout.NORTH);
        add(CrearConsola(),     BorderLayout.CENTER);
    }
    
    private void MostrarBienvenida(){
        // os.name = identifica el sistema operativo
        // os.version = identifica la version
        // os.arch =identifica el procesador
        
        String osNombre  = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch    = System.getProperty("os.arch");

        EscribirSalida(osNombre + " [Version " + osVersion + "] (" + osArch + ")\n");
        EscribirSalida("(c) Microsoft Corporation. All rights reserved.\n\n");
        EscribirPrompt();
        areaConsola.requestFocusInWindow();
    }
    
    void EscribirSalida(String texto) {
        areaConsola.append(texto);
    }
    
    private void EscribirPrompt() {
        areaConsola.append(rutaActual + ">");
        inicioLinea = areaConsola.getDocument().getLength();
        areaConsola.setCaretPosition(inicioLinea);
    }
    
    private JScrollPane CrearConsola() {
        areaConsola = new JTextArea();
        areaConsola.setBackground(C_FONDO);
        areaConsola.setForeground(C_TEXTO);
        areaConsola.setFont(FUENTE_CONSOLA);
        areaConsola.setCaretColor(Color.WHITE);
        areaConsola.setLineWrap(true);
        areaConsola.setWrapStyleWord(false);
        areaConsola.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        areaConsola.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int caret = areaConsola.getCaretPosition();

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    ProcesarComando();

                    return;
                }

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (caret <= inicioLinea) e.consume();
                    return;
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_HOME) {
                    if (caret <= inicioLinea) e.consume();
                    return;
                }

                if (caret < inicioLinea) {
                    areaConsola.setCaretPosition(areaConsola.getDocument().getLength());
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (areaConsola.getCaretPosition() < inicioLinea) {
                    areaConsola.setCaretPosition(areaConsola.getDocument().getLength());
                }
            }
        });

        areaConsola.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (areaConsola.getCaretPosition() < inicioLinea) {
                    areaConsola.setCaretPosition(areaConsola.getDocument().getLength());
                }
            }
        });

        JScrollPane scroll = new JScrollPane(areaConsola);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(C_FONDO);
        scroll.getVerticalScrollBar().setBackground(new Color(30, 30, 30));

        return scroll;
    }
    
    
    private JPanel CrearBarraTitulo() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(C_TITULO);
        barra.setPreferredSize(new Dimension(0, 32));

        JPanel izq = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 7));
        izq.setOpaque(false);

        JLabel icono = new JLabel("C:\\");
        icono.setFont(new Font("Courier New", Font.BOLD, 10));
        icono.setForeground(Color.WHITE);
        icono.setOpaque(true);
        icono.setBackground(Color.BLACK);
        icono.setBorder(BorderFactory.createEmptyBorder(1, 3, 1, 3));
        icono.setPreferredSize(new Dimension(34, 18));
        icono.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel titulo = new JLabel("Administrator: Command Prompt");
        titulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titulo.setForeground(Color.WHITE);

        izq.add(icono);
        izq.add(titulo);

        JPanel der = new JPanel(new FlowLayout(FlowLayout.RIGHT, 1, 4));
        der.setOpaque(false);

        JButton btnMin    = CrearBotonVentana("─", new Color(70,120,190), new Color(100,150,220));
        JButton btnMax    = CrearBotonVentana("□", new Color(70,120,190), new Color(100,150,220));
        JButton btnCerrar = CrearBotonVentana("✕", new Color(70,120,190), new Color(196, 43, 28));

        btnMin.addActionListener(e -> setState(Frame.ICONIFIED));
        btnMax.addActionListener(e -> {
            if (getExtendedState() == MAXIMIZED_BOTH) setExtendedState(NORMAL);
            else setExtendedState(MAXIMIZED_BOTH);
        });
        btnCerrar.addActionListener(e -> System.exit(0));

        der.add(btnMin);
        der.add(btnMax);
        der.add(btnCerrar);

        barra.add(izq, BorderLayout.WEST);
        barra.add(der, BorderLayout.EAST);

        final Point[] puntoArrastre = {null};
        barra.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { puntoArrastre[0] = e.getPoint(); }
        });
        barra.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (puntoArrastre[0] != null) {
                    Point loc = getLocation();
                    setLocation(loc.x + e.getX() - puntoArrastre[0].x,
                                loc.y + e.getY() - puntoArrastre[0].y);
                }
            }
        });

        return barra;
    }
    
    // solo para guardar lo que creamos
    private JButton CrearBotonVentana(String texto, Color normal, Color hover) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(normal);
        btn.setPreferredSize(new Dimension(46, 24));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            
            public void mouseEntered(MouseEvent e) { 
                btn.setBackground(hover);
            }
            public void mouseExited (MouseEvent e) { 
                btn.setBackground(normal); 
            }
            
        });
        return btn;
    }
    
    private JPanel CrearBarraEstado(){
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(C_ESTADO);
        barra.setPreferredSize(new Dimension(0, 24));
        barra.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        labelRuta = new JLabel("  " + rutaActual);
        labelRuta.setFont(FUENTE_UI);
        labelRuta.setForeground(Color.WHITE);

        String osNombre  = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        
        labelInfo = new JLabel(osNombre + " [Version " + osVersion + "]  |  Presiona ENTER para ejecutar");
        labelInfo.setFont(FUENTE_UI);
        labelInfo.setForeground(new Color(200, 220, 255));

        barra.add(labelRuta, BorderLayout.WEST);
        barra.add(labelInfo, BorderLayout.EAST);

        return barra;
    }
    
    public void escribirLinea(String texto) {
    EscribirSalida(texto + "\n");
    }

    public String getDirectorioActual() {
        return dirActual.getAbsolutePath();
    }

    public void setDirectorioActual(File dir) {
        this.dirActual = dir;
        this.rutaActual = dir.getAbsolutePath();
    }

    public void iniciarModoEscritura(String nombreArchivo) {
        modoEscritura = true;
        archivoEscritura = nombreArchivo;
        EscribirSalida("(Escriba texto. Escriba EXIT para terminar)\n>> ");
        inicioLinea = areaConsola.getDocument().getLength();
    }

    private void ProcesarComando() {
        int fin = areaConsola.getDocument().getLength();
        String linea;
        try {
            linea = areaConsola.getDocument().getText(inicioLinea, fin - inicioLinea).trim();
        } catch (BadLocationException ex) {
            linea = "";
        }

        if (linea.isEmpty()) {
            EscribirSalida("\n\n");
            EscribirPrompt();
            return;
        }

        EscribirSalida("\n");

        String comando = linea.split("\\s+")[0].toLowerCase();
        String[] partes = linea.split("\\s+", 2);
         comando = partes[0].toLowerCase();
        String arg = partes.length > 1 ? partes[1] : "";
        switch (comando){
            case "mkdir": logicaBase.mkdir(arg);  break;
            case "mfile": logicaBase.mifile(arg);   break;
            case "rm":    logicaBase.rm(arg);      break;
            case "cd":    logicaBase.cd(arg);      break;
            case "<...>": logicaBase.cd("..");     break;
          case "dir":   logicaP2.dir();        break;
            case "date":  logicaP2.date();         break;
            case "time":  logicaP2.time();         break;
            case "wr":    logicaP2.wr(arg);        break;
            case "rd":    logicaP2.rd(arg);        break;
                
            default:
                EscribirSalida("'" + linea + "' no se reconoce el comando ingresado.\n");
                break;
        }

        EscribirSalida("\n");
        areaConsola.setCaretPosition(areaConsola.getDocument().getLength());
        EscribirPrompt();
    }
}
