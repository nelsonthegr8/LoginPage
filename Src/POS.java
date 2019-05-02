import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class POS extends JPanel {
	private static JTextField textField;
	private static JTable table = new JTable(new DefaultTableModel(null, new Object[]{"ItemNum","Item", "Price"}));
	private static String item = "";
	private boolean check = true;
	private static JLabel lblTotal = new JLabel("subtotal");
	private static JLabel lblTotal_1 = new JLabel("total");
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
		btnAdd.setBounds(508, 13, 97, 25);
		add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 46, 521, 437);
		add(scrollPane);
		
		
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setBounds(744, 391, 135, 16);
		add(lblTotal);
		
		JButton btnFinished = new JButton("Finished");
		btnFinished.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double amount = Float.valueOf((String) lblTotal.getText().trim());
				functions.transactionFinished(amount);
			}
		});
		btnFinished.setBounds(744, 481, 97, 25);
		add(btnFinished);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setBounds(658, 392, 53, 14);
		add(lblSubtotal);
		lblTotal_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		lblTotal_1.setBounds(744, 417, 46, 14);
		add(lblTotal_1);
		
		JLabel lblTotal_2 = new JLabel("Total");
		lblTotal_2.setBounds(658, 417, 46, 14);
		add(lblTotal_2);

	}
	
	public static void setTable(String type,String price) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{item,type, price});
		textField.setText("");
		lblTotal.setText(functions.totalAmount());
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
	}
}
