import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JFileChooser;

public class AddUsr extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private BufferedImage[] Images = new BufferedImage[30];
	private String usrName;

	/**
	 * Create the panel.
	 */
	public AddUsr() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("New Usr");
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		
		textField = new JTextField();
		textField.setColumns(20);
		
		JLabel lblNewLabel_2 = new JLabel("Password  ");
		
		textField_1 = new JTextField();
		textField_1.setColumns(20);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(lblNewLabel_1);
		panel_1.add(textField);
		panel_1.add(lblNewLabel_2);
		panel_1.add(textField_1);
		
		JLabel lblAboutMe = new JLabel("About Me");
		panel_1.add(lblAboutMe);
		
		JTextArea textArea = new JTextArea();
		textArea.setMaximumSize(new Dimension(200, 130));
		textArea.setRows(6);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setPreferredSize(new Dimension(290, 130));
		textArea.setSize(new Dimension(200, 130));
		panel_1.add(textArea);
		
		JFileChooser fileChooser = new JFileChooser();
		
		JButton btnAddPicture = new JButton("Add Picture");
		btnAddPicture.setPreferredSize(new Dimension(300, 25));
		btnAddPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panel_1.removeAll();
				panel_1.add(fileChooser);
				panel_1.validate();
				panel_1.repaint();
			}
		});
		panel_1.add(btnAddPicture);
		
		JButton btnNewButton = new JButton("Add Usr");
		btnNewButton.setPreferredSize(new Dimension(100, 25));
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					usrName = textField.getText();
				}catch(NullPointerException p) {
					System.out.println("broke");
				}
				if( Information.CheckUsername(usrName)) {
					System.out.println("true ur in");
//				try {
//					
//				}catch(IOException e) {
//					
//				}
			  }else if(Information.CheckUsername(usrName) == false) {
				  System.out.println("false im in");
			  }
			}
		});

	}
}
