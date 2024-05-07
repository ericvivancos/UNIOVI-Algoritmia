

public class StringInterleavingDynamicProgramming {
	private String a;
	private String b;
	private String c;
	private char[][] table;

	public static void main(String[] args) {
		String a = "HELLO";  
	    String b = "EVERYBODY"; 
	    String c = "HELLOEVERYBODY"; //case a
	    //String c = "HEEVERYBLLOODY"; //case b
	    //String c = "HEVEERYLBLOODY"; //case c
//	    String c = "EVERYHELBODYLO"; //case d
	    
	    StringInterleavingDynamicProgramming o = new StringInterleavingDynamicProgramming(a, b, c);
	    o.isInterleaved(0, 0, 0);
	    o.printTable();
	}
	
	public StringInterleavingDynamicProgramming(String a, String b, String c) {
		this.a = "0" + a;
		this.b = "0" + b;
		this.c = "0" + c;
		this.table = new char[this.a.length()][this.b.length()];
	}

	/**
	 * To check if c is an interleaving of a and b
	 * @param posA Index in the first string
	 * @param posB Index in the second string
	 * @param posC Index in the potential shuffle/interleaving of a and b
	 * @return Returns true if c is an interleaving of a and b, otherwise returns false  
	 */
	public char isInterleaved(int posA, int posB, int posC) { 
		//first cell
		table[0][0] = 'T';
		
		//first column
		for (int i = 1; i < a.length(); i++) {
			if (c.charAt(i) == a.charAt(i)) {
				table[i][0] = table[i-1][0]; // check left
			}
			else table[i][0] = 'F';
		}
		
		//first row
		for (int j = 1; j < b.length(); j++) {
			if (c.charAt(j) == b.charAt(j)) {
				table[0][j] = table[0][j-1]; // check top
			}
			else table[0][j] = 'F';
		}
		
		//the rest of the table
		for (int i = 1; i < a.length(); i++) {
			for (int j = 1; j < b.length(); j++) {
				table[i][j] = 'F';
				if (c.charAt(i+j) == a.charAt(i)) {
					table[i][j] = table[i-1][j]; // check left
				}
				if (c.charAt(i+j) == b.charAt(j)) {
					if (table[i][j] != 'T')
						table[i][j] = table[i][j-1]; // check top
				} 
			}
		}
		
		return table[a.length()-1][b.length()-1];
	}
	
	private void printTable() {
		System.out.printf("Word: %10s\n", c.substring(1)); 
		System.out.printf("%5c", ' '); 
		for (int j = 0; j < b.length(); j++) {
			System.out.printf("%5c", b.charAt(j)); 
		}
		System.out.printf("\n"); 
		for (int i = 0; i < a.length(); i++) {
			System.out.printf("%5c", a.charAt(i)); 
			for (int j = 0; j < b.length(); j++) {
				System.out.printf("%5c", table[i][j]); 
			}
			System.out.printf("\n"); 
			
		}
	}

}
