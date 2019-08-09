package code;
import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

public class BoardGUI {
	
	BoardModel pboard;
	JFrame boardFrame;
	PicrossButton[][] iconBoard;

	public BoardGUI(BoardModel pboard){
		this.pboard = pboard;
		boardFrame = new JFrame("board frame");
		iconBoard = new PicrossButton[pboard.rows][pboard.cols];
		drawBoard();
	}


	public void drawBoard(){
		LinkedList<Integer>[] rowAdjacencyList = getRowAdjacencyList(pboard);
		LinkedList<Integer>[] colAdjacencyList = getColAdjacencyList(pboard);
		
		//DRAW BOARD
		
		boardFrame.setVisible(true);
	}
	
	
	
	/**
	 * 
	 * @param board
	 * @return array of llists repping the adjacency count of each row of the solution
	 */
	private LinkedList[] getRowAdjacencyList(BoardModel board) {
		return null;
	}
	
	//SIMILAR 2 ABOVE
	private LinkedList<Integer>[] getColAdjacencyList(BoardModel pboard2) {
		return null;
	}
	
	//resource manager helper method
	private ImageIcon getNumberIcon(int n) {
		return ResourceManager.instance().getNumberIcon(n);
	}
}
