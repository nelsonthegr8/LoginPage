import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class panelHolder extends JPanel {
	/**
	 * Create the panel.
	 */
	private static JButton btnPurchaseHistory = new JButton("Purchase History");
	private static JButton btnNewButton = new JButton("Inventory");
	private static JLabel lblNewLabel = new JLabel("Welcome");
	private static JButton btnNewButton_1 = new JButton("Return");
	private static JButton btnPos = new JButton("P.O.S");
	public panelHolder() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		btnPos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPos.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		btnPos.setBackground(new Color(255, 255, 255));
		
		
		btnPos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				POS.setTransactiontxt(functions.TransactionNum());
				POS.setBool();
				Main_Window.changePanel(functions.POS());
			}
		});
		btnPos.setBounds(86, 251, 97, 25);
		add(btnPos);
		btnPurchaseHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPurchaseHistory.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		btnPurchaseHistory.setBackground(new Color(255, 255, 255));
		btnPurchaseHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					functions.refreshSalesTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Main_Window.changePanel(functions.PurchHistory());
			}
		});
		
		
		
		btnPurchaseHistory.setBounds(759, 251, 141, 25);
		add(btnPurchaseHistory);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(255, 255, 255));
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					functions.refreshMerchTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Main_Window.changePanel(functions.Merch());
			}
		});
		btnNewButton.setBounds(561, 251, 97, 25);
		add(btnNewButton);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogOut.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		btnLogOut.setBackground(new Color(255, 255, 255));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.login());
			}
		});
		btnLogOut.setBounds(844, 43, 97, 29);
		add(btnLogOut);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblMainMenu.setForeground(new Color(255, 255, 255));
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setBounds(405, 60, 153, 36);
		add(lblMainMenu);
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(361, 112, 239, 36);
		add(lblNewLabel);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.Return());
			}
		});
		btnNewButton_1.setBounds(293, 252, 89, 23);
		add(btnNewButton_1);

	}
	
	public static void isManager(boolean manager) {
		btnPurchaseHistory.setVisible(manager);
		btnNewButton.setVisible(manager);
		if(manager == false) {
			btnPos.setBounds(293, 252, 89, 23);
			btnNewButton_1.setBounds(569, 252, 89, 23);
		}else {
			btnPos.setBounds(86, 251, 97, 25);
			btnNewButton_1.setBounds(293, 252, 89, 23);
		}
		lblNewLabel.setText("Welcome "+functions.CurrentusrName);
	}

	
}
