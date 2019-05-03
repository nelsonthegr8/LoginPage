import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Merch extends JPanel {
	private static JTable table = new JTable(new DefaultTableModel(null, new Object[]{"ItemNum", "Price","Description","Quantity"}));
	public Merch() {
		setLayout(null);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Window.changePanel(functions.panelHolder());
			}
		});
		btnHome.setBounds(850, 28, 89, 23);
		add(btnHome);
		
		JButton btnChangeQuantity = new JButton("Restock");
		btnChangeQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functions.AddInventory();
				JOptionPane.showMessageDialog(null, "Items have been Restocked!");
			}
		});
		btnChangeQuantity.setBounds(818, 339, 121, 23);
		add(btnChangeQuantity);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(62, 63, 674, 404);
		add(scrollPane);
		
	}
public static void setTable(TableModel tables) {
	table.setModel(tables);
}
public static JTable getTable() {
	return table;
}
}
