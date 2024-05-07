

import java.util.Random;

public class StringInterleavingTest {
	public static void main(String[] args) {
	    int n = 12;
	    String a = generateRandomWord(n);
	    String b = generateRandomWord(n);
	    System.out.printf("Size of the words: %d\n", n);
	    System.out.printf("\ta: %s\n", a);
	    System.out.printf("\tb: %s\n", b);

	    StringInterleavingBacktracking backtracking = new StringInterleavingBacktracking(a, b);
	    long t1 = System.currentTimeMillis();
	    backtracking.isInterleaved(0, 0);
	    long t2 = System.currentTimeMillis();
	    System.out.printf("Time to generate all the possible ways to interleave a and b with backtracking: %d milliseconds\n", t2 - t1);
	    
	    StringInterleavingBranchAndBound branchAndBound = new StringInterleavingBranchAndBound(a, b);
	    t1 = System.currentTimeMillis();
	    branchAndBound.branchAndBound(branchAndBound.getRootNode());
	    t2 = System.currentTimeMillis();
	    System.out.printf("Time to generate all the possible ways to interleave a and b with branch and bound: %d milliseconds\n", t2 - t1);
	    
	    System.out.printf("Number of shuffles: %d\n", backtracking.getSolutions().size());
	    	    
	    t1 = System.currentTimeMillis();
	    for (String sol : backtracking.getSolutions()) {
		    StringInterleavingDivideAndConquer divideAndConquer = new StringInterleavingDivideAndConquer(a, b, sol);
		    
		    if (!divideAndConquer.isInterleaved(0, 0, 0))  
		    	System.out.printf("%s is not interleaved of %s and %s\n", sol, a, b); 
		    //else
		    //	System.out.printf("%s is interleaved of %s and %s\n", sol, a, b);  
	    }
	    t2 = System.currentTimeMillis();
	    System.out.printf("Time to check if all combinations are correct with divide and conquer: %d milliseconds\n", t2 - t1); 
	    
	    t1 = System.currentTimeMillis();
	    for (String sol : backtracking.getSolutions()) {
		    StringInterleavingDynamicProgramming dynamicProgramming = new StringInterleavingDynamicProgramming(a, b, sol);
		    
		    if (dynamicProgramming.isInterleaved(0, 0, 0) == 'F')  
		    	System.out.printf("%s is not interleaved of %s and %s\n", sol, a, b); 
		    //else
		    //	System.out.printf("%s is interleaved of %s and %s\n", sol, a, b);  
	    }
	    t2 = System.currentTimeMillis();
	    System.out.printf("Time to check if all combinations are correct with dynamic programming: %d milliseconds\n", t2 - t1); 
	}
	
	public static String generateRandomWord(int n) {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    Random r = new Random();
	    StringBuilder buffer = new StringBuilder(n);
	    for (int i = 0; i < n; i++) {
	        int randomLimitedInt = r.nextInt(rightLimit - leftLimit) + leftLimit;
	        buffer.append((char)randomLimitedInt);
	    }
		return buffer.toString();
	}
}
