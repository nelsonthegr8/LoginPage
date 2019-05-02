import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class POS extends JPanel {
	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public POS() {
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setBounds(893, 13, 82, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.panelHolder());
			}
		});
		setLayout(null);
		add(btnNewButton);
		
		JLabel lblItem = new JLabel("Item #");
		lblItem.setBounds(89, 17, 56, 16);
		add(lblItem);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setBounds(143, 14, 285, 22);
		add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(508, 13, 97, 25);
		add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 2, 2);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(26, 46, 521, 437);
		add(table);
		
		JLabel lblTime = new JLabel("time");
		lblTime.setBounds(480, 522, 56, 16);
		add(lblTime);
		
		JLabel lblTotal = new JLabel("total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(721, 391, 135, 16);
		add(lblTotal);
		
		JButton btnFinished = new JButton("Finished");
		btnFinished.setBounds(744, 481, 97, 25);
		add(btnFinished);

	}
}
