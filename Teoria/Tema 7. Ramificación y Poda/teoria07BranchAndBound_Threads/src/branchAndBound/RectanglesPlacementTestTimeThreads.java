//BRANCH AND BOUND PROBLEM: OPTIMAL PLACEMENT OF RECTANGLES
package branchAndBound;

import utilConcurrent.BranchAndBoundThreads;

class RectanglesPlacementTestTimeThreads extends BranchAndBoundThreads {    
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); //size of the board
		
		long t1 = System.currentTimeMillis();
		RectanglesPlacementTestTimeThreads problem = new RectanglesPlacementTestTimeThreads(n); 
		problem.branchAndBound(problem.getRootNode(), 5); 
		long t2 = System.currentTimeMillis();
		
		System.out.println("The execution WITH threads took " + (t2-t1) + " milliseconds");			
		problem.printSolutionTrace(); //there is always a solution for this problem
	}
	
	public RectanglesPlacementTestTimeThreads(int n) {
	    rootNode = new Game(n); //we create the board to start playing
	}
    
}
/***************************************************/