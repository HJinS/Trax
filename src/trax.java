import GUI.GameGUI;
import Button.TileBtn;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Trax {
  /*
   * Retain: GUI.GameGUI, GameBtn, GameRule
   * */
  // About GUI library
  private Dimension WINDOW_SIZE = new Dimension(800, 600);

  private static JFrame windowFrame = new JFrame();
  public static JPanel backPanel = new JPanel();

  GameGUI gui = new GameGUI();
  TileBtn btn = new TileBtn();

  Trax() {
    gui.setGUI(windowFrame, backPanel);
    btn.setBtn(backPanel);

    windowFrame.setSize(WINDOW_SIZE);
    windowFrame.setVisible(true);
  }
}
