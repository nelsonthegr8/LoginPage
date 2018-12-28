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
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
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

	/**
	 * Create the panel.
	 */
	public AddUsr() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("New Usr");
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 10));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBack = new JButton("Back");
		panel.add(btnBack);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		
		textField = new JTextField();
		textField.setColumns(19);
		
		JLabel lblNewLabel_2 = new JLabel("Password  ");
		
		textField_1 = new JTextField();
		textField_1.setColumns(19);
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
		textArea.setPreferredSize(new Dimension(240, 130));
		textArea.setSize(new Dimension(200, 130));
		panel_1.add(textArea);
		
		JButton btnAddPicture = new JButton("Add Picture");
		btnAddPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				panel_1.removeAll();
				panel_1.add(fileChooser);
				panel_1.validate();
				panel_1.repaint();
			}
		});
		panel_1.add(btnAddPicture);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(5, 30));
		panel_2.setMaximumSize(new Dimension(50, 50));
		add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(100, 10));
		panel_3.setSize(new Dimension(10, 0));
		add(panel_3, BorderLayout.EAST);

	}
}
