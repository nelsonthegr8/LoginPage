import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class functions {
//initialize the variables that will be passed around the application
public static String CurrentusrName = "";
private static JPanel Login = new LogIn();
private static JPanel PiS = new POS();
private static JPanel merch = new Merch();
private static JPanel panelholder = new panelHolder();
private static JPanel purch = new PurchHistory();
private static JPanel Re = new Return();
private static double amount = 0.0;
private static boolean manager = false;
private static int TransactionNum = 0;
private static String Receipt = "";
private static String filePath = "src/Reciepts/";
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
			if(myRs.getString("Manager").trim().equals("Y")) {
				manager = true;
			}
			panelHolder.isManager(manager);
			POS.managerOrNo(manager);
			manager = false;
			Main_Window.changePanel(functions.panelholder);
			LogIn.resetFields();
			myConn.close();
			getLastTransactionNumber();
		}
	}
	
	
	//this function is called when the user tries to log in and it is successful or not
			public static void getLastTransactionNumber() throws SQLException {
				// 1. Establish a Connection
				Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
				// 2. Prepare Statement
				PreparedStatement myStmt = myConn.prepareStatement("SELECT TOP 1 * FROM Sales ORDER BY Transaction_Number DESC");
				//4. execute sql query
				ResultSet myRs = myStmt.executeQuery();
				if(myRs.next() == false) {
					JOptionPane.showMessageDialog( null , "there has been an error connecting to the server");
					myConn.close();
				}else {
					TransactionNum = myRs.getInt("Transaction_Number") + 1;
					Receipt = TransactionNum+".txt";
					myConn.close();
				}
			}
		
	
	
	
	//this function is called when the user tries to log in and it is successful or not
		public static void UserLookUp(String uname) throws SQLException {
			// 1. Establish a Connection
			Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
			// 2. Prepare Statement
			PreparedStatement myStmt = myConn.prepareStatement("select * from Associates where User_Name = ?");
			//3. set the parameters
			myStmt.setString(1, uname);
			//4. execute sql query
			ResultSet myRs = myStmt.executeQuery();
			if(myRs.next() == false) {
				JOptionPane.showMessageDialog( null , "Username is Incorrect");
				myConn.close();
			}else {
				PurchHistory.setFandL(myRs.getString("First_Name"), myRs.getString("Last_Name"));
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
			JOptionPane.showMessageDialog(null, "incorrect item number");
			myConn.close();
		}else {
			
			POS.setTable(myRs.getString("Type"),myRs.getString("Price"));
			myConn.close();
		}
	}
