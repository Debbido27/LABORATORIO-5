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
    
    public void date(){
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        consola.escribirLinea("La fecha actual es: " + fecha);
    }
    
    public void time() {
        String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
        consola.escribirLinea("La fecha actual es: " + fecha);
    }
    
    public void wr(String nombreArchivo)  {
        if(nombreArchivo.isEmpty()){
            consola.escribirLinea("Uso: wr <archivo.ext>");
            return;
        }
        
        String ruta = consola.getDirectorioActual()+java.io.File.separator+nombreArchivo;
        
        java.io.File archivo = new java.io.File(ruta);
        if(!archivo.exists()){
            consola.escribirLinea("El archivo no existe: " + nombreArchivo);
            consola.escribirLinea("Cree el arhcivo primero con: mfile" + nombreArchivo);
            return;
        }
        
        consola.escribirLinea("Escribiendo en: " + nombreArchivo);
        consola.escribirLinea("(Escriba texto linea por linea. Escriba EXIT para terminar)");
        
        try (FileWriter fw = new FileWriter(archivo, true)){
            while(true){
                String linea = consola.pedirTexto("(Ingrese texto EXIT para terminar):");
                if(linea == null){ 
                    consola.escribirLinea("Escritura Cancelada");
                    break;
                }
                
                if(linea.equals("EXIT")){
                    consola.escribirLinea("Escritura finalizada");
                    break;
                }
                
                fw.write(linea + System.lineSeparator());
                consola.escribirLinea(linea);         
            }
        } catch (Exception e) {
            consola.escribirLinea("Error al escribir; " + e.getMessage());
        }
    }
    
    public void rd(String nombreArchivo){
        if(nombreArchivo.isEmpty()){
            consola.escribirLinea("Uso: rd <archivo.ext>");
            return;
        }
        
        String ruta = consola.getDirectorioActual() + java.io.File.separator + nombreArchivo;
        
        java.io.File archivo = new java.io.File(ruta);
        if(!archivo.exists()) {
            consola.escribirLinea("El archivo no existe: " + nombreArchivo);
            return;
        }
        
        consola.escribirLinea("---- Contenido de " + nombreArchivo + "---");
        
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numLinea = 1;
            boolean vacio = true;
            
            while ((linea = br.readLine()) != null) {
                consola.escribirLinea(linea);
                numLinea++;
                vacio = false;
            }
            
            if(vacio) {
                consola.escribirLinea("(El archivo esta vacio)");
            }
        }catch (Exception e) {
            consola.escribirLinea("Error al leer: " + e.getMessage());
        }
        
        consola.escribirLinea("--- Fin del Archivo ---");
    } 
}
 