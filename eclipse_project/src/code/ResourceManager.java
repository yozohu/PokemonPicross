package code;

import javax.swing.ImageIcon;

public class ResourceManager {
	
	private static final int NUM_SQUARE_ICONS = 3;
	static final int MAX_GAME_SIZE = 5;
	
	private static ResourceManager rmInstance = null;
	private ImageIcon[] squareIcons;
	private ImageIcon[] numberIcons;
	
	private ResourceManager() {
		squareIcons = new ImageIcon[NUM_SQUARE_ICONS];
		squareIcons[SquareState.BLANK.ordinal()] = new ImageIcon(getClass().getResource("/res/icons/squares/blank.png"));
		squareIcons[SquareState.FILLED.ordinal()] = new ImageIcon(getClass().getResource("/res/icons/squares/filled.png"));
		squareIcons[SquareState.MARKED.ordinal()] = new ImageIcon(getClass().getResource("/res/icons/squares/marked.png"));
			
		numberIcons = new ImageIcon[MAX_GAME_SIZE+1];
		for(int i = 0; i<=MAX_GAME_SIZE; i++){
			numberIcons[i] = new ImageIcon(getClass().getResource("/res/icons/numbers/"+Integer.toString(i)+".png"));
		}
	}
	
	public static ResourceManager instance() {
		if(rmInstance == null) {
			rmInstance = new ResourceManager();
		}
		
		return rmInstance;
	}
	
	public ImageIcon getSquareIcon(SquareState state) {
		if(state.ordinal() >= NUM_SQUARE_ICONS) {
			return null;
		}
		return squareIcons[state.ordinal()];
	}
	
	public ImageIcon getNumberIcon(int number) {
		if(number>MAX_GAME_SIZE || number<0) {
			return null;
		}
		return numberIcons[number];
	}
}
