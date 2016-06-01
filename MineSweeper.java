package minesweeper;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MineSweeper implements MouseListener{
	private static MineButton[][] buttons;
	JFrame frame;
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	private int numberOfMines;
	private int numberFlagged;
	private JLabel flagLabel;
	public MineSweeper() {
		numberOfMines = 0;
		numberFlagged = 0;
		buttons = new MineButton[WIDTH][HEIGHT];
		frame = new JFrame("Mine Sweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 900);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		JPanel minebuttons = new JPanel(new GridLayout(WIDTH, HEIGHT));
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				buttons[x][y] = new MineButton(x, y);
				buttons[x][y].addMouseListener(this);
				minebuttons.add(buttons[x][y]);
				if (buttons[x][y].getMine())
					numberOfMines++;
			}
		}
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				buttons[x][y].setMineNumber();
			}
		}
		frame.add(minebuttons);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		frame.add(statusPanel, BorderLayout.SOUTH);
		
		JLabel mineLabel = new JLabel("Number Of Mines: " + Integer.toString(numberOfMines));
		mineLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mineLabel.setForeground(Color.RED);
		statusPanel.add(mineLabel);
		
		statusPanel.add(Box.createHorizontalGlue());
		
		flagLabel = new JLabel("Number Flagged: " + Integer.toString(numberFlagged));
		flagLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		flagLabel.setForeground(Color.RED);
		statusPanel.add(flagLabel);
	}
	public static MineButton getB(int x, int y) {
		return buttons[x][y];
	}
	private void fixFlagLabel() {
		flagLabel.setText("Number Flagged: " + Integer.toString(numberFlagged));
	}
	private void checkWin(){
		int buttonsDealtWith = 0;
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				if (buttons[x][y].isRevealed()) buttonsDealtWith++;
				if (buttons[x][y].getMine()) buttonsDealtWith++;
			}
		}
		if (buttonsDealtWith++ == (WIDTH * HEIGHT)) {
			JOptionPane.showMessageDialog(frame, "You Win!");
			frame.dispose();
			new MineSweeper();
		}
	}
	private void gameOver() {
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				if (buttons[x][y].getMine()) {
					buttons[x][y].setBackground(Color.RED);
				}
			}
		}
		JOptionPane.showMessageDialog(frame, "Game Over");
		frame.dispose();
		new MineSweeper();
	}
	public void actionPerformed(ActionEvent e) {
		if (!((MineButton)e.getSource()).getMine()) {
			((MineButton)e.getSource()).revealMineNumber();
		}
		else {
			gameOver();
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() {
				new MineSweeper();
			}
		});
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
	@Override
	public void mouseReleased(MouseEvent me) {
		if (SwingUtilities.isLeftMouseButton(me)) {
			if (!((MineButton)me.getSource()).getMine() && !((MineButton)me.getSource()).isFlagged()) {
				((MineButton)me.getSource()).revealMineNumber();
			}
			else if (((MineButton)me.getSource()).isFlagged()) {
				((MineButton)me.getSource()).removeFlagged();
				numberFlagged--;
			}
			else {
				gameOver();
			}
		}
		else {
			if (!((MineButton)me.getSource()).isFlagged()) {
				((MineButton)me.getSource()).setFlagged();
				numberFlagged++;
			}
			else {
				((MineButton)me.getSource()).removeFlagged();
				numberFlagged--;
			}
		}
		fixFlagLabel();
		checkWin();
	}
}
