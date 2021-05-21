
public class Sudoku {
	public static final int EMPTY = 0;
	public static final int SIZE = 9;
	private static int [][] board;
	
	//CONSTRUCTOR
	public Sudoku(int[][] board) {
		this.board = new int[SIZE][SIZE];
		
		for(int i  = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}		
	}

	//Checking if the number is in the row
	private static boolean isInRow(int row, int num) {
		for(int i  = 0; i<SIZE; i++) {
			if(board[row][i] == num) {
				return true;
			}
		}
		return false;
	}
	
	//Checking if the number is in the column
	private static boolean isInColumn(int col, int num) {
		for(int i  = 0; i<SIZE; i++) {
			if(board[i][col] == num) {
				return true;
			}
		}
		return false;
	}
	
	//Checking if the number is in the 3x3 box
	private static boolean isIn3x3(int row, int col, int num) {
		
		int r = row - row % 3;
		int c = col - col % 3;
		
		for(int i  = r; i<r + 3; i++) {
			for(int j = c; j<c + 3; j++) {
				if(board[i][j] == num) {
					return true;
				}
			}
		}
		
		return false;		
	}
	
	//Putting in common all the validations
	private boolean isNumValid(int row, int col, int num) {
		return (!isInRow(row, num) && !isInColumn(col, num) && !isIn3x3(row, col, num)); 
	}
	
	
	//Solve the sudoku
	public boolean solveSudoku() {
		//int []numbers = {1,2,3,4,5,6,7,8,9};
				
		for(int i  = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				if(board[i][j] == EMPTY) {
					for(int num = 1; num <=SIZE; num++) {
						if(isNumValid(i, j, num)) {
							board[i][j] = num;
							
							if(solveSudoku()) {
								return true;
							}else {
								board[i][j] = EMPTY;
							}
						}
					}
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void displaySudoku() {
		for(int i  = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			
			System.out.println();
		}
	}
}
