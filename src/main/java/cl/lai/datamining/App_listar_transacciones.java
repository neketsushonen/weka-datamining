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
    	HashSet<String> grupos = new HashSet<String>();
    	TreeMap<String, HashSet<String>> fechas_grupo = new TreeMap<String, HashSet<String>>();
    	while((aux=reader.readLine())!=null){
    		String fecha = aux.split(";")[1];
    		String fechaFormato[] = fecha.split("/");
    		String fechaFormateada = fechaFormato[2]+"/"+fechaFormato[1]+"/"+fechaFormato[0];
    		String producto_grupo  = aux.split(";")[3];
    		grupos.add(producto_grupo);
    		boolean fecha_existe = fechas_grupo.containsKey(fechaFormateada);
    		if(fecha_existe==false)
    			fechas_grupo.put(fechaFormateada, new HashSet<String>());
    		fechas_grupo.get(fechaFormateada).add(producto_grupo);
    	}
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("fecha");
    	for(String grupo: grupos){
    		buffer.append(";").append(grupo);
    	}
    	System.out.println(buffer);
    	writer.println(buffer);
    	
    	for(Map.Entry<String, HashSet<String>> entry:fechas_grupo.entrySet()){
    		buffer = new StringBuffer();
    		String fecha = entry.getKey();
    		HashSet<String> grupos_asociado_alafecha = entry.getValue();
    		buffer.append(entry.getKey());
    		for(String grupo: grupos){
        		 if(grupos_asociado_alafecha.contains(grupo)){
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
