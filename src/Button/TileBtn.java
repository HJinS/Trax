package Button;

import javax.swing.JPanel;

public class TileBtn{
  BtnSetting btnSetting = new BtnSetting();
  ReturnPack[] returnInfo = new ReturnPack[128];

  public TileBtn() {}

  public void setBtn(JPanel panel){
    btnSetting.setBtn(panel);
    System.out.println(returnInfo);
  }
}
