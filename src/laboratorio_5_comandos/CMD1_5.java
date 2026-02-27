
package laboratorio_5_comandos;

import java.io.File;
import java.io.IOException;
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
                System.out.println("'"+comandoPrincipal+"' no se reconoce como un comando interno.");
                break;    
        }
    }
    
    private void mkdirs(String FolderName){
        if(FolderName.isEmpty()){
            System.out.println("ERROR: Especificar nombre para la carpeta...");
            System.out.println("Ex: mkdir <nombre> ");
            return;
        }
        
        File newFolder = new File (dirActual, FolderName);
        
        if(newFolder.exists()){
            System.out.println("Error: Ya existe una carpeta con ese nombre");
        }else{
            if(newFolder.mkdir()){
                System.out.println("Carpeta '"+FolderName + "'creada exitosamente.");
            }else{
                System.out.println("Eror: No se pudo crear la carpeta");
            }
        }
    }
    
    
    private void Mfile (String FileName){
        if(FileName.isEmpty()){
            System.out.println("Error: Debes especificar un nombre para el archivo...");
            System.out.println("Ex: mfile <nombre.ext>");
            return;
        }
        
        File newFile = new File(dirActual, FileName);
        
        if(newFile.exists()){
            System.out.println("Error: Ya existe un archivo con ese nombre");
        }else{
            try{
                if(newFile.createNewFile()){
                    System.out.println("Archivo '"+FileName+"' creado exitosamente");
                }
            }catch(IOException e){                         //Se investigo get message imprime el error especifico
                System.out.println("Error al crera archivo"+e.getMessage());
            }
        }
    }
    
    
    private void Rm(String nombre){
        if(nombre.isEmpty()){
            System.out.println("Error:  Debe especificar el nombre del archivo o carpeta");
            System.out.println("Uso: rm <nombre>");
            return;
        }
        
        File elemento = new File (dirActual,nombre);
        
        if(!elemento.exists()){
            System.out.println("Error: No existe un archivo o carpeta con el nombre '"+nombre+" '");
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
            System.out.println("Error: Debe especificar una carpeta...");
            System.out.println("Ex: cd <nombre carpeta>");
            return;
        }
        
        if(FolderNombre.equals("..")){
            File carpetaRaiz = dirActual.getParentFile();
            
            if(carpetaRaiz!=null){
                dirActual = carpetaRaiz;
                System.out.println("Dir actual cambiado a: "+dirActual.getPath());
            }else{
                System.out.println("Ya esta en el directorio raiz.");
                
            }
            return;
            }
           File nuevaCarpeta = new File(dirActual,FolderNombre);
           
           if(!nuevaCarpeta.exists()){
               System.out.println("Error: La carpeta '"+FolderNombre+"' no existe...");
           }else if(!nuevaCarpeta.isDirectory()){
               System.out.println("Error: '"+FolderNombre+"' no es una carpeta");
           }else{
               dirActual=nuevaCarpeta;
               System.out.println("Directorio actual cambiado a: "+dirActual.getPath());
           }
        }
    
    
    
    }


    
   

