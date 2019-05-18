
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

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
	public static boolean TransactionFound = false;
	private static boolean fileOpen = false;
	private BufferedImage Logopic;
	/**
	 * Create the panel.
	 */
	public Return() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReturn.setForeground(new Color(255, 255, 255));
		btnReturn.setBackground(new Color(0, 51, 153));
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
					functions.closeNotepad();
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
		lblTransactionNumber.setForeground(Color.WHITE);
		lblTransactionNumber.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		lblTransactionNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransactionNumber.setBounds(596, 201, 188, 23);
		add(lblTransactionNumber);
		
		try {
			Logopic = ImageIO.read(new File("src/pictures/magnifying.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnLookUp = new JButton(new ImageIcon(Logopic));
		btnLookUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLookUp.setForeground(new Color(255, 255, 255));
		btnLookUp.setBackground(new Color(255, 255, 255));
		btnLookUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(TransactionFound == false) {	
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
				
				}else {
					JOptionPane.showMessageDialog(null,"Please finish the return before looking up another transaction");
					textField.setText("");
				}
			}
		});
		btnLookUp.setBounds(806, 241, 89, 30);
		add(btnLookUp);
		
		
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setBounds(149, 65, 193, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(51, 153, 51));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(check) {
					try {
						item = textField_1.getText().trim();
					}catch(NullPointerException r) {
						JOptionPane.showMessageDialog(null, "Please enter an item number");
					}
				functions.returnFunction("src/Reciepts/"+receipt, item);
				textField_1.setText("");
				}else {
					JOptionPane.showMessageDialog(null,"Please enter a transaction number first and hit look up");
					textField_1.setText("");
				}
			}
		});
		btnAdd.setBounds(404, 64, 89, 23);
		add(btnAdd);
		
		JButton btnHome = new JButton("Home");
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileOpen) {functions.closeNotepad();}
				resetFields();
				Main_Window.changePanel(functions.panelHolder());
			}
		});
		btnHome.setBounds(806, 35, 89, 23);
		add(btnHome);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(204, 51, 0));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(check) {
				try {
					item = textField_1.getText();
				}catch(NullPointerException f) {
					
				}
				functions.RemoveItem(item,"r");
			}else {
				JOptionPane.showMessageDialog(null,"Please enter a transaction number first and hit look up");
				textField_1.setText("");
			}
			
			}
		});
		btnRemove.setBounds(518, 63, 97, 25);
		add(btnRemove);
		
		JLabel lblItem = new JLabel("item #");
		lblItem.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblItem.setForeground(Color.WHITE);
		lblItem.setBounds(58, 67, 56, 16);
		add(lblItem);
		
		JLabel lblReturn = new JLabel("Return");
		lblReturn.setHorizontalAlignment(SwingConstants.CENTER);
		lblReturn.setForeground(Color.WHITE);
		lblReturn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblReturn.setBounds(441, 13, 81, 38);
		add(lblReturn);

	}
	
	public static void setTable(String item,String price) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{item,price});
	}
	public static JTable getTable() {
		return table;
	}
	public static void fileNonExist() {
		check = false;
		JOptionPane.showMessageDialog(null,"The reciept is not in file try another transaction number");
	}
	public static void fileExist() {
		check = true;
		JOptionPane.showMessageDialog(null,"The reciept has been found");
		receipt = textField.getText().trim();
		textField.setText("");
		TransactionFound = true;
		fileOpen = true;
	}
	public static void removeItem(int Row) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.removeRow(Row);
		textField_1.setText("");
	}
	private void resetFields() {
		TransactionFound = false;
		check = false;
		textField.setText("");
		textField_1.setText("");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
	}
}
