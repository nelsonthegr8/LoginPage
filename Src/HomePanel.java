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
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.Dimension;

public class HomePanel extends JPanel {
	/**
	 * Create the panel.
	 */
	public HomePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Home");
		panel_2.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
//		textArea.setEditable(false);
		textArea.setText(Information.getAboutusr());
		add(textArea, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(50, 10));
		add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(50, 10));
		add(panel_1, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);

	}

}
