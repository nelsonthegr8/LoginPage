import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class LogIn extends JPanel {
	private static JTextField textField;
	private static String userName, Password;
	private static JPasswordField passwordField;
	
	/**
	 * Create the panel.
	 */
	public LogIn() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(418, 84, 125, 100);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(369, 175, 225, 48);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(418, 218, 125, 100);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(51, 153, 51));
		btnNewButton.setBounds(330, 426, 298, 76);
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
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(369, 308, 225, 48);
		add(passwordField);
		add(btnNewButton);
		
		JLabel lblPos = new JLabel("P.O.S");
		lblPos.setForeground(Color.WHITE);
		lblPos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPos.setFont(new Font("Serif", Font.BOLD, 26));
		lblPos.setBounds(423, 13, 108, 61);
		add(lblPos);

	}
	public static void resetFields() {
		passwordField.setText("");
		textField.setText("");
		userName = Password = "";
		
	}
	
}
