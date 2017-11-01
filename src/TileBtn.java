import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TileBtn implements ActionListener, KeyListener, MouseListener {

   private JPanel tilePanel = new JPanel();

   // button & logic
   private JButton[][] btn = new JButton[128][128];
   private int[][] btnRule = new int[128][128];
   private String[][] tileType = new String[128][128];
   private int[][] btnCnt = new int[128][128];
   private boolean btnFix = true;
   private String str;
   private int count = 0;

   // tile icon
   private Image backImage;
   private Image changedImage;
   private Image tileImage;
   
   private ImageIcon backImageIcon = new ImageIcon("img/0.png");
   private ImageIcon tmp_tile1 = new ImageIcon("img/1_tmp.png");
   private ImageIcon tmp_tile2 = new ImageIcon("img/2_tmp.png");
   private ImageIcon tmp_tile3 = new ImageIcon("img/3_tmp.png");
   private ImageIcon tmp_tile4 = new ImageIcon("img/4_tmp.png");
   private ImageIcon tmp_tile5 = new ImageIcon("img/5_tmp.png");
   private ImageIcon tmp_tile6 = new ImageIcon("img/6_tmp.png");
   private ImageIcon tile1 = new ImageIcon("img/1.png");
   private ImageIcon tile2 = new ImageIcon("img/2.png");
   private ImageIcon tile3 = new ImageIcon("img/3.png");
   private ImageIcon tile4 = new ImageIcon("img/4.png");
   private ImageIcon tile5 = new ImageIcon("img/5.png");
   private ImageIcon tile6 = new ImageIcon("img/6.png");

   public TileBtn() {
      
	  tilePanel.setLayout(new GridLayout(128, 128));

	  backImage = backImageIcon.getImage();
	  changedImage = backImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	  backImageIcon = new ImageIcon(changedImage);
	  
	  setIconSize();
	  
      // button setting in tilePanel
      for (int i = 0; i < 128; i++) {
         for (int j = 0; j < 128; j++) {

            int command = 128 * i + j;
            String string = Integer.toString(command);
            btn[i][j] = new JButton(backImageIcon);
            btn[i][j].setActionCommand(string);
            btn[i][j].setPreferredSize(new Dimension(44, 44));
            btn[i][j].addActionListener(this);
            btn[i][j].setContentAreaFilled(false);
			btn[i][j].setFocusPainted(false);
			btn[i][j].setBorderPainted(false);
            btnRule[i][j] = 0;
            tileType[i][j] = "Empty";
            btnCnt[i][j] = 0;
            TraxUI.backPanel.add(btn[i][j]);
         }
      }
   }

   void setIconSize(){
	   tileImage = tmp_tile1.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tmp_tile1 = new ImageIcon(changedImage);
	   
	   tileImage = tmp_tile2.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tmp_tile2 = new ImageIcon(changedImage);
	   
	   tileImage = tmp_tile3.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tmp_tile3 = new ImageIcon(changedImage);
	   
	   tileImage = tmp_tile4.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tmp_tile4 = new ImageIcon(changedImage);
	   
	   tileImage = tmp_tile5.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tmp_tile5 = new ImageIcon(changedImage);
	   
	   tileImage = tmp_tile6.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tmp_tile6 = new ImageIcon(changedImage);
	   
	   tileImage = tile1.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tile1 = new ImageIcon(changedImage);
	   
	   tileImage = tile2.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tile2 = new ImageIcon(changedImage);
	   
	   tileImage = tile3.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tile3 = new ImageIcon(changedImage);
	   
	   tileImage = tile4.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tile4 = new ImageIcon(changedImage);
	   
	   tileImage = tile5.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tile5 = new ImageIcon(changedImage);
	   
	   tileImage = tile6.getImage();
	   changedImage = tileImage.getScaledInstance(70,70, Image.SCALE_SMOOTH);
	   tile6 = new ImageIcon(changedImage);
	   
   }
   
   
   
   @Override
   public void actionPerformed(ActionEvent e) {

      if (e.getSource() instanceof JButton) {
         if (e.getSource() instanceof JButton) {
            if (btnFix == false && str != e.getActionCommand()) {
               btn[Integer.parseInt(str) / 128][Integer.parseInt(str) % 128].setIcon(backImageIcon);
               btnCnt[Integer.parseInt(str) / 128][Integer.parseInt(str) % 128] = 0;
               tileType[Integer.parseInt(str) / 128][Integer.parseInt(str) % 128] = "Empty";
               btnRule[Integer.parseInt(str) / 128][Integer.parseInt(str) % 128] = 0;
            }
            str = e.getActionCommand();
            int x = Integer.parseInt(str) / 128;
            int y = Integer.parseInt(str) % 128;
            btn[x][y].addKeyListener(this);
            btn[x][y].addMouseListener(this);
            checkTile(x, y);
            drawTile(x, y, btnRule[x][y]);
            btnCnt[x][y]++;
            btnFix = false;
         }
      }

   }
   
   
   private void fixTile(int x, int y) {
      count++;
      btn[x][y].removeKeyListener(this);
      btn[x][y].setEnabled(false);

      if (tileType[x][y] == "NorthWest")
         btn[x][y].setDisabledIcon(tile1);
      else if (tileType[x][y] == "NorthEast")
         btn[x][y].setDisabledIcon(tile2);
      else if (tileType[x][y] == "EastSouth")
         btn[x][y].setDisabledIcon(tile3);
      else if (tileType[x][y] == "SouthWest")
         btn[x][y].setDisabledIcon(tile4);
      else if (tileType[x][y] == "EastWest")
         btn[x][y].setDisabledIcon(tile5);
      else if (tileType[x][y] == "NorthSouth")
         btn[x][y].setDisabledIcon(tile6);

      if (count == 1) {
         for (int j = 0; j < 128; j++) {
            for (int i = 0; i < 128; i++) {
               if (i == x && j == y)
                  continue;
               btn[i][j].setEnabled(false);
               btn[i][j].setDisabledIcon(backImageIcon);
            }
         }
      }
      if (btnCnt[x - 1][y] == 0)
         btn[x - 1][y].setEnabled(true);
      if (btnCnt[x + 1][y] == 0)
         btn[x + 1][y].setEnabled(true);
      if (btnCnt[x][y - 1] == 0)
         btn[x][y - 1].setEnabled(true);
      if (btnCnt[x][y + 1] == 0)
         btn[x][y + 1].setEnabled(true);
      autoTile();
      btnFix = true;
   }


   private void drawTile(int i, int j, int c) {
      if (btnRule[i][j] == 0) {
         switch (btnCnt[i][j] % 2) {
         case 0:
            btn[i][j].setIcon(tmp_tile5);
            tileType[i][j] = "EastWest";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile3);
            tileType[i][j] = "EastSouth";
            break;

         }
      } else if (btnRule[i][j] == 1) { // ¾Æ·¡ÂÊ white
         switch (btnCnt[i][j] % 3) {
         case 0:
            btn[i][j].setIcon(tmp_tile3);
            tileType[i][j] = "EastSouth";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile4);
            tileType[i][j] = "SouthWest";
            break;
         case 2:
            btn[i][j].setIcon(tmp_tile5);
            tileType[i][j] = "EastWest";
            break;
         }

      } else if (btnRule[i][j] == 2) { // À§ÂÊ black
         switch (btnCnt[i][j] % 3) {
         case 0:
            btn[i][j].setIcon(tmp_tile1);
            tileType[i][j] = "NorthWest";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile2);
            tileType[i][j] = "NorthEast";
            break;
         case 2:
            btn[i][j].setIcon(tmp_tile6);
            tileType[i][j] = "NorthSouth";
            break;
         }
      } else if (btnRule[i][j] == 3) { // ¿À¸¥ÂÊ white
         switch (btnCnt[i][j] % 3) {
         case 0:
            btn[i][j].setIcon(tmp_tile1);
            tileType[i][j] = "NorthWest";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile4);
            tileType[i][j] = "SouthWest";
            break;
         case 2:
            btn[i][j].setIcon(tmp_tile6);
            tileType[i][j] = "NorthSouth";
            break;
         }
      } else if (btnRule[i][j] == 4) { // ¿À¸¥ÂÊ black
         switch (btnCnt[i][j] % 3) {
         case 0:
            btn[i][j].setIcon(tmp_tile2);
            tileType[i][j] = "NorthEast";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile3);
            tileType[i][j] = "EastSouth";
            break;
         case 2:
            btn[i][j].setIcon(tmp_tile5);
            tileType[i][j] = "EastWest";
            break;
         }
      } else if (btnRule[i][j] == 5) { // ¾Æ·¡ÂÊ white
         switch (btnCnt[i][j] % 3) {
         case 0:
            btn[i][j].setIcon(tmp_tile1);
            tileType[i][j] = "NorthWest";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile2);
            tileType[i][j] = "NorthEast";
            break;
         case 2:
            btn[i][j].setIcon(tmp_tile5);
            tileType[i][j] = "EastWest";
            break;
         }
      } else if (btnRule[i][j] == 6) { // ¾Æ·¡ÂÊ black
         switch (btnCnt[i][j] % 3) {
         case 0:
            btn[i][j].setIcon(tmp_tile3);
            tileType[i][j] = "EastSouth";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile4);
            tileType[i][j] = "SouthWest";
            break;
         case 2:
            btn[i][j].setIcon(tmp_tile6);
            tileType[i][j] = "NorthSouth";
            break;
         }
      } else if (btnRule[i][j] == 7) { // ¿ÞÂÊ white
         switch (btnCnt[i][j] % 3) {
         case 0:
            btn[i][j].setIcon(tmp_tile2);
            tileType[i][j] = "NorthEast";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile3);
            tileType[i][j] = "EastSouth";
            break;
         case 2:
            btn[i][j].setIcon(tmp_tile6);
            tileType[i][j] = "NorthSouth";
            break;
         }
      } else if (btnRule[i][j] == 8) { // ¿ÞÂÊ black
         switch (btnCnt[i][j] % 3) {
         case 0:
            btn[i][j].setIcon(tmp_tile1);
            tileType[i][j] = "NorthWest";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile4);
            tileType[i][j] = "SouthWest";
            break;
         case 2:
            btn[i][j].setIcon(tmp_tile5);
            tileType[i][j] = "EastWest";
            break;
         }
      } else if (btnRule[i][j] == 9) { // À§white¿À¸¥black
         switch (btnCnt[i][j] % 2) {
         case 0:
            btn[i][j].setIcon(tmp_tile3);
            tileType[i][j] = "EastSouth";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile5);
            tileType[i][j] = "EastWest";
            break;
         }
      } else if (btnRule[i][j] == 10) { // À§black¿À¸¥white
         switch (btnCnt[i][j] % 2) {
         case 0:
            btn[i][j].setIcon(tmp_tile1);
            tileType[i][j] = "NorthWest";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile6);
            tileType[i][j] = "NorthSouth";
            break;
         }
      } else if (btnRule[i][j] == 11) { // À§white¿Þb
         switch (btnCnt[i][j] % 2) {
         case 0:
            btn[i][j].setIcon(tmp_tile4);
            tileType[i][j] = "SouthWest";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile5);
            tileType[i][j] = "EastWest";
            break;
         }
      } else if (btnRule[i][j] == 12) { // À§b¿Þw
         switch (btnCnt[i][j] % 2) {
         case 0:
            btn[i][j].setIcon(tmp_tile2);
            tileType[i][j] = "NorthEast";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile6);
            tileType[i][j] = "NorthSouth";
            break;
         }
      } else if (btnRule[i][j] == 13) { // ¹Øw¿À¸¥b
         switch (btnCnt[i][j] % 2) {
         case 0:
            btn[i][j].setIcon(tmp_tile2);
            tileType[i][j] = "NorthEast";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile5);
            tileType[i][j] = "EastWest";
            break;
         }
      } else if (btnRule[i][j] == 14) { // ¹Øb¿À¸¥w
         switch (btnCnt[i][j] % 2) {
         case 0:
            btn[i][j].setIcon(tmp_tile4);
            tileType[i][j] = "SouthWest";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile6);
            tileType[i][j] = "NorthSouth";
            break;
         }
      } else if (btnRule[i][j] == 15) { // ¹Øw¿Þb
         switch (btnCnt[i][j] % 2) {
         case 0:
            btn[i][j].setIcon(tmp_tile1);
            tileType[i][j] = "NorthWest";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile5);
            tileType[i][j] = "EastWest";
            break;
         }
      } else if (btnRule[i][j] == 16) { // ¹Øb¿Þw
         switch (btnCnt[i][j] % 2) {
         case 0:
            btn[i][j].setIcon(tmp_tile3);
            tileType[i][j] = "EastSouth";
            break;
         case 1:
            btn[i][j].setIcon(tmp_tile6);
            tileType[i][j] = "NorthSouth";
            break;
         }
      }

   }
   

   private void checkTile(int i, int j) {
      // Å¸ÀÏ ÇÑ°³ Á¢
      if ((tileType[i - 1][j] == "NorthWest" || tileType[i - 1][j] == "NorthEast" || tileType[i - 1][j] == "EastWest")
            && tileType[i + 1][j] == "Empty" && tileType[i][j - 1] == "Empty" && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 1;
      } else if ((tileType[i - 1][j] == "EastSouth" || tileType[i - 1][j] == "SouthWest"
            || tileType[i - 1][j] == "NorthSouth") && tileType[i + 1][j] == "Empty" && tileType[i][j - 1] == "Empty"
            && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 2;
      } else if (tileType[i - 1][j] == "Empty" && tileType[i + 1][j] == "Empty" && tileType[i][j - 1] == "Empty"
            && (tileType[i][j + 1] == "NorthEast" || tileType[i][j + 1] == "EastSouth"
                  || tileType[i][j + 1] == "NorthSouth")) {
         btnRule[i][j] = 3;
      } else if (tileType[i - 1][j] == "Empty" && tileType[i + 1][j] == "Empty" && tileType[i][j - 1] == "Empty"
            && (tileType[i][j + 1] == "NorthWest" || tileType[i][j + 1] == "SouthWest"
                  || tileType[i][j + 1] == "EastWest")) {
         btnRule[i][j] = 4;
      } else if (tileType[i - 1][j] == "Empty"
            && (tileType[i + 1][j] == "EastSouth" || tileType[i + 1][j] == "SouthWest"
                  || tileType[i + 1][j] == "EastWest")
            && tileType[i][j - 1] == "Empty" && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 5;
      } else if (tileType[i - 1][j] == "Empty"
            && (tileType[i + 1][j] == "NorthWest" || tileType[i + 1][j] == "NorthEast"
                  || tileType[i + 1][j] == "NorthSouth")
            && tileType[i][j - 1] == "Empty" && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 6;
      } else if (tileType[i - 1][j] == "Empty"
            && tileType[i + 1][j] == "Empty" && (tileType[i][j - 1] == "NorthWest"
                  || tileType[i][j - 1] == "SouthWest" || tileType[i][j - 1] == "NorthSouth")
            && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 7;
      } else if (tileType[i - 1][j] == "Empty"
            && tileType[i + 1][j] == "Empty" && (tileType[i][j - 1] == "NorthEast"
                  || tileType[i][j - 1] == "EastSouth" || tileType[i][j - 1] == "EastWest")
            && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 8;
      }

      // Å¸ÀÏ µÎ°³ Á¢
      else if ((tileType[i - 1][j] == "NorthWest" || tileType[i - 1][j] == "NorthEast"
            || tileType[i - 1][j] == "EastWest") && tileType[i + 1][j] == "Empty" && tileType[i][j - 1] == "Empty"
            && (tileType[i][j + 1] == "NorthWest" || tileType[i][j + 1] == "SouthWest"
                  || tileType[i][j + 1] == "EastWest")) {
         btnRule[i][j] = 9;
      } else if ((tileType[i - 1][j] == "EastSouth" || tileType[i - 1][j] == "SouthWest"
            || tileType[i - 1][j] == "NorthSouth") && tileType[i + 1][j] == "Empty" && tileType[i][j - 1] == "Empty"
            && (tileType[i][j + 1] == "NorthEast" || tileType[i][j + 1] == "EastSouth"
                  || tileType[i][j + 1] == "NorthSouth")) {
         btnRule[i][j] = 10;
      } else if ((tileType[i - 1][j] == "NorthWest" || tileType[i - 1][j] == "NorthEast"
            || tileType[i - 1][j] == "EastWest") && tileType[i + 1][j] == "Empty"
            && (tileType[i][j - 1] == "NorthEast" || tileType[i][j - 1] == "EastSouth"
                  || tileType[i][j - 1] == "EastWest")
            && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 11;
      } else if ((tileType[i - 1][j] == "EastSouth" || tileType[i - 1][j] == "SouthWest"
            || tileType[i - 1][j] == "NorthSouth") && tileType[i + 1][j] == "Empty"
            && (tileType[i][j - 1] == "NorthWest" || tileType[i][j - 1] == "SouthWest"
                  || tileType[i][j - 1] == "NorthSouth")
            && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 12;
      } else if (tileType[i - 1][j] == "Empty"
            && (tileType[i + 1][j] == "EastSouth" || tileType[i + 1][j] == "SouthWest"
                  || tileType[i + 1][j] == "EastWest")
            && tileType[i][j - 1] == "Empty" && (tileType[i][j + 1] == "NorthWest"
                  || tileType[i][j + 1] == "SouthWest" || tileType[i][j + 1] == "EastWest")) {
         btnRule[i][j] = 13;
      } else if (tileType[i - 1][j] == "Empty"
            && (tileType[i + 1][j] == "NorthWest" || tileType[i + 1][j] == "NorthEast"
                  || tileType[i + 1][j] == "NorthSouth")
            && tileType[i][j - 1] == "Empty" && (tileType[i][j + 1] == "NorthEast"
                  || tileType[i][j + 1] == "EastSouth" || tileType[i][j + 1] == "NorthSouth")) {
         btnRule[i][j] = 14;
      } else if (tileType[i - 1][j] == "Empty"
            && (tileType[i + 1][j] == "EastSouth" || tileType[i + 1][j] == "SouthWest"
                  || tileType[i + 1][j] == "EastWest")
            && (tileType[i][j - 1] == "NorthEast" || tileType[i][j - 1] == "EastSouth"
                  || tileType[i][j - 1] == "EastWest")
            && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 15;
      } else if (tileType[i - 1][j] == "Empty"
            && (tileType[i + 1][j] == "NorthWest" || tileType[i + 1][j] == "NorthEast"
                  || tileType[i + 1][j] == "NorthSouth")
            && (tileType[i][j - 1] == "NorthWest" || tileType[i][j - 1] == "SouthWest"
                  || tileType[i][j - 1] == "NorthSouth")
            && tileType[i][j + 1] == "Empty") {
         btnRule[i][j] = 16;
      }

   }
   
   private void autoTile() {
      for (int j = 0; j < 128; j++) {
         for (int i = 0; i < 128; i++) {
            if (btn[i][j].isEnabled() == false)
               continue;
            else {
               checkAutoTile(i, j);
            }
         }

      }

   }
   

   private void checkAutoTile(int i, int j) {
      if ((tileType[i - 1][j] == "NorthWest" || tileType[i - 1][j] == "NorthEast" || tileType[i - 1][j] == "EastWest")
            && (tileType[i][j + 1] == "NorthEast" || tileType[i][j + 1] == "EastSouth"
                  || tileType[i][j + 1] == "NorthSouth")) {
         tileType[i][j] = "SouthWest";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i - 1][j] == "NorthWest" || tileType[i - 1][j] == "NorthEast"
            || tileType[i - 1][j] == "EastWest")
            && (tileType[i][j - 1] == "NorthWest" || tileType[i][j - 1] == "SouthWest"
                  || tileType[i][j - 1] == "NorthSouth")) {
         tileType[i][j] = "EastSouth";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i + 1][j] == "EastSouth" || tileType[i + 1][j] == "SouthWest"
            || tileType[i + 1][j] == "EastWest")
            && (tileType[i][j + 1] == "NorthEast" || tileType[i][j + 1] == "EastSouth"
                  || tileType[i][j + 1] == "NorthSouth")) {
         tileType[i][j] = "NorthWest";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i + 1][j] == "EastSouth" || tileType[i + 1][j] == "SouthWest"
            || tileType[i + 1][j] == "EastWest")
            && (tileType[i][j - 1] == "NorthWest" || tileType[i][j - 1] == "SouthWest"
                  || tileType[i][j - 1] == "NorthSouth")) {
         tileType[i][j] = "NorthEast";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i - 1][j] == "EastSouth" || tileType[i - 1][j] == "SouthWest"
            || tileType[i - 1][j] == "NorthSouth")
            && (tileType[i][j + 1] == "NorthWest" || tileType[i][j + 1] == "SouthWest"
                  || tileType[i][j + 1] == "EastWest")) {
         tileType[i][j] = "NorthEast";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i - 1][j] == "EastSouth" || tileType[i - 1][j] == "SouthWest"
            || tileType[i - 1][j] == "NorthSouth")
            && (tileType[i][j - 1] == "NorthWest" || tileType[i][j - 1] == "EastSouth"
                  || tileType[i][j - 1] == "EastWest")) {
         tileType[i][j] = "NorthWest";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i + 1][j] == "NorthWest" || tileType[i + 1][j] == "NorthEast"
            || tileType[i + 1][j] == "NorthSouth")
            && (tileType[i][j + 1] == "NorthWest" || tileType[i][j + 1] == "SouthWest"
                  || tileType[i][j + 1] == "EastWest")) {
         tileType[i][j] = "EastSouth";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i + 1][j] == "NorthWest" || tileType[i + 1][j] == "NorthEast"
            || tileType[i + 1][j] == "NorthSouth")
            && (tileType[i][j - 1] == "NorthEast" || tileType[i][j - 1] == "EastSouth"
                  || tileType[i][j - 1] == "EastWest")) {
         tileType[i][j] = "SouthWest";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i - 1][j] == "NorthWest" || tileType[i - 1][j] == "NorthEast"
            || tileType[i - 1][j] == "EastWest")
            && (tileType[i + 1][j] == "EastSouth" || tileType[i + 1][j] == "SouthWest"
                  || tileType[i + 1][j] == "EastWest")) {
         tileType[i][j] = "EastWest";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i][j + 1] == "NorthEast" || tileType[i][j + 1] == "EastSouth"
            || tileType[i][j + 1] == "NorthSouth")
            && (tileType[i][j - 1] == "NorthWest" || tileType[i][j - 1] == "SouthWest"
                  || tileType[i][j - 1] == "NorthSouth")) {
         tileType[i][j] = "NorthSouth";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i - 1][j] == "EastSouth" || tileType[i - 1][j] == "SouthWest"
            || tileType[i - 1][j] == "NorthSouth")
            && (tileType[i + 1][j] == "NorthWest" || tileType[i + 1][j] == "NorthEast"
                  || tileType[i + 1][j] == "NorthSouth")) {
         tileType[i][j] = "NorthSouth";
         btnCnt[i][j]++;
         fixTile(i, j);
      } else if ((tileType[i][j + 1] == "NorthWest" || tileType[i][j + 1] == "SouthWest"
            || tileType[i][j + 1] == "EastWest")
            && (tileType[i][j - 1] == "NorthEast" || tileType[i][j - 1] == "EastSouth"
                  || tileType[i][j - 1] == "EastWest")) {
         tileType[i][j] = "EastWest";
         btnCnt[i][j]++;
         fixTile(i, j);
      }

   }

   @Override
   public void mouseClicked(MouseEvent e) {
      if (e.getButton() == 3) {
         fixTile(Integer.parseInt(str) / 128, Integer.parseInt(str) % 128);
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
         fixTile(Integer.parseInt(str) / 128, Integer.parseInt(str) % 128);

      }
      
   }

   @Override
   public void keyTyped(KeyEvent arg0) {
      // TODO Auto-generated method stub
      
   }
   
   JPanel returnTileBtn() {
      return tilePanel;
      
   }

}
