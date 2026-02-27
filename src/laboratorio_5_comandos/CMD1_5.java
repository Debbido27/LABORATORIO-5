
package laboratorio_5_comandos;

import java.io.File;
import java.util.Scanner;


public class CMD1_5 {
    Scanner entrada = new Scanner(System.in);
    private File dirActual;
   
    public  CMD1_5(){
        this.dirActual=new File(System.getProperty("user.dir"));
        
    }
    
    public void iniciar(){
        String comando;
        boolean corriendo = true;
        
        System.out.println("CONSOLA CMD");
        System.out.println("Escribe 'EXIT' para salir");
        System.out.println();
        
    }
}
