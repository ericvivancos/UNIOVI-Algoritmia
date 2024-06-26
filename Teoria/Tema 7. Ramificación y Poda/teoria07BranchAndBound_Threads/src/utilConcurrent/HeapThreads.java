package utilConcurrent;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

import util.Node;

public class HeapThreads {
	private PriorityBlockingQueue<Node> nodes; //nodes on the Heap
	private ConcurrentHashMap<Integer, Node> usedNodes; //to compose the solution and to know what nodes have been treated
	
	public HeapThreads() {
		nodes = new PriorityBlockingQueue<Node>();
        usedNodes = new ConcurrentHashMap<Integer, Node>();
	}
	
	public void createEmpty() {
		nodes.clear();
	}
	
	public void insert(Node node) {
        if (!nodeRepeated(node)) { //to not repeat used nodes and avoid infinite loops (e.g., in the puzzle problem)
            nodes.add(node);
        }
	}

    private boolean nodeRepeated(Node node) {
        for (Node n : usedNodes.values()) {
        	if (node.equals(n))
        		return true;
        }
        return false;
    }
	
	public boolean empty() {
		return nodes.isEmpty();
	}
	
	public int estimateBest() {
		return nodes.peek().getHeuristicValue();
	}
	
	public Node extractBestNode() {
		Node node = nodes.poll();
        usedNodes.put(node.hashCode(), node); //we save it because it can be part of the solution. In addition, we can check if we have explored a node previously
		return node;
	}
	
	public ArrayList<Node> extractUsedNodesFrom(Node node) {
		ArrayList<Node> result = new ArrayList<Node>();
		
        result.add(node); //add the last node
        int parentID = node.getParentID(); //find its parent node

        while (parentID != -1) { //while there is a parent node
        	node = usedNodes.get(parentID);
            result.add(node);
            parentID = node.getParentID();
        }
              
		return result;
	}
}
