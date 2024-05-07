import java.util.ArrayList;
import java.util.List;

public class StringInterleavingBacktracking {
	private String a;
	private String b;
	private StringBuilder c;
	private List<String> solutions;

	public static void main(String[] args) {
		String a = "HELLO";  
		String b = "EVERYBODY"; 

		StringInterleavingBacktracking o = new StringInterleavingBacktracking(a, b);

		o.isInterleaved(0, 0);
		int i = 1;
		for (String sol : o.getSolutions()) {
			System.out.printf("%d: %s\n", i++, sol); 
		}
	}

	public StringInterleavingBacktracking(String a, String b) {
		this.a = a;
		this.b = b;
		c = new StringBuilder();
		solutions = new ArrayList<String>();
	}

	/**
	 * To check if c is an interleaving of a and b
	 * @param posA Index in the first string
	 * @param posB Index in the second string
	 * @return Returns true if c is an interleaving of a and b, otherwise returns false  
	 */
	public void isInterleaved(int posA, int posB) { 
		if ((a.length() == posA) && (b.length() == posB)) { //it is a solution
			solutions.add(c.toString());
		} 
		else {
			if (posA < a.length()) {
				c.append(a.charAt(posA));
				isInterleaved(posA+1, posB);
				c.deleteCharAt(posA + posB); // remove last char added to C
			}
			if (posB < b.length()) {
				c.append(b.charAt(posB));
				isInterleaved(posA, posB+1);
				c.deleteCharAt(posA + posB);
			}
		}
	}

	public List<String> getSolutions() {
		return solutions;
	}

}
