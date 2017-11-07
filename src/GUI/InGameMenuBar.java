package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InGameMenuBar {

  private Dimension PANEL_SIZE = new Dimension(5632, 5632); // dimension has two field height, width data structure

  private JPanel inGameMenuBar = new JPanel(); // Btn grid

  private JButton btnNew = new JButton("New");
  private JButton btnLoad = new JButton("Load");
  private JButton btnSave = new JButton("Save");
  private JButton btnOption = new JButton("Option");
  private JButton btnUndo = new JButton("Undo");
  private JButton btnRecord = new JButton("Record"); // system process button init

  // Sys
  private JTextField txtPathName; // JTextField: will help to define system path

  public InGameMenuBar(){
    btnRecord.setToolTipText("Record");
    btnUndo.setToolTipText("Undo");
    btnNew.setToolTipText("New");
    btnSave.setToolTipText("Save");
    btnLoad.setToolTipText("Load"); // add text at frame button

    inGameMenuBar.add(btnNew);
    inGameMenuBar.add(btnSave);
    inGameMenuBar.add(btnLoad);
    inGameMenuBar.add(btnOption);
    inGameMenuBar.add(btnUndo);
    inGameMenuBar.add(btnRecord);
  }

  JPanel returnInGameMenuBar(){
    return inGameMenuBar;
  }

  void setInGameMenuBar(JFrame frame, JPanel panel){
    frame.add(inGameMenuBar, BorderLayout.NORTH);
  }

}
