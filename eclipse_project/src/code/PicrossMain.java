package code;

public class PicrossMain {
	public static void main(String[] args){
		boolean[][] sol = { {true, true, false, false, true},
							{false, false, false, false, false},
							{true, true, true, true, true},
							{false,  true, true, true, false},
							{true, false, true, true, true},  };
		BoardGUI g = new BoardGUI(new BoardModel(sol));	
	}
}
