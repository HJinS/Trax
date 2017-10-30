import java.awt.Color;
import java.awt.event.InputEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Menu
{
	JMenu menu1,menu2,menu3,menu4,menu5,zoom,Timerview,Background,PVPmode;
	JMenuBar menuBar;
	JMenuItem SaveGame,LoadGame,AutoSave,LoadOption,SaveOption,Exit,Computermode,Rule,Version,
			  NewGame,zoomin,zoomout,Timervis,Timerunvis,Grass,Grassland,Rank,Normal;
	
	Menu()
	{
		menuBar = new JMenuBar();
		menu1 = new JMenu("File(F)");
		NewGame= new JMenuItem("New Game(N)");
		menu1.add(NewGame);
		LoadGame = new JMenuItem("Load Game(O)");
		menu1.add(LoadGame);
		SaveGame = new JMenuItem("Save Game(S)");
		menu1.add(SaveGame);
		AutoSave = new JMenuItem("Auto Save");
		menu1.add(AutoSave);
		LoadOption = new JMenuItem("Load Option");
		menu1.add(LoadOption);
		SaveOption = new JMenuItem("Save Option");
		menu1.add(SaveOption);
		Exit = new JMenuItem("Exit");
		menu1.add(Exit);
		menuBar.add(menu1);

		menu2 = new JMenu("EDIT(E)"); //'파일'이라는 메뉴를 생성T
		Timerview = new JMenu("Timer");
		Timervis= new JMenuItem("Timer visable");
		Timerunvis = new JMenuItem("Timer unvisable");
		Timerview.add(Timervis);
		Timerview.add(Timerunvis);
		menu2.add(Timerview);
		Background = new JMenu("Background");
		Grass = new JMenuItem("Grass");
		Grassland = new JMenuItem("Grass Land");
		Background.add(Grass);
		Background.add(Grassland);
		menu2.add(Background);
		zoom = new JMenu("zoom");
		zoomout = new JMenuItem("zoom out");
		zoomin = new JMenuItem("zoom in");
		zoom.add(zoomin);
		zoom.add(zoomout);
		menu2.add(zoom);
		menuBar.add(menu2); //메뉴바에 '파일'이라는 메뉴를 추가
		
		menu3 = new JMenu("Network(N)"); //'파일'이라는 메뉴를 생성
		PVPmode = new JMenu("PVPmode");
		Normal = new JMenuItem("Normal");
		Rank = new JMenuItem("Rank");
		PVPmode.add("Normal");
		PVPmode.add("Rank");
		menu3.add(PVPmode);
		menu3.add( new JMenuItem("Computer mode"));
		menuBar.add(menu3);

		menu4 = new JMenu("Help(H)"); //'파일'이라는 메뉴를 생성
		Version = new JMenuItem("Version");
		menu4.add(Version);
		Rule = new JMenuItem("Rule");
		menu4.add(Rule);
		menuBar.add(menu4); //메뉴바에 '파일'이라는 메뉴를 추가
		
		menu1.setMnemonic('F');
		menu2.setMnemonic('E');
		menu3.setMnemonic('N');
		menu4.setMnemonic('H');
		
		NewGame.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_MASK));
		LoadGame.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_MASK));
		SaveGame.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_MASK));
		  
		menuBar.setBorder(BorderFactory.createLineBorder(Color.gray)); //메뉴바 색상 지정
	}
}
