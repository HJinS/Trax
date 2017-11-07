package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStatusBar {
	private boolean turn;

	static JLabel gameStatusBar = new JLabel("BlackTurn");

	public GameStatusBar() {

	}

	public void getWhoesTurn(boolean turn) {
		this.turn = turn;

		if (turn)
			gameStatusBar.setText("White Turn");
		else if (!turn)
			gameStatusBar.setText("Black Trun");

	}

	JLabel returnGameStatusBar() {
		return gameStatusBar;
	}

	void setGameStatusBar(JFrame frame, JPanel panel) {
		frame.add(gameStatusBar, BorderLayout.SOUTH);

	}
}
