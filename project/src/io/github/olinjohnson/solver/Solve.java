package io.github.olinjohnson.solver;

public class Solve {

	public Puzzle run(Puzzle squares) {

		int range = 9;
		backtrack(squares, 0, 0, range);
		return squares;
		
	}

	public static boolean backtrack(Puzzle arr, int juncX, int juncY, int r) {
		int junctionComp = arr.coordsComp[juncY][juncX];
		int newJunctionX;
		int newJunctionY;
		if(juncX == 8) {
			newJunctionX = 0;
			newJunctionY = juncY + 1;
		}else {
			newJunctionX = juncX + 1;
			newJunctionY = juncY;
		}
		
		if(juncY == 8 && juncX == 8) {
			for(int i = 1; i <= r; i++) {
				if(!arr.checkIfNum(arr, 8, 8, i, r)) {
					arr.coords[8][8] = i;
				}
			}
			return true;
		}
		
		for(int i = 1; i <= r; i++) {
			if(junctionComp != 0) {
				if(backtrack(arr, newJunctionX, newJunctionY, r)) {
					return true;
				}else {
					return false;
				}
			}else {
				if(!arr.checkIfNum(arr, juncX, juncY, i, r)) {
					arr.coords[juncY][juncX] = i;
					if(backtrack(arr, newJunctionX, newJunctionY, r)) {
						return true;
					}else {
						arr.coords[juncY][juncX] = 0;
					}
				}
			}
		}
		return false;
	}
	
	//Function to get the first value in an array that is not 0
	int getFirstValue(int[] a) {
		for(int i = 0, n = a.length; i < n; i++) {
			if(a[i] != 0) {
				return a[i];
			}
 		}
		return -1;
	}
	
}
