package code;

public class BoardModel {

	private boolean[][] solution; //2D array repping which squares are filled (true) in the final solution
	int squaresRight; //number of squares in the right state
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

		squaresRight = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j< cols; j++) {
				if(!solution[i][j]) {squaresRight++;}
			}
		}
	}
	
	public void decNumOfCorrectSquares(){
		squaresRight--;
	}

	public void incNumOfCorrectSquares(){
		squaresRight++;
	}

	public boolean solutionFound(){
		return (squaresRight == rows*cols);
	}

	public boolean inSolution(int row, int col) {
		return solution[row][col];
	}

	public int numOfCorrectSquares() {
		return squaresRight;
	}

}
