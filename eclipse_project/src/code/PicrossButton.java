package code;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PicrossButton extends JButton {
	private int row;
	private int col;
	private SquareState state;

	public PicrossButton(int r, int c){
		super();
		row = r;
		col = c;
		state = SquareState.BLANK;
		setBorder(null);
		setIcon(state);
		
		addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				/*
				 * HERE GOES CODE RUN WHEN THIS BUTTON IS CLICKED
				 * BUTTON STATE SHOULD CHANGE
				 * THIS IS THE STATE TRANSITION THAT SHOULD HAPPEN
				 *  blank --> filled --> marked --> blank
				 */
				
				setIcon(state);
			}
		});

	}

	//getters
	public int row(){return row;}
	public int col(){return col;}
	
	//resource manager helper methods
	private void setIcon(SquareState s) {
		setIcon(ResourceManager.instance().getSquareIcon(state));
	}

}
