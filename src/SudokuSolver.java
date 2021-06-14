import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class SudokuSolver {

	public static int [][] SUDOKU_GRID = new int [Sudoku.SIZE][Sudoku.SIZE];
	
	public static void main(String[] args) {		
		InitGUI();
	}
	
	private static void InitGUI() {
		JFrame frame = new JFrame("My Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        frame.setSize(500, 500);
        frame.setResizable(false);
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();     
        gbc.fill = GridBagConstraints.HORIZONTAL;       
        
        int i = 0, j = 0;
        Dimension dimensions = new Dimension(35, 35);
        for(i = 0; i < Sudoku.SIZE; i++) {
        	for(j = 0; j < Sudoku.SIZE; j++) {
        		JTextField jt = new JTextField();
        		jt.setPreferredSize(dimensions);
        		gbc.gridx = j;
                gbc.gridy = i;
                grid.add(jt, gbc);                
        	}
        }
        
        JLabel resolveLabel = new JLabel(" ");
        resolveLabel.setHorizontalAlignment(JLabel.CENTER);;
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth=9;
        grid.add(resolveLabel, gbc);
        
        JButton solutionButton = new JButton("Show solution");
        gbc.gridx = 0;
        gbc.gridy = i+1;
        gbc.gridwidth=9;
        grid.add(solutionButton, gbc);
        solutionButton.setVisible(false);
        
        JButton resolveButton = new JButton("Resolve!");
        resolveButton.addActionListener(new ActionListener() {        	
        	public void actionPerformed(ActionEvent e) {
        		GetNumbers(grid);
        		SolveSudoku(resolveLabel, solutionButton);
        	}
        });
        
        gbc.gridx = 0;
        gbc.gridy = i+2;
        gbc.gridwidth=9;
        grid.add(resolveButton, gbc);        
        
        frame.add(grid);
        frame.setVisible(true);
	}
	
	private static void SolveSudoku(JLabel resolveLabel, JButton solutionButton) {
		Sudoku sudoku = new Sudoku(SUDOKU_GRID);
		System.out.println("---------------------");
				
		if(sudoku.solveSudoku()) {
			resolveLabel.setText("This sudoku is solvable!!");
			resolveLabel.setBackground(Color.green);
			resolveLabel.setOpaque(true);
			
			solutionButton.setVisible(true);
			solutionButton.addActionListener(new ActionListener() {        	
	        	public void actionPerformed(ActionEvent e) {
	        		ShowSolution(solutionButton, sudoku);
	        	}
	        });
		}else {
			solutionButton.setVisible(false);
			resolveLabel.setText("This sudoku is UNSOLVABLE!!");
			resolveLabel.setBackground(Color.red);
			resolveLabel.setOpaque(true);
		}
	}
	
	private static void GetNumbers(JPanel grid) {		
		ArrayList<JTextField> jtArrayList = new ArrayList<JTextField>();	
		
		for(Component child : grid.getComponents()) {
        	if(child instanceof JTextField) {
        		jtArrayList.add((JTextField)child);
        	}
        }
		int counter = 0;	
				
		for(int i = 0; i < Sudoku.SIZE; i++) {
			for(int j = 0; j < Sudoku.SIZE; j++) {				
				if((jtArrayList.get(counter).getText().toString()).equals("")) {
					SUDOKU_GRID[i][j] = 0;
				}else {
					SUDOKU_GRID[i][j] = Integer.parseInt(jtArrayList.get(counter).getText().toString());
				}
				
				counter++;
			}
		}
	}
	
	private static void ShowSolution(JButton solutionButton, Sudoku sudoku) {
		JFrame solution = new JFrame();
		solution.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      
        solution.setSize(300, 300);
        solution.setResizable(false);
        
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();     
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        
        int i = 0, j = 0;
        Dimension dimensions = new Dimension(35, 35);
        for(i = 0; i < Sudoku.SIZE; i++) {
        	for(j = 0; j < Sudoku.SIZE; j++) {
        		JLabel solutionLabel = new JLabel(String.valueOf(sudoku.board[i][j]));
        		solutionLabel.setHorizontalAlignment(JLabel.CENTER);
        		solutionLabel.setSize(dimensions);
        		gbc.gridx = j;
                gbc.gridy = i;
                grid.add(solutionLabel, gbc);                
        	}
        }
        
        solution.add(grid);
        solution.setVisible(true);
	}
}
