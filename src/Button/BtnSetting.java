package Button;

import FindPath.FindPath;
import GUI.GameStatusBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BtnSetting implements ActionListener, KeyListener, MouseListener {
  private Button[][] btn = new Button[128][128]; // button val

  private FindPath findPath = new FindPath();
  private boolean sessionBtnFix = true;
  private String sessionActionCommand;
  private int count = 0;

  // tile icon

  private ImageIcon tmp_tile1 = new ImageIcon("Img/1_tmp.png");
  private ImageIcon tmp_tile2 = new ImageIcon("Img/2_tmp.png");
  private ImageIcon tmp_tile3 = new ImageIcon("Img/3_tmp.png");
  private ImageIcon tmp_tile4 = new ImageIcon("Img/4_tmp.png");
  private ImageIcon tmp_tile5 = new ImageIcon("Img/5_tmp.png");
  private ImageIcon tmp_tile6 = new ImageIcon("Img/6_tmp.png");
  private ImageIcon tile1 = new ImageIcon("Img/1.png");
  private ImageIcon tile2 = new ImageIcon("Img/2.png");
  private ImageIcon tile3 = new ImageIcon("Img/3.png");
  private ImageIcon tile4 = new ImageIcon("Img/4.png");
  private ImageIcon tile5 = new ImageIcon("Img/5.png");
  private ImageIcon tile6 = new ImageIcon("Img/6.png");


  public BtnSetting(){

  }

  void setBtn(JPanel panel){
    for(int i_idx = 0; i_idx < 128; i_idx++){
      for(int j_idx = 0; j_idx < 128; j_idx++) {
        // init each button
        btn[i_idx][j_idx] = new Button();

        // access at idx with division by 128
        int idxConst = 128 * i_idx + j_idx;
        String command = Integer.toString(idxConst);
        // add action listener(-> actionPerformed)
        btn[i_idx][j_idx].button.setActionCommand(command);
        btn[i_idx][j_idx].button.addActionListener(this);

        btn[i_idx][j_idx].rule =0;
        btn[i_idx][j_idx].type = "Empty";
        btn[i_idx][j_idx].count = 0;

        // add btn at panel
        panel.add(btn[i_idx][j_idx].button);
      }
    }
  }

  public Button[][] returnBtn(){
    return btn;
  }

  @Override
  public void actionPerformed(ActionEvent e){
    // click event occur
    if(e.getSource() instanceof JButton){
      if (sessionBtnFix == false && sessionActionCommand != e.getActionCommand()) {
        btn[Integer.parseInt(sessionActionCommand) / 128][Integer.parseInt(sessionActionCommand) % 128].button.setIcon(new ImageIcon("Img/0.png"));
        btn[Integer.parseInt(sessionActionCommand) / 128][Integer.parseInt(sessionActionCommand) % 128].count = 0;
        btn[Integer.parseInt(sessionActionCommand) / 128][Integer.parseInt(sessionActionCommand) % 128].type = "Empty";
        btn[Integer.parseInt(sessionActionCommand) / 128][Integer.parseInt(sessionActionCommand) % 128].rule = 0;
      } // when press non target btn, will be not changed

      sessionActionCommand = e.getActionCommand(); // getActionCommand return the each command, so we can calc idx
      // decode to access at index
      int x = Integer.parseInt(sessionActionCommand) / 128;
      int y = Integer.parseInt(sessionActionCommand) % 128;

      btn[x][y].button.addKeyListener(this);
      btn[x][y].button.addMouseListener(this);

      // when press target btn, btn will be changed by rule

      tileRule(x, y);
      setTile(x, y, btn[x][y].rule);
      btn[x][y].count++;

      sessionBtnFix = false;
    }
  }
  private void tileRule(int i, int j) {
    // If there is a single tile around the center tile

    // up : black
    if ((btn[i-1][j].type == "EastSouth" || btn[i-1][j].type == "SouthWest"
        || btn[i-1][j].type == "NorthSouth") && btn[i+1][j].type == "Empty" && btn[i][j-1].type == "Empty"
        && btn[i][j+1].type == "Empty") {
      btn[i][j].rule = 1;
    }
    // up :white
    else if ((btn[i-1][j].type == "NorthWest" || btn[i-1][j].type == "NorthEast"
        || btn[i-1][j].type == "EastWest") && btn[i+1][j].type == "Empty" && btn[i][j-1].type == "Empty"
        && btn[i][j+1].type == "Empty") {
      btn[i][j].rule = 2;
    }
    // right : black
    else if (btn[i-1][j].type == "Empty" && btn[i+1][j].type == "Empty" && btn[i][j-1].type == "Empty"
        && (btn[i][j+1].type == "NorthWest" || btn[i][j+1].type == "SouthWest"
        || btn[i][j+1].type == "EastWest")) {
      btn[i][j].rule = 3;
    }
    // right : white
    else if (btn[i-1][j].type == "Empty" && btn[i+1][j].type == "Empty" && btn[i][j-1].type == "Empty"
        && (btn[i][j+1].type == "NorthEast" || btn[i][j+1].type == "EastSouth"
        || btn[i][j+1].type == "NorthSouth")) {
      btn[i][j].rule = 4;
    }
    // down :black
    else if (btn[i-1][j].type == "Empty" && btn[i][j-1].type == "Empty" && btn[i][j+1].type == "Empty"
        && (btn[i+1][j].type == "NorthWest" || btn[i+1][j].type == "NorthEast"
        || btn[i+1][j].type == "NorthSouth")) {
      btn[i][j].rule = 5;
    }
    // down : white
    else if (btn[i-1][j].type == "Empty" && btn[i][j-1].type == "Empty" && btn[i][j+1].type == "Empty"
        && (btn[i+1][j].type == "EastSouth" || btn[i+1][j].type == "SouthWest"
        || btn[i+1][j].type == "EastWest")) {
      btn[i][j].rule = 6;
    }
    // left : black
    else if (btn[i-1][j].type == "Empty" && btn[i+1][j].type == "Empty" && btn[i][j+1].type == "Empty"
        && (btn[i][j-1].type == "NorthEast" || btn[i][j-1].type == "EastSouth"
        || btn[i][j-1].type == "EastWest")) {
      if (btn[i][j-3].type == "EastWest" && btn[i][j-2].type == "EastWest"
          && btn[i][j-1].type == "EastWest" && btn[i][j].type == "EastWest") {
        btn[i][j].rule = 17;
      }else{
        btn[i][j].rule = 7;
      }
    }
    // left : white
    else if (btn[i-1][j].type == "Empty" && btn[i+1][j].type == "Empty" && btn[i][j+1].type == "Empty"
        && (btn[i][j-1].type == "NorthWest" || btn[i][j-1].type == "SouthWest"
        || btn[i][j-1].type == "NorthSouth")) {
      btn[i][j].rule = 8;
    }

    // If there is two tile around the center tile

    // up :white , right : black
    else if ((btn[i-1][j].type == "NorthWest" || btn[i-1][j].type == "NorthEast"
        || btn[i-1][j].type == "EastWest")
        && (btn[i][j+1].type == "NorthWest" || btn[i][j+1].type == "SouthWest"
        || btn[i][j+1].type == "EastWest")
        && btn[i+1][j].type == "Empty" && btn[i][j-1].type == "Empty") {
      btn[i][j].rule = 9;
    }
    // up : black , right :white
    else if ((btn[i-1][j].type == "EastSouth" || btn[i-1][j].type == "SouthWest"
        || btn[i-1][j].type == "NorthSouth")
        && (btn[i][j+1].type == "NorthEast" || btn[i][j+1].type == "EastSouth"
        || btn[i][j+1].type == "NorthSouth")
        && btn[i+1][j].type == "Empty" && btn[i][j-1].type == "Empty") {
      btn[i][j].rule = 10;
    }
    // right : white , down : black
    else if ((btn[i+1][j].type == "EastSouth" || btn[i+1][j].type == "SouthWest"
        || btn[i+1][j].type == "EastWest")
        && (btn[i][j-1].type == "NorthEast" || btn[i][j-1].type == "EastSouth"
        || btn[i][j-1].type == "EastWest")
        && btn[i-1][j].type == "Empty" && btn[i][j+1].type == "Empty") {
      btn[i][j].rule = 11;
    }
    // right : black, down : white
    else if ((btn[i+1][j].type == "NorthWest" || btn[i+1][j].type == "NorthEast"
        || btn[i+1][j].type == "NorthSouth")
        && (btn[i][j-1].type == "NorthWest" || btn[i][j-1].type == "SouthWest"
        || btn[i][j-1].type == "NorthSouth")
        && btn[i-1][j].type == "Empty" && btn[i][j+1].type == "Empty") {
      btn[i][j].rule = 12;
    }
    // down : white , left : black
    else if ((btn[i-1][j].type == "EastSouth" || btn[i-1][j].type == "SouthWest"
        || btn[i-1][j].type == "NorthSouth")
        && (btn[i][j-1].type == "NorthWest" || btn[i][j-1].type == "SouthWest"
        || btn[i][j-1].type == "NorthSouth")
        && btn[i+1][j].type == "Empty" && btn[i][j+1].type == "Empty") {
      btn[i][j].rule = 13;
    }
    // down : black , left : white
    else if ((btn[i-1][j].type == "NorthWest" || btn[i-1][j].type == "NorthEast"
        || btn[i-1][j].type == "EastWest")
        && (btn[i][j-1].type == "NorthEast" || btn[i][j-1].type == "EastSouth"
        || btn[i][j-1].type == "EastWest")
        && btn[i+1][j].type == "Empty" && btn[i][j+1].type == "Empty") {
      btn[i][j].rule = 14;
    }
    // left : white , up : black
    else if ((btn[i+1][j].type == "NorthWest" || btn[i+1][j].type == "NorthEast"
        || btn[i+1][j].type == "NorthSouth")
        && (btn[i][j+1].type == "NorthEast" || btn[i][j+1].type == "EastSouth"
        || btn[i][j+1].type == "NorthSouth")
        && btn[i-1][j].type == "Empty" && btn[i][j-1].type == "Empty") {
      btn[i][j].rule = 15;
    }
    // left : black , up : white
    else if ((btn[i+1][j].type == "EastSouth" || btn[i+1][j].type == "SouthWest"
        || btn[i+1][j].type == "EastWest")
        && (btn[i][j+1].type == "NorthWest"
        || btn[i][j+1].type == "SouthWest" || btn[i][j+1].type == "EastWest")
        &&btn[i-1][j].type == "Empty"&& btn[i][j-1].type == "Empty") {
      btn[i][j].rule = 16;
    }
  }

  private void setTile(int i, int j, int c) {

    if (btn[i][j].rule == 0) {
      switch (btn[i][j].count % 2) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile5);
          btn[i][j].type = "EastWest";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile3);
          btn[i][j].type = "EastSouth";
          break;

      }
    } else if (btn[i][j].rule == 1) {
      switch (btn[i][j].count % 3) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile1);
          btn[i][j].type = "NorthWest";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile2);
          btn[i][j].type = "NorthEast";
          break;
        case 2:
          btn[i][j].button.setIcon(tmp_tile6);
          btn[i][j].type = "NorthSouth";
          break;
      }
    } else if (btn[i][j].rule == 2) {
      switch (btn[i][j].count % 3) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile3);
          btn[i][j].type = "EastSouth";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile4);
          btn[i][j].type = "SouthWest";
          break;
        case 2:
          btn[i][j].button.setIcon(tmp_tile5);
          btn[i][j].type = "EastWest";
          break;
      }
    } else if (btn[i][j].rule == 3) {
      switch (btn[i][j].count % 3) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile2);
          btn[i][j].type = "NorthEast";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile3);
          btn[i][j].type = "EastSouth";
          break;
        case 2:
          btn[i][j].button.setIcon(tmp_tile5);
          btn[i][j].type = "EastWest";
          break;
      }
    } else if (btn[i][j].rule == 4) {
      switch (btn[i][j].count % 3) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile4);
          btn[i][j].type = "SouthWest";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile1);
          btn[i][j].type = "NorthWest";
          break;
        case 2:
          btn[i][j].button.setIcon(tmp_tile6);
          btn[i][j].type = "NorthSouth";
          break;
      }
    } else if (btn[i][j].rule == 5) {
      switch (btn[i][j].count % 3) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile3);
          btn[i][j].type = "EastSouth";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile4);
          btn[i][j].type = "SouthWest";
          break;
        case 2:
          btn[i][j].button.setIcon(tmp_tile6);
          btn[i][j].type = "NorthSouth";
          break;
      }
    } else if (btn[i][j].rule == 6) {
      switch (btn[i][j].count % 3) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile1);
          btn[i][j].type = "NorthWest";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile2);
          btn[i][j].type = "NorthEast";
          break;
        case 2:
          btn[i][j].button.setIcon(tmp_tile5);
          btn[i][j].type = "EastWest";
          break;
      }
    } else if (btn[i][j].rule == 7) {
      switch (btn[i][j].count % 3) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile1);
          btn[i][j].type = "NorthWest";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile4);
          btn[i][j].type = "SouthWest";
          break;
        case 2:
          btn[i][j].button.setIcon(tmp_tile5);
          btn[i][j].type = "EastWest";
          break;
      }
    } else if (btn[i][j].rule == 8) {
      switch (btn[i][j].count % 3) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile3);
          btn[i][j].type = "EastSouth";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile2);
          btn[i][j].type = "NorthEast";
          break;
        case 2:
          btn[i][j].button.setIcon(tmp_tile6);
          btn[i][j].type = "NorthSouth";
          break;
      }
    }

    else if (btn[i][j].rule == 9) {
      switch (btn[i][j].count % 2) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile3);
          btn[i][j].type = "EastSouth";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile5);
          btn[i][j].type = "EastWest";
          break;
      }
    } else if (btn[i][j].rule == 10) {
      switch (btn[i][j].count % 2) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile1);
          btn[i][j].type = "NorthWest";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile6);
          btn[i][j].type = "NorthSouth";
          break;
      }
    } else if (btn[i][j].rule == 11) {
      switch (btn[i][j].count % 2) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile1);
          btn[i][j].type = "NorthWest";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile5);
          btn[i][j].type = "EastWest";
          break;
      }
    } else if (btn[i][j].rule == 12) {
      switch (btn[i][j].count % 2) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile3);
          btn[i][j].type = "EastSouth";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile6);
          btn[i][j].type = "NorthSouth";
          break;
      }
    } else if (btn[i][j].rule == 13) {
      switch (btn[i][j].count % 2) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile2);
          btn[i][j].type = "NorthEast";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile6);
          btn[i][j].type = "NorthSouth";
          break;
      }
    } else if (btn[i][j].rule == 14) {
      switch (btn[i][j].count % 2) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile4);
          btn[i][j].type = "SouthWest";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile5);
          btn[i][j].type = "EastWest";
          break;
      }
    } else if (btn[i][j].rule == 15) {
      switch (btn[i][j].count % 2) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile4);
          btn[i][j].type = "SouthWest";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile6);
          btn[i][j].type = "NorthSouth";
          break;
      }
    } else if (btn[i][j].rule == 16) {
      switch (btn[i][j].count % 2) {
        case 0:
          btn[i][j].button.setIcon(tmp_tile2);
          btn[i][j].type = "NorthEast";
          break;
        case 1:
          btn[i][j].button.setIcon(tmp_tile5);
          btn[i][j].type = "EastWest";
          break;
      }
    }

  }

  private void fixTile(int x, int y) {
    count++;
    btn[x][y].button.removeKeyListener(this);
    btn[x][y].button.setEnabled(false);

    if (btn[x][y].type == "NorthWest"){
      btn[x][y].button.setDisabledIcon(tile1);
      findPath.findPath(btn[x][y].type, btn[x][y].rule,x, y);
    }
    else if (btn[x][y].type == "NorthEast"){
      btn[x][y].button.setDisabledIcon(tile2);
      findPath.findPath(btn[x][y].type, btn[x][y].rule,x, y);
    }
    else if (btn[x][y].type == "EastSouth"){
      btn[x][y].button.setDisabledIcon(tile3);
      findPath.findPath(btn[x][y].type, btn[x][y].rule,x, y);
    }
    else if (btn[x][y].type == "SouthWest"){
      btn[x][y].button.setDisabledIcon(tile4);
      findPath.findPath(btn[x][y].type, btn[x][y].rule,x, y);
    }
    else if (btn[x][y].type == "EastWest"){
      btn[x][y].button.setDisabledIcon(tile5);
      findPath.findPath(btn[x][y].type, btn[x][y].rule,x, y);
    }
    else if (btn[x][y].type == "NorthSouth"){
      btn[x][y].button.setDisabledIcon(tile6);
      findPath.findPath(btn[x][y].type, btn[x][y].rule,x, y);
    }

    if (count == 1) {
      for (int j = 0; j < 128; j++) {
        for (int i = 0; i < 128; i++) {
          if (i == x && j == y)
            continue;
          btn[i][j].button.setEnabled(false); // Button unavailable
          btn[i][j].button.setDisabledIcon(new ImageIcon("Img/0.png"));
        }
      }
    }
    // Button available (up, down, left , right)
    if (btn[x - 1][y].count == 0)
      btn[x - 1][y].button.setEnabled(true);
    if (btn[x + 1][y].count == 0)
      btn[x + 1][y].button.setEnabled(true);
    if (btn[x][y - 1].count == 0)
      btn[x][y - 1].button.setEnabled(true);
    if (btn[x][y + 1].count == 0)
      btn[x][y + 1].button.setEnabled(true);

    autoTile();

    sessionBtnFix = true;
  }

  private void autoTile() {
    for (int j = 0; j < 128; j++) {
      for (int i = 0; i < 128; i++) {
        if (btn[i][j].button.isEnabled() == false)
          continue;
        else {
          autoTileRule(i, j);
        }
      }

    }
  }

  private void autoTileRule(int i, int j) {
    if ((btn[i - 1][j].type == "NorthWest" || btn[i - 1][j].type == "NorthEast" || btn[i - 1][j].type == "EastWest")
        && (btn[i][j + 1].type == "NorthEast" || btn[i][j + 1].type == "EastSouth"
        || btn[i][j + 1].type == "NorthSouth")) {
      btn[i][j].type = "SouthWest";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i - 1][j].type == "NorthWest" || btn[i - 1][j].type == "NorthEast"
        || btn[i - 1][j].type == "EastWest")
        && (btn[i][j - 1].type == "NorthWest" || btn[i][j - 1].type == "SouthWest"
        || btn[i][j - 1].type == "NorthSouth")) {
      btn[i][j].type = "EastSouth";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i + 1][j].type == "EastSouth" || btn[i + 1][j].type == "SouthWest"
        || btn[i + 1][j].type == "EastWest")
        && (btn[i][j + 1].type == "NorthEast" || btn[i][j + 1].type == "EastSouth"
        || btn[i][j + 1].type == "NorthSouth")) {
      btn[i][j].type = "NorthWest";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i + 1][j].type == "EastSouth" || btn[i + 1][j].type == "SouthWest"
        || btn[i + 1][j].type == "EastWest")
        && (btn[i][j - 1].type == "NorthWest" || btn[i][j - 1].type == "SouthWest"
        || btn[i][j - 1].type == "NorthSouth")) {
      btn[i][j].type = "NorthEast";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i - 1][j].type == "EastSouth" || btn[i - 1][j].type == "SouthWest"
        || btn[i - 1][j].type == "NorthSouth")
        && (btn[i][j + 1].type == "NorthWest" || btn[i][j + 1].type == "SouthWest"
        || btn[i][j + 1].type == "EastWest")) {
      btn[i][j].type = "NorthEast";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i - 1][j].type == "EastSouth" || btn[i - 1][j].type == "SouthWest"
        || btn[i - 1][j].type == "NorthSouth")
        && (btn[i][j - 1].type == "NorthEast" || btn[i][j - 1].type == "EastSouth"
        || btn[i][j - 1].type == "EastWest")) {
      btn[i][j].type = "NorthWest";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i + 1][j].type == "NorthWest" || btn[i + 1][j].type == "NorthEast"
        || btn[i + 1][j].type == "NorthSouth")
        && (btn[i][j + 1].type == "NorthWest" || btn[i][j + 1].type == "SouthWest"
        || btn[i][j + 1].type == "EastWest")) {
      btn[i][j].type = "EastSouth";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i + 1][j].type == "NorthWest" || btn[i + 1][j].type == "NorthEast"
        || btn[i + 1][j].type == "NorthSouth")
        && (btn[i][j - 1].type == "NorthEast" || btn[i][j - 1].type == "EastSouth"
        || btn[i][j - 1].type == "EastWest")) {
      btn[i][j].type = "SouthWest";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i - 1][j].type == "NorthWest" || btn[i - 1][j].type == "NorthEast"
        || btn[i - 1][j].type == "EastWest")
        && (btn[i + 1][j].type == "EastSouth" || btn[i + 1][j].type == "SouthWest"
        || btn[i + 1][j].type == "EastWest")) {
      btn[i][j].type = "EastWest";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i][j + 1].type == "NorthEast" || btn[i][j + 1].type == "EastSouth"
        || btn[i][j + 1].type == "NorthSouth")
        && (btn[i][j - 1].type == "NorthWest" || btn[i][j - 1].type == "SouthWest"
        || btn[i][j - 1].type == "NorthSouth")) {
      btn[i][j].type = "NorthSouth";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i - 1][j].type == "EastSouth" || btn[i - 1][j].type == "SouthWest"
        || btn[i - 1][j].type == "NorthSouth")
        && (btn[i + 1][j].type == "NorthWest" || btn[i + 1][j].type == "NorthEast"
        || btn[i + 1][j].type == "NorthSouth")) {
      btn[i][j].type = "NorthSouth";
      btn[i][j].count++;
      fixTile(i, j);
    } else if ((btn[i][j + 1].type == "NorthWest" || btn[i][j + 1].type == "SouthWest"
        || btn[i][j + 1].type == "EastWest")
        && (btn[i][j - 1].type == "NorthEast" || btn[i][j - 1].type == "EastSouth"
        || btn[i][j - 1].type == "EastWest")) {
      btn[i][j].type = "EastWest";
      btn[i][j].count++;
      fixTile(i, j);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == 3) {
      fixTile(Integer.parseInt(sessionActionCommand) / 128, Integer.parseInt(sessionActionCommand) % 128);
    }

  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      int x = Integer.parseInt(sessionActionCommand) / 128;
      int y = Integer.parseInt(sessionActionCommand) % 128;
      fixTile(x, y);
      
    }

  }

  @Override
  public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }

}
