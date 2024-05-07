package utilConcurrent;

import java.util.ArrayList;
import java.util.List;

import util.Node;

public class BranchAndBoundThreads {
	protected static HeapThreads ds; //nodes to be explored (not used nodes)
	protected static Node bestNode; //to save the final node of the best solution
	protected static Node rootNode; //initial node
	protected static int pruneLimit; 
	       
	public BranchAndBoundThreads() {
		ds = new HeapThreads(); //we create an instance of the Heap class to save the nodes
	}
	    
	public void branchAndBound(Node rootNode, int numberOfThreads) { 	
		ds.insert(rootNode); //first node to be explored
		pruneLimit = rootNode.initialValuePruneLimit();
		
		List<WorkerThread> threads = new ArrayList<WorkerThread>();

		for (int i = 0; i < numberOfThreads; i++) {
			WorkerThread w = new WorkerThread();
			threads.add(w);
		}
		
		for (WorkerThread w : threads) {
			w.start();
		}
		
		for (WorkerThread w : threads) {
			try {
				w.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
