import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class LogIn extends JPanel {
	private static JTextField textField;
	private static String userName, Password;
	private static JPasswordField passwordField;
	
	/**
	 * Create the panel.
	 */
	public LogIn() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setBounds(270, 136, 125, 100);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(524, 162, 225, 48);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(270, 222, 125, 100);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(341, 425, 298, 76);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					userName = textField.getText();
					Password = passwordField.getText();
				}catch(NullPointerException e) {
					JOptionPane.showInternalMessageDialog(null, "Please Fill out the fields");
				}
				
				try {
					functions.loginQuery(userName, Password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setBounds(524, 248, 225, 48);
		add(passwordField);
		add(btnNewButton);
		
		JLabel lblPos = new JLabel("P.O.S");
		lblPos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPos.setBounds(450, 13, 108, 61);
		add(lblPos);

	}
	public static void resetFields() {
		passwordField.setText("");
		textField.setText("");
		userName = Password = "";
		
	}
}
