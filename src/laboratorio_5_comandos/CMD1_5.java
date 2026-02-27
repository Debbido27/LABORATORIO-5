
package laboratorio_5_comandos;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class CMD1_5 {
    Scanner entrada = new Scanner(System.in);
    private File dirActual;
       private Gui consola;

    public  CMD1_5(){
        this.dirActual=new File(System.getProperty("user.dir"));
        this.consola = consola;
    }
    
    public void iniciar(){
        String comando;
        boolean ejecutando = true;
        
         consola.EscribirSalida("CONSOLA CMD");
         consola.EscribirSalida("Escribe 'EXIT' para salir");
         consola.EscribirSalida("");
        
        while(ejecutando){
            consola.EscribirSalida(dirActual.getPath()+">");
            comando = entrada.nextLine().trim();
            
            if(comando.equalsIgnoreCase("exit")){
                //se pone en false para terminar
                ejecutando=false;
            }else if(comando.isEmpty()){
                //ignorar
                continue;
            }else{
               procesarComando(comando); 
            }
            
        }
    }
    
    private void procesarComando(String comando){
        String [] partes = comando.split(" ",2);
        String comandoPrincipal = partes [0].toLowerCase();
        String comandos = partes.length > 1 ? partes[1]:"";
        
        //SWITCH PARA LOS COMANDOS DEL USUARIO
        
        switch (comandoPrincipal){
            case "mkdir":
                mkdirs(comandos);
                break;
                
            case  "mfile":
                Mfile(comandos);
                break;
                
            case "Rm":
                Rm(comandos);
                break;
                
            case "Cd":
                Cd(comandos);
                break;
                
            case "<...>":
                Cd("..");
                break;
                
            default:
                 consola.EscribirSalida("'"+comandoPrincipal+"' no se reconoce como un comando interno.");
                break;    
        }
    }
    
    private void mkdirs(String FolderName){
        if(FolderName.isEmpty()){
             consola.EscribirSalida("ERROR: Especificar nombre para la carpeta...");
             consola.EscribirSalida("Ex: mkdir <nombre> ");
            return;
        }
        
        File newFolder = new File (dirActual, FolderName);
        
        if(newFolder.exists()){
             consola.EscribirSalida("Error: Ya existe una carpeta con ese nombre");
        }else{
            if(newFolder.mkdir()){
                 consola.EscribirSalida("Carpeta '"+FolderName + "'creada exitosamente.");
            }else{
                 consola.EscribirSalida("Eror: No se pudo crear la carpeta");
            }
        }
    }
    
    
    private void Mfile (String FileName){
        if(FileName.isEmpty()){
             consola.EscribirSalida("Error: Debes especificar un nombre para el archivo...");
             consola.EscribirSalida("Ex: mfile <nombre.ext>");
            return;
        }
        
        File newFile = new File(dirActual, FileName);
        
        if(newFile.exists()){
             consola.EscribirSalida("Error: Ya existe un archivo con ese nombre");
        }else{
            try{
                if(newFile.createNewFile()){
                     consola.EscribirSalida("Archivo '"+FileName+"' creado exitosamente");
                }
            }catch(IOException e){                         //Se investigo get message imprime el error especifico
                 consola.EscribirSalida("Error al crera archivo"+e.getMessage());
            }
        }
    }
    
    
    private void Rm(String nombre){
        if(nombre.isEmpty()){
             consola.EscribirSalida("Error:  Debe especificar el nombre del archivo o carpeta");
             consola.EscribirSalida("Uso: rm <nombre>");
            return;
        }
        
        File elemento = new File (dirActual,nombre);
        
        if(!elemento.exists()){
             consola.EscribirSalida("Error: No existe un archivo o carpeta con el nombre '"+nombre+" '");
            return;
        }
      
            if(elemento.delete()){
                System.out.println("Carpeta '"+nombre+"' eliminada exisotamente...");
            }else{
                System.out.println("Error: No se pudo eliminar el archivo...");  
        }
    }
    
    private void Cd(String FolderNombre){
        if(FolderNombre.isEmpty()){
             consola.EscribirSalida("Error: Debe especificar una carpeta...");
             consola.EscribirSalida("Ex: cd <nombre carpeta>");
            return;
        }
        
        if(FolderNombre.equals("..")){
            File carpetaRaiz = dirActual.getParentFile();
            
            if(carpetaRaiz!=null){
                dirActual = carpetaRaiz;
                 consola.EscribirSalida("Dir actual cambiado a: "+dirActual.getPath());
            }else{
                 consola.EscribirSalida("Ya esta en el directorio raiz.");
                
            }
            return;
            }
           File nuevaCarpeta = new File(dirActual,FolderNombre);
           
           if(!nuevaCarpeta.exists()){
                consola.EscribirSalida("Error: La carpeta '"+FolderNombre+"' no existe...");
           }else if(!nuevaCarpeta.isDirectory()){
                consola.EscribirSalida("Error: '"+FolderNombre+"' no es una carpeta");
           }else{
               dirActual=nuevaCarpeta;
                consola.EscribirSalida("Directorio actual cambiado a: "+dirActual.getPath());
           }
        }
    
    
    
    }


    
   

