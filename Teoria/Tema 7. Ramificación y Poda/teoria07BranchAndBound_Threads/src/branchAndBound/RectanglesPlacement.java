//BRANCH AND BOUND PROBLEM: OPTIMAL PLACEMENT OF RECTANGLES
package branchAndBound;

import java.util.ArrayList;

import util.BranchAndBound;
import util.Node;

class RectanglesPlacement extends BranchAndBound {    
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); //size of the board
		
		RectanglesPlacement problem = new RectanglesPlacement(n); 
		problem.branchAndBound(problem.getRootNode()); 
		problem.printSolutionTrace(); //there is always a solution for this problem
	}
	
	public RectanglesPlacement(int n) {
	    rootNode = new Game(n); //we create the board to start playing
	}
    
}
/***************************************************/


/***************************************************/
class Game extends Node {
    private int[][] board; //board to place rectangles
    private ArrayList<Piece> pieces; //pieces (rectangles) to be placed on the board

    public Game(int n) { //generates a new board and pieces (to place them in the board in the best way possible) (ROOT NODE)
        board = new int[n][n]; //size of the board n x n
        
        //EXAMPLE 1
        pieces = new ArrayList<Piece>();
        /*Piece p1 = new Piece(1, 2);
        Piece p2 = new Piece(2, 2);
        Piece p3 = new Piece(1, 3);
        pieces.add(p1); pieces.add(p2); pieces.add(p3);
        */
        //EXAMPLE 2
        //Piece p1 = new Piece(1, 3);
        //Piece p2 = new Piece(1, 1);
        //pieces.add(p1); pieces.add(p2);
        
        //EXAMPLE 3
        Piece p1 = new Piece(2, 5);
        Piece p2 = new Piece(1, 3);
        Piece p3 = new Piece(1, 5);
        Piece p4 = new Piece(3, 1);
        Piece p5 = new Piece(1, 1);
        Piece p6 = new Piece(2, 1);
        pieces.add(p1); pieces.add(p2); pieces.add(p3); pieces.add(p4); pieces.add(p5); pieces.add(p6);
    }

    public Game(int[][] board, ArrayList<Piece> pieces, int depth, int parentID) {
        this.board = board;
        this.pieces = pieces;
        this.depth = depth;
        this.parentID = parentID;
        calculateHeuristicValue();
    }

    private ArrayList<Object> placeNewPiece() {
        ArrayList<Object> boards = new ArrayList<Object>();
        
        for (int i=0; i<board.length; i++) {
        	for (int j=0; j<board.length; j++) {
                int[][] newBoard = tryPositionNewPiece(i, j, PieceOrientation.Horizontal);
                if (newBoard != null) boards.add(newBoard);

                newBoard = tryPositionNewPiece(i, j, PieceOrientation.Vertical);
                if (newBoard != null) boards.add(newBoard);
        	}
        }        
        return boards;
    }
    
    private int[][] tryPositionNewPiece(int x, int y, PieceOrientation orientation) {
    	int[][] newBoard = new int[board.length][board.length];
    	for (int i = 0; i < board.length; i++)
    		for (int j = 0; j < board.length; j++)
    			newBoard[i][j] = board[i][j]; //copy the board in a new board for new nodes
    	
    	if (insertNewPiece(x, y, orientation, newBoard, pieces.get(getDepth())))
    			return newBoard;
	    else return null; //if we return null, then it is not a valid node
    }
    
    private boolean insertNewPiece(int x, int y, PieceOrientation orientation, int[][] newBoard, Piece piece) {
    	boolean result = false;
    	
    	int limitX = 0;
        int limitY = 0;
    	if (orientation == PieceOrientation.Horizontal) { //the default orientation
    		limitX = piece.x;
    		limitY = piece.y;
    	}
        else { //if the orientation is vertical we need to interchange the coordinates of the piece
        	limitX = piece.y;
        	limitY = piece.x;
        }
    	
    	//check the size of the board
    	if ((x+limitX > newBoard.length)||(y+limitY > newBoard.length)) return false;
    	
    	//check if the piece is on another piece
    	for (int i=x; i<x+limitX; i++) {
        	for (int j=y; j<y+limitY; j++) {
        		if (newBoard[i][j] != 0) return false;
        	}
        }	

    	//we need that the piece is next to another piece (but not on another piece)
    	if (depth == 0) result = true; //the first element does not need a neighbor
    	else { //if it is not the first element
	    	if (x+limitX < newBoard.length) //on the right
	    		for (int k = y; k < y+limitY; k++)
	    			if (newBoard[x+limitX][k] != 0) result = true; //we have a neighbor
	    	if (x != 0) //on the left
	    		for (int k = y; k < y+limitY; k++)
	    			if (newBoard[x-1][k] != 0) result = true; //we have a neighbor
	    	if (y != 0) //on the top
	    		for (int k = x; k < x+limitX; k++)
	    			if (newBoard[k][y-1] != 0) result = true; //we have a neighbor
	    	if (y+limitY < newBoard.length) //on the button
	    		for (int k = x; k < x+limitX; k++)
	    			if (newBoard[k][y+limitY] != 0) result = true; //we have a neighbor
    	}
    	
    	//we insert the piece on the board
    	for (int i=x; i<x+limitX; i++) {
        	for (int j=y; j<y+limitY; j++) {
                newBoard[i][j] = getDepth()+1;
        	}
        }
    	
    	return result;
    }

    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer("=============\n");
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++)
            	sb.append(board[i][j]);
            sb.append("\n");
        }
        sb.append("=============\n");
        return sb.toString();
    }
  
	@Override
	public void calculateHeuristicValue() {
    	int firstValueX = -1;
    	int firstValueY = -1;
    	int lastValueX = -1;
    	int lastValueY = -1;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
            	if ((firstValueY == -1)&&(board[i][j] != 0)) {
            		firstValueY = i;
            	}
            	if (board[i][j] != 0) {
            		lastValueY = i;
            	}
            }	
        }
        
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
            	if ((firstValueX == -1)&&(board[j][i] != 0)) {
            		firstValueX = i;
            	}
            	if (board[j][i] != 0) {
            		lastValueX = i;
            	}
            }	
        }
        heuristicValue = (Math.abs(lastValueX-firstValueX)+1)*(Math.abs(lastValueY-firstValueY)+1); //area			
	}

    /* To get the children of the current node. They 
     * point to their parent through the parentID */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
        ArrayList<Object> boards = new ArrayList<Object>();
        Game temp;
        int[][] testBoard;
        
        //Possible positions for a new piece placed on the board
        boards = placeNewPiece(); //we could place the new piece in different locations, so for each location we have a new state
        for (Object board : boards) {
        	testBoard = (int[][])board;
            temp = new Game(testBoard, pieces, depth+1, hashCode()); //parentID = hashCode() of the previous node
            result.add(temp);
        }
        return result;
	}
	
	@Override
    public boolean isSolution() {
    	return (depth == pieces.size()) ? true : false; //we have a solution only when all the pieces are placed
    }

} //Game
/***************************************************/


/***************************************************/
class Piece {
	int x;
	int y;
	
	public Piece(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
/***************************************************/


/***************************************************/
enum PieceOrientation {
	Horizontal,
	Vertical
}
/***************************************************/