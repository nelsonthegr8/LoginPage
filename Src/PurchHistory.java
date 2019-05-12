


import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


@SuppressWarnings("serial")
public class PurchHistory extends JPanel {
	private static JTable table = new JTable(){
		@Override
	    public boolean isCellEditable(int row, int column) {    
			return false;
	    };

	};
	private static JTextField textField_1 = new JTextField();
	private static JTextField textField_2 = new JTextField();
	private static String filePath = "src/Reciepts/Receipt";
	private boolean fileOpen = false;
	private static JTextField textField;
	private static JTextField textField_3;
	
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
				if(fileOpen) {functions.closeNotepad();}
				resetFields();
			}
		});
		btnNewButton.setBounds(857, 72, 89, 23);
		add(btnNewButton);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(692, 186, 94, 14);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(692, 214, 94, 14);
		add(lblLastName);
		
		
		textField_1.setEditable(false);
		textField_1.setBounds(826, 183, 120, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		
		textField_2.setEditable(false);
		textField_2.setBounds(826, 211, 120, 20);
		add(textField_2);
		textField_2.setColumns(10);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     int i = table.getSelectedRow();
				     TableModel model = table.getModel();
				     if(fileOpen) {
				    	functions.closeNotepad();
					    fileOpen = false;
				}
				     functions.openFileinNotepad(filePath+model.getValueAt(i, 5).toString().trim());
				     fileOpen = true;
				}
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				String uname = "";
				uname = model.getValueAt(i, 0).toString();
				try {
					functions.UserLookUp(uname);
				} catch (SQLException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setBounds(128, 112, 511, 386);
		add(scrollPane);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(692, 247, 56, 16);
		add(lblPhone);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(826, 244, 120, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setBounds(692, 282, 56, 16);
		add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(783, 279, 163, 22);
		add(textField_3);
		textField_3.setColumns(10);

	}
	public static JTable getTable() {
		return table;
	}
	public static void setTable(TableModel tables) {
		table.setModel(tables);
	}
	public static void setFandL(String first,String last,String phone, String address) {
		textField_1.setText(first);
		textField_2.setText(last);
		textField.setText(phone);
		textField_3.setText(address);
	}
	public static void resetFields() {
		textField_1.setText("");
		textField_2.setText("");
		textField.setText("");
		textField_3.setText("");
		}
	public static String getFname() {
		return textField_1.getText();
	}
}
