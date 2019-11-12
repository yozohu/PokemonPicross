package code;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.LinkedList;

import javax.swing.*;

public class BoardGUI implements ActionListener{
	
	private static final int MAX_GAME_SIZE = ResourceManager.MAX_GAME_SIZE;
	private int rows;
	private int cols;
	private BoardModel pboard;
	JFrame boardFrame;
	PicrossButton[][] iconBoard;

	public BoardGUI(BoardModel pboard){
		this.pboard = pboard;
		boardFrame = new JFrame("board frame");
		iconBoard = new PicrossButton[pboard.rows][pboard.cols];
		rows = pboard.rows;
		cols = pboard.cols;
		drawBoard();
	}

	public void drawBoard(){
		LinkedList<Integer>[] ral = getRowAdjacencyList(pboard);
		LinkedList<Integer>[] cal = getColAdjacencyList(pboard);
		
		//DRAW BOARD
		boardFrame.setLayout(new GridBagLayout() );
		JPanel boardView = new JPanel( new GridBagLayout() ); //bottom right
		JPanel ralView = new JPanel( new GridBagLayout() ); //bottom left
		JPanel calView = new JPanel( new GridBagLayout() );; //top right
		
		int numIx;
		GridBagConstraints cr = new GridBagConstraints();
		cr.insets = new Insets(1,1,1,1);
		for(int i = 0; i < rows; i ++) {
			
			numIx = 0;
			while(!ral[i].isEmpty()) {
				cr.gridx = MAX_GAME_SIZE-numIx++;
				cr.gridy = i;
				ralView.add(new ImageView(getNumberIcon(ral[i].removeLast())), cr);
			}
		}
		
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(1,1,1,1);
		for(int i = 0; i < cols; i ++) {
			
			numIx = 0;
			while(!cal[i].isEmpty()) {
				cc.gridx = i;
				cc.gridy = MAX_GAME_SIZE-numIx++;
				calView.add(new ImageView(getNumberIcon(cal[i].removeLast())), cc);
			}
		}
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new  Insets(1,1,1,1); //spacing of squares 
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j<cols; j++) {
				c.gridx = j;
				c.gridy = i;
				iconBoard[i][j] = new PicrossButton(i,j);
				iconBoard[i][j].addActionListener(this);
				boardView.add(iconBoard[i][j], c);
			}
		}
		
		GridBagConstraints bf = new GridBagConstraints();
		bf.gridy = 1; //bottom row
		bf.gridx = 0;
		boardFrame.add(ralView, bf);
		
		bf.gridx = 1;
		boardFrame.add(boardView, bf);
		
		bf.gridy = 0;
		boardFrame.add(calView, bf);
		boardFrame.setSize(400, 400);
		boardFrame.setVisible(true);
	}
	
	/**
	 * 
	 * @param board
	 * @return array of llists repping the adjacency count of each row of the solution
	 */
	private LinkedList[] getRowAdjacencyList(BoardModel board) {
		LinkedList<Integer>[] ral = new LinkedList[board.rows];
		
		for(int r = 0; r < ral.length; r++) {
			ral[r] = new LinkedList<Integer>();
			int adjCount;
			
			for(int c = 0; c< board.cols; c++) {
				if(board.inSolution(r, c)) {
					adjCount = 0;
					while(c< board.cols && board.inSolution(r, c)) {
						adjCount++;
						c++;
					}
					ral[r].add(adjCount);
				}
			}
			if (ral[r].isEmpty()) {
				ral[r].add(0);
			}
		}
		return ral;
	}
	
	//SIMILAR 2 ABOVE
	private LinkedList<Integer>[] getColAdjacencyList(BoardModel board) {
		LinkedList<Integer>[] cal = new LinkedList[board.cols];
		
		for(int c = 0; c < cal.length; c++) {
			cal[c] = new LinkedList<Integer>();
			int adjCount;
			
			for(int r = 0; r < board.rows; r++) {
				if(board.inSolution(r, c)) {
					adjCount = 0;
					while( r < board.rows && board.inSolution(r, c)) {
						adjCount++;
						r++;
					}
					cal[c].add(adjCount);
				}
			}
			if (cal[c].isEmpty()) {
				cal[c].add(0);
			}
		}

		return cal;
	}
	
	//resource manager helper method
	private ImageIcon getNumberIcon(int n) {
		return ResourceManager.instance().getNumberIcon(n);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		PicrossButton b = (PicrossButton)a.getSource();
		
		//change to switch case?
		
		if(b.state() == SquareState.BLANK) { //about to fill this position
			pboard.fill(b.row(), b.col());
		}
		
		if(b.state() == SquareState.FILLED) { //abt 2 mark this square
			pboard.mark(b.row(), b.col());
		}
		
		if(b.state() == SquareState.FILLED) { //abt 2 clear this square
			pboard.clear(b.row(), b.col());
		}
		
	}

	public void showSolution() {
		// TODO Auto-generated method stub
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j<cols; j++) {
				if(pboard.inSolution(i, j)) {
					iconBoard[i][j].setState(SquareState.FILLED);
					pboard.mark(i,j);
				}else {
					iconBoard[i][j].setState(SquareState.BLANK);
				}
				iconBoard[i][j].redraw();
			}
		}
		pboard.printStatus();
	}
}
