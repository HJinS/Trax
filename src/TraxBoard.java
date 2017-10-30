import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;

public class TraxBoard extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JFrame frame = new JFrame();
	private JTextField txtPathName;
	public static JPanel panel = new JPanel();
	private JPanel BtnPanel = new JPanel();
	private JPanel board = new JPanel();
	
	private JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private Dimension panelSize = new Dimension(9000,9000);
	private JButton btnNew = new JButton("New");
	private JButton btnLoad = new JButton("Load");
	private JButton btnSave = new JButton("Save");
	private JButton btnOption = new JButton("Option");
	private JButton btnUndo = new JButton("Undo");
	private JButton btnRecord = new JButton("Record");
	private Menu menu = new Menu();
	private BtnClass Btn;
	private Point ViewPos = new Point(3400,3550);
	public TraxBoard()
	{	
		txtPathName = new JTextField();
		txtPathName.setText("c:/java/trax/traxtest1.obj");
		txtPathName.setColumns(20);
		
		btnRecord.setToolTipText("Record");
		btnUndo.setToolTipText("Undo");
		btnNew.setToolTipText("New");
		btnSave.setToolTipText("Save");
		btnLoad.setToolTipText("Load");
		
		BtnPanel.add(btnNew);
		BtnPanel.add(btnSave);
		BtnPanel.add(btnLoad);
		BtnPanel.add(btnOption);
		BtnPanel.add(btnUndo);
		BtnPanel.add(btnRecord);
		BtnPanel.add(txtPathName);
		
		panel.setLayout(new GridLayout(128,128));
		panel.setPreferredSize(panelSize);
		
		SetScroll();
		Btn = new BtnClass();
		
		board.add(BtnPanel, BorderLayout.NORTH);
		
		add(scroll,BorderLayout.CENTER);
		add(board,BorderLayout.NORTH);
		setJMenuBar(menu.menuBar);
		
		setSize(1000,900);
		setVisible(true);
		setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

	}

	void SetScroll()
	{
		scroll.setViewportView(panel);
		scroll.getViewport().setViewPosition(ViewPos);
	}
}