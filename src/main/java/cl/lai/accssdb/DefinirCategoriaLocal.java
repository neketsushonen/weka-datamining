package cl.lai.accssdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cl.lai.datamining.Soundex;

public class DefinirCategoriaLocal {

	public static Statement getStatementLocal() throws SQLException{
        Connection c=null;
        try {Class.forName("org.postgresql.Driver"); } 
        catch (ClassNotFoundException cnfe) {
        	System.err.println("Couldn't find driver class:");
            cnfe.printStackTrace();
        }
        try {
        	c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/georeferenciador", "chunhaulai", "");
        } catch (SQLException se) {
        	System.out.println("Couldn't connect: print out a stack trace and exit.");
            se.printStackTrace();
            System.exit(1);
        }
        Statement s = null;
        try {s = c.createStatement();} 
        catch (SQLException se) {
        	System.out.println("We got an exception while creating a statement:" +
                              "that probably means we're no longer connected.");
            se.printStackTrace();
            System.exit(1);
        }
        return s;
    }
	public static void main(String[] args)throws Exception {
		StringBuffer buffer = new StringBuffer();
 		
		List<Rubro> rubros = new ArrayList<Rubro>();
		Statement s = getStatementLocal();
		ResultSet rs = s.executeQuery("    SELECT  codigo_sii, nom_sii,count(1) as cant FROM public.t_comercios where codigo_sii is not null group by  codigo_sii,nom_sii  having count(1) > 100 order by cant  desc ");
		while(rs.next()){
			int codigo = rs.getInt("codigo_sii");
			String nombre = rs.getString("nom_sii").replace(';', '-').replaceAll("\n", " ");
			System.out.println(nombre);
			Rubro r = new Rubro(codigo, nombre);
			rubros.add(r);
			buffer.append(r.rubro).append("@");
		}
		rs.close();
		System.out.println(rubros.size());
		 
		PrintWriter writer = new PrintWriter(new File("/Users/chunhaulai/Documents/workspace/lai-datamining-app/src/resources/arriendo_dpto_categoria.csv"));
    	writer.println("propiedad;precio;"+buffer.toString().replaceAll(";", ",").replaceAll("@", ";"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/chunhaulai/Documents/workspace/lai-datamining-app/src/resources/arriendo_dpto.csv"))));
    	int i = 0;
    	String aux = reader.readLine();
    	while((aux=reader.readLine())!=null){
    		buffer = new StringBuffer();
    		buffer.append(i++).append(";");
    		
    		String arrays[] = aux.replaceAll("\"", "").split(";");
    		Double lat = Double.parseDouble(arrays[8]);
    		Double lng = Double.parseDouble(arrays[9]);
    		int precio = Integer.parseInt(arrays[3].replace(".", "@").replaceAll("@", ""));
    		buffer.append(precio).append(";");
    		
    		StringBuffer sqlUnion = new StringBuffer();
    		int count = 0;
    		for(Rubro r: rubros){
    			String sql = "select %s as rubro, case   when distancia between 0 and 100 then '0-100'  when  distancia between 101 and 200 then '101-200'  when  distancia between 201 and 300 then '201-300'  when  distancia between 301 and 400 then '301-400'  else '>400' end as distancia from  (  SELECT   st_distance(st_transform(the_geom,32718), st_transform(st_geomfromtext('POINT (%s %s)',4326),32718))   AS distancia   FROM public.t_comercios WHERE CODIGO_SII = %s ORDER BY distancia limit 1) as p ";
    			sqlUnion.append(String.format(sql,String.valueOf(count), arrays[9],arrays[8],String.valueOf(r.codigo)));
    			if(count+1 != rubros.size())
    				sqlUnion.append(" union ");
    			else sqlUnion.append(" order by rubro");
    			count++;
    		}
    		rs = s.executeQuery(sqlUnion.toString());
			while(rs.next()){
				String distancia = rs.getString("distancia");
				buffer.append(distancia).append(";");
	    		
			}
			writer.println(buffer.toString()); 
    		writer.flush();
			
    	}
    	writer.close();
	}

}

class Rubro{
	int codigo;
	String rubro;
	public Rubro(int codigo, String rubro) {
		super();
		this.codigo = codigo;
		this.rubro = rubro;
	}
	
}
