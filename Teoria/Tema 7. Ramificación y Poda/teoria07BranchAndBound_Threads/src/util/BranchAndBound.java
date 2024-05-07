package util;

import java.util.ArrayList;

public class BranchAndBound {
	protected Heap ds; //nodes to be explored (not used nodes)
	protected Node bestNode; //to save the final node of the best solution
	protected Node rootNode; //initial node
	protected int pruneLimit; 
	       
	public BranchAndBound() {
		ds = new Heap(); //we create an instance of the Heap class to save the nodes
	}
	      
	public void branchAndBound(Node rootNode) { 
		ds.insert(rootNode); //first node to be explored
	
		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();	
			
			ArrayList<Node> children = node.expand(); 
			for (Node child : children)
				if (child.isSolution()) {
					int cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					} 
				}
				else
					if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
					}
		} //while
	}
		
    public Node getRootNode() {
    	return rootNode;
    }

    public void printSolutionTrace() {
    	if (bestNode == null) {
			System.out.println("Original:");
			System.out.println(rootNode);
    		System.out.println("THERE IS NO SOLUTION");
    	} 
    	else {
            ArrayList<Node> result = ds.extractUsedNodesFrom(bestNode); //extract the path of the used nodes from bestNode to the rootNode

            for (int i = 1; i <= result.size();  i++) {
    			if (i == 1) 
    				System.out.println("Original:");
    			else 
    				System.out.println("Step " + (i-1)+ ":");
				System.out.println(result.get(result.size()-i));
    	    }
            System.out.println("\nSolution with " + bestNode.getDepth() + " step(s).");	
    	}
    }
}
