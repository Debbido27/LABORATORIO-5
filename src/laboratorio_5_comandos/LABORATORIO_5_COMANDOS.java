
package laboratorio_5_comandos;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Dell
 */
public class LABORATORIO_5_COMANDOS{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            Gui consola = new Gui();
            consola.setVisible(true);
        });
        
    }
    
}
