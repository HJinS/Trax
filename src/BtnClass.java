import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.*;

public class BtnClass
{
	ImageIcon icon = new ImageIcon("img/0.png");
	Image changedImage;
	Image Image;
	private JButton [][]btn = new JButton[128][128];
	private Dimension btnSize = new Dimension(70,70);
	private Dimension ImageSize = new Dimension(70,70);
	private ImageIcon []Tile = new ImageIcon[6];
	private ImageIcon []tmpTile = new ImageIcon[6];
	private int x = 55,y = 55;//Tile을 두는 장소(btn[y][x]) default (55,55)
	private LinkedList<TileIndex> TileInfo = new LinkedList<TileIndex>();
	private int iconindex = 4;//icon 배열 index
	private int []Index_row = new int[127];//가로
	private int []Index_column = new int[127];//세로
	public BtnClass()
	{
		Image = icon.getImage();
		changedImage = Image.getScaledInstance(ImageSize.width,ImageSize.height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(changedImage);
		setTile();
		for(int i = 0;i < btn.length;i++){
			for(int j = 0;j < btn[0].length ;j++){
				btn[i][j] = new JButton();
			}
		}	
		for(int i = 0;i < 128;i++){
			for(int j = 0;j < 128;j++){
				btn[i][j].setPreferredSize(btnSize);
				btn[i][j].addMouseListener(new btnListener());
				btn[i][j].setIcon(icon);
				//btn[i][j].setBorderPainted(false);
				btn[i][j].setContentAreaFilled(false);
				btn[i][j].setFocusPainted(false);
				TraxBoard.panel.add(btn[i][j]);	
			}
		}
		TraxBoard.panel.addMouseListener(new btnListener());
	}
	void setTile()
	{
		Tile[0] = new ImageIcon("img/1.png");
		Tile[1] = new ImageIcon("img/2.png");
		Tile[2] = new ImageIcon("img/3.png");
		Tile[3] = new ImageIcon("img/4.png");
		Tile[4] = new ImageIcon("img/5.png");
		Tile[5] = new ImageIcon("img/6.png");
		
		tmpTile[0] = new ImageIcon("img/1_tmp.png");
		tmpTile[1] = new ImageIcon("img/2_tmp.png");
		tmpTile[2] = new ImageIcon("img/3_tmp.png");
		tmpTile[3] = new ImageIcon("img/4_tmp.png");
		tmpTile[4] = new ImageIcon("img/5_tmp.png");
		tmpTile[5] = new ImageIcon("img/6_tmp.png");
		
		for(int i = 0;i<6;i++){
			Image = Tile[i].getImage();
			changedImage = Image.getScaledInstance(ImageSize.width,ImageSize.height, Image.SCALE_SMOOTH);
			Tile[i] = new ImageIcon(changedImage);
		}
		for(int i = 0;i<6;i++){
			Image = tmpTile[i].getImage();
			changedImage = Image.getScaledInstance(ImageSize.width,ImageSize.height, Image.SCALE_SMOOTH);
			tmpTile[i] = new ImageIcon(changedImage);
		}
	}
	private boolean isEmpty()
	{
		if(TileInfo.isEmpty() == true)
			return true;
		else
			return false;
	}
	class btnListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{	
			if(!e.isMetaDown()){//왼쪽
				if(isEmpty() == true){
					if(iconindex == 1)
						iconindex = 4;
					else if(iconindex == 4)
						iconindex = 1;
					btn[y][x].setIcon(tmpTile[iconindex]);
				}
				else{
					Object o = e.getSource();
					for(int i = 0;i<128;i++){
						for(int j = 0;j<128;j++){
							if(o.equals(btn[i][j])){
								x = j;
								y = i;
								break;
							}
						}
					}
					int row = 0;
					int column = 0;
					for(int i = 0;i<TileInfo.size();i++){
						if(y == TileInfo.get(i).getY()){
							Index_row[row] = i;
							row++;
						}
						if(x == TileInfo.get(i).getX()){
							Index_row[column] = i;
							column++;
						}
					}
					for(int i = 0;i<row;i++){
						for(int j = i;j<row;j++){
							if(TileInfo.get(Index_row[i]).getX() > TileInfo.get(Index_row[j]).getX()){
								int tmp = Index_row[i];
								Index_row[i] = Index_row[j];
								Index_row[j] = tmp;
							}
						}
					}
					for(int i = 0;i<column;i++){
						for(int j = i;j<column;j++){
							if(TileInfo.get(Index_column[i]).getY() > TileInfo.get(Index_column[j]).getY()){
								int tmp = Index_column[i];
								Index_column[i] = Index_column[j];
								Index_column[j] = tmp;
							}
						}
					}
					if(column == 0 && row != 0){
						if(x < TileInfo.get(Index_row[0]).getX()){
							x = TileInfo.get(Index_row[0]).getX() - 1;
						}
						else if(x > TileInfo.get(Index_row[row]).getX()){
							x = TileInfo.get(Index_row[row]).getX() + 1;
						}
					}
					else if(row == 0 && column != 0){
						if(y < TileInfo.get(Index_column[0]).getY()){
							y = TileInfo.get(Index_column[0]).getY() - 1;
						}
						else if(y > TileInfo.get(Index_column[column]).getY()){
							y = TileInfo.get(Index_column[column]).getY() + 1;
						}
					}
					else if (row != 0 && column != 0){
						if(Math.abs(x - TileInfo.get(Index_row[0]).getX())
							<Math.abs(y -TileInfo.get(Index_column[0]).getY())){
							if(x < TileInfo.get(Index_row[0]).getX()){
								x = TileInfo.get(Index_row[0]).getX() - 1;
							}
							else if(x > TileInfo.get(Index_row[row]).getX()){
								x = TileInfo.get(Index_row[row]).getX() + 1;
							}
						}
						else if(Math.abs(x - TileInfo.get(Index_row[0]).getX())
								>Math.abs(y -TileInfo.get(Index_column[0]).getY())){
							if(y < TileInfo.get(Index_column[0]).getY()){
								y = TileInfo.get(Index_column[0]).getY() - 1;
							}
							else if(y > TileInfo.get(Index_column[column]).getY()){
								y = TileInfo.get(Index_column[column]).getY() + 1;
							}
						}
						else if(Math.abs(x - TileInfo.get(Index_row[0]).getX())
								==Math.abs(y -TileInfo.get(Index_column[0]).getY())){
							x = -1;
							y = -1;
							System.out.println("Invalid");
						}
					}
				}
			}
			else{//오른쪽
				btn[y][x].setIcon(Tile[iconindex]);
				TileInfo.add(new TileIndex(x,y,iconindex));
			}
		}

		@Override
		public void mousePressed(MouseEvent e){}

		@Override
		public void mouseReleased(MouseEvent e){}

		@Override
		public void mouseEntered(MouseEvent e){}

		@Override
		public void mouseExited(MouseEvent e){}
		
	}
}
