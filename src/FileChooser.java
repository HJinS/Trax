import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
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
