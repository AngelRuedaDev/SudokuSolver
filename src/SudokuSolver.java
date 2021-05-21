
public class SudokuSolver {

	public static int [][] SUDOKU_GRID= {
			{0,4,0,9,0,0,6,0,1},
			{7,8,0,0,0,0,0,3,0},
			{1,0,5,0,0,4,9,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,9,0,5,4,0,2,0,6},
			{0,0,0,0,0,1,0,0,7},
			{0,7,0,0,8,9,1,0,0},
			{0,0,0,1,2,0,0,0,4},
			{8,0,0,0,0,0,0,9,0}
	};
	
	public static void main(String[] args) {		
		
		Sudoku sudoku = new Sudoku(SUDOKU_GRID);
		sudoku.displaySudoku();
		System.out.println("---------------------");
		
		if(sudoku.solveSudoku()) {
			System.out.println("SOLVED!");
			sudoku.displaySudoku();
		}else {
			System.out.println("UNSOLVABLE!!! :(");
		}
		
		
	}
	
	
	
	
}
	



	


