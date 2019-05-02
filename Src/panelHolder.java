import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class panelHolder extends JPanel {
	/**
	 * Create the panel.
	 */
	public panelHolder() {
		setLayout(null);
		
		JButton btnPos = new JButton("P.O.S");
		btnPos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.POS());
			}
		});
		btnPos.setBounds(86, 251, 97, 25);
		add(btnPos);
		
		JButton btnPurchaseHistory = new JButton("Purchase History");
		btnPurchaseHistory.setBounds(718, 251, 136, 25);
		add(btnPurchaseHistory);
		
		JButton btnNewButton = new JButton("Inventory");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(512, 251, 97, 25);
		add(btnNewButton);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(303, 251, 97, 25);
		add(btnReturn);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.login());
			}
		});
		btnLogOut.setBounds(844, 43, 97, 25);
		add(btnLogOut);

	}

	
}
