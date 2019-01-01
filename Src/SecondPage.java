import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class SecondPage extends JFrame {

	private JPanel contentPane;
	LogIn f1 = new LogIn();
	AddUsr Panel2 = new AddUsr();
	HomePanel Panel = new HomePanel();

	public SecondPage(String uname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(255, 255, 255));
		toolBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel(uname);
		panel_1.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));
		
		   
		JButton HomePagebtn = new JButton("Home");
		HomePagebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 panel.removeAll();
				 panel.add(Panel);
				 panel.validate();
				 panel.repaint();
				
			}
		});
		HomePagebtn.setForeground(new Color(255, 255, 255));
		HomePagebtn.setBackground(new Color(51, 102, 204));
		toolBar.add(HomePagebtn);
		
		JButton btnNewButton_1 = new JButton("About Me");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
					
			}
		});
		btnNewButton_1.setBackground(new Color(0, 153, 255));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		toolBar.add(btnNewButton_1);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		toolBar.add(btnLogout);
		
	}
}
