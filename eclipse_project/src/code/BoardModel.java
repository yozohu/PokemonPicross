package code;

public class BoardModel {

	private boolean[][] solution; //2D array repping which squares are filled (true) in the final solution
	private SquareState[][] board; // the actual board theyre playing on.. T = they filled the square
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
		board = new SquareState[rows][cols];
		clearBoard();

		squaresRight = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j< cols; j++) {
				if(!solution[i][j]) {squaresRight++;}
			}
		}
	}

	public boolean solutionFound(){
		
		squaresRight = 0;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j <cols; j++) {
				if(solution[i][j] && (board[i][j] == SquareState.FILLED)) {
					squaresRight++;
				}
				if (!solution[i][j] && (board[i][j] != SquareState.FILLED)) {
					squaresRight++;
				}
			}
		}
		
		return (squaresRight == rows*cols);
	}

	public boolean inSolution(int row, int col) {
		return solution[row][col];
	}

	public int numOfCorrectSquares() {
		return squaresRight;
	}
	
	public void fill(int row, int col) {
		if(board[row][col] == SquareState.FILLED) {
			return;
		}
		
		board[row][col] = SquareState.FILLED;
		if (solution[row][col]) {
			squaresRight++;
		}else {
			squaresRight--;
		}
		
	}
	
	public void clear(int row, int col) {
		if(board[row][col] == SquareState.BLANK) {
			return;
		}
		
		if(board[row][col] == SquareState.MARKED) {
			board[row][col] = SquareState.BLANK;
			return;
		}
		
		board[row][col] = SquareState.BLANK;
		if (!solution[row][col]) {
			squaresRight++;
		}else {
			squaresRight--;
		}
		
	}
	
	public void mark(int row, int col) {
		if(board[row][col] == SquareState.MARKED) {
			return;
		}
		
		if(board[row][col] == SquareState.BLANK) {
			board[row][col] = SquareState.MARKED;
			return;
		}
		
		board[row][col] = SquareState.MARKED;
		if (!solution[row][col]) {
			squaresRight++;
		}else {
			squaresRight--;
		}
		
	}
	
	public void clearBoard() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j <cols; j++) {
				board[i][j] = SquareState.BLANK;
			}
		}
	}
	
	public void printStatus() {
		if(solutionFound()) {
			System.out.println("SOLUTION FOUND");
		}else {
			System.out.println("Correct spots:" + squaresRight+"/"+ (rows*cols));
		}
	}
}
