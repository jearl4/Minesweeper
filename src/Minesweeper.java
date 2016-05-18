import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Minesweeper implements ActionListener {
	JFrame frame = new JFrame("Minesweeper"); // frame containing the game
	JButton reset = new JButton("Reset");
	JButton[][] buttons = new JButton[20][20]; // display of mine field grid
	int[][] counts = new int[20][20]; // will hold the data for the squares
	Container grid = new Container();
	final int MINE = 10; // holds value of mine location

	public Minesweeper() {
		frame.setSize(1000, 800);
		frame.setLayout(new BorderLayout());
		frame.add(reset, BorderLayout.SOUTH); // position the reset button
		reset.addActionListener(this); // listen for click

		// make button grid
		grid.setLayout(new GridLayout(20, 20));
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(this);
				grid.add(buttons[i][j]);
			}
		}
		frame.add(grid, BorderLayout.CENTER);
		createRandomMines(30);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Minesweeper();

	}

	/**
	 * create list of all possible values, pick one from list then remove that
	 * element from list to ensure no duplicate mines are placed. Hundredth and
	 * thousandth digit keeps track of x, tens and ones keeps track of y.
	 * 
	 * @param numberOfMines
	 *            says how many mines will be created
	 */
	public void createRandomMines(int numberOfMines) {
		ArrayList<Integer> listOfTiles = new ArrayList<Integer>();
		// add all possible scenarios of numbers
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts.length; y++) {
				// allows for division and mod to get number back
				listOfTiles.add(x * 100 + y);
			}
		}
		counts = new int[20][20];
		for (int i = 0; i < numberOfMines; i++) {
			// pick out a mine location
			int choice = (int) (Math.random() * listOfTiles.size());
			counts[listOfTiles.get(choice) / 100][listOfTiles.get(choice) % 100] = MINE;
			listOfTiles.remove(choice);
		}
		// check all neighbors for land mines and assign values
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts.length; y++) {
				if (counts[x][y] != MINE) {
					int neighborCount = 0;
					// top left tile
					if (x > 0 && y > 0 && counts[x - 1][y - 1] == MINE) {
						neighborCount++;
					}
					// top center
					if (y > 0 && counts[x][y - 1] == MINE) {
						neighborCount++;
					}
					// top right
					if (x < counts.length - 1 && y > 0 && counts[x + 1][y - 1] == MINE) {
						neighborCount++;
					}
					// center left
					if (x > 0 && counts[x - 1][y] == MINE) {
						neighborCount++;
					}
					// center right
					if (x < counts.length - 1 && counts[x + 1][y] == MINE) {
						neighborCount++;
					}
					// bottom left
					if (x > 0 && y < counts.length - 1 && counts[x - 1][y + 1] == MINE) {
						neighborCount++;
					}
					// bottom center
					if (y < counts.length - 1 && counts[x][y + 1] == MINE) {
						neighborCount++;
					}
					// bottom right
					if (x < counts.length - 1 && y < counts.length - 1 && counts[x + 1][y + 1] == MINE) {
						neighborCount++;
					}
					counts[x][y] = neighborCount;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(reset)) {
			// reset the mine field
		} else {
			for (int x = 0; x < buttons.length; x++) {
				for (int y = 0; y < buttons.length; y++) {
					if (event.getSource() == buttons[x][y]) {
						buttons[x][y].setText(counts[x][y] + "");
						buttons[x][y].setEnabled(false);
					}
				}
			}
		}
	}

}
