
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Return extends JPanel {
	private static JTable table = new JTable(new DefaultTableModel(null, new Object[]{"ItemNum","Price"}));
	private static JTextField textField = new JTextField();
	private static JTextField textField_1 = new JTextField();
	private static String receipt = "";
	private String item = "";
	private int transactionNum = 0;
	private static boolean check = false;
	private static boolean nCheck = true;
	/**
	 * Create the panel.
	 */
	public Return() {
		setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(check) {
					HashMap<String, Integer> map = new HashMap<>();
					for(int i = 0; i < table.getRowCount(); i++) {
						if(map.containsKey(table.getValueAt(i, 0))){
				            map.put((String) table.getValueAt(i, 0), map.get(table.getValueAt(i, 0)) + 1); 
				        }else {
				        	map.put((String) table.getValueAt(i, 0), 1);
				        }
					}
					String[] items = new String[map.keySet().size()];
					int[] Quantities = new int[map.values().size()];
					int i = 0;
					int j = 0;
					for(int n : map.values()) {
						Quantities[i] = n;
						i++;
					}
					for(String n : map.keySet()) {
						items[j] = n;
						j++;
					}
					for(int count = 0; count < items.length; count++) {
						try {
							//this calls the functions to update the merchandise database
							functions.updateInventory((functions.Quantities(items[count]) + Quantities[count]),items[count]);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					}
					resetFields();
					JOptionPane.showMessageDialog(null,"Items have been returned and inventory has been updated");
					}else {
					JOptionPane.showMessageDialog(null,"Please enter a transaction number first and hit look up");
				}
			}});
		btnReturn.setBounds(793, 478, 89, 23);
		add(btnReturn);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(131, 96, 445, 416);
		add(scrollPane);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		textField.setBounds(796, 198, 99, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblTransactionNumber = new JLabel("Transaction Number");
		lblTransactionNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransactionNumber.setBounds(596, 201, 139, 14);
		add(lblTransactionNumber);
		
		JButton btnLookUp = new JButton("Look Up");
		btnLookUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					transactionNum = Integer.parseInt(textField.getText().trim());
				}catch(NumberFormatException f) {
					JOptionPane.showMessageDialog(null,"Please enter a transaction number.");
					nCheck = false;
				}
			
			if(nCheck) {	
				try {
					functions.transactionLookUp(transactionNum);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			nCheck = true;
			}
		});
		btnLookUp.setBounds(806, 241, 89, 23);
		add(btnLookUp);
		
		
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setBounds(149, 65, 193, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(check) {
					try {
						item = textField_1.getText();
					}catch(NullPointerException r) {
						JOptionPane.showMessageDialog(null, "Please enter an item number");
					}
				functions.returnFunction("Reciepts/"+receipt, item);
				textField_1.setText("");
				}else {
					JOptionPane.showMessageDialog(null,"Please enter a transaction number first and hit look up");
				}
			}
		});
		btnAdd.setBounds(404, 64, 89, 23);
		add(btnAdd);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFields();
				Main_Window.changePanel(functions.panelHolder());
			}
		});
		btnHome.setBounds(806, 35, 89, 23);
		add(btnHome);

	}
	
	public static void setTable(String item,String price) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{item,price});
	}
	public static void fileNonExist() {
		check = false;
		JOptionPane.showMessageDialog(null,"The reciept is not in file try another transaction number");
	}
	public static void fileExist() {
		check = true;
		JOptionPane.showMessageDialog(null,"The reciept has been found");
		receipt = textField.getText().trim()+".txt";
		textField.setText(" ");
	}
	private void resetFields() {
		check = false;
		textField.setText("");
		textField_1.setText("");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
	}
}
