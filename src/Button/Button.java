package Button;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button {
  private Dimension BUTTON_SIZE = new Dimension(46, 46);

  JButton button;
  String type; // tile type = 0...6.png
  int rule; // tile produce rule
  int count; // to fix around tile

  public Button(){ // init
    this.button = new JButton(new ImageIcon("Img/0.png"));
    button.setPreferredSize(new Dimension(BUTTON_SIZE));
  }
}
