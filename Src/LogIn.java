import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Dimension;

public class LogIn extends JFrame {
//	 variables i will be using throughout my code some i pull statically from the information class
//	Information Info = new Information();
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private String Uname;
	private String Pass;
//	private String Usernames[] = Information.getUsernames();
//	private String Passwords[] = Information.getPasswords();
	private static LogIn frame = new LogIn();
	private int UsrInput;
	private String PersonalUsr;
	AddUsr addPane = new AddUsr();
	static SecondPage SecondP = new SecondPage();
	
	/**
	 * Launch the application.
	 */
	
	//This is a try catch used to help me weed out any errors causing the window not to open up
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		//this part is adding all of the items to the Jframe
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 392);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 500, 365);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 255));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel UserName = new JLabel("User Name");
		UserName.setBounds(130, 104, 73, 14);
		panel_1.add(UserName);
		UserName.setForeground(new Color(0, 0, 0));
		UserName.setBackground(new Color(0, 0, 0));
		
		textField = new JTextField();
		textField.setBounds(210, 101, 126, 20);
		panel_1.add(textField);
		textField.setColumns(15);
		
		JLabel UserPass = new JLabel("Password");
		UserPass.setBounds(130, 148, 68, 14);
		panel_1.add(UserPass);
		UserPass.setBackground(new Color(51, 0, 0));
		UserPass.setForeground(new Color(0, 0, 0));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(210, 145, 126, 20);
		panel_1.add(passwordField);
		passwordField.setEchoChar('*');
		passwordField.setColumns(15);
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.setBounds(210, 217, 89, 23);
		panel_1.add(LoginBtn);
		LoginBtn.setForeground(new Color(0, 0, 0));
		LoginBtn.setBackground(new Color(255, 255, 255));
		
		JButton btnNewButton = new JButton("Add Usr");
		btnNewButton.setBounds(399, 32, 89, 23);
		panel_1.add(btnNewButton);
		btnNewButton.setBackground(new Color(153, 153, 255));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 240, 240));
		
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.add(panel_1, BorderLayout.CENTER);
				panel.validate();
				panel.repaint();
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.removeAll();
				panel.add(addPane, BorderLayout.CENTER);
				panel.add(btnNewButton_1, BorderLayout.SOUTH);
				panel.validate();
				panel.repaint();
			}
		});
		LoginBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					Uname = textField.getText();
					Pass = passwordField.getText();
				}catch(NullPointerException e) {
				}

					if (Arrays.asList(Information.getUsernames()).contains(Uname) && Pass.equals(Information.getPasswords()[Arrays.asList(Information.getUsernames()).indexOf(Uname)])) {
						UsrInput = Arrays.asList(Information.getUsernames()).indexOf(Uname);
						PersonalUsr = Information.getUsernames()[UsrInput];
						Information.setUsrloginnum(Uname);
						SecondP.setVisible(true);
						textField.setText(" ");
						frame.dispose();
						
					}else if(Arrays.asList(Information.getUsernames()).contains(Uname) == false) {
						JOptionPane.showMessageDialog(null, " The User Name Entered is not Registered in our system.");
					}else if(Arrays.asList(Information.getUsernames()).contains(Uname) && Pass.equals(Information.getPasswords()[Arrays.asList(Information.getUsernames()).indexOf(Uname)]) == false) {
						JOptionPane.showMessageDialog(null, "The Password entered is incorrect");
					}
			}
		});
		
	}
	public static void closeWindow() {
		SecondP.dispose();
		
	}

}
