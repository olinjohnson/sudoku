package io.github.olinjohnson.sudoku;

public class Generator {

    Puzzle generatePuzzle() {
        int[][][] squares = new int[3][3][3];

        for(int z = 0; z < 3; z++) {
            for(int i = 0; i < 3; i++) {
                for(int x = 0; x < 3; x++) {
                    int rand = (int) Math.round(Math.random() * 9);
                    while(rand == 0 || arrayHasElem(squares[z], rand)) {
                        rand = (int) Math.round(Math.random() * 9);
                    }
                    squares[z][i][x] = rand;
                }
            }
        }

        Puzzle newpuzzle = new Puzzle();
        for(int z = 0; z < 3; z++) {
            for(int i = 0; i < 3; i++) {
                for(int x = 0; x < 3; x++) {
                    newpuzzle.coords[i + z * 3][x + z * 3] = squares[z][i][x];
                }
            }
        }
        newpuzzle.coordsComp = newpuzzle.coords.clone();
        Solve solver = new Solve();
        newpuzzle = solver.run(newpuzzle);

        return newpuzzle;

    }

    static boolean arrayHasElem(int[][] arr, int key) {

        for(int i = 0; i < arr.length; i++) {
            for(int x = 0; x < arr[i].length; x++) {
                if(arr[i][x] == key) {
                    return true;
                }
            }
        }
        return false;

    }

}
