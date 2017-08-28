package cl.lai.datamining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class App_listar_transacciones 
{
    public static void main( String[] args ) throws IOException{
    	HashMap<String, String> tipoProducto = new HashMap<String, String>();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/chunhaulai/Documents/workspace/lai-datamining-app/src/resources/nombre_producto_grupo.csv"))));
    	PrintWriter writer = new PrintWriter(new File("/Users/chunhaulai/Documents/workspace/lai-datamining-app/src/resources/listar_producto_grupo_fecha.csv"));
    	 
    	String aux = reader.readLine();
    	TreeSet<String> grupos = new TreeSet<String>();
    	TreeMap<String, HashSet<String>> fechas_grupo = new TreeMap<String, HashSet<String>>();
    	while((aux=reader.readLine())!=null){
    		String fecha = aux.split(";")[1];
    		String fechaFormato[] = fecha.split("/");
     		String producto_grupo  = aux.split(";")[3];
    		grupos.add(fechaFormato[1]); //por cada grupo del producto, se le asigna el mes
    		boolean grupo_existe = fechas_grupo.containsKey(producto_grupo);
    		if(grupo_existe==false)
    			fechas_grupo.put(producto_grupo, new HashSet<String>());
    		fechas_grupo.get(producto_grupo).add(fechaFormato[1]);
    	}
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("grupo");
    	for(String grupo: grupos){
    		buffer.append(";").append(grupo);
    	}
    	System.out.println(buffer);
    	writer.println(buffer);
    	
    	for(Map.Entry<String, HashSet<String>> entry:fechas_grupo.entrySet()){
    		buffer = new StringBuffer();
    		String grupo = entry.getKey();
    		HashSet<String> grupos_asociado_alafecha = entry.getValue();
    		buffer.append(grupo);
    		for(String fecha: grupos){
        		 if(grupos_asociado_alafecha.contains(fecha)){
        			 buffer.append(";").append(1);
        		 }else{
        			 buffer.append(";").append(0);
        		 }
        	}
    		writer.println(buffer);
    	}
    	
    	writer.close();
    }
}
