package GUI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

class OpenActionListener extends JFrame implements ActionListener{
	Container contentPane;
	JLabel imageLabel = new JLabel();	
	JFileChooser chooser;
		
	OpenActionListener(){
		chooser = new JFileChooser();
	}
	public void actionPerformed(ActionEvent e){
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"txt file",
				"txt");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		
		if(ret != JFileChooser.APPROVE_OPTION){
			JOptionPane.showMessageDialog(null, "no file", "warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String filePath = chooser.getSelectedFile().getPath();
		
		imageLabel.setIcon(new ImageIcon(filePath));
		pack();
	}
}
