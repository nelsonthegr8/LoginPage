import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class Merch extends JPanel {
	private static JTable table = new JTable(new DefaultTableModel(null, new Object[]{"ItemNum", "Price","Description","Quantity"})){
		@Override
	    public boolean isCellEditable(int row, int column) {    
			if(column == 3) {
	        return true;
	        }else return false;
	    };

	};
	public Merch() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JButton btnHome = new JButton("Home");
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.panelHolder());
			}
		});
		btnHome.setBounds(850, 28, 89, 23);
		add(btnHome);
		
		JButton btnChangeQuantity = new JButton("Restock");
		btnChangeQuantity.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChangeQuantity.setForeground(new Color(255, 255, 255));
		btnChangeQuantity.setBackground(new Color(0, 51, 204));
		btnChangeQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functions.AddInventory();
				JOptionPane.showMessageDialog(null, "Items have been Restocked!");
				try {
					functions.refreshMerchTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnChangeQuantity.setBounds(818, 339, 121, 23);
		add(btnChangeQuantity);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setBounds(62, 63, 674, 404);
		add(scrollPane);
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblInventory.setForeground(Color.WHITE);
		lblInventory.setBounds(375, 13, 115, 27);
		add(lblInventory);
		
	}
public static void setTable(TableModel tables) {
	table.setModel(tables);
}
public static JTable getTable() {
	return table;
}
}
