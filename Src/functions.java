import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class functions {
//initialize the variables that will be passed around the application
public static String CurrentusrName = "";
private static JPanel Login = new LogIn();
private static JPanel PiS = new POS();
private static JPanel panelholder = new panelHolder();
private static double amount = 0.0;
//this function is called when the user tries to log in and it is successful or not
	public static void loginQuery(String uname, String pass) throws SQLException {
		// 1. Establish a Connection
		Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
		// 2. Prepare Statement
		PreparedStatement myStmt = myConn.prepareStatement("select * from Associates where User_Name = ? and Pass = ?");
		//3. set the parameters
		myStmt.setString(1, uname);
		myStmt.setString(2, pass);
		//4. execute sql query
		ResultSet myRs = myStmt.executeQuery();
		if(myRs.next() == false) {
			JOptionPane.showMessageDialog( null , "Username or Password is Incorrect");
			myConn.close();
		}else {
			JOptionPane.showMessageDialog( null , "Login Successful");
			CurrentusrName = uname;
			Main_Window.changePanel(functions.panelholder);
			LogIn.resetFields();
			myConn.close();
		}
	}
	
//this functions sets the POS Table	
	public static void refreshTable(String item) throws SQLException {
		// 1. Establish a Connection
		Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
		// 2. Prepare Statement
		PreparedStatement myStmt = myConn.prepareStatement("select * from Merchandise where Item_Number = ?");
		//
		myStmt.setString(1, item);
		//4. execute sql query
		ResultSet myRs = myStmt.executeQuery();
		if(myRs.next() == false) {
			myConn.close();
		}else {
			
			POS.setTable(myRs.getString("Type"),myRs.getString("Price"));
			myConn.close();
		}
	}
	
	//this functions sets the POS Table	
		public static int Quantities(String item) throws SQLException {
			int q = 0;
			// 1. Establish a Connection
			Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
			// 2. Prepare Statement
			PreparedStatement myStmt = myConn.prepareStatement("select Quantity from Merchandise where Item_Number = ?");
			//
			myStmt.setString(1, item);
			//4. execute sql query
			ResultSet myRs = myStmt.executeQuery();
			if(myRs.next() == false) {
				myConn.close();
			}else {
				q = myRs.getInt("Quantity");
				myConn.close();
			}
			return q;
		}
//this function gives a current total of the purchase
	public static String totalAmount() {
		amount = 0.0;
		DecimalFormat df = new DecimalFormat("###.##");
		JTable table = POS.getTable();
		for(int i = 0; i < table.getRowCount(); i++) {
			amount += Float.valueOf((String) table.getValueAt(i, 2));
		}
		return " "+df.format(amount);
	}
//this function outputs the information to a txt document and to the sales database and updates the quantities left in stock	
	public static void transactionFinished(double total) {
		DecimalFormat df = new DecimalFormat("###.##");
		String reciept = "receipt.txt";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		HashMap<String, Integer> map = new HashMap<>();
		try {
			//this part outputs the info to a receipt
			PrintWriter outputStream = new PrintWriter(reciept);
			outputStream.println("\t Receipt\n");
			outputStream.println("Date: "+formatter.format(date)+"\n");
			JTable table = POS.getTable();
			for(int i = 0; i < table.getRowCount(); i++) {
				outputStream.println("Item: "+table.getValueAt(i, 1)+"\t Price: $"+table.getValueAt(i, 2));
				if(map.containsKey(table.getValueAt(i, 0))){
		            map.put((String) table.getValueAt(i, 0), map.get(table.getValueAt(i, 0)) + 1); 
		        }else {
		        	map.put((String) table.getValueAt(i, 0), 1);
		        }
			}
			//this gets the info from the hash map and stores it into a temporary set so i can pass the variables throgh a query and update the Inventory table inside of the database
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
			outputStream.println("\nSubTotal: "+df.format(total));
			outputStream.println("Total: "+df.format((total * .06) + total));
			outputStream.close();
			//this part updates the sql server inventory by calling a function that queries to the database
			for(int count = 0; count < items.length; count++) {
				try {
					//this calls the functions to update the merchandise database
					updateInventory((Quantities(items[count]) - Quantities[count]),items[count]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			//calls function that adds to sales table
			
			//this part resets the fields of the pos register
			POS.resetFields();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateInventory(int quant, String item) throws SQLException {
		// 1. Establish a Connection
		Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
		// 2. Prepare Statement
		PreparedStatement myStmt = myConn.prepareStatement("UPDATE Merchandise\r\n" + 
				"SET Quantity = ?\r\n" + 
				"WHERE Item_Number =?;");
		//
		myStmt.setInt(1, quant);
		myStmt.setString(2, item);
		//4. execute sql query
		ResultSet myRs = myStmt.executeQuery();
		if(myRs.next() == false) {
			myConn.close();
		}else {
			//POS.setTable(DbUtils.resultSetToTableModel(myRs));
			myConn.close();
		}
	}
	
	public static void refreshSalesTable() throws SQLException {
		// 1. Establish a Connection
		Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
		// 2. Prepare Statement
		PreparedStatement myStmt = myConn.prepareStatement("select * from Sales");
		//4. execute sql query
		ResultSet myRs = myStmt.executeQuery();
		if(myRs.next() == false) {
			myConn.close();
		}else {
			//POS.setTable(DbUtils.resultSetToTableModel(myRs));
			myConn.close();
		}
	}
	
	public static void refreshMerchTable(String item) throws SQLException {
		// 1. Establish a Connection
		Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
		// 2. Prepare Statement
		PreparedStatement myStmt = myConn.prepareStatement("select * from Merchandise");
		//4. execute sql query
		ResultSet myRs = myStmt.executeQuery();
		if(myRs.next() == false) {
			myConn.close();
		}else {
			//POS.setTable(DbUtils.resultSetToTableModel(myRs));
			myConn.close();
		}
	}
	
	public static JPanel login() {
		return Login;
	}
	
	public static JPanel panelHolder() {
		return panelholder;
	}
	
	public static JPanel POS() {
		return PiS;
	}
}
