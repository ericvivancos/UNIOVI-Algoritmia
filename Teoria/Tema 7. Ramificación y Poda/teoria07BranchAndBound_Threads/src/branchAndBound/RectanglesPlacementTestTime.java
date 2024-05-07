//BRANCH AND BOUND PROBLEM: OPTIMAL PLACEMENT OF RECTANGLES
package branchAndBound;

import util.BranchAndBound;

class RectanglesPlacementTestTime extends BranchAndBound {    
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); //size of the board
		
		long t1 = System.currentTimeMillis();
		RectanglesPlacementTestTime problem = new RectanglesPlacementTestTime(n); 
		problem.branchAndBound(problem.getRootNode()); 
		long t2 = System.currentTimeMillis();
		
		System.out.println("The execution WITHOUT threads took " + (t2-t1) + " milliseconds");			
		problem.printSolutionTrace(); //there is always a solution for this problem
	}
	
	public RectanglesPlacementTestTime(int n) {
	    rootNode = new Game(n); //we create the board to start playing
	}
    
}
/***************************************************/