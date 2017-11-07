package GUI;

import javax.swing.JOptionPane;

public class FinishPopUp {
  private int win;
  private JOptionPane winPane = new JOptionPane();
  private String winMessage = new String();
  
  public void getWhoWin(int win){
    this.win = win;
  }
  
  public void showWinner(){
    if(win == 1){
      winMessage = "White Win!!";
   	  winPane.showMessageDialog(null, winMessage);
	}else if(win == 2){
	  winMessage = "Black Win!!";
	  winPane.showMessageDialog(null, winMessage);
	}
  }
}

