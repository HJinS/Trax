package GUI;

import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollBar {
  private Point CAMERA_POS = new Point(3400, 3550);

  private JScrollPane scrollBar;

  public ScrollBar(){
    scrollBar = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // JSrollPane: to make scroll bar
  }

  void setScrollBar(JFrame frame, JPanel panel){
    scrollBar.setViewportView(panel);
    scrollBar.getViewport().setViewPosition(CAMERA_POS);

    frame.add(scrollBar, BorderLayout.CENTER);
  }
}
