package code;

import java.io.File;
import java.io.IOException;

public class IPHTest {
	
	public static void main(String[] args) throws IOException {
		String pythonOutputDataFileName = "output.txt";
		ImageProcessingHandler iph = ImageProcessingHandler.getInstance();

		iph.getPixelDataFromFile(pythonOutputDataFileName);
		
//		iph.printPixelData();
	iph.solutionFromPixelData();
		iph.descaleSol(40);
//		iph.printSol();
		

		BoardGUI g = new BoardGUI(new BoardModel(iph.getSolution()));	
		g.showSolution();
	}

}
