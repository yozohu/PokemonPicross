package code;

public class BoardModel {

	private boolean[][] solution; //2D array repping which squares are filled (true) in the final solution
	int numSquaresLeft; //number of uncovered squares left in solution
	int rows;
	int cols;

	/**
	 * 
	 * 
	 * @param width
	 * @param height
	 * @param solution If the square at row i, column j is in the solution, solution[i][j] is true.
	 */
	public BoardModel(boolean[][] solution){ 
		//DOUBLE NULL CHECK FOR SOLUTION ARRAY
		this.solution = solution;
		rows = solution.length;
		cols = solution[0].length;
		
		//SET NUMBER OF SQUARES LEFT......SHOULDNT B ZERO
		numSquaresLeft = 0;
	}
	
	public void decSquaresLeft(){
		numSquaresLeft--;
	}

	public void incSquaresLeft(){
		numSquaresLeft++;
	}

	public boolean solutionFound(){
		return (numSquaresLeft == 0);
	}

	public boolean inSolution(int row, int col) {
		return solution[row][col];
	}

}
