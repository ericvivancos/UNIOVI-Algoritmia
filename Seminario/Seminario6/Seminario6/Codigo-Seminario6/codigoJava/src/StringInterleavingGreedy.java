

public class StringInterleavingGreedy {
	private String a;
	private String b;
	private String c;

	public static void main(String[] args) {
		String a = "HELLO";  
	    String b = "EVERYBODY"; 
	    String c = "HELLOEVERYBODY"; //case a
//	    String c = "HEEVERYBLLOODY"; //case b
//	    String c = "HEVEERYLBLOODY"; //case c
	    
	    StringInterleavingGreedy o = new StringInterleavingGreedy(a, b, c);
	    
	    if (o.isInterleaved())  
	    	System.out.printf("%s is interleaved of %s and %s", c, a, b);  
	    else
	    	System.out.printf("%s is not interleaved of %s and %s", c, a, b); 
	}
	
	public StringInterleavingGreedy(String a, String b, String c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * To check if c is an interleaving of a and b
	 * @return Returns true if c is an interleaving of a and b, otherwise returns false  
	 */
	public boolean isInterleaved() { 
	    int posA = 0; //index for a
	    int posB = 0; //index for b
	        
	    for (int posC = 0; posC < c.length(); posC++) { //iterate through all characters of c
	        if (getChar(a, posA) == getChar(c, posC)) //match first character of c with first character of a. If matches them move a to next
	        	posA++;  
	  
	        else if (getChar(b, posB) == getChar(c, posC)) //match first character of c with first character of b. If matches them move b to next
	        	posB++;  
	  
	        else return false; //if doesn't match with either a or b, then return false     
	    }  

	    //if a or b still have some characters, then length of c is smaller than sum of lengths of a and b, so return false   
	    if (posA < a.length() || posB < b.length()) 
	        return false;  
	  
	    return true;  
	}
	
	private char getChar(String string, int position) {
		try {
			return string.charAt(position);
		}
		catch (Exception ex) {
			return '*';
		}
	}

}
