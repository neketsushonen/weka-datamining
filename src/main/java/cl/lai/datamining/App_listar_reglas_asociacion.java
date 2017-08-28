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
public class App_listar_reglas_asociacion 
{
    public static void main( String[] args ) throws IOException{
    	HashMap<String, String> tipoProducto = new HashMap<String, String>();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/chunhaulai/Documents/workspace/lai-datamining-app/src/resources/listar_producto_grupo_fecha.csv"))));
    	 
    	String aux = null;
    	Instances ventas = null;
    	ArrayList<Attribute> attributes = null;
    	int count = 0;
    	 while((aux=reader.readLine())!=null){
    		String grupos[] = aux.split(";");
    		if(count==0){
    			attributes = new ArrayList<Attribute>(grupos.length-1);
        		for(int i=1;i<grupos.length;i++){
        			ArrayList<String> my_nominal_values = new ArrayList<String>(3); 
        	        my_nominal_values.add("1");  
         	        Attribute attr = new Attribute(grupos[i],my_nominal_values);
        			attributes.add(attr);
        		}
        		ventas = new Instances("ventas", attributes, 0);
    		}else{
    			DenseInstance inst = new DenseInstance(attributes.size());
    			for(int i=1;i<grupos.length;i++){
    				if( ("0".equalsIgnoreCase(grupos[i]) )){
    					inst.setMissing(attributes.get(i-1));
    				}else
    					inst.setValue(attributes.get(i-1),"1" );
        		}
    			ventas.add(inst);
    		}
    		count++;
    		 
    	}
    	 
    	Apriori aprioriObj = new Apriori();
    	try {
    		String []options =  {"-C","0.9"};
    		aprioriObj.setOptions(options);
    		aprioriObj.buildAssociations(ventas);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	System.out.println(aprioriObj);
    }
}
