import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class HomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Home");
		add(lblNewLabel, BorderLayout.NORTH);
		
		JTree tree = new JTree();
		add(tree, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton, BorderLayout.NORTH);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_1.add(btnNewButton_2, BorderLayout.NORTH);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_1.add(btnNewButton_3, BorderLayout.SOUTH);
		
		JButton btnNewButton_4 = new JButton("New button");
		panel_1.add(btnNewButton_4, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		add(textPane, BorderLayout.CENTER);

	}

}
