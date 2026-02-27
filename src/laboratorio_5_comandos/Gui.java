/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio_5_comandos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.*;


public class Gui extends JFrame {
    
    private JTextArea   areaOutput;
    private JTextField  inputComando;
    private JLabel      labelPrompt;
    private JLabel      labelRuta;
    private JLabel      labelInfo;
    
    private String rutaActual = System.getProperty("user.dir");

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
    
    private void Construir_UI(){
        
    }
    
    private void 
    
    private JPanel CrearBarraTitulo(){
        
    }
    
    private JPanel CrearBarraEstado(){
        
    }
    
    
    
}
