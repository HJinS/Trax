import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SystemBar {
  private JPanel sysBtnPanel = new JPanel(); // Btn grid

  private JButton btnNew = new JButton("New");
  private JButton btnLoad = new JButton("Load");
  private JButton btnSave = new JButton("Save");
  private JButton btnOption = new JButton("Option");
  private JButton btnUndo = new JButton("Undo");
  private JButton btnRecord = new JButton("Record"); // system process button init

  // Sys
  private JTextField txtPathName; // JTextField: will help to define system path

  SystemBar(){

    btnRecord.setToolTipText("Record");
    btnUndo.setToolTipText("Undo");
    btnNew.setToolTipText("New");
    btnSave.setToolTipText("Save");
    btnLoad.setToolTipText("Load"); // add text at frame button

    sysBtnPanel.add(btnNew);
    sysBtnPanel.add(btnSave);
    sysBtnPanel.add(btnLoad);
    sysBtnPanel.add(btnOption);
    sysBtnPanel.add(btnUndo);
    sysBtnPanel.add(btnRecord);
  }

  JPanel returnBtnAttatch(){
    return sysBtnPanel;
  }
}
