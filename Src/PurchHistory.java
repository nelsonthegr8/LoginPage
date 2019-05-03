


import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PurchHistory extends JPanel {
	private static JTable table = new JTable();
	private static JTextField textField = new JTextField();
	private static JTextField textField_1 = new JTextField();
	private static JTextField textField_2 = new JTextField();
	
	/**
	 * Create the panel.
	 */
	public PurchHistory() {
		setLayout(null);
		
		
		
		
		JLabel lblSalesHistory = new JLabel("Sales History");
		lblSalesHistory.setBounds(352, 42, 127, 14);
		add(lblSalesHistory);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.panelHolder());
				resetFields();
			}
		});
		btnNewButton.setBounds(857, 72, 89, 23);
		add(btnNewButton);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(729, 186, 94, 14);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(729, 214, 94, 14);
		add(lblLastName);
		
		
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(734, 456, 104, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Get Info");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = "";
				boolean check = true;
				try {
					uname = textField.getText();
				}catch(NullPointerException p) {
					check = false;
				}
				if(check) {
					try {
						functions.UserLookUp(uname);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				check = false;
			}
		});
		btnNewButton_1.setBounds(869, 453, 89, 23);
		add(btnNewButton_1);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(649, 459, 68, 14);
		add(lblUserName);
		
		
		textField_1.setEditable(false);
		textField_1.setBounds(826, 183, 120, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		
		textField_2.setEditable(false);
		textField_2.setBounds(826, 211, 120, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(128, 112, 511, 386);
		add(scrollPane);

	}
	public static JTable getTable() {
		return table;
	}
	public static void setTable(TableModel tables) {
		table.setModel(tables);
	}
	public static void setFandL(String first,String last) {
		textField_1.setText(first);
		textField_2.setText(last);
		textField.setText("");
	}
	public static void resetFields() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		}
	public static String getFname() {
		return textField_1.getText();
	}
}
