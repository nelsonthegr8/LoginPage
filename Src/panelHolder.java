import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
		setLayout(null);
		
		
		btnPos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				POS.setTransactiontxt(functions.TransactionNum());
				POS.setBool();
				Main_Window.changePanel(functions.POS());
			}
		});
		btnPos.setBounds(86, 251, 97, 25);
		add(btnPos);
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
		
		
		
		btnPurchaseHistory.setBounds(699, 251, 136, 25);
		add(btnPurchaseHistory);
		
		
		
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
		btnNewButton.setBounds(511, 251, 97, 25);
		add(btnNewButton);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.login());
			}
		});
		btnLogOut.setBounds(844, 43, 97, 25);
		add(btnLogOut);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setBounds(379, 48, 153, 14);
		add(lblMainMenu);
		
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(335, 105, 239, 14);
		add(lblNewLabel);
		
		
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
			btnPos.setBounds(228, 251, 97, 25);
			btnNewButton_1.setBounds(569, 252, 89, 23);
		}else {
			btnPos.setBounds(86, 251, 97, 25);
			btnNewButton_1.setBounds(293, 252, 89, 23);
		}
		lblNewLabel.setText("Welcome "+functions.CurrentusrName);
	}

	
}
