import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class functions {
//initialize the variables that will be passed around the application
public static String CurrentusrName = "";
private static JPanel Login = new LogIn();
private static JPanel PoS = new POS();
private static JPanel panelholder = new panelHolder();

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
		}else {
			JOptionPane.showMessageDialog( null , "Login Successful");
			CurrentusrName = uname;
			Main_Window.changePanel(functions.panelholder);
			LogIn.resetFields();
		}
	}
	
	public static ArrayList<Merchandise> MerchandiseList(){
		ArrayList<Merchandise> merch = new ArrayList<>();
		
		return merch;
	}
	
	public static void RefreshTable() {
		
	}
	
	public static JPanel login() {
		return Login;
	}
	
	public static JPanel panelHolder() {
		return panelholder;
	}
	
	public static JPanel POS() {
		return PoS;
	}
}
