package FindPath;

import GUI.FinishPopUp;
import GUI.GameStatusBar;

public class FindPath{
  FinishPopUp finishPopUp;
  GameStatusBar gameStatusBar;

  int sessX;
  int sessY; // find overlap

  int sessRule; // rule == 0 => win, rule %2 == 0 => white rule, rule %2 == 1 => black rule
  int addedCount; // we can know turn about this value to divide by %2 and count up for index
  int EWCounter;

  int whoWin;
  boolean whoseTurn;

  public FindPath(){
    this.EWCounter = 0;
    this.whoWin = 0;
    this.addedCount = 0;
    this.finishPopUp = new FinishPopUp();
    this.gameStatusBar = new GameStatusBar();
  } // no init

  public void findPath(String type, int rule, int x, int y){
    /* use session to find overlap
    * */
    if(sessX == x && sessY == y){
      // if function has overlap arg, will pass
    }else {

      if(type == "EastWest"){
        this.EWCounter ++;
      }

      this.sessX = x;
      this.sessY = y; // no overlap => save index x,y

      if (addedCount % 2 == 0){ // find turn
        whoseTurn = true; // white turn
      }else {
        whoseTurn = false; // black turn
      }
      if (addedCount != 0 && rule == 0) { // win rule
        if (sessRule % 2 == 0) { // who win,find with session rule
          whoWin = 1; //  white win
        } else {
          whoWin = 2; // black win
        }
      } else {
        if(EWCounter == 4){
          whoWin = 1;
        }else{
          this.sessRule = rule; // if rule is not 0, save rule data at session
        }
      }

      finishPopUp.getWhoWin(whoWin);
      finishPopUp.showWinner();
      gameStatusBar.getWhoesTurn(whoseTurn);

      addedCount++;
    }
  }
}
