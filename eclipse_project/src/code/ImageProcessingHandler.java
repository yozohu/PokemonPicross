package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*This class responsible for creating a solution array from a given image
 * IF the image does not alr have a solution file in the name ___,
 *  then it creates one
 * 
 * Uses the python script to generte the pixel data of an image
 * 
 * Singleton? bc u only ever need on instance..
 * esp since itll be running processes
 */
public class ImageProcessingHandler {

	private static final int THRESHOLD = 100; // RGB threshold to determine whetehr a pixel will go black or white
	private String fileName; // name of current image being processed
	private int pixelData[];
	private boolean solution[][];
	private int imgWidth;
	private int imgHeight;
	private static ImageProcessingHandler instance = null;

	// private constructor, initiaizing wtv needs 2 be
	private ImageProcessingHandler() {

	}

	public static ImageProcessingHandler getInstance() {

		if (instance == null) {
			instance = new ImageProcessingHandler();
		}
		
		return instance;
	}

	// methods

//	getSolutionFromImage(String imageFilePath){
//		//if( file does not exist) {
//		//	handle;
//		//}
//		
//		getPixelData(imageFilePath); //calls python script
//
//	}

	void getPixelDataFromFile(String fileName) throws IOException{
		File f = new File(fileName);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		//below assumes file properly follows certain pattern...
		imgWidth = Integer.parseInt(br.readLine());
		imgHeight =  Integer.parseInt(br.readLine());
		
		int pixDataSize = 3*imgWidth*imgHeight;
		pixelData = new int[pixDataSize];
		
		String currLine;
		int pixDataIx = 0;
		while( (currLine = br.readLine()) != null ) {
			pixelData[pixDataIx++] = Integer.parseInt(currLine);
		}
	}

	
	 public void solutionFromPixelData(){
		 solutionFromPixelData(pixelData);
	 }
	 
	 private void solutionFromPixelData(int[] pixelArr) {
		 solution = new boolean[imgHeight][imgWidth]; 
		 int sum; //of rgb values 
		 int solIx = 0; 
		 for(int i = 0; i < pixelArr.length/3;i++) { 
			 sum = pixelArr[3*i] + pixelArr[3*i +1] + pixelArr[3*i+2];
			 solution[solIx/imgWidth][solIx%imgWidth] = (sum<THRESHOLD) ? true : false;
			 solIx++; 
		 } 
	 }
	 

	public void printPixelData() {
		for (int i : pixelData) {
			System.out.println(i);
		}
	}
	
	public void printSol() {
		
		for(int i = 0; i < imgHeight; i++) {
			for(int j=0; j< imgWidth; j++) {
				System.out.print(solution[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void descaleSol(int g) { //reduce size by grouping g^2 num of squares togetehr
		
		int[][] tempSol = new int[(int) Math.ceil((double)imgHeight/g)][(int) Math.ceil((double)imgWidth/g)];
		
		for(int col = 0; col< imgWidth; col++) {
			for(int row = 0; row < imgHeight; row++) {
				tempSol[row/g][col/g] += (solution[row][col])? 1:0;
			}
		}
		
		imgHeight = (int) Math.ceil((double)imgHeight/g);
		imgWidth = (int) Math.ceil((double)imgWidth/g);
		
		
		solution = new boolean[imgHeight][imgWidth];
		
		for(int col = 0; col< imgWidth; col++) {
			for(int row = 0; row < imgHeight; row++) {
				solution[row][col] =  (tempSol[row][col] > g*g/3 )? true: false;
			}
		}

	}
	
	public static void print2DArray(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			
			for(int j =0; j < arr[0].length; j ++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
				
		}
	}
	
	public boolean[][] getSolution(){
		return solution;
	}
	
	
}
