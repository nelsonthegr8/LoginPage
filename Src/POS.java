import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class POS extends JPanel {
	
	
	private static JTextField textField;
	private static boolean manager = false;
	private static JTable table = new JTable(new DefaultTableModel(null, new Object[]{"ItemNum","Item", "Price"})){
		@Override
	    public boolean isCellEditable(int row, int column) {    
			if(manager && column == 2) {
				scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        return true;
	        }else 
	        {scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
	        return false;}
	    };

	};
	private static String item = "";
	private boolean check = true;
	private static JLabel lblTotal = new JLabel("0.00");
	private static JLabel lblTotal_1 = new JLabel("0.00");
	private static JLabel label = new JLabel("");
	private static boolean homeCheck = true;
	private static JScrollPane scrollPane = new JScrollPane(table);
	/**
	 * Create the panel.
	 */
	public POS() {
		setBackground(Color.DARK_GRAY);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(893, 13, 82, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.panelHolder());
				homeCheck = false;
				resetFields();
			}
		});
		setLayout(null);
		add(btnNewButton);
		
		JLabel lblItem = new JLabel("Item #");
		lblItem.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblItem.setForeground(new Color(255, 255, 255));
		lblItem.setBounds(89, 61, 56, 16);
		add(lblItem);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setBounds(144, 58, 285, 22);
		add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBackground(new Color(51, 153, 51));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					item = textField.getText();
				}catch(NullPointerException f) {
					check = false;
				}
				
			if(check) {
				try {
					functions.refreshTable(item);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			check = true;
		}	
		});
		btnAdd.setBounds(505, 57, 97, 25);
		add(btnAdd);
		
		
		table.getModel().addTableModelListener(new TableModelListener() {

		      public void tableChanged(TableModelEvent e) {
		    	  setTotalsandSubtotal();
		      }
		    });
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setBounds(26, 91, 521, 437);
		add(scrollPane);
		lblTotal.setForeground(new Color(255, 255, 255));
		lblTotal.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setBounds(744, 396, 135, 16);
		add(lblTotal);
		
		JButton btnFinished = new JButton("Finished");
		btnFinished.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFinished.setBackground(new Color(0, 51, 153));
		btnFinished.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double amount = Float.valueOf((String) lblTotal.getText().trim());
				functions.transactionFinished(amount);
			}
		});
		btnFinished.setBounds(744, 481, 97, 25);
		add(btnFinished);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setForeground(new Color(255, 255, 255));
		lblSubtotal.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		lblSubtotal.setBounds(658, 392, 74, 25);
		add(lblSubtotal);
		lblTotal_1.setForeground(new Color(255, 255, 255));
		lblTotal_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		lblTotal_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		lblTotal_1.setBounds(744, 417, 46, 25);
		add(lblTotal_1);
		
		JLabel lblTotal_2 = new JLabel("Total");
		lblTotal_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		lblTotal_2.setForeground(new Color(255, 255, 255));
		lblTotal_2.setBounds(658, 417, 46, 25);
		add(lblTotal_2);
		
		JLabel lblPos = new JLabel("P.O.S");
		lblPos.setForeground(new Color(255, 255, 255));
		lblPos.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblPos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPos.setBounds(264, 18, 56, 27);
		add(lblPos);
		
		JLabel lblTransactionNumber = new JLabel("Transaction Number");
		lblTransactionNumber.setForeground(new Color(255, 255, 255));
		lblTransactionNumber.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblTransactionNumber.setBounds(613, 189, 177, 25);
		add(lblTransactionNumber);
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		label.setForeground(new Color(255, 255, 255));
		
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(767, 189, 74, 25);
		add(label);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.setBackground(new Color(204, 51, 0));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					item = textField.getText();
				}catch(NullPointerException f) {
					check = false;
				}
				
			if(check) {
				functions.RemoveItem(item,"p");
			}
			check = true;
			}
		});
		btnRemove.setBounds(645, 57, 97, 25);
		add(btnRemove);

	}
	
	public static void setTable(String type,String price) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{item,type, price});
		textField.setText("");
		setTotalsandSubtotal();
	}
	public static void setPrice(int row,int column, String data) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setValueAt(data, row, column);
		setTotalsandSubtotal();
	}
	public static void removeItem(int Row) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.removeRow(Row);
		textField.setText("");
		setTotalsandSubtotal();
	}
	public static void setTotalsandSubtotal() {
		try {
		lblTotal.setText(functions.totalAmount());
		}catch(NumberFormatException e) {JOptionPane.showMessageDialog(null, "Please enter a number in the price area! Not letters!");
		functions.resetPrices();
		}
		
		double total = Float.valueOf((String) lblTotal.getText().trim());
		total = total + (total * .06);
		DecimalFormat df = new DecimalFormat("###.##");
		lblTotal_1.setText(" "+df.format(total));
	}
	
	public static JTable getTable() {
		return table;
	}
	
	public static void resetFields() {
		textField.setText("");
		lblTotal.setText("");
		lblTotal_1.setText("");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		if(homeCheck) {
		functions.addTransactionNum();}
		setTransactiontxt(functions.TransactionNum());
	}
	
	public static void setTransactiontxt(int Trans) {
		label.setText(""+Trans);
	}
	public static void setBool() {
		homeCheck = true;
	}
	public static void managerOrNo(boolean yesNo) {
		manager = yesNo;
		
		}
}
