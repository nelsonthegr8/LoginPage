import java.util.Arrays;

public class Information {
	
	private static String Usernames[] = new String[30];
	private static String Passwords[] = new String[30];
	private static int count = -1;
	private static boolean userNameEntered;
	
	public static void setUsernames(String uname) {
		count++;
		Usernames[count] = uname;
	}
	public static boolean CheckUsername(String name) {
		if(Arrays.asList(Usernames).contains(name) == false) {
			userNameEntered = false;
		}else if(Arrays.asList(Usernames).contains(name) == true) {
			userNameEntered = true;
		}
		return userNameEntered;
	} 
	
}
