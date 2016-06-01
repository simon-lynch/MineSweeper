package minesweeper;
import java.awt.Color;
import java.util.Stack;

import javax.swing.JButton;
@SuppressWarnings("serial")
public class MineButton extends JButton {
	private int xpos;
	private int ypos;
	private boolean mine;
	private String type;
	private boolean revealed;
	private int mineNumber;
	private boolean flagged;
	public MineButton(int x, int y) {
		xpos = x;
		ypos = y;
		if (xpos == 0 && ypos == 0) type = "topleft";
		else if (xpos == 0 && ypos == (MineSweeper.HEIGHT - 1)) type = "bottomleft";
		else if (xpos == (MineSweeper.WIDTH - 1) && ypos == 0) type = "topright";
		else if (xpos == (MineSweeper.WIDTH - 1) && ypos == (MineSweeper.HEIGHT - 1)) type = "bottomright";
		else if(xpos == 0) type = "xnegedge";
		else if (ypos == 0) type = "ynegedge";
		else if (ypos == (MineSweeper.HEIGHT - 1)) type = "yposedge";
		else if (xpos == (MineSweeper.WIDTH - 1)) type = "xposedge";
		else type = "middle";
		if (Math.random() < 0.135) {
			mine = true;
		}
		else {
			mine = false;
		}
		setOpaque(true);
		setBackground(Color.BLUE);
		revealed = false;
		flagged = false;
	}
	public boolean getMine() {
		return mine;
	}
	public boolean isRevealed() {
		return revealed;
	}
	public boolean isFlagged() {
		return flagged;
	}
	public void setFlagged() {
		setBackground(Color.ORANGE);
		setText("F");
		flagged = true;
	}
	public void revealMineNumber() {
		setBackground(Color.GREEN);
		if (mineNumber != 0)
			setText(Integer.toString(mineNumber));
		revealed = true;
		if (mineNumber == 0) {
			Stack<MineButton> s = new Stack<MineButton>();
			if (type == "middle") {
				s.push(MineSweeper.getB(xpos - 1, ypos - 1));
				s.push(MineSweeper.getB(xpos - 1, ypos));
				s.push(MineSweeper.getB(xpos - 1, ypos + 1));
				s.push(MineSweeper.getB(xpos, ypos - 1));
				s.push(MineSweeper.getB(xpos, ypos + 1));
				s.push(MineSweeper.getB(xpos + 1, ypos - 1));
				s.push(MineSweeper.getB(xpos + 1, ypos));
				s.push(MineSweeper.getB(xpos + 1, ypos + 1));
			}
			else if (type == "xposedge") {
				s.push(MineSweeper.getB(xpos - 1, ypos - 1));
				s.push(MineSweeper.getB(xpos - 1, ypos));
				s.push(MineSweeper.getB(xpos - 1, ypos + 1));
				s.push(MineSweeper.getB(xpos, ypos - 1));
				s.push(MineSweeper.getB(xpos, ypos + 1));
			}
			else if (type == "xnegedge") {
				s.push(MineSweeper.getB(xpos, ypos - 1));
				s.push(MineSweeper.getB(xpos, ypos + 1));
				s.push(MineSweeper.getB(xpos + 1, ypos - 1));
				s.push(MineSweeper.getB(xpos + 1, ypos));
				s.push(MineSweeper.getB(xpos + 1, ypos + 1));
			}
			else if (type == "ynegedge") {
				s.push(MineSweeper.getB(xpos + 1, ypos));
				s.push(MineSweeper.getB(xpos + 1, ypos + 1));
				s.push(MineSweeper.getB(xpos, ypos + 1));
				s.push(MineSweeper.getB(xpos - 1, ypos));
				s.push(MineSweeper.getB(xpos - 1, ypos + 1));
			}
			else if (type == "yposedge") {
				s.push(MineSweeper.getB(xpos - 1, ypos - 1));
				s.push(MineSweeper.getB(xpos - 1, ypos));
				s.push(MineSweeper.getB(xpos, ypos - 1));
				s.push(MineSweeper.getB(xpos + 1, ypos - 1));
				s.push(MineSweeper.getB(xpos + 1, ypos));
			}
			else if (type == "topleft") {
				s.push(MineSweeper.getB(xpos + 1, ypos));
				s.push(MineSweeper.getB(xpos + 1, ypos + 1));
				s.push(MineSweeper.getB(xpos, ypos + 1));
			}
			else if (type == "bottomleft") {
				s.push(MineSweeper.getB(xpos + 1, ypos - 1));
				s.push(MineSweeper.getB(xpos + 1, ypos));
				s.push(MineSweeper.getB(xpos, ypos - 1));
			}
			else if (type == "topright") {
				s.push(MineSweeper.getB(xpos - 1, ypos));
				s.push(MineSweeper.getB(xpos - 1, ypos + 1));
				s.push(MineSweeper.getB(xpos, ypos + 1));
			}
			else if (type == "bottomright") {
				s.push(MineSweeper.getB(xpos - 1, ypos - 1));
				s.push(MineSweeper.getB(xpos - 1, ypos));
				s.push(MineSweeper.getB(xpos, ypos - 1));
			}
			while (!s.isEmpty()) {
				if (!s.peek().isRevealed()) {
					s.pop().revealMineNumber();
				}
				else {
					s.pop();
				}
			}
		}
	}
	public void removeFlagged() {
		flagged = false;
		setText("");
		setBackground(Color.BLUE);
	}
	public void setMineNumber() {
		int numberOfMines = 0;
		if (type == "middle") {
			if(MineSweeper.getB(xpos - 1, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos - 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos - 1, ypos + 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos + 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos + 1).getMine()) numberOfMines++;
		}
		else if (type == "xposedge") {
				if(MineSweeper.getB(xpos - 1, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos - 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos - 1, ypos + 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos + 1).getMine()) numberOfMines++;
		}
		else if (type == "xnegedge") {
			if(MineSweeper.getB(xpos, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos + 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos + 1).getMine()) numberOfMines++;
		}
		else if (type == "ynegedge") {
			if(MineSweeper.getB(xpos + 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos + 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos + 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos - 1, ypos).getMine()) numberOfMines++;
				if(MineSweeper.getB(xpos - 1, ypos + 1).getMine()) numberOfMines++;
		}
		else if (type == "yposedge") {
			if(MineSweeper.getB(xpos - 1, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos - 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos).getMine()) numberOfMines++;
		}
		else if (type == "topleft") {
			if(MineSweeper.getB(xpos + 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos + 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos + 1).getMine()) numberOfMines++;
		}
		else if (type == "bottomleft") {
			if(MineSweeper.getB(xpos + 1, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos + 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos - 1).getMine()) numberOfMines++;
		}
		else if (type == "topright") {
			if(MineSweeper.getB(xpos - 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos - 1, ypos + 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos + 1).getMine()) numberOfMines++;
		}
		else if (type == "bottomright") {
			if(MineSweeper.getB(xpos - 1, ypos - 1).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos - 1, ypos).getMine()) numberOfMines++;
			if(MineSweeper.getB(xpos, ypos - 1).getMine()) numberOfMines++;
		}
		mineNumber = numberOfMines;
	}
}
