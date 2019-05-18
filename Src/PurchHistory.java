


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
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;


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
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		
		
		
		JLabel lblSalesHistory = new JLabel("Sales History");
		lblSalesHistory.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblSalesHistory.setForeground(Color.WHITE);
		lblSalesHistory.setBounds(352, 42, 127, 27);
		add(lblSalesHistory);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		lblFirstName.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setBounds(692, 186, 94, 14);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
		lblLastName.setForeground(Color.WHITE);
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
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		scrollPane.setBounds(128, 112, 511, 386);
		add(scrollPane);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setBounds(692, 247, 56, 16);
		add(lblPhone);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(826, 244, 120, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(692, 282, 79, 16);
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
