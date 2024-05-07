

import java.util.ArrayList;
import topics.branchandbound.util.BranchAndBound;
import topics.branchandbound.util.Heap;
import topics.branchandbound.util.Node;

public class StringInterleavingBranchAndBound extends BranchAndBound {
	private int nSol;
	
	public static void main(String[] args) {
		String a = "HELLO";  
	    String b = "EVERYBODY"; 
				
	    StringInterleavingBranchAndBound o = new StringInterleavingBranchAndBound(a, b);
	    o.branchAndBound(o.getRootNode());
	    System.out.println("Number of solutions: " + o.getNumberSolutions());
	}
	
	@Override
	public void branchAndBound(Node rootNode) { 
		ds.insert(rootNode); //First node to be explored
	
		pruneLimit = rootNode.initialValuePruneLimit(); // limite de poda!

		while (!ds.empty() && ds.estimateBest() < pruneLimit) { //CHANGE
			Node node = ds.extractBestNode();	
			
			ArrayList<Node> children = node.expand(); 
			
			for (Node child : children)
				if (child.isSolution()) {
					int cost = child.getHeuristicValue();
					if (cost < pruneLimit) { 
						//pruneLimit = cost; //CHANGE
						nSol++; //CHANGE
						bestNode = child;
					    //printSolutionTrace(); //CHANGE
					} 
				}
				else
					if (child.getHeuristicValue() < pruneLimit) { 
						ds.insert(child); // para su posterior exploración!
					}
		} //while
	}
	
	public int getNumberSolutions() {
		return nSol;
	}
	
    public StringInterleavingBranchAndBound(String a, String b) {
    	this.ds = new HeapRepeatedNodes();
    	this.rootNode = new Shuffle(a, b); 
    }
}
/***************************************************/


class HeapRepeatedNodes extends Heap {
	@Override
	public void insert(Node node) {
        //if (!nodeRepeated(node)) { //To not repeat used nodes and avoid infinite loops (e.g., in the puzzle problem)
            nodes.add(node);
        //}
	}
}


/***************************************************/
class Shuffle extends Node {
	private String a;
	private String b;
	private StringBuilder c;
	int posA;
	int posB;

    public Shuffle(String a, String b) { 
    	this.a = a;
    	this.b = b;
    	this.c = new StringBuilder();
    	this.posA = 0;
    	this.posB = 0;
    }

    public Shuffle(Shuffle parent, String c, int posA, int posB) {
    	super();
    	
        this.a = parent.a;
        this.b = parent.b;
        this.c = new StringBuilder(c);
        this.posA = posA;
        this.posB = posB;
        this.depth = parent.depth + 1;
        this.parentID = parent.getID();
        calculateHeuristicValue();
    } 
   
    @Override
    public String toString() {
        return c.toString();
    }

    @Override
    public void calculateHeuristicValue() {
		int length = a.length() + b.length();
		heuristicValue = length - c.length();
    }
    
    /* To get the children of the current node. They 
     * point to their parent through the parentID */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
	    Shuffle temp;
	       
    	if (posA < a.length()) {
    		StringBuilder d = new StringBuilder(c);
	    	d.append(a.charAt(posA));
		    temp = new Shuffle(this, d.toString(), posA+1, posB);
		    result.add(temp);
    	}
    	if (posB < b.length()) {
    		StringBuilder d = new StringBuilder(c);
	    	d.append(b.charAt(posB));
		    temp = new Shuffle(this, d.toString(), posA, posB+1);
		    result.add(temp);
    	}
	    return result;
	}

	@Override
	public boolean isSolution() {
		return (getHeuristicValue() == 0) ? true : false;
	}

} 
/***************************************************/

