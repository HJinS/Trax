import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class TraxUI {
  /*
   * This class is about whole project. so, should be changed class name
   */
  // Constant
  private Dimension PANEL_SIZE = new Dimension(9000, 9000); // dimension has two field height, width data structure
  private Point VIEW_POS = new Point(3400, 3550); // user view point(about scroll)

  // GUI
  static JFrame frame = new JFrame(); // JFrame: will help to make window (sketch book)
  public static JPanel backPanel = new JPanel(); // Background panel, JPanel: to add panel in GUI
  private JScrollPane scrollBar = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
      JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // JSrollPane: to make scroll bar

  // else
  private SystemBar systemBar = new SystemBar();
  //private Menu menu = new Menu(); // frame menu class
  //private BtnClass Btn;
  
  private TileBtn btn;

  public TraxUI() {

    setSysBar();
    setScroll(); // set scroll bar and setting

//    Btn = new BtnClass(); // make button
    MenuBar menubar = new MenuBar();
    frame.add(scrollBar, BorderLayout.CENTER);
    
    //frame.setJMenuBar(menu.menuBar);
    btn = new TileBtn();
    
    frame.setSize(1000, 900);
    frame.setVisible(true);
   }

  void setSysBar() {
	frame.add(systemBar.returnBtnAttatch(),BorderLayout.NORTH);// add frame button
    backPanel.setLayout(new GridLayout(128, 128));
    backPanel.setPreferredSize(PANEL_SIZE); // set grid at panel
  }

  void setScroll() {
    scrollBar.setViewportView(backPanel);
    scrollBar.getViewport().setViewPosition(VIEW_POS);
  }
}
