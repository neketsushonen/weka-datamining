package cl.lai.datamining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App_definir_grupo 
{
    public static void main( String[] args ) throws IOException{
    	HashMap<String, String> tipoProducto = new HashMap<String, String>();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/chunhaulai/Documents/workspace/lai-datamining-app/src/resources/nombre_producto.csv"))));
    	PrintWriter writer = new PrintWriter(new File("/Users/chunhaulai/Documents/workspace/lai-datamining-app/src/resources/nombre_producto_grupo.csv"));
    	writer.println("producto;fecha;codigo_gurpo;grupo");
    	String aux = reader.readLine();
    	while((aux=reader.readLine())!=null){
    		String arrays[] = aux.replaceAll("\"", "").split(",");
    		String producto  = arrays[0];
    		String soundex = Soundex.soundex(producto);
    		tipoProducto.put(producto, soundex);
    		System.out.println(producto.split(" ")[0]+";"+soundex);
    		writer.println(arrays[0]+";"+arrays[1]+";"+soundex+";"+producto.split(" ")[0]);
    	}
    	writer.close();
    }
}
