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

import javax.smartcardio.ATR;

import weka.associations.Apriori;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

/**
 * Hello world!
 *
 */
public class App_listar_reglas_asociacion_dpto 
{
    public static void main( String[] args ) throws IOException{
    	HashMap<String, String> tipoProducto = new HashMap<String, String>();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/chunhaulai/Documents/workspace/lai-datamining-app/src/resources/arriendo_dpto_categoria.csv"))));
    	BufferedReader readerSoloGrupos = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/chunhaulai/Documents/workspace/lai-datamining-app/src/resources/arriendo_dpto_categoria.csv"))));
   	 
    	String aux = null;
    	Instances dptos = null;
    	ArrayList<Attribute> attributes = null;
    	int count = 0;
     	List<HashSet<String>> my_nominal_grupos = new ArrayList<HashSet<String>>( ); 
    	int filas = 0;
    	while((aux=readerSoloGrupos.readLine())!=null){
    		String grupos[] = aux.split(";");
    		if(filas==0){
    			int columna =0;
     			for(String g: grupos){
     				//System.out.println(g+"=>"+columna);
     				my_nominal_grupos.add(new HashSet<String>());
     				columna++;
     			}
    				
     		}else{
    			int columna =0;
     			for(String g: grupos){
     				my_nominal_grupos.get(columna).add(g);
    				columna++;
    			}
    		}
    		filas++;
    	}
    	filas = 0;
      	 while((aux=reader.readLine())!=null){
     		String grupos[] = aux.split(";");
    		if(filas==0){
    			attributes = new ArrayList<Attribute>(grupos.length-1);
        		for(int i=1;i<grupos.length;i++){
        			Attribute attr = new Attribute(grupos[i],new ArrayList<String>(my_nominal_grupos.get(i)));
        			attributes.add(attr);
        		}
        		dptos = new Instances("dptos", attributes, 0);
    		}else{
    			DenseInstance inst = new DenseInstance(attributes.size());
    			//seteo del mes para el atributo 0
    			//System.out.println(grupos[0].split("/")[1]);
     			//seteo del resto de los atributos
    			for(int i=1;i<grupos.length;i++){
    				if( (">400".equalsIgnoreCase(grupos[i]) )){
    					inst.setMissing(attributes.get(i-1 ));
    				}else
    			//	System.out.println(attributes.get(i-1 ));
    				//System.out.println(grupos[i]);
    					inst.setValue(attributes.get(i-1 ),grupos[i]);
        		}
    			dptos.add(inst);
    		}
    		filas++;
    		 
    	}
     	Apriori aprioriObj = new Apriori();
    	try {
    		//minimo soporte 0.1, minima confianza 0.5, cantidad de reglas: 30
    		String []options =  {"-C","0.5","-N","30","-M","0.1"};
    		aprioriObj.setOptions(options);
    		aprioriObj.buildAssociations(dptos);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	System.out.println(aprioriObj);
    }
}
