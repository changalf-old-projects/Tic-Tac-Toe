package TicTacToe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class TicTacToe extends JFrame implements ActionListener {
	
	public static int WIDTH = 450;
	public static int HEIGHT = 670;
	
	int n = 3;				//used when checking for winners in hasWinner(x, y, s);
	
	private JPanel[] row = new JPanel[8];
	private JTextField[] scoreDisplay = new JTextField[4];
	private JTextField messageDisplay = new JTextField();
	private JLabel playerX = new JLabel("X");		
	private JLabel playerO = new JLabel("O");	
	private JLabel winScoreX, lossScoreX, winScoreO, lossScoreO;
	private boolean playerTurn;	
													
	private JButton[][] square = new JButton[3][3];		
	private JButton resetButton;
	private int numWinsX = 0, numLossesX = 0, numWinsO = 0, numLossesO = 0;
	private Font playerFont = new Font("Comic Sans", Font.BOLD, 30);
	private Font displayFont = new Font("Times New Roman", Font.BOLD, 16);
	private Font winLoseLabelFont = new Font("Helvetica", Font.BOLD, 12);
	
	private Dimension buttonDim = new Dimension(80, 80);
	private Dimension displayDim = new Dimension(60, 50);
	private Dimension labelDim = new Dimension(120, 50);
	private Dimension winLoseLabelDim = new Dimension(60, 30);
	private Dimension resetBtnDim = new Dimension(150, 25);
	private Dimension messageDisplayDim = new Dimension(200, 30);
	
	private Border dashedBorder = BorderFactory.createDashedBorder(Color.YELLOW, 3, 3, 3, true);
	private Border dashedBevelBorder = BorderFactory.createEtchedBorder(Color.CYAN.darker(), Color.CYAN.darker());
	
	public TicTacToe() {
		super("Tic-Tac-Toe Board");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDesign();
		setLayout(new GridLayout(8, 5));
		setResizable(false);
		
		winScoreX = new JLabel("W");
		lossScoreX = new JLabel("L");
		winScoreO = new JLabel("W");
		lossScoreO = new JLabel("L");
		
		playerTurn = true;			
		
		//initialize the first panel
		row[0] = new JPanel();
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//initialize rest of panels
		for (int i = 1; i < row.length - 2; i++) {
			row[i] = new JPanel();
			row[i].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		}
		
		row[6] = new JPanel();
		row[6].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
		
		row[7] = new JPanel();
		row[7].setLayout(new FlowLayout(FlowLayout.CENTER));
 		
		//initialize buttons
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				square[i][j] = new JButton();
				square[i][j].setPreferredSize(buttonDim);
				square[i][j].setForeground(Color.BLUE);
				square[i][j].setBorder(new EtchedBorder(Color.BLACK, Color.RED));
				square[i][j].addActionListener(this);		
			}
		} 
		
		//initialize and implement displays
		for (int i = 0; i < scoreDisplay.length; i++) {
			scoreDisplay[i] = new JTextField();
			scoreDisplay[i].setFont(displayFont);
			scoreDisplay[i].setPreferredSize(displayDim);
			scoreDisplay[i].setEditable(false);
			scoreDisplay[i].setText("0");
			scoreDisplay[i].setOpaque(true);
			scoreDisplay[i].setBackground(Color.ORANGE);
			scoreDisplay[i].addActionListener(this);			
		}
		
		//set player label size and font
		playerX.setPreferredSize(labelDim);
		playerX.setFont(playerFont);
		playerX.setHorizontalAlignment(JLabel.CENTER);
		playerX.setBorder(new LineBorder(Color.YELLOW, 3));
		playerO.setPreferredSize(labelDim);
		playerO.setFont(playerFont);
		playerO.setHorizontalAlignment(JLabel.CENTER);
		playerO.setBorder(new LineBorder(Color.CYAN.darker(), 3));
		
		//set win label/loss label (player X) size and font
		winScoreX.setPreferredSize(winLoseLabelDim);
		winScoreX.setFont(winLoseLabelFont);
		winScoreX.setHorizontalAlignment(JLabel.CENTER);
		winScoreX.setVerticalAlignment(JLabel.BOTTOM);
		winScoreX.setBorder(dashedBorder);
		lossScoreX.setPreferredSize(winLoseLabelDim);
		lossScoreX.setFont(winLoseLabelFont);
		lossScoreX.setHorizontalAlignment(JLabel.CENTER);
		lossScoreX.setVerticalAlignment(JLabel.BOTTOM);
		lossScoreX.setBorder(dashedBevelBorder);
		
		//set win label/loss label (player O) size and font
		winScoreO.setPreferredSize(winLoseLabelDim);
		winScoreO.setFont(winLoseLabelFont);
		winScoreO.setHorizontalAlignment(JLabel.CENTER);
		winScoreO.setVerticalAlignment(JLabel.BOTTOM);
		winScoreO.setBorder(dashedBorder);
		lossScoreO.setPreferredSize(winLoseLabelDim);
		lossScoreO.setFont(winLoseLabelFont);
		lossScoreO.setHorizontalAlignment(JLabel.CENTER);
		lossScoreO.setVerticalAlignment(JLabel.BOTTOM);
		lossScoreO.setBorder(dashedBevelBorder);
		
		//add components to frame
		row[0].add(playerX);
		row[0].add(playerO);
		add(row[0]);
		
		row[1].add(winScoreX);
		row[1].add(lossScoreX);
		row[1].add(winScoreO);
		row[1].add(lossScoreO);
		add(row[1]);
		
		//add counter displays
		for (int i = 0; i < scoreDisplay.length; i++) {
			row[2].add(scoreDisplay[i]);
		}
		add(row[2]);
		
		//add first row of squares
		for (int i = 0; i < n; i++) {
			row[3].add(square[0][i]);
		}
		add(row[3]);
		
		//add second row of squares
		for (int i = 0; i < n; i++) {
			row[4].add(square[1][i]);
		}
		add(row[4]);
		
		//add final row of squares
		for (int i = 0; i < n; i++) {
			row[5].add(square[2][i]);
		}
		add(row[5]);
		
		resetButton = new JButton("Reset Game");
		resetButton.setPreferredSize(resetBtnDim);
		resetButton.setForeground(Color.DARK_GRAY);
		resetButton.addActionListener(this);
		row[6].add(resetButton);
		add(row[6]);
		
		messageDisplay.setPreferredSize(messageDisplayDim);
		messageDisplay.setHorizontalAlignment(JTextField.CENTER);
		messageDisplay.setOpaque(true);
		messageDisplay.setForeground(Color.WHITE);
		messageDisplay.setBackground(Color.GREEN.darker());
		messageDisplay.setEditable(true);
		row[7].add(messageDisplay);
		add(row[7]);
		
		setVisible(true);
	}
	
	//"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
	public void setDesign() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//determine which player's turn it is;
	//true = player's turn; false = not his turn
	public void switchTurn() {
		if (playerTurn) {
			playerTurn = false;			
		} else {
			playerTurn = true;			
		}
	}
	
	//is there a winner yet?
	public boolean hasWinner(int x, int y, String t) {
		try {
			//check rows
			for (int i = 0; i < n; i++) {
				if (square[x][i].getText() != t) {
					break;			//no winner yet
				}
				if (i == n - 1) {
					playerWins(t);
					return true;
				}
			}
			//check columns
			for (int j = 0; j < n; j++) { 
				if (square[j][y].getText() != t) {
					break;
				}
				if (j == n - 1) {
					playerWins(t);
					return true;
				}
			}
			//check diagonal
			if (x == y) {
				for (int k = 0; k < n; k++) {
					if (square[k][k].getText() != square[x][y].getText()) {
						break;
					}
					if (k == 2) {
						playerWins(t);
						return true;
					}
				}
			}
			//check anti-diagonal
			if (x == 0 && y == 2 || x == 1 && y == 1 || x == 2 && y == 0) {
				for (int k = 0; k < n; k++) {
					if (square[k][(n - 1) - k].getText() != square[x][y].getText()) {
						break;
					}
					if (k == 2) {
						playerWins(t);
						return true;
					}
				}
			}
			
			if (isBoardFull()) {
				resetSquares();
			}
			
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			aioobe.printStackTrace();
		}
		return false;
	}
	
	//save some lines of code by having hasWinner() call this method and this method call other methods
	public void playerWins(String p) {
		incrementScore(p);
		displayMessage(p);
		resetSquares();
	}
	
	//update counter for wins and losses
	public void incrementScore(String s) {
		try {
			if (s == ("X")) {
				numWinsX += 1;
				scoreDisplay[0].setText(String.valueOf(numWinsX));
				numLossesO += 1;
				scoreDisplay[3].setText(String.valueOf(numLossesO));
			} else if (s == ("O")) {	
				numWinsO += 1;
				scoreDisplay[1].setText(String.valueOf(numWinsO));
				numLossesX += 1;
				scoreDisplay[2].setText(String.valueOf(numLossesX));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayMessage(String s) {
		messageDisplay.setText("Congrats!  Player " + s + " wins!");
	}
	
	public void resetSquares() {
		try {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
				square[i][j].setText("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void resetGame() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				square[i][j].setText("");
			}
		}
		for (int i = 0; i < scoreDisplay.length; i++) {
			scoreDisplay[i].setText("0");
		}
		
		messageDisplay.setText("");
		
		numWinsX = 0;
		numLossesX = 0;
		numWinsO = 0;
		numLossesO = 0;
		
		playerTurn = true;
	}
	
	//reset all squares if board is full without any winner
	public boolean isBoardFull() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (square[i][j].getText() == "") {
					return false;
				}
			}
		}
		messageDisplay.setText("Game over! Board is full!");
		return true;
	}
	
	//x will be the row index and y will be the col index
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == resetButton) {
			resetGame();
			return;
		}

		int x = 0, y = 0;

		if (playerTurn) {
			if (ae.getSource() == square[0][0]) {
				square[0][0].setText("X");
				x = 0;
				y = 0;
			} else if (ae.getSource() == square[0][1]) {
				square[0][1].setText("X");
				x = 0;
				y = 1;			
			} else if (ae.getSource() == square[0][2]) {
				square[0][2].setText("X");
				x = 0;
				y = 2;
			} else if (ae.getSource() == square[1][0]) {
				square[1][0].setText("X");
				x = 1;
				y = 0;
			} else if (ae.getSource() == square[1][1]) {
				square[1][1].setText("X");
				x = 1;
				y = 1;
			} else if (ae.getSource() == square[1][2]) {
				square[1][2].setText("X");
				x = 1;
				y = 2;
			} else if (ae.getSource() == square[2][0]) {
				square[2][0].setText("X");
				x = 2;
				y = 0;
			} else if (ae.getSource() == square[2][1]) {
				square[2][1].setText("X");
				x = 2;
				y = 1;
			} else if (ae.getSource() == square[2][2]) {
				square[2][2].setText("X");
				x = 2;
				y = 2;
			}
			switchTurn();
			hasWinner(x, y, "X");
		} else {
			if (ae.getSource() == square[0][0]) {
				square[0][0].setText("O");
				x = 0;
				y = 0;
			} else if (ae.getSource() == square[0][1]) {
				square[0][1].setText("O");
				x = 0;
				y = 1;			
			} else if (ae.getSource() == square[0][2]) {
				square[0][2].setText("O");
				x = 0;
				y = 2;
			} else if (ae.getSource() == square[1][0]) {
				square[1][0].setText("O");
				x = 1;
				y = 0;
			} else if (ae.getSource() == square[1][1]) {
				square[1][1].setText("O");
				x = 1;
				y = 1;
			} else if (ae.getSource() == square[1][2]) {
				square[1][2].setText("O");
				x = 1;
				y = 2;
			} else if (ae.getSource() == square[2][0]) {
				square[2][0].setText("O");
				x = 2;
				y = 0;
			} else if (ae.getSource() == square[2][1]) {
				square[2][1].setText("O");
				x = 2;
				y = 1;
			} else if (ae.getSource() == square[2][2]) {
				square[2][2].setText("O");
				x = 2;
				y = 2;
			}
			switchTurn();
			hasWinner(x, y, "O");
		}
	}

}