//this function turns finds the item in the table and what row it is and sends it back to pos to be removed
	public static void RemoveItem(String item, String cName) {
		
		JTable table = new JTable();
		if(cName.equals("p")) {
			table = POS.getTable();
		}else if(cName.equals("r")){
			table = Return.getTable();
		}
		 List<String> items = new ArrayList<String>();
		 List<Integer> row = new ArrayList<Integer>();
		 
		for(int i = 0; i < table.getRowCount(); i++) {
			items.add(table.getValueAt(i, 0).toString());
			row.add(i);
		}
	
		if(item.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter an item number.");
		}else if(items.contains(item) && cName.equals("p")) {
			POS.removeItem(row.get(items.indexOf(item)));
		}else if(items.contains(item) && cName.equals("r")){
			Return.removeItem(row.get(items.indexOf(item)));
		}else if(items.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please add items first.");
		}else {
			JOptionPane.showMessageDialog(null, "Please choose an item that has been added to the transaction.");
		}
	
	}
	
	//this functions enters sale into sales table	
		public static void entersSales(String uname, String date, String time, String total, int num, String receipt) throws SQLException {
			// 1. Establish a Connection
			Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
			// 2. Prepare Statement
			PreparedStatement myStmt = myConn.prepareStatement("insert into Sales(Associate,Transaction_Date,Transaction_Time,Total,Transaction_Number,Recipt)\r\n" + 
					"			values(?,?,?,?,?,?)");
			//
			myStmt.setString(1, uname);
			myStmt.setString(2, date);
			myStmt.setString(3, time);
			myStmt.setString(4, total);
			myStmt.setInt(5, num);
			myStmt.setString(6, receipt);
			//4. execute sql query
			myStmt.executeQuery();
			myConn.close();
		}
	
	//this functions gets the amount left in a stock of a product and returns the number from there databse
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
//this function gives a current total of the purchase to the pos screen
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
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		HashMap<String, Integer> map = new HashMap<>();
		JTable table = POS.getTable();
			for(int i = 0; i < table.getRowCount(); i++) {
				if(map.containsKey(table.getValueAt(i, 0))){
		            map.put((String) table.getValueAt(i, 0), map.get(table.getValueAt(i, 0)) + 1); 
		        }else {
		        	map.put((String) table.getValueAt(i, 0), 1);
		        }
			}
			try {
				outputToText(total);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
			try {
				entersSales(CurrentusrName, formatter.format(date), java.time.LocalTime.now().toString(), df.format((total * .06) + total),TransactionNum,Receipt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			//this part resets the fields of the pos register
			POS.resetFields();
			JOptionPane.showMessageDialog( null , "Purchase Successful");
	}
	
	public static void outputToText(double total) throws FileNotFoundException {
		DecimalFormat df = new DecimalFormat("###.##");
		String reciept = "src/Reciepts/"+Receipt;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String[] print = {"src/Reciepts/Receipt"+Receipt,"Reciept.txt"};
		try {
			PrintWriter Remic = new PrintWriter(reciept);
			JTable table = POS.getTable();
			for(int i = 0; i < table.getRowCount(); i++) {
				Remic.println(table.getValueAt(i, 0)+"/"+table.getValueAt(i, 2));
			}
			Remic.close();
		}catch(FileNotFoundException e) {
			
		}
		for(int j = 0; j < print.length; j++) {
		PrintWriter outputStream = new PrintWriter(print[j]);
		outputStream.println("\t \t \t Receipt");
		
		try {
			UserLookUp(CurrentusrName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		outputStream.println("Cashier: "+PurchHistory.getFname()+"\t\t"+"Date: "+formatter.format(date)+"\t Time: "+java.time.LocalTime.now().toString());
		outputStream.println("Transaction Number: "+TransactionNum);
		PurchHistory.resetFields();
		JTable table = POS.getTable();
		for(int i = 0; i < table.getRowCount(); i++) {
			outputStream.println("Item: "+table.getValueAt(i, 1)+"\t Price: $"+table.getValueAt(i, 2));
		}
		outputStream.println("\nSubTotal: "+df.format(total));
		outputStream.println("Total: "+df.format((total * .06) + total));
		outputStream.close();
	}
		}
	//this function updates the inventory table in the databses quantities by removing or adding to the stock quantity
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
		myStmt.executeQuery();
		myConn.close();
	}
	
	public static void refreshSalesTable() throws SQLException {
		// 1. Establish a Connection
		Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
		// 2. Prepare Statement
		PreparedStatement myStmt = myConn.prepareStatement("select * from Sales ORDER BY Transaction_Number ASC");
		//4. execute sql query
		ResultSet myRs = myStmt.executeQuery();
		if(myRs.isBeforeFirst()) {
			PurchHistory.setTable(DbUtils.resultSetToTableModel(myRs));
			myConn.close();
		}else {
			
			myConn.close();
		}
	}
	
	public static void refreshMerchTable() throws SQLException {
		// 1. Establish a Connection
		Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
		// 2. Prepare Statement
		PreparedStatement myStmt = myConn.prepareStatement("select * from Merchandise");
		//4. execute sql query
		ResultSet myRs = myStmt.executeQuery();
		if(myRs.isBeforeFirst()) {
			Merch.setTable(DbUtils.resultSetToTableModel(myRs));
			myConn.close();
		}else {
			
			myConn.close();
		}
	}
	
	public static void transactionLookUp(int num) throws SQLException {
		// 1. Establish a Connection
		Connection myConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Users;integratedSecurity=true");
		// 2. Prepare Statement
		PreparedStatement myStmt = myConn.prepareStatement("select * from Sales where Transaction_Number = ?");
		//
		myStmt.setInt(1, num);
		//4. execute sql query
		ResultSet myRs = myStmt.executeQuery();
		if(myRs.next()) {
			Return.fileExist();
			
			//Runtime rs = Runtime.getRuntime();
		    ProcessBuilder rs = new ProcessBuilder("notepad",filePath+"Receipt"+num+".txt");
		    try {
		      rs.start();
		    }
		    catch (IOException e) {
		      System.out.println(e);
		    }
		    
			myConn.close();
		}else {
			Return.fileNonExist();
			myConn.close();
		}
	}
	
	public static void AddInventory() {
		JTable table = Merch.getTable();
		String[] items = new String[table.getRowCount()];
		int[] Quantities = new int[table.getRowCount()];
		
		for(int i = 0; i < table.getRowCount(); i++) {
		//this loops through the table and sets all the values to the quantities and items to throw into the sql querie
			Quantities[i] = Integer.valueOf(table.getValueAt(i, 3).toString());
			items[i] = table.getValueAt(i, 0).toString();
		}
		
		for(int count = 0; count < items.length; count++) {
			try {
				//this calls the functions to update the merchandise database
				updateInventory(Quantities[count],items[count]);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	
	public static void returnFunction(String text, String item) {
		 List<String> list = new ArrayList<String>(); 
		    List<String> pricelist = new ArrayList<String>();
		    List<String> itemNum = new ArrayList<String>();
		    File file = new File(text+".txt");
		    
		    if(file.exists()){
		        try { 
		            list = Files.readAllLines(file.toPath(),Charset.defaultCharset());
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		      if(list.isEmpty())
		          return;
		    }
		    
		    for(String line : list){
		        String [] res = line.split("/");
		        itemNum.add(res[0]);
		        
		        pricelist.add(res[1]);
		        
		    }
		   
		    if(itemNum.contains(item)) {
		    	Return.setTable(itemNum.get(itemNum.indexOf(item)), pricelist.get(itemNum.indexOf(item)));
		    }else {
				JOptionPane.showMessageDialog(null,"The item number entered is not on the reciept try again");
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
	public static JPanel Merch() {
		return merch;
	}
	public static JPanel PurchHistory() {
		return purch;
	}
	public static JPanel Return() {
		return Re;
	}
	public static int TransactionNum() {
		return TransactionNum;
	}
	public static void addTransactionNum() {
		TransactionNum++;
		Receipt = TransactionNum+".txt";
	}
}
