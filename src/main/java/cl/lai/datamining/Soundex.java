package cl.lai.datamining;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Soundex {
	
	public static void main(String[] args)throws Exception{
	    System.out.println(soundex("LOS")+":"+soundex("LEONES")+"::"+soundex("PROVIDENCIA"));
	    System.out.println(soundex("LAS")+":"+soundex("LEINES")+"::"+soundex("PROVIDENCIA"));
		   
	    //System.exit(1);
	   
	    
	    //System.out.println(soundex("Example"));
	    //System.out.println(soundex("Sownteks"));
	    //System.out.println(soundex("Ekzampul"));
		//System.out.println(soundex("jimenez")+":"+soundex("cimenes"));
		 String[] results = codeSentence("LOS LEONES" );
	        for ( String s : results )
	            System.out.println(s);
	       results = codeSentence("LAS LEOAAAAAAINES" );
	        for ( String s : results )
	            System.out.println( s );
	        
	  }
	 
	private static String getCode(char c){
	  switch(c){
	    case 'B': case 'F': case 'P': case 'V':
	      return "1";
	    case 'C': case 'G': case 'K':
	    	return "7";
	    case 'J': case 'H': 
	    	return "8";
	    case 'Q': case 'S': case 'X': case 'Z':
	      return "2";
	    case 'D': case 'T':
	      return "3";
	   // case 'L':
	     // return "4";
	    case 'M': case 'N':
	      return "5";
	    case 'L': case 'R':
	      return "6";
	    default:
	      return "";
	  }
	}
	 
	public static String soundex(String s){
	  String code, previous, soundex;
	  code = s.toUpperCase().charAt(0) + "";
	  code = getCode(code.charAt(0));
	  previous = "9";
	  for(int i = 1;i < s.length();i++){
	    String current = getCode(s.toUpperCase().charAt(i));
	    if(current.length() > 0 && !current.equals(previous)){
	      code = code + current;
	    }
	    previous = current;
	  }
	  soundex = (code + "0000").substring(0, 4);
	  return soundex;
	}
	
	public static String soundex(Statement s, String input)throws Exception{
		String x = input.replaceAll("'", "''");
		ResultSet rs = s.executeQuery("select * from soundexesp('"+x+"')");
		if(rs.next())return rs.getString(1);
		else return soundex(x);
	}
	
	

	  // regexs used to match the start of words
	  static final String[] startCodes = {
	    "\\Aw?[uh]?([aeiou])"
	  };
	 
	  // regexs used to match the rest of the word
	  static final String[][] loopCodes = {
	    { "\\A(c[eiéí]|z|ll|sh|ch|sch|cc|y[aeiouáéíóú]|ps|bs|x|j|g[eiéí])", "s" },
	    { "\\A([aeiouhwáéíóúü]+)", "" },
	    { "\\A(y)", "" },
	    { "\\A(ñ|gn)", "n" },
	    { "\\A([dpc]t)", "t" },
	    { "\\A(c[aouáóú]|ck|q)", "k" },
	    { "\\A(v)", "b" }
	  };
	 
	  static final Pattern[] starters, loopers;
	 
	  // compile the regexs upon loading the class
	  static {
	    starters = new Pattern[ startCodes.length ];
	    for ( int i = 0; i < starters.length; i++ )
	      starters[ i ] = Pattern.compile( startCodes[i] );
	    loopers = new Pattern[ loopCodes.length ];
	    for ( int i = 0; i < loopers.length; i++ )
	      loopers[ i ] = Pattern.compile( loopCodes[i][0] );
	  }
	 
	  // codes a single word
	  public static String codeWord( String v ) {
	    final int vl = v.length();
	    v = v.toLowerCase();
	    StringBuilder sb = new StringBuilder( vl );
	 
	    int i = 0;
	 
	    for ( int j = 0; j < starters.length; j++ ) {
	      Matcher m = starters[ j ].matcher( v );
	      if ( m.lookingAt() ) {
	        i += m.end(1)-1;
	        sb.append( m.group(1) );
	        break;
	      }
	    }
	 
	    while ( i < vl ) {
	      String s = v.substring( i, vl );
	      boolean found = false;
	      for ( int j = 0; j < loopers.length; j++ ) {
	        Matcher m = loopers[ j ].matcher( s );
	        if ( m.lookingAt() ) {
	          found = true;
	          i += m.end(1)-1;
	          sb.append( loopCodes[j][1] );
	          break;
	        }
	      }
	      if ( ! found ) {
	        char c = v.charAt( i );
	        sb.append( c );
	        int j = i + 1;
	        while ( j < vl ) {
	          if ( c != v.charAt( j ) )
	            break;
	          j++;
	        }
	        i = j - 1;
	      }
	      i++;
	    }
	    if ( v.charAt( vl-1 ) == 'd' ) sb.setCharAt( sb.length()-1, 't' );
	    return sb.toString();
	  }
	 
	  // codes a complete sentence discarding punctuation
	  // and common pronouns found in names
	  // (i.e. 'Los' in 'Los Angeles')
	  public static String[] codeSentence( String v ) {
	    v = v.replaceAll( "\\p{Punct}+\\s*|\\s+", " " ).toLowerCase().trim();
	    String[] w = v.split( "\\s+(?:.\\s+)?(?:(?:de(?:\\s+los|\\s+la|\\s+las)?|del)\\s+)?" );
	    for ( int i = 0; i < w.length; i++ ) {
	      w[i] = codeWord( w[i] );
	    }
	    return w;
	  }
	 
	  
	  
}
