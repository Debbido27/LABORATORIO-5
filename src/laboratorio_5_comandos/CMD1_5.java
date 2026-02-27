
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
        boolean ejecutando = true;
        
        System.out.println("CONSOLA CMD");
        System.out.println("Escribe 'EXIT' para salir");
        System.out.println();
        
        while(ejecutando){
            System.out.println(dirActual.getPath()+">");
            comando = entrada.nextLine().trim();
            
            if(comando.equalsIgnoreCase("exit")){
                //se pone en false para terminar
                ejecutando=false;
            }else if(comando.isEmpty()){
                //ignorar
                continue;
            }else{
                
            }
            
        }
    }
}
