package utilConcurrent;

import java.util.ArrayList;

import util.Node;

public class WorkerThread extends Thread {
	          
	@Override
	public void run() { 
		long l1 = System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName() + " - STARTING");
		
		while (!BranchAndBoundThreads.ds.empty() && BranchAndBoundThreads.ds.estimateBest() < BranchAndBoundThreads.pruneLimit) {
			Node node = BranchAndBoundThreads.ds.extractBestNode();	
				
			ArrayList<Node> children = node.expand(); 				

			for (Node child : children) 
				if (child.isSolution()) {
					int cost = child.getHeuristicValue();
					synchronized (BranchAndBoundThreads.class) {						
						if (cost < BranchAndBoundThreads.pruneLimit) {
							BranchAndBoundThreads.pruneLimit = cost;
							BranchAndBoundThreads.bestNode = child;
						} 
					}
				}
				else
					if (child.getHeuristicValue() < BranchAndBoundThreads.pruneLimit) {
						BranchAndBoundThreads.ds.insert(child);
					}
		} //while
		long l2 = System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName() + " - FINISHING AFTER " + (l2-l1) + " milliseconds");
	}
		
}
