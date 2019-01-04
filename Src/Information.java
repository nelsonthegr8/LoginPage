import java.util.Arrays;

import javax.swing.JOptionPane;

public class Information {
	
	private static String Usernames[] = {"Nelsonthegr8","William12","","","","","","","",""};
	private static String Passwords[] = {"HelloPass13","Skittles","","","","","","","",""};
	private static int count = 1;
	private static boolean userNameEntered;
	private static String aboutUsr[] = {" Hey Nelson, move in step with the Holy Spirit. Hold on to \n these Scriptures today,\n Psalm 118:6 \n \"The Lord is with me; I will not be afraid. What can mere \n mortals do to me? \" What can they Do!!!!??? \n\n Proverbs 16:9 \n \" In their hearts humans plan their course, but the Lord \n establishes their steps.\" ",
			" Hello bow aoow",
			" "," "," "," "," "," "," "," "};
	private static int UsrloginNum;

	
	public static void setUsernames(String uname) {
		if(count < 10) {
		count++;
		Usernames[count] = uname;
		}else JOptionPane.showMessageDialog(null, "There are enough users in the database wait untill one quits");
	}
	
	public static void setPassword(String pname) {
		Passwords[count] = pname;
	}
	public static boolean CheckUsername(String name) {
		if(Arrays.asList(Usernames).contains(name) == false) {
			userNameEntered = false;
		}else if(Arrays.asList(Usernames).contains(name) == true) {
			userNameEntered = true;
		}
		return userNameEntered;
	} 
	
	public static String[] getUsernames () {
		return Usernames;
	}
	
	public static String[] getPasswords () {
		return Passwords;
	}
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount() {
		count++;
	}
	
	public static String getAboutusr() {
		return aboutUsr[UsrloginNum];
	}
	
	public static void setAboutusr(String aboutMe) {
		aboutUsr[count] = aboutMe;
	}
	
	public static void setUsrloginnum(String uname) {
		UsrloginNum = Arrays.asList(Usernames).indexOf(uname);
	}
	
	public static int getUsrloginnum() {
		return UsrloginNum;
	}
	
	public static String getUsername() {
		return Usernames[UsrloginNum];
	}
	
	
	
}
