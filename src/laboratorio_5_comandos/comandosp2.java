/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio_5_comandos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gpopo
 */
public class comandosp2 {
    
    private Gui consola;
    
    public comandosp2(Gui consola){
        this.consola = consola; 
    }
    
    public void dir() {
        java.io.File carpeta = new java.io.File(consola.getDirectorioActual());
        java.io.File[] contenido = carpeta.listFiles();

        consola.EscribirSalida(" Directorio: " + consola.getDirectorioActual() + "\n\n");

        if (contenido == null || contenido.length == 0) {
            consola.EscribirSalida("El directorio esta vacio.\n");
            return;
    }

        int archivos = 0, carpetas = 0;

        for (java.io.File f : contenido) {
            if (f.isDirectory()) {
                consola.EscribirSalida("<DIR>    " + f.getName() + "\n");
                carpetas++;
            } else {
                consola.EscribirSalida("         " + f.getName() + "\n");
                archivos++;
        }
    }

        consola.EscribirSalida("\n");
        consola.EscribirSalida("         " + archivos + " archivos\n");
        consola.EscribirSalida("         " + carpetas + " carpetas\n");
    }
    
    public void date(){
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        consola.EscribirSalida("La fecha actual es: " + fecha);
    }
    
    public void time() {
        String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
        consola.EscribirSalida("La fecha actual es: " + hora + "/n");
    }
    
public void wr(String nombreArchivo) {
    if (nombreArchivo.isEmpty()) {
        consola.EscribirSalida("Uso: wr <archivo.ext>\n");
        return;
    }
    String ruta = consola.getDirectorioActual() + java.io.File.separator + nombreArchivo;
    java.io.File archivo = new java.io.File(ruta);
    if (!archivo.exists()) {
        consola.EscribirSalida("El archivo no existe: " + nombreArchivo + "\n");
        consola.EscribirSalida("Cree el archivo primero con: mfile " + nombreArchivo + "\n");
        return;
    }
    consola.EscribirSalida("Escribiendo en: " + nombreArchivo + "\n");
    consola.iniciarModoEscritura(nombreArchivo);
}   
    
    public void rd(String nombreArchivo){
        if(nombreArchivo.isEmpty()){
            consola.EscribirSalida("Uso: rd <archivo.ext>");
            return;
        }
        
        String ruta = consola.getDirectorioActual() + java.io.File.separator + nombreArchivo;
        
        java.io.File archivo = new java.io.File(ruta);
        if(!archivo.exists()) {
            consola.EscribirSalida("El archivo no existe: " + nombreArchivo);
            return;
        }
        
        consola.EscribirSalida("---- Contenido de " + nombreArchivo + "---");
        
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numLinea = 1;
            boolean vacio = true;
            
            while ((linea = br.readLine()) != null) {
                consola.EscribirSalida(linea);
                numLinea++;
                vacio = false;
            }
            
            if(vacio) {
                consola.EscribirSalida("(El archivo esta vacio)");
            }
        }catch (Exception e) {
            consola.EscribirSalida("Error al leer: " + e.getMessage());
        }
        
        consola.EscribirSalida("--- Fin del Archivo ---");
    } 
}
 