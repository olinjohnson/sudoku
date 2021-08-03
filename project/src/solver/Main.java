package solver;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Puzzle squares = new Puzzle();
		int range = 9;
		System.out.println(backtrack(squares, 0, 0, range));
		printPuzzle(squares);
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
	public static int getFirstValue(int[] a) {
		for(int i = 0, n = a.length; i < n; i++) {
			if(a[i] != 0) {
				return a[i];
			}
 		}
		return -1;
	}
	
	//Function to print the sudoku board
	public static void printPuzzle(Puzzle a) {
		for(int i = 0; i < 9; i++) {
			for(int n = 0; n < 9; n++) {
				if(n == 8) {
					System.out.print(a.coords[i][n] + "\n");
				}else {
					System.out.print(a.coords[i][n]);
				}
			}
		}
		System.out.println("\n");
	}
}