import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Minesweeper {
	JFrame frame = new JFrame("Minesweeper"); // frame containing the game
	JButton reset = new JButton("Reset");
	JButton[][] buttons = new JButton[20][20]; // display of mine field grid
	int[][] counts = new int[20][20]; // will hold the data for the squares
	Container grid = new Container();

	public Minesweeper() {
		frame.setSize(400, 400);
		frame.setLayout(new BorderLayout());
		frame.add(reset, BorderLayout.SOUTH); // position the reset button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Minesweeper game = new Minesweeper();

	}

}
