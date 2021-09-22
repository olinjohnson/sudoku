package io.github.olinjohnson.solver;

public class Puzzle {
	
	//Sudoku puzzle test case
	public int[][] coords = {
			{0,0,0, 0,0,0, 0,0,0},
			{0,0,0, 0,0,0, 0,0,0},
			{0,0,0, 0,0,0, 0,0,0},
			
			{0,0,0, 0,0,0, 0,0,0},
			{0,0,0, 0,0,0, 0,0,0},
			{0,0,0, 0,0,0, 0,0,0},
			
			{0,0,0, 0,0,0, 0,0,0},
			{0,0,0, 0,0,0, 0,0,0},
			{0,0,0, 0,0,0, 0,0,0}
	};
	
	public int[][] coordsComp = coords.clone();
	
	//Gets an array of values in the row
	public int[] getAdjacentRows(Puzzle arr, int r, int x, int y) {
		int[] returnArr = {0,0,0,0,0,0,0,0,0};
		//iterate through all the rows and add values to returnArr
		for(int i = 0; i < r; i++) {
			returnArr[i] = arr.coords[y][i];
		}
		return returnArr;
	}
	
	//Gets an array of values in the column
	public int[] getAdjacentColumns(Puzzle arr, int r, int x, int y) {
		int[] returnArr = {0,0,0,0,0,0,0,0,0};
		//iterate through all the columns and add values to returnArr
		for(int i = 0; i < r; i++) {
			returnArr[i] = arr.coords[i][x];
		}
		return returnArr;
	}
	
	//Gets an array of values in the box
	public int[] getAdjacentBox(Puzzle arr, int r, int x, int y) {
		int[] returnArr = {0,0,0,0,0,0,0,0,0};

		//Get the top left coord of the box that the parameters is in
		int boxY = getBoxY(y);
		
		//Continue getting the top left coord of the box that the parameters is in
		if(x <= 2) {
			returnArr = getBox(arr,r,0,boxY);
		}else if(x <= 5 && x > 2) {
			returnArr = getBox(arr,r,3,boxY);
		}else {
			returnArr = getBox(arr,r,6,boxY);
		}	
		return returnArr;
	}
	
	
	
	
	//Part of getAdjacentBox
	private static int[] getBox(Puzzle arr, int r, int x, int y) {
		int[] returnArr = {0,0,0,0,0,0,0,0,0};
		
		int ninthCounter = 0;
		
		//Iterate through all the values in a box and add them to returnArr
		for(int i = 0; i < 3; i++) {
			for(int n = 0; n < 3; n++) {
				returnArr[ninthCounter] = arr.coords[y+i][x+n];
				ninthCounter++;
			}
		}
		
		return returnArr;
	}
	
	//Part of getAdjacentBox
	private static int getBoxY(int y) {
		if(y <= 2) {
			return 0;
		}else if(y <= 5 && y > 2) {
			return 3;
		}else {
			return 6;
		}
	}
	
	
	
	public boolean checkIfNum(Puzzle arr, int x, int y, int number, int r) {
		if(!arr.containsNum(arr.getAdjacentRows(arr, r, x, y), number) &&
			!arr.containsNum(arr.getAdjacentColumns(arr, r, x, y), number) &&
			!arr.containsNum(arr.getAdjacentBox(arr, r, x, y), number)) {
			//System.out.println("DOES NOT CONTAIN NUM");
			return false;
			
		} else {
			//System.out.println("CONTAINS NUM");
			return true;
		}
	}
	
	//A function to check if an array contains a specified number
	private boolean containsNum(int[] a, int num) {
		for(int i = 0, n = a.length; i < n; i++) {
			if(a[i] == num) {
				return true;
			}
		}
		return false;
	}

}