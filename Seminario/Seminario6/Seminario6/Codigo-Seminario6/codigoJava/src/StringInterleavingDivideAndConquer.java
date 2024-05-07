

public class StringInterleavingDivideAndConquer {
	private String a;
	private String b;
	private String c;

	public static void main(String[] args) {
		String a = "HELLO";  
		String b = "EVERYBODY"; 
		String c = "HELLOEVERYBODY"; //case a
		//String c = "HEEVERYBLLOODY"; //case b
		//String c = "HEVEERYLBLOODY"; //case c
		//String c = "EVERYHELBODYLO"; //case d	

		StringInterleavingDivideAndConquer o = new StringInterleavingDivideAndConquer(a, b, c);

		if (o.isInterleaved(0, 0, 0))  
			System.out.printf("%s is interleaved of %s and %s", c, a, b);  
		else
			System.out.printf("%s is not interleaved of %s and %s", c, a, b); 
	}

	public StringInterleavingDivideAndConquer(String a, String b, String c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * To check if c is an interleaving of a and b
	 * @param posA Index in the first string
	 * @param posB Index in the second string
	 * @param posC Index in the potential shuffle/interleaving of a and b
	 * @return Returns true if c is an interleaving of a and b, otherwise returns false  
	 */
	public boolean isInterleaved(int posA, int posB, int posC) { 
		//base case: if all strings are empty 
		if ((a.length() == posA) && (b.length() == posB) && (c.length() == posC)) 
			return true; 

		//if c is empty and any of the two strings is not empty 
		if (c.length() == posC)
			return false; 

		//if any of the above mentioned two possibilities is true, then return true, otherwise false 
		return ((getChar(a, posA) == getChar(c, posC)) && (isInterleaved(posA+1, posB, posC+1))) 
				|| ((getChar(b, posB) == getChar(c, posC)) && (isInterleaved(posA, posB+1, posC+1))); 
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
