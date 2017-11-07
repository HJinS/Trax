package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameGUI {
	/*
	 * Retain: SystemMenuBar, GUI.InGameMenuBar, GameStatus
	 */

	// Constant value
	private Dimension PANEL_SIZE = new Dimension(5623, 5623);

	// GUI be made by Developer
	private GUI.SysMenuBar sysMenuBar = new GUI.SysMenuBar();
	private GUI.InGameMenuBar inGameMenuBar = new GUI.InGameMenuBar();
	private GUI.ScrollBar scrollBar = new GUI.ScrollBar();
	private GUI.GameStatusBar gameStatusBar = new GUI.GameStatusBar();

	public GameGUI() {

	}

	public void setGUI(JFrame frame, JPanel panel) {
		sysMenuBar.setSysMenuBar(frame);

		gameStatusBar.setGameStatusBar(frame, panel);

		inGameMenuBar.setInGameMenuBar(frame, panel);
		panel.setLayout(new GridLayout(128, 128));
		panel.setPreferredSize(PANEL_SIZE);

		scrollBar.setScrollBar(frame, panel);
	}
}
